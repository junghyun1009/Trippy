import router from "@/router"
import axios from "axios"
// import _ from 'lodash'

export default ({
  state: {
    diaries: [],
    diary: {},
    comments: [],
    diaryTemp: {
      title: '제주도 3박 4일 여행',
      startDate: '2022-07-01',
      endDate: '2022-07-04',
      company: '친구',
      count: 4,
      transport: ['뚜벅이', '대중교통'],
      routes: [
        {routeNum: 1, routeName: '제주국제공항', geocode: {lat: 33.51041350000001, lng: 126.4913534}},
        {routeNum: 2, routeName: '한담해변', geocode: {lat: 33.461609, lng: 126.3105212}},
        {routeNum: 3, routeName: '랜디스도넛 제주애월점', geocode: {lat: 33.4611909, lng: 126.3116327}},
        {routeNum: 4, routeName: '곽지해수욕장', geocode: {lat: 33.450902, lng: 126.3057298}}
      ],
      stories: [
        {pk: 1, place: "제주 국제 공항", photoList: [ {file: "[object File]", preview: "https://www.okinawa.halekulani.com/lang_module/images/home/img_main-sp.jpg" } ], content: "111", rate: 3 }, 
        {pk: 2, place: "한담해변", photoList: [ {file: "[object File]", preview: "blob:http://localhost:8080/a3e66b80-2256-4034-bab0-f24ae67c0dfc" } ], content: "222", rate: 5 }
      ]
    },
  },
  getters: {
    diaries: state => state.diaries,
    diary: state => state.diary,
    comments: state => state.comments,
    diaryTemp: state => state.diaryTemp,
    // 이 친구 긴가민가
    isAuthor: (state, getters) => {
      return state.diary.member_id?.name === getters.currentUser.name
    },
    // isDiary: state => !_.isEmpty(state.diary)
  },
  mutations: {
    CREATE_DIARY(state, diary) {
      state.diary = diary
      // 얘는 지워도 될 것 같은데 일단 실험해봐야 함
      state.diaries.push(state.diary)
      console.log(state.diary)
      console.log(state.diaries)
    },

    SET_DIARY(state, diary) {
      state.diary = diary
    },

    SET_COMMENTS(state, comments) {
      state.comments = comments
    },

    // 홈화면에 추천(일단은 전부 띄우는 것)
    // 근데 diaries에 써도 되는건지 잘 모르겠음?
    GET_ALL_DIARY(state, diaries) {state.diaries = diaries},
  },
  actions: {
    // 일지 CREATE
    // 일지 저장
    createDiary({ commit }, diary) {
      commit('CREATE_DIARY', diary)
      // axios({
      //   url: 'http://localhost:8000/posts/',
      //   method: 'post',
      //   data: diary,
      //   headers: getters.authHeader,
      // })
      // .then(res => {
      //   commit('CREATE_DIARY', res.data)
      //   router.push({
      //     name: 'diaryDetail'
      //   })
      // })
    },

    // 일지 READ
    // 단일 일지
    fetchDiary({ commit, getters }, diaryPk) {
      axios({
        url: `http://localhost:8000/posts/detail/${diaryPk}`,
        method: 'get',
        headers: getters.authHeader
      })
      .then(res => commit('SET_DIARY', res.data))
      .catch(err => {
        console.error(err.response)
        if (err.response.status === 404) {
          router.push({ name: 'notFound404' })
        }
      })
    },

    // 일지 UPDATE
    // 일지 DELETE
    deleteDiary({ commit, getters }, diaryPk) {
      axios({
        url: `http://localhost:8000/posts/api/${diaryPk}`,
        method: 'delete',
        headers: getters.authHeader
      })
      .then(() => {
        commit('SET_DIARY', {})
        router.push({ name: 'home' })
      })
      .catch(err => console.error(err.response))
    },


    // 일지 comment 
    createComment({ getters, commit }, diaryPk, content) {
      const comment = { content }
      axios({
        url: `http://localhost:8000/comments/post/${diaryPk}`,
        method: 'post',
        data: comment,
        headers: getters.authHeader
      })
      .then( res => {
        commit('SET_COMMENTS'),
        res.data
        router.push('DiaryCommentView')
      })
      .catch(err => console.error(err.response))
    },

    updateComment({ getters, commit}, {diaryPk, commentPk, content}) {
      const comment = { content, diaryPk, commentPk }
      axios({
        url:'http://localhost:8000/comments/api/post',
        method: 'put',
        data: comment,
        headers: getters.authHeader,
      })
      .then(res => {
        commit('SET_COMMENTS', res.data)
      })
      .catch(err => console.error(err.response))
    }
  },

  fetchComment({ getters, commit}, diaryPk) {
    axios({
      url: `http://localhost:8000/comments/post/${diaryPk}`,
      method: 'get',
      headers: getters.authHeader
    })
    .then( res => {
      commit('SET_COMMENTS', res.data)
    })
    .catch(err => {
      if (err.response.status === 404) {
        router.push({ name: 'notFound404'})
      }
    })
  },

  deleteComment({ getters, commit}, diaryPk, commentPk) {
    if (confirm('정말 삭제하시겠습니까?')) {
      axios({
        url: `http://localhost:8000/comments/post/`,
        method: 'delete',
        data: {diaryPk, commentPk},
        headers: getters.authHeader,
      })
      .then(res => {
        commit('SET_COMMENTS', res.data)
      })
      .catch(err => console.error(err.response))
    }
  },

  showAllDiary({ commit }) {
    axios({
      url: 'http://localhost:8000/posts',
      method: 'get'
    })
    .then((res) => {
      const allDiary = res.data
      commit('SHOW_ALL_DIARY', allDiary)
    })
  },
})
