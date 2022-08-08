<template>
  <div>
    <div>
      <el-menu
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
      </el-menu>
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

    </div>
  </div>
</template>

<script>
import { ref } from 'vue'
import { mapGetters, mapActions } from 'vuex'


export default {
  name: "NavBar",
  components: {
  },
  data () {
    return {
      visible: false,
      flag: 0,
      searchPlace: '',
      activeIndex: ref('3')
    }
  },
  computed: {
    ...mapGetters(['isLoggedIn', 'profile',]),
  },
	methods: {
    ...mapActions(['logout']),
    goSearch() {

    },
    // showSearchBar() {
    //   this.flag = 1
    // },
    closeSearchBar() {
      this.flag = 0
    },
    showInput() {
      console.log(this.searchPlace)
      this.searchPlace = ''
    }
	}


}
</script>

<style scoped>
@import url('https://fonts.googleapis.com/css2?family=Alfa+Slab+One&display=swap');

* {
  background-color: "#F16B51";
}

.el-menu-demo {
  display: flex;
  align-items: center;
  margin: 0;
  border: none;
}

.menu-item {
  padding: 0 1rem 0 0;
}

.menu-icon {
  display: flex;
  align-items: center;
}

.trippyLogo {
	font-family: 'Alfa Slab One', cursive;
	font-weight: 300;
	font-size: 20px;
  text-decoration: none;
  }

.flex-grow {
  flex-grow: 1;
}

</style>