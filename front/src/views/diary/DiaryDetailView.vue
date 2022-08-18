<template>
  <div>
    <!-- diaryTemp -> diary로 바꿔 -->
    <!-- 사진 어떻게 넘어오나 확인해야돼 -->
    <!-- {{ diary }} -->
    <!-- {{ authorInfo }} -->
    <div class="diary-detail-header">
      <div class="title-icons">
        <div class="title-location">
          <h3>{{ diary.title }}</h3>
          <span>{{ diary.countryName }} | {{ diary.cityName }}</span>
        </div>
        <div class="icons">
          <!-- 여기부터는 공통 -->
          <div class="icon-cnt">
            <span v-if="!diary.like" class="material-symbols-outlined" @click="isLiked=1, goLike()">favorite</span>
            <span v-else class="material-symbols-outlined filled-heart" @click="isLiked=0, goUnlike()">favorite</span>
            <span class="cnt">{{ likeCount }}</span>
          </div>
          <!-- <router-link :to="{ name: 'diaryComment' }" class="icon-cnt">
            <span class="material-symbols-outlined">chat_bubble</span>
            <span class="cnt">7</span>
          </router-link> -->
          <div class="icon-cnt" @click="commentClicked=true">
            <span class="material-symbols-outlined">chat_bubble</span>
            <span class="cnt">{{ diary.comments.length }}</span>
          </div>
          
          <!-- 댓글 창 열림 -->
          <el-drawer v-model="commentClicked" direction="btt" size="50%">
            <template #header>
              <h2>Comments</h2>
            </template>
            <template #default>
              <!-- <div>
                <ul>
                  <li v-for="comment in comments" :key="comment.pk">
                    <comment-item :comment="comment"></comment-item>
                  </li>
                </ul>
              </div> -->
              <comment-item :diaryPk="this.diaryPk"></comment-item>
            </template>
            <template #footer>
              <div class="comment-form">
                <div v-if="isChild">
                  <p>@{{ parentComment }}님에게 답글 남기는 중</p>
                  <span @click="closeInfo">x</span>
                </div>
                <!-- 수정할 댓글 comment로 보냄 -->
                <comment-edit-form v-if="isEditing" :diaryPk="this.diaryPk" :commentToEdit="commentToEdit"></comment-edit-form>
                <comment-form v-else :diaryPk="this.diaryPk"></comment-form>
              </div>
            </template>
          </el-drawer>

          <!-- 로그인한 유저와 글 쓴 유저가 같다면 -->
          <!-- {{ currentUser }} -->
          <!-- {{ diary.email }} -->
          <div v-if="isAuthor">
            <edit-delete-button class="edit-delete"></edit-delete-button>
            <!-- <router-link :to="{ name: 'diaryEdit' }">
              <span class="material-symbols-outlined">edit_square</span>
            </router-link> -->
            <!-- deleteDiary에 pk 넘겨주기 -->
            <!-- <span class="material-symbols-outlined">delete</span> -->
          </div>
        </div>
      </div>

      <div class="diary-detail-body">
        <div class="profile-div">
          <!-- <el-avatar :size="100" :src="diary.member_id.img_path" /> -->
          <router-link :to="{ name: 'profile', params: { authorId: this.authorId } }">
            <el-avatar :size="80" :src="authorInfo.img_link" />
          </router-link>
          <!-- <span>{{ diary.member_id.name }}</span> -->
          <router-link :to="{ name: 'profile', params: { authorId: this.authorId } }">
            <span class="username">{{ diary.name }}</span>
          </router-link>
        </div>
        <div class="btn-tag">
          <!-- 작성자와 로그인 유저가 다른 경우 -->
          <div v-if="!isAuthor" class="follow-button">
            <el-button v-if="!isFollow" type="primary" @click="followNow(), follow(followId)">팔로우</el-button>
            <el-button v-else type="primary" plain @click="unfollowNow(), unfollow(followId)">팔로잉</el-button>
          </div>

          <div class="info-tag">
            <!-- 여기는 공통 -->
            <!-- <el-tag>{{ diary.countryName }}</el-tag> -->
            <!-- <el-tag>{{ diary.cityName }}</el-tag> -->
            <el-tag class="tag" effect="plain">{{ diary.startDate.substr(0, 10) }}-{{ diary.endDate.substr(0, 10) }}</el-tag>
            <el-tag class="tag" effect="plain">{{ partyTag }} ({{ diary.count }}명)</el-tag>
            <el-tag class="tag" effect="plain" v-for="(trans, idx) in diary.postTransports" :key="idx">{{ trans.name }}</el-tag>
          </div>
        </div>
      </div>
    </div>

    <img :src="authorInfo.img_link" alt="">

    <div id="map" style="height: 70vw; position: relative; overflow: hidden;"></div>
    <div class="route-tag">
      <el-tag class="tag" effect="dark" v-for="(route, idx) in diary.routes" :key="idx">{{ route.index }}. {{ route.routeName }}</el-tag>
    </div>

    <!-- <div>
      <div v-for="(story, idx) in diaryTemp.stories" :key="idx">
        <div>
          <h3>{{ story.pk }}. {{ story.place }}</h3>
          <el-rate disabled v-model=story.rate></el-rate>
          <el-carousel trigger="click" height="150px" :autoplay=false>
            <el-carousel-item v-for="(photo, index) in story.photoList" :key="index">
              {{ photo }} -->
              <!-- <img :src="photoUrl(photo.file)" :alt="photo.preview"/> -->
            <!-- </el-carousel-item>
          </el-carousel>
          <p>{{ story.content }}</p>
        </div>
      </div>
    </div> -->

    <div>
      <el-tabs tab-position="left" :stretch="true" class="story-tab">
        <el-tab-pane v-for="(story, idx) in diary.detailLocations" :key="idx" :label="(idx+1).toString()">
          <div class="story-title">
            <h3>{{ story.detailLocationName }}</h3>
            <el-rate disabled v-model=story.rating></el-rate>
          </div>
          <div class="story-image">
            <!-- <el-carousel indicator-position="outside" trigger="click" height="10rem" :autoplay=false arrow="always"> -->
              <!-- <el-carousel-item v-for="(photo, index) in story.photoList" :key="index"> -->
                <!-- {{ photo }} -->
            <!-- <p v-if="typeof story.filename === 'string'">{{ story.filename.slice(-3) }}</p> -->
            <img v-if="(story.filename!=null) && (typeof story.filename === 'string' && story.filename.slice(-3) != 'txt')" 
            :src="story.filepath" :alt="story.filepath"/>
              <!-- </el-carousel-item> -->
            <!-- </el-carousel> -->
          </div>
          <div class="story-content">
            <p>{{ story.detailLocationContent }}</p>
          </div>
        </el-tab-pane>
      </el-tabs>
    </div>

  <hr>
  <br>

  <!-- comment? -->
  <!-- <diary-comment-view></diary-comment-view> -->

  </div>
