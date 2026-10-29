# MCP Browser

An Android browser that only goes where an AI tells it to, for reading with a purpose.

- Opening the app starts an MCP server at `http://<phone-ip>:8765/mcp` (Streamable HTTP), shown at the top until the first page opens.
- One tool: `open_url(url, intention)`. The intention is required and is shown at the top with the time spent on it.
- The page can't navigate by itself: taps never reach it, and script navigations are blocked. HTTP redirects of a page the AI opened are allowed.
- Tap text to annotate: the keyboard opens with a caret at that spot; type, then tap anywhere else to save. Tap an annotation to edit it. Annotations come back when you revisit the page.
- Cookies are kept by the browser between runs.
- The server runs only while the app is open. No auth: anyone who can reach the address can open links.

## Settings

App info → **Additional settings in the app** (there is no menu in the app):

- **Log file**: choose or create the file everything is appended to.
- **Import cookies.txt**: imports cookies in the Netscape `cookies.txt` format, e.g. to stay logged in to sites. Delete the file after importing; it works like your passwords.

## Log

One JSON object per line, appended in order. Each annotation belongs to the page line above it; `id` ties an update to its annotation.

```
{"time":"2026-10-29T14:02:13-04:00","type":"page","intention":"compare tents","url":"https://…","title":"…"}
{"time":"…","type":"annotation","action":"create","id":"mh3k2x9a1b","text":"too heavy","prefix":"…","suffix":"…","offset":1234}
{"time":"…","type":"annotation","action":"update","id":"mh3k2x9a1b","text":"too heavy, but cheap","prefix":"…","suffix":"…","offset":1234}
```

`prefix` and `suffix` are the page text on either side of the annotation; `offset` is its position in the page text. An update with empty `text` deletes the annotation.

## Build

`./gradlew assembleDebug` → `build/outputs/apk/debug/mcp-browser-debug.apk`,
or download the `mcp-browser-apk` artifact from the GitHub Actions run.
