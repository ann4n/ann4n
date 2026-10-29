// Injected after each page load as (this function)(savedAnnotations, token).
// Tap text to put a caret there and type an annotation; tap anywhere else to save it.
// An annotation's position is the gap between two characters of the page's text.
(function (saved, token) {
  if (window.__mcpbAnnotate) return;
  window.__mcpbAnnotate = true;
  const native = window.MCPBrowserNative;
  const CLS = 'mcpb-ann', CONTEXT = 32;

  const style = document.createElement('style');
  style.textContent = `.${CLS}{color:#b25d00;font-style:italic;outline:none;white-space:pre-wrap}` +
    `.${CLS}:not(:empty){background:rgba(255,170,0,.15);border-radius:2px;padding:0 .15em}`;
  (document.head || document.documentElement).appendChild(style);

  // The page's text, without scripts, styles or annotations.
  function textNodes() {
    const walker = document.createTreeWalker(document.body, NodeFilter.SHOW_TEXT, {
      acceptNode: n => n.parentElement.closest(`script,style,noscript,.${CLS}`)
        ? NodeFilter.FILTER_REJECT : NodeFilter.FILTER_ACCEPT,
    });
    const nodes = [];
    for (let n; (n = walker.nextNode());) nodes.push(n);
    return nodes;
  }

  function locate(span) {
    let text = '', offset = -1;
    for (const n of textNodes()) {
      if (offset < 0 && span.compareDocumentPosition(n) & Node.DOCUMENT_POSITION_FOLLOWING) offset = text.length;
      text += n.data;
    }
    if (offset < 0) offset = text.length;
    return {
      prefix: text.slice(Math.max(0, offset - CONTEXT), offset),
      suffix: text.slice(offset, offset + CONTEXT),
      offset,
    };
  }

  // Where a saved annotation goes now: where its surrounding text is, nearest its old offset,
  // matching less of that text if the page has changed around it.
  function find(a) {
    const text = textNodes().map(n => n.data).join('');
    for (const n of [CONTEXT, 16, 8]) {
      const before = a.prefix.slice(-n), around = before + a.suffix.slice(0, n);
      if (!around) return -1;
      let best = -1;
      for (let i = text.indexOf(around); i >= 0; i = text.indexOf(around, i + 1)) {
        const at = i + before.length;
        if (best < 0 || Math.abs(at - a.offset) < Math.abs(best - a.offset)) best = at;
      }
      if (best >= 0) return best;
    }
    return -1;
  }

  function insertAt(node, offset) {
    const span = document.createElement('span');
    span.className = CLS;
    const after = node.splitText(offset);
    after.parentNode.insertBefore(span, after);
    return span;
  }

  for (const a of saved) {
    if (!a.text) continue;
    let offset = find(a);
    if (offset < 0) continue;
    for (const n of textNodes()) {
      if (offset <= n.data.length) {
        const span = insertAt(n, offset);
        span.dataset.id = a.id;
        span.textContent = a.text;
        break;
      }
      offset -= n.data.length;
    }
  }

  let active = null, original = '', swallowUntil = 0;

  function edit(span) {
    active = span;
    original = span.textContent;
    span.contentEditable = 'plaintext-only';
    span.addEventListener('blur', save, { once: true });
    span.focus();
    const range = document.createRange();
    range.selectNodeContents(span);
    range.collapse(false);
    getSelection().removeAllRanges();
    getSelection().addRange(range);
  }

  function save() {
    const span = active;
    if (!span) return;
    active = null;
    swallowUntil = Date.now() + 700; // the tap that ended editing shouldn't start a new annotation
    span.removeAttribute('contenteditable');
    const text = span.innerText.replace(/\n$/, '');
    if (text !== original) {
      const action = span.dataset.id ? 'update' : 'create';
      span.dataset.id = span.dataset.id || Date.now().toString(36) + Math.random().toString(36).slice(2, 8);
      native.save(token, JSON.stringify({ action, id: span.dataset.id, text, ...locate(span) }));
    }
    if (!text) span.remove();
  }

  addEventListener('pagehide', save);

  // Every tap annotates; none reach the page, so links and page scripts can't navigate.
  addEventListener('click', e => {
    e.preventDefault();
    e.stopImmediatePropagation();
    if (active || Date.now() < swallowUntil) return;
    const existing = e.target.closest && e.target.closest('.' + CLS);
    if (existing) return edit(existing);
    const r = document.caretRangeFromPoint(e.clientX, e.clientY);
    if (r && r.startContainer.nodeType === Node.TEXT_NODE) edit(insertAt(r.startContainer, r.startOffset));
  }, true);
})
