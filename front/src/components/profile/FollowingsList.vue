<template>
  <div>
    <div>
      <el-row>
        <el-col :span="8" v-for="following in followingList" :key="following.id">
          <followings-list-item :following="following"></followings-list-item>
        </el-col>
      </el-row>
    </div>
  </div>
</template>

<script>
import FollowingsListItem from '@/components/profile/FollowingsListItem.vue'
import { mapActions, mapGetters } from 'vuex'

export default {
  name: 'FollowingsList.vue',
  props: {
    isMyProfile: Boolean
  },
  components: {
    FollowingsListItem,
  },
	computed: {
    ...mapGetters(['followingList', 'followingCount',]),
    followings() {
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
        this.yourFollowings(this.$route.params.authorId)
        this.yourFollowingsCount(this.$route.params.authorId)
      } else {
        console.log('my profile')
        this.myFollowings()
        this.myFollowingsCount()
      }
    }
  },
  methods: {
    ...mapActions([
      'myFollowings',
      'myFollowingsCount',
      'yourFollowings',
      'yourFollowingsCount',
      ]),
  }
}
</script>

<style scoped>
.my-header {
  display: flex;
  flex-direction: row;
  justify-content: space-between;
}

</style>