</template>

<script>
/* eslint-disable no-undef */
import { mapGetters, mapActions } from 'vuex'
import EditDeleteButton from '@/components/common/EditDeleteButton.vue'
import CommentForm from '@/components/diary/CommentForm.vue'
import CommentEditForm from '@/components/diary/CommentEditForm.vue'
import CommentItem from '@/components/diary/CommentItem.vue'

export default {
  name: 'DiaryDetailView',
  components: {
    EditDeleteButton,
    CommentForm,
    CommentEditForm,
    CommentItem,
  },
  data() {
    return {
      isLiked: 0,
      commentClicked: false,
      diaryPk: this.$route.params.diaryPk,
      // isFollow: false,
      followId: {
        follower_id: null,
        following_id: null,
      },
      isFollow: this.followingStatus
    }
  },
  // diaryTemp 얘는 내가 만든 데이터. 나중에 diary로 바꿔
  computed: {
    ...mapGetters(['isAuthor', 'diary', 'isChild', 'parentComment', 'currentUser', 'commentToEdit', 'isEditing', 'authorId', 
    'authorInfo', 'followingStatus', 'likeCount']),
    partyTag() {
      const party = this.diary.company
      const partyList = ['가족', '커플', '친구', '개인']
      return partyList[party-1]
    },
    photoUrl(file) {
      const newUrl = URL.createObjectURL(file)
      return newUrl
    }
  },
  watch: {
    followingStatus(newVal) {
      // 팔로우 여부 바뀌면
      this.isFollow = newVal
      // 팔로워 숫자 다시 받아오기
      // this.yourFollowersCount(this.currentProfile)
      // 팔로워 정보 다시 받아오기
      // this.yourFollowers(this.currentProfile)
    },
    likeStatus(newVal) {
      this.isLiked = newVal
      this.fetchLikeCount(this.diaryPk)
    }
  },
  methods: {
    ...mapActions(['fetchDiary', 'deleteDiary', 'hideParent', 'likeDiary', 'unlikeDiary', 'checkLike','fetchCurrentUser', 'fetchDiaryUser',
    'follow', 'unfollow', 'setFollowingStatus', 'fetchLikeCount']),
    goLike() {
      this.likeDiary(this.diary)
      this.fetchLikeCount(this.diaryPk)
    },
    goUnlike() {
      this.unlikeDiary(this.diary)
      this.fetchLikeCount(this.diaryPk)
    },
    addMarkers() {
      console.log(this.diary.routes)
      const map = new google.maps.Map(document.getElementById("map"), {
          center: {lat: this.diary.routes[0].lat, lng: this.diary.routes[0].lng},
          zoom: 13,
          disableDefaultUI: true,
      });
      const geocodes = []
      this.diary.routes.forEach((each) => {
        let labelNum = (each.index).toString()
        geocodes.push({lat: each.lat, lng: each.lng})
        new google.maps.Marker({
            position: {lat: each.lat, lng: each.lng},
            label: labelNum,
            map: map,
        });
      })
      const routePath = new google.maps.Polyline({
        path: geocodes,
        geodesic: true,
        strokeColor: '#FF0000',
        strokeOpacity: 1.0,
        strokeWeight: 2
      })
      routePath.setMap(map)
    },

    closeInfo() {
      this.hideParent()
    },

    followNow() {
      this.isFollow = !this.isFollow
      // follower_id - 내 아이디
      // following_id - 내가 팔로우하는 사람 아이디
      this.followId.follower_id = this.currentUser.id
      this.followId.following_id = this.diary.memberId
    },

    unfollowNow() {
      this.isFollow = !this.isFollow
      // follower_id - 내 아이디
      // following_id - 내가 언팔로우할 사람 아이디
      this.followId.follower_id = this.currentUser.id
      this.followId.following_id = this.diary.memberId
    },
  },
  created() {
    this.fetchDiary(this.diaryPk)
    this.fetchCurrentUser()
    this.fetchLikeCount(this.diaryPk)
  },
  mounted() {
    setTimeout(() => this.addMarkers(), 500),
    this.fetchDiaryUser(this.authorId)
    setTimeout(() => this.checkLike(this.diaryPk), 10)
    // this.addMarkers()
    this.setFollowingStatus(this.diary.memberId)
  }
}
</script>

