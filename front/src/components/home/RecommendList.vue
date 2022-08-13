<template>
  <div class="container">
    <el-row>
      <el-col :span="8" v-for="diary in diaries" :key="diary.id">
        <recommend-list-item :diary="diary"></recommend-list-item>
      </el-col>
    </el-row>
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
    ...mapGetters(['allDiaries']),
    diaries() {
      const diaries = this.$store.getters.allDiaries
      console.log(diaries)
      return diaries
    }
  },
  methods: {
    ...mapActions(['fetchAllDiaries'])
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