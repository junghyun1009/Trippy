<template>
  <div>
		<div class="following">
			<router-link :to="{ name: 'profile', params: { authorId: following.id } }">
				<el-avatar :size="60" :src="following.img_link"/>
			</router-link>
				<!-- {{ follower }} -->
			<span class="following-name">{{ following.name }}</span>
			<!-- <span>{{ follower.img_link }}</span> -->
		</div>
  </div>
</template>

<script>
import { mapGetters } from 'vuex'

export default {
  name: 'FollowingsListItem',
	props: {
		following: Object
	},
  computed: {
    ...mapGetters(['followingList'])
  }

}
</script>

<style scoped>
 .following {
	display: flex;
	flex-direction: column;
	align-items: center;
 }

 .following-name {
	font-size: 0.8rem;
	font-weight: 400;
 }
</style>