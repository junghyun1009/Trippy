// import router from '@/router/index.js'
import router from '@/router'
import axios from 'axios'

export default ({
    state: {
			allDiaries: [],
			regionDiaries: []
    },
    getters: {
			allDiaries: state => state.allDiaries,
			regionDiaries: state => state.regionDiaries,
    },
    mutations: {
			SET_REGION_DIARIES: (state, regionDiaries) => state.regionDiaries = regionDiaries,
			GET_ALL_DIARIES (state, allDiaries) {
				allDiaries.forEach((diary) => {
					diary.detailLocations.forEach((location) => {
						if (location.filename != null) {
							diary.representativeImg = location.filepath
							return false
						}
					})
				})
				state.allDiaries = allDiaries
			}
    },
    actions: {
			// getRegionDiaries({ dispatch, commit }, region) {
			// 	dispatch('fetchAllDiaries')
			// 	const diaryArray = this.getters.allDiaries
			// 	const diaryIndex = diaryArray.filter(diaryArray => diaryArray.cityName === region);
			// 	commit('GET_REGION_DIARIES')
			// 	console.log(diaryIndex)
			// 	if ( diaryIndex.length === 0 ) {
			// 		alert(' 해당 지역에의 정보가 없습니다. 전체 리스트로 돌아갑니다 ')
			// 	}
			// },

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
				.catch( res => {
					console.log(res.data)
				})
			},

			fetchRegionDiaries({ commit }, region) {
				const country = region.country
				const city = region.city
				axios({
					url: `http://i7a506.p.ssafy.io:8080/api/posts/${country}/${city}`,
					method: 'get',
					params: country, city
				})
				.then( res => {
					console.log(res.data)
					commit('SET_REGION_DIARIES', res.data)
					console.log('successfully fetched requested diaries')
				})
				.catch( err => {
					console.error(err)
					if ( err.response.data === "해당 게시물을 찾을 수 없습니다." ) {
						alert(' 해당 지역에 대한 게시물을 찾을 수 없습니다. 전체 목록으로 돌아갑니다 ')
						router.push({ name: 'home' })
					}
				})
			}
    },
    modules: {
    }
  })
  