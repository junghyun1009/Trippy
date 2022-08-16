<template>
  <div>
	<div class="follower-profile"  v-for="follower in followerList" :key="follower.id">
		<span>{{ follower.img_path }}</span>
		<span class="follower-name">{{ follower.name }}</span>
	</div>
  </div>
</template>

<script>
import { mapGetters } from 'vuex'

export default {
  name: 'FollowersListItem',
	computed: {
		...mapGetters(['followerList'])
	},
	method: {
		test() {
			console.log(this.followerList)
		}
	}

}
</script>

<style>

</style>