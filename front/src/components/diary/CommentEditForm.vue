<template>
  <div>
    <form @submit.prevent="onSubmit">
      <div class="comment-form">
        <!-- {{ commentToEdit }} -->
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
export default {
  name: 'CommentEditForm',
  props: {
    diaryPk: Number,
    commentToEdit: Object
  },
  data() {
    return {
      originalContent: this.commentToEdit.info.content
    }
  },
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