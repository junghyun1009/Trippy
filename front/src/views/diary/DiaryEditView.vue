<template>
  <div>
    <h1>Diary Edit</h1>
    <!-- <diary-form v-if="isDiary" :diary="diaryTemp" action="update"></diary-form> -->
    <diary-form :diary="diaryTemp" action="update"></diary-form>
  </div>
</template>

<script>
import DiaryForm from '@/components/diary/DiaryForm.vue'
import { mapGetters } from 'vuex'

export default {
  name: 'DiaryEditView',
  components: {
    DiaryForm
  },
  computed: {
    ...mapGetters(['isDiary', 'diaryTemp'])
  }
}
</script>

<style>

</style>