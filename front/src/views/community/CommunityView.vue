<template>
  <div>
    <h3>동행 찾기</h3>
    <span @click="getLocation()">현재 위치</span>

    <router-link :to="{ name: 'communityDetail' }">
      <!-- v-for 추가예정 -->
      <el-card class="card">
        <div>
          <el-tag class="tag">{{ temp.category }}</el-tag>
          <el-tag class="tag">장소</el-tag>
        </div>
        <div class="title">
          <span>{{ recruitState }}</span>
          <h4>{{ temp.title }}</h4>
        </div>
        <div class="option">
          <p>{{ temp.option.age[0] }}~{{ temp.option.age[1] }}세 {{ temp.option.gender }} 참여 가능</p>
          <p>{{ temp.date[0] }} ~ {{ temp.date[1] }}, {{ temp.time }}</p>
          <p>{{ recruitCount}} / {{ temp.recruit_volume }}명 참여</p>
        </div>
        <div class="content">
          <p>{{ convertDesc }}</p>
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
        return '모집중'
      },
      recruitCount() {
        return 3
      },
      convertDesc() {
        let length = 55
        let desc = ''
        if (this.temp.desc.length > length) {
          desc = this.temp.desc.substr(0, length - 2) + '...'
        }
        return desc
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
      },
    },
  }
</script>

<style scoped>
* {
  box-sizing: border-box;
  margin: 0;
  text-decoration: none;
}

.card {
  text-align: left;
}

.tag {
  margin-right: 10px;
}

.title {
  display: flex;
  align-items: center;
}

.title span {
  margin-right: 5px;
}

.content {
  border-radius: 10px;
  background-color: #d9d9d9;
  padding: 10px;
}


</style>