import router from "@/router"
import axios from "axios"

export default ({
  state: {
    routeGeocodes: [],
    routeNames: [],
    stories: [],
    story: {},
    diaries: [],
    diary: {},
    diaryTemp: {
      title: '제주도 3박 4일 여행',
      season: ['2022-07-01', '2022-07-04'],
      company: '친구',
      count: 4,
      transport: ['뚜벅이', '대중교통'],
      geocodes: [
        {lat: 33.51041350000001, lng: 126.4913534},
        {lat: 33.461609, lng: 126.3105212},
        {lat: 33.4611909, lng: 126.3116327},
        {lat: 33.450902, lng: 126.3057298}
      ],
      routes: ['제주국제공항', '한담해변', '랜디스도넛 제주애월점', '곽지해수욕장'],
      stories: [
        {pk: 1, place: "제주 국제 공항", photoList: [ {file: "[object File]", preview: "https://www.okinawa.halekulani.com/lang_module/images/home/img_main-sp.jpg" } ], content: "111", rate: 3 }, 
        {pk: 2, place: "한담해변", photoList: [ {file: "[object File]", preview: "blob:http://localhost:8080/a3e66b80-2256-4034-bab0-f24ae67c0dfc" } ], content: "222", rate: 5 }
      ]
    },
  },
  getters: {
    routeGeocodes: state => state.routeGeocodes,
    routeNames: state => state.routeNames,
    stories: state => state.stories,
    story: state => state.story,
    diaries: state => state.diaries,
    diary: state => state.diary,
    diaryTemp: state => state.diaryTemp,
    // 이 친구 긴가민가
    isAuthor: (state, getters) => {
      return state.diary.member_id?.name === getters.currentUser.name
    }
  },
  mutations: {
    ADD_GEOCODE(state, geocode) {
      state.routeGeocodes.push(geocode)
      console.log(state.routeGeocodes)
    },
    ADD_ROUTE(state, route) {
      state.routeNames.push(route)
      console.log(state.routeNames)
    },
    DELETE_ROUTE(state, routeIdx) {
      state.routeGeocodes.splice(routeIdx, 1)
      state.routeNames.splice(routeIdx, 1)
      console.log(state.routeGeocodes)
      console.log(state.routeNames)
    },
    CREATE_STORY(state, [index, story]) {
      // console.log(index)
      state.story = story
      state.stories.splice(index, 1, story)
      console.log(state.story)
      console.log(state.stories)
    },
    DELETE_STORY(state, index) {
      state.stories.splice(index, 1)
      console.log(state.stories)
    },
    CREATE_DIARY(state, diary) {
      state.diary = diary
      state.diary.geocodes = state.routeGeocodes
      state.diary.routes = state.routeNames
      state.diary.stories = state.stories
      // 얘는 지워도 될 것 같은데 일단 실험해봐야 함
      state.diaries.push(state.diary)
      // 초기화
      state.routeGeocodes = []
      state.routeNames = []
      state.stories = []

      console.log(state.diary)
      console.log(state.diaries)
    },
    SET_DIARY(state, diary) {
      state.diary = diary
    },
  },
  actions: {
    // 일지 CREATE
    // 위도 경도 추가
    addGeocode({ commit }, geocode) {
      commit('ADD_GEOCODE', geocode)
    },
    // 루트 추가
    addRoute({ commit }, route) {
      commit('ADD_ROUTE', route)
    },
    // 루트 삭제
    deleteRoute({ commit }, routeIdx) {
      commit('DELETE_ROUTE', routeIdx)
    },
    // 스토리 저장
    createStory({ commit }, [index, story]) {
      commit('CREATE_STORY', [index, story])
    },
    // 스토리 삭제
    deleteStory({ commit }, index) {
      commit('DELETE_STORY', index)
    },
    // 일지 저장
    createDiary({ commit, getters }, diary) {
      // commit('CREATE_DIARY', diary)
      axios({
        url: 'http://localhost:8000/posts/api',
        method: 'post',
        data: diary,
        headers: getters.authHeader,
      })
      .then(res => {
        commit('CREATE_DIARY', res.data)
        router.push({
          name: 'diaryDetail'
        })
      })
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
    }
  },
})
