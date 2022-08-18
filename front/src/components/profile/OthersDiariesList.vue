<template>
  <div class="container">
    <el-row>
      <el-col :span="8" v-for="diary in othersDiary" :key="diary.id">
        <others-diaries-list-item :diary="diary"></others-diaries-list-item>
      </el-col>
    </el-row>
  </div>
</template>

<script>
import { mapGetters } from 'vuex'
import OthersDiariesListItem from '@/components/profile/OthersDiariesListItem.vue'

export default {
  name: 'OthersDiariesList',
  components: {
    OthersDiariesListItem
  },
  computed: {
    ...mapGetters(['othersDiary'])
  },
  methods: {
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