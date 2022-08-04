<template>
  <div>
    <h3>동행 찾기</h3>
    <span @click="getLocation()">현재 위치</span>

    <router-link :to="{ name: 'communityDetail' }">
      <!-- v-for 추가예정 -->
      <el-card>
        <div class="header">
          <h4>{{ temp.title }}</h4>
          <el-tag>장소</el-tag>
        </div>
        <div class="content">
          <p>{{ convertDate[0] }} ~ {{ convertDate[1] }}</p>
          <p>{{ temp.desc }}</p>
        </div>
        <div class="footer">
          <span>{{ recruitState }} {{ recruitCount}} / {{ temp.recruit_volume }}</span>
        </div>
      </el-card>
    </router-link>
  </div>
</template>

<script>
import { mapGetters } from 'vuex'
export default {
    name: 'CommunityView',
    data() {
      return {
        position: {},
        
      }
    },
    computed: {
      ...mapGetters(['temp']),
      recruitState() {
        return '모집 중'
      },
      recruitCount() {
        return 3
      },
      convertDate() {
        const months = {'Jan': 1, 'Feb': 2, 'Mar': 3, 'Apr': 4, 'May': 5, 'Jun': 6, 'Jul': 7, 'Aug': 8, 'Sep': 9, 'Oct': 10, 'Nov': 11, 'Dec': 12}
        const start_year = this.temp.dateTime[0].substr(11, 4)
        let start_month = this.temp.dateTime[0].substr(4,3)
        const start_day = this.temp.dateTime[0].substr(8,2)
        const end_year = this.temp.dateTime[1].substr(11, 4)
        let end_month = this.temp.dateTime[1].substr(4,3)
        const end_day = this.temp.dateTime[1].substr(8,2)
        if (start_month in months || end_month in months) {
          start_month = months[start_month]
          end_month = months[end_month]
        }
        
        const start_date = start_year + '-' + start_month.toString().padStart(2, '0') + '-' + start_day
        const end_date = end_year + '-' + end_month.toString().padStart(2, '0') + '-' + end_day
        return [start_date, end_date]
      }
    },
    methods: {
      getLocation() {
        if (navigator.geolocation) {
          navigator.geolocation.getCurrentPosition(this.getLocationValue)
          console.log(this.position)
        } else {
          alert('위치 정보를 찾을 수 없습니다.')
        }
      },
      getLocationValue(position) {
        this.position.latitude = position.coords.latitude
        this.position.longitude = position.coords.longitude
      }
    }
}
</script>

<style scoped>
* {
  text-decoration: none;
}

.header {
  display: flex;
  justify-content: space-between;
  align-items: center;
}

.content {
  text-align: left;
}

.footer {
  text-align: right;
}
</style>