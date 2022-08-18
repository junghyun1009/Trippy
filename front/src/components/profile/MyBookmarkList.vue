<template>
  <div>
    {{myBookmarks}}
    <el-row>
      <el-col :span="8" v-for="bookmark in myBookmarks" :key="bookmark.id">
        <my-bookmark-list-item></my-bookmark-list-item>
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

<style>

</style>