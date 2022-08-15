<template>
  <div>
    <p @click="visible = true">
      FOLLOWERS
    </p>
    <el-dialog class="follower-modal" v-model="visible" :show-close="false">
      <template #header="{ close, titleId, titleClass }">
        <div class="my-header">
            <h4 :id="titleId" :class="titleClass">FOLLOWERS</h4>
            <el-button type="danger" @click="close">
            x
            </el-button>
        </div>
        <div>
          <el-row>
            <el-col :span="8">
              <followers-list-item v-for="follower in followers" :key="follower.id" :follower="follower"></followers-list-item>
            </el-col>
          </el-row>
        </div>
      </template>
    </el-dialog>
      <p>{{ followerCount }}</p>
  </div>
</template>

<script>
import FollowersListItem from '@/components/profile/FollowersListItem.vue'
import { mapGetters } from 'vuex'

export default {
  name: 'FollowersList.vue',
  components: {
    FollowersListItem,
  },
	computed: {
    ...mapGetters(['followerList', 'followingList', 'followerCount', 'followingCount',]),
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
  methods: {
    

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