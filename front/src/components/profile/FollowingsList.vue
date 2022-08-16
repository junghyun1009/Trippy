<template>
  <div>
    <p @click="visible = true">
      FOLLOWINGS
    </p>

    <el-dialog class="following-modal" v-model="visible" :show-close="false">
      <template #header="{ close, titleId, titleClass }">
        <div class="my-header">
            <h4 :id="titleId" :class="titleClass">FOLLOWINGS</h4>
            <el-button type="danger" @click="close">
            x
            </el-button>
        </div>
        <div>
          <el-row>
            <el-col :span="8">
              <followings-list-item></followings-list-item>
            </el-col>
          </el-row>
        </div>
      </template>
    </el-dialog>

    <p>{{ followingCount }}</p>
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