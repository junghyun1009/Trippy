<template>
  <header>
    <div>
      <div v-if="this.$route.name === 'home'" class="logo">Trippy</div>
      <div v-else class="view-name-group">
        <div class="menu-icon" @click="goBack()">
          <span class="material-symbols-outlined">arrow_back_ios</span>
        </div>
        <div class="view-name">{{ viewName }}</div>
      </div>
    </div>
    <div v-if="this.$route.name !== 'login'" class="menu">
      <div class="menu-icon search" @click="goSearch">
        <span class="material-symbols-outlined">search</span>
      </div>
      <div @click="visible=true" class="menu-icon">
        <span class="material-symbols-outlined">menu</span>
      </div>
    </div>
    <!-- <el-menu
      :default-active="activeIndex"
      class="el-menu-demo"
      mode="horizontal"
      :ellipsis="false"
      background-color="#F16B51"
      text-color="#fff"
    >

      <el-menu-item index="0">
        <a href="/" v-if="this.$route.name === 'home'" class="trippyLogo">Trippy</a>
        <div v-else>{{ this.$route.name }}</div>
      </el-menu-item>
      <div class="flex-grow" />
      <el-menu-item class="menu-item" index="1">
        <div class="menu-icon">
          <span class="material-symbols-outlined">search</span>
        </div>
      </el-menu-item>
      <el-menu-item class="menu-item" index="2">
        <div @click="visible=true" class="menu-icon">
          <span class="material-symbols-outlined">menu</span>
        </div>
      </el-menu-item>
    </el-menu> -->
    <el-drawer v-model="visible" :show-close="false" size="70%">
      <!-- 로그인한 유저 -->
      <!-- <div v-if="isLoggedIn"> -->
        <div> 
          <div @click="goProfile(), visible=false">
            <el-avatar :size="100" :src="profile.img_path"/>
            <p>
              {{ profile.name }}
              <span class="material-symbols-outlined">arrow_forward_ios</span>
            </p>
          </div>
          <!-- <router-link :to="{ name: 'profileEdit' }" @click="visible=false">프로필 수정</router-link>
          <span @click="logout()"> | 로그아웃</span> -->
          <hr>
          <p @click="goMyDiary(), visible=false">내 일지</p>
          <p @click="goMyLikes(), visible=false">내 좋아요</p>
          <p>내 동행찾기</p>
          <hr>
          <p @click="goCommunity(), visible=false">동행찾기</p>
          <p @click="goBadge(), visible=false">뱃지</p>
          <hr>
          <p @click="goSetting(), visible=false">설정</p>
          <hr>
        </div>
      <!-- </div> -->
      <!-- 로그인 안 한 유저 -->
      <!-- <div v-if="!isLoggedIn"> -->
        <div>
          <p @click="goLogin(), visible=false">로그인</p>
          <p @click="goSignup(), visible=false">회원가입</p>
        </div>
      <!-- </div> -->
    </el-drawer>
  </header>
</template>

<script>
import { mapGetters, mapActions } from 'vuex'


export default {
  name: 'TheHeader',
  components: {
  },
  data () {
    return {
      visible: false,
      // flag: 0,
      searchPlace: '',
    }
  },
  computed: {
    ...mapGetters(['isLoggedIn', 'profile',]),
    viewName() {
      const name = this.$route.name
      if (name === 'search') {
        return '검색'
      } else if (name === 'passwordChange') {
        return '비밀번호 변경'
      } else if (name === 'passwordFind') {
        return '비밀번호 찾기'
      } else if (name === 'diaryCreate') {
        return '다이어리 작성'
      } else if (name === 'diaryEdit') {
        return '다이어리 수정'
      } else if (name === 'signUp' || name === 'signUpOption') {
        return '회원가입'
      } else if (name === 'profile') {
        return '프로필'
      } else if (name === 'profileEdit') {
        return '프로필 수정'
      } else if (name === 'community') {
        return '동행찾기'
      } else if (name === 'communityCreate') {
        return '동행찾기 작성'
      } else if (name === 'communityEdit') {
        return '동행찾기 수정'
      } else if (name === 'badgeList') {
        return '뱃지'
      } else if (name === 'chatList') {
        return '채팅'
      } else {
        return ''
      }
    }
  },
	methods: {
    ...mapActions(['logout']),
    goBack() {
      this.$router.go(-1)
    },
    goSearch() {
      this.$router.push({ name:  'search' })
    },
    goProfile() {
      this.$router.push({ name: 'profile' })
    },
    goMyDiary() {
      this.$router.push({ name: 'profile'})
    },
    goMyLikes() {
      this.$router.push({ name: 'profile' })
    },
    goCommunity() {
      this.$router.push({ name: 'community' })
    },
    goBadge() {
      this.$router.push({ name: 'badgeList' })
    },
    goSetting() {
      this.$router.push({ name: 'setting' })
    },
    goLogin() {
      this.$router.push({ name: 'login' })
    },
    goSignup() {
      this.$router.push({ name: 'signUp' })
    },
    // showSearchBar() {
    //   this.flag = 1
    // },
    // closeSearchBar() {
    //   this.flag = 0
    // },
    showInput() {
      console.log(this.searchPlace)
      this.searchPlace = ''
    }
	}


}
</script>

<style scoped>
@import url('https://fonts.googleapis.com/css2?family=Alfa+Slab+One&display=swap');

header {
  display: flex;
  justify-content: space-between;
  align-items: center;
  padding: 1rem;
  background-color: #F16B51;
}

.logo {
  font-family: 'Alfa Slab One', cursive;
  font-size: 1.3rem;
  color: #fff;
}

.view-name-group {
  display: flex;
  align-items: center;
  color: #ffffff;
}

.view-name {
  font-weight: bold;
  font-size: 1.1rem;
}

.menu {
  display: flex;
  align-items: center;
}

.menu-icon {
  display: flex;
  justify-content: center;
  align-items: center;
  color: #fff;
}

.search {
  margin-right: 0.5rem;
}

/* 
.flex-grow {
  flex-grow: 1;
} */

</style>