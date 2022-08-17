<template>
  <div class="home">
    <div class="diary-list">
      <search-bar></search-bar>
      <region-list></region-list>
      <recommend-list></recommend-list>
    </div>

    <el-divider />

    <div class="following-diary-list">
      <div v-if="isLoggedIn" class="following-diary-description">
        <p>{{ currentUser.name }}님이 팔로우 하고 있는</p>
        <p><span>{{ this.followingName.name }}</span> 님이 추천하는 코스</p>
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
  // watch: {
  //   followingName(newVal) {
  //     console.log(newVal)
  //     this.followingIdforDiary = newVal.id 
  //     console.log(this.followingIdforDiary)
  //   }
  // },
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
  },
  mounted() {
    if (localStorage.getItem('reloaded')) {
        localStorage.removeItem('reloaded');
    } else {
        localStorage.setItem('reloaded', '1');
        location.reload();
    }
    this.fetchCurrentUser()
    this.myFollowings()
    setTimeout(() => {
      this.randomFunction()
    }, 100);
  },
  }

</script>


<style scoped>
* {
  padding:0;
  margin:0;
  }

body{
	font-family:Verdana, Geneva, sans-serif;
	font-size:18px;
	background-color:#CCC;
}

.following-diary-description {
  display: flex;
  flex-direction: column;
  align-items: flex-start;
  margin: 0 2%;
  margin-bottom: 3%;;
}

.following-diary-description p {
	font-size: 2.5vh;
	font-weight: 500;
}

.following-diary-description span {
  color: #F16B51;
	font-size: 2.5vh;
	font-weight: 500;
}

.following-diary-list {
  /* display: flex; */
  margin: 0 2%;
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

.image {

}

.my-float{
	margin-top:22px;
}

</style>