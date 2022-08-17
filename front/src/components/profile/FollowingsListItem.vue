<template>
  <div>
	<div class="following-profile"  v-for="following in followingList" :key="following.id">
		<span>{{ following.img_path }}</span>
		<span class="following-name">{{ following.name }}</span>
	</div>
  </div>
</template>

<script>
import { mapGetters } from 'vuex'

export default {
  name: 'FollowingsListItem',
  computed: {
    ...mapGetters(['followingList'])
  }

}
</script>

<style>

</style>