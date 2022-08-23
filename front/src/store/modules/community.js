import router from "@/router"
import axios from "axios"
import { ElMessageBox } from "element-plus"

export default ({
  state: {
    posts: [],
    post: {},
    searchCommunities: []
    // isBookmark: false,
  },
  getters: {
    posts: state => state.posts,
    post: state => state.post,
    // isBookmark: state => state.isBookmark,
    isPostAuthor: (state, getters) => {
      return state.post.name === getters.currentUser.name
    },
    searchCommunities: state => state.searchCommunities
    // temp: state => state.temp,
    
  },
  mutations: {
    SET_POSTS: (state, posts) => state.posts = posts,
    SET_POST: (state, post) => state.post = post,
    SET_POST_BOOKMARK: (state, bookmark) => (state.post.bookmark = bookmark),
    SET_SEARCH_COMMUNITIES: (state, result) => (state.searchCommunities = result) 
  },
  actions: {
    // 첫번째 게시글인가요?
    checkFirstPost({ getters }) {
      axios({
        url: 'https://i7a506.p.ssafy.io/api/auth/community/memberDetail',
        method: 'get',
        headers: getters.authHeader
      })
      .then( res => {
        console.log(res)
        const postList = res.data
        if ( postList.length === 1 ){
          ElMessageBox.alert('만남의 시작 뱃지를 획득하셨어요!', 
          '뱃지 획득을 축하합니다!', {
            confirmButtonText: 'OK'
          })
        }
        console.log(res.data)
      })
    },

    // 게시글 CREATE
    createPost({ commit, getters, dispatch }, post) {
      axios({
        url: 'https://i7a506.p.ssafy.io/api/auth/community',
        method: 'POST',
        data: post,
        headers: getters.authHeader,
      })
      .then(res => {
        console.log(res.data)
        dispatch('checkFirstPost')
        console.log('create!!!!!!!!!!!!!!', res.data)
        commit('SET_POST', post)
        router.push({
          name: 'communityDetail',
          params: { postPk: res.data.postId}
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
    // fetchBookmark({ commit, getters }) {
    //   axios({
    //     url: `https://i7a506.p.ssafy.io/api/auth/bookmark`,
    //     method: 'GET',
    //     headers: getters.authHeader
    //   })
    //   .then(res => {
    //     console.log(res.data)
    //     commit('SET_POST_BOOKMARK', res.data)
    //   })
    //   .catch(err => console.error(err.response))
    // },
    // 게시글 북마크 설정
    createBookmark({ commit, getters }, postPk) {
      // console.log(postPk)
      axios({
        url: `https://i7a506.p.ssafy.io/api/auth/bookmark/${postPk}`,
        method: 'POST',
        headers: getters.authHeader,
      })
      .then((res) => {
        // getters.isBookmark = true
        console.log(res.data)
        commit('SET_POST_BOOKMARK', true)
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
        commit('SET_POST_BOOKMARK', false)
      })
      .catch(err => console.err(err.response))
    },
    // 게시글 북마크 체크
    checkBookmark({ commit, getters }, postPk) {
      axios({
        url: `https://i7a506.p.ssafy.io/api/auth/bookmark/chk/${postPk}`,
        method: 'GET',
        headers: getters.authHeader
      })
      .then((res) => {
        console.log(res.data)
        commit('SET_POST_BOOKMARK', res.data)
        // if (res.data === true) {
        //   commit('SET_POST_BOOKMARK', true)
        // } else {
        //   commit('SET_POST_BOOKMARK', false)
        // }
      })
      .catch(err => console.err(err.response))
    },

    // 동행 검색 - 나라
    searchCommunity({ dispatch }, searchInput) {
      console.log(searchInput)
      const country = searchInput.region
      axios({
        url: `https://i7a506.p.ssafy.io/api/search/communityPost?countryName=${country}`,
        // url: `https://i7a506.p.ssafy.io/api/search?title=${title}`,
        // url: 'https://i7a506.p.ssafy.io/api/search' + '?' + `${title}` + '?' + `${company}` + '?' + `${transport}`,
        method: 'get',
        params: country
      })
      .then( res => {
        const payload = {
          result: res.data,
          query: {
            region: country
          }
        }
        dispatch('searchCommunityCity', payload)
        }
      )
    },

    // 동행 검색 - 도시
    searchCommunityCity({ commit }, searchInput) {
      const city = searchInput.query.region
      axios({
        url: `https://i7a506.p.ssafy.io/api/search/communityPost?cityName=${city}`,
        method: 'get',
        params: city
      })
      .then( res => {
        const payload = {
          result: [...searchInput.result, ...res.data],
          query: searchInput
        }
        console.log(payload.result)
        var searchResult = payload.result
        if ( searchResult.length === 0 ){
          console.log('검색결과가 없습니다')
        }
        commit('SET_SEARCH_COMMUNITIES', payload.result),
        console.log('search completed')
      })
    },

  },
})
