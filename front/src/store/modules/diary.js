export default ({
  state: {
    stories: [],
    story: {},
    diaries: [],
    diary: {}
  },
  getters: {
    stories: state => state.stories,
    story: state => state.story,
    diary: state => state.diary
  },
  mutations: {
    CREATE_STORY(state, story) {
      state.story = story
      state.stories.push(state.story)
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
      state.diaries.push(state.diary)
      console.log(state.diary)
      console.log(state.diaries)
    }
  },
  actions: {
    createStory({ commit }, story) {
      commit('CREATE_STORY', story)
    },
    deleteStory({ commit }, index) {
      commit('DELETE_STORY', index)
    },
    createDiary({ commit }, diary) {
      commit('CREATE_DIARY', diary)
    }
  },
})
