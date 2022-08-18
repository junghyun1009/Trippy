<template>
  <div class="container">
    <!-- {{bookmark}} -->
    <router-link :to="{ name: 'communityDetail', params: { postPk: bookmark.id } }">
      <el-card class="grid-content" :body-style="{ padding: 0 }" shadow="hover">
        <span class="title-span">{{ bookmark.title }}</span>
      </el-card>
    </router-link>
  </div>
</template>

<script>
export default {
  name: 'MyBookmarkListItem',
  props: {
    bookmark: Object,
  }
}
</script>

<style scoped>
.image {
  width: 100%;
  height: 100px;
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
  border-radius: 4px;
  min-height: 10vh;
}

.el-card__body:hover {
  opacity: 0.2;
}

</style>