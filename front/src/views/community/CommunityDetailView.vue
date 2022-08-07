<template>
  <div>
    <div class="header">
      <div class="tags">
        <el-tag class="tag">{{ temp.category }}</el-tag>
        <el-tag class="tag">장소</el-tag>
      </div>
      <router-link class="router" :to="{ name: 'profile' }">
        <div class="profile">
          <el-avatar class="profile-image" :size="40" src="" />
          <span class="username">나유저</span>
        </div>
      </router-link>
      <hr>
    </div> 
    <div class="title">
      <span class="state">{{ recruitState }}</span>
      <h4>{{ temp.title }}</h4>
    </div>
    <div class="options">
      <p class="option">
        <span class="material-symbols-outlined">groups</span>
        {{ temp.option.age[0] }}~{{ temp.option.age[1] }}세 | {{ temp.option.gender }}
      </p>
      <p class="option">
        <span class="material-symbols-outlined">event_note</span>
        {{ convertDate }}, {{ temp.time }}
      </p>
      <p class="option">
        <span class="material-symbols-outlined">location_on</span>
        {{ temp.place }}
      </p>
    </div>
    
    <div class="content">
      <p class="description">{{ this.temp.desc }}</p>
      <hr>
    </div>
    <div class="members">
      <p class="member-count">
        <span>{{ recruitCount }}</span>
        / {{ temp.recruit_volume }}명 참여
      </p>
      <div class="users">
        <div class="user">
          <el-avatar :size="40" src="" />
          <span>나유저</span>
        </div>
        <!-- <div class="user">
          <el-avatar :size="40" src="" />
          <span>나유저</span>
        </div> -->
      </div>
    </div>
    <div class="participation">
      <el-button class="button">참가하기</el-button>
    </div>
  </div>
</template>

<script>
import { mapGetters } from 'vuex'

export default {
    name: 'CommunityDetailView',
    data() {
      return {
        isBookmark: true
      }
    },
    computed: {
      ...mapGetters(['temp']),
      recruitState() {
        return '모집중'
      },
      recruitCount() {
        return 3
      },
      convertDate() {
        let date = ''
        if (!this.temp.isDay) {
          date = this.temp.start_date + '~' + this.temp.end_date
        } else {
          date = this.temp.start_date
        }
        return date
      },
    },
  }
</script>

<style scoped>
* {
  box-sizing: border-box;
  margin: 0;
}

hr {
  border: 0;
  height: 0;
  border-top: 1px solid #d9d9d9;
}

.header {
  padding: 0.5rem;
}

.tags {
  text-align: left;
}

.tag {
  margin-right: 0.3rem;
  margin-bottom: 1rem;
}

.router {
  text-decoration: none;
}

.profile {
  display: flex;
  align-items: center;
  margin-bottom: 1rem;
}

.profile-image {
  margin-right: 0.3rem;
}

.username {
  font-weight: 400;
}

.title {
  display: flex;
  align-items: center;
  padding: 0.5rem;
  font-size: 1.2rem;
}

.state {
  font-weight: bold;
  color: #F16B51;
  margin-right: 0.3rem;
}

.options {
  padding: 0.5rem;
  font-size: 0.9rem;
}

.option {
  display: flex;
  align-items: center;
  margin-bottom: 0.3rem;
}

.option > span {
  margin-right: 0.3rem;
}

.content {
  padding: 0.5rem;
}

.description {
  text-align: left;
  margin-bottom: 1rem;
}

.members {
  padding: 0.5rem;
}

.member-count {
  text-align: left;
  font-size: 0.9rem;
  font-weight: 400;
  margin-bottom: 0.3rem;
}

.member-count > span {
  color: #F16B51;
  font-weight: 400;
}

.users {
  display: flex;
  align-items: center;
}

.user {
  display: flex;
  flex-direction: column;
  justify-content: center;
  margin-right: 0.3rem;
}

.user > span {
  font-size: 0.8rem;
  font-weight: 400;
}

.participation {
  display: flex;
  justify-content: center;
}

.button {
  position: fixed;
  width: 90%;
  bottom: 1rem;
}


</style>