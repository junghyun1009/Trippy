import router from "@/router"
import axios from "axios"
import { ElMessageBox } from "element-plus"
import VueCookies from 'vue-cookies'
// import _ from 'lodash'

export default ({
  state: {
    diaries: [],
    diary: {},
    // images: [],
    // image: {},

    comment: {},
    comments: [],
    commentToEdit: {},
    isChild: false,
    isEditing: false,
    parentComment: '',
    authorId: null,
    parentId: null,
    location: []
  },
  getters: {
    diaries: state => state.diaries,
    diary: state => state.diary,
    // images: state => state.images,
    // image: state => state.image,
    comment: state => state.comment,
    comments: state => state.comments,
    commentToEdit: state => state.commentToEdit,
    isChild: state => state.isChild,
    isEditing: state => state.isEditing,
    parentComment: state => state.parentComment,
    parentId: state => state.parentId,
    // 이 친구 긴가민가
    isAuthor: (state, getters) => {
      return state.diary?.name === getters.currentUser.name
    },
    authorId: state => state.authorId,
    location: state => state.location
    // isDiary: state => !_.isEmpty(state.diary)
  },
  mutations: {

    SET_DIARY(state, diary) {
      // diary.detailLocations.forEach((location) => {
      //   if (location.filename != null) {
      //     diary.representativeImg = location.filepath
      //     return false
      //   }
      // })
      state.diary = diary
      console.log(state.diary)
    },

    // SET_IMAGES(state, image) {
    //   state.image = image
    //   state.images.push(image)
    //   console.log(state.images)
    // },

    SET_COMMENT(state, payload) {
      state.comment.user = payload.user
      state.comment.info = payload.info
      console.log(state.comment)
      state.comments.push(state.comment)
      state.comment = {}
    },

    SWITCH_IS_EDITING(state, comment) {
      console.log(3, comment)
      state.commentToEdit = comment
      console.log(state.commentToEdit)
      state.isEditing = true
    },

    EMPTY_COMMENTS(state) {
      state.comments = []
    },

    SET_AUTHOR_ID(state, authorId) { 
      state.authorId = authorId
      console.log(state.authorId)
    },

    // 홈화면에 추천(일단은 전부 띄우는 것)
    // 근데 diaries에 써도 되는건지 잘 모르겠음?
    GET_ALL_DIARY(state, diaries) {
      diaries.forEach((diary) => {
        console.log(diary.detailLocations)
        diary.detailLocations.forEach((location) => {
          if (location.filepath.substr(-1) != '/') {
            diary.representativeImg = location.filepath
            return false
          }
        })
      })
      state.diaries = diaries
    },

    SHOW_PARENT(state, parent) {
      state.isChild = true
      state.parentComment = parent.member
      state.parentId = parent.parentId
    },

    HIDE_PARENT(state) {
      state.isChild = false
    },

    SET_LOCATION(state, location) {
      state.location = location
    },

    SET_DIARY_LIKE: (state, like) => (state.diary.like = like),
  },
  actions: {
    // 첫번째 일지인가요?
    checkFirstDiary({getters}) {
      axios({
        url: 'https://i7a506.p.ssafy.io/api/auth/posts/memberDetail',
        method: 'get',
        headers: getters.authHeader
      })
      .then( res => {
        const diaryList = res.data
        if ( diaryList.length === 1 ){
          ElMessageBox.alert('기록의 시작 뱃지를 획득하셨어요!', 
          '뱃지 획득을 축하합니다!', {
            confirmButtonText: 'OK'
          })
        }
        console.log(res.data)

      })
    },

    // 일지 CREATE
    // 일지 저장
    createDiary({ commit, dispatch }, diary) {
      // commit('CREATE_DIARY', diary)
      // console.log(1)
      console.log(diary)
      axios({
        url: 'https://i7a506.p.ssafy.io/api/auth/posts',
        method: 'post',
        data: diary,
        headers: {
          "Content-Type": "multipart/form-data",
          'X-AUTH-TOKEN': `${VueCookies.get('accessToken')}`
        }
      })
      .then(res => {
        console.log(res.data)
        commit('SET_DIARY', diary)
        dispatch('checkFirstDiary')
        // console.log(3)
        // console.log(getters.diary)
        router.push({
          name: 'diaryDetail',
          params: { diaryPk: res.data.postId }
        })
      })
    },

    // 일지 READ
    // 단일 일지
    fetchDiary({ commit, getters }, diaryPk) {
      axios({
        url: `https://i7a506.p.ssafy.io/api/posts/detail/${diaryPk}`,
        method: 'get',
        headers: getters.authHeader
      })
      .then(res => {
        commit('SET_DIARY', res.data)
        console.log('diary set')
        const authorId = res.data.memberId
        commit('SET_AUTHOR_ID', authorId)
        // const diary = res.data
        // diary.detailLocations.forEach((location) => {
        //   const imagePk = location.id
        //   dispatch('fetchImage', imagePk)
        // })
      })
      .catch(err => {
        console.error(err.response)
        if (err.response.status === 404) {
          router.push({ name: 'notFound404' }) 
        }
      })
    },

    // fetchImage({ commit, getters }, imagePk) {
    //   axios({
    //     url: `https://i7a506.p.ssafy.io/api/posts/images/${imagePk}`,
    //     method: 'get',
    //     headers: getters.authHeader
    //   })
    //   .then((res) => {
    //     console.log(res.data)
    //     commit('SET_IMAGES', res.data)
    //   })
    // },

    // 일지 UPDATE
    updateDiary({ commit }, diary) {
      axios({
        url: `https://i7a506.p.ssafy.io/api/auth/posts/${diary.id}`,
        method: 'put',
        data: diary.content,
        headers: {
          "Content-Type": "multipart/form-data",
          'X-AUTH-TOKEN': `${VueCookies.get('accessToken')}`
        }
      })
      .then(res => {
        console.log(res.data)
        commit('SET_DIARY', diary)
        router.push({
          name: 'diaryDetail',
          parmas: { diaryPk: res.data }
        })
      })
    },

    // 일지 DELETE
    deleteDiary({ commit, getters }, diaryPk) {
      axios({
        url: `https://i7a506.p.ssafy.io/api/auth/posts/${diaryPk}`,
        method: 'delete',
        headers: getters.authHeader
      })
      .then(res => {
        commit('SET_DIARY', {})
        console.log(res)
        router.push({ name: 'home' })
      })
      .catch(err => console.error(err.response))
    },

    // 일지 댓글 CREATE
    createComment({ commit, getters }, payload) {
      console.log(payload)
      axios({
        url: 'https://i7a506.p.ssafy.io/api/auth/comment',
        method: 'post',
        data: payload,
        headers: getters.authHeader
      })
      .then((res) => {
        console.log(res.data);
        commit('SET_COMMENT', payload)
        router.push({
          name: 'diaryDetail',
          parmas: { diaryPk: payload.postId }
        })
        location.reload()
      })
      .catch((err) => console.error(err.response))
    },

    // 일지 댓글 목록 조회
    fetchComment({ getters, commit, dispatch }, diaryPk) {
      axios({
        url: `https://i7a506.p.ssafy.io/api/comment/${diaryPk}`,
        method: 'get',
        headers: getters.authHeader
      })
      .then((res) => {
        console.log(res.data)
        commit('EMPTY_COMMENTS')
        res.data.forEach((comment) => {
          dispatch('fetchUser', comment)
        })
      })
      .catch(err => {
        if (err.response.status === 404) {
          router.push({ name: 'notFound404'})
        }
      })
    },

    // 각 댓글 유저 조회
    fetchUser({ getters, commit }, comment) {
      console.log(comment)
      axios({
        url: `https://i7a506.p.ssafy.io/api/members/${comment.memberId}`,
        method: 'get',
        headers: getters.authHeader
      })
      .then((res) => {
        console.log(res.data)
        const user = res.data.name
        const payload = {
          user: user,
          info: comment
        }
        commit('SET_COMMENT', payload)
      })
    },

    // 댓글 수정 상태 전환 및 댓글 정보 보내기
    switchIsEditing({ commit }, comment) {
      console.log(2, comment)
      commit('SWITCH_IS_EDITING', comment)
    },

    // 댓글 수정
    updateComment({ commit, getters }, comment) {
      console.log(comment)
      axios({
        url: `https://i7a506.p.ssafy.io/api/auth/comment/${comment.id}`,
        method: 'put',
        data: comment.data,
        headers: getters.authHeader
      })
      .then((res) => {
        console.log(res.data);
        commit('SET_COMMENT', comment.data)
        router.push({
          name: 'diaryDetail',
          parmas: { diaryPk: comment.data.postId }
        })
        location.reload()
      })
      .catch((err) => console.error(err.response))
    },

    // 댓글 삭제
    deleteComment({ getters, commit }, pk) {
      axios({
        url: `https://i7a506.p.ssafy.io/api/auth/comment/${pk.commentId}`,
        method: 'delete',
        headers: getters.authHeader,
      })
      .then(res => {
        console.log(res.data)
        commit('SET_COMMENT', {})
        router.push({
          name: 'diaryDetail',
          parmas: { diaryPk: pk.diaryId }
        })
        location.reload()
      })
      .catch(err => console.error(err.response))
    },
  
    showParent({ commit }, parent) {
      commit('SHOW_PARENT', parent)
    },

    hideParent({ commit }) {
      commit('HIDE_PARENT')
    },
    // 다이어리 좋아요
    likeDiary({ commit, getters }, diary) {
      console.log(diary.id)
      axios({
        url: `https://i7a506.p.ssafy.io/api/auth/likepost`,
        method: 'post',
        data: {post_id: diary.id},
        headers: getters.authHeader
      })
      .then((res) => {
        console.log(res.data)
        commit('SET_DIARY_LIKE', true)
      })
      .catch(err => console.err(err.response))
    },
    // 좋아요 취소
    unlikeDiary({ commit, getters }, diary) {
      axios({
        url: `https://i7a506.p.ssafy.io/api/auth/likepost`,
        method: 'delete',
        data: {post_id: diary.id},
        headers: getters.authHeader
      })
      .then((res) => {
        console.log(res.data)
        commit('SET_DIARY_LIKE', false)
      })
      .catch(err => console.err(err.response))
    },
    checkLike({ commit, getters }, diaryPk) {
      axios({
        url: `https://i7a506.p.ssafy.io/api/auth/likepost/chk/${diaryPk}`,
        method: 'get',
        headers: getters.authHeader
      })
      .then((res) => {
        console.log(res.data)
        commit('SET_DIARY_LIKE', res.data)
      })
      .catch(err => console.err(err.response))
    },
    fetchLocation({ commit }) {
      axios({
        url: 'https://i7a506.p.ssafy.io/api/locations',
        method: 'get'
      })
      .then((res) => {
        commit('SET_LOCATION', res.data)
      })
    }
  },

})
