<template>
  <div class="navbar">
    <div class="buttons">
      <el-button class="button" link @click="goHome">
        <div class="menu">
          <span class="material-symbols-outlined menu-icon">home</span>
          <span class="menu-desc">홈</span>
        </div>
      </el-button>
      <el-button class="button" link @click="goCommunity">
        <div class="menu">
          <span class="material-symbols-outlined menu-icon">explore</span>
          <span class="menu-desc">동행찾기</span>
        </div>
      </el-button>
      <el-popover v-model:visible="isClicked" placement="top" :width="150" trigger="click">
        <div class="popover">
          <el-button class="form-button diary" link @click="goDiaryForm">
            <span class="form-desc">다이어리</span>
            <span class="material-symbols-outlined menu-icon">description</span>
          </el-button>
          <el-button class="form-button community" link @click="goCommunityForm">
            <span class="form-desc">동행찾기</span>
            <span class="material-symbols-outlined menu-icon">groups_2</span>
          </el-button>
        </div>
        <template #reference>
          <el-button class="button" link ref="create">
            <div class="menu">
              <span class="material-symbols-outlined menu-icon">edit</span>
              <span class="menu-desc">글쓰기</span>
            </div>
          </el-button>
        </template>
      </el-popover>
      <!-- <el-button class="button" link @click="goChat">
        <div class="menu">
          <span class="material-symbols-outlined menu-icon">forum</span>
          <span class="menu-desc">CHAT</span>
        </div>
      </el-button> -->
      <el-button class="button" link @click="goProfile">
        <div class="menu">
          <span class="material-symbols-outlined menu-icon profile">person</span>
          <span class="menu-desc">내 트리피</span>
        </div>
      </el-button>
    </div>
  </div>
</template>

<script>
import { ElMessageBox } from 'element-plus'
import { mapActions, mapGetters } from 'vuex'
import VueCookies from 'vue-cookies'

export default {
	name: 'NavBar',
	data() {
		return {
			isClicked: false,
		}
	},
  computed: {
    ...mapGetters(['profile', 'isLoggedIn'])
  },
  mounted() {
    this.fetchProfile()
    console.log(this.profile.id)
  },
	methods: {
    ...mapActions(['fetchProfile']),

		toggle() {
			this.isClicked = !this.isClicked
		},	
		goHome() {
      this.isClicked = false
			this.$router.push({ name: 'home' })
		},
		goCommunity() {
      this.isClicked = false
      this.$router.push({ name: 'community' })
		},
		goDiaryForm() {
      this.toggle()
			this.$router.push({ name: 'diaryCreate' })
		},
		goCommunityForm() {
      this.toggle()
			this.$router.push({ name: 'communityCreate' })
		},
    goChat() {
      this.isClicked = false
      this.$router.push({ name: 'chatList' })
    },
		goProfile() {
      this.isClicked = false
      if ( !VueCookies.get('accessToken') && !VueCookies.get('refreshToken') ) {
        ElMessageBox.alert('로그인을 해주세요!', '알림', {
          confirmButtonText: 'OK',
        })
        this.$router.push({ name: 'login' })
      } else {
      const userid = this.profile.id
			// this.$router.push({ name: 'profile', params: { username }})
			// this.$router.push({ name: 'profile', params: { authorId: `${username}` }})
			this.$router.push({ name: 'profile', params: { authorId: userid }})
			// this.$router.push({ path: `/profile/${username}` })
      }
		},
    
	},
}
</script>

<style scoped>
.navbar {
  position: fixed;
  bottom: 0vh;
  width: 100%;
  /* height: 3rem; */
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

.popover {
  display: flex;
  flex-direction: column;
  justify-content: center;
}

.form-desc {
  font-size: 1rem;
  margin-right: 0.6rem;
}

.community {
  margin: 0 !important;
}

.buttons {
  display: flex;
  justify-content: space-around;
  align-items: center;
}

.button {
  margin: 0;
}

.menu {
  display: flex;
  flex-direction: column;
}

.menu-icon {
  font-size: 1.5rem;
  margin-bottom: 0.4rem;
}

.menu-desc {
  font-size: 0.75rem;
  /* font-weight: bold; */
}

</style>