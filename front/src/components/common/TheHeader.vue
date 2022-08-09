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
    <div class="menu">
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
        <router-link :to="{ name: 'profile' }" @click="visible =false">
          <el-avatar :size="100" :src="profile.img_path" />
        </router-link>
        <p>{{ profile.name }}의 로그</p>
        <router-link :to="{ name: 'profileEdit' }" @click="visible =false">프로필 수정</router-link>
        <span @click="logout()"> | 로그아웃</span>
        <hr>
        <router-link :to="{ name: 'diaryCreate' }" @click="visible =false">다이어리 작성</router-link>
        <br>
        <router-link :to="{ name: 'community' }" @click="visible =false">동행 구하기</router-link>
        <hr>
        <router-link :to="{ name: 'chatList' }" @click="visible =false">채팅</router-link>

      </div>
      <!-- 로그인 안 한 유저 -->
      <!-- <div v-else> -->
      <div>
        <router-link :to="{ name: 'login' }" @click="visible =false">로그인</router-link>
        <br>
        <router-link :to="{ name: 'signUp' }" @click="visible =false">회원가입</router-link>
      </div>

    </el-drawer>

  </header>
</template>

<script>
import { mapGetters, mapActions } from 'vuex'


export default {
  name: "NavBar",
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
  color: #fff;
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