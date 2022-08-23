<template>
  <div class="container">
    <!-- {{myLikes}} -->
    <el-row>
      <el-col :span="8" v-for="like in myLikes.slice().reverse()" :key="like.id">
        <my-likes-list-item :like="like"></my-likes-list-item>
      </el-col>
    </el-row>
  </div>
</template>

<script>
import MyLikesListItem from '@/components/profile/MyLikesListItem.vue'
import { mapGetters } from 'vuex'

export default {
  name: 'MyLikesList',
  components: {
    MyLikesListItem
  },
  computed: {
    ...mapGetters(['myLikes']),
  }
}
</script>

<style>

</style>