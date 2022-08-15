<template>
  <div>
    <form @submit.prevent="onSubmit">
      <div class="comment-form">
        <!-- 수정 상태인지 아닌지 체크 -->
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
    data() {
      return {
        content: '',
      }
    },
    props: {
      diaryPk: Number,
    },
    computed: {
      ...mapGetters(['comment', 'isUpdating', 'profile']),
      // content() {
      //   return this.comment
      // }
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