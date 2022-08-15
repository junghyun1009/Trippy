// import router from "@/router"
import axios from "axios"

export default {
  state: {
    profile: {},
    myDiaries: [],
    isMyProfile: false,
    theirProfile: {},
    followerList: {},
    followingList: {},
    followerCount: null,
    followingCount: null,
  },
  getters: {
    profile: state => state.profile,
    myDiaries: state => state.myDiaries,
    myBadge: state => state.myBadge,
    isMyProfile: state => state.isMyProfile,
    theirProfile: state => state.theirProfile,
    followerList: state => state.followerList,
    followingList: state => state.followingList,
    followerCount: state => state.followerCount,
    followingCount: state => state.followingCount,
  },
  mutations: {
    SET_PROFILE: (state, profile) => state.profile = profile,
    SET_MY_BADGE: (state, badge) => state.badge = badge,
    IS_MY_PROFILE: (state, isMyProfile) => state.isMyProfile = isMyProfile,
    SET_THEIR_PROFILE: (state, theirProfile) => state.theirProfile = theirProfile,
    FOLLOWER_LIST: (state, followerList) => state.followerList = followerList,
    FOLLOWING_LIST: (state, followingList) => state.followingList = followingList,
    FOLLOWER_COUNT: (state, followerCount) => state.followerCount = followerCount,
    FOLLOWING_COUNT: (state, followingCount) => state.followingCount = followingCount,
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
    },

    // 팔로잉 기능
    // 내 follower/following
    myFollowers({dispatch, getters, commit}) {
      axios({
        url: 'http://i7a506.p.ssafy.io:8080/api/auth/follow/follower',
        method: 'get',
        headers: getters.authHeader
      })
      .then( res => {
        console.log('my follower list')
        console.log(res.data)
        commit('FOLLOWER_LIST', res.data)
        dispatch('myFollowersCount')
      })
    },

    myFollowersCount({getters, commit}) {
      axios({
        url: 'http://i7a506.p.ssafy.io:8080/api/auth/follow/follower/cnt',
        method: 'get',
        headers: getters.authHeader
      })
      .then( res => {
        console.log('number of my followers')
        console.log(res.data)
        commit('FOLLOWER_COUNT', res.data)
      })
    },

    myFollowings({dispatch, getters, commit}) {
      axios({
        url: 'http://i7a506.p.ssafy.io:8080/api/auth/follow/following',
        method: 'get',
        headers: getters.authHeader
      })
      .then( res => {
        console.log('my following list')
        console.log(res.data)
        commit('FOLLOWING_LIST', res.data)
        dispatch('myFollowingsCount')
      })
    },

    myFollowingsCount({getters, commit}) {
      axios({
        url: 'http://i7a506.p.ssafy.io:8080/api/auth/follow/following/cnt',
        method: 'get',
        headers: getters.authHeader
      })
      .then( res => {
        console.log('number of people I follow')
        console.log(res.data)
        commit('FOLLOWING_COUNT', res.data)
      })
    },


    // 팔로우 언팔로우
    follow({ getters }, followId) {
      axios({
        url: 'http://i7a506.p.ssafy.io:8080/api/auth/follow',
        headers: getters.authHeader,
        data: followId
      })
    },

    unfollow({ getters }, followId) {
      axios({
        url: 'http://i7a506.p.ssafy.io:8080/api/auth/follow/undo',
        headers: getters.authHeader,
        data: followId
      })
    }
    
    // fetchMyBadge({ commit }) {
    //   commit('SET_MY_BADGE')
    // }


  }
}