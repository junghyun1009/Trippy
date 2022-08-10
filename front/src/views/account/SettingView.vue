<template>
  <div>
    <p @click="goProfileEdit">프로필 수정</p>
    <p @click="goPasswordChange">비밀번호 변경</p>
    <p @click="logout">로그아웃</p>
    <p>회원 탈퇴</p>
  </div>
</template>

<script>
import { mapActions } from 'vuex'

export default {
  name: 'SettingView',
  methods: {
    ...mapActions(['logout']),
    goProfileEdit() {
      this.$router.push({ name: 'profileEdit' })
    },
    goPasswordChange() {
      this.$router.push({ name: 'passwordChange' })
    },
  }
}
</script>

<style>

</style>