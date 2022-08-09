<template>
  <div>
    <!-- diaryTemp -> diary로 바꿔 -->
    <!-- 사진 어떻게 넘어오나 확인해야돼 -->
    <div class="diary-detail-header">
      <div class="title-icons">
        <h3>{{ diaryTemp.title }}</h3>
        <div class="icons">
          <!-- 여기부터는 공통 -->
          <div class="icon-cnt">
            <span v-if="!isLiked" class="material-symbols-outlined" @click="isLiked=1">favorite</span>
            <span v-else class="material-symbols-outlined filled-heart" @click="isLiked=0">favorite</span>
            <span class="cnt">777</span>
          </div>
          <router-link :to="{ name: 'diaryComment' }" class="icon-cnt">
            <span class="material-symbols-outlined">chat_bubble</span>
            <span class="cnt">7</span>
          </router-link>

          <!-- 로그인한 유저와 글 쓴 유저가 같다면 -->
          <!-- <div v-if="isAuthor"> -->
          <edit-delete-button class="edit-delete"></edit-delete-button>
            <!-- <router-link :to="{ name: 'diaryEdit' }">
              <span class="material-symbols-outlined">edit_square</span>
            </router-link> -->
            <!-- deleteDiary에 pk 넘겨주기 -->
            <!-- <span class="material-symbols-outlined">delete</span> -->
          <!-- </div> -->
        </div>
      </div>

      <div class="diary-detail-body">
        <div class="profile-div">
          <!-- <el-avatar :size="100" :src="diary.member_id.img_path" /> -->
          <router-link :to="{ name: 'profile' }">
            <el-avatar :size="80" src="" />
          </router-link>
          <!-- <span>{{ diary.member_id.name }}</span> -->
          <router-link :to="{ name: 'profile' }">
            <span class="username">나유저</span>
          </router-link>
        </div>
        <div class="btn-tag">
          <!-- 작성자와 로그인 유저가 다른 경우 -->
          <el-button class="follow-btn" v-if="!isFollowed" @click="isFollowed=1">
            <span class="material-symbols-outlined">add</span>
            <span class="follow">팔로우</span>
          </el-button>
          <el-button class="following-btn" v-else @click="isFollowed=0">
            <span class="material-symbols-outlined">check</span>
            <span class="following">팔로잉</span>
          </el-button>
          <div class="tag">
            <!-- 여기는 공통 -->
            <el-tag>{{ diaryTemp.startDate.substr(5, 10) }}-{{ diaryTemp.endDate.substr(5, 10) }}</el-tag>
            <el-tag>{{ diaryTemp.company }} ({{ diaryTemp.count }}명)</el-tag>
            <el-tag v-for="(trans, idx) in diaryTemp.transport" :key="idx">{{ trans }}</el-tag>
          </div>
        </div>
      </div>
    </div>


    <div id="map" style="height: 70vw; position: relative; overflow: hidden;"></div>
    <div class="route-tag">
      <el-tag class="tag" v-for="(route, idx) in diaryTemp.routes" :key="idx">{{ route.routeNum }}. {{ route.routeName }}</el-tag>
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
        <el-tab-pane v-for="(story, idx) in diaryTemp.stories" :key="idx" :label="(idx+1).toString()">
          <div class="story-title">
            <h3>{{ story.place }}</h3>
            <el-rate disabled v-model=story.rate></el-rate>
          </div>
          <div class="story-image">
            <el-carousel indicator-position="outside" trigger="click" height="10rem" :autoplay=false arrow="always">
              <el-carousel-item v-for="(photo, index) in story.photoList" :key="index">
                <!-- {{ photo }} -->
                <img :src="photo.preview" :alt="photo.preview"/>
              </el-carousel-item>
            </el-carousel>
          </div>
          <div class="story-content">
            <p>{{ story.content }}</p>
          </div>
        </el-tab-pane>
      </el-tabs>
    </div>

  <hr>
  <br>

  <!-- comment? -->
  <diary-comment-view></diary-comment-view>

  </div>
</template>

<script>
/* eslint-disable no-undef */
import { mapGetters, mapActions } from 'vuex'
import DiaryCommentView from '@/views/diary/DiaryCommentView.vue'
import EditDeleteButton from '@/components/common/EditDeleteButton.vue'

export default {
  name: 'DiaryDetailView',
  components: {
    DiaryCommentView,
    EditDeleteButton,
  },
  data() {
    return {
      isLiked: 0,
      isFollowed: 0,
      // 라우터에 diaryPk 추가하기
      // diaryPk: this.$route.parmas.diaryPk
    }
  },
  // diaryTemp 얘는 내가 만든 데이터. 나중에 diary로 바꿔
  computed: {
    ...mapGetters(['isAuthor', 'diary', 'diaryTemp']),
    photoUrl(file) {
      const newUrl = URL.createObjectURL(file)
      return newUrl
    }
  },
  methods: {
    ...mapActions(['fetchDiary', 'deleteDiary']),
    addMarkers() {
      const map = new google.maps.Map(document.getElementById("map"), {
          center: {lat: this.diaryTemp.routes[0].lat, lng: this.diaryTemp.routes[0].lng},
          zoom: 10,
          disableDefaultUI: true,
      });
      const geocodes = []
      this.diaryTemp.routes.forEach((each) => {
        let labelNum = (each.routeNum).toString()
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
  },
  // created() {
  //   this.fetchDiary(this.diaryPk)
  // },
  mounted() {
    this.addMarkers()
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
  background-color: bisque;
  padding: 0 0 1.3rem 0;
}

.title-icons {
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin-bottom: 1rem;
}

.title-icons > h3 {
  font-weight: 500;
  margin-left: 1rem;
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

.btn-tag .tag {
  display: flex;
  justify-content: flex-end;
  flex-wrap: wrap;
  width: 11rem;
  margin-top: 0.5rem;
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
  font-weight: 500;
  margin-top: 0.5rem;
  margin-bottom: 0;
}

.el-rate {
  --el-rate-fill-color: #F16B51;
}

.story-image {
  margin-top: 1rem;
}

.story-image img {
  height: 10rem;
}

.story-content {
  text-align: left;
}
</style>