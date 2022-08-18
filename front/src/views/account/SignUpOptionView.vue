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
      <el-button type="primary" @click="mergeObjects()">완료</el-button>
    </div>
    <!-- 완료하면 로그인 페이지로 이동 -->
  

  </div>
</template>

<script>
import ElMessageBox from 'element-plus'
import { mapActions } from 'vuex'

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
    ...mapActions(['signupTwo', 'sendPhoto']),

    mergeObjects(){
      // 첫번째 signup 페이지에서의 data(객체)와 
      // 두번째 signup 페이지에서의 data(객체)를 merge해서
      // 새로운 userData 객체로 만듦
      // 이 객체를 axios 해서 보낼 것
      // 사진도 formdata로 변환해서 데이터랑 묶어서 보낼 것
      const dataOne = this.$store.getters.userData
      // this.userinfo.img_path = file
      const profileData = new FormData()
      // 얘가 사진 뺀 내용 -> Blob으로 바꿔서 보내기
      this.userData = Object.assign(dataOne, this.userinfo)
      profileData.append("member", new Blob([JSON.stringify(this.userData)], {type: "application/json"}))
      // 얘가 사진
      profileData.append("image", this.profilePhoto.file)

      console.log(this.userData)
      for (var key of profileData.keys()) {
        console.log(key);
      }
      for (var value of profileData.values()) {
        console.log(value);
      }

      this.signupTwo(profileData)
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
        console.log(this.profilePhoto)
      } else {
        ElMessageBox.alert("사진 파일만 추가 가능합니다", "알림", {
          confirmButtonText: 'OK',
        })
      }

      let fileInput = document.getElementById("file")
      fileInput.value = ''
    },

    removePhoto() {
      this.profilePhoto = {}
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
  margin-left: 10%;
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