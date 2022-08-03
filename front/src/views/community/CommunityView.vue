<template>
  <div>
    <h3>동행 찾기</h3>

    <router-link :to="{ name: 'communityDetail' }">
      <!-- v-for 추가예정 -->
      <el-card>
        <div class="header">
          <h4>{{ temp.title }}</h4>
          <el-tag>장소</el-tag>
        </div>
        <div class="content">
          <p>{{ temp.dateTime }}</p>
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
    computed: {
      ...mapGetters(['temp']),
      recruitState() {
        return '모집 중'
      },
      recruitCount() {
        return 3
      },
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