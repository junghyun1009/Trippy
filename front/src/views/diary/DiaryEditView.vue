<template>
  <div class="diary-create-div">
    <!-- <diary-form v-if="isDiary" :diary="diaryTemp" action="update"></diary-form> -->
    <diary-form :diary="diary" action="update"></diary-form>
  </div>
</template>

<script>
import DiaryForm from '@/components/diary/DiaryForm.vue'
import { mapActions, mapGetters } from 'vuex'

export default {
  name: 'DiaryEditView',
  components: {
    DiaryForm
  },
  computed: {
    ...mapGetters(['isDiary', 'diary'])
  },
  methods: {
    ...mapActions(['fetchDiary'])
  },
  created() {
    this.fetchDiary(this.$route.params.diaryPk)
  }
}
</script>

<style scoped>
.diary-create-div {
  margin: 1rem 1rem 2rem 1rem;
}
</style>