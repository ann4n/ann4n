# OpenAI's ~$100/month ChatGPT Pro tier ("Pro 100") and Codex usage limits — state as of 2026-10-30

Research date: 2026-10-30. Official OpenAI docs (learn.chatgpt.com, developers.openai.com) were read directly as Markdown/HTML on this date. OpenAI's Help Center (help.openai.com) and openai.com returned HTTP 403 to every fetch, so Help Center content (incl. the canonical "About ChatGPT Pro tiers" article) is known only through press quotes and is labelled that way. **Very recent change:** on 2026-10-29 (the day before this research) OpenAI doubled the Pro 100 Work/Codex allowance. Most community measurements predate that change.

## 1. Does OpenAI sell a ~$100/month tier with Codex? Name, price, launch date, positioning

### Takeaway
Yes. It is **ChatGPT Pro at $100/month**: "Pro $100" on OpenAI's pricing page, and "Pro 100" in Help Center and press usage. It is not a Codex-only plan. It launched on **2026-04-09**, sold as "5x more Codex usage than Plus". Since DevDay (2026-09-29), Pro is sold in three tiers: **$100, $200 and $500**. On **2026-10-29**, OpenAI doubled the Pro 100 Work/Codex allowance and reversed a planned cut to Pro 200. So the user's "Codex Pro 5x" label described the plan at launch and is now out of date.

