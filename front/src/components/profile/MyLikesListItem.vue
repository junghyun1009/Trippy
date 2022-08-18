<template>
  <div class="container">
    <router-link :to="{ name: 'diaryDetail', params: { diaryPk: like.id } }">
      <el-card class="grid-content" :body-style="{ padding: 0 }" shadow="hover">
        <img :src="like.representativeImg" class="image"/>
        <span class="title-span">{{ like.title }}</span>
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

<style>

</style>