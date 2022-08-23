<template>
  <div class="home">
    <div class="diary-list">
      <search-bar @click="goSearch"></search-bar>
      <div class="regional-menu">
        <h3>지금 <span class="logo">Trippy</span>에서 핫한 여행코스</h3>
      </div>
      <region-list></region-list>
      <div class="recommend-menu">
        <h3><span class="logo">Trippy</span>여행자들의 생생한 여행 후기</h3>
      </div>
      <recommend-list></recommend-list>
    </div>


    <el-divider />

    <div class="following-diary-list">
      <div v-if="isLoggedIn" class="following-diary-description">
        <h3>{{ currentUser.name }}님이 팔로우 하고 있는</h3>
        <h3><span>{{ this.followingName.name }}</span>님이 추천하는 코스</h3>
        <following-diary-list v-if="this.followingIdforDiary" :followingIdforDiary="followingIdforDiary"></following-diary-list>
      </div>

    </div>

    <br>
    <br>
    <br>

    <br>

  </div>
</template>

<script>
// @ is an alias to /src
import SearchBar from '@/components/common/SearchBar.vue'
import RecommendList from '@/components/home/RecommendList.vue'
import FollowingDiaryList from '@/components/home/FollowingDiaryList.vue'
import RegionList from '@/components/common/RegionList.vue'
import { mapGetters, mapActions } from 'vuex'

export default {
  name: 'HomeView',
  data() {
    return {
      followingName: {},
      followingIdforDiary: '',
    }
  },
  watch: {
    followingList: {
      deep: true,
      handler: 'randomFunction'
    }
  },
  components: {
    SearchBar,
    RecommendList,
    RegionList,
    FollowingDiaryList,
  },
  computed: {
    ...mapGetters(['isLoggedIn', 'followingList', 'currentUser']),
  },
  methods: {
    ...mapActions(['myFollowings', 'fetchCurrentUser', 'saveFollowingId']),
    randomFunction(){
      const followingList = this.followingList
      var following = followingList[Math.floor(Math.random()*followingList.length)]
      this.followingName = following
      console.log('followingName', this.followingName)
      this.followingIdforDiary = this.followingName.id
      this.saveFollowingId(this.followingIdforDiary)
    },
    goSearch() {
      this.$router.push({ name: 'search' })
    }
  },
  created() {
    if (localStorage.getItem('reloaded')) {
        localStorage.removeItem('reloaded');
    } else {
        localStorage.setItem('reloaded', '1');
        location.reload();
    }

  },
  mounted() {
    this.fetchCurrentUser()
    this.myFollowings()
    // setTimeout(() => {
    //   this.randomFunction()
    // }, 100);
  },
}
</script>


<style scoped>
@import url('https://fonts.googleapis.com/css2?family=Alfa+Slab+One&display=swap');

* {
  padding:0;
  margin:0;
  }

body{
	font-family:Verdana, Geneva, sans-serif;
	font-size: 18px;
	background-color:#CCC;
}

.logo {
  font-family: 'Alfa Slab One', cursive;
  font-size: 1.5em;
  margin-right: 0.1rem;
}

.regional-menu {
  padding: 1rem;
}

.regional-menu h3 {
	font-size: 1rem;
	font-weight: bold;
}

.regional-menu span {
	color: #F16B51;
	font-size: 1.2rem;
	font-weight: bold;
}

.recommend-menu {
  padding: 1.5rem 1rem 1rem 1rem;
  margin-top: 0.5rem;
}

.recommend-menu h3 {
	font-size: 1rem;
	font-weight: bold;
}

.recommend-menu span {
	color: #F16B51;
	font-size: 1rem;
	font-weight: bold;
}


.following-diary-description {
  display: flex;
  flex-direction: column;
  align-items: flex-start;
  margin: 0 2%;
  margin-bottom: 3%;;
}

.following-diary-description h3 {
	font-size: 1.2rem;
	font-weight: bold;
}

.following-diary-description span {
  color: #F16B51;
	font-size: 1.2rem;
	font-weight: bold;
}

.following-diary-list {
  /* display: flex; */
  margin: 0 2%;
}

</style>