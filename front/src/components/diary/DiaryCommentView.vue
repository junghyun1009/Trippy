<template>
  <div>
    <el-drawer v-model="isClicked" direction="btt">
      <template #header>
        <h4>COMMENTS</h4>
      </template>
      <template #default>
        <div>
          <ul>
            <li v-for="comment in comments" :key="comment.pk">
              <comment-item :comment="comment"></comment-item>
            </li>
          </ul>
        </div>
      </template>
      <template #footer>
        <div style="flex: auto">
          <comment-form :diaryPk="this.diaryPk"></comment-form>
        </div>
      </template>
    </el-drawer>
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
        diaryPk: this.$store.getters.diary.pk,
        isClicked: true
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