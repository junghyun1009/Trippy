<template>
  <div>
    <div>
      <el-row>
        <el-col :span="8">
          <followers-list-item></followers-list-item>
        </el-col>
      </el-row>
    </div>
  </div>
</template>

<script>
import FollowersListItem from '@/components/profile/FollowersListItem.vue'
import { mapActions, mapGetters } from 'vuex'

export default {
  name: 'FollowersList.vue',
  props: {
    isMyProfile: Boolean
  },
  components: {
    FollowersListItem,
  },
	computed: {
    ...mapGetters(['followerList', 'followerCount',]),
		followers() {
			// 이부분에 데이터 들어오면 될듯
			return this.$store.state.profile.image
		}
	},
  data() {
    return {
      visible: false,
    }
  },
  watch: {
    isMyProfile(newVal) {
      console.log(newVal)
      console.log(this.isMyProfile)
      if ( this.isMyProfile === false ) {
        console.log('not my profile')
        this.yourFollowers(this.$route.params.authorId)
        this.yourFollowersCount(this.$route.params.authorId)
      } else {
        console.log('my profile')
        this.myFollowers()
        this.myFollowersCount()
      }
    }
  },
  methods: {
    ...mapActions([
      'myFollowers',
      'myFollowersCount',
      'yourFollowers',
      'yourFollowersCount',
      ]),
  }
}
</script>

<style scoped>
.my-header {
  display: flex;
  justify-content: space-between;
  margin: 0;
}

.follower-modal {
  width: 80vw;
  margin: 0;
}

</style>