// import router from '@/router/index.js'
import router from '@/router'
import axios from 'axios'
import { ElMessageBox  } from 'element-plus'

export default ({
    state: {
			allDiaries: [],
			regionDiaries: [],
			followingDiaries: [],
			searchDiaries: [],
			followingId: null,
    },
    getters: {
			allDiaries: state => state.allDiaries,
			regionDiaries: state => state.regionDiaries,
			followingDiaries: state => state.followingDiaries,
			searchDiaries: state => state.searchDiaries,
			followingId: state => state.followingId,
    },
    mutations: {
			// SET_REGION_DIARIES: (state, regionDiaries) => state.regionDiaries = regionDiaries,
			// SET_SEARCH_DIARIES: (state, searchDiaries) => state.searchDiaries = searchDiaries,
			GET_ALL_DIARIES (state, allDiaries) {
				// allDiaries.forEach((diary) => {
				// 	diary.detailLocations.forEach((location) => {
				// 		if (location.filename != null) {
				// 			diary.representativeImg = location.filepath
				// 			return false
				// 		}
				// 	})
				// })
				// 로고를 기본 썸네일로 저장하고 사진이 있다면 첫번째 사진을 썸네일로 등록
				for(let i=0; i<allDiaries.length; i++) {
					for(let j=0; j<allDiaries[i].detailLocations.length; j++) {
						allDiaries[i].representativeImg = require('@/assets/Trippy.png')
						if ((allDiaries[i].detailLocations[j].filename!=null) && 
						(typeof allDiaries[i].detailLocations[j].filename === 'string' && allDiaries[i].detailLocations[j].filename.slice(-3) != 'txt')){
							allDiaries[i].representativeImg = allDiaries[i].detailLocations[j].filepath
							break;
						}
					}
				}
				state.allDiaries = allDiaries
			},
			SET_REGION_DIARIES (state, regionDiaries) {
				regionDiaries.forEach((diary) => {
					diary.detailLocations.forEach((location) => {
						if (location.filename != null) {
							diary.representativeImg = location.filepath
							return false
						}
					})
				})
				state.regionDiaries = regionDiaries
			},

			
			SET_FOLLOWING_DIARIES (state, followingDiaries) { 
				console.log(followingDiaries);
				for(let i=0; i<followingDiaries.length; i++) {
					for(let j=0; j<followingDiaries[i].detailLocations.length; j++) {
						followingDiaries[i].representativeImg = require('@/assets/Trippy.png')
						if ((followingDiaries[i].detailLocations[j].filename!=null) && 
						(typeof followingDiaries[i].detailLocations[j].filename === 'string' && followingDiaries[i].detailLocations[j].filename.slice(-3) != 'txt')){
							followingDiaries[i].representativeImg = followingDiaries[i].detailLocations[j].filepath
							break;
						}
					}
				}
				state.followingDiaries = followingDiaries;
			},
			// SET_FOLLOWING_DIARIES (state, followingDiaries) {
			// 	followingDiaries.forEach((diary) => {
			// 		diary.detailLocations.forEach((location) => {
			// 			if (location.filename != null) {
			// 				diary.representativeImg = location.filepath
			// 				return false
			// 			}
			// 		})
			// 	})
			// 	state.followingDiaries = followingDiaries
			// },

			SET_SEARCH_DIARIES (state, searchDiaries) {
				searchDiaries.forEach((diary) => {
					diary.detailLocations.forEach((location) => {
						if (location.filename != null) {
							diary.representativeImg = location.filepath
							return false
						}
					})
				})
				state.searchDiaries = searchDiaries
			},

			SAVE_FOLLOWING_ID: (state, followingId) => state.followingId = followingId
    },
    actions: {
			fetchAllDiaries({ commit }) {
				axios({
					url: 'https://i7a506.p.ssafy.io/api/posts',
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
					url: `https://i7a506.p.ssafy.io/api/posts/${country}/${city}`,
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
						ElMessageBox.alert(' 해당 지역에 대한 게시물을 찾을 수 없습니다. 전체 목록으로 돌아갑니다 ', '알림', {
							confirmButtonText: 'OK',
						})
						router.push({ name: 'home' })
					}
				})
			},

			saveFollowingId({ commit }, followingId ) {
				console.log('this is the id of a person I follow', followingId)
				commit('SAVE_FOLLOWING_ID', followingId)
			},

			fetchFollowingDiaries({ commit, getters}, followingIdforDiary){
				console.log(followingIdforDiary)
				console.log('hellO')
				axios({
					url: 'https://i7a506.p.ssafy.io/api/posts',
					method: 'get',
					data: followingIdforDiary
				})
				.then( res => {
					console.log(res.data)
					const AllDiariesList = res.data

					console.log(AllDiariesList)
					console.log(followingIdforDiary)

					var tempList = []
					for (let i = 0; i < AllDiariesList.length; i++ ){
						console.log(AllDiariesList[i].memberId)
						if ( AllDiariesList[i].memberId === getters.followingId ) {
							tempList.push(AllDiariesList[i])
						} 
					}
					console.log(tempList)
					commit('SET_FOLLOWING_DIARIES', tempList)
				})
				.catch( err => {
					console.log(err)
				})
			},

			searchDiary({ commit }, searchInput){
				const title = searchInput.title
				const company = searchInput.company
				const transport = searchInput.transportId
				console.log(title)
				console.log(company)
				console.log(transport)
				axios({
					url: `https://i7a506.p.ssafy.io/api/search?title=${title}&company=${company}&transportId=${transport}`,
					// url: `https://i7a506.p.ssafy.io/api/search?title=${title}`,
					// url: 'https://i7a506.p.ssafy.io/api/search' + '?' + `${title}` + '?' + `${company}` + '?' + `${transport}`,
					method: 'get',
					params: title, company, transport
				})
				.then( res => {
					console.log(res.data)
					var searchResult = res.data
					if ( searchResult.length === 0 ){
						console.log('검색결과가 없습니다')
					}
					commit('SET_SEARCH_DIARIES', res.data),
					console.log('search completed')
					router.push({ name: 'search'})
					}
				)
			}
    },
    modules: {
    }
  })
  