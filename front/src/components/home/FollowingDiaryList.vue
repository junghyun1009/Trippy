<template>
  <div class="container">
    <div class="following-diaries">
      <el-row>
        <el-col :span="8" v-for="diary in followingDiaries.slice().reverse()" :key="diary.id">
          <following-diary-list-item :diary="diary"></following-diary-list-item>
        </el-col>
      </el-row>
    </div>
  </div>
</template>

<script>
import FollowingDiaryListItem from '@/components/home/FollowingDiaryListItem.vue'
import { mapGetters, mapActions } from 'vuex'

export default {
  name: "RecommendList",
  components: { 
      FollowingDiaryListItem 
  },
	// data () {
	// 	return {
	// 		followingAuthorId: null,
	// 	}
	// },
	// props: {
	// 	followingIdforDiary: String
	// },

  mounted() {
		this.fetchFollowingDiaries(this.followingId)
		console.log(this.$store.getters.followingDiaries)
		// this.propstoData()
  },
	watch: {
		followingDiaries(newVal) {
			console.log(newVal)
			console.log(this.$store.getters.followingDiaries)
		}
	},
  computed: {
    ...mapGetters(['followingDiaries', 'allDiaries', 'followingId']),
  },
  methods: {
    ...mapActions(['fetchFollowingDiaries', 'fetchAllDiaries']),
		// propstoData(){
		// 	console.log(this.followingIdforDiary)
		// 	this.followingAuthorId = this.followingIdforDiary
		// 	console.log('props to data')
		// 	console.log(this.followingAuthorId)
		// }
  }
}
</script> 

<style scoped>
.container {
  display: flex;
  justify-content: center;
  margin: 0 3%;
}
.el-row {
  margin-bottom: 20px;
}
.el-row:last-child {
  margin-bottom: 0;
}
.el-col {
  border-radius: 4px;
}

</style>