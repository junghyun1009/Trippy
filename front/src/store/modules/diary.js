import { createStore } from 'vuex'

export default createStore({
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
      state.stories.push(state.story)
    }
  },
  actions: {
    createStory({ commit }, story) {
      commit('CREATE_STORY', story)
    }
  },
})
