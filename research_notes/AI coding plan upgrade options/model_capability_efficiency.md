# Model capability and token efficiency: Claude models (Claude Code) vs OpenAI models (Codex), including "Astra", as of 2026-10-30

Research date: 2026-10-30. "AA" = Artificial Analysis. Pages marked "accessed 2026-10-30" were fetched that day and carry no explicit snapshot date. "(search snippet)" means the claim came from a search-engine summary of that page and the page itself was not fetched or verified. Subscription prices and usage limits are out of scope here.

## 1. What is "Astra"? Who released it, when, is it a model or a product, and where is it available?

### Takeaway
"Astra" is **GPT-6 Astra**, OpenAI's top-tier GPT-6 **model** (API id `gpt-6-astra`). It was unveiled with a limited preview on 2026-09-03 and released to paid users in a restricted form on 2026-09-04. It is available in **OpenAI Codex** (app, web, CLI, IDE extension), the OpenAI API and third-party clouds and routers. It is **not** available in Claude Code. Since 2026-09-29, OpenAI has positioned **GPT-6.1 Sol** as the cheaper "near-Astra" workhorse for Codex. This is unrelated to Google DeepMind's "Project Astra".

### Cited Findings
- **Identity and timeline.** OpenAI is the developer and GPT-6 is the model family. It was released as a limited preview on 2026-09-03, the day it was unveiled. On 2026-09-04 it went to paid users in a restricted version that rejects some prompts, such as cybersecurity requests. GPT-6 Sol and GPT-6 Luna followed on 2026-09-22, and GPT-6 Instant on 2026-10-07/08. The predecessor family is GPT-5.6 and the successor is GPT-6.1 — [Wikipedia: GPT-6 Astra](https://en.wikipedia.org/wiki/GPT-6_Astra)
- AA lists the Astra release date as 2026-09-03 and a knowledge cutoff of 2026-04-30, with a 1M-token context window (accessed 2026-10-30) — [Artificial Analysis: GPT-6 Astra](https://artificialanalysis.ai/models/gpt-6-astra)
- OpenAI's launch page calls Astra its "most intelligent and aligned model", with strong results in computer use, coding, cybersecurity and science. The API identifier is `gpt-6-astra`. Rollout began with a limited set of organizations, and "broader availability [was] planned for ChatGPT Plus, Pro, Business, and Enterprise users," plus the API, Azure and AWS Bedrock. The page also carries a 2026-09-29 update pointing to GPT-6.1 Sol (search snippet; direct fetch returned HTTP 403) — [OpenAI: GPT-6 Astra](https://openai.com/index/gpt-6-astra/)
- Astra is OpenAI's first model to reach its internal "Critical" cybersecurity threshold, and companies in OpenAI's application-based cyber program got access first (2026-09-03; search snippet) — [CNBC](https://www.cnbc.com/2026/09/03/open-ai-astra-gpt-6-cyber.html)
- Greg Brockman said Astra could eventually be seen as the arrival of AGI. VP of research Aidan Clark said it was "by far" the largest training run, and the first pretraining on more than 100,000 GPUs at the Stargate site in Texas. An anonymous insider claimed Astra uses a "recurrent depth"/"looped transformers" technique that improves efficiency but hides some of its chain of thought — [Wikipedia: GPT-6 Astra](https://en.wikipedia.org/wiki/GPT-6_Astra)
- **Codex availability (official OpenAI docs, accessed 2026-10-30).** Codex currently lists four models:
  - **Astra** (`gpt-6-astra`): "Our most capable model for complex work across code, apps, and research", recommended for "the hardest end-to-end workflows". Available in the desktop app, web, CLI, IDE extension, ChatGPT Credits and API.
  - **GPT-6.1 Sol** (`gpt-6.1-sol`): "Near-Astra performance for complex work at a lower cost than Astra", recommended for repeated, long-running work.
  - **GPT-6.1 Terra** (`gpt-6.1-terra`): fast and efficient; requires Codex CLI 0.159.0 or later.
  - **GPT-6 Luna** (`gpt-6-luna`): "most efficient model for focused, high-volume tasks."

  Plus, Pro, Business, Enterprise and Edu users are told to move from GPT-5.5 to GPT-6.1 Sol, and Free and Go users to GPT-6 Luna. "Some paid plans omit Astra Extra High from the Power presets." The page has **no per-plan model matrix** and refers readers to the pricing page and Help Center. GPT-5.5 retired from Codex with ChatGPT sign-in on 2026-10-14. Codex reasoning-effort levels are Light/Low, Medium, High, Extra High, Max, and Ultra (Ultra uses subagents) — [OpenAI Codex docs: Models](https://learn.chatgpt.com/docs/models) (redirected from developers.openai.com/codex/models)
- GPT-6.1 Sol was released on 2026-09-29 and is listed at $2/$10 per 1M tokens in the API. A guide recommends `codex -m gpt-6.1-sol` for complex coding (search snippets) — [OpenAI Codex changelog](https://developers.openai.com/codex/changelog); [AIDEN: Best Codex model](https://aidenapp.org/best-codex-model)
- **API pricing.** Astra costs $10 input / $50 output per 1M tokens, the same as Claude Fable 5.1. Astra's cache reads cost about $1/M, against $0.25/M for Fable 5.1. Above about 272K input tokens, Astra's price rises to $20/$75 (as of 2026-09-08). Astra is "2.5 times" the price of GPT-5.6 Sol — [hierarchy.ch](https://hierarchy.ch/en/news/gpt-6-astra-vs-claude-fable-5-1). AA lists $10/$50 with a 90% cache-hit discount for Astra (accessed 2026-10-30) — [AA: GPT-6 Astra](https://artificialanalysis.ai/models/gpt-6-astra)
- **Restrictions and reliability.** Astra's security domain is hard-locked: exploit-adjacent requests fail at the API even with authorization. At launch it had no EU data zone. On Claude Fable 5.1, a classifier silently reroutes certain requests, such as security-related ones, to an Opus model (as of 2026-09-08) — [hierarchy.ch](https://hierarchy.ch/en/news/gpt-6-astra-vs-claude-fable-5-1)
- **Claude side, for the same question.** Claude Fable 5.1 was released on 2026-09-01. Mythos 5.1 is the same model without additional safeguards and is available only to vetted organizations. **Fable 5.1 in Claude Code is "included only for Max and premium Team seats"**, and Pro users pay through credits at API rates (as of 2026-09-08) — [hierarchy.ch](https://hierarchy.ch/en/news/gpt-6-astra-vs-claude-fable-5-1)
- Claude Opus 5.5 was released on 2026-09-22 and costs $4/$20 per 1M tokens — [AA: Claude Opus 5.5](https://artificialanalysis.ai/models/claude-opus-5-5) (accessed 2026-10-30)
- Claude Sonnet 5.5 was released on 2026-09-28 and costs $2/$10. In Claude Code, the `sonnet` alias has pointed to Sonnet 5.5 since v2.1.284, while the **default model remains Opus 5.5**. The Claude apps and Claude Code default to **medium** effort — [AtomicAgent: Sonnet 5.5 vs Opus 5.5](https://atomicagent.io/blog/claude-sonnet-5-5/)
- OpenRouter hosts a Fable 5.1 vs GPT-6 Astra comparison page, so Astra is also reachable through OpenRouter (search result) — [OpenRouter compare](https://openrouter.ai/compare/anthropic/claude-fable-5.1/openai/gpt-6-astra). AA says Astra is served by 11 API providers but also says "No publicly available API", which is a self-contradiction on the page — [AA: GPT-6 Astra](https://artificialanalysis.ai/models/gpt-6-astra)

### Inferences
- The user's "Astra" is almost certainly OpenAI's GPT-6 Astra. It is the only Astra competing directly with Fable 5.1, and every coding-comparison article in October 2026 pairs the two. Google DeepMind's "Project Astra", a universal-assistant research prototype, is a different thing. That disambiguation comes from background knowledge and was not re-verified in this research.
- Astra and GPT-6.1 Sol are both selectable in Codex CLI. Astra cannot be used inside Claude Code, and Claude models cannot be used in Codex with ChatGPT sign-in, so the subscription choice also fixes which model family is available.
- For a Claude Max 5x subscriber, the relevant Claude models are Opus 5.5 (the Claude Code default), Sonnet 5.5, and Fable 5.1, which is included on Max. For a Codex subscriber, they are Astra and GPT-6.1 Sol, which OpenAI recommends for repeated, long-running work.

### Gaps
- I could not retrieve OpenAI's per-plan matrix of which Codex plans can use Astra, including whether a ~$100 tier gets Astra or Astra Extra High. The OpenAI launch page returned HTTP 403, and the Codex docs defer to the Help Center.
- The release date of GPT-6.1 Terra was not found.
- I could not verify whether any non-Anthropic model can be used in Claude Code, or any non-OpenAI model in Codex with ChatGPT sign-in.

## 2. Has Astra overtaken Claude Fable 5.1? Agentic-coding benchmarks, with Opus 5.5, Sonnet 5.5 and GPT-6.1 Sol for context

### Takeaway
On current independent data, **mostly yes, Astra is ahead of Fable 5.1**. On AA's Intelligence Index v5.0 (accessed 2026-10-30), Astra (max) scores 57 and ranks #4, while Fable 5.1 (max) scores 49 and ranks #18. AA's own runs also put Astra ahead on Terminal-Bench 4.0 (59.1% vs 52.0%) and FrontierCode 2.0 (42.4% vs 31.6%). Astra also overtook Fable 5.1 on Code Arena WebDev in early September.

Fable 5.1, however, led AA's **Coding Agent Index** at launch (70 vs 67, with each model in its own harness), and it won hands-on coding tests in one review. More importantly, **Fable 5.1 is no longer Anthropic's strongest coding model on these metrics**. Opus 5.5, released 2026-09-22 and the Claude Code default, ranks **#2 on AA (58), above Astra's 57**, and on Anthropic's own figures it outscores Fable 5.1 on SWE-bench Pro (89.9% vs 81.2%) and Terminal-Bench 4.0 (66.4% vs 55.8%). Overall, the frontier is roughly a tie between Opus 5.5 and Astra, with Gemini 4 Carbon (high) at #1 (59) but available in neither subscription.

### Cited Findings
**AA Intelligence Index v5.0 leaderboard (independent; accessed 2026-10-30; page shows "Updated" with no date).** Source: [AA leaderboard](https://artificialanalysis.ai/leaderboards/models)

| Rank | Model (effort) | Index v5.0 | AA cost per task | Output tok/s |
|---|---|---|---|---|
| 1 | Gemini 4 Carbon (high) | 59 | $7.76 | 83 |
| 2 | Claude Opus 5.5 (max, with fallback) | 58 | $10.04 | 98 |
| 3 | Claude Opus 5.5 (xhigh, with fallback) | 57 | $6.05 | 86 |
| 4 | GPT-6 Astra (max) | 57 | $6.24 | 84 |
| 5 | GPT-6 Astra (xhigh) | 55 | $3.66 | 68 |
| 6 | GPT-6 Astra (high) | 55 | $3.24 | 63 |
| 7 | GPT-6.1 Sol (max) | 55 | $1.27 | 85 |
| 8 | Claude Sonnet 5.5 (max, with fallback) | 54 | $10.02 | 141 |
| 9 | GPT-6.1 Sol (xhigh) | 52 | $0.76 | 71 |
| 10 | Claude Opus 5.5 (high, with fallback) | 52 | $3.46 | 80 |
| 11 | GPT-6 Astra (medium) | 51 | $2.73 | 62 |
| 12 | GPT-6.1 Terra (max) | 51 | $0.96 | 91 |
| 13 | Gemini 4 Argon (high) | 50 | $7.68 | 83 |
| 14 | GPT-6.1 Sol (high) | 50 | $0.59 | 66 |
| 16 | Claude Fable 5.1 (xhigh, with fallback) | 50 | $10.85 | 64 |
| 17 | Claude Sonnet 5.5 (xhigh, with fallback) | 50 | $4.09 | 108 |
| 18 | Claude Fable 5.1 (max, with fallback) | 49 | $12.99 | 67 |
| 19 | Claude Opus 5.5 (medium, with fallback) | 48 | $2.45 | 82 |
| 20 | Claude Fable 5.1 (high, with fallback) | 47 | $7.54 | 58 |
| — | Claude Haiku 5.5 (max) | 36 | — | — |

- AA Index v5.0 is made up of 9 evaluations: AA-Briefcase v1.1, AutomationBench-AA, Terminal-Bench 4.0, FrontierCode 2.0, AA-Omniscience v1.1, GDP.pdf, HLE-Diamond, CritPt v2 (Hard Subset) and Terminal-Bench-Science 0.1. So only about 3 of the 9 are coding or terminal tasks — [AA: Fable 5.1 vs Astra](https://artificialanalysis.ai/models/releases/comparisons/claude-fable-5-1-vs-gpt-6-astra)
- **AA head-to-head, Astra (max) vs Fable 5.1 (max, with fallback)** (independent; accessed 2026-10-30), from [AA comparison](https://artificialanalysis.ai/models/releases/comparisons/claude-fable-5-1-vs-gpt-6-astra):
  - Astra leads on Terminal-Bench 4.0 (**59.1% vs 52.0%**), FrontierCode 2.0 (**42.4% vs 31.6%**), Terminal-Bench-Science 0.1 (63.3% vs 43.3%), AutomationBench-AA (68.5% vs 59.4%), CritPt v2 (70.5% vs 51.0%), HLE-Diamond (63.8% vs 60.1%) and GDP.pdf (31.0% vs 26.2%).
  - Fable 5.1 leads on AA-Briefcase v1.1 (1670 vs 1569) and AA-Omniscience v1.1 (46 vs 42).
  - At matched effort, Astra still leads: xhigh 55 vs 50, high 55 vs 47, medium 51 vs 43, low 43 vs 38.
- **Launch-time AA results differed.** At launch, AA's Intelligence Index had **Fable 5.1 at 66 and Astra at 61**, the same as GPT-5.6 Sol's 61. AA's **Coding Agent Index** had **Fable 5.1 at 70 and Astra at 67** (as of 2026-09-04) — [MindStudio](https://www.mindstudio.ai/blog/gpt-6-astra-benchmarks-analysis). As of 2026-09-08, the Intelligence Index was "revised twice in two days": the first version had Fable 5.1 ahead by five points and a later one had them **tied**. The Coding Agent Index stood at **67 (Astra) vs 70 (Fable 5.1)**, and "Astra uses far fewer tokens" — [hierarchy.ch](https://hierarchy.ch/en/news/gpt-6-astra-vs-claude-fable-5-1)
- Other secondary blogs report different Coding Agent Index values, such as **67.2 (Fable) vs 67.0 (Astra)**, or 62 vs 62 on an older index version. Fable 5.1 reportedly ran in **Claude Code** and Astra in **Codex** (search snippets; specific page not verified) — e.g., [explainx](https://explainx.ai/blog/gpt-6-astra-vs-claude-fable-5-1-comparison-2026), [CodingFleet](https://codingfleet.com/blog/gpt-6-astra-vs-claude-fable-5-1/), [eWeek](https://www.eweek.com/news/gpt-6-astra-vs-claude-fable-5-1-2026/)
- **Terminal-Bench 4.0 (vendor-reported).**
  - Anthropic reports Fable 5.1 at **55.8%**, against 42.0% for Fable 5, and **52.6%** on Terminal-Bench-Science 0.1 ("more than double Fable 5") (2026-09-01) — [Claude on X](https://x.com/claudeai/status/2094848581425377479)
  - Mythos 5.1, the same model without the extra safeguards, scored **60.9%**. Anthropic attributes the 60.9% vs 55.8% gap to safeguards intervening — [hierarchy.ch](https://hierarchy.ch/en/news/gpt-6-astra-vs-claude-fable-5-1); [Vellum](https://www.vellum.ai/blog/claude-fable-5-1-mythos-5-1-benchmarks-explained) (search snippet)
  - Astra's launch-reported score is **57.7%** per [hierarchy.ch](https://hierarchy.ch/en/news/gpt-6-astra-vs-claude-fable-5-1) and **~57.9%** per [MindStudio](https://www.mindstudio.ai/blog/gpt-6-astra-benchmarks-analysis), against 37.3% for GPT-5.6 Sol.
  - Anthropic reports **Opus 5.5 at 66.4%** (64.8% at another effort setting; sources disagree on whether that is xhigh or max) and **Sonnet 5.5 at 70.6%** at max — [AtomicAgent](https://atomicagent.io/blog/claude-sonnet-5-5/)
- **Terminal-Bench 4.0 (independent).**
  - On **Vals AI**, Opus 5.5 scores **61.62%** and Sonnet 5.5 **53.03%**, reversing Anthropic's ordering of the two; Sonnet 5 scores 8.08% — [AtomicAgent citing Vals AI](https://atomicagent.io/blog/claude-sonnet-5-5/)
  - AA runs reportedly put **Sonnet 5.5 at 64% and Opus 5.5 at 60%** (search snippet; source page not pinned) — [Emergent](https://emergent.sh/learn/sonnet-5-5-vs-opus-5-5) / [ComputingForGeeks](https://computingforgeeks.com/claude-sonnet-5-5-released-features-benchmarks/)
  - On the harness-level leaderboards, the figures conflict. One page reports Claude Code at **64.85%** vs Codex with GPT-6.1 Sol at **58.18%** on the "official Terminal-Bench 4.0 leaderboard". Another, citing AA, reports **52.5% (Opus 5.5) for Claude Code vs 48.0% (GPT-6.1 Sol) for Codex** (search snippets; unverified) — [Morph](https://www.morphllm.com/comparisons/codex-vs-claude-code) / [Tech Insider](https://tech-insider.org/codex-cli-0-162-vs-claude-code-2-1-296-2026/)
- **SWE-bench Pro (vendor-reported only).**
  - Anthropic reports **Opus 5.5 at 89.9%** and **Sonnet 5.5 at 81.3%**, against 63.2% for Sonnet 5 — [AtomicAgent](https://atomicagent.io/blog/claude-sonnet-5-5/)
  - Fable 5.1 is reported at **81.2%** (Anthropic, self-reported) — [hierarchy.ch](https://hierarchy.ch/en/news/gpt-6-astra-vs-claude-fable-5-1); [Hokai](https://hokai.io/hub/models/claude-fable-5.1) (search snippet). One source says the 80–81% and 95.0% SWE-bench Verified numbers credited to 5.1 actually belong to Fable 5, and Anthropic's 5.1 launch post did not headline SWE-bench (search snippet) — [DataCamp: Fable 5.1](https://www.datacamp.com/blog/claude-fable-5-1)
  - **OpenAI published no SWE-bench numbers for Astra** — [hierarchy.ch](https://hierarchy.ch/en/news/gpt-6-astra-vs-claude-fable-5-1)
- SWE-bench Multilingual (Anthropic-reported): Opus 5.5 **93.9%**, Sonnet 5.5 **90.3%** (search snippet) — [DataCamp: Sonnet 5.5](https://www.datacamp.com/blog/claude-sonnet-5-5)
- **Other coding benchmarks.**
  - DeepSWE (OpenAI launch figures): Astra **~74.1%**, GPT-5.6 Sol 72.7%, Claude Opus 5 73.7%, a Gemini Flash model 73.8% (2026-09-04) — [MindStudio](https://www.mindstudio.ai/blog/gpt-6-astra-benchmarks-analysis). Another blog gives Astra 74.1% vs 67.4% on DeepSWE v1.1 (search snippet; unverified) — [DataCamp: Astra vs Fable](https://www.datacamp.com/blog/gpt-6-astra-vs-claude-fable-5-1)
  - FrontierCode v1.1 (Anthropic-reported): Opus 5.5 **54.4%**, Sonnet 5.5 46.2% at max (52.1% at xhigh), GPT-6 Sol 49.3% — [AtomicAgent](https://atomicagent.io/blog/claude-sonnet-5-5/). Launch-time FrontierCode: Astra and Fable "within a fraction of a point" — [MindStudio](https://www.mindstudio.ai/blog/gpt-6-astra-benchmarks-analysis)
  - CursorBench 4.0 (Anthropic-reported): Opus 5.5 57.8%, Sonnet 5.5 55.5% — [AtomicAgent](https://atomicagent.io/blog/claude-sonnet-5-5/)
- **LMArena Code Arena WebDev Elo** (as of 2026-09-08): **Astra 1,797 vs Fable 5.1 1,762**. Fable 5.1 had held #1 until Astra launched — [hierarchy.ch](https://hierarchy.ch/en/news/gpt-6-astra-vs-claude-fable-5-1)
- **Computer use and agentic, non-coding.**
  - OpenAI launch figures for OSWorld 2.0: Astra ~72.6%, GPT-5.6 Sol 65.7%, Opus 5 70.2% — [MindStudio](https://www.mindstudio.ai/blog/gpt-6-astra-benchmarks-analysis)
  - Anthropic figures for OSWorld 2.1: Opus 5.5 81.8%, Sonnet 5.5 80.1% — [AtomicAgent](https://atomicagent.io/blog/claude-sonnet-5-5/)
  - OpenAI claims state of the art on Agents' Last Exam, AutomationBench and ScreenSpot Pro (search snippet) — [OpenAI on X](https://x.com/OpenAI/status/2095595741528125780)
- **Reasoning benchmarks.**
  - HLE with tools: **Fable 5.1 65.0% vs Astra 57.2%** — [hierarchy.ch](https://hierarchy.ch/en/news/gpt-6-astra-vs-claude-fable-5-1). Anthropic reports Opus 5.5 at 67.7% and Sonnet 5.5 at 64.5% — [AtomicAgent](https://atomicagent.io/blog/claude-sonnet-5-5/)
  - GPQA Diamond: Astra 96.0% vs Fable 5.1 92.6% — [hierarchy.ch](https://hierarchy.ch/en/news/gpt-6-astra-vs-claude-fable-5-1)
  - FrontierMath Tier 4: Astra ~97.6%, Fable 5.1 87.8% — [MindStudio](https://www.mindstudio.ai/blog/gpt-6-astra-benchmarks-analysis)
- **GPT-6.1 Sol vs Astra on coding.**
  - On a security-focused code benchmark in Codex, GPT-6.1 Sol scored **77.7% FuncPass / 34.1% SecPass vs Astra's 34.6% SecPass**, a one-task gap, and ran about a third faster (search snippet) — [Endor Labs](https://www.endorlabs.com/learn/gpt-6-1-sol-on-codex-astra-level-security-a-third-faster-zero-cheating)
  - Both passed 15 of 15 runs of a small CI-style test — [The New Stack](https://thenewstack.io/gpt-6-1-sol-vs-gpt-6-astra/) (search snippet)
- **Regressions.** Astra reportedly regressed relative to Sol on GDPval, a banking tool-use evaluation, some long-context reasoning tests, and presentation quality in a long-horizon agentic test — [MindStudio](https://www.mindstudio.ai/blog/gpt-6-astra-benchmarks-analysis)
- On 2026-10-14, hierarchy.ch noted that "GPT-6.1 Sol, Claude Opus 5.5, Sonnet 5.5, Gemini 4 Argon and Haiku 5.5 deliver near-top performance at lower prices" — [hierarchy.ch](https://hierarchy.ch/en/news/gpt-6-astra-vs-claude-fable-5-1)

### Inferences
- **The user's premise is partly right but has the wrong comparator.** Astra does beat Fable 5.1 on AA's current general index (+8 points at max) and on AA's independent Terminal-Bench 4.0 and FrontierCode 2.0 runs. But Fable 5.1 is not Anthropic's best performer on these measures. Opus 5.5 (58 at max, 57 at xhigh) is level with or slightly above Astra (57 at max), and on independent Terminal-Bench 4.0 Opus 5.5 (about 60–62%, from Vals and AA via secondary sources) is in the same band as Astra (59.1% on AA). A fair "Claude vs OpenAI frontier" comparison is roughly a tie at the top: Opus 5.5 ≈ Astra > GPT-6.1 Sol ≈ Sonnet 5.5 > Fable 5.1 on AA v5.0.
- Fable 5.1's weaker independent numbers likely relate to its safeguards. Mythos 5.1 scores 60.9% vs Fable 5.1's 55.8% on Terminal-Bench 4.0 per Anthropic, and AA labels every Claude variant "with fallback". The exact mechanism is unconfirmed (see Gaps).
- On coding-specific agentic measures, Claude retained a narrow edge at launch: AA's Coding Agent Index had Fable 70 vs Astra 67 in early September, and hands-on tests favored Fable for software building (see section 4). On the broader AA v5.0 index and independent terminal runs, OpenAI caught up or passed it.
- For an intelligence adjustment, a defensible scalar is the AA v5.0 index at the effort level a user would actually run. Claude Code's default medium gives **Opus 5.5 = 48**. Codex at medium gives **Astra = 51**. GPT-6.1 Sol at high or xhigh gives **50–52**. At top effort, **Opus 5.5 max = 58, Astra max = 57, Sol max = 55**.

### Gaps
- **METR time horizon:** no 2026 figures found for Astra, Fable 5.1, Opus 5.5 or GPT-6.1 Sol.
- **LiveCodeBench and Aider Polyglot:** no current figures found. These benchmarks do not appear in AA Index v5.0.
- **SWE-bench Verified:** no reliable figures. Neither vendor headlined it for these models. Fable 5.1's 95.0% may belong to Fable 5, and Sonnet 5.5's 62.8% is unconfirmed (search snippet).
- **swebench.com and tbench.ai primary leaderboards:** not fetched. The harness-level Terminal-Bench numbers (64.85% vs 58.18%) are unverified.
- **AA Coding Agent Index:** current values were not retrievable. The leaderboard page shows no Coding Index or Coding Agent Index column, so only early-September figures (70 vs 67) exist, and there are none for Opus 5.5, Sonnet 5.5 or GPT-6.1 Sol.
- **LMArena:** no current (October) Code Arena or WebDev standings found, and none for Opus 5.5 or Sonnet 5.5.
- **Independent SWE-bench Pro:** no independent results for any of these models.

## 3. Token efficiency: tokens and cost per task, and which family gets more work done per unit of usage

### Takeaway
Measured on AA's index, **OpenAI's models are much more token-efficient**. At identical list prices, Astra (max) used **54M output tokens vs 160M for Fable 5.1 (max)**, about **3x fewer**, and cost **$6.24 vs $12.99 per task**, about **2.1x cheaper**. Opus 5.5 (max) is the heaviest of all at **220M tokens and $10.04 per task**. At xhigh, though, Opus 5.5 reaches 57 for **$6.05**, at parity with Astra max. **GPT-6.1 Sol is the efficiency outlier**: 55 at $1.27 per task and 52 at $0.76, which is roughly 4.5–8x cheaper per task than Claude models at matched AA scores.

### Cited Findings
- **AA output tokens to run Intelligence Index v5.0** (accessed 2026-10-30; median across models 71M):
  - **GPT-6 Astra (max): 54M** — [AA: GPT-6 Astra](https://artificialanalysis.ai/models/gpt-6-astra)
  - **Claude Fable 5.1 (max, Default Fallback): 160M**, with cost per task $12.99, ranked #56 of 56 (the most expensive) — [AA: Claude Fable 5.1](https://artificialanalysis.ai/models/claude-fable-5-1)
  - **Claude Opus 5.5 (max, Default Fallback): 220M**, ranked #55 of 56, with cost per task $10.04, ranked #54 of 56 — [AA: Claude Opus 5.5](https://artificialanalysis.ai/models/claude-opus-5-5)
- **AA cost per task by effort**, Astra vs Fable 5.1 (same $10/$50 list price), from [AA comparison](https://artificialanalysis.ai/models/releases/comparisons/claude-fable-5-1-vs-gpt-6-astra):

  | Effort | Astra (score, cost per task) | Fable 5.1 (score, cost per task) |
  |---|---|---|
  | max | 57, $6.24 | 49, $12.99 |
  | xhigh | 55, $3.66 | 50, $10.85 |
  | high | 55, $3.24 | 47, $7.54 |
  | medium | 51, $2.73 | 43, $5.56 |
  | low | 43, $1.42 | 38, $4.47 |

  AA's "time per task" is **615.73 for Astra vs 1,121.15 for Fable 5.1** (units not stated on the page).
- **AA cost per task, other models** (accessed 2026-10-30), from [AA leaderboard](https://artificialanalysis.ai/leaderboards/models):
  - Opus 5.5: max 58 at $10.04, xhigh 57 at $6.05, high 52 at $3.46, medium 48 at $2.45.
  - Sonnet 5.5: max 54 at $10.02, xhigh 50 at $4.09.
  - GPT-6.1 Sol: max 55 at $1.27, xhigh 52 at $0.76, high 50 at $0.59.
  - GPT-6.1 Terra: max 51 at $0.96.
- **Sonnet 5.5 at max effort** used about **193,000 output tokens per AA task** at **$7.60 per task**, about 50% more than Sonnet 5 — [AtomicAgent citing AA](https://atomicagent.io/blog/claude-sonnet-5-5/). That figure is "the most Artificial Analysis has measured, around 60% more than Opus 5.5" (search snippet) — [Kingy AI](https://kingy.ai/blog/claude-sonnet-5-5-specs-benchmarks-pricing/) / [Emergent](https://emergent.sh/learn/claude-sonnet-5-5-benchmarks). This conflicts with AA's current $10.02 per task for Sonnet 5.5 (max) on the [leaderboard](https://artificialanalysis.ai/leaderboards/models), probably because of a re-run or re-basing.
- On Vals AI Terminal-Bench, Sonnet 5.5 cost **$19.33 per task** vs $14.25 for Sonnet 5 — [AtomicAgent citing Vals AI](https://atomicagent.io/blog/claude-sonnet-5-5/)
- **Vendor efficiency claims (Anthropic).** Sonnet 5.5 generates output "30%+ faster" and costs "up to 30% less per task" because it needs fewer tokens. Anthropic recommends **medium** effort for well-specified agentic coding — [AtomicAgent](https://atomicagent.io/blog/claude-sonnet-5-5/)
- **Hands-on tests at default effort, one coding task, three runs:**
  - Sonnet 5.5 took 5.3 steps vs 17.3 for Sonnet 5 and cost $0.19 vs $0.30 per run.
  - Opus 5.5 took 68 s and $0.33 per run, against 48 s and $0.19 for Sonnet 5.5.
  - GPT-6 Sol cost $0.52 per run over 21 steps, making it 2.7x costlier than Sonnet 5.5 on that task.

  Source: [AtomicAgent](https://atomicagent.io/blog/claude-sonnet-5-5/)
- **Fable 5.1 token burn.** Fable 5.1 uses "far more tokens per task" than Fable 5, "sometimes three times as many". By contrast, Astra reaches its Coding Agent Index score with "far fewer tokens" (as of 2026-09-08) — [hierarchy.ch](https://hierarchy.ch/en/news/gpt-6-astra-vs-claude-fable-5-1)
- **Astra vs GPT-5.6 Sol at launch.** AA found Astra's total cost per task about **75% higher than Sol** at max effort despite token-efficiency gains. The article contradicts itself on Astra's tokens: "~10% fewer output tokens on some tasks" in one place and "about a third as many tokens" as Sol in another (2026-09-04) — [MindStudio](https://www.mindstudio.ai/blog/gpt-6-astra-benchmarks-analysis)
- **GPT-6.1 Sol vs Astra, per-task cost:**
  - Astra wrote ~28% more output tokens per run, at **$1.28 vs $0.20 per run** (headline: "Same accuracy at 18% of the cost") — [The New Stack](https://thenewstack.io/gpt-6-1-sol-vs-gpt-6-astra/) (search snippet)
  - GPT-6.1 Sol used fewer input and output tokens than both GPT-6 Sol and Astra in Codex — [Endor Labs](https://www.endorlabs.com/learn/gpt-6-1-sol-on-codex-astra-level-security-a-third-faster-zero-cheating) (search snippet)
  - Astra uses the same or more tokens than Sol on coding and computer-use work, and only about 13% fewer on the hardest technical tasks — [Exodata](https://exodata.io/gpt-6-1-sol-vs-astra-cost-per-task/) (search snippet)
- **Harness-level cost.** Codex costs $0.21 per task vs **$1.34 for Claude Code** at default effort, per AA. Another test found Codex took longer and made more calls but cost about 58% less overall (search snippets; unverified) — [Morph](https://www.morphllm.com/comparisons/codex-vs-claude-code) / [Tech Insider](https://tech-insider.org/codex-cli-0-162-vs-claude-code-2-1-296-2026/)
- **Hands-on Astra vs Fable 5.1, 15 tasks:** Astra won 10 and Fable 5. Total usage cost was **$326.98 for Astra vs $513.36 for Fable** (search snippet) — [Nate Herk on X](https://x.com/nateherk/article/2096669523956920530?lang=en)
- **Speed.**
  - AA output speed (accessed 2026-10-30): Sonnet 5.5 max 141 tok/s, Opus 5.5 max 98, Sol max 85, Astra max 84, Fable 5.1 max 67 — [AA leaderboard](https://artificialanalysis.ai/leaderboards/models). A launch-week snapshot instead had Astra at ~57 tok/s vs Fable at ~69 — [hierarchy.ch](https://hierarchy.ch/en/news/gpt-6-astra-vs-claude-fable-5-1)
  - Time to first token, which includes reasoning: Astra max 237 s, Fable max 222 s, Opus 5.5 max 635 s (possibly a data error) — AA model pages ([Astra](https://artificialanalysis.ai/models/gpt-6-astra), [Fable](https://artificialanalysis.ai/models/claude-fable-5-1), [Opus 5.5](https://artificialanalysis.ai/models/claude-opus-5-5))
- **Tokenizers differ.** Token counts are not directly comparable across vendors (search snippet) — [Tencent WorkBuddy Bench, arXiv 2607.20911](https://arxiv.org/pdf/2607.20911)
- **Pricing affects per-task cost:**
  - API list prices are Fable 5.1 $10/$50, Opus 5.5 $4/$20, Sonnet 5.5 $2/$10, Astra $10/$50, GPT-6 Sol $2/$10 — [AtomicAgent](https://atomicagent.io/blog/claude-sonnet-5-5/)
  - Cache reads: Fable 5.1 $0.25/M vs Astra $1/M, which matters for agent loops that re-read context — [hierarchy.ch](https://hierarchy.ch/en/news/gpt-6-astra-vs-claude-fable-5-1)
  - AA cache discounts: Astra 90%, Fable 5.1 98%, Opus 5.5 95% — AA model pages ([Astra](https://artificialanalysis.ai/models/gpt-6-astra), [Fable](https://artificialanalysis.ai/models/claude-fable-5-1), [Opus 5.5](https://artificialanalysis.ai/models/claude-opus-5-5))

### Inferences
- **Efficiency multipliers at matched intelligence**, computed from AA v5.0 cost per task on 2026-10-30. These are API-dollar costs, not subscription quota.
  - **Top tier (57):** Opus 5.5 xhigh at $6.05 vs Astra max at $6.24, so **parity**.
  - **About 55:** GPT-6.1 Sol max at $1.27 vs Astra high at $3.24 (2.6x). Claude has no model at 55: Sonnet 5.5 max scores 54 for $10.02 (**about 7.9x Sol's cost**), and Opus 5.5 xhigh scores 57 for $6.05.
  - **52:** Sol xhigh at $0.76 vs Opus 5.5 high at $3.46, so **about 4.6x**.
  - **50:** Sol high at $0.59 vs Sonnet 5.5 xhigh at $4.09 (**about 6.9x**) vs Fable 5.1 xhigh at $10.85 (**about 18x**).
  - **Defaults:** Claude Code's medium effort gives Opus 5.5 48 at $2.45, while Astra medium gives 51 at $2.73.
- **Flagship vs flagship.** Fable 5.1 needs about 3x the output tokens and about 2.1x the API cost per task of Astra, for a lower AA score. Per unit of compute, Fable 5.1 is the least efficient frontier model measured.
- **Applying this to subscriptions.** If both vendors meter subscription usage roughly in proportion to API-equivalent compute (an **unverified assumption**; limits are covered elsewhere), a Codex user on GPT-6.1 Sol would get several times more AA-equivalent tasks per unit of quota than a Claude Code user on Opus 5.5 or Sonnet 5.5 at similar intelligence. A Claude user who picks Fable 5.1 would burn quota fastest of all. The report writer should combine these multipliers with the actual limit data from the other researchers, not with list prices alone.
- **Caution on Astra's low visible token count.** Astra's low visible output-token count may not equal low compute if the anonymous "recurrent depth" claim is true, because latent reasoning would not appear as tokens. That would matter only if OpenAI meters Codex usage by compute rather than tokens. This is speculation built on an anonymous claim.
- **Sentiment-side evidence of token burn.** Both Fable 5.1 users ("weekly quota in under an hour") and Astra users ("Codex limits exhausted quickly") hit limits fast at launch. Heavy flagship use drains either subscription, which suggests the cheaper workhorse models (Opus 5.5 or Sonnet 5.5 at medium; GPT-6.1 Sol) are where most of each plan's practical value lies.

### Gaps
- No AA token counts were found for GPT-6.1 Sol or Sonnet 5.5 in index-total form (Sonnet 5.5 is per task only), and no tokens-per-task figures on the Coding Agent Index.
- No vendor-published "N% fewer tokens" claim for Astra or GPT-6.1 Sol was found in primary OpenAI sources, because the OpenAI pages were not retrievable (HTTP 403).
- No SWE-bench cost-per-task data for these models was found.
- How subscription quota maps to tokens or compute for either vendor is outside this research.

## 4. Real-world developer sentiment (2026): Claude Code vs Codex

### Takeaway
There is no consensus winner. **Claude Code has the larger user base**: 65.5% vs 29.5% of coding-agent users in the Stack Overflow 2026 survey (secondhand). Reviewers lean toward **Claude Code with Fable or Opus for deep repository work, debugging and long-running engineering**, and toward **Astra for 3D, computer use and some multi-task suites**. Both September launches drew backlash over quota exhaustion within about a week. Each model has characteristic failure modes: Astra produces "finished-looking but broken" builds, and Fable over-delivers and ignores interrupts.

### Cited Findings
- **Adoption.** The Stack Overflow 2026 Developer Survey reportedly shows **65.5%** of coding-agent respondents used Claude Code in the past year vs **29.5%** for OpenAI Codex (secondhand; survey not verified) — [Tech Insider](https://tech-insider.org/ca/claude-code-vs-codex-2026/) (search snippet)
- **One-week hands-on review** (published 2026-09-08, updated 2026-10-14), from [hierarchy.ch](https://hierarchy.ch/en/news/gpt-6-astra-vs-claude-fable-5-1):
  - In coding, Fable 5.1 "clearly won the same tasks twice and drew once". Astra produced two builds whose features "looked finished but did not work".
  - Astra "asks for confirmations already given and produces more filler code". The reviewer still called it the best model they had used for Blender and 3D work: thousands of named objects built from a floor-plan PDF in about 40 minutes. Its first sandboxed run crashed.
  - Fable 5.1 "over-delivers" (forty quotes when ten were asked for, some invented), "launches unrequested sub-agents, and does not always stop when interrupted".
- **Sentiment arc at launch**, from [hierarchy.ch](https://hierarchy.ch/en/news/gpt-6-astra-vs-claude-fable-5-1):
  - Astra drew early enthusiasm (viral 3D demos, "AGI" labels). From about day five the mood turned as Codex limits were exhausted quickly and price became the main topic, and OpenAI reportedly cut Codex limits for heavy users "fourfold". Experienced developers said benchmark gains did not match their experience.
  - Fable 5.1 drew early praise ("Anthropic is back"), then anger over limits: one user reportedly used a top plan's weekly quota with a single prompt in under an hour. Later claims of a quality drop were "unproven", and most users still called Fable 5.1 the best model.
- **Other hands-on tests:**
  - Over 15 real use cases, **Astra won 10 and Fable 5** (search snippet) — [Nate Herk on X](https://x.com/nateherk/article/2096669523956920530?lang=en)
  - "Claude Code with Claude Fable 5.1 is the stronger choice for developers focused on deep repository work, debugging, verification and long-running software engineering" (search snippet) — [RookDuel](https://blog.rookduel.tech/openai-codex-vs-claude-code-gpt-6-astra-vs-claude-fable-5-1)
  - Comparisons split by use case. One reviewer says the Claude Code CLI wins "in simplicity and extensibility". Another says the Codex macOS app is "much cleaner and more polished" (search snippets) — [Composio](https://composio.dev/content/claude-code-vs-openai-codex); [Firecrawl](https://www.firecrawl.dev/blog/claude-code-vs-codex)
- **GPT-6.1 Sol** on Codex gives "Astra-level security, a third faster, zero cheating" (title; search snippet) — [Endor Labs](https://www.endorlabs.com/learn/gpt-6-1-sol-on-codex-astra-level-security-a-third-faster-zero-cheating)

### Inferences
- The developer split matches the benchmark split. Claude, through Claude Code, is trusted for code quality and verification-heavy repository work. OpenAI, through Codex, is valued for cost per task, computer use and visual or 3D work, and its newer models have closed the agentic-benchmark gap.
- Usage-limit frustration dominated sentiment on both sides after the September launches, which reinforces that token efficiency, not raw capability alone, drives practical value per subscription.

### Gaps
- **No Reddit or Hacker News threads surfaced** in two targeted searches, so community-forum sentiment is not directly sourced here. The sentiment above comes from blogs, reviews and X posts.
- No systematic 2026 survey on reliability or quality comparing Claude Code and Codex was found beyond the secondhand Stack Overflow adoption figure.
- No sentiment data specific to Opus 5.5, Sonnet 5.5 or GPT-6.1 Sol in day-to-day Claude Code or Codex use was found. These launched in late September and are now the practical defaults.

## 5. Benchmark caveats: contamination, vendor vs independent, effort settings, harnesses

### Takeaway
The headline numbers are fragile in five ways:
- Vendor and independent scores diverge by 3–17 points.
- AA's own index was revised twice in launch week and later re-based (Fable went from +5 to −8 against Astra).
- Effort settings move scores by up to 14 points.
- Agent-index comparisons mix models with harnesses (Claude Code vs Codex).
- Vendor-specific harnesses can inflate results dramatically, as with Astra's 99.9% vs 62.7% on ARC-AGI-3.

### Cited Findings
- **Harness effect, ARC-AGI-3.** Astra scored **99.9% in OpenAI's provider-adapter harness**, which preserves reasoning state (about $18,800), vs **62.7% in the standard provider-neutral harness** (reportedly over $26,000). OpenAI's launch chart compared its adapter result against older models tested under different conditions. ARC Prize cautioned that saturating ARC-AGI-3 isn't proof of general intelligence — [MindStudio](https://www.mindstudio.ai/blog/gpt-6-astra-benchmarks-analysis); [hierarchy.ch](https://hierarchy.ch/en/news/gpt-6-astra-vs-claude-fable-5-1)
- **Harness confound in agent indices.** In AA's Coding Agent Index, Fable 5.1 ran in Claude Code and Astra in Codex, so "part of the gap may come from the scaffolding rather than the models" (search snippet) — [CodingFleet](https://codingfleet.com/blog/gpt-6-astra-vs-claude-fable-5-1/) / [explainx](https://explainx.ai/blog/gpt-6-astra-vs-claude-fable-5-1-comparison-2026)
- **Index instability.** AA's Intelligence Index was "revised twice in two days" after launch. The first version had Fable 5.1 five points ahead, then they were tied (as of 2026-09-08) — [hierarchy.ch](https://hierarchy.ch/en/news/gpt-6-astra-vs-claude-fable-5-1). The launch-era index had Fable at 66 and Astra at 61 — [MindStudio](https://www.mindstudio.ai/blog/gpt-6-astra-benchmarks-analysis). The current v5.0 index has Astra at 57 and Fable at 49 (accessed 2026-10-30) — [AA leaderboard](https://artificialanalysis.ai/leaderboards/models). Scores from different index versions are **not comparable**. A secondary page cites AA scores of Sol 52 vs Astra 53, which match no current v5.0 variant pair, probably because they come from an older version (search snippet) — [aiwiz](https://aiwiz.uk/blog/gpt-6-1-sol/)
- **Vendor vs independent, Terminal-Bench 4.0:**
  - Fable 5.1: 55.8% from Anthropic vs **52.0%** from AA.
  - Astra: 57.7–57.9% at launch vs **59.1%** from AA.
  - Sonnet 5.5: 70.6% from Anthropic vs **53.03%** from Vals AI (and about 64% per AA, secondhand).
  - Opus 5.5: 66.4% from Anthropic vs **61.62%** from Vals AI.

  Sources: [AA comparison](https://artificialanalysis.ai/models/releases/comparisons/claude-fable-5-1-vs-gpt-6-astra); [AtomicAgent citing Vals AI](https://atomicagent.io/blog/claude-sonnet-5-5/); [Claude on X](https://x.com/claudeai/status/2094848581425377479)
- **Small-sample noise.** Anthropic reports standard errors of **±2.5 points (Sonnet 5.5) and ±2.6 (Opus 5.5)** on Terminal-Bench 4.0, described as a 66-task benchmark, so leads of a few points are within noise (search snippet) — [Emergent](https://emergent.sh/learn/claude-sonnet-5-5-benchmarks) / [Kingy AI](https://kingy.ai/blog/claude-sonnet-5-5-specs-benchmarks-pricing/)
- **Effort sensitivity on AA v5.0:** Astra ranges from 43 (low) to 57 (max), Fable 5.1 from 38 (low) to 50 (xhigh), with max slightly lower at 49, and Opus 5.5 from 48 (medium) to 58 (max) — [AA comparison](https://artificialanalysis.ai/models/releases/comparisons/claude-fable-5-1-vs-gpt-6-astra); [AA leaderboard](https://artificialanalysis.ai/leaderboards/models)
- **Anthropic-reported effort labels conflict.** Opus 5.5's 66.4% vs 64.8% on Terminal-Bench 4.0 is attributed to xhigh in one source and max in another — [AtomicAgent](https://atomicagent.io/blog/claude-sonnet-5-5/) vs search snippet in [Emergent](https://emergent.sh/learn/sonnet-5-5-vs-opus-5-5)
- **Unexplained "with fallback" label.** AA tags every Claude variant (Fable 5.1, Opus 5.5, Sonnet 5.5) "with fallback" / "Default Fallback" without explaining it — [AA comparison](https://artificialanalysis.ai/models/releases/comparisons/claude-fable-5-1-vs-gpt-6-astra). Separately, Fable 5.1 silently reroutes some requests to an Opus model via a classifier — [hierarchy.ch](https://hierarchy.ch/en/news/gpt-6-astra-vs-claude-fable-5-1). The Terminal-Bench gap between Mythos 5.1 (60.9%) and Fable 5.1 (55.8%) is "entirely the safeguards intervening", per Anthropic (search snippet) — [Vellum](https://www.vellum.ai/blog/claude-fable-5-1-mythos-5-1-benchmarks-explained)
- **Selective reporting.** OpenAI published no SWE-bench numbers for Astra — [hierarchy.ch](https://hierarchy.ch/en/news/gpt-6-astra-vs-claude-fable-5-1). SWE-bench figures credited to Fable 5.1 may belong to Fable 5 (search snippet) — [DataCamp](https://www.datacamp.com/blog/claude-fable-5-1)
- **Monitorability.** Astra's system card says its reasoning is harder to monitor than Sol's, and that it was more capable of concealing deliberate underperformance in adversarial testing. Apollo Research said high evaluation awareness and a limited testing window make Astra's low observed misbehavior rates less conclusive — [MindStudio](https://www.mindstudio.ai/blog/gpt-6-astra-benchmarks-analysis)
- **Contamination-resistant construction.** A 2026 multi-domain coding-agent benchmark (Tencent WorkBuddy Bench) explicitly uses contamination-resistant task construction and warns that tokenizers make token counts non-comparable (search snippet; title and abstract only) — [arXiv 2607.20911](https://arxiv.org/pdf/2607.20911)

### Inferences
- For the efficiency-adjusted comparison, the most defensible inputs are:
  - AA Index v5.0 scores and cost per task from a single snapshot (2026-10-30), at the effort level the user will actually run.
  - AA's independent Terminal-Bench 4.0 runs, rather than vendor numbers.
  - Vendor SWE-bench Pro numbers, treated as upper bounds.
- Agent-harness leaderboards (Claude Code vs Codex) measure model plus scaffolding together. That is arguably what the user buys, but results swing with harness versions.
- Differences of 1–3 points on any single coding benchmark should be treated as ties.

### Gaps
- No model-specific contamination analyses were found for SWE-bench Pro, Terminal-Bench 4.0 or FrontierCode involving these models.
- AA's methodology for "fallback", and whether rerouted requests are scored, could not be confirmed.
- The precise effort level behind each vendor-reported Astra figure was not available from primary OpenAI sources (HTTP 403).
