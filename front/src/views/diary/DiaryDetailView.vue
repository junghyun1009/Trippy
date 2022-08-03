<template>
  <div>
    <!-- temp -> diary로 바꿔 -->
    <!-- 사진 어떻게 넘어오나 확인해야돼 -->
    <div class="diary-detail-header">
      <div class="title-icons">
        <h3>{{ diaryTemp.title }}</h3>
        <div class="icons">
          <!-- 로그인한 유저와 글 쓴 유저가 같다면 -->
          <router-link :to="{ name: 'diaryEdit' }">
            <icon-base viewBox="0 0 1024 1024" width="24" height="24" iconColor="#F16B51" icon-name="editicon">
              <edit-icon/>
            </icon-base>
          </router-link>
          <icon-base viewBox="0 0 1024 1024" width="24" height="24" iconColor="#F16B51" icon-name="deleteicon">
            <delete-icon/>
          </icon-base>
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
      <el-tag>{{ diaryTemp.season[0].substr(5, 10) }}-{{ diaryTemp.season[1].substr(5, 10) }}</el-tag>
      <el-tag>{{ diaryTemp.company }} ({{ diaryTemp.count }}명)</el-tag>
      <el-tag v-for="(trans, idx) in diaryTemp.transport" :key="idx">{{ trans }}</el-tag>
    </div>

    <div id="map" style="height: 480px; position: relative; overflow: hidden;"></div>
    <el-tag v-for="(route, idx) in diaryTemp.routes" :key="idx">{{ idx + 1}}. {{ route }}</el-tag>

    <div>
      <div v-for="(story, idx) in diaryTemp.stories" :key="idx">
        <div>
          <h3>{{ story.pk }}. {{ story.place }}</h3>
          <el-rate disabled v-model=story.rate></el-rate>
          <el-carousel trigger="click" height="150px" :autoplay=false>
            <el-carousel-item v-for="(photo, index) in story.photoList" :key="index">
              {{ photo }}
              <!-- <img :src="photoUrl(photo.file)" :alt="photo.preview"/> -->
            </el-carousel-item>
          </el-carousel>
          <p>{{ story.content }}</p>

        </div>
      </div>
    </div>

  </div>
</template>

<script>
/* eslint-disable no-undef */
import { mapGetters } from 'vuex'
import EditIcon from '@/components/icon/EditIcon.vue'
import DeleteIcon from '@/components/icon/DeleteIcon.vue'
import EmptyHeart from '@/components/icon/EmptyHeart.vue'
import FilledHeart from '@/components/icon/FilledHeart.vue'
import CommentIcon from '@/components/icon/CommentIcon.vue'

export default {
  name: 'DiaryDetailView',
  components: {
    EditIcon,
    DeleteIcon,
    EmptyHeart,
    FilledHeart,
    CommentIcon
  },
  data() {
    return {
      isLiked: 0
    }
  },
  computed: {
    ...mapGetters(['diaryTemp']),
    photoUrl(file) {
      const newUrl = URL.createObjectURL(file)
      return newUrl
    }
  },
  methods: {
    addMarkers() {
      const map = new google.maps.Map(document.getElementById("map"), {
          center: this.diaryTemp.geocodes[0],
          zoom: 10,
      });
      this.diaryTemp.geocodes.forEach((each) => {
        let labelNum = (this.diaryTemp.geocodes.indexOf(each)+1).toString()
        new google.maps.Marker({
            position: each,
            label: labelNum,
            map: map,
        });
      })
      const routePath = new google.maps.Polyline({
        path: this.diaryTemp.geocodes,
        geodesic: true,
        strokeColor: '#FF0000',
        strokeOpacity: 1.0,
        strokeWeight: 2
      })
      routePath.setMap(map)
    },
  },
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

</style>