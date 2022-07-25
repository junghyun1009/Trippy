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
        pk: null,
        title: '',
        option: {
          datePick: '',
          partyType: '가족',
          memberNum: 1,
          transportationList: [],
        }
      }
    }
  }
}

</script>

<style scoped>

</style>