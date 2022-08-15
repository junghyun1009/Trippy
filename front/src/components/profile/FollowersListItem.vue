<template>
  <div>
		<div class="follower-profile" props="follower">
			<span>{{ followerList[0].img_path }}</span>
			<span class="follower-name">{{ followerList[0].name }}</span>
			<span>이건 보이냐</span>
		</div>
  </div>
</template>

<script>
import { mapGetters } from 'vuex'

export default {
  name: 'FollowersListItem',
	props: {
		follower: Object,
	},
	computed: {
		...mapGetters(['followerList'])
	},
	mounted() {
		this.test()
	},
	method: {
		test() {
			console.log(this.followerList)
		}
	}

}
</script>

<style>
.follower-profile {
	background-color: black;
	width: 70px;
	height: 70px;
	border-radius: 50%;
}
</style>