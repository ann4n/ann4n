# Multi-account Claude Code: mechanics, prompt-caching cost, and terms-of-service risk (as of 2026-10-30)

Labels used below: **[Official]** = Anthropic docs/legal pages; **[Staff]** = Anthropic employee public statement; **[Community]** = GitHub issues/READMEs/forums; **[Press/Secondary]** = news or blogs; **[Historical]** = no longer current or pre-2026. Inferences are only in the "Inferences" subsections.

## Q1. How do people run multiple Claude accounts in Claude Code today (config dirs vs credential swapping, where credentials live, /login and /logout)?

### Takeaway
Anthropic officially documents one method: give each account its own `CLAUDE_CONFIG_DIR` (for example a shell alias per account). Everything else, such as in-place credential swapping, automatic rotation and proxies, comes from community tools. Credentials live in the macOS Keychain, keyed per config dir, or in `<config dir>/.credentials.json` on Linux and Windows. Claude Code still has no native `auth switch`, and the feature request has been open since March 2026 with no Anthropic response.

### Cited Findings
- **[Official]** The Claude Code docs have a "Log in with multiple accounts" section: "To stay signed in to multiple accounts at once, such as work and personal accounts, give each account its own configuration directory… set the `CLAUDE_CONFIG_DIR` environment variable… Each directory has its own settings, session history, and claude.ai login or API key." Their example is `alias claude-work='CLAUDE_CONFIG_DIR=~/.claude-work claude'`, and the first run in a new dir walks through login and setup again. — [Authentication docs](https://code.claude.com/docs/en/authentication)
- **[Official]** `CLAUDE_CONFIG_DIR` env var: "Override the configuration directory (default: `~/.claude`). All settings, session history, and plugins are stored under this path… Useful for running multiple accounts side by side." It can be set in the shell, user settings, or managed settings, but it is "Ignored in project and local settings". — [Env vars reference](https://code.claude.com/docs/en/env-vars)
- **[Official]** Where credentials are stored:
  - **macOS:** the encrypted Keychain. When the Keychain rejects the write (e.g., locked in an SSH session), the login goes to `~/.claude/.credentials.json` (mode 0600) instead.
  - **Linux:** `~/.claude/.credentials.json`, mode 0600.
  - **Windows:** `%USERPROFILE%\.claude\.credentials.json`, protected by user-profile ACLs.
  - **With `CLAUDE_CONFIG_DIR` set:** Claude Code "keeps the `.credentials.json` file under that directory instead… and keys the macOS Keychain entry to that directory too, so a session with a different `CLAUDE_CONFIG_DIR` reads a different entry." — [Authentication docs](https://code.claude.com/docs/en/authentication)
- **[Community]** macOS Keychain entry naming: with a custom config dir, the entry is `Claude Code-credentials-<8 hex>`, where the suffix is `sha256(CLAUDE_CONFIG_DIR)[0..8]`. Commenters verified two accounts staying signed in at once on CLI 2.1.220 and 2.1.252. — [GitHub issue #30031](https://github.com/anthropics/claude-code/issues/30031)
- **[Official]** `.claude.json` (account details, app state, personal MCP servers) normally sits at `~/.claude.json`. When `CLAUDE_CONFIG_DIR` is set it moves under that directory: "every `~/.claude` path on this page lives under that directory instead. The same applies to `.claude.json`." — [.claude directory docs](https://code.claude.com/docs/en/claude-directory)
- **[Official]** `/logout` "Logging out also resets your first-launch setup state, so the next time you run `claude` it walks you through login and setup again." Claude Code manages `.credentials.json` through `/login` and `/logout`. `/status` shows which credential is active, plus the organization and email. — [Authentication docs](https://code.claude.com/docs/en/authentication)
- **[Official]** Credential precedence, highest first:
  1. Cloud provider env vars
  2. `ANTHROPIC_AUTH_TOKEN`
  3. `ANTHROPIC_API_KEY`
  4. `apiKeyHelper`
  5. `CLAUDE_CODE_OAUTH_TOKEN`
  6. Anthropic profiles
  7. Subscription OAuth from `/login`, "the default for Claude Pro, Max, Team, and Enterprise users"

  `claude setup-token` mints a **one-year** OAuth token for `CLAUDE_CODE_OAUTH_TOKEN`. It "requires a Pro, Max, Team, or Enterprise plan" and "can only make model requests". — [Authentication docs](https://code.claude.com/docs/en/authentication)
- **[Official]** One caveat to separate dirs: they "don't keep two Claude Console sign-ins without an API key apart", because that credential type is stored outside the config directory. — [Authentication docs](https://code.claude.com/docs/en/authentication)
- **[Official]** Login lifetime: a warning appears 3 days before expiry. Once a login has expired and can't refresh, every request fails with `Login expired · Please run /login`. Unattended background or Remote Control sessions "can't recover until you sign in again". — [Authentication docs](https://code.claude.com/docs/en/authentication)
- **[Community]** Rough edges reported in #30031 (open since 2026-03-02, about 79 thumbs-up, labels `area:auth`/`enhancement`, no Anthropic staff reply):
  - The Desktop app ignores `CLAUDE_CONFIG_DIR`.
  - The VS Code extension host resolves its own config dir, so session lists and plugins can leak across accounts (#30538).
  - JetBrains and VS Code may launch `claude` without sourcing shell rc files, which silently selects the default account.
  - One Linux/WSL2 user saw a second profile fall back to `~/.claude/.credentials.json`. This was unresolved.
  - Logging a dir into the wrong identity fails silently.

  — [GitHub issue #30031](https://github.com/anthropics/claude-code/issues/30031)
- **[Community]** From a commenter's reading of the 2.1.285 binary: a running session uses a cached access token and only re-reads credentials when that token is within about 120 s of expiry. A 401 invalidates the cache, but a **429 rate-limit does not**. External swap tools therefore cannot redirect a running session when it hits a limit, and the swap takes effect at token refresh or on restart. — [GitHub issue #30031](https://github.com/anthropics/claude-code/issues/30031)
- **[Community]** Two families of community tools exist:
  - **Per-config-dir launchers:**
    - [SaschaHeyer/claude-switch](https://github.com/SaschaHeyer/claude-switch): `~/.claude-work`, `~/.claude-personal`.
    - [Nemo-Illusionist/claude-code-account-switcher](https://github.com/Nemo-Illusionist/claude-code-account-switcher/wiki): binds accounts to directories ("You don't switch accounts — you cd").
    - claude-profile and claude-acc, listed in [#30031](https://github.com/anthropics/claude-code/issues/30031).
  - **In-place credential swappers that keep a single `~/.claude`:**
    - [realiti4/claude-swap](https://github.com/realiti4/claude-swap): about 4.3k stars, also on PyPI.
    - [Symbioose/claude-account-switcher](https://github.com/Symbioose/claude-account-switcher): macOS menu bar app that stores backups in the Keychain.
    - [fairy-pitta/cc-account-switcher](https://github.com/fairy-pitta/cc-account-switcher): macOS, Linux and WSL; named profiles, directory auto-switch, rate-limit switch via hooks.
    - [jeck00119/claude-codex-switcher](https://github.com/jeck00119/claude-codex-switcher): encrypted, with export/import.
    - Janus and hats (macOS), listed in #30031.
- **[Community]** How claude-swap works:
  - It "Swaps only the account-specific Claude login" and preserves account-independent OAuth state such as MCP logins.
  - It holds Claude Code's own credential locks while writing.
  - It stores backups in the Keychain on macOS, and in files under `~/.claude-swap-backup/` (Windows) or `$XDG_DATA_HOME/claude-swap/` (Linux).
  - Its README warns: "Do not run `/logout` first: current Claude Code may revoke the refresh token stored for the account you are leaving."

  — [claude-swap README](https://github.com/realiti4/claude-swap)

### Inferences
- There are two workable patterns:
  - **(a) One config dir per account.** Isolation is clean and accounts can run in parallel, but global config, history and memory split apart (see Q2).
  - **(b) One shared `~/.claude` with credentials swapped in place,** or one process per account launched with its own `CLAUDE_CODE_OAUTH_TOKEN`. Transcripts, skills and memory are naturally shared, but you have to manage credential races and restarts.
- Launching each worker with `CLAUDE_CODE_OAUTH_TOKEN=<token from claude setup-token>` on one shared config dir looks like the simplest way to put different processes on different accounts without duplicating config. This combination is not documented, so test it.
- The default macOS Keychain entry for `~/.claude` is presumably the unsuffixed `Claude Code-credentials`. This research did not verify that name.

### Gaps
- No official doc says whether `/logout` on a claude.ai subscription login revokes the refresh token server-side. Only the claude-swap README warns about it, while the docs say `/logout` "removes and revokes" the credential only in the Console keyless-profile context.
- The Linux/WSL fallback bug (second profile reading `~/.claude/.credentials.json`) had no resolution or version fix in the thread.

## Q2. What lives in the config directory vs. the repo, what carries over across accounts, and how do community tools handle the rest?

### Takeaway
Everything "global" sits in the config dir: settings, user CLAUDE.md, skills, agents, commands, plugins, MCP servers in `.claude.json`, transcripts, auto memory and prompt history. Anything committed in the repo (`.claude/`, CLAUDE.md, `.mcp.json`) works under any account. With one config dir per account, nothing global carries over unless you symlink or sync it. With in-place swapping, everything carries over except the login itself.

### Cited Findings
- **[Official]** Global scope (`~/.claude/` or `CLAUDE_CONFIG_DIR`) holds:
  - `CLAUDE.md`, `rules/*.md`, `settings.json`
  - `skills/<name>/SKILL.md`, `commands/*.md`, `output-styles/*.md`, `agents/*.md`, `workflows/*.js`, `agent-memory/<name>/`
  - `keybindings.json`, `themes/*.json`, `plugins/` (including `installed_plugins.json` and `plugins/synced/` for plugins synced from the claude.ai account)
  - `.claude.json`: "App state, account details, UI toggles, personal MCP servers"

  — [.claude directory docs](https://code.claude.com/docs/en/claude-directory)
- **[Official]** Session and app data, also global:
  - Transcripts: `projects/<project>/<session>.jsonl`, plus `<session>/subagents/` and `tool-results/`.
  - **Auto memory:** `projects/<project>/memory/`.
  - `history.jsonl` (up-arrow and Ctrl+R prompt history), `file-history/` (checkpoints), `plans/`, `tasks/`, `stats-cache.json` (`/usage` totals).
  - Account-tied caches: `remote-settings.json` and `policy-limits.json`. The latter has a `.stamp.json` "which account or API key the cache belongs to". Both are deleted on logout.
  - Transcripts and most session data are swept after `cleanupPeriodDays` (default 30). Auto memory is not swept.

  — [.claude directory docs](https://code.claude.com/docs/en/claude-directory)
- **[Official]** Project scope, committed to the repo: `CLAUDE.md`, `CLAUDE.local.md` (gitignored), `.claude/settings.json`, `.claude/settings.local.json`, `.mcp.json`, `.claude/skills/`, `commands/`, `agents/`, `rules/`, `workflows/`, `agent-memory/`, `.worktreeinclude`, and `AGENTS.md`. — [.claude directory docs](https://code.claude.com/docs/en/claude-directory)
- **[Official]** Transcript location: "By default, Claude Code stores transcripts as JSONL at `~/.claude/projects/<project>/<session-id>.jsonl`". Moving storage off `~/.claude` is done with `CLAUDE_CONFIG_DIR`. `CLAUDE_CODE_PROJECT_DIR_NAME` (v2.1.234+) pins the `<project>` folder name, but only together with `CLAUDE_CONFIG_DIR`. — [Sessions docs](https://code.claude.com/docs/en/sessions)
- **[Official]** If you resume the same session in two terminals without forking, "messages from both interleave into one transcript." — [Sessions docs](https://code.claude.com/docs/en/sessions)
- **[Community]** Drift across config dirs: each dir "has its own settings, plugins, and skills". MCP servers added with `claude mcp add` live in `.claude.json` and don't carry over. `known_marketplaces.json` and `installed_plugins.json` contain absolute paths, so they need per-dir copies. — [GitHub issue #30031](https://github.com/anthropics/claude-code/issues/30031)
- **[Community]** One user's workaround is to symlink `settings.json`, `skills/`, `commands/`, `agents/` and the plugin folders across config dirs, while keeping `.claude.json` and `.credentials.json` separate. Symlink-swapping `.claude.json` and `.credentials.json` themselves is "not parallel-safe". — [GitHub issue #30031](https://github.com/anthropics/claude-code/issues/30031)
- **[Community]** Desktop stores Code-tab session records per account and organization under `claude-code-sessions`, while transcripts in `~/.claude/projects` are shared. Switching accounts therefore hides history rather than losing it. A tool called claude-transplant moves Desktop session records between accounts on macOS. — [GitHub issue #30031](https://github.com/anthropics/claude-code/issues/30031)
- **[Community]** claude-swap "session mode" (`cswap run <account>`) uses "your normal `~/.claude` setup (settings, CLAUDE.md, skills, MCP servers, etc.)". Each account keeps its own chat history unless `--share-history` is passed. User-scope MCP servers are mirrored from the default profile on each launch, but MCP OAuth logins are not copied. — [claude-swap README](https://github.com/realiti4/claude-swap)

### Inferences
- **What carries over with one config dir per account and no symlinks:** only repo-level files (project CLAUDE.md, `.claude/` skills, agents, commands and settings, `.mcp.json`).
- **What does not carry over:**
  - user CLAUDE.md and user settings, hooks and permissions
  - user skills, agents, commands, output styles and workflows
  - plugins and personal MCP servers
  - transcripts, so `--continue` and the resume picker are empty on the other account
  - auto memory: each account's Claude learns separately
  - prompt history, `/usage` stats and checkpoints
- **Minimum sync set for parallel config dirs:** symlink `settings.json`, `CLAUDE.md`, `rules/`, `skills/`, `agents/`, `commands/`, `output-styles/`, `workflows/` and `agent-memory/`. To share history and memory, also symlink `projects/`, but avoid running two accounts in the same session at once because of transcript interleaving. Never share `.credentials.json` or `.claude.json`. Plugins need care because of the absolute paths.
- Skills and plugins **synced from a claude.ai account** (`plugins/synced/`, synced skills) are tied to that account. They likely won't appear under the other four accounts unless enabled on each.

### Gaps
- No official guidance exists on safely sharing `projects/` (transcripts and auto memory) between config dirs.
- Whether `history.jsonl` and `stats-cache.json` tolerate concurrent writers from several processes was not established.

## Q3. Can a session started on account A be resumed on account B? Known thinking-signature errors?

### Takeaway
Yes, mechanically. With a shared config dir, `--continue`/`--resume` just work. With separate config dirs, resume by absolute transcript path or symlink `projects/`. The conversation is re-sent to account B as one uncached request. As of 2026, the API silently drops thinking blocks that came from another account for Sonnet 5.5 and Haiku 5.5, so the request succeeds but that earlier reasoning is lost. Older community reports describe hard `Invalid signature in thinking block` 400s when switching accounts or providers.

### Cited Findings
- **[Official]** `claude --resume <transcript-path>` "Resumes the session stored in the `.jsonl` transcript file at that absolute path". `claude --resume <session-id>` searches "The current project directory and its git worktrees" and then "Every other project on this machine". — [Sessions docs](https://code.claude.com/docs/en/sessions)
- **[Official]** A resumed session restores the conversation and its model, and "By default, the resumed conversation keeps the system prompt it started with". Flags like `--settings`, `--mcp-config` and `--plugin-dir` must be passed again. — [Sessions docs](https://code.claude.com/docs/en/sessions); [Prompt caching (Claude Code)](https://code.claude.com/docs/en/prompt-caching)
- **[Official]** "Resume this conversation?" dialog: "its estimate is the share of your 5-hour usage limit that the saved conversation alone would use… On Pro and Max plans, the dialog can appear when you resume a long conversation after a long break or on a different model." — [Sessions docs](https://code.claude.com/docs/en/sessions)
- **[Official]** Thinking-block account binding: "Thinking blocks that Claude Sonnet 5.5 or Claude Haiku 5.5 produces work only in the account that produced them or in an account linked to it. When another account sends one of these blocks, the API drops the block before the model sees it, and the request succeeds. The model answers without the reasoning in the dropped blocks." With the `thinking-binding-controls-2026-08-01` beta header the drop is reported as `reason: "organization_binding_mismatch"`; "Without the header, the drop is silent." — [Preserved thinking docs](https://platform.claude.com/docs/en/build-with-claude/preserved-thinking)
- **[Official]** "The API can also leave out a thinking block that another account produced, if that account is no longer in good standing." This applies to Fable, Mythos, Opus 5, Opus 5.5, Sonnet 5.5 and Haiku 5.5. — [Preserved thinking docs](https://platform.claude.com/docs/en/build-with-claude/preserved-thinking)
- **[Official]** There is a separate **400** prefix-mismatch error on Claude 5.1+ models when the system prompt, tools or earlier messages change: ``Invalid `signature` in `thinking` block. The block is bound to a different conversation.`` A tampered or undecryptable signature "always returns a 400 error". Model switches drop unreadable blocks without error (`model_binding_mismatch`). — [Preserved thinking docs](https://platform.claude.com/docs/en/build-with-claude/preserved-thinking)
- **[Community]** Reports of `Invalid signature in thinking block` on resume or after switching:
  - anthropics/claude-code [#21726](https://github.com/anthropics/claude-code/issues/21726): signatures not persisted; one user reproduced it by moving between backends.
  - [#10627](https://github.com/anthropics/claude-code/issues/10627)
  - [#63147](https://github.com/anthropics/claude-code/issues/63147): thinking text stored empty while the signature was kept.
  - CLIProxyAPI [#1584](https://github.com/router-for-me/CLIProxyAPI/issues/1584): switching from Gemini CLI to Claude OAuth mid-conversation.
  - cc-switch [#3930](https://github.com/farion1231/cc-switch/issues/3930): a local proxy replaying signed thinking on resume.
  - Reported workarounds: start a fresh session, or strip the bad thinking blocks from the JSONL (e.g., [claude-code-thinking-blocks-fix](https://github.com/miteshashar/claude-code-thinking-blocks-fix)).
- **[Official]** Cloud (web) sessions can be teleported to the CLI only under the same account: "You must be authenticated to the same claude.ai account used in the cloud session." — [Claude Code on the web docs](https://code.claude.com/docs/en/claude-code-on-the-web)

### Inferences
- **Moving a local CLI session between accounts works;** cloud sessions do not move between accounts.
- **Expected behaviour after a mid-session account switch on current models:**
  - **Sonnet 5.5 / Haiku 5.5:** prior thinking is silently dropped. The work continues, but with less continuity of reasoning.
  - **Opus 5.5 / Opus 5 / Fable:** prior thinking is kept, unless account A has been suspended, in which case it is dropped.
  - The prefix-mismatch 400 should not be triggered by the account change alone, because Claude Code keeps the original system prompt on resume. No doc confirms this specifically for cross-config-dir resumes, so treat it as likely but untested.
- **Older thinking-signature failures** tracked in GitHub issues may still bite third-party proxies or tools that edit transcripts. Native Claude Code plus an in-place credential swap is the lowest-risk path.

### Gaps
- No Anthropic doc defines what makes two consumer accounts "linked" for thinking-block purposes. For example, it is unclear whether a shared email or payment method counts.
- The dates of the community thinking-signature issues were not verified, so their relevance to current versions is uncertain.
- No official statement covers whether Claude Code itself strips thinking blocks when the active credential changes.

## Q4. Prompt caching: isolation scope, cost of switching accounts, TTL used by Claude Code for subscribers, and the controlling env vars

### Takeaway
API caches are isolated per organization (and per workspace on the Claude API). Claude Code's own cache is "effectively scoped to one machine and directory". Switching accounts mid-session is therefore a full cache miss: the next request rewrites the whole prefix at cache-write price. On a subscription within included usage, Claude Code uses a **1-hour TTL** for the main conversation, so writes cost 2× base input. Reads cost 0.1×, or 0.05× on Opus 5.5 and Sonnet 5.5. One switch therefore costs roughly 20–40× a normal cached turn's prefix cost. Subscribers who go into usage credits drop to a 5-minute TTL by default, and several env vars and settings override the TTL.

### Cited Findings
- **[Official, API]** "Caches are isolated between organizations. Different organizations never share caches, even if they use identical prompts. Caches are also isolated by workspace within an organization on the Claude API, Claude Platform on AWS, and Microsoft Foundry; Bedrock and Claude Models on Google Cloud (Google managed) use organization-level isolation only." "Cache hits require 100 percent identical prompt segments." — [Prompt caching API docs](https://platform.claude.com/docs/en/build-with-claude/prompt-caching)
- **[Official, API]** Pricing multipliers:
  - 5-minute cache writes: **1.25×** base input.
  - 1-hour cache writes: **2×**.
  - Cache reads: **0.1×**. Footnotes: "Cache hits and refreshes on Claude Opus 5.5 and Claude Sonnet 5.5 are priced at 0.05x"; Fable 5.1 and Mythos 5.1 at 0.025×; all other models 0.1×.
  - "By default, the cache has a 5-minute lifetime. The cache is refreshed for no additional cost each time the cached content is used."
  - Minimum cacheable prompt is 512 tokens for the 5.x-generation models.

  — [Prompt caching API docs](https://platform.claude.com/docs/en/build-with-claude/prompt-caching)
- **[Official, Claude Code]** "In Claude Code, the cache is effectively scoped to one machine and directory. The system prompt embeds your auto memory paths, and the conversation opens with an announcement of the working directory, platform, shell, and OS version. Two sessions in different directories therefore build different prefixes and miss each other's cache." "Sessions you run in parallel in the same directory build matching prefixes and can read each other's cache." Sequential sessions match only if the git-status snapshot matches. — [Prompt caching (Claude Code)](https://code.claude.com/docs/en/prompt-caching)
- **[Official, Claude Code]** Where the cache lives: "API key, Claude subscription, or Claude Platform on AWS: the cache lives in Anthropic's infrastructure". "Each model has its own cache. Switching models recomputes the entire request even when the content is identical." The docs list cache-invalidating actions (model switch, effort change on most models, fast mode, MCP or plugin changes, compaction, upgrades), but **an account switch is not mentioned on the page.** — [Prompt caching (Claude Code)](https://code.claude.com/docs/en/prompt-caching)
- **[Official, Claude Code]** TTL per request bucket:

  | Bucket | Claude subscription, within plan usage | Usage credits, API key, or cloud provider |
  | - | - | - |
  | Main conversation | **One hour** | Five minutes |
  | Everything else (subagents, workflows, teammates, forks, compaction, titles) | Five minutes, except server-controlled helpers, which get one hour | Five minutes |

  "Once you go over your plan's usage limit and Claude Code draws on usage credits… Claude Code drops the main conversation to the five-minute TTL." — [Prompt caching (Claude Code)](https://code.claude.com/docs/en/prompt-caching)
- **[Official]** TTL controls (each takes only `5m` or `1h`):
  - **Main conversation:** `promptCacheTtl` setting or `CLAUDE_CODE_PROMPT_CACHE_TTL`.
  - **Everything else:** `subagentPromptCacheTtl` or `CLAUDE_CODE_SUBAGENT_PROMPT_CACHE_TTL`.
  - All four require v2.1.242+.
  - **Precedence:**
    1. `FORCE_PROMPT_CACHING_5M=1`
    2. The bucket's env var
    3. The bucket's setting
    4. Subagent frontmatter `experimental.cacheTtl` (v2.1.248+; a `1h` there is ignored while on usage credits)
    5. `ENABLE_PROMPT_CACHING_1H=1`
    6. The default
  - `ENABLE_PROMPT_CACHING_1H` is "Intended for API key, Bedrock… users. For subscription users within included usage, Claude Code requests the 1-hour TTL automatically… Subscription users drawing on usage credits can set it to keep the 1-hour TTL."
  - `DISABLE_PROMPT_CACHING` (plus per-model `_HAIKU`, `_SONNET`, `_OPUS`, `_FABLE`) turns caching off.

  — [Prompt caching (Claude Code)](https://code.claude.com/docs/en/prompt-caching); [Env vars reference](https://code.claude.com/docs/en/env-vars)
- **[Official]** You can verify the TTL with `claude -p "hello" --output-format json`: 1-hour writes appear under `usage.cache_creation.ephemeral_1h_input_tokens`, 5-minute writes under `ephemeral_5m_input_tokens`. `/usage` shows a "Prompt cache (main)" hit-ratio line (v2.1.251+) with a likely cause for the last miss (v2.1.260+). The statusline gets a `prompt_cache` object (v2.1.251+). — [Prompt caching (Claude Code)](https://code.claude.com/docs/en/prompt-caching)
- **[Official]** "If you leave a long conversation idle while it is on the one-hour TTL, Claude Code can compact it". The `idleCompaction` setting controls this (v2.1.290+). After a break longer than the TTL, `/compact` reprocesses history uncached. — [Prompt caching (Claude Code)](https://code.claude.com/docs/en/prompt-caching)
- **[Official, API rate limits, not subscription quotas]** For most models, cached input tokens do not count toward API input-tokens-per-minute rate limits. — [API rate limits docs](https://platform.claude.com/docs/en/api/rate-limits) (seen via search summary, page not fetched)
- **[Community]** How cache reads weigh against **subscription** limits is unpublished, and sources disagree:
  - A third-party analysis concludes "a cache-read token uses close to none of your limit" on Claude plans ([Wokey](https://wokey.ai/claude-usage-limits)).
  - A GitHub issue argues cache reads count heavily against Max quota ([#24147](https://github.com/anthropics/claude-code/issues/24147)).

### Inferences
- **An account switch forces a full miss.**
  - Each consumer login belongs to its own organization. That inference rests on `/status` showing an organization per login, `forceLoginOrgUUID` semantics, and the thinking-binding reason being named `organization_binding_mismatch`.
  - API caches never cross organizations. Separately, a different `CLAUDE_CONFIG_DIR` changes the auto-memory path embedded in the system prompt, which breaks the prefix even before org isolation applies.
  - So the system-prompt, project-context and conversation layers all miss on the first request under account B. After that, B warms its own cache normally.
- **Cost of one switch on the main conversation** (subscription, 1h TTL, in API-price-equivalent terms):
  - The first request after a switch costs about `context_tokens × 2.0`, compared with `context_tokens × 0.05` (Opus 5.5 / Sonnet 5.5) or `× 0.1` (other models) for a normal cached turn.
  - That is about **40×** (Opus/Sonnet 5.5), **20×** (0.1× models) or **80×** (Fable 5.1) a normal turn's prefix cost.
  - With a 5-minute TTL, for example after moving to usage credits, the ratios are 25× and 12.5×.
  - **Example:** a 120k-token context on Sonnet 5.5 normally costs about 6k base-input-equivalent tokens to re-read. A switch makes it about 240k, i.e., the same as roughly 39 extra normal turns of prefix reads, or 2× the cost of sending the context uncached at base price.
  - Each switch is one-time per conversation, and the cost lands on the account you switch **to**.
- **Switching back never recovers a cache in practice.** A 5-hour usage window outlasts the 1-hour TTL, so by the time an exhausted account resets, its cache has expired and returning to it is also a cold start. Only fast round-trips, under an hour and before account A is exhausted, could re-hit A's old prefix.
- **How to keep switches cheap:** switch at natural breaks, after `/compact` or `/clear`, when the context is small. Prefer starting fresh sessions on the new account over resuming long ones. Subagent caches are already per-request-group and 5-minute by default, so routing subagents to different accounts loses little extra cache.
- Usage-limit accounting weights are unpublished. In subscription terms, a switch most likely costs about one cold re-read of the context, comparable to resuming a long session after more than an hour away. Claude Code explicitly warns about that case with the "Resume this conversation?" estimate of the share of the 5-hour limit.

### Gaps
- Anthropic has not published how cache writes and reads are weighted against Pro/Max/Team usage limits, so the real "usage lost" per switch can't be stated precisely.
- No doc states explicitly that each individual Pro account is a separate organization for cache purposes. This is inferred above.
- When Claude Code first made the 1-hour TTL the subscriber default (before the v2.1.242 TTL controls) was not established. The docs don't distinguish Pro, Max, Team and Enterprise; all are treated as "Claude subscription".

## Q5. Subagents and cache; can Claude Code natively run a subagent under another account; how could a supervisor route work by remaining usage?

### Takeaway
Subagents never share the parent's cache. Forks do, but forks run under the parent's account. Claude Code has no per-subagent or per-request account setting; it is single-credential per process. A supervisor therefore has to launch separate `claude -p` or Agent SDK processes per account, and can read remaining usage through the official statusline `rate_limits` fields or the undocumented `/api/oauth/usage` endpoint. Community tools such as claude-swap, Symbioose, TeamClaude and ccflare already automate rotation, and the proxy-style ones carry the highest terms risk.

### Cited Findings
- **[Official]** "A subagent starts its own conversation with its own system prompt and tool set, separate from the parent's. Its first request doesn't read the parent's cache… Subagents fall outside the main-conversation TTL bucket, so they get five minutes even on a subscription". "A fork… inherits the parent's system prompt, tools, and conversation history exactly, so its first request reads the parent's cache." Workflow fan-outs hold same-prefix agents for up to 5 s (`CLAUDE_CODE_WORKFLOW_PREFIX_STAGGER_MS`) so they can read the first agent's cache. — [Prompt caching (Claude Code)](https://code.claude.com/docs/en/prompt-caching); [Env vars reference](https://code.claude.com/docs/en/env-vars)
- **[Official]** Subagent controls are model-only: `CLAUDE_CODE_SUBAGENT_MODEL` and `CLAUDE_CODE_SUBAGENT_MODEL_FORCE` (v2.1.257+). The env-var reference has no per-subagent credential or account variable. — [Env vars reference](https://code.claude.com/docs/en/env-vars)
- **[Community]** A user with **four Max subscriptions** asked for built-in multi-account load balancing, using `anthropic-ratelimit-unified-*` response headers to pick the least-used account and fail over on 429. The issue notes the codebase is single-account throughout (`getClaudeAIOAuthTokens()`, `withRetry.ts`). It was closed "not planned" by the author ("Filed in error") on 2026-04-05 with no staff reply. Possible duplicates: #34341, #16536, #26777. — [GitHub issue #43978](https://github.com/anthropics/claude-code/issues/43978)
- **[Official]** Statusline JSON includes:
  - `rate_limits.five_hour.used_percentage`, `rate_limits.seven_day.used_percentage` (0–100)
  - `…resets_at` (Unix epoch seconds)

  These "appear only for claude.ai Pro and Max subscribers… and only after the first API response in the session". A window drops once its `resets_at` passes. — [Statusline docs](https://code.claude.com/docs/en/statusline)
- **[Official]** `CLAUDE_CODE_RETRY_WATCHDOG=1` makes unattended sessions wait out usage limits until reset. `CLAUDE_CODE_RETRY_WATCHDOG_USAGE_LIMIT_RECHECK_MS` (v2.1.305+) re-checks "whether a usage limit was already lifted" for "a claude.ai subscription's session or weekly limit". — [Env vars reference](https://code.claude.com/docs/en/env-vars)
- **[Community]** Undocumented usage endpoint: `GET https://api.anthropic.com/api/oauth/usage`, with the OAuth bearer token and `anthropic-beta: oauth-2025-04-20`, returns `five_hour` and `seven_day` utilization.
  - Users report persistent 429s from it ([#31021](https://github.com/anthropics/claude-code/issues/31021)).
  - It returns no data for Enterprise ([#34346](https://github.com/anthropics/claude-code/issues/34346)).
  - It is used by statusline and monitor tools ([ohugonnot/claude-code-statusline](https://github.com/ohugonnot/claude-code-statusline); [Claude-Code-Usage-Monitor #202](https://github.com/Maciek-roboblog/Claude-Code-Usage-Monitor/issues/202)).
- **[Community]** claude-swap auto-rotation:
  - Tracks each account's 5-hour and 7-day windows, plus optional per-model weekly windows, via "the usage endpoint".
  - Default switch threshold 90%, 5-minute cooldown, hysteresis.
  - Strategies `best`, `next-available`, `consume-first`, `priority-consume-first`.
  - Polls exhausted accounts about every 10 minutes and quarantines dead refresh tokens.
  - `cswap run <account>` gives per-terminal parallel accounts.

  — [claude-swap README](https://github.com/realiti4/claude-swap)
- **[Community]** Other tools:
  - [Symbioose/claude-account-switcher](https://github.com/Symbioose/claude-account-switcher): "View live usage and rate limits, auto-switch at limits".
  - **cux:** "Pools multiple Pro/Max accounts in one session", `/switch` via hook.
  - **aistat:** shows 5h/7d usage per account.
  - Both cux and aistat are listed in [#30031](https://github.com/anthropics/claude-code/issues/30031).
- **[Community]** Proxy load balancers:
  - **ccflare** (snipeship) sits between Claude Code and the API via `ANTHROPIC_BASE_URL`, adds multiple accounts through an OAuth setup, and "load-balances across multiple accounts" with session-based stickiness. — [snipeship/claudeflare](https://github.com/snipeship/claudeflare)
  - **TeamClaude** (karpeleslab) auto-switches when an account nears its session or weekly quota and can import existing Claude Code logins. — [ecosyste.ms listing](https://awesome.ecosyste.ms/projects/github.com%2Fkarpeleslab%2Fteamclaude)

### Inferences
- **Feasible supervisor design with native Claude Code only:**
  - One orchestrator process launches worker `claude -p` (or Agent SDK) processes. Each worker gets `CLAUDE_CODE_OAUTH_TOKEN=<that account's setup-token>`, or its own `CLAUDE_CONFIG_DIR`.
  - The orchestrator reads each account's remaining 5-hour and 7-day percentages from the last statusline or `--output-format json` data, or polls the undocumented OAuth usage endpoint, and dispatches the next task to the account with the most headroom.
  - In-session Agent-tool subagents can't be routed this way; only separate processes can.
  - Because each worker is a fresh conversation on its account, there is no extra cache loss beyond what subagents already incur.
- **Terms risk differs by tool type.**
  - Per-account launchers and swap tools leave the unmodified Claude Code binary talking to Anthropic.
  - Proxies like ccflare and TeamClaude store and intermediate OAuth tokens and route traffic through consumer subscriptions. That is the pattern the legal page and Usage Policy prohibit (see Q7), so it carries clearly higher risk.
- Polling the undocumented usage endpoint is outside documented interfaces and is rate-limited. The documented, lower-risk signals are the statusline `rate_limits` fields and 429 handling.

### Gaps
- No official API documents remaining-usage queries for consumer plans. The `anthropic-ratelimit-unified-*` header names come only from a community issue and were not verified against current responses.
- How Anthropic's abuse detection treats one machine or IP driving several consumer accounts in parallel is unknown, since there is no official statement.

## Q6. Claude Team plan: do seats share prompt cache? Can transcripts or cloud sessions be shared?

### Takeaway
Team seats sit in one organization, so the API's isolation boundary would allow sharing. In practice Claude Code's cache is scoped per machine and directory, and nothing indicates automatic cross-member cache reuse. Local CLI transcripts are never synced. Cloud (web) sessions can be shared read-only with the org through "Team" visibility, but only the owning account can teleport or continue them. Team also falls under the Commercial Terms rather than the Consumer Terms.

### Cited Findings
- **[Official]** "Caches are isolated between organizations… Caches are also isolated by workspace within an organization on the Claude API". — [Prompt caching API docs](https://platform.claude.com/docs/en/build-with-claude/prompt-caching)
- **[Official]** "In Claude Code, the cache is effectively scoped to one machine and directory… Within those boundaries, two requests share a cache only when their model, speed, and prefix match." — [Prompt caching (Claude Code)](https://code.claude.com/docs/en/prompt-caching)
- **[Official]** Cloud session sharing on Team and Enterprise: "Visibility options: Private and Team. Team visibility makes the session visible to other members of your claude.ai organization". "Recipients see the latest state when they open the link, but their view doesn't update in real time." Claude in Slack sessions are auto-shared with Team visibility. On Pro and Max, the options are Private or **Public** (visible to any logged-in claude.ai user). — [Claude Code on the web docs](https://code.claude.com/docs/en/claude-code-on-the-web)
- **[Official]** Teleporting a cloud session to the terminal requires "Same account: You must be authenticated to the same claude.ai account used in the cloud session". "Cloud sessions share rate limits with all other Claude and Claude Code usage within your account." — [Claude Code on the web docs](https://code.claude.com/docs/en/claude-code-on-the-web)
- **[Official]** Team includes "server-managed settings for organization-wide Claude Code configuration". Admins can force logins to their org with `forceLoginOrgUUID`. — [Authentication docs](https://code.claude.com/docs/en/authentication)
- **[Official]** Claude Code is governed by the "Commercial Terms of Service – for Team, Enterprise, and Claude API users" and the "Consumer Terms of Service – for Free, Pro, and Max users". — [Legal and compliance](https://code.claude.com/docs/en/legal-and-compliance)

### Inferences
- **A Team plan does not solve the switching problem.**
  - Each seat is a separate claude.ai account with its own usage allowance, since cloud and CLI usage counts "within your account".
  - Teammates' Claude Code prefixes differ: home-dir memory paths, working dir and git snapshot.
  - Local transcripts and memory stay on each machine.
  - The only shared artifact is a read-only, non-live view of cloud sessions set to Team visibility, plus org-wide managed settings.
- One person using several Team seats would still face per-account cache isolation in practice, because of the per-config-dir prefix differences, even inside one org.

### Gaps
- No doc confirms or denies whether two Team seats running byte-identical prefixes (same machine, same dir, same config path) would hit each other's cache. The API rules suggest yes, but this was not tested.
- Per-seat usage allowances and whether a single human may hold multiple Team seats were out of scope here and are covered by other researchers.

## Q7. Terms of service: multiple consumer accounts, sharing, automation, third-party harnesses, limit circumvention, and enforcement reports

### Takeaway
No Anthropic legal text found in this research says "one account per person". The Consumer Terms (effective 2025-10-08) say nothing about multiple accounts or usage limits. An Anthropic Claude Code staffer publicly said in February 2026 that holding multiple Max accounts is not against the terms. The risk comes from adjacent rules:
- **Consumer Terms:** no credential sharing, no automated access except via API key, no bypassing "systems or protective measures".
- **Usage Policy:** no creating multiple accounts "to avoid detection, circumvent product guardrails", no ban evasion, no bypassing "restrictions", and no proxying "that route[s] requests through consumer subscriptions".
- **Claude Code legal page:** subscription limits "assume ordinary, individual usage", and subscription OAuth is only for native Anthropic apps.

Every documented 2026 ban wave involved third-party harnesses or OAuth proxies, resale, or automated fraud flags. No credible primary report shows a ban for simply rotating several personally owned accounts in the official Claude Code, but anecdotal ban reports exist and enforcement is automated and opaque.

### Cited Findings
- **[Official]** Consumer Terms (page shows "Effective Oct 8, 2025"):
  - §2: "You may not share your Account login information, Anthropic API key, or Account credentials with anyone else. You also may not make your Account available to anyone else." "You are responsible for all activity occurring under your Account".
  - §3 item 7 prohibits use "Except when you are accessing our Services via an Anthropic API Key or where we otherwise explicitly permit it, to access the Services through automated or non-human means, whether through a bot, script, or otherwise".
  - §3 also bars "bypassing any of our systems or protective measures".
  - §12: Anthropic "may suspend or terminate your access to the Services (including any Subscriptions) at any time without notice to you… if we believe that you have breached these Terms" and may "increase or decrease capacity limits".
  - A targeted re-check found **no** occurrence of "multiple accounts", "more than one account", "circumvent", "usage limits", "rate limits" or "free trial".

  — [Consumer Terms](https://www.anthropic.com/legal/consumer-terms)
- **[Press/Secondary, conflicts with the above]** Usagebar (2026-10-18) quotes the Consumer Terms as prohibiting "creating multiple accounts … to circumvent rate limits, usage limits, or free trial availability". That wording was **not found** in the current Consumer Terms page, and the article cites no ban reports. — [Usagebar](https://usagebar.com/blog/can-i-have-two-claude-pro-accounts); contradicted by [Consumer Terms](https://www.anthropic.com/legal/consumer-terms)
- **[Official]** Usage Policy, "Do Not Abuse Our Platform". Verbatim, from page HTML retrieved 2026-10-30. The page displays "Effective Nov 12, 2026", which is after this research date, so this appears to be a newly published version taking effect in November.
  - "Coordinate malicious activity across multiple accounts, or create multiple accounts, to avoid detection, circumvent product guardrails, or generate identical or similar inputs that otherwise violate our Usage Policy"
  - "Circumvent a ban through the use of a different account, such as the creation of a new account, use of an existing account, or providing access to a person or entity that was previously banned"
  - "Utilize automation in account creation or to engage in spammy behavior"
  - "Intentionally bypass or interfere with capabilities, restrictions, or guardrails established within our products, including by circumvention of the account sign-up or log-in process or by instructing the model to produce harmful outputs (e.g., jailbreaking or prompt injection), without prior authorization from Anthropic"
  - "Resell, proxy, or otherwise provide access to Claude through unauthorized means, including services that route requests through consumer subscriptions or misrepresent the product or client being used"
  - Enforcement: "we may warn you or throttle, limit, suspend, or terminate your access to our products and services."

  — [Usage Policy](https://www.anthropic.com/legal/aup)
- **[Official]** Claude Code legal page, current as of 2026-10-30:
  - "Advertised usage limits for Pro and Max plans assume ordinary, individual usage of Claude Code and the Agent SDK."
  - "OAuth authentication is intended exclusively for purchasers of Claude Free, Pro, Max, Team, and Enterprise subscription plans and is designed to support ordinary use of Claude Code and other native Anthropic applications."
  - "Anthropic does not permit third-party developers to offer Claude.ai login into their own applications, or to route requests through Free, Pro, or Max plan credentials on behalf of their users. Moreover, developers may not collect, store, or intermediate Claude.ai credentials or session tokens."
  - It does not prevent "an end user from signing in to the unmodified Claude Code binary with their own Claude subscription".
  - "Anthropic reserves the right to take measures to enforce these restrictions and may do so without prior notice."

  — [Legal and compliance](https://code.claude.com/docs/en/legal-and-compliance)
- **[Historical, Press]** Third-party harness crackdown:
  - Around **2026-01-09**, Anthropic deployed server-side checks blocking subscription OAuth tokens in third-party harnesses such as OpenCode, Cline and RooCode ([zbuild](https://www.zbuild.io/resources/news/opencode-blocked-anthropic-2026); [HN thread](https://news.ycombinator.com/item?id=46549823)).
  - Thariq Shihipar (Claude Code team) wrote: "Third-party harnesses using Claude subscriptions create problems for users and are prohibited by our Terms of Service", adding that they "generate unusual traffic patterns", "making it really hard for us to help debug when they have questions about rate limit usage or account bans".
  - In **February 2026** the legal page said: "Using OAuth tokens obtained through Claude Free, Pro, or Max accounts in any other product, tool, or service…" (including the Agent SDK) violates the Consumer Terms. Anthropic framed this as clarifying §3.7, which has existed since February 2024.

  — [The Register, 2026-02-20](https://www.theregister.com/2026/02/20/anthropic_clarifies_ban_third_party_claude_access/); [AlternativeTo](https://alternativeto.net/news/2026/2/anthropic-officially-bans-using-subscription-authentication-for-third-party-claude-use)
- **[Press/Secondary]** Later changes:
  - Subscriptions reportedly stopped covering third-party tools such as OpenClaw from **2026-04-04** ([MLQ](https://mlq.ai/news/anthropic-ends-paid-access-for-claude-in-third-party-tools-like-openclaw/)).
  - By October 2026 the "any other product… including the Agent SDK" line was gone from the legal page ([Autonomee](https://autonomee.ai/blog/claude-code-terms-of-service-explained/)). This research's fetch of the current legal page confirms that wording is absent and replaced by the "ordinary use… native Anthropic applications" text quoted above.
- **[Staff]** Thariq Shihipar on X, 2026-02-18: "we haven't changed anything here it's not against terms of service to have multiple MAX accounts, though sometimes people are using multiple MAX accounts to do things like resell tokens which of course is agai[nst our terms]". The text is truncated in the search snippet. — [X post](https://x.com/trq212/status/2024230184287949207); also quoted by [MetricNexus](https://metricnexus.ai/blog/anthropic-banning-multiple-claude-accounts)
- **[Historical, Official via press]** On 2025-07-28 Anthropic introduced weekly limits partly because "a small number of users are violating our usage policies by sharing and reselling accounts". — [TechCrunch](https://techcrunch.com/2025/07/28/anthropic-unveils-new-rate-limits-to-curb-claude-code-power-users/); [MetricNexus quoting @AnthropicAI](https://metricnexus.ai/blog/anthropic-banning-multiple-claude-accounts)
- **[Press/Secondary, low evidentiary weight]** MetricNexus ("Anthropic Is Banning Users With Multiple Claude Accounts", 2026-02-26, updated 2026-10-23) relies on anecdotal X posts from February 2026 of paying users losing several Max accounts. It cites **no primary evidence** that multi-account holding triggered bans. Its proposed triggers (shared IP or device, overnight loops, third-party tools) are unsourced speculation, and an unnamed Anthropic representative is quoted saying "some user accounts were automatically banned for triggering abuse filters". — [MetricNexus](https://metricnexus.ai/blog/anthropic-banning-multiple-claude-accounts)
- **[Community/Press]** Other enforcement signals:
  - An HN thread reports instant suspension right after purchase, with commenters suspecting payment-card triggers ([HN](https://news.ycombinator.com/item?id=48134808)).
  - Forbes (2026-04-11) reported Reddit users describing sudden suspensions with "suspicious signals" boilerplate. The article itself returned 403 to this research, so this is known only from a search snippet ([Forbes](https://www.forbes.com/sites/digital-assets/2026/04/11/suspicious-signals-why-ais-debanking-problem-should-worry-you/)).
  - TechCrunch (2026-09-08) reported hackers stealing subscribers' Claude tokens, with one victim's account suspended and reinstated after about two weeks ([TechCrunch](https://techcrunch.com/2026/09/08/hackers-are-stealing-claude-tokens-from-subscribers/)).
  - A Japanese analysis of primary sources concludes the consumer terms contain no explicit one-account-per-person rule ([note.com](https://note.com/piyo_bird/n/n922517e2d9ed?hl=en)).
  - Anthropic has a public safeguards, warnings and appeals help page ([support article](https://support.claude.com/en/articles/8241253-safeguards-warnings-and-appeals), cited by MetricNexus and not fetched here).

### Inferences
- **Owning five Pro accounts and using each, one at a time, in the official Claude Code binary** is not expressly prohibited by any current text found. Anthropic staff have publicly said multiple accounts are fine. The grey zone is purpose and pattern:
  - The legal page ties advertised limits to "ordinary, individual usage".
  - The Usage Policy bars intentionally bypassing product "restrictions".
  - Deliberate rotation to exceed one plan's limits could be read as bypassing a restriction, though "guardrails" in the multi-account bullet reads as safety-oriented. That is an interpretive risk, not settled text.
- **Clearly higher-risk patterns:**
  - Proxies or load balancers that store and intermediate OAuth tokens (ccflare, TeamClaude-style), which match "services that route requests through consumer subscriptions".
  - Spoofing the Claude Code client.
  - Sharing any account with another person.
  - Automating account creation.
  - Creating a new account after a ban.
- **Lower-risk patterns:** the official binary with `CLAUDE_CONFIG_DIR` per account (Anthropic's own documented method, framed for work and personal accounts), or the official binary plus a credential-swap script.
- **Practical ban risk** looks to come mostly from opaque automated abuse and fraud detection: payment signals, unusual traffic, third-party clients. Five accounts on one machine, IP and card, all hitting limits in rotation, is an unusual pattern that could plausibly trip such systems. There is no evidence either way. Any ban is at Anthropic's discretion, "without notice", and could affect all linked accounts.

### Gaps
- No primary-source report (Anthropic statement, email or support reply) was found of a ban or warning for **rotating personally owned Pro accounts in official Claude Code to extend usage**. Equally, no official confirmation was found that doing so is allowed beyond the staff tweet about holding multiple Max accounts.
- The Usage Policy version in force before 2026-11-12 was not retrieved, so its exact wording on multiple accounts is unverified. The quotes above are from the version the page currently displays.
- Ban-volume statistics, e.g., a blog claim of 11.4M accounts banned January–June 2026 citing Anthropic's Transparency Hub ([pasqualepillitteri.it](https://pasqualepillitteri.it/en/news/24559/claude-account-banned-2026-numbers-how-to-appeal)), were not verified against the Transparency Hub itself.
- The Commercial Terms, which govern Team, were not reviewed for multi-seat or single-user rules.
