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
        <el-menu-item index="0"><span class="trippyLogo" @click="$router.push('/')">TRIPPY</span></el-menu-item>
        <div class="flex-grow" />
        <el-menu-item index="1">
          <div v-if="flag===0" @click="showSearchBar" class="menu-icon">
            <icon-base viewBox="0 0 1024 1024" width="24" height="24" iconColor="#fff" icon-name="searchbar">
              <search-bar/>
            </icon-base>
          </div>
          <div v-else class="menu-icon">
            <el-input v-model="searchPlace" placeholder="어디로 떠날까요?" @keyup.enter="showInput"></el-input>
            <icon-base viewBox="0 0 1024 1024" width="24" height="24" iconColor="#fff" icon-name="closeicon" @click="closeSearchBar">
              <close-icon/>
            </icon-base>
          </div>
        </el-menu-item>
        <el-menu-item index="2">
          <div @click="visible=true" class="menu-icon">
            <icon-base viewBox="0 0 1024 1024" width="24" height="24" iconColor="#fff" icon-name="menuicon">
              <menu-icon/>
            </icon-base>
          </div>
        </el-menu-item>
      </el-menu>
      <el-drawer v-model="visible" :show-close="true" :modal="false" size="70%">
        <!-- 로그인한 유저 -->
        <!-- <div v-if="isLoggedIn"> -->
        <div>
          <router-link :to="{ name: 'profile' }" @click="visible =false">
            <el-avatar :size="100" :src="profile.img_path" />
          </router-link>
          <p>{{ profile.name }}의 로그</p>
          <router-link :to="{ name: 'profileEdit' }" @click="visible =false">프로필 수정</router-link>
          <span> | 로그아웃</span>
          <hr>
          <router-link :to="{ name: 'diaryCreate' }" @click="visible =false">다이어리 작성</router-link>
          <br>
          <router-link :to="{ name: 'community' }" @click="visible =false">동행 구하기</router-link>
          <hr>
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
import SearchBar from '@/components/icon/SearchBar.vue'
import MenuIcon from '@/components/icon/MenuIcon.vue'
import CloseIcon from '@/components/icon/CloseIcon.vue'
import { mapGetters } from 'vuex'


export default {
  name: "NavBar",
  components: {
    SearchBar,
    MenuIcon,
    CloseIcon
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
    ...mapGetters(['isLoggedIn', 'profile'])
  },
	methods: {
    showSearchBar() {
      this.flag = 1
    },
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

.el-menu-demo {
  display: flex;
  align-items: center;
}

.menu-icon {
  display: flex;
  align-items: center;
}

.trippyLogo {
	font-family: 'Alfa Slab One', cursive;
	font-weight: 300;
	font-size: 20px;
  }

.flex-grow {
  flex-grow: 1;
}

</style>