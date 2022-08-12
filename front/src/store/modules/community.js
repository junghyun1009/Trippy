
export default ({
  state: {
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
    temp: state => state.temp,
  },
  mutations: {
  },
  actions: {
  },
  modules: {
  }
})
