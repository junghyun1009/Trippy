<template>
  <div class="container">
    <router-link :to="{ name: 'diaryDetail', params: { diaryPk: diary.id } }">
      <el-card class="grid-content" :body-style="{ padding: 0 }" shadow="hover">
        <img :src="diary.representativeImg" class="image"/>
        <el-tag type="dark" size="small" class="tag">{{ diary.countryName}}</el-tag>
        <el-tag type="dark" size="small" class="tag">{{ diary.cityName}}</el-tag>
        <div style="padding: 0.3rem">
          <p class="title">{{ diary.title.length > 13 ? ''+diary.title.slice(0, 11) + '...' : diary.title }}</p>
            <!-- <img> -->
            <p class="name">{{ diary.name }}</p>
        </div>
      </el-card>
    </router-link>
    <br>
  </div>
</template>

<script>

export default {
  name: "RecommendListItem",
  props: {
    diary: Object,
  },
}
</script>

<style scoped>
.container {
  height: 230px;
}

.image {
  /* position: absolute; */
  width: 100%;
  overflow: hidden;
  height: 150px;
  display: block;
  border-radius: 0.25rem 0.25rem 0 0;
}

.tag {
  position: relative;
  bottom: 1.8rem;
  margin:0 0.1rem;
}

.title {
  position: relative;
  bottom: 1.3rem;
  font-size: 1.1rem;
  margin-left: 0%;
  font-weight: 400;
}

.name {
  position: relative;
  bottom: -0.5rem;
  font-size: 0.8rem;
}

a {
  text-decoration: none;
}

.diary-card {
  position: relative;
  width: 100%;
}

.diary-card-desc {
  position: absolute;
  margin-top: -30%;
}

.diary-card-desc span {
  font-weight: 500;
  text-decoration: none;
  color: black;
}

.grid-content {
  height: 28vh;
  width: 100%;
  /* padding-bottom: 75%; */
}

.el-card {
  height: 100%;
}

.el-card__body:hover {
  opacity: 0.2;
}

</style>