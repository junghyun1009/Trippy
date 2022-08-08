<template>
  <div>
    <div class="navbar">
			<div class="forms" v-if="isClicked">
				<el-button class="form-button diary" @click="goDiaryForm(), toggle()">다이어리</el-button>
				<el-button class="form-button community" @click="goCommunityForm(), toggle()">동행찾기</el-button>
			</div>
			<el-button link @click="goHome">
				<span class="material-symbols-outlined">home</span>
			</el-button>
			<el-button link>
				<span class="material-symbols-outlined">search</span>
			</el-button>
			<el-button link @click="toggle">
				<span class="material-symbols-outlined create">edit_square</span>
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

<style scoped>
.navbar {
  position: fixed;
  bottom: 0vh;
  width: 100%;
  border-top: 1px solid #d9d9d9;
  background-color: #fff;
  padding: 0.3rem;
}

.forms {
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
}

.form-button {
  z-index: 1;
  position: fixed;
  bottom: 7vh;
  margin-bottom: 0.3rem;
  transform: translate(30%, 0%);
}

.diary {
  z-index: 2;
  bottom: 12vh;
}


.material-symbols-outlined {
  margin-right: 2rem;
  font-size: 2rem;
}
</style>