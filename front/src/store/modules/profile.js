// import router from "@/router"
import axios from "axios"

export default {
  state: {
    profile: {}
  },
  getters: {
    profile: state => state.profile
  },
  mutations: {
    SET_PROFILE: (state, profile) => state.profile = profile
  },
  actions: {
    fetchProfile({ commit, getters }) {
      axios({
        url: 'http://i7a506.p.ssafy.io:8080/api/auth/members',
        method: 'get',
        headers: getters.authHeader,
      })
      .then( res => {
        console.log(res.data)
        commit('SET_PROFILE', res.data)
      })
    }
  }
}