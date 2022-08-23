<template>
  <div class="container">
    <router-link :to="{ name: 'diaryDetail', params: { diaryPk: diary.id } }">
      <el-card class="grid-content" :body-style="{ padding: 0 }" shadow="hover">
        <img :src="diary.representativeImg" class="image"/>
        <div style="padding: 1rem">
          <span class="title-span">{{ diary.title }}</span>
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
.image {
  width: 100%;
  overflow: hidden;
  /* height: 10rem; */
  display: block;
  border-radius: 0.25rem 0.25rem 0 0;
}

.title-span {
  font-size: 1em;
  margin-left: 0%;
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
  display: flex;
  border-radius: 0.25rem;
  height: 15rem;
  /* width: 100%; */
  /* padding-bottom: 75%; */
}

.el-card__body:hover {
  opacity: 0.2;
}

</style>