<template>
  <div>
    <!-- diaryTemp -> diary로 바꿔 -->
    <!-- 사진 어떻게 넘어오나 확인해야돼 -->
    <div class="diary-detail-header">
      <div class="title-icons">
        <h3>{{ diaryTemp.title }}</h3>
        <div class="icons">
          <!-- 로그인한 유저와 글 쓴 유저가 같다면 -->
          <!-- <div v-if="isAuthor"> -->
            <router-link :to="{ name: 'diaryEdit' }">
              <icon-base viewBox="0 0 1024 1024" width="24" height="24" iconColor="#F16B51" icon-name="editicon">
                <edit-icon/>
              </icon-base>
            </router-link>
            <!-- deleteDiary에 pk 넘겨주기 -->
            <icon-base viewBox="0 0 1024 1024" width="24" height="24" iconColor="#F16B51" icon-name="deleteicon">
              <delete-icon/>
            </icon-base>
          <!-- </div> -->
          <!-- 여기부터는 공통 -->
          <icon-base v-if="!isLiked" viewBox="0 0 512 512" width="24" height="24" iconColor="#F16B51" icon-name="emptyheart" @click="isLiked=1">
            <empty-heart/>
          </icon-base>
          <icon-base v-else viewBox="0 0 512 512" width="24" height="24" iconColor="#F16B51" icon-name="filledheart" @click="isLiked=0">
            <filled-heart/>
          </icon-base>
          <router-link :to="{ name: 'diaryComment' }">
            <icon-base viewBox="0 0 1024 1024" width="24" height="24" iconColor="#F16B51" icon-name="commenticon">
              <comment-icon/>
            </icon-base>
          </router-link>

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
            <span>나유저</span>
          </router-link>
        </div>
        <div>
          <!-- 작성자와 로그인 유저가 다른 경우 -->
          <el-button v-if="!isFollowed" @click="isFollowed=1">+팔로우</el-button>
          <el-button v-else @click="isFollowed=0">팔로잉</el-button>
          <br>
          <!-- 여기는 공통 -->
          <el-tag>{{ diaryTemp.startDate.substr(5, 10) }}-{{ diaryTemp.endDate.substr(5, 10) }}</el-tag>
          <el-tag>{{ diaryTemp.company }} ({{ diaryTemp.count }}명)</el-tag>
          <br>
          <el-tag v-for="(trans, idx) in diaryTemp.transport" :key="idx">{{ trans }}</el-tag>
        </div>
      </div>
    </div>


    <div id="map" style="height: 480px; position: relative; overflow: hidden;"></div>
    <el-tag v-for="(route, idx) in diaryTemp.routes" :key="idx">{{ route.routeNum }}. {{ route.routeName }}</el-tag>

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
      <el-tabs tab-position="right" :stretch="true" class="story-tab">
        <el-tab-pane v-for="(story, idx) in diaryTemp.stories" :key="idx" :label="story.place">
          <h3>{{ story.pk }}. {{ story.place }}</h3>
          <el-rate disabled v-model=story.rate></el-rate>
          <el-carousel trigger="click" height="150px" :autoplay=false>
            <el-carousel-item v-for="(photo, index) in story.photoList" :key="index">
              <!-- {{ photo }} -->
              <img :src="photo.preview" :alt="photo.preview"/>
            </el-carousel-item>
          </el-carousel>
          <p>{{ story.content }}</p>
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
import EditIcon from '@/components/icon/EditIcon.vue'
import DeleteIcon from '@/components/icon/DeleteIcon.vue'
import EmptyHeart from '@/components/icon/EmptyHeart.vue'
import FilledHeart from '@/components/icon/FilledHeart.vue'
import CommentIcon from '@/components/icon/CommentIcon.vue'
import DiaryCommentView from '@/views/diary/DiaryCommentView.vue'

export default {
  name: 'DiaryDetailView',
  components: {
    EditIcon,
    DeleteIcon,
    EmptyHeart,
    FilledHeart,
    CommentIcon,
    DiaryCommentView,
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

.diary-detail-header {
  width: 100%;
  background-color: bisque;
  padding: 0 0 20px 0;
}

.diary-detail-header > h3 {
  margin-bottom: 10px;
}

.title-icons {
  display: flex;
  justify-content: space-around;
  align-items: center;
}

.icons {
  width: 120px;
  display: flex;
  justify-content: space-evenly;
}

.diary-detail-body {
  display: flex;
  justify-content: space-around;
  align-items: center;
}

.profile-div {
  display: flex;
  align-items: center;
  margin-left: 10px;
}

.profile-div > span {
  margin-left: 10px;
  font-size: 20px;
}

.story-tab {
  margin-top: 20px;
  margin-bottom: 20px;
}

</style>