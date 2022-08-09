<template>
  <div>
    <form @submit.prevent="onSubmit" class="comment-list-form">
      <el-container>
				<el-input type="text" id="comment" v-model="content" required/>
				<el-button class="btn" link>
          <span class="material-symbols-outlined">send</span>
        </el-button>
			</el-container>
    </form>
  </div>
</template>

<script>
import { mapActions } from 'vuex'

export default {
    name: 'CommentForm',
    data() {
			return {
				content: '',
			}
		},
		methods: {
			...mapActions(['createComment']),
			onSubmit() {
				this.createComment(),
				this.content = ''
			}
		}
}
</script>

<style>
@import url('https://fonts.googleapis.com/css2?family=IBM+Plex+Sans+KR:wght@300;500&display=swap');

  * { 
      font-family: 'IBM Plex Sans KR', sans-serif;
    } 
.comment-list-form {
  display: flex;
}
.container {
  display: flex;
  padding: 0;
}
.commentbox {
  display: flex;
  width: 500px;
  margin-left: 3px;
}
button {
  margin-left: 12px;
}
.material-symbols-outlined {
  color: #F16B51;
}
.userinfo {
  font-style: bold;
  margin-left: 3px;
  margin-bottom: 8px;
  font-size: 18px;
}
</style>