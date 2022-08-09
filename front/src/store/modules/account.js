import router from '@/router/index.js'
import axios from 'axios'
// import jwt_decode from "jwt-decode"

export default {
  state: {
    //this.$cookies.get("refreshToken") ||
    accessToken:  '',
    currentUser: {},
    profile: {},
    userData: {},
    authError: null,
    fromPasswordFindView: false,
  },
  getters: {
    isLoggedIn: state => !!state.accessToken,
    currentUser: state => state.currentUser,
    profile: state => state.profile,
    userData: state => state.userData,
    authError: state => state.authError,
    authHeader: state => ({ Authorization: `Token ${state.accessToken}`}),
    fromPasswordFindView: state => state.fromPasswordFindView
  },
  mutations: {
    SET_ACCESS_TOKEN:(state, token) => state.accesstoken = token,
    SET_CURRENT_USER:(state, user) => state.currentUser = user,
    SET_PROFILE: (state, profile) => state.profile = profile,
    SET_USER_DATA: (state, userData) => state.userData = userData,
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

    login({ dispatch }, userData) {
      axios({
        url: '/members/login',
        method: 'post',
        data: userData,
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

    signupOne({ commit }, userData) {
      // 첫번째 signup 페이지에서의 data 를 state에 저장
      commit('SET_USER_DATA', userData)
    },
    
    signupTwo({commit,}, userData) {
      commit('SET_USER_DATA', userData)
      console.log(this.getters.userData.password)
      console.log(this.getters.userData.description)
        axios({
          url: 'http://i7a506.p.ssafy.io:8080/api/members/join',
          method: 'post',
          data: this.getters.userData,
        })
          .then( () => {
            // // accessToken은 state에 저장
            // const accessToken = res.data.access_token
            // dispatch('saveToken', accessToken)
            // // refreshToken은 쿠키에 저장
            // const refreshToken = res.data.refresh_token
            // this.$cookies.set("refreshToken", refreshToken)
            // dispatch('fetchCurrentUser')
            router.push({ name: 'login' })
          })
          .catch(err => {
            console.error(err.response)
            commit('SET_AUTH_ERROR', err.response)
          })
      },

    fetchCurrentUser({ getters, dispatch, commit }, ) {
      if (getters.isLoggedIn) {
        axios({
          url: '/api/auth/members',
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
        url: 'http://i7a506.p.ssafy.io/members/logout',
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
    },

    updatePassword({ getters }, userinfo) {
      axios({
        url: 'http://i7a506.p.ssafy.io/members/changepw',
        method: 'put',
        headers: getters.authHeader,
        // @PathVariable email ???
        data: userinfo.newPassword
      })
        .then()
    }, 


  },
  modules: {
  }
}