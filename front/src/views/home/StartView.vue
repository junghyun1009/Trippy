<template>
  <div class="start">
    <video src="@/assets/ucc.mp4" autoplay loop muted></video>
    <div class="box">
      <span class="not-logo">Trip</span>
      <span class="not-logo">with</span>
      <span class="logo">Trippy</span>
      <el-button class="button" type="primary" @click="goLogin()">시작하기</el-button>
    </div>
  </div>
</template>

<script>
export default {
  name: 'StartView',
  methods: {
    goLogin() {
      this.$router.push({ name: 'login' })
    }
  }
}
</script>

<style scoped>
  video {
    width: 100%;
  }

  .box {
    display: flex;
    flex-direction: column;
    align-items: center;
    position: absolute;
    top: 35%;
    left: 35%;
  }

  .box span {
    font-family: 'Alfa Slab One', cursive;
  }

  .not-logo {
    font-size: 1.5em;
  }

  .logo {
    color: #F16B51;
    font-size: 2em;
  }

  .button {
    margin-top: 2rem;
    width: 100%;
  }

  .el-button--primary {
    --el-button-hover-bg-color: #F16B51;
    --el-button-hover-border-color: #F16B51;
    --el-button-active-color: #F16B51;
    --el-button-active-bg-color: #F16B51;
    --el-button-active-border-color: #F16B51;
  }
</style>