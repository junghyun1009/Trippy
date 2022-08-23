<template>
  <div>
    <router-link :to="{ name: 'communityDetail', params: { postPk: community.id } }">
    <el-card class="card">
      <div>
        <el-tag type="dark" class="tag">{{ community.cityName }}</el-tag>
        <el-tag type="dark" class="tag">{{ community.place }}</el-tag>
        <!-- <el-tag class="tag">{{ convertTag }}</el-tag> -->
        <el-tag type="dark" class="tag">{{ community.category === 1 ? '식사' : community.category === 2 ? '동행' : community.category === 3 ? '파티' : community.category === 4 ? '이동수단 셰어' : '기타' }}</el-tag>
        <!-- <el-tag class="tag" v-for="(tag, idx) in convertTag" :key="idx">{{ tag }} </el-tag> -->
      </div>
      <div class="title">
        <!-- <span class="state">{{ post.recruitCurrentVolume < post.recruitVolume ? '모집중' : '모집마감' }}</span> -->
        <h4>{{ community.title }}</h4>
      </div>
      <div class="options">
        <p class="option">
          <span class="material-symbols-outlined icon">sms</span>
          <span v-if="community.startAge===19 && community.endAge===70 && community.gender==='누구나'">누구나</span>
          <span v-else>{{ community.startAge === community.endAge ? `${community.startAge}세` : community.startAge === 19 && community.endAge === 70 ? '누구나' : `${community.startAge}~${community.endAge}세`}} | {{ community.gender }}</span>
          참여 가능
        </p>
        <p class="option">
          <span class="material-symbols-outlined icon">event_note</span>
          <!-- {{ convertDate }}, {{ convertTime }} -->
          <span>{{ community.endDate ? ''+community.startDate.slice(5, 10) + '~' + ''+community.endDate.slice(5,10) : ''+community.startDate.slice(5, 10) }},</span>
          <span>{{ ''+community.meetingTime.slice(11, 16) }}</span>
        </p>
        <!-- <p class="option">
          <span class="material-symbols-outlined icon">groups</span>
          {{ post.recruitCurrentVolume + 1 }} / {{ post.recruitVolume + 1}}명 참여
        </p> -->
      </div>
      <div class="content">
        <!-- <p>{{ convertDesc }}</p> -->
        <p>{{ community.description.length > 50 ? ''+community.description.slice(0, 45) + '...' : community.description}}</p>
      </div>

      
    </el-card>
  </router-link>
  </div>
</template>

<script>
export default {
  name: 'SearchedListItem',
  props: {
    community: Object
  }
}
</script>

<style scoped>
* {
  box-sizing: border-box;
  margin: 0;
  text-decoration: none;
}

.container {
  padding: 1rem 0;
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

.title > h4 {
  font-size: 1.3rem;
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

.icon {
  font-size: 1rem;
}

.content {
  font-size: 0.9rem;
  border-radius: 10px;
  background-color: #d9d9d9;
  padding: 10px;
}

.create {
  display: flex;
  justify-content: center;
  align-items: center;
  position: fixed;
  width: 3rem;
  height: 3rem;;
  bottom: 1.5rem;
  right: 1.5rem;
  border-radius: 50%;
  background-color: #F16B51;
  color: #fff;
}
</style>