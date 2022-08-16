<template>
  <div class="container">
      <div class="tags">
        <el-tag class="tag">{{ post.cityName}}</el-tag>
        <el-tag class="tag">{{ post.category === 1 ? '식사' : post.category === 2 ? '동행' : post.category === 3 ? '파티' : post.category === 4 ? '이동수단 셰어' : '기타' }}</el-tag>
      </div>
    <div class="header">
      <router-link class="router" :to="{ name: 'profile' }">
        <div class="profile">
          <el-avatar class="profile-image" :size="40" src="" />
          <span class="username">{{ post.name }}</span>
        </div>
      </router-link>
      <div v-if="!isPostAuthor" class="bookmark">
        <span v-if="post.bookmark===false" class="material-symbols-outlined" @click="goBookmark">bookmark_add</span>
        <span v-else class="material-symbols-outlined filled" @click="cancelBookmark">bookmark</span>
      </div>
      <div v-if="isPostAuthor">
        <edit-delete-button class="edit-delete"></edit-delete-button>
      </div>
    </div> 
    <hr>
    <div class="title">
      <span class="state">{{ recruitState }}</span>
      <h4>{{ post.title }}</h4>
    </div>
    <div class="options">
      <p class="option">
        <span class="material-symbols-outlined">groups</span>
        {{ post.startAge === post.endAge ? post.startAge : `${post.startAge}~${post.endAge}`}}세 | {{ post.gender }}
      </p>
      <p class="option">
        <span class="material-symbols-outlined">event_note</span>
        <span>{{ post.endDate ? `${post.startDate.slice(5, 10)}~${post.endDate.slice(5,10)}` : ''+post.startDate.slice(5, 10) }},</span>
        <span>{{ ''+post.meetingTime.slice(11, 16) }}</span>
      </p>
      <p class="option">
        <span class="material-symbols-outlined">location_on</span>
        {{ post.place }}
      </p>
    </div>
    
    <div class="content">
      <p class="description">{{ post.description }}</p>
      <hr>
    </div>
    <div class="members">
      <p class="member-count">
        <span>{{ post.recruitCurrentVolume }}</span>
        / {{ post.recruitVolume }}명 참여
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
import { mapGetters, mapActions } from 'vuex'
import EditDeleteButton from '@/components/common/EditDeleteButton.vue'

export default {
  name: 'CommunityDetailView',
  components: {
    EditDeleteButton
  },
  data() {
    return {
      // isBookmark: false,
      postPk: this.$route.params.postPk
    }
  },
  computed: {
    ...mapGetters(['post', 'isPostAuthor']),
    recruitState() {
      return '모집중'
    },
    convertTag() {
      const category = this.post.category
      const categoryList = ['식사', '동행', '파티', '이동수단 셰어', '기타']
      return categoryList[category-1]
    },
    // convertDate() {
    //   let date = ''
    //   if (!this.post.isDay) {
    //     date = this.post.startDate.substr(5,5) + '~' + this.post.endDate.substr(5,5)
    //   } else {
    //     date = this.post.startDate.substr(5,5)
    //   }
    //   return date
    // },
    // convertTime() {
    //   let time = ''
    //   time = this.post.meetingTime.substr(11,5)
    //   return time
    // }
  },
  methods: {
    ...mapActions(['fetchPost', 'fetchCurrentUser', 'fetchBookmark', 'createBookmark', 'deleteBookmark', 'checkBookmark']),
    goBookmark() {
      this.checkBookmark(this.postPk)
      this.createBookmark(this.postPk)
      // this.switchIsBookmark()
      // this.isBookmark = true
      // console.log(1, this.isBookmark)
    },
    cancelBookmark() {
      this.checkBookmark(this.postPk)
      this.deleteBookmark(this.postPk)
      // this.switchIsBookmark()
      // console.log(2, this.isBookmark)
    },
    // switchIsBookmark() {
    //   this.isBookmark = !this.isBookmark
    // }
  },
  created() {
    this.checkBookmark(this.postPk)
    this.fetchCurrentUser()
    this.fetchPost(this.postPk)
    // this.fetchBookmark()
  },
  // mounted() {
    
  // },
  // updated() {
  //   this.checkBookmark(this.postPk)
  // }
}
</script>

<style scoped>
* {
  box-sizing: border-box;
  margin: 0;
}

.container {
  padding: 1rem;
}

hr {
  border: 0;
  height: 0;
  border-top: 1px solid #d9d9d9;
}

.header {
  display: flex;
  justify-content: space-between;
  align-items: center;
  position: relative;
  padding: 0.5rem;
}

.tags {
  text-align: left;
}

.tag {
  margin-right: 0.3rem;
  margin-bottom: 1rem;
}

.filled {
  font-variation-settings:
  'FILL' 1,
  'wght' 400,
  'GRAD' 0,
  'opsz' 48
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

.edit-delete {
  position: absolute;
  top: 1rem;
  right: 1rem;
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