import router from '@/router/index.js'

export default {
  state: {
    fromPasswordFindView: false,
  },
  getters: {
    fromPasswordFindView: state => state.fromPasswordFindView
  },
  mutations: {
    FROM_PASSWORD_FIND_VIEW: (state) => state.fromPasswordFindView = true
  },
  actions: {
    fromPasswordFindView({commit}, ) {
      commit('FROM_PASSWORD_FIND_VIEW')
      console.log('hi')
      console.log(this.getters.fromPasswordFindView)
      console.log('hello')
      router.push('/passwordchange')
    }
  },
  modules: {
  }
}