<template>
  <div>

    <form @submit.prevent="signupOne(userData)">
    <div class="email">
      <div class="email-format">
        <p>이메일</p>
        <el-input 
        id="email"
        class="input"
        v-model="userData.email" 
        v-if="!emailAuthorized"
        placeholder="username@email.com"
        @blur="checkEmail()"
        ></el-input>
        <el-input class="input" id="email2" v-else v-model="userData.email" disabled></el-input>
        <div class="email-button">
          <el-button type="primary" @click="checkEmailDuplicate(userData), checkEmail()">중복확인</el-button>
          <el-button type="primary" v-if="!this.isDuplicate" v-model="verificationCode" @click="successMessage(), emailCodeSignUp(userData.email)">인증번호 받기</el-button>
          <el-button type="primary" v-if="this.isDuplicate" disabled>인증번호 받기</el-button>
        </div>
          <account-error-list :errorMessage="emailError" v-if="!emailFormat"></account-error-list>
      </div>
      <br>
      <div v-if="!emailAuthorized" class="email-verification-code">
        <p>인증번호 입력</p>
        <el-input class="input" v-model="verificationCode" placeholder="발송된 인증번호를 입력하세요"></el-input>

        <div class="email-button">
          <el-button type="primary" @click="emailAuth(verificationCode)">인증확인</el-button><br>
          <el-button type="primary" @click="successMessage(), emailCodeSignUp(userData.email)">인증번호 다시받기</el-button>        
        </div>
      </div>
    
    </div>

    <br>
    <div class="password">
      <p>비밀번호</p>
      <el-input v-model="userData.password" 
        type="password" 
        id="password"
        placeholder="비밀번호" 
        autocomplete="off"
        maxlength="20"
        class="input"
        @keyup="checkPasswordValidity"
        show-password
        />
      <p v-if="userData.password.length>=8 && passwordFormat">비밀번호를 사용해도 좋습니다.</p>
      <account-error-list :errorMessage="passwordValidityError" v-if="!passwordFormat"></account-error-list>
      <el-input v-model="passwordCheck" 
        type="password" 
        placeholder="비밀번호 확인" 
        autocomplete="off"
        maxlength="20"
        @keyup="checkPasswordMatch"
        />
      <p v-if="passwordMatch && userData.password.length>=8 && (userData.password.length===passwordCheck.length)">비밀번호가 일치합니다.</p>
      <account-error-list :errorMessage="passwordMatchError" v-if="!passwordMatch"></account-error-list>

      <!-- 비밀번호와 비밀번호 확인되지 않으면 자동으로 매치되는지 확인하는 기능 -->
    </div>

    <br>
    <div class="phone">
      <p>핸드폰 번호</p>
      <el-input  id="phone"
      class="input"
      v-model="userData.phone" 
      placeholder="010-1234-5678"
      @blur="checkPhone()"
      ></el-input>
      <account-error-list :errorMessage="phoneError" v-if="!phoneFormat"></account-error-list>
    </div>

    <div class="nickname">
      <p>닉네임</p>
      <el-input 
      id="nickname" 
      class="input"
      v-model="userData.name" 
      placeholder="사용할 별명을 입력해주세요" 
      maxlength="10"  @blur="checkNickname"></el-input>
      <account-error-list :errorMessage="nicknameError" v-if="!nicknameFormat"></account-error-list>
    </div>

    <br>
    <div class="gender">
      <p>성별</p>
      <el-select id="gender" v-model="userData.gender" class="m-2 input" placeholder="Select">
      <el-option 

        v-for="item in options"
        :key="item.value"
        :label="item.label"
        :value="item.value"
        />
      </el-select>
    </div>

    <br> 
    <div class="birthdate">
      <p>생년월일</p>
      <div class="demo-date-picker input m-2">
        <el-date-picker
          class="date-input"
          id="birthdate"
          v-model="date"
          type="date"
          placeholder="생년월일을 선택해주세요!"
        />
      </div>
    </div>
    <br>
    <el-button class="signup-button" type="primary" @click="checkBlank(), dateParsing(), signupOne(userData), youShallNotPass()">회원가입</el-button>
  </form>
  </div>
</template>

<script>
import AccountErrorList from '@/components/account/AccountErrorList.vue'
import { userErrorMessage } from '@/common/constant.js'
import { mapActions, mapGetters } from 'vuex'
import { ElMessage } from 'element-plus'

