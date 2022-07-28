<template>
  <div>
    <div class="current_password" v-show="!fromPasswordFindView">
      <p>현재 비밀번호</p>
      <el-input v-model="userinfo.currentPassword" type="password" placeholder="비밀번호" show-password></el-input>
    </div>
    <!-- 만약 PasswordFindView에서 연결되면, 현재 비밀번호는 빼고 노출 -->
    <div class="new_password">
      <p>새 비밀번호</p>
      <el-input v-model="userinfo.newPassword" type="password" placeholder="비밀번호" show-password></el-input>
      <el-input v-model="userinfo.newPasswordCheck" type="password" placeholder="비밀번호 확인" show-password></el-input>
              <el-button type="primary">인증확인</el-button>
      <!-- 비밀번호와 비밀번호 확인되지 않으면 자동으로 매치되는지 확인하는 기능 -->
      <account-error-list :errorMessage="passwordMatchError"></account-error-list>

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
      passwordMatchError: userErrorMessage.passwordMatchError
    }
  },
}
</script>

<style>

</style>