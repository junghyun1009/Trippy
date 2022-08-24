<template>
  <div class="container">
    <router-link :to="{ name: 'diaryDetail', params: { diaryPk: like.id } }">
      <el-card class="grid-content" :body-style="{ padding: 0 }" shadow="hover">
        <img :src="like.representativeImg" class="image"/>
        <!-- <span class="title-span">{{ like.title }}</span> -->
      </el-card>
    </router-link>
  </div>
</template>

<script>
export default {
  name: 'MyLikesListItem',
  props: {
    like: Object,
  }
}
</script>

<style scoped>
.container {
  width: 26vw;
  height: 26vw;
  margin: 0 0 0.3rem 0;
}

.image {
  /* width: 100%; */
  /* height: 100px; */
  width: 26vw;
  height: 26vw;
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