<template>
  <div>
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
        startDate: '',
        endDate: '',
        company: '가족',
        count: 1,
        transport: [],
        routes: [],
        stories: []
      }
    }
  }
}

</script>

<style scoped>

</style>