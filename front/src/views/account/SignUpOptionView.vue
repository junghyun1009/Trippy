<template>
  <div>
    <div class="image">
      <div v-if="profilePhoto && Object.keys(profilePhoto).length === 0 && profilePhoto.constructor === Object">
        <div>이미지</div>
        <div>
          <label for="file">사진 등록</label>
          <input type="file" id="file" ref="files" @change="uploadPhoto"/>
        </div>
      </div>
      <div v-else>
        <img :src="profilePhoto.preview" :alt="profilePhoto.preview"/>
        <br>
        <el-button @click="removePhoto">
          x
        </el-button>
      </div>
    </div>


    <div class="description">
      <p>소개</p>
      <el-input v-model="userinfo.description" placeholder="자신을 소개해주세요!(선택)"></el-input>
    </div>

    <br>
    <!-- 완료하면 로그인 페이지로 이동 -->
    <el-button type="primary" @click="$router.push('/login')">완료</el-button>
  </div>
</template>

<script>

export default {
  name: "SignUpOptionView",
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
    
  }
}
</script>

<style scoped>

img {
  width: 150px;
  height: 150px;
  border-radius: 50%;
}

</style>