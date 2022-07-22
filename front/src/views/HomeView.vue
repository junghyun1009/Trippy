<template>
  <div class="home">
    <img alt="Vue logo" src="../assets/logo.png">
    <router-link :to="{ name: 'diaryCreate' }">Create Diary</router-link>
  </div>
</template>

<script>
// @ is an alias to /src

export default {
  name: 'HomeView',
}
</script>
