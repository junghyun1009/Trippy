// import router from "@/router"
import axios from "axios"

export default {
  state: {
    profile: {},
    myDiaries: [],
    myLikes: [],
    myBookmarks: [],
    badgeList: [],
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
    myLikes: state => state.myLikes,
    myBookmarks: state => state.myBookmarks,
    badgeList: state => state.badgeList,
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
    SET_BADGE_LIST: (state, badgeList) => state.badgeList = badgeList,
    IS_MY_PROFILE: (state, isMyProfile) => state.isMyProfile = isMyProfile,
    SET_THEIR_PROFILE: (state, theirProfile) => state.theirProfile = theirProfile,
    FOLLOWER_LIST: (state, followerList) => state.followerList = followerList,
    FOLLOWING_LIST: (state, followingList) => state.followingList = followingList,
    FOLLOWER_COUNT: (state, followerCount) => state.followerCount = followerCount,
    FOLLOWING_COUNT: (state, followingCount) => state.followingCount = followingCount,
    FOLLOWING_STATUS: (state, followingStatus ) => state.followingStatus = followingStatus,
    FETCH_MY_DIARY: (state, myDiaries) => {
      for(let i=0; i<myDiaries.length; i++) {
        for(let j=0; j<myDiaries[i].detailLocations.length; j++) {
          myDiaries[i].representativeImg = require('@/assets/Trippy.png')
          if ((myDiaries[i].detailLocations[j].filename!=null) && 
          (typeof myDiaries[i].detailLocations[j].filename === 'string' && myDiaries[i].detailLocations[j].filename.slice(-3) != 'txt')){
            myDiaries[i].representativeImg = myDiaries[i].detailLocations[j].filepath
            break;
          }
        }
      }
      state.myDiaries = myDiaries
    },
    FETCH_MY_LIKES: (state, myLikes) => {
      for(let i=0; i<myLikes.length; i++) {
        for(let j=0; j<myLikes[i].detailLocations.length; j++) {
          myLikes[i].representativeImg = require('@/assets/Trippy.png')
          if ((myLikes[i].detailLocations[j].filename!=null) && 
          (typeof myLikes[i].detailLocations[j].filename === 'string' && myLikes[i].detailLocations[j].filename.slice(-3) != 'txt')){
            myLikes[i].representativeImg = myLikes[i].detailLocations[j].filepath
            break;
          }
        }
      }
      state.myLikes = myLikes
    },
    FETCH_MY_BOOKMARK: (state, myBookmarks) => {
      state.myBookmarks = myBookmarks
      console.log('눈치챙겨')
    },
  },
  actions: {
    fetchProfile({ commit, getters }) {
      axios({
        url: 'https://i7a506.p.ssafy.io/api/auth/members',
        method: 'get',
        headers: getters.authHeader,
      })
      .then( res => {
        console.log('when called fetchProfile:', res.data)
        commit('SET_PROFILE', res.data)
      })
    },

    fetchMyDiary({ commit, getters }) {
      axios({
        url: 'https://i7a506.p.ssafy.io/api/auth/posts/memberDetail',
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

    fetchTheirProfile({commit, }, memberId) {
      console.log(memberId)
      axios({
        url: `https://i7a506.p.ssafy.io/api/members/${memberId}`,
        method: 'get',
        params: memberId
      })
      .then( res => {
        console.log('authors profile from url param:', res.data)
        commit('SET_THEIR_PROFILE', res.data)
      })
      .catch(err => 
        console.log(err))
    },


    // 팔로잉 기능
    // 내 follower/following
    myFollowers({dispatch, getters, commit }) {
      axios({
        url: 'https://i7a506.p.ssafy.io/api/auth/follow/follower',
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
        url: 'https://i7a506.p.ssafy.io/api/auth/follow/follower/cnt',
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
        url: 'https://i7a506.p.ssafy.io/api/auth/follow/following',
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
        url: 'https://i7a506.p.ssafy.io/api/auth/follow/following/cnt',
        method: 'get',
        headers: getters.authHeader
      })
      .then( res => {
        console.log('number of people I follow:', res.data)
        commit('FOLLOWING_COUNT', res.data)
      })
    },


    // 다른 사람의 follower/following
    yourFollowers({dispatch, commit }, memberId) {
      axios({
        url: `https://i7a506.p.ssafy.io/api/auth/follow/follower/${memberId}`,
        method: 'get',
        // headers: getters.authHeader,
        params: memberId
      })
      .then( res => {
        console.log('their follower list:', res.data)
        commit('FOLLOWER_LIST', res.data)
        dispatch('yourFollowersCount', memberId)
      })
    },

    yourFollowersCount({ commit }, memberId) {
      axios({
        url: `https://i7a506.p.ssafy.io/api/auth/follow/follower/cnt/${memberId}`,
        method: 'get',
        // headers: getters.authHeader,
        params: memberId
      })
      .then( res => {
        console.log('number of their followers:', res.data)
        commit('FOLLOWER_COUNT', res.data)
      })
    },

    yourFollowings({dispatch, commit }, memberId) {
      axios({
        url: `https://i7a506.p.ssafy.io/api/auth/follow/following/${memberId}`,
        method: 'get',
        // headers: getters.authHeader,
        params: memberId
      })
      .then( res => {
        console.log('their following list:', res.data)
        commit('FOLLOWING_LIST', res.data)
        dispatch('yourFollowingsCount', memberId)
      })
    },

    yourFollowingsCount({ commit }, memberId) {
      axios({
        url: `https://i7a506.p.ssafy.io/api/auth/follow/following/cnt/${memberId}`,
        method: 'get',
        // headers: getters.authHeader,
        params: memberId
      })
      .then( res => {
        console.log('number of people they follow:', res.data)
        commit('FOLLOWING_COUNT', res.data)
      })
    },

    // 팔로우 언팔로우
    follow({ getters, commit }, followId) {
      console.log(followId)
      axios({
        url: 'https://i7a506.p.ssafy.io/api/auth/follow',
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
        url: 'https://i7a506.p.ssafy.io/api/auth/follow/undo',
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
        url: `https://i7a506.p.ssafy.io/api/auth/follow/chk/${following_id}`,
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
    },
    

    // 배지
    fetchBadges({ commit }, memberId) {
      // 프로필에 보이는 배지 일때는 url param으로 받아오고
      // 배지 페이지에서는 fetchProfile해서 id값으로 받아옴
      console.log('member id from url params', memberId)
      axios({
        url: `https://i7a506.p.ssafy.io/api/members/badges/${memberId}`,
        method: 'get',
        params: memberId,
      })
      .then( res => {
        console.log('successfully fetched badge list')
        const badgeList = res.data
        commit('SET_BADGE_LIST', badgeList)
        console.log('badge list:', badgeList)
      })
      .catch(err => {
        console.log(err)
      })
    },

    // 좋아요 가져오기
    fetchMyLikes({ commit, getters }) {
      // console.log(memberId)
      axios({
        url: 'https://i7a506.p.ssafy.io/api/auth/likepost',
        method: 'get',
        headers: getters.authHeader
      })
      .then((res) => {
        console.log(res.data)
        commit('FETCH_MY_LIKES', res.data)
      })
      .catch(err => console.err(err.response))
    },
    // 북마크 가져오기
    fetchMyBookmark({ commit, getters }) {
      // console.log(memberId)
      axios({
        url: 'https://i7a506.p.ssafy.io/api/auth/bookmark',
        method: 'get',
        headers: getters.authHeader
      })
      .then((res) => {
        console.log(res.data)
        commit('FETCH_MY_BOOKMARK', res.data)
      })
      .catch(err => console.err(err.response))
    },

  }
}