import { createStore } from 'vuex';

import account from '@/store/modules/account.js'
import community from '@/store/modules/community.js'
import diary from '@/store/modules/diary.js'

export default createStore({
  modules: {
    account,
    community,
    diary,
  }
});