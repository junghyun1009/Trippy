import router from '@/router/index.js'
import axios from 'axios'
// import jwt_decode from "jwt-decode"

export default {
  state: {
    accessToken: this.$cookies.get("refreshToken") || '',
    currentUser: '',
    profile: {},
    authError: null,
    fromPasswordFindView: false,
  },
  getters: {
    isLoggedIn: state => !!state.accessToken,
    currentUser: state => state.currentUser,
    profile: state => state.profile,
    authError: state => state.authError,
    authHeader: state => ({ Authorization: `Token ${state.accessToken}`}),
    fromPasswordFindView: state => state.fromPasswordFindView
  },
  mutations: {
    SET_ACCESS_TOKEN:(state, token) => state.accesstoken = token,
    SET_CURRENT_USER:(state, user) => state.currentUser = user,
    SET_PROFILE: (state, profile) => state.profile = profile,
    SET_AUTH_ERROR: (state, error) => state.error = error,
    FROM_PASSWORD_FIND_VIEW: (state) => state.fromPasswordFindView = true,
  },
  actions: {
    saveToken({ commit }, accessToken ) {
      commit('SET_ACCESS_TOKEN', accessToken)

    },
    removeToken({ commit }, ) {
      commit('SET_ACCESS_TOKEN', '')
    },

    login({ dispatch }, userinfo) {
      axios({
        url: 'http://localhost:8000/members/login',
        method: 'post',
        data: userinfo,
      })
      .then( res => {
        // accessToken은 state에 저장
        const accessToken = res.data.access_token
        dispatch('saveToken', accessToken)
        // refreshToken은 쿠키에 저장
        const refreshToken = res.data.refresh_token
        this.$cookies.set("refreshToken", refreshToken)
        dispatch('fetchCurrentUser')
        router.push({ name: 'home' })
      })
      .catch(err => {
        console.error(err)
      })
    },

    signup({ commit, dispatch }, userinfo) {
      axios({
        url: 'http://localhost:8000/members/signup',
        method: 'post',
        data: userinfo
      })
      .then( res => {
        // accessToken은 state에 저장
        const accessToken = res.data.access_token
        dispatch('saveToken', accessToken)
        // refreshToken은 쿠키에 저장
        const refreshToken = res.data.refresh_token
        this.$cookies.set("refreshToken", refreshToken)
        dispatch('fetchCurrentUser')
        router.push({ name: 'login' })
      })
      .catch(err => {
        console.error(err.response.data)
        commit('SET_AUTH_ERROR', err.response.data)
      })
    },

    fetchCurrentUser({ getters, dispatch, commit }) {
      if (getters.isLoggedIn) {
        axios({
          // 여기 pk 넣는 형식 정확히 모르겠음
          url: 'http://localhost:8000/members/api/{id}',
          method: 'get',
          headers: getters.authHeader,
        })
        .then(res => commit('SET_CURRENT_USER', res.data))
        .catch(err => {
          if (err.response.status === 400) {
            dispatch('removeToken')
            router.push({ name: 'login' })
          }
        })
      }
    },

    logout({ getters, dispatch }) {
      axios({
        url: 'http://localhost:8000/members/logout',
        method: 'post',
        headers: getters.authHeader,
      })
      .then(() => {
        dispatch('removeToken')
        router.push({ name: 'home' })
      })
      .catch( err => {
        console.error(err.response)
      })
    },

    fromPasswordFindView({commit}, ) {
      commit('FROM_PASSWORD_FIND_VIEW')
      console.log(this.getters.fromPasswordFindView)
      router.push('/passwordchange')
    }
  },
  modules: {
  }
}