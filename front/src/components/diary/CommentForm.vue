<template>
  <div>
    <form @submit.prevent="onSubmit">
      <div class="comment-form">
				<el-input class="comment-input" type="text" id="comment" v-model="content" required/>
				<el-button class="btn" link @click="onSubmit">
          <span class="material-symbols-outlined">send</span>
        </el-button>
        <!-- {{ isChild }}
        {{ parentId }} -->
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
      ...mapGetters(['profile', 'isChild', 'parentId']),
    },
		methods: {
			...mapActions(['createComment', 'fetchProfile']),
			onSubmit() {
        // diary.pk diarydetail에서 props로 받아와서 같이 넘겨주기
        // 댓글 생성
        if (!this.isChild) {
          const payload = {
            content: this.content,
            memberId: this.profile.id,
            postId: this.diaryPk,
            name: this.profile.name,
            imgPath: this.profile.img_link
          }
          this.createComment(payload)
          console.log('보낸 거', payload)
          this.content = ''
        } else {
          // 대댓글 생성
          const payload = {
            parentId: this.parentId,
            content: this.content,
            memberId: this.profile.id,
            postId: this.diaryPk,
            name: this.profile.name,
            imgPath: this.profile.img_link
          }
          this.createComment(payload)
          this.content = ''
        }
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