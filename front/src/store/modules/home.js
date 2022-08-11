import axios from "axios"

export default ({
    state: {
			allDiary: {}
    },
    getters: {
			allDiary: state => state.allDiary,
    },
    mutations: {
			GET_ALL_DIARY: state => state.alldiary = alldiary
    },
    actions: {
			fetchAllDiary() {
				axios({
					url: 'http://i7a506.p.ssafy.io:8080/api/auth/posts/memberDetail',
					method: 'get',
				})
				.then( res => {
					console.log('yes maam')
				})
			}
    },
    modules: {
    }
  })
  