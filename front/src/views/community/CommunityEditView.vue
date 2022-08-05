<template>
  <div>
    <h1>Community Edit</h1>
    <community-form :post="temp" action="update"></community-form>
  </div>
</template>

<script>
import CommunityForm from '@/components/community/CommunityForm.vue'
import { mapGetters } from 'vuex'

export default {
    name: 'CommunityEditView',
    components: {
      CommunityForm
    },
    computed: {
      ...mapGetters(['temp'])
    }
}
</script>

<style>

</style>