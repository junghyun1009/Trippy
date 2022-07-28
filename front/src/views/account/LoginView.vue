<template>
  <div>
    <el-input v-model="userinfo.description" placeholder="이메일"></el-input>
    <br>
    <br>
    <el-input v-model="userinfo.password" type="password" placeholder="비밀번호" show-password></el-input>
    <br><br>
    <el-button type="primary">로그인</el-button>
    <br><br>
    <router-link :to="{ name: 'signUp' }">회원가입</router-link>
    |
    <router-link :to="{ name: 'passwordFind' }">비밀번호 찾기</router-link>
    <account-error-list :errorMessage="emailError"></account-error-list>
    <account-error-list :errorMessage="loginError"></account-error-list>
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
    }
}

}
</script>

<style>

</style>