<template>
  <div class="container">
    <!-- {{bookmark}} -->
    <router-link :to="{ name: 'communityDetail', params: { postPk: bookmark.id } }">
      <el-card class="grid-content" :body-style="{ padding: 0 }" shadow="hover">
        <h5 class="title">{{ bookmark.place }}</h5>
        <p class="detail">{{ bookmark.cityName }}</p>
        <p class="detail">{{ bookmark.endDate ? `${bookmark.startDate.slice(5, 10)}~${bookmark.endDate.slice(5,10)}` : ''+bookmark.startDate.slice(5, 10) }}</p>
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
.container {
  width: 100px;
  height: 100px;
  margin-bottom: 0.5rem;
}

.image {
  width: 100%;
  /* height: 100px; */
  display: block;
  border-radius: 5%;
}
.title {
  font-size: 0.9em;
  font-weight: 400;
  margin-left: 0%;
}

.detail {
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
  display: flex;
  align-items: center;
  padding: 0.5rem;
  border-radius: 4px;
  min-height: 100px;
}

.el-card__body:hover {
  opacity: 0.2;
}

</style>