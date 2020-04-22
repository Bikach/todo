
import { configuration } from '@codedoc/core';

import { theme } from './theme';


export const config = /*#__PURE__*/configuration({
  theme,                                  // --> add the theme. modify `./theme.ts` for chaning the theme.
  dest: {
    namespace: '/todo'                    // --> your github pages namespace. remove if you are using a custom domain.
  },
  page: {
    title: {
      base: 'Todo'                        // --> the base title of your doc pages
    }
  },
  misc: {
    github: {
      user: 'Bikach',                     // --> your github username (where your repo is hosted)
      repo: 'todo',                       // --> your github repo name
    }
  },
});
