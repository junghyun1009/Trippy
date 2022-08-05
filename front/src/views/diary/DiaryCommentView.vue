<template>
  <div>
    COMMENTS

    <ul>
      <li v-for="comment in comments" :key="comment.pk">
        <comment-item :comment="comment"></comment-item>
      </li>
    </ul>

    <comment-form :diaryPk="this.diaryPk"></comment-form>

  </div>
</template>

<script>
import CommentForm from '@/components/diary/CommentForm.vue'
import CommentItem from '@/components/diary/CommentItem.vue'

export default {
    name: 'DiaryCommentView',
    components: {
      CommentForm,
      CommentItem,
    },
    data() {
      return {
        diaryPk: this.$store.getters.diary.pk
      }
    },
    props: {
      comments: Array,
    }
}
</script>

<style>
  li {
    list-style-type: none;
  }
</style>