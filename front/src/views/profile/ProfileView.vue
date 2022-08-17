<template>
  <div class="container">
    <div class="background">
      <div class="profile-picture">
        <el-avatar :size="90">
          <img :src="theirProfile.img_link" alt="user">
        </el-avatar>
      </div>
      <!-- {{ theirProfile }} -->
    </div>

    <!-- 이름 / 팔로우 버튼  -->
    <div class="background-info">
      <div class="blank"></div>
      <div class="username-follow">
          <!-- 내 프로필 페이지라면 팔로우 버튼 안뜸 -->
          <!-- 만약 fetchCurrentUser의 id와 profile param의 id가 같다면 -->
          <div class="my-page-username" v-if="isMyProfile">
            <h2>{{ theirProfile.name }}</h2>
            <el-button class="button" link @click="goProfileEdit()">
              <span class="material-symbols-outlined icon">edit</span>
            </el-button>
          </div>
          <!-- 남의 프로필 페이지라면 팔로우 버튼 뜸-->
          <div class="my-page-username" v-else>
            <h2>{{ theirProfile.name }}</h2>
            <div class="follow-button">
              <el-button v-if="!isFollow" type="primary" @click="followNow(), follow(followId)">팔로우</el-button>
              <el-button v-else type="primary" plain @click="unfollowNow(), unfollow(followId)">팔로잉</el-button>
            </div>
        </div>
      </div>
    </div>
    

    <!-- 팔로워 / 팔로잉 -->
    <div class="follow">
      <div class="user-follow" @click="followerClicked=true">
        <span class="follow-title">Followers</span>
        <span>{{ followerCount }}</span>
      </div>
      <div>
        <span>|</span>
      </div>
      <div class="user-follow" @click="followingClicked=true">
        <span class="follow-title">Followings</span>
        <span>{{ followingCount }}</span>
      </div>
    </div>
    

    <!-- 자기소개 -->
    <div class="description">
      <p v-if="isMyProfile">{{ theirProfile.description }}</p>
      <p v-else>{{ theirProfile.description }}</p>
    </div>
    

    <!-- followers 창 열기-->
    <el-drawer v-model="followerClicked" direction="btt" size="50%">
      <template #header>
        <h2>팔로워</h2>
      </template>
      <template #default>
        <followers-list :isMyProfile="isMyProfile"/>
      </template>
    </el-drawer>

    <!-- following 창 열기 -->
    <el-drawer v-model="followingClicked" direction="btt" size="50%">
      <template #header>
        <h2>팔로잉하는 사람</h2>
      </template>
      <template #default>
        <followings-list :isMyProfile="isMyProfile"/>
      </template>
    </el-drawer>






    <!-- 내 다이어리 / 내 좋아요 / 내 동행찾기 -->
    <!-- vue warn 나서 일단 이거 뺴놓음  -->
    <!-- @tab-click="handleClick" -->
    <el-tabs
    v-if="isMyProfile"
    v-model="activeName"
    type="card"
    class="demo-tabs"
    
    >
      <el-tab-pane label="My Diary">
        <my-diaries-list></my-diaries-list>
      </el-tab-pane>
      <el-tab-pane label="My Likes">
      </el-tab-pane>
      <el-tab-pane label="My Companions">
      </el-tab-pane>
    </el-tabs>


    <!-- 남의 페이지라면 다이어리 목록만 노출-->
    <el-tabs
    v-else
    v-model="activeName"
    type="card"
    class="demo-tabs"
    >
      <el-tab-pane label="My Diary">
        <!-- 상대방이 쓴 일지 목록 -->
        <my-diaries-list></my-diaries-list>
      </el-tab-pane>
    </el-tabs>


  </div>
</template>

<script>
import FollowersList from '@/components/profile/FollowersList.vue'
import FollowingsList from '@/components/profile/FollowingsList.vue'
import MyDiariesList from '@/components/profile/MyDiariesList.vue'
import { mapActions, mapGetters } from 'vuex'

