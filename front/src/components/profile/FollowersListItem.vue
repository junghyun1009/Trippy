<template>
  <div>
		<div class="follower">
			<router-link :to="{ name: 'profile', params: { authorId: follower.id } }">
				<el-avatar :size="60" :src="follower.img_link" />
			</router-link>
				<!-- {{ follower }} -->
			<span class="follower-name">{{ follower.name }}</span>
			<!-- <span>{{ follower.img_link }}</span> -->
		</div>
  </div>
</template>

<script>
import { mapGetters } from 'vuex'

export default {
  name: 'FollowersListItem',
	props: {
		follower: Object
	},
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

<style scoped>
 .follower {
	display: flex;
	flex-direction: column;
	align-items: center;
 }

 .follower-name {
	font-size: 0.8rem;
	font-weight: 400;
 }
</style>