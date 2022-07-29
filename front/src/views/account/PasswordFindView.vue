<template>
  <div>
    <!-- 이메일로 인증번호 받기 전 "beforeEmailVerification" 노출 -->
    <div v-if="!userinfo.verificationCode" class="email">
      <h4>이메일</h4>
      <p>trippy에 가입한 이메일을 작성해주세요</p>
      <el-input v-model="userinfo.email" type="email" id="email" placeholder="username@email.com" @blur="checkEmail()"></el-input>
      <el-button type="primary" @click="countdownTimer(), addCode()">인증번호 받기</el-button>
      <account-error-list :errorMessage="emailError" v-if="!emailFormat"></account-error-list>
    </div>

    <!-- 이메일로 인증번호 보낸 이후 노출 -->
    <div v-else>
      <div class="verification_code">
        <span>인증번호 입력</span>
        <!-- 3분 카운트 하는 시계 -->
        <span>{{ resTimeData }}</span>
        <el-input v-model="userinfo.verificationCode" placeholder="발송된 인증번호를 입력하세요"></el-input>
        <!-- 3분 지나면 인증확인 버튼 disable되게 -->
        <el-button type="primary" v-if="this.timeCounter == 0" disabled>인증확인</el-button>
        <el-button type="primary" v-else-if="this.timeCounter > 0" @click="fromPasswordFindView()">인증확인</el-button>
        <el-button type="primary" @click="countdownReset(), addCode(), successMessage()">인증번호 다시받기</el-button>        
      </div>

      <br>
      <div class="verification_guide">
        <span style="color: red">입력하신 이메일로 인증번호를 보냈습니다.</span><br>
        <span style="color: red">발송된 인증번호를 3분내로 입력하세요.</span>
        <br><br>
        <span>인증 번호를 받지 못하셨나요?</span><br>
        <span> - 이메일 주소를 올바르게 입력했는지 확인해주세요</span><br>
        <span> - 스팸 메일함을 확인해주세요</span>
      </div>
  
      <account-error-list :userErrorMessage="wrongVerificationCode"></account-error-list>
    </div>

  </div>
</template>

<script>
import { mapActions } from 'vuex'
import AccountErrorList from '@/components/account/AccountErrorList.vue'
import { ElMessage } from 'element-plus'
import { userErrorMessage } from '@/common/constant.js'

export default {
    name: "PasswordFindView",
    components: {
        AccountErrorList,
    },
    data() {
        return {
            userinfo: {
                email: '',
                verificationCode: false,
            },
            timeCounter: 180,
            resTimeData: '',
            wrongVerificationCode: userErrorMessage.wrongVerificationCode,
            emailError: userErrorMessage.emailError,
            emailFormat: true,
        }
    },
	methods: {
    ...mapActions(['fromPasswordFindView']),

    checkEmail() {
    var inputEmail = document.getElementById('email').value;
    var regEmail = /^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*\.[a-zA-Z]{2,3}$/;
    if (regEmail.test(inputEmail) === false) {
      this.emailFormat = false;
    } else {
      this.emailFormat = true
    }        
    },

    // 이거는 verification code 보냈을 때 분기할 수 있게 가라로 만들어 놓은거
    // 나중에 back에서 보내오는 정보에 따라서 코드 수정 필요
    addCode() {
      if ( this.emailFormat == false ) {
        this.userinfo.verificationCode = false
      } else {
        this.userinfo.verificationCode = true
      }
    },
    countdownTimer() {
      if (this.timeCounter >= 0) {
        setTimeout(() => {
          this.timeCounter -= 1
          this.countdownTimer()
          this.resTimeData = this.prettyTime()
        }, 1000)
      } else {
        this.timeCounter = 0
      }
    },
    countdownReset() {
      if (this.timeCounter <= 0){
        this.timeCounter = 180
        this.countdownTimer()
      } else {
        this.timeCounter = 180
      }
    },
    pad(n, width) {
          n = n + '';
          return n.length >= width ? n : new Array(width - n.length + 1).join('0') + n
        },
    prettyTime() {
      let time = this.timeCounter / 60
      let minutes = parseInt(time)
      let seconds = Math.round((time - minutes)*60)
      return this.pad(minutes, 2) + ":" + this.pad(seconds, 2)
    },
    successMessage() {
        ElMessage({
          message: '인증번호가 성공적으로 발송되었습니다',
          type: 'success',
      })
    },
	}
}
</script>

<style>

</style>