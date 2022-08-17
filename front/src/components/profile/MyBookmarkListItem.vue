<template>
  <div>
    <el-card>
      <span>{{ bookmark }}</span>
    </el-card>
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

<style>

</style>