export default {
  components: { 
    AccountErrorList 
    },
    name: "SignUpView",
    data() {
        return {
            userData: {
                birth: '',
                email: '',
                gender: '',
                name: '',
                password: '',
                phone: '',
            },
            options: [
                {
                    value: 1,
                    label: '남성',
                },
                {
                    value: 0,
                    label: '여성',
                },
            ], 
            emailError: userErrorMessage.emailError,
            passwordMatchError: userErrorMessage.passwordMatchError,
            passwordValidityError: userErrorMessage.passwordValidityError,
            nicknameError: userErrorMessage.nicknameError,
            alreadyRegistered: userErrorMessage.alreadyRegistered,
            phoneError: userErrorMessage.phoneError,
            wrongVerificationCode: userErrorMessage.wrongVerificationCode,
            emailFormat: true,
            passwordFormat: true,
            phoneFormat: true,
            passwordMatch: true,
            nicknameFormat: true,
            passwordCheck: '',
            date: '',
            verificationCode: '',
            emailSent: false,
            pass: false,
            emailpass: false,
            passwordpass: false,
            passwordcheckpass: false,
            phonepass: false,
            nicknamepass: false,
            emailAuthorized: false,
        }
    },
    computed: {
      ...mapGetters(['isDuplicate']),
      totalPass() {
        let pass = this.pass
        pass = this.emailpass && this.passwordpass && this.phonepass && this.nicknamepass
        return pass
      }
      // 얘 뒤집어서 써야돼
    },
    methods: {
      ...mapActions(['signupOne', 'checkEmailDuplicate', 'emailCodeSignUp']),

      // 받아온 날짜를 ISO string 형식으로 변환해주는 함수
      dateParsing() {
        const birthdate = new Date(this.date)
        this.userData.birth = birthdate.toISOString()
      },

      // 이메일 형식 확인 regex
      checkEmail() {
      var inputEmail = document.getElementById('email').value;
      var regEmail = /^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*\.[a-zA-Z]{2,3}$/;
      if (regEmail.test(inputEmail) === false) {
        this.emailFormat = false,   
        this.emailpass = false;                   
      } else {
        this.emailFormat = true
        this.emailpass = true
      }        
      },

    // 받아온 인증번호와, 입력한 인증번호가 동일한지 확인
    emailAuth() {
      console.log(this.verificationCode)
      if ( !this.verificationCode ) {
        alert('인증번호를 입력하세요') 
      } else if ( this.$store.getters.verificationCode === this.verificationCode ){
        alert('인증이 완료되었습니다')
        this.emailAuthorized = true;
      } else {
        alert('인증번호가 일치하지 않습니다')
      }
    },

    successMessage() {
        ElMessage({
          message: '인증번호가 성공적으로 발송되었습니다',
          type: 'success',
      })
    },




      // 비밀번호 , 비밀번호 확인에 넣은 번호가 같은지 확인하는 함수
      checkPasswordMatch() {
        if (this.userData.password === this.passwordCheck) {
          this.passwordMatch = true
          this.passwordcheckpass = true
        } else {
          this.passwordMatch = false,
          this.passwordcheckpass = false
        }
      },

      // 비밀번호 형식 확인 regex
      checkPasswordValidity() {
        var inputPassword = document.getElementById('password').value;
        // 문자, 숫자, 그리고 최소 하나의 특수문자
        // var regPassword = /^(?=.*[0-9])(?=.*[a-zA-Z])(?=.*\\W)(?=\\S+$).{8,20}/;
        var regPassword = /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[$@$!%*?&])[A-Za-z\d$@$!%*?&]{8,20}/;
        if (regPassword.test(inputPassword)) {
          this.passwordFormat = true
          this.passwordpass = true
        } else {
          this.passwordFormat = false
          this.passwordpass = false
        }
      },

      // 휴대폰 형식 확인 regex
      checkPhone() {
        var inputPhone = document.getElementById('phone').value;
        var regPhone = /^01(?:0|1|[6-9])[.-]?(\d{3}|\d{4})[.-]?(\d{4})$/;
        if (regPhone.test(inputPhone)) {
          this.phoneFormat = true
          this.phonepass = true
        } else {
          this.phoneFormat = false
          this.phonepass = false
        }
      },

      // 닉네임 형식 확인 regex
      checkNickname() {
        var regNickname = /^([0-9]|[a-z]|[A-Z]|[가-힣]).{1,10}$/;
        var blank = /''/
        if (regNickname.test(this.userData.name)) {
          this.nicknameFormat = true
          this.nicknamepass = true
        } else if (blank.test(this.userData.name)) {
          this.nicknameFormat = true
          this.nicknamepass = true
        } else {
          this.nicknameFormat = false
          this.nicknamepass = false
        }
      },

      // 빈칸이 있는지 없는지 확인하는 함수
      checkBlank() {
        var emailBlank = document.getElementById('email2').value
        var passwordBlank = document.getElementById('password').value
        var phoneBlank = document.getElementById('phone').value
        var nicknameBlank = document.getElementById('nickname').value
        var genderBlank = document.getElementById('gender').value
        var birthdateBlank = document.getElementById('birthdate').value
        if ( emailBlank == '' | passwordBlank == '' | nicknameBlank == '' | genderBlank == '' | birthdateBlank == '' | phoneBlank == '') {
          alert("빈 칸 없이 모든 필드를 채워주세요!")
          console.log(this.userData)
        } 
      },

      // 회원가입2로 넘어갈 수 있는지 확인하는 함수
      // 중복되는 이메일을 가지고 있으면 안됨
      // 비밀번호 두 개가 같아야 함
      // 닉네임이 2글자 이상이어야 함
      youShallNotPass() {
        if ( this.totalPass ) { 
          console.log(this.totalPass)
          this.$router.push('/signup/option') 
        } else {
          console.log(this.totalPass)
          alert('형식에 맞는지 확인해주세요!')
        }
      }
    },
  }

</script>

<style scoped>

* {
  box-sizing: border-box;
  margin: 0; 
}

  .demo-date-picker {
    display: flex;
    width: 100%;
    padding: 0;
    flex-wrap: wrap;
  }

  .demo-date-picker .block:last-child {
    border-right: none;
  }

  button {
    width: 49%;
  }

  .signup-button {
    width: 100%;
    margin-top: 5%;
  }

  .email-button {
    display: flex;
    justify-content: space-between;
  }

  form {
    margin: 15% 3%;
  }

  p {
    display: flex;
  }

  .gender {
    display: flex;
    flex-direction: column;
  }

  .birthdate {
    display: flex;
    flex-direction: column; 
  }

  .input {
    margin: 2% 0;
  }

  .el-button+.el-button {
    margin-left: 0;
  }

</style>