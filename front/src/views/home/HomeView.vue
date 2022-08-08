<template>
  <div class="home">
    <search-bar></search-bar>
    <region-list></region-list>
    <recommend-list v-for="diary in diaries" :key="diary.id" :diary="diary"></recommend-list>

    <p>팔로우 하고 있는</p>
    <p>ㅇㅇㅇ 님이 추천하는 코스</p>
    <recommend-list></recommend-list>
    
    <br>
    <br>

    <router-link :to="{ name: 'diaryCreate' }">Create Diary</router-link><br>
    <router-link :to="{ name: 'login' }">로그인</router-link><br>
    <router-link :to="{ name: 'passwordChange' }">비밀번호변경</router-link>
    <!-- <router-link :to="{ name: 'profile' }">Profile</router-link> -->

    <br><br><br>



  </div>
</template>

<script>
// @ is an alias to /src
import SearchBar from '@/components/common/SearchBar.vue'
import RecommendList from '@/components/home/RecommendList.vue'
import RegionList from '@/components/common/RegionList.vue'
import { mapGetters, mapActions } from 'vuex'

export default {
  name: 'HomeView',
  components: {
    SearchBar,
    RecommendList,
    RegionList,
  },
  created() {
    this.showAllDiary()
  },
  computed: {
    ...mapGetters(['GET_ALL_DIARY']),
    diaries() {
      return this.$store.state.diaries
    }
  },
  methods: {
    ...mapActions(['showAllDiary'])
    }
  }

</script>
<style>
:root {
  --el-color-primary: #F16B51;
}
</style>

<style scoped>
*{padding:0;margin:0;}

body{
	font-family:Verdana, Geneva, sans-serif;
	font-size:18px;
	background-color:#CCC;
}

.float{
	position:fixed;
	width:60px;
	height:60px;
	bottom:40px;
	right:40px;
	background-color:#F16B51;
	color:#FFF;
	border-radius:50px;
	text-align:center;
	box-shadow: 2px 2px 3px #999;
}

.my-float{
	margin-top:22px;
}
</style>