<template>
  <div>
    <div v-for="(badge, idx) in badgeList" :key="idx">
      <!-- 여기에서 조건문 -->
      <!-- 사용자의 뱃지 목록에 있다면 -->
      <img :src=badge.image :alt=badge.image @click="badge.drawer=true">
      <p>{{ badge.name }}</p>
      <el-drawer v-model=badge.drawer direction="btt" size="50%">
        <img :src=badge.image :alt=badge.image style="width: 70px; height: 90px;">
        <p>{{ badge.name }}</p>
        <span>{{ badge.description }}</span><br>
        <span>앞으로도 Trippy에서 여행의 기억을 쌓아가세요!</span>
      </el-drawer>
      
      <!-- 사용자의 뱃지 목록에 없다면 -->
      <img class="not-obtained badge" :src=badge.image alt="not obtained" @click="badge.drawer=true">
      <p>{{ badge.name }}</p>
      <el-drawer v-model=badge.drawer direction="btt" size="50%">
        <img :src=badge.image :alt=badge.image style="width: 70px; height: 90px;">
        <p>{{ badge.name }}</p>
        <span>{{ badge.description }}</span><br>
        <span>어떤 모양의 뱃지일까요~?</span>
      </el-drawer>

    </div>
  </div>
</template>

<script>
export default {
  name: 'BadgeListView',
  data () {
    return {
      badgeList: [
        {
          pk: 1,
          name: '여행 초보',
          description: '첫 여행일지를 작성하셨네요!',
          image: require('@/assets/badge1.png'),
          drawer: false
        },
        {
          pk: 2,
          name: '경기도의 딸',
          description: '경기도 여행을 세 번 가셨군요!',
          image: require('@/assets/badge2.png'),
          drawer: false
        }
      ]
    }
  }
}
</script>

<style scoped>
.not-obtained {
  filter: blur(10px);
}
</style>