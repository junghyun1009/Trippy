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
        startDate: '',
        isDay: false,
        endDate: '',
        time: '',
        recruit_volume: '',
        option: {
          gender: '누구나',
          startAge: 0,
          endAge: 70,
          isLocal: false
        },
        place: '',
      }
    }
  }
}
</script>

<style>

</style>