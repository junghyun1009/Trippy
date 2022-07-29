<template>
  <div class="home">
    <search-bar></search-bar>
    <region-list></region-list>
    <recommend-list></recommend-list>
    
    <br>
    <br>

    <router-link :to="{ name: 'diaryCreate' }">Create Diary</router-link><br>
    <router-link :to="{ name: 'login' }">로그인</router-link><br>
    <router-link :to="{ name: 'passwordChange' }">비밀번호변경</router-link>
    <!-- <router-link :to="{ name: 'profile' }">Profile</router-link> -->
  </div>
</template>

<script>
// @ is an alias to /src
import SearchBar from '@/components/common/SearchBar.vue'
import RecommendList from '@/components/home/RecommendList.vue'
import RegionList from '@/components/common/RegionList.vue'

export default {
  name: 'HomeView',
  components: {
    SearchBar,
    RecommendList,
    RegionList,
  }
}
</script>
