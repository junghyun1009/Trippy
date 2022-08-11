<template>
  <div class="navbar">
    <div class="buttons">
      <el-button class="button" link @click="goHome">
        <span class="material-symbols-outlined">home</span>
      </el-button>
      <el-button class="button" link @click="goFeed">
        <span class="material-symbols-outlined">explore</span>
      </el-button>
      <el-popover v-model:visible="isClicked" placement="top" :width="150" trigger="click">
        <el-button class="form-button diary" link @click="goDiaryForm">
          다이어리
          <span class="material-symbols-outlined">summarize</span>
        </el-button>
        <el-button class="form-button community" link @click="goCommunityForm">
          동행찾기
          <span class="material-symbols-outlined">diversity_3</span>
        </el-button>
        <template #reference>
          <el-button class="button" link ref="create">
            <span class="material-symbols-outlined">edit_square</span>
          </el-button>
        </template>
      </el-popover>
      <el-button class="button" link @click="goProfile">
        <span class="material-symbols-outlined profile">person</span>
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
  font-size: 1.1rem;
  margin-bottom: 0.3rem;
}

.form-button span {
  font-size: 1.1rem;
  margin-left: 1rem;
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