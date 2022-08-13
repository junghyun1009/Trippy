<template>
  <div class="diary-create-div">
    <!-- <diary-form v-if="isDiary" :diary="diaryTemp" action="update"></diary-form> -->
    <diary-edit-form :diary="diary" :story="convertStories" action="update"></diary-edit-form>
    <!-- {{ convertStories }} -->
  </div>
</template>

<script>
import DiaryEditForm from '@/components/diary/DiaryEditForm.vue'
import { mapActions, mapGetters } from 'vuex'

export default {
  name: 'DiaryEditView',
  components: {
    DiaryEditForm
  },
  data() {
    return {
      // images: []
    }
  },
  computed: {
    ...mapGetters(['isDiary', 'diary']),
    convertStories() {
      const convert = [];
      this.diary.detailLocations.forEach((story) => {
        console.log(story)
        const each = {}
        each.detailLocationName = story.detailLocationName
        each.detailLocationContent = story.detailLocationContent
        each.rating = story.rating
        // if (story.filename === null) {
        //   each.preview = ''
        //   this.images.push([])
        // } else {
        //   const url = story.filepath
        //   const file = async function urltofile () {
        //     const response = await fetch(url)
        //     const data = await response.blob()
        //     const ext = url.split(".").pop()
        //     const filename = url.split("/").pop()
        //     const metadata = { type: `image/${ext}`}
        //     return new File([data], filename, metadata)
        //   }
        //   console.log(file())
        //   each.preview = URL.createObjectURL(file)
        //   this.images.push(file)
        // }
        convert.push(each)
      })
      return convert
    },
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