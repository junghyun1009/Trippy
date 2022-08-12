<template>
  <div class="container">
    <div class="current-password" v-show="!fromPasswordFindView">
      <h3>현재 비밀번호</h3>
      <el-input v-model="userinfo.currentPassword" type="password" placeholder="비밀번호" @click="PasswordVerification()" show-password></el-input>
    </div>
    <!-- 만약 PasswordFindView에서 연결되면, 현재 비밀번호는 빼고 노출 -->
    <div class="new-password">
      <h3>새 비밀번호</h3>
      <el-input v-model="userinfo.newPassword" v-if="currentPasswordVerified" id="password" type="password" placeholder="비밀번호" @blur="checkPasswordValidity"></el-input>
      <el-input v-model="userinfo.newPassword" v-if="!currentPasswordVerified" id="password" type="password" placeholder="비밀번호" @blur="checkPasswordValidity" disabled></el-input>
      <el-input v-model="userinfo.newPasswordCheck" v-if="currentPasswordVerified" type="password" placeholder="비밀번호 확인" @blur="checkPasswordMatch"></el-input>
      <el-input v-model="userinfo.newPasswordCheck" v-if="!currentPasswordVerified" type="password" placeholder="비밀번호 확인" @blur="checkPasswordMatch" disabled></el-input>
      <!-- 비밀번호와 비밀번호 확인되지 않으면 자동으로 매치되는지 확인하는 기능 -->
      <account-error-list :errorMessage="passwordValidityError" v-if="!passwordFormat"></account-error-list>
      <account-error-list :errorMessage="passwordMatchError" v-if="!passwordMatch"></account-error-list>
      <el-button type="primary" @click="changePassword()">비밀번호 변경하기</el-button>

    </div>
  </div>
</template>

<script>
import AccountErrorList from '@/components/account/AccountErrorList.vue'
import { userErrorMessage } from '@/common/constant.js'
import { mapActions } from 'vuex'

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
      currentPasswordVerified: false,

    }
  },
  methods: {
    ...mapActions(['changePassword']),

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

    PasswordVerification() {
      const currentPassword = this.$store.getters.userData.password
      if ( currentPassword !== this.userinfo.currentPassword ) {
        alert('비밀번호를 다시 한 번 확인해주세요')
      }
    }
  }
}
</script>

<style scoped>
  * {
    box-sizing: border-box;
    margin: 2%;
    }

  .container {
    display: flex;
    flex-direction: column;
    justify-content: center;
    margin-top: 40%;
  }

  button {
    width: 100%;
    margin-top: 20%;
  }

  h3 {
    display: flex;
    color: #F16B51;
    font-weight: 500;
  }


</style>