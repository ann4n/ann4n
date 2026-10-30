# Claude subscription plans for Claude Code: usage limits, model access and API-equivalent value (as of 2026-10-30)

Scope note: all official pages below were fetched on 2026-10-30. Help Center articles show only relative dates ("Updated this week", "over 2 weeks ago"). Figures from 2025 are labelled HISTORICAL. Community figures are labelled as such and are not Anthropic statements.

## Q1. What are the current Claude Code usage limits for Pro, Max 5x and Max 20x (5-hour session and weekly), and how have they changed since Aug 2025?

### Takeaway
Anthropic no longer publishes hours, messages or prompts for any plan. It states only per-5-hour-session multiples: Pro is at least 5x Free, Max 5x is 5x Pro, Max 20x is 20x Pro, Team Standard is 1.25x Pro and Team Premium is 6.25x Pro. Each paid plan also has an all-models weekly limit whose size is not published, and Max has a Fable sub-cap of 50% of the weekly limit. The only numeric weekly figures Anthropic ever gave date from Aug 2025 (Pro 40–80 Sonnet-4 hours per week; Max 5x 140–280; Max 20x 240–480) and are now historical. Since then limits have been raised several times: Nov 2025 (Opus 4.5), May 2026 (5-hour limits doubled), Sept 2026 (a reported permanent +25% to weekly limits) and 22 Sept 2026 (Opus 5.5 raised 5-hour limits).

