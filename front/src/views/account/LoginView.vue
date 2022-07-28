<template>
  <div>
    <!-- 없는 회원정보 -->
    <el-input v-model="userinfo.description" placeholder="이메일" id="email" ></el-input>
    <br>
    <br>
    <el-input v-model="userinfo.password" type="password" placeholder="비밀번호" show-password></el-input>
    <br><br>
    <el-button type="primary" @click="checkMember(), checkEmail()">로그인</el-button>
    <br><br>
    <router-link :to="{ name: 'signUp' }">회원가입</router-link>
    |
    <router-link :to="{ name: 'passwordFind' }">비밀번호 찾기</router-link>
    <account-error-list :errorMessage="emailError" v-show="!emailFormat"></account-error-list>
    <account-error-list :errorMessage="notMember" v-show="!isMember"></account-error-list>
    <account-error-list :errorMessage="loginError" v-show="authentication == false"></account-error-list>
  </div>
</template>

<script>
import AccountErrorList from '@/components/account/AccountErrorList.vue'
import { userErrorMessage } from '@/common/constant.js'

export default {
    name: 'LoginView',
    components: {
        AccountErrorList
    },
    data() {
        return { userinfo: {
            email: '',
            password: '',
        },
        loginError: userErrorMessage.loginError,
        emailError: userErrorMessage.emailError,
        notMember: userErrorMessage.notMember,
        emailFormat: true,
        authentication: null,
        isMember: true,
      }
    },
    methods: {
      checkEmail() {
      var inputEmail = document.getElementById('email').value;
      var regEmail = /^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*\.[a-zA-Z]{2,3}$/;
      if (regEmail.test(inputEmail) === false) {
        this.emailFormat = false;
      } else {
        // 만약 이메일 형식이 같다면 로그인 화면으로 ㄱㄱ -- 이건 나중에 auth 되면 바꿔야 함
        this.$router.push('/')
        }        
      },  
      checkAuthentication() {
        if ( this.authentication == false) {
          this.authentication = false;
        }
      },
      // 일단 어떤 로직을 짜야할지 몰라서 ... 함수만 만들어놓음 (일단 다 넘어가긴 함)
      checkMember() {
        if (this.isMember == true) {
          this.isMember = false;
        }
      }
    }
}
</script>

<style>

</style>