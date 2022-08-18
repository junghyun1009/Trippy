<template>
  <div class="container">
    <div v-if="regionDiaries" class="region-diaries">
      <el-row>
        <el-col :span="12" v-for="diary in regionDiaries.slice().reverse()" :key="diary.id">
          <recommend-list-item :diary="diary"></recommend-list-item>
        </el-col>
      </el-row>
    </div>

    <div v-if="searchDiaries" class="search-diaries">
      <br>
      <div v-if="searchDiaries.length > 0" class="search-diary">
        <el-row>
          <el-col :span="12" v-for="diary in searchDiaries.slice().reverse()" :key="diary.id">
            <recommend-list-item :diary="diary"></recommend-list-item>
          </el-col>
        </el-row>
      </div>
      <div v-else class="search-dairy">
        <p>검색결과가 없습니다</p>
        <el-divider></el-divider>
        <p> 전체글보기</p>
      </div>
    </div>
    <br>

    <div v-if="allDiaries.length > 0 && searchDiaries.length === 0" class="all-diaries">
      <el-row>
        <!-- infinite scroll -->
        <!-- 아니면 더 보기? -->
        <el-col :span="12" v-for="diary in allDiaries.slice(-10).reverse()" :key="diary.id">
          <recommend-list-item :diary="diary"></recommend-list-item>
        </el-col>
      </el-row>
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
}
.el-row {
  margin-bottom: 20px;
}
.el-row:last-child {
  margin-bottom: 0;
}
.el-col {
  border-radius: 4px;
}

</style>