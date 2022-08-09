<template>
  <div>
  <!-- <li class="comment-list-item">
    <router-link :to="{ name: 'profile', params: { username: comment.user.username } }">
      <i class="fa-solid fa-circle-user"></i>{{ comment.user.username }}
    </router-link>

    <span v-if="!isEditing">{{ payload.content }}</span>

    <span v-if="isEditing">
      <input type="text" v-model="payload.content">
      <a class="editdelete" @click="onUpdate"><i class="fa-solid fa-pen-clip"></i></a> |
      <a class="editdelete" @click="switchIsEditing"><i class="fa-solid fa-xmark"></i></a>
    </span>

    <span v-if="currentUser.username === comment.user.username && !isEditing">
      <a class="editdelete" @click="switchIsEditing"><i class="fa-solid fa-pen-clip"></i></a> |
      <a class="editdelete" @click="deleteComment(payload)"><i class="fa-solid fa-trash-can"></i></a>
    </span>
  </li> -->
    <div v-for="(comment, idx) in comments" :key="idx">
      <!-- 댓글 -->
      <div class="parent-comment">
        <router-link :to="{ name: 'profile' }">
          <el-avatar :size="40" src="" />
        </router-link>
        <div>
          <p class="member">{{ comment.member }}</p>
          <p class="content">{{ comment.content }}</p>
        </div>
      </div>
      <!-- 대댓글 -->
      <div v-for="(child, index) in comment.children" :key="index">
        <div class="child-comment">
          <router-link :to="{ name: 'profile' }">
            <el-avatar :size="40" src="" />
          </router-link>
          <div>
            <p class="member">{{ child.member }}</p>
            <p class="content">{{ child.content }}</p>
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
    comments: Array,
  },
  data() {
    return {
      isEditing: false,
      // payload: {
      //   diaryPk: this.comment.diary.pk,
      //   commentPk: this.comment.pk,
      //   content: this.comment.content
      // },
    }
  },
  computed: {
    ...mapGetters(['currentUser',]),
  },
  methods: {
    ...mapActions(['updateComment', 'deleteComment']),
    switchIsEditing() {
      this.isEditing = !this.isEditing
    },
    onUpdate() {
      this.updateComment(this.payload)
      this.isEditing = false
    }
  },
}
</script>

<style>
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
.parent-comment {
  display: flex;
}

.parent-comment > div {
  text-align: left;
  margin-left: 0.5rem;
}

.child-comment {
  display: flex;
  background-color: bisque;
  border-radius: 1rem;
  margin-left: 3rem;
  margin-top: 0.5rem;
  padding: 1rem;
}

.child-comment > div {
  text-align: left;
  margin-left: 0.5rem;
}

.member {
  color: black;
  font-size: 0.8rem;
}

.content {
  color: black;
  font-size: 0.8rem;
  font-weight: 100;
}
</style>