<template>
  <div>
    <div>
      <el-menu
        :default-active="activeIndex"
        class="el-menu-demo"
        mode="horizontal"
        :ellipsis="false"
      >
        <el-menu-item index="0"><span class="trippyLogo" @click="$router.push('/')">TRIPPY</span></el-menu-item>
        <div class="flex-grow" />
        <el-menu-item index="1">
          <el-button v-if="flag===0" @click="showSearchBar">search</el-button>
          <div v-else>
            <el-input v-model="searchPlace" placeholder="어디로 떠날까요?" @keyup.enter="showInput"></el-input>
            <el-button @click="closeSearchBar">x</el-button>
          </div>
        </el-menu-item>
        <el-menu-item index="2">
          <p @click="visible = true">
            Hamburger
          </p>
          <el-drawer v-model="visible" :show-close="true" :modal="false" size="70%">
            <!-- 로그인한 유저 -->
            <p>프로필 사진</p>
            <p>'유저 이름'의 로그</p>
            <router-link :to="{ name: 'profileEdit' }">프로필 수정</router-link>
            <span> | 로그아웃</span>
            <hr>
            <router-link :to="{ name: 'diaryCreate' }">다이어리 작성</router-link>
            <br>
            <router-link :to="{ name: 'community' }">동행 구하기</router-link>
            <hr>
            <!-- 로그인 안 한 유저 -->
            <router-link :to="{ name: 'login' }">로그인</router-link>
            <br>
            <router-link :to="{ name: 'signUp' }">회원가입</router-link>

          </el-drawer>
        </el-menu-item>
      </el-menu>

    </div>
  </div>
</template>

<script>
import { ref } from 'vue'


export default {
  name: "NavBar",
  data () {
    return {
      visible: false,
      flag: 0,
      searchPlace: '',
      activeIndex: ref('3')
    }
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
    }
	}


}
</script>

<style scoped>
@import url('https://fonts.googleapis.com/css2?family=Alfa+Slab+One&display=swap');

.trippyLogo {
	font-family: 'Alfa Slab One', cursive;
	font-weight: 300;
	font-size: 20px;
  }

.flex-grow {
  flex-grow: 1;
}

</style>