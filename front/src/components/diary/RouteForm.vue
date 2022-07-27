<template>
  <div>
    <div class="hospital-search">
      <el-tag v-for="(route, idx) in routeList" :key="idx" class="mx-1"
      closable :disable-route="false" type=''>
        {{ route }}
      </el-tag>
      <br>
      <el-button @click="addMarkers">지도 위에 루트 표시하기</el-button>
      <input
        id="pac-input"
        class="controls"
        type="text"
        placeholder="Search Box"
      />
    </div>
    <div id="map" style="height: 480px; position: relative; overflow: hidden;"></div>
    <div id="infowindow-content">
      <span id="place-name" class="title"></span><br />
      <span id="place-address"></span>
    </div>
  </div>
</template>

<script>
/* eslint-disable no-undef */

export default {
  name: 'RouteForm',
  data() {
    return {
      locationMarkers: [],
      routeList: [],
      labelList: []
    }
  },
  methods: {
    initMap() {
      const map = new google.maps.Map(document.getElementById("map"), {
          center: { lat: 37.5642135 ,lng: 127.0016985 },
          zoom: 13,
      });
      const input = document.getElementById("pac-input");
      // Specify just the place data fields that you need.
      const autocomplete = new google.maps.places.Autocomplete(input, {
          fields: ["place_id", "geometry", "name", "formatted_address"],
      });
      autocomplete.bindTo("bounds", map);
      map.controls[google.maps.ControlPosition.TOP_LEFT].push(input);
      const infowindow = new google.maps.InfoWindow();
      const infowindowContent = document.getElementById("infowindow-content");
      infowindow.setContent(infowindowContent);
      const geocoder = new google.maps.Geocoder();
      const marker = new google.maps.Marker({ map: map });
      marker.addListener("click", () => {
          infowindow.open(map, marker);
      });
      autocomplete.addListener("place_changed", () => {
          infowindow.close();
          const place = autocomplete.getPlace();
          if (!place.place_id) {
              return;
          }
          geocoder
              .geocode({ placeId: place.place_id })
              .then(({ results }) => {
              map.setZoom(15);
              map.setCenter(results[0].geometry.location);
              // Set the position of the marker using the place ID and location.
              // @ts-ignore TODO This should be in @typings/googlemaps.
              marker.setPlace({
                  placeId: place.place_id,
                  location: results[0].geometry.location,
              });
              // console.log(place.name)
              // console.log(place.geometry.location.lat())
              // console.log(place.geometry.location.lng())
              let latLng = { lat: place.geometry.location.lat(), lng: place.geometry.location.lng() }
              let route = place.name
              this.locationMarkers.push(latLng)
              this.routeList.push(route)
              // this.addMarker(latLng, map)
              
              // let labelNum = (this.locationMarkers.indexOf(latLng)+1).toString()
              // let newLabel = new google.maps.Marker({
              //     position: latLng,
              //     label: labelNum,
              //     map: map,
              // });
              // this.labelList.push(newLabel)

              console.log(this.locationMarkers)
              console.log(this.routeList)


              // google.maps.event.addListener(newLabel, 'click', () => {
              //   console.log(newLabel)
              //   // let label = Number(newLabel.label)
              //   // newLabel.label = 'hi'
              //   newLabel.setMap(null)
              //   // console.log(label)
              //   // this.removeLabel(label)

              //   })

              // console.log(flag)

              marker.setVisible(true);
              infowindowContent.children["place-name"].textContent = place.name;
              // infowindowContent.children["place-id"].textContent = place.place_id;
              infowindowContent.children["place-address"].textContent =
                  results[0].formatted_address;
              infowindow.open(map, marker);
          })
              .catch((e) => window.alert("Geocoder failed due to: " + e));
      });
    },
    removeLabel(index) {
      this.locationMarkers.splice(index - 1, 1)
      this.routeList.splice(index - 1, 1)
      this.labelList.splice(index - 1, 1)
      console.log(this.locationMarkers)
      console.log(this.routeList)
      console.log(this.labelList)
      this.labelList.forEach((each) => {
        console.log(each.label)
        let labelToNum = Number(each.label)
        if (labelToNum > index) {
          each.setMap(null)
        }
      })
    },
    addMarkers() {
      const map = new google.maps.Map(document.getElementById("map"), {
          center: this.locationMarkers[this.locationMarkers.length - 1],
          zoom: 13,
      });
      console.log(this.locationMarkers)
      this.locationMarkers.forEach((each) => {
        console.log(each)
        let labelNum = (this.locationMarkers.indexOf(each)+1).toString()
        let newLabel = new google.maps.Marker({
            position: each,
            label: labelNum,
            map: map,
        });
        this.labelList.push(newLabel)
      })
    }
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