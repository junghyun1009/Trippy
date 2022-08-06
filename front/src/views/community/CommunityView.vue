<template>
  <div>
    <h3>동행 찾기</h3>
    <span class="material-symbols-outlined location" @click="getLocation">my_location</span>

    <!-- <region-list></region-list> -->
    <router-link :to="{ name: 'communityDetail' }">
      <!-- v-for 추가예정 -->
      <el-card class="card">
        <div>
          <el-tag class="tag">{{ temp.category }}</el-tag>
          <el-tag class="tag">장소</el-tag>
        </div>
        <div class="title">
          <span class="state">{{ recruitState }}</span>
          <h4>{{ temp.title }}</h4>
        </div>
        <div class="options">
          <p class="option">
            <span class="material-symbols-outlined">groups</span>
            {{ temp.option.age[0] }}~{{ temp.option.age[1] }}세 {{ temp.option.gender }} 참여 가능
          </p>
          <p class="option">
            <span class="material-symbols-outlined">event_note</span>
            {{ temp.date[0] }} ~ {{ temp.date[1] }}, {{ temp.time }}
          </p>
          <p class="option">
            <span class="material-symbols-outlined">groups</span>
            {{ recruitCount}} / {{ temp.recruit_volume }}명 참여
          </p>
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
// import RegionList from '@/components/common/RegionList.vue'

export default {
    name: 'CommunityView',
    components: {
      // RegionList
    },
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

.location {
  position: relative;
  right: -9.5rem;
  color: #F16B51;
}

.card {
  text-align: left;
  margin-bottom: 0.5rem;
}

.tag {
  margin-right: 0.3rem;
  margin-bottom: 0.5rem;
}

.title {
  display: flex;
  align-items: center;
  margin-bottom: 0.5rem;
}

.state {
  font-weight: bold;
  color: #F16B51;
  margin-right: 0.3rem;
}

.options {
  font-size: 0.9rem;
  margin-bottom: 0.5rem;
}

.option {
  display: flex;
  align-items: center;
  margin-right: 0.3rem;
  margin-bottom: 0.3rem;
}

.option > span {
  margin-right: 0.3rem;
}

.content {
  font-size: 0.9rem;
  border-radius: 10px;
  background-color: #d9d9d9;
  padding: 10px;
}


</style>