### Cited Findings
**Current official structure**
- "Both Pro and Max plans have a five-hour session limit and a weekly limit." These limits are shared between Claude (chat/Cowork) and Claude Code. Max plans also have "a separate weekly limit for Fable". The article gives no numeric caps and shows "Updated this week". — [Help Center: Use Claude Code with your Pro or Max plan](https://support.claude.com/en/articles/11145838-use-claude-code-with-your-pro-or-max-plan)
- Max 5x gets "five times the Pro plan's per-session usage allowance" and Max 20x gets "20 times the Pro plan's per-session usage allowance". Sessions reset every five hours. The Max weekly limit "applies across all models" and resets at a fixed time assigned to each account. The article states no weekly multiplier versus Pro. It also says Anthropic "may limit your usage in other ways, such as weekly and monthly caps or model and feature usage, at our discretion". — [Help Center: What is the Max plan?](https://support.claude.com/en/articles/11049741-what-is-the-max-plan)
- The pricing page says all plans have limits on a rolling five-hour window and paid plans add weekly limits. Pro is "at least 5x more usage per 5-hour session than Free", and Max gives "5x or 20x more usage than Pro". — [claude.com/pricing](https://claude.com/pricing)
- A third-party measurement site confirms that "Anthropic only states 5-hour multiples (5 and 20 times Pro)" and that weekly limit sizes are not published. — [Wokey, data updated 2026-10-26](https://wokey.ai/claude-usage-limits)
- Wrap-up allowance: if a five-hour limit is hit mid-response, Claude Code shows "Usage limit reached · wrapping up" and finishes the response. Pro gets this up to once per weekly usage period. Max gets it every time a five-hour limit is reached, within the weekly limit. The feature is rolling out gradually. — [Help Center: Use Claude Code with your Pro or Max plan](https://support.claude.com/en/articles/11145838-use-claude-code-with-your-pro-or-max-plan)

**History of changes**
- HISTORICAL, announced 2025-07-28 and effective 2025-08-28: weekly limits were introduced. Anthropic said "most Pro users can expect 40 to 80 hours of Sonnet 4 through Claude Code within their weekly rate limits". The $100 Max plan got 140–280 hours of Sonnet 4 plus 15–35 hours of Opus 4. The $200 Max plan got 240–480 hours of Sonnet 4 plus 24–40 hours of Opus 4. Anthropic estimated the limits would affect "less than 5% of subscribers", and Max subscribers could buy more usage at API rates. — [TechCrunch, 2025-07-28](https://techcrunch.com/2025/07/28/anthropic-unveils-new-rate-limits-to-curb-claude-code-power-users/)
- HISTORICAL, 2025-11-24 (Opus 4.5 launch): "For Claude and Claude Code users with access to Opus 4.5, we've removed Opus-specific caps. For Max and Team Premium users, we've increased overall usage limits", giving "roughly the same number of Opus tokens as you previously had with Sonnet". Pro was not mentioned. — [Anthropic: Claude Opus 4.5](https://www.anthropic.com/news/claude-opus-4-5)
- 2026-03-13 to 03-28 (reported): an off-peak promotion doubled 5-hour limits outside weekday peak hours (8 AM–2 PM ET) and all weekend, for Free, Pro, Max and Team. The bonus did not count against weekly caps, and the promotion ended on schedule. The article names no Anthropic source. — [explainx.ai timeline](https://explainx.ai/blog/claude-usage-limits-2026-timeline-explained)
- 2026-05-06 (explainx citing an Anthropic announcement): Claude Code 5-hour limits were "permanently doubled" for Pro, Max, Team and seat-based Enterprise. The peak-hour reduction was removed for Pro and Max, and weekly caps were unchanged. Anthropic tied this to "more than 300 megawatts of new capacity" from the SpaceX Colossus 1 deal. — [explainx.ai timeline](https://explainx.ai/blog/claude-usage-limits-2026-timeline-explained)
- Summer 2026 promotion: Claude Code weekly limits were raised 50% temporarily. The promotion was extended on 2026-07-18 through Aug 19 and later to mid-September, and it ended on 2026-09-13. — [Wokey](https://wokey.ai/claude-usage-limits); [explainx.ai timeline](https://explainx.ai/blog/claude-usage-limits-2026-timeline-explained)
- 2026-08-30 (ClaudeDevs announcement, as reported): the +50% promotion would end on Sept 14 and be replaced by a permanent +25% raise to weekly Claude Code limits. explainx calculates this as a net cut of about 17% against the promotion level. — [explainx.ai timeline](https://explainx.ai/blog/claude-usage-limits-2026-timeline-explained)
  - Conflict: Techsy and Promoclock reportedly say weekly caps did not change in the 2026 events. I saw this only in a search-result summary and did not verify it. — [Techsy](https://techsy.io/en/blog/claude-2x-usage-limits-explained); [Promoclock](https://promoclock.co/en/blog/claude-usage-limits/)
- 2026-09-03 (reported, not clearly attributed to Anthropic): a `/limit-reset` command resets the Claude Code session limit once a week. The weekly cap still applies. — [explainx.ai timeline](https://explainx.ai/blog/claude-usage-limits-2026-timeline-explained)
- 2026-09-22 (official, Opus 5.5 launch): "we're increasing five-hour usage limits on Pro, Max, Team, and seat-based Enterprise plans". No new numbers were given. Subscribers also got "a rate limit reset, which you can now save and use whenever you choose". Weekly limits were not mentioned. — [Anthropic: Claude Opus 5.5](https://www.anthropic.com/news/claude-opus-5-5)
- SemiAnalysis measured the Opus 5.5 change at "roughly 20% more tokens on Max plans and about 50% more on Pro". Fable 5.1 limits were not raised. — [explainx.ai summary of SemiAnalysis, Oct 2026](https://explainx.ai/blog/semianalysis-claude-vs-chatgpt-subscription-api-value-limit-testing-2026); same figures in [Wokey](https://wokey.ai/claude-usage-limits)
- October 2026 measurements show no cut. Wokey's weekly API-equivalent values were:
  - Pro: $125 (09-24), $145 (10-01), $162 (10-08), $170 (10-12), $170 (10-19), $157 (10-26).
  - Max 20x: $1,404, $1,510, $1,727, $1,751, $1,643, $1,602 on the same dates.
  - Wokey attributes the latest dip to sampling noise and model mix. — [Wokey](https://wokey.ai/claude-usage-limits)

### Inferences
- The Aug 2025 hour ranges should not be quoted as current. Since then Opus-specific caps were removed (Nov 2025), 5-hour limits were doubled (May 2026), weekly limits got a reported +25% (Sept 2026) and 5-hour limits were raised again (Sept 22, 2026). Today's Pro allowance in Claude Code is therefore very likely well above 40–80 Sonnet-4-hours a week, but there is no official number to cite. The model generation and tokenizer have also changed (see Q6), so "hours" are not comparable across years anyway.
- Wokey's data implies that Pro's weekly cap equals about 15 full 5-hour windows ($11 per window against $157 per week). A Pro user who "keeps running out" is usually hitting the 5-hour window first, unless they max out about 2–3 windows a day all week.

### Gaps
- No official current hours, messages or prompts exist for any plan. Anthropic publishes only multiples.
- I could not read Anthropic's own pages for the May 6, 2026 doubling or the Aug 30, 2026 "+25% permanent" announcement. Both come via explainx.ai, and the +25% is disputed by other blogs.
- Whether Max currently has a Sonnet-only weekly cap is unclear. One third-party source in search results claimed it does, while official pages mention only the Fable cap.

## Q2. Is Max 5x really 5x Pro, what is the real weekly ratio, and is 5 x Pro >, = or < 1 x Max 5x?

### Takeaway
Officially, Max 5x is 5x Pro per 5-hour session, which is exactly what five Pro accounts give in aggregate per window. The weekly ratio is not published. The best October 2026 measurement (SemiAnalysis, Opus 5.5) puts Max 5x at about 4.9x Pro in monthly, weekly-capped value. That makes **5 x Pro roughly equal to 1 x Max 5x in raw capacity** (5 x Pro about 3% higher, within measurement error). Under Anthropic's HISTORICAL Aug 2025 hour ranges, Max 5x was only 3.5x Pro weekly, so 5 x Pro would have been about 1.43x Max 5x. That gap is the basis of a pending class action. The Sept 2026 increase, which favoured Pro (+50% against +20% on Max), has narrowed it.

### Cited Findings
- Official: Max 5x gets "five times the Pro plan's per-session usage allowance". No weekly ratio is given. — [Help Center: What is the Max plan?](https://support.claude.com/en/articles/11049741-what-is-the-max-plan)
- SemiAnalysis, October 2026, Opus 5.5 monthly API-equivalent value capped by weekly limits:
  - Pro: $1,178 (58.9x its price).
  - Max 5x: $5,725 (57.3x).
  - Max 20x: $11,726 (58.6x, agentic workload).
  - explainx's own arithmetic: Max 5x / Pro ≈ 4.9x (fee ratio 5x) and Max 20x / Max 5x ≈ 2.0x (fee ratio 2x). Multipliers are "nearly flat across tiers (57x to 59x), so value scales roughly with price". Results are tracked within ±5%. — [explainx.ai summary of SemiAnalysis, Oct 2026](https://explainx.ai/blog/semianalysis-claude-vs-chatgpt-subscription-api-value-limit-testing-2026)
- Wokey, week of 2026-10-19 to 10-26:
  - Max 20x is about 10x Pro weekly ("10× Claude Pro, not 20×") but about 24x Pro per 5-hour window.
  - The Max 20x weekly cap binds after about 6.1 full windows; Pro's after about 15.
  - Max 5x / Pro is "not computable", because only 2 Max 5x accounts were measured (Wokey publishes a plan at 5 or more). — [Wokey](https://wokey.ai/claude-usage-limits)
- HISTORICAL Aug 2025 published ranges: Pro 40–80, Max $100 140–280 and Max $200 240–480 Sonnet-4 hours a week. — [TechCrunch, 2025-07-28](https://techcrunch.com/2025/07/28/anthropic-unveils-new-rate-limits-to-curb-claude-code-power-users/)
- *Kahn v. Anthropic, PBC* (proposed class action filed 2026-06-14 in N.D. Cal.; first amended complaint 2026-07-20):
  - It alleges Max 20x delivers about 6–8x Pro and Max 5x about 3.5x Pro, not the advertised 20x and 5x.
  - It seeks a class of everyone who bought Max 5x or 20x since the tiers launched in April 2025.
  - Anthropic reportedly declined to comment. These are allegations, not findings.
  - I read only search-result summaries, not the complaint itself. — [ChatGPT Is Eating the World, 2026-06-15](https://chatgptiseatingtheworld.com/2026/06/15/karl-kahn-sues-anthropic-in-class-action-for-alleged-misrepresentation-and-false-advertising-in-promoting-max-20x-plans-for-claude-allegedly-as-delivering-twenty-times-greater-than-usage-lim/); [explainx.ai lawsuit explainer ("20x Is Only 1.7× Weekly")](https://www.explainx.ai/blog/anthropic-claude-max-lawsuit-usage-limits-2026)
- The Opus 5.5 limit increase (2026-09-22) gave roughly +20% tokens on Max and +50% on Pro, per SemiAnalysis. — [explainx.ai summary of SemiAnalysis](https://explainx.ai/blog/semianalysis-claude-vs-chatgpt-subscription-api-value-limit-testing-2026)

### Inferences
- **Aug 2025 ratios** (my arithmetic on the published hours): Max 5x / Pro = 140/40 = 280/80 = **3.5x**; Max 20x / Pro = **6x**; Max 20x / Max 5x ≈ **1.7x**.
- **October 2026 ratios**:
  - SemiAnalysis: Max 5x / Pro ≈ **4.9x** and Max 20x / Pro ≈ **10x**.
  - Wokey independently gets Max 20x / Pro ≈ 10.2x ($1,602 / $157). That is consistent with SemiAnalysis's 9.95x.
- **5 x Pro against 1 x Max 5x on raw Opus 5.5 capacity**, using SemiAnalysis Oct 2026 figures:
  - 5 × $1,178 = $5,890 of API value a month, against $5,725 for Max 5x.
  - That makes 5 x Pro ≈ 1.03x Max 5x, which is effectively equal given ±5% measurement error.
  - Cross-check: scaling Wokey's Max 20x ($6,944 a month) by SemiAnalysis's Max 5x / Max 20x ratio (0.49) gives Max 5x ≈ $3,390 a month. Five Wokey Pro accounts give 5 × $681 ≈ $3,405, which is again about equal.
- **Per 5-hour window**: five Pro windows equal Max 5x by Anthropic's own definition. Wokey found Max 20x exceeds its per-window multiple (24x against 20x), but no per-window measurement exists for Max 5x.
- **The non-linearity has moved.** In 2025 Max 5x was the tier that under-delivered against "5x" (3.5x weekly). By Oct 2026 Max 5x weekly is close to 5x, while Max 20x is only about 10x Pro weekly (about 2x Max 5x for 2x the price).
- **Price**: Pro billed annually is $17 a month ($200 a year), so five annual Pro subscriptions cost $1,000 a year (≈$83 a month). Max 5x is monthly-only at $100 ($1,200 a year). This is arithmetic only; multi-account policy and mechanics are out of scope.

### Gaps
- There is no direct measurement of Max 5x per-window or weekly limits from Wokey (n=2), and no 2026 ccusage-based Max 5x measurement turned up.
- The full SemiAnalysis report is paywalled. I relied on explainx.ai's reproduction, which says some sections are subscriber-only.
- The Kahn complaint text was not read directly.

## Q3. Which models can each plan use in Claude Code, do top models burn limits faster, and are there separate caps?

### Takeaway
Opus 5.5, Sonnet 5.5 and Haiku 5.5 are included on both Pro and Max, and Opus 5.5 is the Claude Code default model on Pro, Max, Team and Enterprise. **Fable 5.1 (and Fable 5) is not included in Pro limits.** On Pro it runs only on paid usage credits at API rates. On Max 5x and 20x (and Team Premium) it is included, but only up to 50% of the weekly limit. Anthropic says Fable "uses [limits] faster than other models" without giving a factor. SemiAnalysis's numbers imply Fable 5.1 burns about 4.5x the limit per token of Opus 5.5, and Sonnet 5.5 about 0.5–0.65x.

### Cited Findings
- Pricing page model table:
  - Fable: Free "No"; Pro "Usage credits"; Max 5x/20x "50% of weekly limits"; Team "Premium seats: 50% of weekly limits"; Enterprise yes.
  - Opus: yes on Pro, Max, Team and Enterprise; not on Free.
  - Sonnet and Haiku: all plans, including Free.
  - Latest models listed: Opus 5.5, Sonnet 5.5, Haiku 5.5. Legacy models include Fable 5, Opus 5, Opus 4.8, Opus 4.7, Opus 4.6, Sonnet 4.6, Sonnet 4.5 and Opus 4.5. — [claude.com/pricing](https://claude.com/pricing)
- Fable help article (dated 2026-09-02):
  - Pro: Fable 5 and 5.1 are not included in usage limits and run on usage credits from the start.
  - Max (5x and 20x treated the same), Team premium seats and seat-based Enterprise premium seats: "up to 50% of weekly usage limits" can go to Fable at no extra cost. "Fable draws from the regular weekly limits and uses them faster than other models." This is not a 50% increase: "you can never use more than your weekly limit". Once the Fable cap is reached, you continue on usage credits or switch model.
  - Team standard and Enterprise standard seats: Fable runs on usage credits.
  - Claude Code version requirements: Fable 5 needs 2.1.170+; Fable 5.1 needs 2.1.255+.
  - The 50% Fable 5 promotion ended 2026-07-19, 11:59:59 PM PT, and Fable 5.1 was never part of it. Eligible Pro and Team standard users got a one-time credit when Fable 5 moved to usage credits in July 2026; there is no equivalent for Fable 5.1. — [Help Center: Claude Fable models on your plan](https://support.claude.com/en/articles/15424964-claude-fable-models-on-your-plan)
- Claude Code model configuration docs:
  - Default model: `default` resolves to "Opus 5.5" for Pro, Max, Team, Enterprise and the Anthropic API.
  - Before v2.1.280, Pro and Team Standard defaulted to Sonnet 5, while Max, Team Premium, Enterprise and the API defaulted to Opus 5.
  - "Neither Fable model is the account-type default on any plan or provider."
  - Aliases: `fable`, `opus`, `sonnet`, `haiku`, `best` (resolves to Fable where available, otherwise Opus) and `opusplan` (Opus in plan mode, Sonnet for execution).
  - When Fable bills to usage credits, `/model` shows "Requires usage credits" and interactive sessions ask for consent. In `-p` and Agent SDK runs, "Claude Code bills it without asking".
  - Rate-limit errors "never trigger a switch" to another model.
  - Opus 4.6 with 1M context is included on Max, Team and Enterprise but "requires usage credits" on Pro (legacy model). — [Claude Code Docs: Model configuration](https://code.claude.com/docs/en/model-config)
- Measured burn effects (community):
  - Opus 5.5 uses about 0.6x the limit per token of other Opus and Fable models.
  - Output costs about 4–7x input in limit usage. — [Wokey](https://wokey.ai/claude-usage-limits)
- SemiAnalysis monthly value by model (Oct 2026):
  - Max 20x, agentic workload: Opus 5.5 $11,726; Sonnet 5.5 $12,529; Fable 5.1 $2,485.
  - Max 20x, chat workload: $9,200; $7,009; $2,480.
  - Max 5x, Fable 5.1: $1,273. Pro, Fable 5.1: "Not on Claude Pro".
  - Fable 5.1 "can be used for only up to half of the plan's usage limit". — [explainx.ai summary of SemiAnalysis](https://explainx.ai/blog/semianalysis-claude-vs-chatgpt-subscription-api-value-limit-testing-2026)

### Inferences
- **Derived burn rates.** This is my own arithmetic on SemiAnalysis figures and list prices with 5-minute cache writes.
  - On SemiAnalysis's agentic mix (0.4% fresh input, 96.6% cache reads, 2.6% cache writes, 0.3% output), blended list prices are about $0.757 per million tokens for Fable 5.1, $0.399 for Opus 5.5 and $0.200 for Sonnet 5.5.
  - Implied tokens per weekly-capped month on Max 20x: Opus 5.5 about 29B, Sonnet 5.5 about 63B, Fable 5.1 about 6.6B if it could use 100% of the limit.
  - **Per token, Fable 5.1 burns about 4.5x the limit of Opus 5.5, and Sonnet 5.5 about 0.47x.** On the chat mix the figures are about 4.5x and about 0.66x.
  - Treat these as rough. They conflict with Wokey's statement that implies a smaller gap (other Opus and Fable models about 1.7x Opus 5.5).
- On Max 5x, using Fable 5.1 up to its 50% cap gives about $1,273 of Fable value but consumes half the weekly limit. That leaves about $2,860 of Opus 5.5 capacity (half of $5,725). The upgrade's Fable benefit is real but bounded.
- With 5 x Pro, every Fable token is paid at full API rates ($10 input / $50 output per million tokens). Pro users who enable usage credits should note that `claude -p` and Agent SDK runs on Fable are billed without a prompt.

### Gaps
- Anthropic publishes no per-model burn multipliers.
- Haiku 5.5's burn rate is unmeasured.
- Whether a Sonnet-only weekly cap exists on Max today is unverified (see Q1).

## Q4. How does paid extra usage / overage work on Pro and Max?

### Takeaway
"Usage credits" (the 2025 name was "extra usage") are available on Pro, Max and Team. They are prepaid, **billed at standard API rates with no discount**, and apply to Claude Code. They are the only way to use Fable on Pro. Every overage dollar buys about $1 of API usage, against roughly $35–59 of API-equivalent usage per subscription dollar.

### Cited Findings
- Usage credits cover Pro, Max 5x and Max 20x:
  - Enable them at Settings > Usage on the web; mobile subscribers must enable them on the web.
  - Prepay with "Add funds", set a monthly spend limit or "Set to unlimited", and optionally turn on auto-reload below a threshold.
  - They are "billed at standard API rates", billed separately from the subscription, and "Price and plans are subject to change at Anthropic's discretion".
  - "Usage credits apply to both Claude conversations and Claude Code terminal usage."
  - "In most cases, usage credits do not expire." In Japan they expire after 6 months, from 2026-09-10.
  - On Max, once the weekly Fable limit is used up with credits enabled, requests switch to credits.
  - "Monthly API credits don't apply to usage credits in Claude, Claude Code, or Claude Cowork." — [Help Center: Manage usage credits for paid Claude plans](https://support.claude.com/en/articles/12429409-manage-usage-credits-for-paid-claude-plans)
- Alternative: switch Claude Code to a Claude Console account with purchased API credits, also at standard API rates. — [Help Center: Use Claude Code with your Pro or Max plan](https://support.claude.com/en/articles/11145838-use-claude-code-with-your-pro-or-max-plan)
- The pricing page FAQ says paid plans can turn on usage credits to keep working "at standard API rates" after hitting a limit. — [claude.com/pricing](https://claude.com/pricing)
- Team: admins can enable prepaid usage credits for all seat types and "Set spending caps at organization and individual user levels." — [Help Center: What is the Team plan?](https://support.claude.com/en/articles/9266767-what-is-the-team-plan)
- HISTORICAL (2025): Max subscribers could buy usage beyond the weekly limit at API rates. — [TechCrunch, 2025-07-28](https://techcrunch.com/2025/07/28/anthropic-unveils-new-rate-limits-to-curb-claude-code-power-users/)

### Inferences
- Overage is very poor value compared with subscription capacity. Pro plus $80 of usage credits gives $80 of API-rate usage. Upgrading to Max 5x for the same extra $80 adds about $4,550 a month of Opus 5.5 capacity ($5,725 − $1,178, SemiAnalysis).
- API prices for overage are listed in Q6. Opus 5.5 overage costs $4 input / $20 output per million tokens.

### Gaps
- No official statement on whether usage-credit billing in Claude Code applies cache-read pricing exactly as on the API. It is presumably "standard API rates" across all token categories, but this is not spelled out.

## Q5. Does anything match the phrase "$100 of tokens from Max"?

### Takeaway
Yes. Since about 7–8 Oct 2026, **Max 5x includes $100 a month of Claude Platform API credits** (Max 20x $200; Team up to $500 pooled). **They cannot be used in Claude Code** (terminal, IDE, desktop, web, GitHub Action) or for usage credits in the Claude apps. They cover the Claude API, Managed Agents, the Agent SDK and the Console playground, including `claude -p` or Agent SDK runs that use an API key from the linked Console organization. Anthropic's ClaudeDevs account says they also work in "third-party harnesses". Pro is not eligible, and the credits expire each billing cycle with no rollover.

### Cited Findings
- Official docs summary table:
  - Eligible plans: Max 5x, Max 20x and Team (including discounted Team).
  - Amounts: "$100 USD (Max 5x), $200 USD (Max 20x), up to $500 USD (Team, pooled)". Team is $20 per Standard seat and $100 per Premium seat, capped at $500.
  - Covers: "Claude API, Claude Managed Agents, the Claude Agent SDK, and playground". Doesn't cover: "Claude Code, extra usage in the Claude apps", or AWS, Bedrock, Google Cloud and Foundry.
  - Credits expire "End of each billing cycle ... with no rollover". New subscribers can claim after 7 days. "Free, Pro, and Enterprise plans aren't eligible."
  - You claim by linking a Console organization; it can't be changed yourself.
  - "Credits don't change your usage limits in Claude or Claude Code."
  - If an organization holding only these credits sends a Claude Code session, Claude Code shows "Credit balance too low · Add funds".
  - When the credits run out, "Usage is never charged to your Claude plan." — [Claude Platform Docs: API credits for Max and Team plans](https://platform.claude.com/docs/en/about-claude/api-credits-for-subscribers)
- Help Center:
  - Covered: the Claude API (Messages and Message Batches), Console Playground, Managed Agents and the Agent SDK. "`claude -p` and Agent SDK runs are covered when you run them yourself with an API key from the linked Console organization."
  - Not covered: interactive Claude Code in the terminal, IDE, desktop or web; extra usage in Claude, Claude Code or Cowork. Runs started by the Claude Code GitHub Action, an IDE extension or the desktop app count as Claude Code usage "even with `-p`".
  - "We're rolling out API credits over a few days." — [Help Center: API credits for Max and Team plans](https://support.claude.com/en/articles/17154008)
- "Max plan monthly API credits can't be used in Claude Code or the Claude apps." — [Help Center: Use Claude Code with your Pro or Max plan](https://support.claude.com/en/articles/11145838-use-claude-code-with-your-pro-or-max-plan)
- ClaudeDevs, 2026-10-07: "We're rolling out monthly Claude Platform API credits for Max and Team plans: Max 5x: $100 Max 20x: $200 Team: up to $500, pooled. Works on any model, including Haiku 5.5, in your code or third-party harnesses." — [ClaudeDevs on X](https://x.com/ClaudeDevs/status/2107895957933408429)
- ClaudeDevs follow-up, 2026-10-08: "Monthly API credits are now available on Max and Team plans". — [ClaudeDevs on X](https://x.com/ClaudeDevs/status/2108228391404966005)
- The pricing page lists "Max 5x includes $100/month in API credits and Max 20x includes $200/month ... for building on the Claude Platform", and Team "Up to $500/month in pooled API credits". — [claude.com/pricing](https://claude.com/pricing)
- A user report from 2026-10-13: "I saw it in my own settings today ... $100 a month on my Max 5x plan". — [Vaibhav Sisinty on X](https://x.com/VaibhavSisinty/status/2109954832539029796)
- (Post dates were decoded from X status-ID timestamps.)

### Inferences
- What $100 buys at list prices (Q6):
  - Opus 5.5: 25M input or 5M output tokens, or about 250M tokens of an agentic, cache-heavy coding mix (≈$0.40 per million tokens blended).
  - Sonnet 5.5: about 500M tokens of that mix.
  - Haiku 5.5 (prompts up to 100K tokens): 1B input or 200M output tokens.
  - Fable 5.1: 10M input or 2M output tokens.
- For a Claude Code user, the $100 is **not extra Claude Code capacity**. It can only fund coding through your own Agent-SDK tooling, `claude -p` with an API key, or a third-party harness using an API key. It adds about 2% to Max 5x's roughly $5,725 a month of in-subscription Opus 5.5 capacity.

### Gaps
- No anthropic.com news post about the credits was found; the launch evidence is the ClaudeDevs posts plus the docs.
- The Help Center does not explicitly address third-party harnesses. Only the ClaudeDevs post mentions them.

## Q6. What are the Claude API per-token prices for Fable 5.1, Opus 5.5, Sonnet 5.5 and Haiku 5.5?

### Takeaway
Prices per million tokens (input / output):
- Fable 5.1: $10 / $50.
- Opus 5.5: $4 / $20.
- Sonnet 5.5: $2 / $10.
- Haiku 5.5: $0.10 / $0.50 for prompts up to 100K tokens; $0.50 / $2.50 above that.

Cache reads are unusually cheap on the 5.x models: 0.025x the input price on Fable 5.1 and 0.05x on Opus 5.5 and Sonnet 5.5.

### Cited Findings
- Official table (USD per million tokens):

| Model | Base input | 5m cache write | 1h cache write | Cache hits & refreshes | Output |
|---|---|---|---|---|---|
| Claude Fable 5.1 | $10 | $12.50 | $20 | $0.25 (0.025x input) | $50 |
| Claude Opus 5.5 | $4 | $5 | $8 | $0.20 (0.05x input) | $20 |
| Claude Sonnet 5.5 | $2 | $2.50 | $4 | $0.10 (0.05x input) | $10 |
| Claude Haiku 5.5 (prompts up to 100,000 tokens) | $0.10 | $0.125 | $0.20 | $0.01 | $0.50 |
| Claude Haiku 5.5 (prompts over 100,000 tokens) | $0.50 | $0.625 | $1 | $0.05 | $2.50 |

  — [Claude Platform Docs: Pricing](https://platform.claude.com/docs/en/about-claude/pricing)
- Other modifiers:
  - Batch API: 50% off (Opus 5.5 batch $2/$10; Sonnet 5.5 $1/$5; Fable 5.1 $5/$25).
  - Opus 5.5 fast mode: $8 input / $40 output.
  - US-only inference (`inference_geo: "us"`): 1.1x.
  - Claude 4.6 and later models (except Haiku 5.5) have the full 1M context at standard pricing. Haiku 5.5 is priced per request by prompt length, with cache reads and writes counted toward the 100K threshold.
  - "Claude 4.7 and later models ... use a newer tokenizer ... approximately 30% more tokens for the same text."
  - Sonnet 5's $2/$10 introductory price became standard; the planned rise to $3/$15 on 2026-09-01 "will not occur".
  - Claude Mythos 5.1 (limited availability) is priced like Fable 5.1. — [Claude Platform Docs: Pricing](https://platform.claude.com/docs/en/about-claude/pricing)
- Legacy prices for reference: Opus 5 and Opus 4.5–4.8 $5/$25 (cache read $0.50); Sonnet 4.5/4.6 $3/$15; Haiku 4.5 $1/$5; Fable 5 $10/$50 (cache read $1). — [Claude Platform Docs: Pricing](https://platform.claude.com/docs/en/about-claude/pricing)
- At launch, Opus 5.5 input and output were "20% less than Opus 5", cache reads were "60% less", and "at default settings it will cost 40% less than Opus 5 on typical workloads". — [Anthropic: Claude Opus 5.5](https://www.anthropic.com/news/claude-opus-5-5)

### Inferences
- Agentic coding traffic is about 83–97% cache reads (Wokey and SemiAnalysis mixes). The low 5.x cache-read prices therefore shrink "API-equivalent value" per token compared with 2025-era models. SemiAnalysis notes Opus API-equivalent value fell at the 5.5 launch even though limits rose about 20% on Max. Dollar-value figures from different price lists (June against October 2026, or 2025) are not directly comparable.

### Gaps
- None material for list prices.

## Q7. How many API-dollars a month does a maxed-out Pro or Max 5x subscription deliver in Claude Code (community measurements)?

### Takeaway
Two independent October 2026 sources agree that value scales roughly linearly with price from Pro to Max 5x to Max 20x, at a high multiple of the fee:
- **SemiAnalysis** (Opus 5.5, synthetic cache-heavy agentic workload): Pro ≈ **$1,178 a month (≈59x)**, Max 5x ≈ **$5,725 (≈57x)**, Max 20x ≈ $11,726 (≈59x).
- **Wokey** (real mixed-model gateway traffic): Pro ≈ **$681 a month (≈34x)**, Max 20x ≈ $6,944 (≈35x). Max 5x is not yet published.

Fable 5.1 yields only about 12x (Max 5x ≈ $1,273 a month), because it burns limits faster and is capped at 50% of the weekly limit.

### Cited Findings
- SemiAnalysis, October 2026 (authors Andrew Megalaa, Max Kan, Dylan Patel), monthly API-equivalent value at first-party list rates, capped by weekly limits:

| Plan (fee) | Model / workload | Value | Multiplier |
|---|---|---|---|
| Pro ($20) | Opus 5.5 | $1,178 | 58.9x |
| Max 5x ($100) | Opus 5.5 | $5,725 | 57.3x |
| Max 5x ($100) | Fable 5.1 | $1,273 | 12.7x |
| Max 20x ($200) | Opus 5.5, agentic | $11,726 | 58.6x |
| Max 20x ($200) | Opus 5.5, chat | $9,200 | 46.0x |
| Max 20x ($200) | Sonnet 5.5, agentic / chat | $12,529 / $7,009 | 62.6x / 35.0x |
| Max 20x ($200) | Fable 5.1, agentic | $2,485 | 12.4x |

- SemiAnalysis method:
  - Agentic mix: 0.4% fresh input, 96.6% cached input, 2.6% cache writes, 0.3% output. Chat mix: 2% / 75% / 13% / 10%.
  - Limits are measured by counting steps between meter increases, within ±5%. Fixed overhead is subtracted, and the monthly total is capped by weekly limits.
  - Workload is not specified for the Pro and Max 5x Opus rows. — [explainx.ai summary of SemiAnalysis](https://explainx.ai/blog/semianalysis-claude-vs-chatgpt-subscription-api-value-limit-testing-2026)
- Wokey, week of 2026-10-19 to 10-26 (third-party; Wokey sells API access, a commercial interest):

| Plan | Value per week | Value per month | Per 5-hour window | Uncached tokens per week | Output per week | Accounts measured |
|---|---|---|---|---|---|---|
| Pro | $157 (range $142–$170) | ≈$681 | $11 | 23.6M | 1.7M | 103 |
| Max 5x | "Collecting" (not published) | — | — | — | — | 2 |
| Max 20x | $1,602 (range $1,147–$1,745) | ≈$6,944 | $263 | 182M | 12.8M | 21 |

  - Total tokens per week: Pro about 141M (83% cache reads); Max 20x about 2.08B (92% cache reads).
  - Method: real subscription accounts behind the Wokey gateway, priced at official API rates including cache reads and writes, medians per account then across accounts, remeasured weekly. — [Wokey](https://wokey.ai/claude-usage-limits)
- SemiAnalysis's earlier June 2026 study (secondary sources only; pre-Opus 5.5, different price list): it "bought every single Claude and ChatGPT subscription plan. Ran long coding tasks non-stop until they hit the weekly limits". It reported "Claude Max delivers $8,000/mo in compute for $200". — [Vaibhav Sisinty on X, 2026-06-11](https://x.com/VaibhavSisinty/status/2065048691791290691); [Aadit Sheth on X, 2026-06-13](https://x.com/aaditsh/status/2065713846476492933); [Tech Jack Solutions](https://techjacksolutions.com/ai-brief/a-200-subscription-costs-openai-up-to-14000-in-compute-semia/)
- ccusage is a community tool (`npx ccusage@latest monthly`) that reports local Claude Code token usage priced at API rates. No 2026 ccusage-based Max 5x figure was found. — [developersdigest.tech (search result)](https://www.developersdigest.tech/blog/claude-max-team-monthly-api-credits-2026)

### Inferences
- Multipliers: Wokey Pro $681 / $20 ≈ **34x**; Wokey Max 20x $6,944 / $200 ≈ **35x**. SemiAnalysis Pro ≈ **59x**; Max 5x ≈ **57x**. The roughly 1.7x gap between the sources comes from workload and model mix: SemiAnalysis uses single-model, maximally cache-heavy synthetic runs, while Wokey prices real mixed traffic.
- **Comparing the two packages:**
  - Max 5x: about $5,725 a month of Opus 5.5 capacity (or a Fable/Opus split, for example about $1,273 of Fable plus about $2,860 of Opus), plus $100 of API credits that can't be used in Claude Code.
  - 5 x Pro: about $5,890 a month of Opus 5.5 capacity, no included Fable and no API credits.
  - On raw dollars the two are a wash. The differences are qualitative: Fable access, the API credits, the per-limit wrap-up allowance, and single-account continuity.
- If Wokey's Max 20x / Max 5x ratio matches SemiAnalysis's (about 2x), Max 5x would come out at about $3,400 a month (≈34x) on Wokey's real-traffic basis.

### Gaps
- No direct Wokey Max 5x figure (n=2).
- The primary SemiAnalysis report is paywalled and was read through explainx.ai. The June 2026 figure comes from social and aggregator posts only.
- No Reddit or HN ccusage tallies for 2026 Max 5x were found within budget.

## Q8. Claude Team plan: seat prices, minimums, seat types, Claude Code on standard seats, and is Team viable at about $100 a month for one person?

### Takeaway
Team is for 2–150 seats, and seat types can be mixed:
- **Standard**: $25 a month ($20 billed annually). Includes Claude Code at 1.25x Pro per session. Fable runs only on usage credits.
- **Premium**: $125 a month ($100 billed annually). 6.25x Pro per session, which is 1.25x Max 5x. Includes Fable under the 50%-of-weekly rule.
- Limits are per member.

With a 2-seat minimum, a single person's cheapest setup with a Premium seat is $150 a month (monthly billing) or $120 a month (annual commitment). Two Standard seats ($50 a month) give each seat only 1.25x Pro. Team is not a cheaper route to Max-5x-level capacity at about $100 a month.

### Cited Findings
- Seat prices (US, excluding tax): Standard $25 per member per month billed monthly or $20 billed annually; Premium $125 or $100.
- "Team plans require a minimum of two members". The maximum is 150 seats; larger organizations move to Enterprise.
- Standard seats include "Access to Claude Code".
- "Team plan Standard seats include 1.25x the Pro plan's per-session usage allowance". "Premium seats include 6.25x the Pro plan's per-session usage allowance". Both have a weekly limit across all models.
- "Usage limits on Team plans are per-member, rather than applied to the team as a whole."
- Admins can enable usage credits with org-level and user-level spend caps. — [Help Center: What is the Team plan?](https://support.claude.com/en/articles/9266767-what-is-the-team-plan)
- The pricing page says "For teams of 2 to 150". Premium is "5x more usage than standard seats". Fable on Team: "Premium seats: 50% of weekly limits". Enterprise is $20 per seat billed annually plus usage "at API rates", with a 20-seat minimum. — [claude.com/pricing](https://claude.com/pricing)
- Fable is not included on Team standard seats (usage credits) and is included on Team premium seats, as on Max. — [Help Center: Claude Fable models on your plan](https://support.claude.com/en/articles/15424964-claude-fable-models-on-your-plan)
- Team API credits are $20 per Standard seat and $100 per Premium seat, pooled and capped at $500. The docs' example, "a team with three Standard seats and two Premium seats receives $260 USD a month", confirms seats can be mixed. Like Max credits, these don't cover Claude Code. — [Claude Platform Docs: API credits for Max and Team plans](https://platform.claude.com/docs/en/about-claude/api-credits-for-subscribers)
- Before Claude Code v2.1.280, Team Standard defaulted to Sonnet 5 and Team Premium to Opus 5. All now default to Opus 5.5. — [Claude Code Docs: Model configuration](https://code.claude.com/docs/en/model-config)

### Inferences
- Minimum one-person setups:

| Setup | Cost per month (monthly billing) | Cost per month (annual billing) | What the person gets |
|---|---|---|---|
| 2 Standard seats | $50 | $40 | 1.25x Pro per session on their own seat; no Fable |
| 1 Premium + 1 Standard | $150 | $120 ($1,440 a year) | 6.25x Pro per session (25% more per session than Max 5x), Fable at 50% of weekly, $120 a month of pooled API credits |

  - In both cases the second seat is idle or belongs to another member, because limits are per member.
- At about $100 a month, Team cannot match Max 5x for one person. Team Premium is the closest analogue, and it costs 20–50% more because of the 2-seat minimum.

### Gaps
- Team Premium weekly limits relative to Max 5x are unmeasured.
- Terms of service on single-person Team organizations were not researched (out of scope).
