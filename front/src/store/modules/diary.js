export default ({
  state: {
    stories: [],
    story: {}
  },
  getters: {
    stories: state => state.stories,
    story: state => state.story
  },
  mutations: {
    CREATE_STORY(state, story) {
      state.story = story
      state.stories.push(story)
    },
    DELETE_STORY(state, index) {
      state.stories.splice(index, 1)
    }
  },
  actions: {
    createStory({ commit }, story) {
      commit('CREATE_STORY', story)
    },
    deleteStory({ commit }, index) {
      commit('DELETE_STORY', index)
    }
  },
})
