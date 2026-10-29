package app.mcpbrowser;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.OpenableColumns;
import android.webkit.CookieManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

/** Only reachable from App info → "Additional settings in the app". */
public class SettingsActivity extends Activity {
    static final String PREFS = "mcpbrowser";
    static final String LOG = "log";
    static final int CHOOSE_LOG = 1, NEW_LOG = 2, IMPORT_COOKIES = 3;

    SharedPreferences prefs;
    TextView logLabel;

    @Override
    protected void onCreate(Bundle state) {
        super.onCreate(state);
        prefs = getSharedPreferences(PREFS, MODE_PRIVATE);

        LinearLayout list = new LinearLayout(this);
        list.setOrientation(LinearLayout.VERTICAL);
        list.setFitsSystemWindows(true);
        list.setPadding(48, 48, 48, 48);
        logLabel = new TextView(this);
        list.addView(logLabel);
        LinearLayout logButtons = new LinearLayout(this);
        logButtons.addView(button("Choose", CHOOSE_LOG, new Intent(Intent.ACTION_OPEN_DOCUMENT).setType("*/*")));
        logButtons.addView(button("New", NEW_LOG, new Intent(Intent.ACTION_CREATE_DOCUMENT)
                .setType("application/octet-stream") // keeps the .jsonl name as is
                .putExtra(Intent.EXTRA_TITLE, "mcp-browser.jsonl")));
        list.addView(logButtons);
        list.addView(button("Import cookies.txt", IMPORT_COOKIES, new Intent(Intent.ACTION_OPEN_DOCUMENT).setType("*/*")));
        setContentView(list);
        showLog();
    }

    void showLog() {
        String uri = prefs.getString(LOG, null);
        logLabel.setText("Log file: " + (uri == null ? "not set" : name(Uri.parse(uri))));
    }

    Button button(String text, int request, Intent intent) {
        Button b = new Button(this);
        b.setText(text);
        b.setOnClickListener(v -> startActivityForResult(intent.addCategory(Intent.CATEGORY_OPENABLE), request));
        return b;
    }

    @Override
    protected void onActivityResult(int request, int result, Intent data) {
        if (result != RESULT_OK || data == null || data.getData() == null) return;
        Uri uri = data.getData();
        if (request == IMPORT_COOKIES) {
            importCookies(uri);
            return;
        }
        try {
            getContentResolver().takePersistableUriPermission(uri,
                    Intent.FLAG_GRANT_READ_URI_PERMISSION | Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
        } catch (SecurityException e) {
            Toast.makeText(this, "That file can't be written to", Toast.LENGTH_LONG).show();
            return;
        }
        prefs.edit().putString(LOG, uri.toString()).apply();
        showLog();
    }

    /** Netscape cookies.txt: domain, subdomains, path, secure, expiry, name, value (tab separated). */
    void importCookies(Uri uri) {
        CookieManager cookies = CookieManager.getInstance();
        long now = System.currentTimeMillis() / 1000;
        int count = 0;
        try (BufferedReader r = new BufferedReader(new InputStreamReader(
                getContentResolver().openInputStream(uri), StandardCharsets.UTF_8))) {
            for (String line; (line = r.readLine()) != null; ) {
                boolean httpOnly = line.startsWith("#HttpOnly_");
                if (httpOnly) line = line.substring("#HttpOnly_".length());
                String[] f = line.split("\t", -1);
                if (line.startsWith("#") || f.length != 7) continue;
                long expiry = Long.parseLong(f[4]);
                if (expiry != 0 && expiry <= now) continue;
                boolean secure = f[3].equals("TRUE");
                String host = f[0].startsWith(".") ? f[0].substring(1) : f[0];
                cookies.setCookie((secure ? "https://" : "http://") + host + f[2], f[5] + "=" + f[6]
                        + (f[1].equals("TRUE") ? "; Domain=" + f[0] : "")
                        + "; Path=" + f[2]
                        + (expiry != 0 ? "; Max-Age=" + (expiry - now) : "")
                        + (secure ? "; Secure" : "")
                        + (httpOnly ? "; HttpOnly" : ""));
                count++;
            }
            cookies.flush();
            Toast.makeText(this, "Imported " + count + " cookies. Delete the cookies file now.", Toast.LENGTH_LONG).show();
        } catch (Exception e) {
            Toast.makeText(this, "Couldn't read that cookies file", Toast.LENGTH_LONG).show();
        }
    }

    String name(Uri uri) {
        try (Cursor c = getContentResolver().query(uri, new String[]{OpenableColumns.DISPLAY_NAME}, null, null, null)) {
            if (c != null && c.moveToFirst()) return c.getString(0);
        } catch (Exception ignored) {
        }
        return uri.getLastPathSegment();
    }
}
