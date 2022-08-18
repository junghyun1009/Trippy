<template>
  <div>
    <div v-if="comments.length === 0">
      <p>첫 번째 댓글의 주인공이 되어보세요!</p>
    </div>
    <div v-for="(comment, idx) in commentsWithUser" :key="idx" class="parent-child">
      <!-- 댓글 -->
      <!-- {{ comment }} -->
      <div class="parent-comment">
        <!-- userId 보내줘야 함 -->
        <!-- {{ comment }} -->
        <router-link :to="{ name: 'profile', params: { authorId: comment.memberId } }">
          <el-avatar :size="40" :src="comment.imgPath" />
        </router-link>
        <div>
          <p class="member">{{ comment.name }}</p>
          <p class="content">{{ comment.content }}</p>
          <!-- 나중에는 comment.pk로 바꿔서 보내야할 듯 -->
          <span class="leave-comment" @click="sendInfo(comment.name, comment.id)">답글 달기</span>
          <span v-if="comment.name === currentUser.name" class="leave-comment" @click="editComment(comment)">수정</span>
          <span v-if="comment.name === currentUser.name" class="leave-comment" @click="removeComment(comment.id)">삭제</span>
        </div>
      </div>
      <!-- 대댓글 -->
      <!-- {{ comment.info.children }} -->
      <div v-if="comment.children.length > 0">
        <div v-for="(child, index) in comment.children" :key="index">
          <!-- {{ child }} -->
          <div class="child-comment">
            <router-link :to="{ name: 'profile', params: { authorId: child.memberId } }">
              <el-avatar :size="40" :src="child.imgPath" />
            </router-link>
            <div>
              <p class="member">{{ child.name }}</p>
              <p class="content">{{ child.content }}</p>
              <span v-if="child.name === currentUser.name" class="leave-comment" @click="editComment(child)">수정</span>
              <span v-if="child.name === currentUser.name" class="leave-comment" @click="removeComment(child.id)">삭제</span>
            </div>
          </div>
        </div>
      </div>
    </div>

  </div>
</template>

<script>
import { mapGetters, mapActions } from 'vuex'
import { ElMessage, ElMessageBox } from 'element-plus'

export default {
  name: 'CommentItem',
  props: { 
    // comments: Array,
    diaryPk: String
  },
  data() {
    return {
    }
  },
  computed: {
    ...mapGetters(['currentUser', 'comments', 'childInfo', 'authorId']),
    commentsWithUser () {
      return this.comments
    }
  },
  methods: {
    ...mapActions(['updateComment', 'deleteComment', 'showParent', 'fetchComment', 'switchIsEditing', 'fetchChildUser']),
    sendInfo(member, parentId) {
      console.log(member)
      const payload = {
        member: member,
        parentId: parentId
      }
      this.showParent(payload)
    },
    editComment(comment) {
      console.log(1, comment)
      this.switchIsEditing(comment)
      // console.log(content)
      // this.updateComment(content)
    },
    removeComment(commentId) {
      ElMessageBox.confirm(
        '정말 삭제하시겠습니까?',
        'Warning',
        {
          confirmButtonText: 'OK',
          cancelButtonText: 'Cancel',
          type: 'warning',
        }
      )
      .then(() => {
          const payload = {
            commentId: commentId,
            diaryId: this.diaryPk
          }
          this.deleteComment(payload)
          ElMessage({
            type: 'success',
            message: '삭제가 완료되었습니다',
          })
      })
      .catch(() => {
        ElMessage({
          type: 'info',
          message: '삭제가 취소되었습니다',
        })
      })
    },
    // 대댓글을 댓글 형식이랑 맞춰주기
    showUser() {
      console.log(1)
      for (let i=0; i<this.commentsWithUser.length; i++) {
        console.log(this.commentsWithUser[i])
        for (let j=0; j<this.commentsWithUser[i].info.children.length; j++) {
          this.fetchChildUser(this.commentsWithUser[i].info.children[j].memberId)
          setTimeout(() => {
            console.log('여기', this.childInfo)
            this.commentsWithUser[i].info.children[j].user = this.childInfo.name
            this.commentsWithUser[i].info.children[j].img = this.childInfo.img_link
            this.commentsWithUser[i].info.children[j].info = {}
            this.commentsWithUser[i].info.children[j].info.id = this.commentsWithUser[i].info.children[j].id
            this.commentsWithUser[i].info.children[j].info.content = this.commentsWithUser[i].info.children[j].content
            this.commentsWithUser[i].info.children[j].info.postId = this.commentsWithUser[i].info.children[j].postId
            this.commentsWithUser[i].info.children[j].info.memberId = this.commentsWithUser[i].info.children[j].memberId
            this.commentsWithUser[i].info.children[j].info.parentId = this.commentsWithUser[i].info.children[j].parentId
            this.commentsWithUser[i].info.children[j].info.children = this.commentsWithUser[i].info.children[j].children
            delete this.commentsWithUser[i].info.children[j].id
            delete this.commentsWithUser[i].info.children[j].content
            delete this.commentsWithUser[i].info.children[j].postId
            delete this.commentsWithUser[i].info.children[j].memberId
            delete this.commentsWithUser[i].info.children[j].parentId
            delete this.commentsWithUser[i].info.children[j].children
          }, 100)
        }
      }
    }
  },
  mounted() {
    this.fetchComment(this.diaryPk)
    // setTimeout(() => {
    //   this.showUser()
    // }, 100);
  }
}
</script>

<style scoped>
/* @import url('https://fonts.googleapis.com/css2?family=IBM+Plex+Sans+KR:wght@300;500&display=swap');
* { 
      font-family: 'IBM Plex Sans KR', sans-serif;
      font-weight: 100;
    } 
.editdelete {
  margin-left: 5px;
  cursor: pointer;
  text-decoration: none;
  color: rgb(251, 66, 66);
}
i {
  margin-right: 5px;
} */
.parent-child {
  margin-bottom: 1.2rem;
}

.parent-comment {
  display: flex;
  margin-bottom: 0.5rem;
}

.parent-comment > div {
  text-align: left;
  margin-left: 0.5rem;
}

.child-comment {
  display: flex;
  background-color: #EFDFDE;
  border-radius: 1rem;
  margin-left: 3rem;
  margin-bottom: 0.5rem;
  padding: 1rem;
}

.child-comment > div {
  text-align: left;
  margin-left: 0.5rem;
}

.member {
  color: black;
  font-size: 0.8rem;
  font-weight: 400;
}

.content {
  color: black;
  font-size: 0.8rem;
  font-weight: 100;
}

.leave-comment {
  font-size: 0.7rem;
  font-weight: 100;
  margin-right: 0.5rem;
}
</style>