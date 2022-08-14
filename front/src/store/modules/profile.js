// import router from "@/router"
import axios from "axios"

export default {
  state: {
    profile: {},
    myDiaries: [],
    isMyProfile: false,
    theirProfile: {},
  },
  getters: {
    profile: state => state.profile,
    myDiaries: state => state.myDiaries,
    myBadge: state => state.myBadge,
    isMyProfile: state => state.isMyProfile,
    theirProfile: state => state.theirProfile,
  },
  mutations: {
    SET_PROFILE: (state, profile) => state.profile = profile,
    SET_MY_BADGE: (state, badge) => state.badge = badge,
    IS_MY_PROFILE: (state, isMyProfile) => state.isMyProfile = isMyProfile,
    SET_THEIR_PROFILE: (state, theirProfile) => state.theirProfile = theirProfile,
    FETCH_MY_DIARY: (state, myDiaries) => {
      myDiaries.forEach((diary) => {
        diary.detailLocations.forEach((location) => {
          if (location.filename != null) {
            diary.representativeImg = location.filepath
            return false
          }
        })
      })
      state.myDiaries = myDiaries
    }
  },
  actions: {
    fetchProfile({ commit, getters }) {
      axios({
        url: 'http://i7a506.p.ssafy.io:8080/api/auth/members',
        method: 'get',
        headers: getters.authHeader,
      })
      .then( res => {
        // console.log(res.data)
        commit('SET_PROFILE', res.data)
      })
    },

    fetchMyDiary({ commit, getters }) {
      axios({
        url: 'http://i7a506.p.ssafy.io:8080/api/auth/posts/memberDetail',
        method: 'get',
        headers: getters.authHeader,
      })
      .then(res => {
        console.log(res.data)
        commit('FETCH_MY_DIARY', res.data)
      })
      .catch(err => {
        console.log(err)
      })
    },

    fetchTheirProfile({commit}, memberId) {
      console.log(memberId)
      axios({
        url: `http://i7a506.p.ssafy.io:8080/api/members/${memberId}`,
        method: 'get',
        params: memberId
      })
      .then( res => {
        console.log(res)
        commit('SET_THEIR_PROFILE', res.data)
      })
      .catch(err => 
        console.log(err))
    }
    
    // fetchMyBadge({ commit }) {
    //   commit('SET_MY_BADGE')
    // }


  }
}