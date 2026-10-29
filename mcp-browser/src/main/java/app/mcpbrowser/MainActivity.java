package app.mcpbrowser;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Collections;

/**
 * A WebView that only navigates when told to by an MCP client.
 * MCP endpoint (Streamable HTTP): http://<phone-ip>:8765/mcp, one tool: open_url.
 */
public class MainActivity extends Activity {
    static final int PORT = 8765;

    WebView web;
    ServerSocket server;

    @Override
    protected void onCreate(Bundle state) {
        super.onCreate(state);
        web = new WebView(this);
        web.getSettings().setJavaScriptEnabled(true);
        web.getSettings().setDomStorageEnabled(true);
        web.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                // Block every navigation the page starts (taps, scripts); allow HTTP redirects
                // of a page the MCP client opened. loadUrl() calls never pass through here.
                return !request.isRedirect();
            }
        });
        setContentView(web);
        web.loadData("<h2>MCP: http://" + ip() + ":" + PORT + "/mcp</h2>", "text/html", null);
        new Thread(this::serve).start();
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
                if (h.toLowerCase().startsWith("content-length:")) {
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
                                        .put("url", new JSONObject().put("type", "string")))
                                .put("required", new JSONArray().put("url")))));
                break;
            case "tools/call":
                String url = params.getJSONObject("arguments").getString("url");
                runOnUiThread(() -> web.loadUrl(url));
                result = new JSONObject().put("content", new JSONArray().put(new JSONObject()
                        .put("type", "text")
                        .put("text", "Opened " + url)));
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
