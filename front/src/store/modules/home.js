// import router from '@/router/index.js'
import axios from 'axios'

export default ({
    state: {
			allDiaries: []
    },
    getters: {
			allDiaries: state => state.allDiaries,
    },
    mutations: {
			GET_ALL_DIARIES: (state, allDiaries) => state.allDiaries = allDiaries,
    },
    actions: {
			fetchAllDiaries({ commit }) {
				axios({
					url: 'http://i7a506.p.ssafy.io:8080/api/posts',
					method: 'get',
				})
				.then( res => {
					console.log('successfully got all diaries')
					const diaries = res.data
					console.log(diaries)
					commit('GET_ALL_DIARIES', diaries)
				})
				.catch( err => {
					console.error(err)
				})
			}
    },
    modules: {
    }
  })
  