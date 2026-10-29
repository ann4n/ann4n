package app.mcpbrowser;

import android.app.Activity;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.webkit.CookieManager;
import android.webkit.JavascriptInterface;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;
import java.util.UUID;

/**
 * A WebView that only navigates when told to by an MCP client.
 * MCP endpoint (Streamable HTTP): http://<phone-ip>:8765/mcp, one tool: open_url(url, intention).
 * Tapping the page annotates it (annotate.js). Each page opened (with its intention) and each annotation
 * is appended as a JSON line to one log file chosen in SettingsActivity; an annotation belongs to the
 * page line above it.
 */
public class MainActivity extends Activity {
    static final int PORT = 8765;
    /** Only our injected script knows this, so pages can't write annotations. */
    static final String TOKEN = UUID.randomUUID().toString();

    SharedPreferences prefs;
    WebView web;
    TextView bar;
    ServerSocket server;
    String address;
    String annotateJs;
    boolean historyLogged = true;
    final Handler handler = new Handler(Looper.getMainLooper());
    final Runnable tick = new Runnable() {
        @Override
        public void run() {
            showBar();
            handler.postDelayed(this, 1000);
        }
    };

    @Override
    protected void onCreate(Bundle state) {
        super.onCreate(state);
        prefs = getSharedPreferences(SettingsActivity.PREFS, MODE_PRIVATE);
        address = "http://" + ip() + ":" + PORT + "/mcp";
        annotateJs = readAsset("annotate.js");

        bar = new TextView(this);
        bar.setSingleLine(true);
        bar.setEllipsize(TextUtils.TruncateAt.END);
        bar.setTextSize(13);
        bar.setPadding(24, 8, 24, 8);

        web = new WebView(this);
        web.getSettings().setJavaScriptEnabled(true);
        web.getSettings().setDomStorageEnabled(true);
        web.addJavascriptInterface(new Bridge(), "MCPBrowserNative");
        web.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                // Block every navigation the page starts (taps, scripts); allow HTTP redirects
                // of a page the MCP client opened. loadUrl() calls never pass through here.
                return !request.isRedirect();
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                if (!url.startsWith("http")) return;
                if (!historyLogged) {
                    historyLogged = true;
                    log("type", "page", "intention", intention(), "url", url, "title", view.getTitle());
                }
                view.evaluateJavascript(annotateJs + "(" + savedAnnotations(url) + ", '" + TOKEN + "')", null);
            }
        });

        LinearLayout root = new LinearLayout(this);
        root.setOrientation(LinearLayout.VERTICAL);
        root.setFitsSystemWindows(true);
        root.addView(bar);
        root.addView(web, new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 0, 1));
        setContentView(root);

        if (prefs.getString(SettingsActivity.LOG, null) == null) {
            web.loadData("<p>Choose a log file: App info &rarr; Additional settings in the app.</p>", "text/html", null);
        }
        new Thread(this::serve).start();
    }

    @Override
    protected void onResume() {
        super.onResume();
        handler.post(tick);
    }

    @Override
    protected void onPause() {
        handler.removeCallbacks(tick);
        web.evaluateJavascript("document.activeElement && document.activeElement.blur()", null); // saves an open annotation
        CookieManager.getInstance().flush();
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        try {
            if (server != null) server.close();
        } catch (IOException ignored) {
        }
        web.destroy();
        super.onDestroy();
    }

    String intention() {
        return prefs.getString("intention", null);
    }

    void open(String url, String intention) {
        if (!intention.equals(intention())) {
            prefs.edit().putString("intention", intention).putLong("intentionStart", System.currentTimeMillis()).apply();
        }
        historyLogged = false;
        web.loadUrl(url);
        showBar();
    }

    /** Elapsed time and intention, or the MCP address before the first intention. */
    void showBar() {
        String intention = intention();
        if (intention == null) {
            bar.setText("MCP: " + address);
            return;
        }
        long s = (System.currentTimeMillis() - prefs.getLong("intentionStart", 0)) / 1000;
        bar.setText(String.format(Locale.US, "%d:%02d  %s", s / 60, s % 60, intention));
    }

    class Bridge {
        @JavascriptInterface
        public void save(String token, String json) {
            if (!TOKEN.equals(token)) return;
            try {
                JSONObject a = new JSONObject(json);
                log("type", "annotation", "action", a.getString("action"), "id", a.getString("id"),
                        "text", a.getString("text"),
                        "prefix", a.getString("prefix"), "suffix", a.getString("suffix"), "offset", a.getInt("offset"));
            } catch (JSONException ignored) {
            }
        }
    }

    /** Latest version of each annotation on this page, from the log. */
    String savedAnnotations(String url) {
        String page = url.split("#")[0];
        String current = null;
        Map<String, JSONObject> latest = new LinkedHashMap<>();
        String uri = prefs.getString(SettingsActivity.LOG, null);
        if (uri != null) {
            try (BufferedReader r = new BufferedReader(new InputStreamReader(
                    getContentResolver().openInputStream(Uri.parse(uri)), StandardCharsets.UTF_8))) {
                for (String line; (line = r.readLine()) != null; ) {
                    try {
                        JSONObject a = new JSONObject(line);
                        if (a.optString("type").equals("page")) current = a.getString("url").split("#")[0];
                        else if (a.optString("type").equals("annotation") && page.equals(current)) {
                            latest.put(a.getString("id"), a);
                        }
                    } catch (JSONException ignored) {
                    }
                }
            } catch (Exception ignored) {
            }
        }
        return new JSONArray(latest.values()).toString();
    }

    /** Appends {"time": now, key: value, ...} to the log. */
    void log(Object... keyValues) {
        try {
            JSONObject record = new JSONObject()
                    .put("time", new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssXXX", Locale.US).format(new Date()));
            for (int i = 0; i < keyValues.length; i += 2) record.put((String) keyValues[i], keyValues[i + 1]);
            append(record + "\n");
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }

    synchronized void append(String line) {
        String uri = prefs.getString(SettingsActivity.LOG, null);
        try (OutputStream out = getContentResolver().openOutputStream(Uri.parse(uri), "wa")) {
            out.write(line.getBytes(StandardCharsets.UTF_8));
        } catch (Exception e) {
            runOnUiThread(() -> Toast.makeText(this, uri == null
                    ? "Not saved: choose a log file in App info → Additional settings in the app"
                    : "Couldn't write to the log file", Toast.LENGTH_LONG).show());
        }
    }

    String readAsset(String name) {
        try (InputStream in = getAssets().open(name)) {
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            byte[] buf = new byte[8192];
            for (int n; (n = in.read(buf)) > 0; ) out.write(buf, 0, n);
            return out.toString("UTF-8");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    void serve() {
        try {
            server = new ServerSocket(PORT);
            while (true) {
                Socket s = server.accept();
                new Thread(() -> handle(s)).start();
            }
        } catch (IOException ignored) {
            // Socket closed in onDestroy.
        }
    }

    void handle(Socket socket) {
        try (Socket s = socket) {
            InputStream in = new BufferedInputStream(s.getInputStream());
            String requestLine = readLine(in);
            int length = 0;
            for (String h; !(h = readLine(in)).isEmpty(); ) {
                if (h.toLowerCase(Locale.ROOT).startsWith("content-length:")) {
                    length = Integer.parseInt(h.substring(15).trim());
                }
            }
            byte[] body = new byte[length];
            new DataInputStream(in).readFully(body);

            if (!requestLine.startsWith("POST ")) {
                respond(s, "405 Method Not Allowed", null);
                return;
            }
            JSONObject response = rpc(new JSONObject(new String(body, StandardCharsets.UTF_8)));
            respond(s, response == null ? "202 Accepted" : "200 OK", response);
        } catch (Exception ignored) {
        }
    }

    /** Handles one JSON-RPC message; returns null for notifications. */
    JSONObject rpc(JSONObject req) throws JSONException {
        if (!req.has("id")) return null;
        String method = req.getString("method");
        JSONObject params = req.optJSONObject("params");
        if (params == null) params = new JSONObject();

        JSONObject result;
        switch (method) {
            case "initialize":
                result = new JSONObject()
                        .put("protocolVersion", params.optString("protocolVersion", "2025-06-18"))
                        .put("capabilities", new JSONObject().put("tools", new JSONObject()))
                        .put("serverInfo", new JSONObject().put("name", "mcp-browser").put("version", "1"));
                break;
            case "tools/list":
                result = new JSONObject().put("tools", new JSONArray().put(new JSONObject()
                        .put("name", "open_url")
                        .put("description", "Open a URL in the browser on the user's phone.")
                        .put("inputSchema", new JSONObject()
                                .put("type", "object")
                                .put("properties", new JSONObject()
                                        .put("url", new JSONObject().put("type", "string"))
                                        .put("intention", new JSONObject()
                                                .put("type", "string")
                                                .put("description", "What the user is opening this page to do,"
                                                        + " in a few words. Shown on their screen with a timer.")))
                                .put("required", new JSONArray().put("url").put("intention")))));
                break;
            case "tools/call":
                JSONObject args = params.getJSONObject("arguments");
                String url = args.getString("url");
                String intention = args.optString("intention").trim();
                if (intention.isEmpty()) {
                    result = toolResult("Not opened: name the intention, what the user is opening this page to do.", true);
                    break;
                }
                runOnUiThread(() -> open(url, intention));
                result = toolResult("Opened " + url, false);
                break;
            case "ping":
                result = new JSONObject();
                break;
            default:
                return new JSONObject()
                        .put("jsonrpc", "2.0")
                        .put("id", req.get("id"))
                        .put("error", new JSONObject().put("code", -32601).put("message", "Method not found"));
        }
        return new JSONObject().put("jsonrpc", "2.0").put("id", req.get("id")).put("result", result);
    }

    static JSONObject toolResult(String text, boolean isError) throws JSONException {
        return new JSONObject()
                .put("content", new JSONArray().put(new JSONObject().put("type", "text").put("text", text)))
                .put("isError", isError);
    }

    static void respond(Socket s, String status, JSONObject body) throws IOException {
        byte[] b = body == null ? new byte[0] : body.toString().getBytes(StandardCharsets.UTF_8);
        OutputStream out = s.getOutputStream();
        out.write(("HTTP/1.1 " + status + "\r\n"
                + "Content-Type: application/json\r\n"
                + "Content-Length: " + b.length + "\r\n"
                + "Connection: close\r\n\r\n").getBytes(StandardCharsets.UTF_8));
        out.write(b);
        out.flush();
    }

    static String readLine(InputStream in) throws IOException {
        ByteArrayOutputStream line = new ByteArrayOutputStream();
        for (int c; (c = in.read()) != -1 && c != '\n'; ) {
            if (c != '\r') line.write(c);
        }
        return line.toString("UTF-8");
    }

    /** First private IPv4 address, i.e. the phone's Wi-Fi address on most networks. */
    static String ip() {
        try {
            for (NetworkInterface ni : Collections.list(NetworkInterface.getNetworkInterfaces())) {
                for (InetAddress a : Collections.list(ni.getInetAddresses())) {
                    if (a instanceof Inet4Address && a.isSiteLocalAddress()) return a.getHostAddress();
                }
            }
        } catch (IOException ignored) {
        }
        return "localhost";
    }
}