<style scoped>

a {
  text-decoration: none;
}

.diary-detail-header {
  /* position: relative; */
  width: 100%;
  background-color: #EFDFDE;
  padding: 0 0 1.3rem 0;
}

.title-icons {
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin-bottom: 1rem;
}

.title-location {
  margin-top: 1rem;
}

.title-location > h3 {
  font-weight: 500;
  margin-left: 1rem;
}

.title-location > span {
  margin-left: 1rem;
  font-weight: 400;
  font-size: 0.8rem;
  color: #F16B51;
}

.icons {
  margin-top: 1rem;
  width: 8rem;
  display: flex;
  justify-content: space-evenly;
  color: #F16B51;
}

.icons > a {
  color: #F16B51;
}

.icon-cnt {
  display: flex;
  flex-direction: column;
  align-items: center;
}

.filled-heart {
  font-variation-settings:
  'FILL' 1,
  'wght' 400,
  'GRAD' 0,
  'opsz' 48
}

.cnt {
  font-size: 0.7rem;
}

.el-drawer h2{
  text-align: left;
  font-weight: 500;
}
/* .edit-delete {
  position: absolute;
  top: 1rem;
  right: 1rem;
} */

.diary-detail-body {
  display: flex;
  justify-content: space-around;
  align-items: center;
}

