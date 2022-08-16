<template>
  <div class="container">
    <div class="background">
      <div class="profile-picture">
        <!-- {{ profile }} -->
        <el-avatar :size="90"> user </el-avatar>
      </div>
    </div>
    <div class="background-info">
      <div class="blank"></div>
      <div class="username-follow">
          <!-- 내 프로필 페이지라면 팔로우 버튼 안뜸 -->
          <!-- 만약 fetchCurrentUser의 id와 profile param의 id가 같다면 -->
          <div class="my-page-username" v-if="isMyProfile">
            <h2>{{ profile.name }}</h2>
            <el-button class="button" link @click="goProfileEdit()">
              <span class="material-symbols-outlined icon">edit</span>
            </el-button>
          </div>
          <!-- 남의 프로필 페이지라면 팔로우 버튼 뜸-->
          <div class="their-page-username" v-else>
            <h2>{{ theirProfile.name }}</h2>
            <div class="follow-button">
              <el-button v-if="!isFollow" type="primary" @click="followNow(), follow(followId)">팔로우</el-button>
              <el-button v-else type="primary" plain @click="unfollowNow(), unfollow(followId)">팔로잉</el-button>
            </div>
        </div>
      </div>
    </div>
    

    <div class="description">
      <p v-if="isMyProfile">{{ profile.description }}</p>
      <p v-else>{{ theirProfile.description }}</p>
    </div>
    
    <div class="followers">
      <div class="user-follow">
        <followers-list/>
      </div>
      <div class="user-follow">
        <followings-list/>
      </div>
    </div>
    
    <!-- vue warn 나서 일단 이거 뺴놓음  -->
    <!-- @tab-click="handleClick" -->
    <el-tabs
    v-if="isMyProfile"
    v-model="activeName"
    type="card"
    class="demo-tabs"
    
    >
      <el-tab-pane label="My Diary">
        <!-- 내가 쓴 일지 목록 -->
        <my-diaries-list></my-diaries-list>
      </el-tab-pane>
      <el-tab-pane label="My Likes">
        <!-- 내가 좋아요 누른 일지 목록 -->
      </el-tab-pane>
      <el-tab-pane label="My Companions">
        <!-- 내가 북마크한 동행 찾기 목록 -->
      </el-tab-pane>
    </el-tabs>


    <!-- 남의 페이지라면 -->
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
      followId: {
        follower_id: null,
        following_id: null,
      },
      isFollow: false,
      isMyProfile: true,
      followerList: [],
      followingList: [],
      activeName: '',
    }
  },
  computed: {
    ...mapGetters(['profile', 'myDiaries', 'theirProfile', 'followingStatus']),
    fetchTheirId() {
      const memberId = this.$route.params.authorId 
      return memberId
    },
  },
  watch: {
    followingStatus(newVal) {
      this.isFollow = newVal
    }
  },
  methods: {
    ...mapActions([
      'fetchProfile', 
      'fetchMyDiary', 
      'fetchTheirProfile', 
      'follow', 
      'unfollow',
      'myFollowers',
      'myFollowersCount',
      'myFollowings',
      'myFollowingsCount',
      'setFollowingStatus',
      ]),

    goProfileEdit(){
      this.$router.push({ name: 'profileEdit' })
    },

    myProfile(){
      console.log(this.theirProfile)
      if ( this.profile.id === this.theirProfile.id ) { 
        this.isMyProfile = true
        this.fetchMyDiary()
      } else {
        this.isMyProfile = false
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
    // isFollowed() {
    //   console.log( this.followingStatus)
    //   console.log( '버튼true?', this.isFollow)
    //   if ( this.followingStatus === true ) {
    //     this.isFollow = true
    //     console.log( '버튼true????', this.isFollow)
    //   } else { this.isFollow = false }
    // },
  },
  mounted() {
    this.fetchProfile()
    this.fetchTheirProfile(this.$route.params.authorId)
    this.myFollowings()
    this.myFollowers()
    this.setFollowingStatus(this.$route.params.authorId)
    this.myProfile()
    // this.isFollowed()
    this.fetchMyDiary()
 },
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

  .user-follow:first-child {
    margin-right: 15px;
  }

  .blank {
    width: 25%;
  }

  .description {
    display: flex;
  }

</style>