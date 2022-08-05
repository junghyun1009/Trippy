<template>
  <div>
    <div class="current_password" v-show="!fromPasswordFindView">
      <p>현재 비밀번호</p>
      <el-input v-model="userinfo.currentPassword" type="password" placeholder="비밀번호" show-password></el-input>
    </div>
    <!-- 만약 PasswordFindView에서 연결되면, 현재 비밀번호는 빼고 노출 -->
    <div class="new_password">
      <p>새 비밀번호</p>
      <el-input v-model="userinfo.newPassword"  id="password" type="password" placeholder="비밀번호" @blur="checkPasswordValidity"></el-input>
      <el-input v-model="userinfo.newPasswordCheck" type="password" placeholder="비밀번호 확인" @blur="checkPasswordMatch"></el-input>
              <el-button type="primary" @click="changePassword()">비밀번호 변경하기</el-button>
      <!-- 비밀번호와 비밀번호 확인되지 않으면 자동으로 매치되는지 확인하는 기능 -->
      <account-error-list :errorMessage="passwordValidityError" v-if="!passwordFormat"></account-error-list>
      <account-error-list :errorMessage="passwordMatchError" v-if="!passwordMatch"></account-error-list>

    </div>
  </div>
</template>

<script>
import AccountErrorList from '@/components/account/AccountErrorList.vue'
import { userErrorMessage } from '@/common/constant.js'

export default {
  name: "PasswordChangeView",
  components: {
    AccountErrorList,
  },
  data() {
    return {
      userinfo: {
        currentPassword: '',
        newPassword: '',
        newPasswordCheck: '',
      },
      fromPasswordFindView: this.$store.getters.fromPasswordFindView,
      passwordMatchError: userErrorMessage.passwordMatchError,
      passwordValidityError: userErrorMessage.passwordValidityError,
      passwordFormat: true,
      passwordMatch: true,

    }
  },
  methods: {
    checkPasswordMatch() {
      if (this.userinfo.newPassword === this.userinfo.newPasswordCheck) {
        this.passwordMatch = true
      } else {
        this.passwordMatch = false
      }
    },
    checkPasswordValidity() {
      var inputPassword = document.getElementById('password').value;
      var regPassword = /^(?=.*[A-Za-z])(?=.*\d)[A-Za-z\d]{8,20}$/;
      if (regPassword.test(inputPassword)) {
        this.passwordFormat = true
        } else {
          this.passwordFormat = false
        }
    },
    changePassword() {
      if (this.passwordFormat == true, this.passwordMatch == true) {
        alert('비밀번호가 변경되었습니다')
        this.$router.push('/login')
      }
    }
  }
}
</script>

<style>

</style>