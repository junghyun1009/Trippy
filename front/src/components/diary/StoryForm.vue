<template>
  <div>
    <div v-for="(newStory, k) in newStories" :key="k">
      <div>
        <span>상세 장소</span>
        <el-input v-model="newStory.place" class="w-50 m-2" placeholder="상세 장소를 입력하세요." />
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
      <el-button @click="addStory()" :disabled="k != newStories.length - 1">add story</el-button>
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
          photo: '',
          content: '',
          rate: null
        }
      ]
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
        photo: addedOne.photo,
        content: addedOne.content,
        rate: addedOne.rate
      }
      this.createStory(newOne)
      this.newStories.push({
        place: '',
        photo: '',
        content: '',
        rate: null
      })
    },
    removeStory(index) {
      console.log(index)
      this.deleteStory(index)
      this.newStories.splice(index, 1)
    },
  }
}
</script>

<style>

</style>