<template>
  <div>
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
  </div>
</template>

<script>
export default {
  name: 'StoryForm',
  props: {
    storyForm: Object
  },
  data() {
    return {
      newStory: {
        place: this.storyForm.place,
        photo: this.storyForm.photp,
        content: this.storyForm.content,
        rate: this.storyForm.rate
      }
    }
  }
}
</script>

<style>

</style>