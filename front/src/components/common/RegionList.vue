<template>
  <div class="container">
			<region-list-item></region-list-item>
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

<style scoped>
.container {
  padding: 0 1rem;
}

.regional-menu {
	display: flex;
	margin-left: 2%;
}

</style>