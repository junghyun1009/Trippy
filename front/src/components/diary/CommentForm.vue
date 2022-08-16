<template>
  <div>
    <form @submit.prevent="onSubmit">
      <div class="comment-form">
				<el-input class="comment-input" type="text" id="comment" v-model="content" required/>
				<el-button class="btn" link @click="onSubmit">
          <span class="material-symbols-outlined">send</span>
        </el-button>
			</div>

    </form>
  </div>
</template>

<script>
import { mapActions, mapGetters } from 'vuex'

export default {
    name: 'CommentForm',
    props: {
      diaryPk: String,
    },
    data() {
      return {
        content: '',
      }
    },
    computed: {
      ...mapGetters(['profile']),
    },
		methods: {
			...mapActions(['createComment', 'fetchProfile']),
			onSubmit() {
        // diary.pk diarydetail에서 props로 받아와서 같이 넘겨주기
        const payload = {
          content: this.content,
          memberId: this.profile.id,
          postId: this.diaryPk
        }
				this.createComment(payload),
				this.content = ''
			}
		},
    mounted() {
      this.fetchProfile()
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