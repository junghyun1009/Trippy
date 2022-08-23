<template>
  <div class="container">
    <div v-if="regionDiaries" class="region-diaries">
      <el-row>
        <el-col :span="12" v-for="diary in regionDiaries.slice().reverse()" :key="diary.id">
          <recommend-list-item :diary="diary"></recommend-list-item>
        </el-col>
      </el-row>
      <el-divider v-if="regionDiaries.length > 0"></el-divider>
    </div>

    <div v-if="searchDiaries" class="search-diaries">
      <div v-if="searchDiaries.length > 0" class="search-diary">
        <h3>검색 결과</h3>
        <br>
        <el-row>
          <el-col :span="12" v-for="diary in searchDiaries.slice().reverse()" :key="diary.id">
            <recommend-list-item :diary="diary"></recommend-list-item>
          </el-col>
        </el-row>
      </div>
      <div v-else-if="searchDiaries.length === 0 && this.$route.path === '/search'" class="search-dairy">
        <br>
        <!-- <p>검색결과가 없습니다</p> -->
        <el-divider></el-divider>
        <p>전체글보기</p>
        <br>
      </div>
    </div>

    <div v-if="allDiaries.length > 0 && searchDiaries.length === 0" class="all-diaries">
      <!-- <el-row>
        <el-col :span="12" v-for="diary in allDiaries[diariesToShow-1]" :key="diary.id">
          <recommend-list-item :diary="diary"></recommend-list-item>
        </el-col>
      </el-row> -->
      <el-row>
        <el-col :span="12" v-for="(diary, index) in allDiaries.slice(-[diariesToShow+1]).reverse()" :key="(diary.id, index)">
          <div v-if="index <= diariesToShow">
          <recommend-list-item :diary="diary"></recommend-list-item>
          </div>
        </el-col>
      </el-row>
          <el-button type="primary" round @click="diariesToShow += 6">더보기</el-button>
    </div>
  </div>
</template>

<script>
import RecommendListItem from '@/components/home/RecommendListItem.vue'
import { mapGetters, mapActions } from 'vuex'

export default {
  name: "RecommendList",
  components: { 
      RecommendListItem 
  },

  data() {
    return {
      diariesToShow: 7
    }
  },

  created() {
    this.fetchAllDiaries()
  },

  computed: {
    ...mapGetters(['regionDiaries', 'allDiaries', 'searchDiaries']),
  },

  methods: {
    ...mapActions(['fetchAllDiaries', 'getRegionDiaries'])
  }
}
</script> 

<style scoped>
.container {
  display: flex;
  justify-content: center;
  margin: 0 3%;
  position: relative;
  /* bottom: 1rem; */
}
.el-row {
  margin-bottom: 1rem;
}
/* .el-row:last-child {
  margin-bottom: 0;
} */
.el-col {
  border-radius: 4px;
  height: 230px;
  margin-bottom: 3rem;
}

.el-button {
  width: 100%;
}

.el-button--primary {
  --el-button-active-bg-color: var(--el-color-primary);
  --el-button-hover-bg-color: #FFD2C9;
  --el-button-hover-border-color: #FFD2C9;
}
</style>