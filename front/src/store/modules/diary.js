export default ({
  state: {
    routeGeocodes: [],
    routeNames: [],
    stories: [],
    story: {},
    diaries: [],
    diary: {}
  },
  getters: {
    routeGeocodes: state => state.routeGeocodes,
    routeNames: state => state.routeNames,
    stories: state => state.stories,
    story: state => state.story,
    diary: state => state.diary
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
      state.diary.stories = state.stories
      state.diary.routes = state.routeNames
      state.diaries.push(state.diary)
      console.log(state.diary)
      console.log(state.diaries)
    }
  },
  actions: {
    addGeocode({ commit }, geocode) {
      commit('ADD_GEOCODE', geocode)
    },
    addRoute({ commit }, route) {
      commit('ADD_ROUTE', route)
    },
    deleteRoute({ commit }, routeIdx) {
      commit('DELETE_ROUTE', routeIdx)
    },
    createStory({ commit }, [index, story]) {
      commit('CREATE_STORY', [index, story])
    },
    deleteStory({ commit }, index) {
      commit('DELETE_STORY', index)
    },
    createDiary({ commit }, diary) {
      commit('CREATE_DIARY', diary)
    }
  },
})
