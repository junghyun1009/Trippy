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
    <p @click="open(), deleteAccount()">
      회원 탈퇴
      <span class="material-symbols-outlined icon">arrow_forward_ios</span>
    </p>
  </div>
</template>

<script>
import { mapActions } from 'vuex'
import { ElMessage, ElMessageBox } from 'element-plus'

export default {
  name: 'SettingView',
  methods: {
    ...mapActions(['logout', 'deleteAccount']),
    goProfileEdit() {
      this.$router.push({ name: 'profileEdit' })
    },
    goPasswordChange() {
      this.$router.push({ name: 'passwordChange' })
    },
    open() {
      ElMessageBox.confirm(
        '정말로 탈퇴하시곘습니까?',
        'Warning',
        {
          confirmButtonText: 'OK',
          cancelButtonText: 'Cancel',
          type: 'warning',
        }
      )
      .then(() => {
        ElMessage({
          type: 'success',
          message: '탈퇴가 완료되었습니다',
        })
        this.deleteAccount()
      })
      .catch(() => {
        ElMessage({
          type: 'info',
          message: '탈퇴가 취소되었습니다',
        })
      })
    }

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