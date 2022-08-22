<template>
  <div id="app">
    <the-header></the-header>
    <router-view/>
    <nav-bar v-if="this.$route.name !== 'login' && this.$route.name !== 'signUp' && this.$route.name !== 'signUpOption'"></nav-bar>
  </div>
</template>

<script>
import TheHeader from '@/components/common/TheHeader.vue'
import NavBar from '@/components/common/NavBar.vue'
import { mapActions } from 'vuex';
export default ({
  name: 'App',
  components: {
    TheHeader,
    NavBar
  },
  created() {
    //메인 컴포넌트를 렌더링하면서 토큰체크
    let accessToken = this.$store.getters.accessToken;
    let refreshToken = this.$store.getters.refreshToken;
    if (accessToken === null && refreshToken === null) { //다 없으면 로그인 페이지로
      //이미 로그인 페이지가 떠있는 상태에서 새로 고침하면 중복 에러 떠서 이렇게 처리함
      this.$router.push({name: 'Login'}).catch(() => {}); 
    } else if ( accessToken===null && refreshToken !==null) {
      this.reissueToken()
    }
  },
  methods: {
    ...mapActions(['reissueToken'])

  },
})
</script>

<style>
@import url('https://fonts.googleapis.com/css2?family=IBM+Plex+Sans+KR:wght@300;500&display=swap');

* { 
  font-family: 'IBM Plex Sans KR', sans-serif;
  font-weight: 400;
} 

:root {
  --el-color-primary: #F16B51 !important;
}

* {
  box-sizing: border-box;
  margin: 0;
}

body {
  padding-bottom: 3rem;
  margin: 0;
}

#app {
  max-width: 500px;
  max-height: 740px;
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  /* text-align: center; */
  color: #2c3e50;
}

nav {
  padding: 30px;
}

nav a {
  font-weight: bold;
  color: #2c3e50;
}

nav a.router-link-exact-active {
  color: #42b983;
}
</style>
