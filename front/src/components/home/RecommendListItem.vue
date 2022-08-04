<template>
  <div>
    <el-row>
      <el-col :span="8">
        <router-link :to="{ name: 'diaryDetail' }">
          <el-card :body-style="{ padding: '0px' }" shadow="never">
            <img :src="diaryTemp.stories[0].photoList[0].preview" class="image" :alt="diaryTemp.stories[0].photoList[0].preview"/>
            <div style="padding: 3px">
              <span class="title-span">{{ diaryTemp.title }}</span>
            </div>
          </el-card>
        </router-link>
      </el-col>
    </el-row>
  </div>
</template>

<script>
import { mapGetters } from 'vuex'

export default {
  name: "RecommendListItem",
  computed: {
    ...mapGetters(['diaryTemp'])
  }
}
</script>

<style>
.image {
  width: 100%;
  height: 150px;
  display: block;
}

.title-span {
  font-size: 12px;
}

</style>