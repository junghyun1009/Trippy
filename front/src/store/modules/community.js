import router from "@/router"
import axios from "axios"

export default ({
  state: {
    posts: [],
    post: {},

    temp: {
      title: '가평에서 같이 노실분~',
      category: '파티',
      desc: '혹시 가평 근처에서 같이 노실 분?혹시 가평 근처에서 같이 노실 분?혹시 가평 근처에서 같이 노실 분?',
      start_date:  '2022-08-12',
      isDay: true,
      end_date: '',
      time: '18:00',
      recruit_volume: 6,
      option: {
        gender: '여성만',
        age: [25, 30],
        isLocal: true
      },
      place: '가평역'
    }
  },
  getters: {
    posts: state => state.posts,
    post: state => state.post,
    temp: state => state.temp,
  },
  mutations: {
    SET_POSTS: (state, posts) => state.posts = posts,
    SET_POST: (state, post) => state.post = post,
  },
  actions: {
    createPost({ commit, getters }, post) {
      axios({
        url: 'http://i7a506.p.ssafy.io:8080/api/auth/community',
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
    }
  },
})
