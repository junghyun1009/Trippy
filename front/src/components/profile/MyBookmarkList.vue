<template>
  <div>
    <el-row>
      <!-- {{myBookmarks}} -->
      <el-col :span="8" v-for="bookmark in myBookmarks" :key="bookmark.id">
        <my-bookmark-list-item :bookmark="bookmark"></my-bookmark-list-item>
      </el-col>
    </el-row>
  </div>
</template>

<script>
import MyBookmarkListItem from '@/components/profile/MyBookmarkListItem.vue'
import { mapGetters } from 'vuex'

export default {
  name: 'MyBookmarkList',
  components: {
    MyBookmarkListItem
  },
  computed: {
    ...mapGetters(['myBookmarks'])
  }
}
</script>

<style scoped>
.container {
  display: flex;
  justify-content: center;
  margin: 0 3% 20% 0%;
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