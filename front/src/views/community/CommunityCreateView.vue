<template>
  <div>
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
        id: null,
        title: '',
        category: 1,
        description: '',
        startDate: '',
        // isDay: true,
        day: true,
        endDate: '',
        meetingTime: '',
        recruitCurrentVolume: 0,
        recruitVolume: 0,
        countryName: '',
        cityName: '',
        gender: '누구나',
        startAge: 0,
        endAge: 70,
        // isLocal: true,
        local: true,
        place: '',
      }
    }
  }
}
</script>

<style>

</style>