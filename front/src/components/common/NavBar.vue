<template>
  <div>
    <div>
			<div v-if="isClicked">
				<el-button @click="goDiaryForm(), toggle()">다이어리</el-button>
				<el-button @click="goCommunityForm(), toggle()">동행찾기</el-button>
			</div>
			<el-button link @click="goHome">
				<span class="material-symbols-outlined">home</span>
			</el-button>
			<el-button link>
				<span class="material-symbols-outlined">search</span>
			</el-button>
			<el-button link @click="toggle">
				<span class="material-symbols-outlined">edit_square</span>
			</el-button>
			<el-button link @click="goProfile">
				<span class="material-symbols-outlined">person</span>
			</el-button>
    </div>
  </div>
</template>

<script>
export default {
	name: 'NavBar',
	data() {
		return {
			isClicked: false,
		}
	},
	methods: {
		toggle() {
			this.isClicked = !this.isClicked
		},	
		goHome() {
			this.$router.push({ name: 'home' })
		},
		// goSearch() {
		// },
		goDiaryForm() {
			this.$router.push({ name: 'diaryCreate' })
		},
		goCommunityForm() {
			this.$router.push({ name: 'communityCreate' })
		},
		goProfile() {
			this.$router.push({ name: 'profile' })
		}
	},
}
</script>

<style>

</style>