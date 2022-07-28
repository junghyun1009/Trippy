<template>
  <div>
    <div v-for="(newStory, k) in newStories" :key="k">
      <div>
        <span>상세 장소</span>
        <el-input v-model="newStory.place" class="w-50 m-2" placeholder="상세 장소를 입력하세요." />
      </div>

      <div>
        <div>사진 등록</div>
        <div v-if="!newStory.photoList.length">
          <div>이미지</div>
            <div>
              <label for="file">사진 등록</label>
              <input type="file" id="file" ref="files" @change="uploadPhoto(k)" multiple />
            </div>
        </div>
        <div v-else>
          <div v-for="(photo, index) in newStory.photoList" :key="index" class="file-preview-wrapper">
            <el-button @click="removePhoto(k, index)">
              x
            </el-button>
            <img :src="photo.preview" :alt="photo.preview"/>
          </div>
          <div>
            <label for="file">추가 사진 등록</label>
            <input type="file" id="file" ref="files" @change="addPhoto(k)" multiple />
          </div>
        </div>
      </div>

      <div>
        <el-input v-model="newStory.content" maxlength="500"
        placeholder="내용을 입력해주세요." show-word-limit type="textarea"/>
      </div>
      <div>
        <span>별점</span>
        <el-rate v-model="newStory.rate" allow-half />
      </div>
      <el-button @click="removeStory(k)" v-show="(stories.length >= 1) && k!=0">delete</el-button>
      <el-button @click="addStory()" v-show="stories.length <= 10" :disabled="k != newStories.length - 1">add story</el-button>
      <hr>
    </div>
  </div>
</template>

<script>
import { mapActions, mapGetters } from 'vuex'
export default {
  name: 'StoryForm',
  data() {
    return {
      newStories: [
        {
          place: '',
          photoList: [],
          content: '',
          rate: null
        }
      ],
    }
  },
  computed: {
    ...mapGetters(['stories'])
  },
  methods: {
    ...mapActions(['createStory', 'deleteStory']),
    addStory() {
      const addedOne = this.newStories[this.newStories.length - 1]
      const newOne = {
        place: addedOne.place,
        photoList: addedOne.photoList,
        content: addedOne.content,
        rate: addedOne.rate
      }
      this.createStory(newOne)
      this.newStories.push({
        place: '',
        photoList: [],
        content: '',
        rate: null
      })
    },
    removeStory(index) {
      // console.log(index)
      this.deleteStory(index)
      this.newStories.splice(index, 1)
    },
    uploadPhoto(index) {
      let addedPhotoList = this.newStories[index].photoList
      // console.log(this.$refs.files)
      // console.log(this.$refs.files[index].files)
      for (let i = 0; i < this.$refs.files[index].files.length; i++) {
        let photo = this.$refs.files[index].files[i]
        console.log(photo)
        if (photo.type.substr(0, 5) === "image") {
          addedPhotoList = [
            ...addedPhotoList,
            {
              file: this.$refs.files[index].files[i],
              preview: URL.createObjectURL(this.$refs.files[index].files[i]),
            }
          ]
        } else {
          alert("사진 파일만 추가 가능합니다")
        }
      }
      this.newStories[index].photoList = addedPhotoList
      let fileInput = document.getElementById("file")
      fileInput.value = ''
      console.log(this.newStories[index].photoList)
    },
    removePhoto(index, num) {
      this.newStories[index].photoList.splice(num, 1)
    },
    addPhoto(index) {
      let addedPhotoList = this.newStories[index].photoList
      // console.log(this.$refs.files)
      // console.log(this.$refs.files[index].files)
      for (let i = 0; i < this.$refs.files[index].files.length; i++) {
        let photo = this.$refs.files[index].files[i]
        if (photo.type.substr(0, 5) === "image") {
          addedPhotoList = [
            ...addedPhotoList,
            {
              file: this.$refs.files[index].files[i],
              preview: URL.createObjectURL(this.$refs.files[index].files[i]),
            }
          ]
        } else {
          alert("사진 파일만 추가 가능합니다")
        }
      }
      this.newStories[index].photoList = addedPhotoList
      let fileInput = document.getElementById("file")
      fileInput.value = ''
    },
  }
}
</script>

<style>
.file-preview-wrapper>img {
    position: relative;
    width: 190px;
    height: 130px;
    z-index: 10;
}
</style>