### Cited Findings
- **Launch, 2026-04-09.** OpenAI's forum post: "We're introducing a new $100/month Pro tier… This new tier offers 5x more Codex usage than Plus and is best for longer, high-effort Codex sessions." The tier keeps all Pro features (the exclusive Pro model, unlimited Instant and Thinking). — [OpenAI Developer Community announcement, 2026-04-09](https://community.openai.com/t/introducing-new-100-month-pro-tier/1378752)
- **Launch promo (historical).** Codex usage was temporarily raised through 2026-05-31, so Pro $100 subscribers got "up to 10x Plus Codex usage". — [OpenAI Developer Community announcement](https://community.openai.com/t/introducing-new-100-month-pro-tier/1378752); [TechCrunch, 2026-04-09](https://techcrunch.com/2026/04/09/chatgpt-pro-plan-100-month-codex/)
- **$200 tier at launch.** A reply quoting the Help Center at launch described the $200 tier as "20× higher limits than Plus". — [OpenAI Developer Community](https://community.openai.com/t/introducing-new-100-month-pro-tier/1378752)
- **Positioning.** TechCrunch reports both Pro plans share core features and differ mainly in rate limits; neither is unlimited. The move was framed as a challenge to Anthropic's $100 Claude plan. — [TechCrunch](https://techcrunch.com/2026/04/09/chatgpt-pro-plan-100-month-codex/)
  - OpenAI spokesperson: "Compared with Claude Code, Codex delivers more coding capacity per dollar across paid tiers."
  - Codex then had >3M weekly users, up 5x in three months.
- **Current official pricing page.** — [OpenAI Codex pricing (developers.openai.com/codex/pricing redirects here)](https://learn.chatgpt.com/docs/pricing)
  - The Pro card reads "From $100/month", "Plans at $100, $200, or $500 USD per month", and "Ultrafast mode access on Pro $500".
  - Plan subtitles: Plus is "Power a few focused coding sessions each week"; Pro is "Choose the Pro plan that fits your usage".
  - Other individual plans: Free $0, Go $8 (Codex on GPT-6 Luna in the desktop app "subject to rollout"), Plus $20.
- **DevDay 2026, 2026-09-29 (San Francisco).** — [The Next Web, 2026-09-29](https://thenextweb.com/news/openai-devday-pro-200-usage-cut-pro-500-plan); [Valletta Software (secondary; updated 2026-09-30)](https://vallettasoftware.com/blog/post/chatgpt-pro-max)
  - OpenAI launched Pro 500 ($500/month). An OpenAI post on X, as quoted by Valletta, called it "a new plan with our highest usage limits (25x Plus) and access to Ultrafast".
  - It announced Pro 200's Work/Codex allowance would fall from 20x to 10x Plus from 2026-10-30. Existing subscribers were protected until 2026-10-29 and got a one-time credit grant worth about $2,500 (62,500 credits, expiring end of 2026).
  - Valletta also claims Pro 200 was closed to new buyers from 2026-09-10. This is unverified.
- **Reversal and doubling, 2026-10-29.** — [The Decoder, 2026-10-29](https://the-decoder.com/openai-doubles-usage-limits-on-pro-plans-as-competition-with-anthropic-heats-up/); [Kingy AI](https://kingy.ai/blog/openai-cancels-chatgpt-pro-usage-cut-doubles-allowances/); [Xenospectrum, 2026-10-30](https://xenospectrum.com/en/chatgpt-pro-usage-limits-doubled/)
  - Pro 100 and Pro 500 Work/Codex allowances doubled ("both get twice the previous allotment").
  - Pro 200's lower allowance was raised to match the higher one, and the planned cut was cancelled.
  - Plus is unchanged. Existing subscribers get the new allowance automatically.
  - Sources cite OpenAI's Help Center article "About ChatGPT Pro tiers", the 2026-10-29 ChatGPT release notes, and an X post by Thibault Sottiaux.
- **No new multipliers published.** Xenospectrum states OpenAI has not disclosed absolute allowances or new multipliers vs Plus; it reports changes only relative to each plan's previous allowance. — [Xenospectrum](https://xenospectrum.com/en/chatgpt-pro-usage-limits-doubled/)
- **Naming.** The canonical Help Center article is titled "About ChatGPT Pro tiers" (it returned 403 to me). — [help.openai.com/en/articles/9793128](https://help.openai.com/en/articles/9793128-about-chatgpt-pro-tiers)
  - Community members also called the $100 tier "Pro Lite". — [GitHub openai/codex discussion #26975](https://github.com/openai/codex/discussions/26975)

### Inferences
- The tier's exact name is "ChatGPT Pro" at $100, styled "Pro $100" or "Pro 100". There is no product called "Codex Pro 5x".
- The nominal ratios have moved over time:
  - Pro 100 was 5x Plus at launch.
  - If the 5x baseline held until 2026-10-29, doubling makes Pro 100 nominally **~10x Plus** for Work/Codex today.
  - By the same logic, Pro 500 would be ~50x Plus (25x doubled) and Pro 200 ~20x.
  - OpenAI has not published these numbers; they are arithmetic on reported relative changes.
- The doubling came one day before this research and limits have changed several times in 2026 (see Q2). The user should expect further changes in either direction.

### Gaps
- I could not read the official Help Center "About ChatGPT Pro tiers" page (HTTP 403). Current absolute or relative multipliers come only from press quotes.
- I could not confirm whether the $2,500 Pro 200 credit grant survived the 2026-10-29 reversal.

## 2. Codex usage limits: Plus vs Pro 100 vs Pro 200 (5-hour, weekly, local vs cloud, code review); is $100 "5x Plus" weekly?

### Takeaway
- **Plus** has rolling ~5-hour windows plus weekly limits. **All Pro tiers currently have no 5-hour limit, only a weekly limit.**
- OpenAI now publishes only Plus per-model local-message ranges per 5 hours. It publishes no Pro numbers and no separate cloud-task or code-review quotas: local messages and cloud chats share one allowance.
- "5x Plus" was the official launch claim. One careful independent measurement (before 2026-10-29) found Pro 100 ≈ 5.1x Plus on raw weekly usage, but ≈ 3.7x after correcting for model mix.
- Since 2026-10-29, Pro 100's allowance is reported doubled, so it is nominally ~10x Plus.

### Cited Findings
- **Official Plus estimates, local messages per five-hour period** (same for Standard Business). — [OpenAI Codex pricing](https://learn.chatgpt.com/docs/pricing)

  | Model | Messages per 5 hours |
  |---|---|
  | GPT-6 Astra | 5–45 |
  | GPT-6.1 Sol | 15–160 |
  | GPT-6.1 Terra | 35–285 |
  | GPT-6 Sol | 15–150 |
  | GPT-6 Luna | 350–3,000 |

  - "These estimates are not fixed message limits."
  - "Local messages and cloud chats share your plan's usage allowance. Weekly limits may also apply."
  - "Pro plans currently have no five-hour limit."
  - "Cloud tasks may use more of your allowance than local messages."
- **Shared allowance.** "ChatGPT Work and Codex share usage." Limits are also shared with other agentic features such as ChatGPT for Excel on Plus and Pro. Image generation uses included limits ~3–5x faster. Voice in Desktop costs $0.05/minute of the Codex usage budget. — [OpenAI Codex pricing](https://learn.chatgpt.com/docs/pricing)
- **Code review.** "Code Review usage applies only when Codex runs reviews through GitHub… Reviews run locally or outside of GitHub count toward your general usage limits." No per-plan code-review quota is published. — [OpenAI Codex pricing](https://learn.chatgpt.com/docs/pricing)
- **Mid-turn behaviour.** "If you reach your usage limits during an active turn, the agent will be able to continue working on that turn, subject to fair use limits." Limits are visible in the usage dashboard and via `/status` in the CLI. — [OpenAI Codex pricing](https://learn.chatgpt.com/docs/pricing)
- **Changelog confirmation** — [Codex changelog](https://learn.chatgpt.com/docs/changelog):
  - 2026-09-03: "Plus and Team users receive an earlier warning when less than half of their allowance remains in an approximately five-hour usage window."
  - 2026-10-13: "Usage widgets adapt to accounts with only a weekly limit."
- **Commitment on the 5-hour limit.** OpenAI said it will not reintroduce the Pro 200 five-hour limit; Valletta quotes Sottiaux committing to "not reintroducing the 5h limit". — [The Next Web](https://thenextweb.com/news/openai-devday-pro-200-usage-cut-pro-500-plan); [Valletta](https://vallettasoftware.com/blog/post/chatgpt-pro-max)
- **Since the Oct 29 change.** Pro 100, 200 and 500 have no 5-hour cap in Work/Codex; weekly limits and model-specific rules still apply. — [Kingy AI](https://kingy.ai/blog/openai-cancels-chatgpt-pro-usage-cut-doubles-allowances/); [Xenospectrum](https://xenospectrum.com/en/chatgpt-pro-usage-limits-doubled/)
- **OpenAI caveat on the doubling.** "Doubling a quota doesn't mean twice as many completed tasks", because consumption varies by model, task type and settings. — [The Decoder](https://the-decoder.com/openai-doubles-usage-limits-on-pro-plans-as-competition-with-anthropic-heats-up/)
- **Independent measurement: MulletIQ, published 2026-10-21, by Merijn van Es.** One account per plan, meter read about hourly with CodexBar, usage priced from local logs at API list rates. All figures predate the 2026-10-29 doubling. — [MulletIQ](https://mulletiq.com/blog/claude-max-5x-vs-codex-pro)

  | Measure | Pro 100 ("Codex Pro 5x") | Plus |
  |---|---|---|
  | Weekly limit, API-equivalent $ (95% interval) | ~$325 ($314–$335) | ~$63 ($60–$73) |
  | Sample period | 2026-09-07 to 2026-10-07 | 2026-10-08 to 2026-10-21 |

  - Pro/Plus ratio: **5.1x raw, 3.7x (3.2–4.0x) after model-mix correction.**
  - Pro's weekly limit was ~$338 before the 2026-09-29 plan change and ~$308 after, so DevDay barely changed Pro 100's weekly size.
  - No 5-hour limit was seen on Pro, and the busiest day used 83% of a week's allowance.
  - There were 20 early weekly resets across Plus and Pro between 2026-08-01 and 2026-10-21.
- **Community reports, historical (GPT-5.5 era, June 2026).** Many users reported cuts around 2026-06-04. — [GitHub openai/codex discussion #26975](https://github.com/openai/codex/discussions/26975)
  - One $100 user: "100-150m tokens were 10-14% of $100 weekly allowance" before; a 150M-token day was 40% after.
  - One $200 user logged 4.21B tokens (2026-05-30 to 06-03) vs 1.81B (06-06 to 06-08) before running out.
  - No OpenAI staff replied. These figures are anecdotal and token counts are mostly cached input.
- **Historical anecdotes, April 2026 forum.** One user hit the 5-hour limit after 3 prompts; another said one session took weekly usage to 84%. — [OpenAI Developer Community](https://community.openai.com/t/introducing-new-100-month-pro-tier/1378752)
- **Historical promo.** 2026-02-02 changelog: "For a limited time… Plus, Pro, Business, Enterprise, and Edu plans get double rate limits." — [Codex changelog](https://learn.chatgpt.com/docs/changelog)

### Inferences
- **"5x Plus" was true in OpenAI's marketing** at launch and through 2026-10-28. Measured weekly value was roughly 3.7–5.1x Plus depending on model mix.
- **After 2026-10-29:** if the doubling applies to the weekly meter, Pro 100 should be roughly 7.4–10x Plus weekly (2 × 3.7–5.1). This is unmeasured.
- **Pro beats Plus by more than the weekly ratio for bursty work.** Plus is also throttled by 5-hour windows and Pro is not, so Pro 100 can spend most of a week's budget in one day.
- **Limits are volatile.** They changed at least in February (2x promo), April (launch, 10x promo to 05-31), around June 4 (community-reported cuts), September 29 (DevDay restructure) and October 29 (doubling). Any figure should be treated as a snapshot.

### Gaps
- No official absolute weekly limits, for any plan.
- No official Pro 100/200/500 per-5-hour or per-week message estimates. OpenAI now publishes per-model ranges for Plus only.
- No published cloud-task or GitHub code-review quotas.
- No independent measurement yet of Pro 100 after the 2026-10-29 doubling, or of Pro 200/500 at all.

## 3. Which models each tier can use; Pro-only restrictions; does higher reasoning effort burn limits faster?

### Takeaway
- Plus and all Pro tiers get the same Codex model lineup: GPT-6 Astra (flagship), GPT-6.1 Sol, GPT-6.1 Terra, GPT-6 Sol, and GPT-6 Luna (small/cheap).
- The only Pro-gated speed feature is **Ultrafast**, on **Pro $500 only**, for Astra and 6.1 Sol.
- Pro 100 and Pro 200 also get small Pro-only extras (Composer predictions beta, Computer History), but no exclusive Codex model.
- Higher reasoning effort, Max/Ultra modes, Fast mode and subagents all consume allowance faster. Fast costs 2.5x and Ultrafast 8x of included usage.

### Cited Findings
- **Plus plan card** lists "GPT-6.1 Sol and GPT-6 Luna". — [OpenAI Codex pricing](https://learn.chatgpt.com/docs/pricing)
  - The feature matrix marks GPT-6.1 Sol, GPT-6.1 Terra, and "GPT-6 Sol and Luna" available on Plus, Pro, Business, Enterprise and API key.
  - GPT-6 Astra appears in the Plus 5-hour table (5–45 messages), so it is usable on Plus.
  - Free/Go get only GPT-6 Luna in the desktop app.
- **Model roles.** — [Codex Models doc](https://learn.chatgpt.com/docs/models); [Model selection](https://learn.chatgpt.com/docs/model-selection)
  - "Astra is our state-of-the-art, most powerful model… Luna is our most efficient model."
  - "GPT-6.1 Sol offers near-Astra performance at a lower cost than Astra."
  - GPT-6.1 Sol has a 500,000-token default context window in Codex.
- **Plan differences in presets.** "Some paid plans omit Astra Extra High… available options and defaults vary by plan, client, workspace settings, and rollout." — [Codex Models doc](https://learn.chatgpt.com/docs/models)
- **Reasoning effort and modes.** — [Codex Models doc](https://learn.chatgpt.com/docs/models)
  - Efforts run Light/Low, Medium, High, Extra High, plus **Max**, which gives the model "more time to reason about a single task… when depth matters more than speed or usage".
  - **Ultra** uses subagents in parallel. GPT-6 Luna supports up to Max, not Ultra.
  - "Higher effort can improve results for complex tasks, but it takes longer and uses more tokens."
  - Subagent workflows "consume more tokens than comparable single-agent runs." — [Subagents doc](https://learn.chatgpt.com/docs/agent-configuration/subagents)
- **Usage drivers.** "Model choice, context, reasoning, tool use, retrieval, and caching all affect usage." — [OpenAI Codex pricing](https://learn.chatgpt.com/docs/pricing)
- **Speed modes.** — [Speed doc](https://learn.chatgpt.com/docs/agent-configuration/speed); [OpenAI Codex pricing](https://learn.chatgpt.com/docs/pricing)
  - Fast mode (`/fast`) is available on all paid plans. It uses included limits at 2.5x the Standard rate and purchased credits at 2x.
  - Ultrafast (GPT-6 Astra, GPT-6.1 Sol) is "available in Codex and ChatGPT Work on Pro $500 and eligible Enterprise and Edu plans". It uses included limits at 8x and credits at 6x. Astra Ultrafast generates tokens "up to 8x faster".
  - "Other self-serve plans don't have access to Ultrafast at launch, even with purchased credits."
- **Pro-only extras** (no Pro-only Codex model). — [OpenAI Codex pricing](https://learn.chatgpt.com/docs/pricing); [Codex changelog, 2026-10-09](https://learn.chatgpt.com/docs/changelog)
  - Composer predictions (beta, "limited" on Pro, unavailable on Plus) applies to "personal ChatGPT Pro plans in supported regions… with GPT-6 Astra and GPT-6.1 Sol".
  - Computer History is available on Pro but not Plus.
- **Model release and retirement timeline** — [Codex changelog](https://learn.chatgpt.com/docs/changelog); [Codex Models doc](https://learn.chatgpt.com/docs/models):
  - 2026-08-31: `gpt-5.4` and `gpt-5.4-mini` retired from Codex with ChatGPT sign-in.
  - 2026-09-14: GPT-5.3-Codex-Spark retired. It was a Pro-only research preview, >1000 tokens/s, launched earlier in 2026.
  - 2026-09-22: GPT-6 Sol and GPT-6 Luna rolled out.
  - 2026-09-29: GPT-6.1 Sol.
  - 2026-10-07: GPT-6.1 Sol became the default in bundled catalogs.
  - 2026-10-08: GPT-6.1 Sol Ultrafast.
  - 2026-10-14: GPT-5.5 retired from ChatGPT sign-in (still available via API key).
  - 2026-10-26: GPT-6.1 Terra on Plus, Pro, Business, Enterprise and Edu.
- **Measured model costs against the Pro 100 weekly meter** (MulletIQ, pre-Oct 29). Each 1% of the weekly limit bought: — [MulletIQ](https://mulletiq.com/blog/claude-max-5x-vs-codex-pro)
  - GPT-6/5.6 Luna: $9.58 of API-list value
  - GPT-6 Astra: $3.65
  - GPT-6/6.1 Sol: $2.48
  - "Sol used about 1.5x the limit per dollar of list price that Astra did."

### Inferences
- On the $100 tier, the user would get the same frontier coding model (GPT-6 Astra) as the $200 and $500 tiers. Higher tiers buy more volume, and only Pro 500 adds Ultrafast speed.
- **The Plus table shows Astra burns allowance about 3x faster than Sol** (5–45 vs 15–160 messages per 5 hours). A Pro 100 user who defaults to Astra at Extra High/Max, or uses Ultra/subagents, will drain the weekly limit much faster than one using GPT-6.1 Sol at Medium.

### Gaps
- Which "paid plans omit Astra Extra High" is not stated; it may be Plus.
- No official per-effort multipliers for subscription usage. Higher effort costs more only because it uses more tokens.
- GPT-6 Astra's exact release date was not found in the changelog excerpt.

## 4. Overage / credits: can Codex users buy more, and at what price?

### Takeaway
- **Plus and Pro users can buy ChatGPT credits** to keep working past included limits, without upgrading. Credits were introduced 2025-10-30.
- Credits are charged per token at a published rate card. At the evidently standard **$0.04/credit**, the rates equal OpenAI API list prices.
- Alternatively, any user can sign Codex in with an **API key** and pay API rates.

### Cited Findings
- **Buying credits.** "ChatGPT Plus and Pro users who reach their usage limit can purchase additional credits to continue working without needing to upgrade their existing plan." — [OpenAI Codex pricing](https://learn.chatgpt.com/docs/pricing)
- **API-key overflow.** "All users may also run extra local chats using an API key, with usage charged at standard API rates." — [OpenAI Codex pricing](https://learn.chatgpt.com/docs/pricing)
- **Credit rate card**, credits per 1M tokens (input / cached input / output), Standard speed. "Codex credit billing has no separate cache-write charge." — [OpenAI Codex pricing](https://learn.chatgpt.com/docs/pricing)

  | Model | Input | Cached input | Output |
  |---|---|---|---|
  | GPT-6 Astra | 250 | 25 | 1,250 |
  | GPT-6.1 Sol | 50 | 2.5 | 250 |
  | GPT-6.1 Terra | 25 | 1.25 | 125 |
  | GPT-6 Sol | 50 | 5 | 250 |
  | GPT-6 Luna | 2.5 | 0.25 | 12.5 |
  | GPT-5.6 Sol | 100 | 10 | 500 |

  - "A typical GPT-5.6 Sol task may use 2-15 credits."
  - Fast mode costs 2x the credit rate and Ultrafast 6x.
  - "Credit purchase prices and applicable discounts depend on your plan or agreement."
- **Launch date.** 2025-10-30 changelog: "Codex users on ChatGPT Plus and Pro can now use on-demand credits for more Codex usage beyond what's included in your plan." — [Codex changelog](https://learn.chatgpt.com/docs/changelog)
- **Credit value.** A third-party pricing guide says 1 credit = $0.04 (100 credits = $4). — [Morph (search-result summary, not fetched)](https://www.morphllm.com/codex-pricing)
  - Press reported the Pro 200 compensation grant as 62,500 credits ≈ $2,500, which also implies $0.04/credit. — [Valletta](https://vallettasoftware.com/blog/post/chatgpt-pro-max); [The Next Web](https://thenextweb.com/news/openai-devday-pro-200-usage-cut-pro-500-plan)
- **Usage-limit reset credits.** These exist: the app shows "their type and expiration, and let[s] you choose which credit to redeem". — [Codex changelog](https://learn.chatgpt.com/docs/changelog)
- **Referrals.** Invitation rewards exist for personal plans; terms are shown in the app. — [OpenAI Codex pricing](https://learn.chatgpt.com/docs/pricing)

### Inferences
- At $0.04/credit, every rate-card entry equals the API list price. For example, GPT-6.1 Sol at 50/2.5/250 credits = $2.00/$0.10/$10.00 per 1M, and Astra at 250/25/1,250 = $10/$1/$50.
- So buying credits is roughly as expensive as paying API rates. The subscription's included allowance is where the value is.
- A "typical" GPT-5.6 Sol task (2–15 credits) would cost about $0.08–$0.60.

### Gaps
- I could not open the official credits article (help.openai.com/en/articles/12642688, HTTP 403). The exact current price per credit pack, minimum purchase and expiry are not directly confirmed; $0.04/credit is corroborated only indirectly.

## 5. OpenAI API per-token prices for the main Codex models, and how prompt caching works

### Takeaway
- Standard API list prices per 1M tokens (input / cached input / output):

  | Model | Input | Cached input | Output |
  |---|---|---|---|
  | gpt-6-astra | $10 | $1 | $50 |
  | gpt-6.1-sol | $2 | $0.10 | $10 |
  | gpt-6.1-terra | $1 | $0.05 | $5 |
  | gpt-6-luna | $0.10 | $0.01 | $0.50 |

- For GPT-5.6 and later, the API now also charges **cache writes**: 1.25x input for the default 30-minute lifetime, 2.5x for an optional 12-hour lifetime. Cache reads are discounted up to 95%.
- Subscription credit billing has no cache-write charge.

### Cited Findings
- **Standard rates, per 1M tokens** (input / output / cached input / 30-min cache write / 12-h cache write). — [OpenAI API pricing (platform.openai.com/docs/pricing redirects here)](https://developers.openai.com/api/docs/pricing)

  | Model | Input | Output | Cached input | 30m write | 12h write |
  |---|---|---|---|---|---|
  | gpt-6-astra | $10.00 | $50.00 | $1.00 | $12.50 | $25.00 |
  | gpt-6.1-sol | $2.00 | $10.00 | $0.10 | $2.50 | $5.00 |
  | gpt-6.1-terra | $1.00 | $5.00 | $0.05 | $1.25 | $2.50 |
  | gpt-6-luna | $0.10 | $0.50 | $0.01 | $0.125 | $0.25 |

- **Other speed tiers and models.** — [OpenAI API pricing](https://developers.openai.com/api/docs/pricing)
  - Batch/Flex is half price. Fast is 2x (e.g., gpt-6.1-sol $4/$20).
  - Ultrafast: gpt-6-astra $60/$300, gpt-6.1-sol $12/$60.
  - gpt-5.6-sol is $4/$20/$0.40. Its promotional pricing runs "at least through November 21, 2026".
  - Legacy gpt-5.3-codex is $1.75 input / $14 output / $0.175 cached.
  - "Priority processing was renamed Fast mode on July 30, 2026." Regional and data-residency endpoints carry a 10% uplift for models released on or after 2026-03-05.
- **Prompt caching guide.** — [OpenAI prompt caching guide](https://developers.openai.com/api/docs/guides/prompt-caching)
  - Cached input is "discounted up to 95%".
  - "For GPT-5.6 and later, cache writes cost 1.25× the standard, uncached input-token rate for the default 30-minute lifetime."
  - The Responses API offers `prompt_cache_options.ttl` = "30m" (default, 1.25x write) or "12h" (2.5x write). These "are total write rates, not surcharges". OpenAI "may retain entries longer than the requested minimum".
  - Up to four cache writes per request, with explicit breakpoints available.
- **Caching under subscription or credits.** "Codex credit billing has no separate cache-write charge." — [OpenAI Codex pricing](https://learn.chatgpt.com/docs/pricing)
  - "GPT-6.1 Sol usage drawn from your subscription has no long-context surcharge in Codex or ChatGPT Work." — [OpenAI Codex pricing](https://learn.chatgpt.com/docs/pricing)
- **Caching in practice.** Measured Codex usage was 96% cached input. Long-context requests (>272K input tokens) were priced at OpenAI's long-context rates. — [MulletIQ](https://mulletiq.com/blog/claude-max-5x-vs-codex-pro)
- **OpenAI's own caution.** "API token prices are separate from subscription usage; don't use them to estimate included tasks." — [OpenAI Codex pricing](https://learn.chatgpt.com/docs/pricing)

### Inferences
- **Agentic coding is dominated by cached input** (≈96%). So for API-key users the effective cost is driven by cached-read rates ($0.10/M for 6.1 Sol, $1/M for Astra), output tokens, and the new cache-write charges.
- **Cache writes may matter for API-key users.** At 1.25x input on every newly written prefix, API-key Codex sessions could cost more than under pre-GPT-5.6 caching. Subscription/credit usage avoids this.

### Gaps
- Long-context API prices (the "Long" toggle on the pricing page) were not extracted.
- I found no documentation of the cache TTL that Codex itself requests (30m vs 12h) when using ChatGPT sign-in or an API key.

## 6. API-equivalent value delivered by maxed-out Plus, Pro 100 and Pro 200 (community measurements, implied multipliers)

### Takeaway
- The best available independent measurement is MulletIQ, October 2026, before the Oct 29 doubling. **Plus ≈ $63/week (~$270/month, ~14x its $20 price)** and **Pro 100 ≈ $325/week (~$1,400/month, ~14x its $100 price)** of API-list-priced usage.
- If the 2026-10-29 doubling applies fully, Pro 100 would now be ~$650/week (~$2,800/month, ~28x). This is unmeasured.
- No credible measurement exists for Pro 200 or Pro 500.

### Cited Findings
- **MulletIQ, 2026-10-21.** Single user and account. Codex usage was mostly GPT-6 Astra. — [MulletIQ](https://mulletiq.com/blog/claude-max-5x-vs-codex-pro)
  - **Pro 100 weekly limit ≈ $325** API-equivalent ($314–$335), measured 2026-09-07 to 2026-10-07. That is "$14" of usage per $1 of plan price per month.
  - A full Pro week was **454M input / 2.9M output tokens**, 96% cached.
  - **Plus weekly ≈ $63** ($60–$73), measured 2026-10-08 to 2026-10-21.
  - The Pro meter fit 1% per $3.25 of API-list usage (R² = 0.99).
- **MulletIQ's Claude comparison** (the Anthropic side is outside this researcher's scope; flag for cross-checking): — [MulletIQ](https://mulletiq.com/blog/claude-max-5x-vs-codex-pro)
  - Claude Max 5x measured ≈ $1,248/week (2026-09-22 to 09-27), ≈ 3.8x Codex Pro 100 at the time.
  - Caveats: one account per vendor, different models (Opus 5.5 vs GPT-6 Astra), and a 6-day Claude sample.
- **Planned Pro 200 cut.** Sottiaux said the 10x Pro 200 (since cancelled) "will net out at half the dollar in API spend compared to the old Pro $200 plan." — [Valletta](https://vallettasoftware.com/blog/post/chatgpt-pro-max)
- **Historical token counts (GPT-5.5, June 2026).** — [GitHub openai/codex discussion #26975](https://github.com/openai/codex/discussions/26975)
  - One user estimated the $100 plan at ~300–500M tokens/week after June 4 (≈1.0–1.1B before) and the $20 plan at ~245M/week on GPT-5.5 Xhigh.
  - A commenter estimated the $200 plan at ~1.8B tokens/week ≈ "$40-80/week" at API rates.
  - These are unverified and use inconsistent methods; the last figure looks implausibly low against MulletIQ's careful data.

### Inferences
Implied multipliers (API-list $ per month ÷ subscription price, using 4.33 weeks/month):

| Plan | Weekly | Monthly | Multiplier | Basis |
|---|---|---|---|---|
| Plus | ~$63 | ~$273 | ~13.7x | Measured, Oct 2026 |
| Pro 100, before Oct 29 | ~$325 | ~$1,408 | ~14.1x | Measured |
| Pro 100, after doubling | ~$650 | ~$2,816 | ~28x | Unmeasured extrapolation |
| Pro 200 | — | — | — | No data. At nominal 20x Plus it would be ~4x Plus 's ~$273, ≈ $1,100/month at Plus's per-unit rate; a guess resting on two unverified ratios |

- Value per dollar depends heavily on model choice:
  - On the Pro 100 meter, Luna bought ~$9.58 of API-list value per 1%, Astra ~$3.65, Sol ~$2.48.
  - "API-equivalent $" therefore swings with model mix. Sol-heavy use shows lower $ value but more tokens and messages, because Sol is 5x cheaper than Astra at API rates.
  - The comparison basis must be stated: API-list pricing, mostly Astra, ~96% cache hits.
- **Head-to-head with Claude** (other researchers' domain). Pre-Oct 29, Claude Max 5x delivered ~3.8x Codex Pro 100's API-equivalent usage. After the doubling, the gap would narrow to roughly 1.9x if the doubling is fully realised. That is inference, and it depends on Anthropic-side numbers others must verify.

### Gaps
- No measurement after 2026-10-29.
- No measurements for Pro 200 or Pro 500.
- No Reddit or Hacker News datasets found in this pass.
- I did not verify whether ccusage, tokscale or CodexBar have published aggregate Codex API-equivalent dashboards. MulletIQ used CodexBar for meter readings; a GitHub user cited ccusage for dollar estimates.

## 7. Do OpenAI's terms allow one person to run multiple ChatGPT accounts to pool Codex limits?

### Takeaway
I found no clause that explicitly bans one person holding several personal accounts. But OpenAI's Terms of Use prohibit circumventing "any rate limits or restrictions", and stacking accounts specifically to evade Codex usage limits falls squarely under that. OpenAI's sanctioned ways to get more capacity are buying credits, upgrading tier, or using an API key.

### Cited Findings
- **Consumer Terms of Use.** Users may not "Interfere with or disrupt our Services, including circumvent any rate limits or restrictions or bypass any protective measures or safety mitigations we put on our Services." — [ConductAtlas transcription of OpenAI Terms of Use (search-result snippet; another ConductAtlas URL returned 404 to me)](https://conductatlas.com/platform/openai/openai-terms-of-use/provision/CA-P-078688/no-interference-with-or-disruption-of-services/)
- **Business Terms.** Separately bar users who "violate or circumvent Usage Limits or otherwise configure the Services to avoid Usage Limits." — [ConductAtlas, OpenAI Business Terms (search-result snippet)](https://conductatlas.com/platform/openai/openai-business-terms/provision/CA-P-059977/prohibition-on-circumventing-usage-limits/)
- **Community forum** (not official statements): — [OpenAI Developer Community thread](https://community.openai.com/t/you-can-use-codex-continuously-without-becoming-a-member/1378147); [2023 thread on two Plus accounts](https://community.openai.com/t/using-2-accounts-to-subscribe-to-chatgpt-plus-to-get-twice-the-limit/136434)
  - Users argue that opening extra accounts to keep using Codex breaches the circumvention clause and risks bans.
  - A 2023 user reported a second Plus account was not a successful workaround.
- **Sanctioned paths.** OpenAI points users to credits and API keys: "ChatGPT Plus and Pro users who reach their usage limit can purchase additional credits…"; "All users may also run extra local chats using an API key." — [OpenAI Codex pricing](https://learn.chatgpt.com/docs/pricing)
- **Automation tokens.** Codex access tokens for trusted automation are Business/Enterprise only, not Plus or Pro. — [OpenAI Codex pricing](https://learn.chatgpt.com/docs/pricing)

### Inferences
- Running several Plus accounts and rotating them when one hits its limit carries account-ban risk similar to the equivalent Anthropic scenario.
- Doubling Pro 100 makes this less attractive anyway: five Plus accounts ($100) give ~5x Plus weekly, each still with 5-hour windows. Pro 100 now gives nominally ~10x Plus (measured ~3.7–5.1x before the doubling), with no 5-hour window and no ToS risk.

### Gaps
- I could not load OpenAI's own Terms of Use page (openai.com returned 403). The wording comes from a third-party transcription, and its version date is not confirmed.
- No OpenAI enforcement statements specific to multi-account Codex use were found.

## 8. Migration friction for a Claude Code user moving to Codex CLI

### Takeaway
Codex now has close equivalents for nearly every Claude Code customization layer:
- AGENTS.md hierarchy (and it can read `CLAUDE.md` as a fallback filename)
- SKILL.md skills on the open agent-skills standard
- Subagents and custom agents
- MCP (stdio and Streamable HTTP with OAuth)
- Lifecycle hooks with near-identical event names
- Session resume and fork, `/compact`, plan mode, `/review`
- Opt-in local memories

The friction is mostly re-plumbing: different file locations, TOML config instead of JSON, a different permission/sandbox model, and loss of Claude-specific plugins, commands and prompt tuning.

### Cited Findings
- **AGENTS.md (CLAUDE.md equivalent).** — [AGENTS.md doc](https://learn.chatgpt.com/docs/agent-configuration/agents-md); [Commands reference](https://learn.chatgpt.com/docs/developer-commands)
  - Global file: `~/.codex/AGENTS.md`, or `AGENTS.override.md`.
  - Project discovery walks from the repo root down to the cwd and checks `AGENTS.override.md`, then `AGENTS.md`, then "any fallback names in `project_doc_fallback_filenames`". At most one file per directory is used.
  - Combined size is capped at `project_doc_max_bytes` ("32 KiB by default").
  - Fallback example: `project_doc_fallback_filenames = ["TEAM_GUIDE.md", ".agents.md"]` in `~/.codex/config.toml`.
  - `/init` generates an AGENTS.md scaffold.
- **Skills.** Codex uses "the open agent skills standard" (agentskills.io): a directory with `SKILL.md` that must include `name` and `description`. — [Build skills doc](https://learn.chatgpt.com/docs/build-skills); [Docs index (llms.txt)](https://learn.chatgpt.com/llms.txt)
  - Locations: `$CWD/.agents/skills` up to `$REPO_ROOT/.agents/skills`, `$HOME/.agents/skills`, `/etc/codex/skills`. Symlinked skill folders are followed.
  - Custom prompts are "Deprecated. Use skills for reusable prompts."
- **Subagents.** "Current Codex releases enable subagent workflows by default." — [Subagents doc](https://learn.chatgpt.com/docs/agent-configuration/subagents); [Commands reference](https://learn.chatgpt.com/docs/developer-commands)
  - You can "define custom agents with different model" and reasoning settings, plus `[agents]` defaults in `config.toml`.
  - `/agents` and `/subagents` commands exist. Subagents "consume more tokens".
- **MCP.** — [MCP doc](https://learn.chatgpt.com/docs/extend/mcp); [OpenAI Codex pricing](https://learn.chatgpt.com/docs/pricing)
  - Supports STDIO and Streamable HTTP servers with OAuth.
  - Config lives in `~/.codex/config.toml` or project `.codex/config.toml` ("trusted projects only"). CLI: `codex mcp add <name> -- <command>`.
  - OpenAI advises limiting MCP servers because each one "adds more context… and uses more of your limit".
- **Hooks.** — [Hooks doc](https://learn.chatgpt.com/docs/hooks)
  - Events: `PreToolUse`, `PermissionRequest`, `PostToolUse`, `PreCompact`, `PostCompact`, `UserPromptSubmit`, `SubagentStop`, `Stop`, `SessionStart`, `SubagentStart`, `SessionEnd`.
  - Defined in `hooks.json` or inline `[hooks]` tables in `config.toml`, at `~/.codex/` or `<repo>/.codex/`. Hooks must be reviewed and trusted. MCP-tool hooks are supported.
- **Session resume and related commands.** — [Commands reference](https://learn.chatgpt.com/docs/developer-commands)
  - `codex resume` (`--last`, `--all`), `/resume`, `codex fork` / `/fork`, and `codex exec resume` / `codex exec fork` (with `--worktree`).
  - Esc-Esc edits the previous message and forks.
  - Also `/compact`, `/recap`, `/plan`, `/goal`, `/review`, `/status` (shows limits), `/model`, `/fast`, `/permissions`.
- **Memories.** "Local Codex memories are off by default"; enable them in Settings > Personalization, and use `/memories` per chat. — [Memories doc](https://learn.chatgpt.com/docs/customization/memories); [OpenAI Codex pricing](https://learn.chatgpt.com/docs/pricing)
  - OpenAI advises keeping required guidance in AGENTS.md rather than memories.
  - The pricing matrix marks Memories "limited" on all plans and Computer History as Pro-only.
- **Other surfaces.** Plugins, scheduled tasks, worktrees, SSH remote, cloud tasks, GitHub `@codex` review and Slack/Linear integrations are available on Plus and Pro. — [OpenAI Codex pricing](https://learn.chatgpt.com/docs/pricing); [Docs index](https://learn.chatgpt.com/llms.txt)
  - "Rules" control which commands run outside the sandbox, and beta permission profiles cover filesystem and network access.
  - The old "Codex MCP server" was removed in favour of an app server.
- **Upgrade friction from model churn.** Saved configs, custom agents and scripts referencing retired models (`gpt-5.5`, `gpt-5.4`, `gpt-5.3-codex-spark`) had to be updated. — [Codex Models doc](https://learn.chatgpt.com/docs/models)

### Inferences
- **Instructions:**
  - Adding `"CLAUDE.md"` to `project_doc_fallback_filenames` should let Codex read existing CLAUDE.md files unchanged; the docs show the mechanism with other filenames.
  - Claude-specific syntax such as `@path` imports in CLAUDE.md is not documented for AGENTS.md and likely won't expand.
  - Raise `project_doc_max_bytes` if the combined CLAUDE.md files exceed 32 KiB.
- **Skills:** SKILL.md folders from `.claude/skills` should mostly port by moving or symlinking to `.agents/skills`, since both follow the agent-skills format. Claude Code custom slash commands (`.claude/commands`) need converting into skills.
- **Hooks:** hook scripts with the same event names can likely be reused, but `settings.json` hook config must be rewritten as `hooks.json`/TOML. Payload field names may differ and should be tested.
- **MCP:** Claude Code's `.mcp.json` / `claude mcp add` entries must be re-added as TOML `[mcp_servers]` or via `codex mcp add`.
- **What won't carry over:**
  - Claude Code plugins and marketplaces
  - Output styles, status-line scripts and permission allow/deny lists (Codex uses sandbox + rules + approval modes instead)
  - Prompts tuned to Claude models' habits
  - Session history (Claude transcripts can't be resumed in Codex)
  - Anthropic-specific integrations
- **Bridge option:** the user can keep both tools (Claude Pro for Claude Code plus Codex on a separate plan) and share one instruction file via the fallback mechanism.

### Gaps
- I did not verify hook input/output JSON schemas against Claude Code's, or whether Codex expands `@file` imports in instruction files.
- Search results surfaced a GitHub repo, `openai/codex-plugin-cc`, apparently a Codex plugin for Claude Code. I did not fetch it, so its function is unverified.
- No first-hand migration write-ups (blogs/HN) were reviewed in this pass.
