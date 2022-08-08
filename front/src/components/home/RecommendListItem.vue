<template>
  <div>
    <el-row>
      <el-col :span="12">
        <router-link :to="{ name: 'diaryDetail' }">
          <!-- <el-card :body-style="{ padding: '0px' }" shadow="never">
            <img :src="diaryTemp.stories[0].photoList[0].preview" class="image" :alt="diaryTemp.stories[0].photoList[0].preview"/>
            <div style="padding: 3px">
              <span class="title-span">{{ diaryTemp.title }}</span>
            </div>
          </el-card> -->
          <div class="diary-card" :body-style="{ padding: '0px' }" shadow="never">
            <img :src="diaryTemp.stories[0].photoList[0].preview" class="image" :alt="diaryTemp.stories[0].photoList[0].preview"/>
            <div class="diary-card-desc">
              <div style="padding: 3px">
                <span class="title-span">{{ diaryTemp.title }}</span><br>
                <!-- 이 부분에 게시글 작성자의 username이 올 것 -->
                <!-- <span class="author-span">{{ diary.userid.name }}</span> -->
              </div>
            </div>
          </div>

        </router-link>
      </el-col>
    </el-row>
    <br>
  </div>
</template>

<script>
import { mapGetters } from 'vuex'

export default {
  name: "RecommendListItem",
  computed: {
    ...mapGetters(['diaryTemp', 'diary'])
  }
}
</script>

<style scoped>
.image {
  width: 100%;
  height: 150px;
  display: block;
  border-radius: 5%;
}

.title-span {
  font-size: 1em;
  margin-left: 0%;
}

.diary-card {
  position: relative;
  width: 100%;
}

.diary-card-desc {
  position: absolute;
  /* background-color: rgb(255, 255, 255, 0.7); */
  margin-top: -30%;
  /* width: 100%; */
  /* height: 38%; */
}

.diary-card-desc span {
  font-weight: 500;
  text-decoration: none;
  color: white;
}
</style>