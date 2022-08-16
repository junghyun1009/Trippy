<template>
  <div class="container">
    <div class="profile-image">
      <div v-if="profilePhoto && Object.keys(profilePhoto).length === 0 && profilePhoto.constructor === Object">
        <div class="add-photo">
          <label class="photo-icon" for="file">
            <div class="meterial-symbols-circle">
              <span class="material-symbols-outlined">add_a_photo</span><br>
            </div>
          </label>
          <input class="photo-input" type="file" id="file" ref="files" @change="uploadPhoto"/>
        </div>

      </div>

      <div v-else>
        <img :src="profilePhoto.preview" :alt="profilePhoto.preview"/>
        <br>
        <el-button @click="removePhoto">
          사진 삭제하기
        </el-button>
      </div>
    </div>

    <br>


    <div class="description">
      <p>소개</p>
      <el-input v-model="userinfo.description" placeholder="자신을 소개해주세요! 최대 50자 (선택)" maxlength="50"></el-input>
    </div>
    <!-- 완료하면 로그인 페이지로 이동 -->
    <el-button type="primary" @click="finishSignUp(), mergeObjects(), signupTwo(userData)">완료</el-button>
  
  

    <!-- '여행의 시작' 뱃지 팝업 창 -->
    <el-drawer v-model="badgePopup" direction="btt" size="50%">
      <template #header>
        <h2>회원가입을 축하합니다!</h2>
      </template>
      <template #default>
        <img src="@/assets/badge-start.png" alt="globe">
        <p>여행의 시작 뱃지를 획득하셨어요!</p>
        <p>로그인 후 획득한 뱃지를 확인하실 수 있어요</p>
      </template>
    </el-drawer>
  
  
  </div>
</template>

<script>
import { mapActions } from 'vuex'
import { ElMessageBox } from 'element-plus'

export default {
  name: "SignUpOptionView",

  data() {
      return { 
        userinfo: {
          description: '',
          img_path: ''
        },
        profilePhoto: {},
        userData: {},
        badgePopup: false,
    }
  },

  methods: {
    ...mapActions(['signupTwo']),

    mergeObjects(){
      // 첫번째 signup 페이지에서의 data(객체)와 
      // 두번째 signup 페이지에서의 data(객체)를 merge해서
      // 새로운 userData 객체로 만듦
      // 이 객체를 axios 해서 보낼 것
      const dataOne = this.$store.getters.userData
      this.userData = Object.assign(dataOne, this.userinfo)
    },

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
        alert("사진 파일만 추가 가능합니다")
      }

      let fileInput = document.getElementById("file")
      fileInput.value = ''
    },

    removePhoto() {
      this.profilePhoto = {}
    },
    
    finishSignUp() {
      ElMessageBox.alert('여행의 시작 뱃지를 획득하셨어요! 로그인 후 획득한 뱃지를 확인하실 수 있어요', 
      '회원가입을 축하합니다!', {
        confirmButtonText: 'OK'
      })
      this.$router.push('/login')
    },
    
  }
}
</script>

<style scoped>
.container {
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
}

.profile-image {
  margin-top : 25%;
}

img {
  width: 150px;
  height: 150px;
  border-radius: 50%;
}


.description {
  width: 100%;
}

.description p {
  margin: 2%;
}

.el-input {
  width: 90%;
  overflow: hidden;
}

button {
  width: 90%;
  margin: 0;
  margin-top: 2%;
}

.photo-input {
  visibility: hidden;
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

.add-photo {
  display: flex;
  flex-wrap: wrap;
  justify-content: center;
  align-items: center;
}
</style>