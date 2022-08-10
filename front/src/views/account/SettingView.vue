<template>
  <div>
    <p @click="goProfileEdit">
      프로필 수정
      <span class="material-symbols-outlined icon">arrow_forward_ios</span>
    </p>
    
    <p @click="goPasswordChange">
      비밀번호 변경
      <span class="material-symbols-outlined icon">arrow_forward_ios</span>
    </p>
    <p @click="logout">
      로그아웃
      <span class="material-symbols-outlined icon">arrow_forward_ios</span>
    </p>
    <p>
      회원 탈퇴
      <span class="material-symbols-outlined icon">arrow_forward_ios</span>
    </p>
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

<style scoped>
* {
  padding: 1rem;
}

p {
  margin: 0.8rem 0;
  display: flex;
  justify-content: space-between;
  align-items: center;
}

.icon {
  font-size: 1rem;
  padding: 0;
}
</style>