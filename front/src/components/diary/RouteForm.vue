<template>
  <div>
    <!-- <GoogleMap api-key="AIzaSyAzDQaJSFl6J6q96-otMM30fyN2esgT_PI" style="width: 100%; height: 500px" :center="center" :zoom="15">
    </GoogleMap> -->
    <div class="hospital-search">
      <!-- <div id="findhospital">Find hospital in:</div> -->
      <input
        id="pac-input"
        class="controls"
        type="text"
        placeholder="Search Box"
      />
      <!-- <div id="locationField">
        <input id="autocomplete" placeholder="Enter a city" type="text" />
      </div> -->

      <!-- <div id="controls">
        <select id="country">
          <option value="kr" selected>Korea</option>
        </select>
      </div> -->
    </div>
    <div id="map" style="height: 480px; position: relative; overflow: hidden;"></div>
  </div>
</template>

<script>
/* eslint-disable no-undef */
// import { GoogleMap } from "vue3-google-map"

export default {
  name: 'RouteForm',
  // components: { GoogleMap },
  data() {
    return {
      // center: { lat: 37.5642135, lng: 127.0016985 },
      // locationMarkers: [],
      // locPlaces: [],
      // existingPlace: null
    }
  },
  methods: {
    initAutocomplete() {
        const map = new google.maps.Map(document.getElementById("map"), {
            center: { lat: 37.5642135 ,lng: 127.0016985 },
            zoom: 13,
            mapTypeId: "roadmap",
        });
        // Create the search box and link it to the UI element.
        const input = document.getElementById("pac-input");
        const searchBox = new google.maps.places.SearchBox(input);
        map.controls[google.maps.ControlPosition.TOP_LEFT].push(input);
        // Bias the SearchBox results towards current map's viewport.
        map.addListener("bounds_changed", () => {
            searchBox.setBounds(map.getBounds());
        });
        let markers = [];
        // Listen for the event fired when the user selects a prediction and retrieve
        // more details for that place.
        searchBox.addListener("places_changed", () => {
            const places = searchBox.getPlaces();
            if (places.length == 0) {
                return;
            }
            // Clear out the old markers.
            markers.forEach((marker) => {
                marker.setMap(null);
            });
            markers = [];
            // For each place, get the icon, name and location.
            const bounds = new google.maps.LatLngBounds();
            places.forEach((place) => {
                if (!place.geometry || !place.geometry.location) {
                    console.log("Returned place contains no geometry");
                    return;
                }
                const icon = {
                    url: place.icon,
                    size: new google.maps.Size(71, 71),
                    origin: new google.maps.Point(0, 0),
                    anchor: new google.maps.Point(17, 34),
                    scaledSize: new google.maps.Size(25, 25),
                };
                // Create a marker for each place.
                markers.push(new google.maps.Marker({
                    map,
                    icon,
                    title: place.name,
                    position: place.geometry.location,
                }));
                if (place.geometry.viewport) {
                    // Only geocodes have viewport.
                    bounds.union(place.geometry.viewport);
                }
                else {
                    bounds.extend(place.geometry.location);
                }
            });
            map.fitBounds(bounds);
        });
    }
  },
  mounted() {
    this.initAutocomplete()
  }
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