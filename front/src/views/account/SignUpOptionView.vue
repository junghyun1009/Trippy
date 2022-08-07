<template>
  <div class="container">

    <div class="profile-image">
      <div v-if="profilePhoto && Object.keys(profilePhoto).length === 0 && profilePhoto.constructor === Object">
        <div>
          <icon-base viewBox="0 0 1024 1024" width="50" height="50" iconColor="#409EFF" icon-name="addphoto" class="photo-icon">
            <add-photo/>
          </icon-base>
        </div>
        <br>
        <div>
          <label for="file">사진 등록</label>
          <input type="file" id="file" ref="files" @change="uploadPhoto"/>
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
import AddPhoto from '@/components/icon/AddPhoto.vue'

export default {
  name: "SignUpOptionView",
  components: {
    AddPhoto,
  },
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

img {
  width: 150px;
  height: 150px;
  border-radius: 50%;
}

.photo-icon {
  margin-top: 30px;
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

</style>