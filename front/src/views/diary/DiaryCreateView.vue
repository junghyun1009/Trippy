<template>
  <div>
    <h1>Diary Create</h1>
    <diary-form :diary="diary" action="create"></diary-form>
  </div>
</template>

<script>
import DiaryForm from "@/components/diary/DiaryForm.vue"

export default {
  name: 'DiaryCreateView',
  components: {
    DiaryForm,
  },
  data() {
    return {
      diary: {
        title: '',
        season: [],
        company: '가족',
        count: 1,
        transport: [],
      }
    }
  }
}

</script>

<style scoped>

</style>