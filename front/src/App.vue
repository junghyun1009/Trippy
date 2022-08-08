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
export default ({
  name: 'App',
  components: {
    TheHeader,
    NavBar
  },
  methods: {
    // notShowLoginJoin() {
    //   this.$route.name !== 'login'
    //   this.$route.name !== 'signUp'
    // }
  }
})
</script>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
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
