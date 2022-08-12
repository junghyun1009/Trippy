<template>
  <div>
    <div class="badge-list" v-for="(badge, idx) in badgeList" :key="idx">
      <!-- 여기에서 조건문 -->
      <!-- 사용자의 뱃지 목록에 있다면 -->
      <div class="badge">
        <img class="obtained" :src=badge.image :alt=badge.image @click="badge.drawer=true">
        <p>{{ badge.name }}</p>
      </div>
      <el-drawer v-model=badge.drawer direction="btt" size="50%">
        <img :src=badge.image :alt=badge.image style="width: 70px; height: 90px;">
        <p>{{ badge.name }}</p>
        <span>{{ badge.description }}</span><br>
        <span>앞으로도 Trippy에서 여행의 기억을 쌓아가세요!</span>
      </el-drawer>
      
      <!-- 사용자의 뱃지 목록에 없다면 -->
      <div class="badge">
        <img class="not-obtained" :src=badge.image alt="not obtained" @click="badge.drawer=true">
        <p>{{ badge.name }}</p>
      </div>
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
          name: '여행의 시작',
          description: 'Trippy에 오신 것을 환영합니다!',
          image: require('@/assets/world.png'),
          drawer: false
        },
        {
          pk: 2,
          name: '기록의 시작',
          description: '첫 여행 일지를 기록하셨군요!',
          image: require('@/assets/journal.png'),
          drawer: false
        },
        {
          pk: 3,
          name: '만남의 시작',
          description: '첫 동행 글을 작성하셨군요!',
          image: require('@/assets/chatting-app.png'),
          drawer: false
        }
      ]
    }
  }
}
</script>

<style scoped>
.badge-list {
  display: flex;
  flex-wrap: wrap;
}
.badge {
  display: flex;
  flex-direction: column;
  align-items: center;
}
.obtained {
  width: 10vw;
  height: 10vw;
}
.not-obtained {
  width: 10vw;
  height: 10vw;
  filter: blur(10px);
}
</style>