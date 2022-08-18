<template>
  <div>
    <form @submit.prevent="onSubmit">
      <div class="comment-form">
        {{ commentToEdit }}
        <!-- {{ originalContent }} -->
				<el-input class="comment-input" type="text" id="comment" v-model="originalContent" required/>
				<el-button class="btn" link @click="onSubmit">
          <span class="material-symbols-outlined">send</span>
        </el-button>
      </div>
    </form>
  </div>
</template>

<script>
import { mapActions } from 'vuex'

export default {
    name: 'CommentEditForm',

    props: {
      diaryPk: String,
      commentToEdit: Object
    },

    data() {
      return {
        originalContent: this.commentToEdit.info.content
      }
    },

    methods: {
      ...mapActions(['updateComment']),
      onSubmit() {
        // diary.pk diarydetail에서 props로 받아와서 같이 넘겨주기
        // console.log(this.commentToEdit)
        const payload = {
          id: this.commentToEdit.info.id,
          data: {
            content: this.originalContent,
            memberId: this.commentToEdit.info.memberId,
            postId: this.diaryPk
          }
        }
        this.updateComment(payload),
        this.content = ''
      }
    }
}
</script>

<style scoped>

button {
  margin-left: 12px;
}
.material-symbols-outlined {
  color: #F16B51;
}
.comment-form {
  display: flex;
  width: 90vw;
}
</style>