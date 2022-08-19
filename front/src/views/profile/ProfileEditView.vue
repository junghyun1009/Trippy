<template>
  <div class="container">
    <!-- {{ currentUser }} -->

    <div class="image">
      <!-- <div v-if="profilePhoto && Object.keys(profilePhoto).length === 0 && profilePhoto.constructor === Object">
        <div>이미지</div>
        <div>
          <label for="file">사진 등록</label>
          <input type="file" id="file" ref="files" @change="uploadPhoto"/>
        </div>
      </div> -->
      <!-- 원래 등록되어 있는 이미지 -->
      <div class="profile-img">
        <el-avatar v-if="!isChange" :size="150" :src="currentUser.img_link" />

        <div v-else-if="isChange&& profilePhoto && Object.keys(profilePhoto).length === 0 && profilePhoto.constructor === Object" class="add-photo">
          <label class="photo-icon" for="file">
            <div class="meterial-symbols-circle">
              <span class="material-symbols-outlined">add_a_photo</span><br>
            </div>
          </label>
          <input class="photo-input" type="file" id="file" ref="files" @change="uploadPhoto"/>
        </div>

        <div v-else>
          <el-avatar :size="150" :src="profilePhoto.preview" />
        </div>

        <br>
        <el-button v-if="!isChange" @click="isChange=true">사진 변경하기</el-button>
        <el-button v-else @click="isChange=false">취소하기</el-button>

      </div>
    </div>

    <div class="profil-edit-input">
      <div class="email">
        <p>이메일</p> 
        <el-input  id="email"
        v-model="userinfo.email" 
        placeholder="username@email.com"
        disabled></el-input>
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

      <div class="phone">
        <p>핸드폰 번호</p>
        <el-input id="phone"
        class="input"
        v-model="userinfo.phone" 
        placeholder="010-1234-5678"
        @blur="checkPhone()"
        ></el-input>
        <account-error-list :errorMessage="phoneError" v-if="!phoneFormat"></account-error-list>
      </div>

      <div class="birthdate">
        <p>생년월일</p>
        <div class="demo-date-picker">
            <el-date-picker
              id="birthdate"
              v-model="userinfo.birth"
              type="date"
              placeholder="생년월일을 선택해주세요!"
            />
        </div>
      </div>

      <div class="description">
        <p>소개</p>
        <el-input id="description" v-model="userinfo.description" maxlength="50"></el-input>
        <account-error-list :errorMessage="nicknameError" v-if="!nicknameFormat"></account-error-list>
      </div>
    </div>
      <el-button type="primary" @click="onSubmit">완료</el-button>
  </div>
</template>

<script>
import { userErrorMessage } from '@/common/constant.js'
import AccountErrorList from '@/components/account/AccountErrorList.vue'
import { ElMessageBox } from 'element-plus'
import { mapActions, mapGetters, } from 'vuex'

export default {
  name: 'ProfileEditView',
  components: {
    AccountErrorList
  },
  data() {
    return {
        isChange: false,
        userinfo: {
            email: this.$store.getters.currentUser.email,
            name: this.$store.getters.currentUser.name,
            gender: this.$store.getters.currentUser.gender,
            birth: this.$store.getters.currentUser.birth,
            description: this.$store.getters.currentUser.description,
            img_link: this.$store.getters.currentUser.img_link,
            img_path: this.$store.getters.currentUser.img_path,
            phone: this.$store.getters.currentUser.phone
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
        nicknameError: userErrorMessage.nicknameError,
        alreadyRegistered: userErrorMessage.alreadyRegistered,
        emailFormat: true,
        nicknameFormat: true,
        profilePhoto: {},
        phoneError: userErrorMessage.phoneError,
        phoneFormat: true,
    }
  },
  computed: {
    ...mapGetters(['currentUser', 'profileImg'])
  },
  methods: {
    ...mapActions(['fetchCurrentUser', 'changeProfileImg', 'changeProfile']),

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

    // 원래 저장되어 있던 프로필 사진이 나타나게 해야함
    uploadPhoto() {
      console.log(this.$refs.files.files[0])
      let photo = this.$refs.files.files[0]
      if (photo.type.substr(0, 5) === "image") {
        this.profilePhoto = 
        {
          file: this.$refs.files.files[0],
          preview: URL.createObjectURL(this.$refs.files.files[0]),
        }
      } else {
        ElMessageBox.alert("사진 파일만 추가 가능합니다", '알림', {
          confirmButtonText: 'OK',
        })
      }

      let fileInput = document.getElementById("file")
      fileInput.value = ''

      const profileImg = new FormData()
      profileImg.append('file', this.profilePhoto.file)
      for (var key of profileImg.keys()) {
        console.log(key);
      }
      for (var value of profileImg.values()) {
        console.log(value);
      }
      console.log(profileImg)
      this.changeProfileImg(profileImg)
    },

    // removePhoto() {
    //   this.profilePhoto = {}
    // },
    onSubmit() {
      if (this.userinfo.gender==='여자') {
        this.userinfo.gender = 0
      } else {
        this.userinfo.gender = 1
      }
      // 프로필 사진 정보 저장
      // 프로필 사진 변경 안 함
      if (this.isChange===false) {
        this.userinfo.img_path = this.currentUser.img_path
        this.userinfo.img_link = this.currentUser.img_link
      }
      // 프로필 사진 변경함
      else {
        this.userinfo.img_path = this.profileImg.fileName
        this.userinfo.img_link = this.profileImg.fileUrl
      }
      const payload = {
        userId: this.currentUser.id,
        info: this.userinfo
      }
      this.changeProfile(payload)
      ElMessageBox.alert('회원정보 수정이 완료되었습니다.', '알림', {
        confirmButtonText: 'OK',
      }) 
    }

  },
  created() {
    this.fetchCurrentUser()
    // this.genderParsing()
  },
  mounted() {
    this.fetchCurrentUser()
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
    margin: 0;
  }

  .photo-input {
    visibility: hidden;
    height: 0;
  }

  .profile-img {
    display: flex;
    flex-direction: column;
    align-items: center;
  }

  .add-photo {
  display: flex;
  /* flex-wrap: wrap; */
  flex-direction: column;
  justify-content: center;
  align-items: center;
  }

  .material-symbols-outlined {
  font-size: 8vh;
  }

  .meterial-symbols-circle {
    background-color: rgb(220, 220, 220);
    border-radius: 50%;
    display: flex;
    justify-content: center;
    align-items: center;
    width: 150px;
    height: 150px;
  }

  .delete {
    cursor: pointer;
    text-decoration: underline;
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

  .el-input__wrapper {
    width: 100%;
  }

  .change-delete {
    display: flex;
    justify-content: center;
    color: #F16B51;
  }

  .change-delete a {
    color: #F16B51;
    font-weight: 500;
  }

  .change-delete p {
    color: #F16B51;
    font-weight: 500;
    
  }

</style>