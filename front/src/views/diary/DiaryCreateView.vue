<template>
  <div class="diary-create-div">
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
.diary-create-div {
  margin: 1rem 1rem 2rem 1rem;
}
</style>