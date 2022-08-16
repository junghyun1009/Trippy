<template>
  <div>
    <div class="following-profile"  v-for="following in followingList" :key="following.id">
      <span>{{ following.img_path }}</span>
      <span class="follower-name">{{ following.name }}</span>
    </div>
  </div>
</template>

<script>
import { mapGetters } from 'vuex'

export default {
  name: 'FollowingsListItem',
  computed: {
    ...mapGetters(['followingList'])
  },
}
</script>

<style>
/* .profileimage {
	background-color: black;
	width: 100px;
	height: 100px;
	border-radius: 50%;
} */
</style>