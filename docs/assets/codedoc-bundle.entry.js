import { getRenderer } from '/Users/chakib_houd/localspace/workspace/todo/.codedoc/node_modules/@codedoc/core/dist/es6/transport/renderer.js';
import { initJssCs } from '/Users/chakib_houd/localspace/workspace/todo/.codedoc/node_modules/@codedoc/core/dist/es6/transport/setup-jss.js';initJssCs();
import { installTheme } from '/Users/chakib_houd/localspace/workspace/todo/.codedoc/content/theme.ts';installTheme();
import { codeSelection } from '/Users/chakib_houd/localspace/workspace/todo/.codedoc/node_modules/@codedoc/core/dist/es6/components/code/selection.js';codeSelection();
import { sameLineLengthInCodes } from '/Users/chakib_houd/localspace/workspace/todo/.codedoc/node_modules/@codedoc/core/dist/es6/components/code/same-line-length.js';sameLineLengthInCodes();
import { initHintBox } from '/Users/chakib_houd/localspace/workspace/todo/.codedoc/node_modules/@codedoc/core/dist/es6/components/code/line-hint/index.js';initHintBox();
import { initCodeLineRef } from '/Users/chakib_houd/localspace/workspace/todo/.codedoc/node_modules/@codedoc/core/dist/es6/components/code/line-ref/index.js';initCodeLineRef();
import { initSmartCopy } from '/Users/chakib_houd/localspace/workspace/todo/.codedoc/node_modules/@codedoc/core/dist/es6/components/code/smart-copy.js';initSmartCopy();
import { copyHeadings } from '/Users/chakib_houd/localspace/workspace/todo/.codedoc/node_modules/@codedoc/core/dist/es6/components/heading/copy-headings.js';copyHeadings();
import { contentNavHighlight } from '/Users/chakib_houd/localspace/workspace/todo/.codedoc/node_modules/@codedoc/core/dist/es6/components/page/contentnav/highlight.js';contentNavHighlight();
import { loadDeferredIFrames } from '/Users/chakib_houd/localspace/workspace/todo/.codedoc/node_modules/@codedoc/core/dist/es6/transport/deferred-iframe.js';loadDeferredIFrames();
import { smoothLoading } from '/Users/chakib_houd/localspace/workspace/todo/.codedoc/node_modules/@codedoc/core/dist/es6/transport/smooth-loading.js';smoothLoading();
import { tocHighlight } from '/Users/chakib_houd/localspace/workspace/todo/.codedoc/node_modules/@codedoc/core/dist/es6/components/page/toc/toc-highlight.js';tocHighlight();
import { postNavSearch } from '/Users/chakib_houd/localspace/workspace/todo/.codedoc/node_modules/@codedoc/core/dist/es6/components/page/toc/search/post-nav/index.js';postNavSearch();
import { ToCPrevNext } from '/Users/chakib_houd/localspace/workspace/todo/.codedoc/node_modules/@codedoc/core/dist/es6/components/page/toc/prevnext/index.js';
import { GithubSearch } from '/Users/chakib_houd/localspace/workspace/todo/.codedoc/node_modules/@codedoc/core/dist/es6/components/misc/github/search.js';
import { ToCToggle } from '/Users/chakib_houd/localspace/workspace/todo/.codedoc/node_modules/@codedoc/core/dist/es6/components/page/toc/toggle/index.js';
import { DarkModeSwitch } from '/Users/chakib_houd/localspace/workspace/todo/.codedoc/node_modules/@codedoc/core/dist/es6/components/darkmode/index.js';
import { ConfigTransport } from '/Users/chakib_houd/localspace/workspace/todo/.codedoc/node_modules/@codedoc/core/dist/es6/transport/config.js';
import { TabSelector } from '/Users/chakib_houd/localspace/workspace/todo/.codedoc/node_modules/@codedoc/core/dist/es6/components/tabs/selector.js';
import { CollapseControl } from '/Users/chakib_houd/localspace/workspace/todo/.codedoc/node_modules/@codedoc/core/dist/es6/components/collapse/collapse-control.js';

const components = {
  '3JB7uPGzFE2DquGpFeSq+g==': ToCPrevNext,
  'xmoa9gYlA/VHtlTIkYuinw==': GithubSearch,
  'eYrHXiHf4s1q8//lCCPxog==': ToCToggle,
  'Rv3sAw00JGTGC26JwDYZgw==': DarkModeSwitch,
  '4pD1/ecGinbCMYWVbMEq9Q==': ConfigTransport,
  '0/otAk+/wVBE5f+Kexu0Hw==': TabSelector,
  'fjVYBOATQ3gBUXCfWoJpBw==': CollapseControl
};

const renderer = getRenderer();
const ogtransport = window.__sdh_transport;
window.__sdh_transport = function(id, hash, props) {
  if (hash in components) {
    const target = document.getElementById(id);
    renderer.render(renderer.create(components[hash], props)).after(target);
    target.remove();
  }
  else if (ogtransport) ogtransport(id, hash, props);
}
