<template>
  <div class="container">
    <div class="following-diaries">
        <div class="single-diary" v-if="followingDiaries.length === 1">
          <el-row>
            <el-col :span="24" v-for="diary in followingDiaries.slice().reverse()" :key="diary.id">
              <following-diary-list-item :diary="diary"></following-diary-list-item>
            </el-col>
          </el-row>
        </div>

        <div class="multiple-diaries" v-else >
          <el-row>
            <el-col :span="12" v-for="diary in followingDiaries.slice().reverse()" :key="diary.id">
              <following-diary-list-item :diary="diary"></following-diary-list-item>
            </el-col>
          </el-row>
        </div>

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
  margin-top: 1rem;
}

.el-row {
  margin-bottom: 20px;
}
.el-row:last-child {
  margin-bottom: 0;
}
.el-col {
  border-radius: 4px;
  height: 230px;
  margin-bottom: 3rem;
}

</style>