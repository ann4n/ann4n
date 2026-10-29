// Injected after each page load as (this function)(savedAnnotations, token).
// Tap text that isn't clickable to put a caret there and type an annotation; tap anywhere else
// to save it. Tap an annotation to edit it, drag it to move it. An annotation's position is the
// gap between two characters of the page's text.
(function (saved, token) {
  if (window.__mcpbAnnotate) return;
  window.__mcpbAnnotate = true;
  const native = window.MCPBrowserNative;
  const CLS = 'mcpb-ann', CONTEXT = 32;

  const style = document.createElement('style');
  style.textContent = `.${CLS}{color:#b25d00;font-style:italic;outline:none;white-space:pre-wrap}` +
    `.${CLS}:not(:empty){background:rgba(255,170,0,.15);border-radius:2px;padding:0 .15em}` +
    `.${CLS}:not([contenteditable]){user-select:none;-webkit-user-select:none;-webkit-touch-callout:none}`;
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

  // The gap in the page's text under a point, if there's text there (not the space between blocks).
  function gapAt(x, y) {
    const r = document.caretRangeFromPoint(x, y);
    return r && r.startContainer.nodeType === Node.TEXT_NODE && /\S/.test(r.startContainer.data) &&
      !r.startContainer.parentElement.closest('.' + CLS) ? r : null;
  }

  function insertAt(node, offset, span = document.createElement('span')) {
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

  let active = null, original = '', drag = null, swallowUntil = 0;
  const swallowNextTap = () => { swallowUntil = Date.now() + 700; };

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
    swallowNextTap(); // the tap that ended editing does nothing else
    span.removeAttribute('contenteditable');
    const text = span.innerText.replace(/\n$/, '');
    if (text !== original) {
      if (span.dataset.id) {
        native.save(token, JSON.stringify({ action: 'update', id: span.dataset.id, text }));
      } else {
        span.dataset.id = Date.now().toString(36) + Math.random().toString(36).slice(2, 8);
        native.save(token, JSON.stringify({ action: 'create', id: span.dataset.id, text, ...locate(span) }));
      }
    }
    if (!text) span.remove();
  }

  addEventListener('pagehide', save);

  // Drag an annotation to move it; it follows the finger from gap to gap.
  addEventListener('pointerdown', e => {
    const span = !active && e.target.closest('.' + CLS);
    if (span) drag = { span, x: e.clientX, y: e.clientY, moved: false };
  }, true);
  addEventListener('pointermove', e => {
    if (!drag || (!drag.moved && Math.hypot(e.clientX - drag.x, e.clientY - drag.y) < 10)) return;
    drag.moved = true;
    drag.span.style.display = 'none'; // find the gap in the page's text, not under the note
    const r = gapAt(e.clientX, e.clientY);
    drag.span.style.display = '';
    if (r) {
      drag.span.remove();
      insertAt(r.startContainer, r.startOffset, drag.span);
    }
  }, true);
  const drop = () => {
    if (drag && drag.moved) {
      swallowNextTap();
      native.save(token, JSON.stringify({ action: 'update', id: drag.span.dataset.id, ...locate(drag.span) }));
    }
    drag = null;
  };
  addEventListener('touchmove', e => drag && e.preventDefault(), { capture: true, passive: false }); // no scrolling
  addEventListener('pointerup', drop, true);
  addEventListener('pointercancel', drop, true);

  // Links and form submits only open pages through MCP.
  addEventListener('submit', e => e.preventDefault(), true);

  const CLICKABLE = 'a[href],button,input,select,textarea,label,summary,video,audio,[onclick],' +
    '[role=button],[role=link],[role=tab],[role=menuitem],[role=checkbox],[role=switch],' +
    '[tabindex]:not([tabindex="-1"]),[contenteditable]:not(.' + CLS + ')';

  addEventListener('click', e => {
    const block = () => { e.preventDefault(); e.stopImmediatePropagation(); };
    if (Date.now() < swallowUntil) return block();
    if (active) return active.contains(e.target) ? block() : undefined;
    const existing = e.target.closest('.' + CLS);
    if (existing) return block(), edit(existing);
    const link = e.target.closest('a[href]');
    if (link && link.href.split('#')[0] !== location.href.split('#')[0]) return block();
    if (e.target.closest(CLICKABLE) || getComputedStyle(e.target).cursor === 'pointer') return; // the page's own
    block();
    const r = gapAt(e.clientX, e.clientY);
    if (r) edit(insertAt(r.startContainer, r.startOffset));
  }, true);
})
