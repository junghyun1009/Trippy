<template>
  <div>
    <h1>Diary Create</h1>
    <diary-form></diary-form>
  </div>
</template>

<script>
import DiaryForm from "@/components/diary/DiaryForm.vue"

export default {
  name: 'DiaryCreateView',
  components: {
    DiaryForm,
  }
}

</script>

<style scoped>

</style>