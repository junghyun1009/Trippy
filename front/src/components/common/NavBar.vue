<template>
  <div>
    <div class="navbar">
			<div class="forms" v-if="isClicked">
				<el-button class="form-button diary" @click="goDiaryForm">다이어리</el-button>
				<el-button class="form-button community" @click="goCommunityForm">동행찾기</el-button>
			</div>
			<div class="buttons">
        <el-button class="button" link @click="goHome">
          <span class="material-symbols-outlined">home</span>
        </el-button>
        <el-button class="button" link @click="goFeed">
          <span class="material-symbols-outlined">explore</span>
        </el-button>
        <el-button class="button" link @click="toggle">
          <span class="material-symbols-outlined">edit_square</span>
        </el-button>
        <el-button class="button" link @click="goProfile">
          <span class="material-symbols-outlined profile">person</span>
        </el-button>
      </div>
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
      this.isClicked = false
			this.$router.push({ name: 'home' })
		},
		goFeed() {
      this.isClicked = false
		},
		goDiaryForm() {
      this.toggle()
			this.$router.push({ name: 'diaryCreate' })
		},
		goCommunityForm() {
      this.toggle()
			this.$router.push({ name: 'communityCreate' })
		},
		goProfile() {
      this.isClicked = false
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
  transform: translate(50%, 0%);
}

.diary {
  z-index: 2;
  bottom: 12vh;
}

.buttons {
  display: flex;
  justify-content: space-around;
  align-items: center;
}

.button {
  margin: 0;
}

.material-symbols-outlined {
  font-size: 2rem;
}

</style>