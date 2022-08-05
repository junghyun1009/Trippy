<template>
  <div>
    <recommend-list-item></recommend-list-item>
  </div>
</template>

<script>
import RecommendListItem from '@/components/home/RecommendListItem.vue'

export default {
  name: "RecommendList",
  components: { 
      RecommendListItem 
  },
  props: {
    diary: Object,
  }
}
</script>

<style>

</style>