.profile-div {
  display: flex;
  align-items: center;
  margin-left: 0.8rem;
}

.username {
  margin-left: 0.8rem;
  font-size: 1rem;
  font-weight: 400;
  color: black;
}

.btn-tag {
  display: flex;
  flex-direction: column;
  align-items: flex-end;
  margin-right: 1rem;
}

.follow-btn {
  background-color: #F16B51;
  width: 5rem;
  padding-right: 1.3rem;
}

.follow-btn span {
  color: white;
  font-weight: 400;
  font-size: 0.9rem;
}

.following-btn {
  background-color: white;
  width: 5rem;
  padding-right: 1.3rem;
}

.following-btn span {
  color: #F16B51;
  font-weight: 400;
  font-size: 0.9rem;
}

.btn-tag .info-tag {
  display: flex;
  justify-content: flex-end;
  flex-wrap: wrap;
  width: 11rem;
  margin-top: 0.5rem;
}

.el-tag {
  border-color: var(--el-color-white);
}

.el-tag .el-tag--plain {
  --el-tag-border-color: var(--el-color-white);
}

.info-tag .tag {
  margin-left: 0.3rem;
  margin-top: 0.3rem;
}

.story-tab {
  margin-top: 20px;
  margin-bottom: 20px;
}

.route-tag {
  margin-top: 0.8rem;
  margin-left: 0.3rem;
  display: flex;
  flex-wrap: wrap;
  justify-content: flex-start;
}

.route-tag .tag {
  margin-left: 0.3rem;
  margin-bottom: 0.3rem;
}

.story-title {
  text-align: left;
  margin-left: 0.5rem;
  margin-right: 1rem;
}

.story-title h3 {
  background-color: #EFDFDE;
  padding-left: 0.3rem;
  font-weight: 500;
  margin-top: 0.5rem;
  margin-bottom: 0;
}

.el-rate {
  --el-rate-fill-color: #F16B51;
}

.story-image {
  margin: 1rem 0 1rem 0.5rem;
  display: flex;
  justify-content: left;
  width: 16rem;
}

.story-image img {
  width: 17.3rem;
  /* height: 10rem; */
}

.story-content {
  text-align: left;
  /* margin-top: 1rem; */
  margin-bottom: 2rem;
  margin-left: 0.5rem;
}

.comment-form {
  display: flex;
  flex-direction: column;
}

.comment-form div{
  display: flex;
  margin-bottom: 0.5rem;
  justify-content: space-between
}

.comment-form p {
  text-align: left;
  color: var(--el-text-color-secondary);
  font-size: 0.7rem;
  font-weight: 100;
}

.comment-form span {
  color: var(--el-text-color-secondary);
  font-size: 1rem;
  font-weight: 100;
}

.el-button--primary.is-plain {
  --el-button-text-color: var(--el-color-primary);
  --el-button-bg-color: white;
  --el-button-border-color: var(--el-color-primary);
}

</style>