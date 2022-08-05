<template>
  <div>

    <div class="email">
      <p>이메일</p>
      <el-input  id="email"
      v-model="userinfo.email" 
      placeholder="username@email.com"
      ></el-input>
    </div>

    <div class="nickname">
      <p>닉네임</p>
      <el-input id="nickname" v-model="userinfo.name" placeholder="사용할 별명을 입력해주세요" maxlength="10"  @blur="checkNickname"></el-input>
      <account-error-list :errorMessage="nicknameError" v-if="!nicknameFormat"></account-error-list>
    </div>

    <div class="gender">
      <p>성별</p>
      <el-select id="gender" v-model="userinfo.gender" class="m-2" placeholder="Select">
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
              id="birthdate"
              v-model="userinfo.birth"
              type="date"
              placeholder="생년월일을 선택해주세요!"
            />
        </div>
      </div>
    </div>

    <div class="description">
      <p>소개</p>
      <el-input id="description" v-model="userinfo.description" placeholder="원래내용" maxlength="50"></el-input>
      <account-error-list :errorMessage="nicknameError" v-if="!nicknameFormat"></account-error-list>
    </div>

    <br>
    <router-link :to="{ name: 'passwordChange' }">비밀번호 변경</router-link>
    <p class="delete" @click="open()">회원 탈퇴하기</p>
    <el-button type="primary">완료</el-button>


  </div>
</template>

<script>
import { userErrorMessage } from '@/common/constant.js'
import AccountErrorList from '@/components/account/AccountErrorList.vue'
import { mapActions } from 'vuex'
import { ElMessage, ElMessageBox } from 'element-plus'


export default {
  name: 'ProfileEditView',
  components: {
    AccountErrorList
  },
  data() {
          return {
              userinfo: {
                  email: '',
                  name: '',
                  gender: '',
                  birth: '',
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
              nicknameError: userErrorMessage.nicknameError,
              alreadyRegistered: userErrorMessage.alreadyRegistered,
              emailFormat: true,
              nicknameFormat: true,
          }
  },
  methods: {
    ...mapActions(['deleteAccount']),

    checkNickname() {
      var regNickname = /^([0-9]|[a-z]|[A-Z]|[가-힣]).{1,10}$/;
      var blank = /''/
      if (regNickname.test(this.userinfo.name)) {
        this.nicknameFormat = true
      } else if (blank.test(this.userinfo.name)) {
        this.nicknameFormat = true
      } else {
        this.nicknameFormat = false
      }
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
.delete {
  cursor: pointer;
  text-decoration: underline;
}

</style>