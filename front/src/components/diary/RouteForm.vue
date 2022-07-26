<template>
  <div>
    <GoogleMap api-key="AIzaSyAzDQaJSFl6J6q96-otMM30fyN2esgT_PI" style="width: 100%; height: 500px" :center="center" :zoom="15">
    </GoogleMap>
  </div>
</template>

<script>
import { GoogleMap } from "vue3-google-map"

export default {
  name: 'RouteForm',
  components: { GoogleMap },
  data() {
    return {
      center: { lat: 37.5642135, lng: 127.0016985 },
      locationMarkers: [],
      locPlaces: [],
      existingPlace: null
    }
  }
}
</script>

<style>

</style>