<template>
  <div class="container">
    <router-link :to="{ name: 'diaryDetail', params: { diaryPk: diary.id } }">
      <el-card class="grid-content" :body-style="{ padding: 0 }" shadow="hover">
        <img :src="diary.representativeImg" class="image"/>
        <span class="title-span">{{ diary.title.length > 13 ? ''+diary.title.slice(0, 11) + '...' : diary.title }}</span>
      </el-card>
    </router-link>
    <br>
  </div>
</template>

<script>
export default {
  name: 'OthersDiariesListItem',
  props: {
    diary: Object
  }
}
</script>

<style scoped>
.container {
  width: 27vw;
  height: 27vw;
  margin: 0 0 0.5rem 0;
}

.image {
  /* width: 100%; */
  /* height: 100px; */
  width: 27vw;
  height: 27vw;
  display: block;
  border-radius: 5%;
}

.title-span {
  font-size: 0.9em;
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
  border-radius: 4px;
  min-height: 10vh;
}

.el-card__body:hover {
  opacity: 0.2;
}

</style>