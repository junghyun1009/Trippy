<template>
  <div>
    <div class="container">
      <p @click="visible = true">
        FOLLOWINGS
      </p>
      <el-dialog v-model="visible" :show-close="false">
        <template #header="{ close, titleId, titleClass }">
          <div class="my-header">
              <h4 :id="titleId" :class="titleClass">FOLLOWINGS</h4>
              <el-button type="danger" @click="close">
              x
              </el-button>
          </div>
        </template>
        <followings-list-item></followings-list-item>
      </el-dialog>
			<p>{{ followingCount }}</p>

    </div>
  </div>
</template>

<script>
import FollowingsListItem from '@/components/profile/FollowingsListItem.vue'
import { mapGetters } from 'vuex'

export default {
  name: 'FollowingsList.vue',
  components: {
    FollowingsListItem,
  },
	computed: {
    ...mapGetters(['followerList', 'followingList', 'followerCount', 'followingCount',]),
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
  methods: {
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