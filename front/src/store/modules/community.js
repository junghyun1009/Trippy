import router from "@/router"
import axios from "axios"
// import VueCookies from 'vue-cookies'

export default ({
  state: {
    posts: [],
    post: {},
    // isBookmark: false,

    // temp: {
    //   title: '가평에서 같이 노실분~',
    //   category: '파티',
    //   desc: '혹시 가평 근처에서 같이 노실 분?혹시 가평 근처에서 같이 노실 분?혹시 가평 근처에서 같이 노실 분?',
    //   start_date:  '2022-08-12',
    //   isDay: true,
    //   end_date: '',
    //   time: '18:00',
    //   recruit_volume: 6,
    //   option: {
    //     gender: '여성만',
    //     age: [25, 30],
    //     isLocal: true
    //   },
    //   place: '가평역'
    // }
  },
  getters: {
    posts: state => state.posts,
    post: state => state.post,
    // isBookmark: state => state.isBookmark,
    isPostAuthor: (state, getters) => {
      return state.post.name === getters.currentUser.name
    }
    // temp: state => state.temp,
  },
  mutations: {
    SET_POSTS: (state, posts) => state.posts = posts,
    SET_POST: (state, post) => state.post = post,
    SET_POST_BOOKMARK: (state, bookmark) => (state.post.bookmark = bookmark)
  },
  actions: {
    // 게시글 CREATE
    createPost({ commit, getters }, post) {
      axios({
        url: 'https://i7a506.p.ssafy.io/api/auth/community',
        method: 'POST',
        data: post,
        headers: getters.authHeader,
      })
      .then(res => {
        console.log(res.data)
        commit('SET_POST', post)
        router.push({
          name: 'communityDetail',
          params: { postPk: res.data }
        })
      })
    },
    // 게시글 목록 READ
    fetchPosts({ commit, getters }) {
      axios({
        url: 'https://i7a506.p.ssafy.io/api/community',
        method: 'GET',
        headers: getters.authHeader,
      })
      .then(res => commit('SET_POSTS', res.data))
      .catch(err => console.err(err.response))
    },
    // 단일 게시글 (디테일 페이지) READ
    fetchPost({ commit, getters }, postPk) {
      axios({
        url: `https://i7a506.p.ssafy.io/api/community/${postPk}`,
        method: 'GET',
        headers: getters.authHeader,
      })
      .then(res => commit('SET_POST', res.data))
      .catch(err => {
        console.err(err.response)
        if (err.response.status === 404) {
          router.push({ name: 'notFound404' })
        }
      })
    },
    // 게시글 UPDATE
    updatePost({ commit, getters }, post) {
      axios({
        url: `https://i7a506.p.ssafy.io/api/auth/community/${post.id}`,
        method: 'PUT',
        data: post.content,
        headers: getters.authHeader,
      })
      .then(res => {
        console.log(res.data)
        commit('SET_POST', post)
        router.push({
          name: 'communityDetail',
          params: { postPk: post.id }
        })
      })
    },
    // 게시글 DELETE
    deletePost({ commit, getters }, postPk) {
      axios({
        url: `https://i7a506.p.ssafy.io/api/auth/community/${postPk}`,
        method: 'DELETE',
        headers: getters.authHeader,
      })
      .then(() => {
        commit('SET_POST', {})
        router.push({ name: 'community' })
      })
      .catch(err => console.error(err.response))
    },
    // 게시글 북마크 가져오기
    fetchBookmark({ commit, getters }) {
      axios({
        url: `https://i7a506.p.ssafy.io/api/auth/bookmark`,
        method: 'GET',
        headers: getters.authHeader
      })
      .then(res => {
        console.log(res.data)
        commit('SET_POST', res.data)
      })
      .catch(err => console.error(err.response))
    },
    // 게시글 북마크 설정
    createBookmark({ commit, getters }, postPk) {
      // console.log(postPk)
      axios({
        url: `https://i7a506.p.ssafy.io/api/auth/bookmark/${postPk}`,
        method: 'POST',
        headers: getters.authHeader,
      })
      .then(res => {
        // getters.isBookmark = true
        console.log(res.data)
        commit('SET_POST_BOOKMARK', res.data)
        // router.push({
        //   name: 'communityDetail',
        //   params: {postPk: postPk}
        // })
      })
      .catch(err => console.err(err.response))
    },
    // 게시글 북마크 해제
    deleteBookmark({ commit, getters }, postPk) {
      axios({
        url: `https://i7a506.p.ssafy.io/api/auth/bookmark/${postPk}`,
        method: 'DELETE',
        headers: getters.authHeader
      })
      .then((res) => {
        console.log(res.data)
        // getters.isBookmark = false
        commit('SET_POST_BOOKMARK', {})
      })
      .catch(err => console.err(err.response))
    }
  },
})
