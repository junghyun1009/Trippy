<template>
  <div>
    <div class="email">
      <p>이메일</p>
      <el-input  id="email"
      v-model="userinfo.email" 
      placeholder="username@email.com"
      ></el-input>
      <el-button type="primary" @click="checkEmail()">인증하기</el-button>
      <account-error-list :errorMessage="emailError" v-if="!emailFormat"></account-error-list>

    </div>

    <div class="password">
      <p>비밀번호</p>
      <el-input v-model="userinfo.password" 
        type="password" 
        id="password"
        placeholder="비밀번호" 
        autocomplete="off"
        maxlength="20"
        @blur="checkPasswordValidity"
        show-password
        />
      <el-input v-model="userinfo.passwordCheck" 
        type="password" 
        placeholder="비밀번호 확인" 
        autocomplete="off"
        maxlength="20"
        @blur="checkPasswordMatch"
        />

      <!-- 비밀번호와 비밀번호 확인되지 않으면 자동으로 매치되는지 확인하는 기능 -->
      <account-error-list :errorMessage="passwordValidityError" v-if="!passwordFormat"></account-error-list>
      <account-error-list :errorMessage="passwordMatchError" v-if="!passwordMatch"></account-error-list>
    </div>

    <div class="nickname">
      <p>닉네임</p>
      <el-input v-model="userinfo.nickname" id="nickname" placeholder="사용할 별명을 입력해주세요" maxlength="10"  @blur="checkNickname"></el-input>
      <account-error-list :errorMessage="nicknameError" v-if="!nicknameFormat"></account-error-list>
    </div>

    <div class="gender">
      <p>성별</p>
      <el-select v-model="userinfo.gender" class="m-2" id="gender" placeholder="Select">
      <el-option
        v-for="item in options"
        :key="item.value"
        :label="item.label"
        :value="item.value"
        />
      </el-select>
    </div>

    <div class="birthdate">
      <div class="demo-date-picker">
        <div class="block">
          <span class="demonstration">생년월일</span>
          <br>
            <el-date-picker
              v-model="userinfo.birthDate"
              id="birthdate"
              type="date"
              placeholder="생년월일을 선택해주세요!"
            />
        </div>
      </div>
    </div>

    <br>
    <el-button type="primary" @click="checkBlank()">회원가입</el-button>

  </div>
</template>

<script>
import AccountErrorList from '@/components/account/AccountErrorList.vue'
import { userErrorMessage } from '@/common/constant.js'

export default {
  components: { 
    AccountErrorList 
    },
    name: "SignUpView",
    data() {
        return {
            userinfo: {
                email: '',
                password: '',
                passwordCheck: '',
                nickname: '',
                gender: '',
                birthDate: '',
            },
            options: [
                {
                    value: 'Male',
                    label: '남성',
                },
                {
                    value: 'Female',
                    label: '여성',
                },
            ], 
            emailError: userErrorMessage.emailError,
            passwordMatchError: userErrorMessage.passwordMatchError,
            passwordValidityError: userErrorMessage.passwordValidityError,
            nicknameError: userErrorMessage.nicknameError,
            alreadyRegistered: userErrorMessage.alreadyRegistered,
            emailFormat: true,
            passwordFormat: true,
            passwordMatch: true,
            nicknameFormat: true,
        }
    },
    methods: {
      // toSignUpOption() {
      //   this.$router.push('/signup/option')
      // },
      checkEmail() {
      var inputEmail = document.getElementById('email').value;
      var regEmail = /^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*\.[a-zA-Z]{2,3}$/;
      if (regEmail.test(inputEmail) === false) {
        this.emailFormat = false;
      } else {
        this.emailFormat = true
      }        
      },
      checkPasswordMatch() {
        if (this.userinfo.password === this.userinfo.passwordCheck) {
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
      checkNickname() {
        var regNickname = /^([0-9]|[a-z]|[A-Z]|[가-힣]).{1,10}$/;
        if (regNickname.test(this.userinfo.nickname)) {
          this.nicknameFormat = true
        } else {
          this.nicknameFormat = false
        }
      },
      checkBlank() {
        var emailBlank = document.getElementById('email').value
        var passwordBlank = document.getElementById('password').value
        var nicknameBlank = document.getElementById('nickname').value
        var genderBlank = document.getElementById('gender').value
        var birthdateBlank = document.getElementById('birthdate').value
        if ( emailBlank == '' | passwordBlank == '' | nicknameBlank == '' | genderBlank == '' | birthdateBlank == '' ) {
          alert("빈 칸 없이 모든 필드를 채워주세요!")
        } else {
          this.$router.push('/signup/option')
        }
      }
    }
  }

</script>

<style scoped>
  .demo-date-picker {
    display: flex;
    width: 100%;
    padding: 0;
    flex-wrap: wrap;
  }
  .demo-date-picker .block {
    padding: 30px 0;
    text-align: center;
    border-right: solid 1px var(--el-border-color);
    flex: 1;
  }
  .demo-date-picker .block:last-child {
    border-right: none;
  }
  .demo-date-picker .demonstration {
    display: block;
    color: var(--el-text-color-secondary);
    font-size: 14px;
    margin-bottom: 20px;
  }
</style>