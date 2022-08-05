
export default ({
  state: {
    temp: {
      title: '가평에서 같이 노실분~',
      category: '파티',
      desc: '혹시 가평 근처에서 같이 노실 분?혹시 가평 근처에서 같이 노실 분?혹시 가평 근처에서 같이 노실 분?',
      date:  ['Wed Aug 03 2022 12:30:16 GMT+0900 (한국 표준시)', 'Thu Aug 04 2022 00:00:00 GMT+0900 (한국 표준시)'],
      time: 'Thu Aug 04 2022 14:40:00 GMT+0900 (한국 표준시)',
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
    temp: state => state.temp,
  },
  mutations: {
  },
  actions: {
  },
  modules: {
  }
})
