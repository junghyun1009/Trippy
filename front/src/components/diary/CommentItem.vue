<template>
  <div>
    <div v-if="comments.length === 0">
      <p>첫 번째 댓글의 주인공이 되어보세요!</p>
    </div>
    <div v-for="(comment, idx) in commentsWithUser" :key="idx" class="parent-child">
      <!-- 댓글 -->
      <div class="parent-comment">
        <!-- userId 보내줘야 함 -->
        <router-link :to="{ name: 'profile' }">
          <el-avatar :size="40" src="" />
        </router-link>
        <div>
          <p class="member">{{ comment.user }}</p>
          <p class="content">{{ comment.info.content }}</p>
          <!-- 나중에는 comment.pk로 바꿔서 보내야할 듯 -->
          <span class="leave-comment" @click="sendInfo(comment.user, comment.info.id)">답글 달기</span>
          <span v-if="comment.user === currentUser.name" class="leave-comment" @click="editComment(comment)">수정</span>
          <span v-if="comment.user === currentUser.name" class="leave-comment" @click="removeComment(comment.info.id)">삭제</span>
        </div>
      </div>
      <!-- 대댓글 -->
      <!-- {{ comment.info.children }} -->
      <div v-for="(child, index) in comment.info.children" :key="index">
        <div class="child-comment">
          <router-link :to="{ name: 'profile' }">
            <el-avatar :size="40" src="" />
          </router-link>
          <div>
            <p class="member">{{ child.user }}</p>
            <p class="content">{{ child.info.content }}</p>
            <span class="leave-comment" @click="editComment(child.content)">수정</span>
            <span class="leave-comment">삭제</span>
          </div>
        </div>
      </div>
    </div>

  </div>
</template>

<script>
import { mapGetters, mapActions } from 'vuex'

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
    ...mapGetters(['currentUser', 'comments', 'profile']),
    commentsWithUser () {
      return this.comments
    }
  },
  methods: {
    ...mapActions(['updateComment', 'deleteComment', 'showParent', 'fetchComment', 'switchIsEditing', 'fetchProfile']),
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
      const payload = {
        commentId: commentId,
        diaryId: this.diaryPk
      }
      this.deleteComment(payload)
    },
    showUser() {
      console.log(1)
      for (let i=0; i<this.commentsWithUser.length; i++) {
        console.log(this.commentsWithUser[i])
        for (let j=0; j<this.commentsWithUser[i].info.children.length; j++) {
          this.fetchProfile(this.commentsWithUser[i].info.children[j].memberId)
          this.commentsWithUser[i].info.children[j].user = this.profile.name
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
        }
      }
    }
  },
  mounted() {
    this.fetchComment(this.diaryPk),
    setTimeout(() => {
      this.showUser()
    }, 100);
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