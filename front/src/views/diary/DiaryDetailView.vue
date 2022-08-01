<template>
  <div>
    <!-- temp -> diary로 바꿔 -->
    <!-- 사진 어떻게 넘어오나 확인해야돼 -->
    <div class="diary-detail-header">
      <h3>{{ temp.newTitle }}</h3>
      <el-tag>{{ temp.newOption.datePick[0].substr(5, 10) }}-{{ temp.newOption.datePick[1].substr(5, 10) }}</el-tag>
      <el-tag>{{ temp.newOption.partyType }} ({{ temp.newOption.memberNum }}명)</el-tag>
      <el-tag v-for="(trans, idx) in temp.newOption.transportationList" :key="idx">{{ trans }}</el-tag>
    </div>

    <div id="map" style="height: 480px; position: relative; overflow: hidden;"></div>
    <el-tag v-for="(route, idx) in temp.routes" :key="idx">{{ idx + 1}}. {{ route }}</el-tag>

    <div>
      <div v-for="(story, idx) in temp.stories" :key="idx">
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

export default {
  name: 'DiaryDetailView',
  computed: {
    ...mapGetters(['temp']),
    photoUrl(file) {
      const newUrl = URL.createObjectURL(file)
      return newUrl
    }
  },
  methods: {
    addMarkers() {
      const map = new google.maps.Map(document.getElementById("map"), {
          center: this.temp.geocodes[0],
          zoom: 10,
      });
      this.temp.geocodes.forEach((each) => {
        let labelNum = (this.temp.geocodes.indexOf(each)+1).toString()
        new google.maps.Marker({
            position: each,
            label: labelNum,
            map: map,
        });
      })
    },
  },
  mounted() {
    this.addMarkers()
  }
}
</script>

<style scoped>

.diary-detail-header {
  position: fixed;
  top: 0;
  width: 100%;
  background-color: bisque;
}

</style>