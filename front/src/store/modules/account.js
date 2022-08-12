import router from '@/router/index.js'
import axios from 'axios'
import VueCookies from 'vue-cookies'
// import jwt_decode from "jwt-decode"

export default {
  state: {
    // accessToken:  '',
    // refreshToken: '',
    currentUser: {},
    profile: {},
    userData: {},
    // 임시 email
    email: localStorage.getItem('email') || '',
    authError: null,
    fromPasswordFindView: false,
    verificationCode: ''
  },
  getters: {
    isLoggedIn: state => !!state.accessToken,
    // refreshToken: state => state.refreshToken,
    currentUser: state => state.currentUser,
    profile: state => state.profile,
    userData: state => state.userData,
    authError: state => state.authError,
    authHeader: () => ({ 'X-AUTH-TOKEN': `${VueCookies.get('accessToken')}`}),
    fromPasswordFindView: state => state.fromPasswordFindView,
    verificationCode: state => state.verificationCode
  },
  mutations: {
    // SET_ACCESS_TOKEN:(state, accessToken) => state.accessToken = accessToken,
    // SET_REFRESH_TOKEN: () => VueCookies.set('refreshToken'),
    SET_CURRENT_USER:(state, user) => state.currentUser = user,
    SET_PROFILE: (state, profile) => state.profile = profile,
    SET_USER_DATA: (state, userData) => state.userData = userData,
    SET_AUTH_ERROR: (state, error) => state.error = error,
    FROM_PASSWORD_FIND_VIEW: (state) => state.fromPasswordFindView = true,
    SET_EMAIL_AUTH_CODE: (state, verificationCode) => state.verificationCode = verificationCode,
  },
  actions: {
    // saveToken({ commit }, accessToken ) {
    //   commit('SET_ACCESS_TOKEN', accessToken)
    // },
    
    removeToken({ getters }, ) {
      console.log(getters)
      VueCookies.remove('accessToken')
      VueCookies.remove('refreshToken')
      localStorage.removeItem('email')
    },

    reissueToken({ dispatch, }) {
      // const data = { "email": localStorage.getItem('email') , "refreshToken": VueCookies.get('refreshToken') }
      // console.log(data)
      const email = localStorage.getItem('email')
      const refreshToken = VueCookies.get('refreshToken')
      axios({
        url: `http://i7a506.p.ssafy.io:8080/api/members/re-issue?email=${email}&refreshToken=${refreshToken}`,
        method: 'get',
        params: email
      })
      .then( res => {
        console.log('access token re-issued')
        // accessToken은 state에 저장.. 하지 않고 1시간 동안 쿠키에 저장
        const accessToken = res.data.accessToken
        // dispatch('saveToken', accessToken)
        VueCookies.set("accessToken", accessToken, '1h')
        // refreshToken은 7일동안 쿠키에 저장
        const refreshToken = res.data.refreshToken
        VueCookies.set("refreshToken", refreshToken, '7d')
        dispatch('fetchCurrentUser')
      })
      .catch(err => {
        console.error(err)
      })
    },

    login({ dispatch }, userinfo) {
      axios({
        url: 'http://i7a506.p.ssafy.io:8080/api/members/login',
        method: 'post',
        data: userinfo,
      })
        .then( res => {
          // accessToken은 state에 저장.. 하지 않고 1시간 동안 쿠키에 저장
          const accessToken = res.data.accessToken
          // dispatch('saveToken', accessToken)
          VueCookies.set("accessToken", accessToken, '1h')
          // refreshToken은 7일동안 쿠키에 저장
          const refreshToken = res.data.refreshToken
          VueCookies.set("refreshToken", refreshToken, '7d')
          dispatch('fetchCurrentUser')
          console.log('loginok')
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
            router.push({ name: 'login' })
          })
          .catch(err => {
            console.error(err.response)
            commit('SET_AUTH_ERROR', err.response)
          })
    },

    // 이메일 중복확인
    checkEmailDuplicate({ getters }, userinfo) {
      console.log(getters)
      axios({
        url: "http://i7a506.p.ssafy.io:8080/members/duplicate",
        method: 'get',
        data: userinfo.email
      })
      .then(() => {
        alert('이메일이 중복되었습니다')
      })
      .catch(err => {
        console.error(err)
      })
    },

    // 인증 코드 요청하기
    emailCode({commit}, userinfo) {
      console.log(userinfo)
      axios({
        url: 'http://i7a506.p.ssafy.io:8080/api/members/join/authmail',
        method: 'post',
        data: userinfo.email
      })
      .then( res => {
        console.log(res)
        commit('SET_EMAIL_AUTH_CODE', res.data)
      })
      .catch(err => {
        console.error(err)
      })
    },

    fetchCurrentUser({ getters, dispatch, commit }, ) {
      if (getters.isLoggedIn) {
        axios({
          url: 'http://i7a506.p.ssafy.io:8080/api/auth/members',
          method: 'get',
          headers: getters.authHeader,
        })
        .then( res => {
          console.log('successfully fetched current user info')
          const email = res.data.email
          console.log(email)
          localStorage.setItem('email', email)
          let gender = res.data.gender
          if ( gender === 1) {
            res.data.gender = '남자'
          } else ( res.data.gender = '여자')
          commit('SET_CURRENT_USER', res.data)})
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
        url: 'http://i7a506.p.ssafy.io:8080/api/members/logout',
        method: 'get',
        headers: getters.authHeader,
      })
      .then((res) => {
        dispatch('removeToken')
        console.log(getters.isLoggedIn)
        router.push({ name: 'home' })
        console.log(res.data)
        alert('성공적으로 로그아웃 되었습니다')
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

    changePassword({ commit, dispatch }, userinfo) {
      axios({
        url: 'http://i7a506.p.ssafy.io:8080/api/members/change_pw',
        method: 'post',
        data: userinfo.newPassword
      })
      .then((res) => {
        console.log(res)
        dispatch('fetchCurrentUser')
        commit('SET_USER_DATA')
        alert('비밀번호가 변경되었습니다')
        router.push({ name: 'login' })
      })
      .catch( err => {
        console.error(err.response)
      })       
    }, 

    deleteAccount({ getters }) {
      axios({
        url: 'http://i7a506.p.ssafy.io:8080/api/auth/members/remove',
        method: 'delete',
        headers: getters.authHeader,
      })
      .then( () => {
        console.log('deleted')
        router.push({ name: 'home' })
      })
      .catch(err => {
        console.error(err)
      })
    }

  },
  modules: {
  }
}