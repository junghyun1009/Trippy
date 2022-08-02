<template>
  <div>
    <h1>Community create</h1>
    <community-form :post="post" action="create"></community-form>
  </div>
</template>

<script>
import CommunityForm from '@/components/community/CommunityForm.vue'
export default {
  name: 'CommunityCreate',
  components: {
      CommunityForm
  },
  data() {
    return {
      post: {
        pk: null,
        title: '',
        category: [],
        desc: '',
        dateTime: [],
        recruit_volume: '',
        gender: '',
        age: 0,
        isLocal: false
      }
    }
  }

}
</script>

<style>

</style>