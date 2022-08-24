<template>
  <div class="container">
    <router-link :to="{ name: 'diaryDetail', params: { diaryPk: diary.id } }">
      <el-card class="grid-content" :body-style="{ padding: 0 }" shadow="hover">
        <img :src="diary.representativeImg" class="image"/>
      </el-card>
    </router-link>
    <br>
  </div>
</template>

<script>

export default {
  name: "MyDiariesListItem",
  props: {
    diary: Object,
  },
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
  align-items: center;
  border-radius: 4px;
  min-height: 100px;
}

.el-card__body:hover {
  opacity: 0.2;
}

</style>