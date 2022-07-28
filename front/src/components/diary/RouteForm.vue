<template>
  <div>
    <div>
      <el-tag v-for="(route, idx) in routeList" :key="idx" class="mx-1"
      closable :disable-route="false" type='' @close="removeRoute(idx)">
        {{ route }}
      </el-tag>
      <br>
      <el-button @click="addMarkers">지도 위에 루트 표시하기</el-button>
      <el-button @click="initMap" :disabled="flag === 0">루트 추가하기</el-button>
      <input id="pac-input" class="controls" type="text" placeholder="Search Box" v-show="flag === 0">
    </div>
    <div id="map" style="height: 480px; position: relative; overflow: hidden;"></div>
    <!-- <div id="infowindow-content">
      <span id="place-name" class="title"></span><br />
      <span id="place-address"></span>
    </div> -->
  </div>
</template>

<script>
/* eslint-disable no-undef */

export default {
  name: 'RouteForm',
  data() {
    return {
      geocodeList: [],
      routeList: [],
      markerList: [],
      flag: 0
    }
  },
  methods: {
    initMap() {
      this.flag = 0
      const map = new google.maps.Map(document.getElementById("map"), {
          center: { lat: 37.5642135 ,lng: 127.0016985 },
          zoom: 13,
      });
      const input = document.getElementById("pac-input");
      const autocomplete = new google.maps.places.Autocomplete(input, {
          fields: ["place_id", "geometry", "name", "formatted_address"],
      });
      autocomplete.bindTo("bounds", map);
      // map.controls[google.maps.ControlPosition.TOP_LEFT].push(input);
      // const infowindow = new google.maps.InfoWindow();
      // const infowindowContent = document.getElementById("infowindow-content");
      // infowindow.setContent(infowindowContent);
      const geocoder = new google.maps.Geocoder();
      const marker = new google.maps.Marker({ map: map });
      // marker.addListener("click", () => {
      //     infowindow.open(map, marker);
      // });
      autocomplete.addListener("place_changed", () => {
          // infowindow.close();
          const place = autocomplete.getPlace();
          if (!place.place_id) {
              return;
          }
          geocoder
              .geocode({ placeId: place.place_id })
              .then(({ results }) => {
              map.setZoom(15);
              map.setCenter(results[0].geometry.location)

              marker.setPlace({
                  placeId: place.place_id,
                  location: results[0].geometry.location,
              })

              let latLng = { lat: place.geometry.location.lat(), lng: place.geometry.location.lng() }
              let route = place.name
              this.geocodeList.push(latLng)
              this.routeList.push(route)

              console.log(this.geocodeList)
              console.log(this.routeList)
              console.log(place.name)

              marker.setVisible(true)
              // infowindowContent.children["place-name"].textContent = place.name
              // infowindowContent.children["place-address"].textContent = results[0].formatted_address
              // infowindow.open(map, marker)
          })
              .catch((e) => window.alert("Geocoder failed due to: " + e))
      });
    },

    removeRoute(index) {
      this.geocodeList.splice(index, 1)
      this.routeList.splice(index, 1)
      this.markerList = []
    
      this.addMarkers()
    },

    addMarkers() {
      this.flag = 1
      const map = new google.maps.Map(document.getElementById("map"), {
          center: this.geocodeList[this.geocodeList.length - 1],
          zoom: 13,
      });
      console.log(this.geocodeList)
      this.geocodeList.forEach((each) => {
        console.log(each)
        let labelNum = (this.geocodeList.indexOf(each)+1).toString()
        let newLabel = new google.maps.Marker({
            position: each,
            label: labelNum,
            map: map,
        });
        this.markerList.push(newLabel)
      })
    },
  },
  mounted() {
    this.initMap()
  },
}
</script>

<style>
#pac-input {
  background-color: #fff;
  font-family: Roboto;
  font-size: 15px;
  font-weight: 300;
  margin-left: 12px;
  padding: 0 11px 0 13px;
  text-overflow: ellipsis;
  width: 400px;
}
</style>