export default {
  name: 'ProfileView',
  components: {
    FollowersList,
    FollowingsList,
    MyDiariesList,
  },
  data() {
    return {
      // 현재 들어온 프로필 주인 아이디(문자열)
      currentProfile: this.$route.params.authorId,
      followId: {
        follower_id: null,
        following_id: null,
      },
      isFollow: false,
      isMyProfile: true,
      followerList: [],
      followingList: [],
      activeName: '',
      followerClicked: false,
      followingClicked: false,
    }
  },
  computed: {
    ...mapGetters(['currentUser', 'profile', 'myDiaries', 'theirProfile', 'followingStatus', 'followerCount', 'followingCount']),
    // fetchTheirId() {
    //   const memberId = this.$route.params.authorId
    //   return memberId
    // },
    fetchAuthorId() {
      return this.$route.params.authorId
    }
  },
  watch: {
    followingStatus(newVal) {
      this.isFollow = newVal
    },
    // isMyProfile(newVal) {
    //   console.log('프로필 이동', this.currentProfile)
    //   this.currentProfile = newVal
    //   this.myProfile()
    // },
    fetchAuthorId(newVal) {
      console.log(newVal)
      this.fetchTheirProfile(this.$route.params.authorId)
      console.log('프로필 이동', this.currentProfile)
      // this.currentProfile = newVal
      this.myProfile()
    }
  },
  methods: {
    ...mapActions([
      'fetchCurrentUser',
      'fetchProfile', 
      'fetchMyDiary', 
      'fetchTheirProfile', 
      'follow', 
      'unfollow',
      'myFollowers',
      'myFollowersCount',
      'myFollowings',
      'myFollowingsCount',
      'yourFollowers',
      'yourFollowersCount',
      'yourFollowings',
      'yourFollowingsCount',
      'setFollowingStatus',
      ]),

    goProfileEdit(){
      this.$router.push({ name: 'profileEdit' })
    },

    // 내 프로필인지 확인
    myProfile(){
      console.log(this.currentUser.id, this.$route.params.authorId)
      if ( this.currentUser.id == this.currentProfile ) { 
        this.isMyProfile = true
        this.fetchMyDiary()
      } else {
        this.isMyProfile = false
      }
      console.log('여기', this.isMyProfile)
      this.fetchFollow()
    },

    // 팔로워, 팔로잉 불러오기
    fetchFollow(){
      // 내 프로필이면
      if (this.isMyProfile) {
        console.log('내 프로필')
        this.myFollowers(this.currentProfile)
        this.myFollowings(this.currentProfile)
      }
      // 내 프로필이 아니면
      else {
        console.log('내 프로필 아님')
        this.yourFollowers(this.currentProfile)
        this.yourFollowings(this.currentProfile)
      }
    },

    followNow() {
      this.isFollow = !this.isFollow
      // follower_id - 내가 팔로우 하게 되는 사람의 아이디
      // following_id - 내 아이디
      this.followId.follower_id = this.profile.id
      this.followId.following_id = this.theirProfile.id
    },
    unfollowNow() {
      this.isFollow = !this.isFollow
      // follower_id - 내가 언팔로우 하게 되는 사람의 아이디
      // following_id - 내 아이디
      this.followId.follower_id = this.profile.id
      this.followId.following_id = this.theirProfile.id
    },
  },
  created() {
    // 현재 로그인한 유저 정보 저장
    this.fetchCurrentUser()
    // 현재 들어간 프로필 유저 정보 저장
    this.fetchTheirProfile(this.currentProfile)
  },
  mounted() {
    // 내 프로필인지 확인 => 정보 저장하는 시간이 필요함
    setTimeout(() => this.myProfile(), 100)
    this.fetchProfile()
    // this.fetchTheirProfile(this.$route.params.authorId)
    // this.myFollowings()
    // this.myFollowers()
    this.setFollowingStatus(this.$route.params.authorId)
    this.fetchMyDiary()
    // this.fetchFollow()
    // setTimeout(() => this.fetchTheirProfile(this.$route.params.authorId), 500)
  },
  updated() {
      // this.fetchTheirProfile(this.$route.params.authorId)
  }
}
</script>

<style scoped>
  * {
    box-sizing: border-box;
    margin: 2%;
    }

  .container {
    display: flex;
    flex-direction: column;
    margin: 0;
  }
  
  .background {
    display: relative;
    background-color: bisque;
    width: 100%;
    height: 15vh;
    padding: 0;
    margin: 0;
  }

  .background-info {
    display: flex;
    margin: 0;
  }

  .username-follow {
    width: 50vw;
    height: 2.2rem;
  }

  .follow-button {
    margin-left: 5%;
  }

  .followers {
    display: flex;
    justify-content: flex-start;
    margin: 0;
    padding: 0;
    height: 10%;
  }

  .user-info {
    margin: 0;
  }

  .profile-picture {
    display: absolute;
    margin-top: 20%;
    margin-right: 65%;
  }

  .my-page-username {
    display: flex;

  }

  .my-page-username h2 {
    font-weight: 500;
    margin-right: 0.5rem;
  }

  .material-symbols-outlined {
    font-size: 1.3rem;
    color: #F16B51;
  }

  .user-follow {
    margin-left: 0;
    margin-right: 0;
    width: 4.5rem;
  }

  .user-follow span {
    font-size: 0.8rem;
    font-weight: 400;
  }

  .follow-title {
    color: #F16B51;
  }

  .blank {
    width: 25%;
  }

  .description {
    display: flex;
  }

  .follow {
    display: flex;
    margin-left: 7.5rem;
    margin-top: 0;
  }

</style>