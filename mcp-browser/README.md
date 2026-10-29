# MCP Browser

An Android browser that only goes where an AI tells it to.

- Opening the app starts an MCP server at `http://<phone-ip>:8765/mcp` (Streamable HTTP). The address is shown on screen.
- One tool: `open_url(url)`.
- The browser blocks every navigation the page starts (tapping links, scripts). HTTP redirects of a page the AI opened are allowed.
- The server runs only while the app is open. No auth: anyone on the same network can open links.

Build: `./gradlew assembleDebug` → `build/outputs/apk/debug/mcp-browser-debug.apk`,
or download the `mcp-browser-apk` artifact from the GitHub Actions run.
