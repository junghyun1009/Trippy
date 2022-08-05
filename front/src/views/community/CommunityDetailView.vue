<template>
  <div>
    <div class="header">
      <div>
        <el-tag class="tag">{{ temp.category }}</el-tag>
        <el-tag class="tag">장소</el-tag>
      </div>
      <router-link class="router" :to="{ name: 'profile' }">
        <div class="profile">
          <el-avatar class="profile-image" :size="40" src="" />
          <span>나유저</span>
        </div>
      </router-link>
      <hr>
    </div> 
    <div class="title">
      <span class="state">{{ recruitState }}</span>
      <h4>{{ temp.title }}</h4>
    </div>
    <div class="option">
      <p class="icon">
        <span class="material-symbols-outlined">groups</span>
        {{ temp.option.age[0] }}~{{ temp.option.age[1] }}세 | {{ temp.option.gender }}
      </p>
      <p class="icon">
        <span class="material-symbols-outlined">event_note</span>
        {{ convertDate[0] }} ~ {{ convertDate[1] }}, {{ convertTime }}
      </p>
      <p class="icon">
        <span class="material-symbols-outlined">location_on</span>
        {{ temp.place }}
      </p>
    </div>
    
    <div class="content">
      <p class="description">{{ this.temp.desc }}</p>
      <hr>
    </div>
    <div class="member">
      <p class="member-count">
        <span>{{ recruitCount }}</span>
        / {{ temp.recruit_volume }}명 참여
      </p>
      <el-avatar :size="40" src="" />
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
      optionTag() {
        const gender = this.temp.option.gender
        const start_age = this.temp.option.age[0]
        const end_age = this.temp.option.age[1]
        let age = start_age + '~' + end_age + '살'
        if (start_age === undefined && end_age === undefined || start_age === 19 && end_age === 50) {
          age = '누구나'
        }
        const isLocal = this.temp.option.isLocal
        let local = ''
        if (isLocal === false) {
          local = '어디서나'
        } else {
          local = '같은 지역만'
        }
        return [gender, age, local]
      },
      convertDate() {
        const months = {'Jan': 1, 'Feb': 2, 'Mar': 3, 'Apr': 4, 'May': 5, 'Jun': 6, 'Jul': 7, 'Aug': 8, 'Sep': 9, 'Oct': 10, 'Nov': 11, 'Dec': 12}
        const start_year = this.temp.date[0].substr(11, 4)
        let start_month = this.temp.date[0].substr(4,3)
        const start_day = this.temp.date[0].substr(8,2)
        const end_year = this.temp.date[1].substr(11, 4)
        let end_month = this.temp.date[1].substr(4,3)
        const end_day = this.temp.date[1].substr(8,2)
        if (start_month in months || end_month in months) {
          start_month = months[start_month]
          end_month = months[end_month]
        }
        
        const start_date = start_year + '-' + start_month.toString().padStart(2, '0') + '-' + start_day
        const end_date = end_year + '-' + end_month.toString().padStart(2, '0') + '-' + end_day
        return [start_date, end_date]
      }
    },
}
</script>

<style scoped>
* {
  text-align: left;
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

.option {
  padding: 0.5rem;
  font-size: 0.9rem;
}

.icon {
  display: flex;
  align-items: center;
  margin-bottom: 0.3rem;
}

.icon > span {
  margin-right: 0.3rem;
}

.content {
  padding: 0.5rem;
}

.description {
  margin-bottom: 1rem;
}

.member {
  padding: 0.5rem;
}

.member-count {
  font-size: 0.9rem;
  font-weight: 400;
  margin-bottom: 0.3rem;
}

.member-count > span {
  color: #F16B51;
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