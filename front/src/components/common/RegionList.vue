<template>
  <div>
		<p>지금 트리피에서 핫한 여행코스</p>
		<region-list-item></region-list-item>
		<br>
		<br>
  </div>
</template>

<script>
import RegionListItem from '@/components/common/RegionListItem.vue'

export default {
  name: 'RegionList',
	components: {
		RegionListItem
	},

}
</script>

<style>

</style>