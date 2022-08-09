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
    <el-button type="primary" @click="finishSignUp()">완료</el-button>
  </div>
</template>

<script>
// import AddPhoto from '@/components/icon/AddPhoto.vue'

export default {
  name: "SignUpOptionView",
  // components: {
  //   AddPhoto,
  // },
  data() {
      return { 
        userinfo: {
          description: '',
        },
        profilePhoto: {},
    }
  },
  methods: {
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
      this.$router.push('/login')
      alert('회원가입을 축하합니다!')
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