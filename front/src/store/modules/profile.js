// import router from "@/router"
import axios from "axios"

export default {
  state: {
    profile: {},
    myDiaries: [],
    isMyProfile: false,
    theirProfile: {},
    followerList: [],
    followingList: {},
    followerCount: null,
    followingCount: null,
    followingStatus: null,
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
    followingStatus: state => state.followingStatus,
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
    FOLLOWING_STATUS: (state, followingStatus ) => state.followingStatus = followingStatus,
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
        console.log('^ my diary list')
        commit('FETCH_MY_DIARY', res.data)
      })
      .catch(err => {
        console.log(err)
      })
    },

    fetchTheirProfile({commit, getters }, memberId) {
      console.log(memberId)
      axios({
        url: `http://i7a506.p.ssafy.io:8080/api/members/${memberId}`,
        method: 'get',
        params: memberId
      })
      .then( res => {
        console.log('authors profile from url param:', res.data)
        commit('SET_THEIR_PROFILE', res.data)
        console.log('getters check after fetching their profile')
        console.log(getters.theirProfile)
      })
      .catch(err => 
        console.log(err))
    },

    // 팔로잉 기능
    // 내 follower/following
    myFollowers({dispatch, getters, commit }) {
      axios({
        url: 'http://i7a506.p.ssafy.io:8080/api/auth/follow/follower',
        method: 'get',
        headers: getters.authHeader
      })
      .then( res => {
        console.log('my follower list:', res.data)
        commit('FOLLOWER_LIST', res.data)
        dispatch('myFollowersCount')
      })
    },

    myFollowersCount({getters, commit }) {
      axios({
        url: 'http://i7a506.p.ssafy.io:8080/api/auth/follow/follower/cnt',
        method: 'get',
        headers: getters.authHeader
      })
      .then( res => {
        console.log('number of my followers:', res.data)
        commit('FOLLOWER_COUNT', res.data)
      })
    },

    myFollowings({dispatch, getters, commit }) {
      axios({
        url: 'http://i7a506.p.ssafy.io:8080/api/auth/follow/following',
        method: 'get',
        headers: getters.authHeader
      })
      .then( res => {
        console.log('my following list:', res.data)
        commit('FOLLOWING_LIST', res.data)
        dispatch('myFollowingsCount')
      })
    },

    myFollowingsCount({getters, commit }) {
      axios({
        url: 'http://i7a506.p.ssafy.io:8080/api/auth/follow/following/cnt',
        method: 'get',
        headers: getters.authHeader
      })
      .then( res => {
        console.log('number of people I follow:', res.data)
        commit('FOLLOWING_COUNT', res.data)
      })
    },


    // 팔로우 언팔로우
    follow({ getters, commit }, followId) {
      console.log(followId)
      axios({
        url: 'http://i7a506.p.ssafy.io:8080/api/auth/follow',
        method: 'post',
        headers: getters.authHeader,
        data: followId
      })
      .then( res => {
        console.log('followed!', res.data)
        commit('FOLLOWING_STATUS', true)
        console.log('setgetters')
        console.log(getters.followingStatus)
      })
    },

    unfollow({ getters, commit }, followId) {
      axios({
        url: 'http://i7a506.p.ssafy.io:8080/api/auth/follow/undo',
        method: 'post',
        headers: getters.authHeader,
        data: followId
      })
      .then( res => {
        console.log('unfollowed!', res.data)
        commit('FOLLOWING_STATUS', false)
        console.log('setgetters')
        console.log(getters.followingStatus)
      })
    },

    setFollowingStatus({ getters, commit }, following_id) {
      console.log(following_id)
      axios({
        url: `http://i7a506.p.ssafy.io:8080/api/auth/follow/chk/${following_id}`,
        method: 'get',
        headers: getters.authHeader,
        params: following_id
      })
      .then( res => {
        console.log(res.data)
          commit('FOLLOWING_STATUS', res.data)
          console.log(getters.followingStatus)
      })
      .catch( err => {
        console.log(err)
      })
    }
    
    // fetchMyBadge({ commit }) {
    //   commit('SET_MY_BADGE')
    // }


  }
}