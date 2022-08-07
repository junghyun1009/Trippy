<template>
  <div class="container">
    <div class="user-info">
      <div class="profile-picture">
        <el-avatar :size="90" class="user-info"> user </el-avatar>
      </div>
      <div class="username-follow">
        <h2>username</h2>
        <div class="user-follow">
          <div class="user-follow">
            <followers-list/>
          </div>
          <div class="user-follow">
            <followings-list/>
          </div>
        </div>
      </div>
    </div>
    
    <el-button v-if="isFollow===false" type="primary" @click="followNow()">팔로우</el-button>
    <el-button v-else type="primary" plain @click="unfollowNow()">팔로잉</el-button>
    <div class="user-info">
      <p>소개글</p>

    </div>
    <el-tabs
    v-model="activeName"
    type="card"
    class="demo-tabs"
    @tab-click="handleClick"
    >
      <el-tab-pane label="My Diary">
        <!-- 내가 쓴 일지 목록 -->
      </el-tab-pane>
      <el-tab-pane label="My Likes">
        <!-- 내가 좋아요 누른 일지 목록 -->
      </el-tab-pane>
      <el-tab-pane label="My Companions">
        <!-- 내가 북마크한 동행 찾기 목록 -->
      </el-tab-pane>
    </el-tabs>
  </div>
</template>

<script>
import FollowersList from '@/components/profile/FollowersList.vue'
import FollowingsList from '@/components/profile/FollowingsList.vue'

export default {
  name: 'ProfileView',
  components: {
    FollowersList,
    FollowingsList,
  },
  data() {
    return {
      isFollow: false,
      followerList: [],
      followingList: [],
    }
  },
  methods: {
    followNow() {
      this.isFollow = !this.isFollow
    },
    unfollowNow() {
      this.isFollow = !this.isFollow
    }
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

.user-follow {
  margin: 0;
}

.user-info {
  display: flex;
}

.user-follow {
  display: flex;
  justify-content: flex-start;
}


.user-follow:first-child {
  margin-right: 15px;
}


</style>