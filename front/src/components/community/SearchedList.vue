<template>
  <div v-if="searchCommunities.length > 0">
    <h3>검색 결과</h3>
    <div v-for="community in searchCommunities" :key="community.id">
      <searched-list-item :community="community"></searched-list-item>
    </div>
    <el-divider></el-divider>
  </div>
</template>

<script>
import { mapGetters } from 'vuex'
import SearchedListItem from '@/components/community/SearchedListItem.vue'

export default {
  name: 'SearchedList',
  components: {
    SearchedListItem
  },
  computed: {
    ...mapGetters(['searchCommunities'])
  }
}
</script>

<style .scoped>
h3 {
  padding: 0rem 1rem 1rem 1rem;
	font-size: 1rem;
	font-weight: bold;
}
</style>