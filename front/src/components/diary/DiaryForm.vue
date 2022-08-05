<template>
  <div>
    <form action="submit">
      <!-- 제목 -->
      <div class="title-box">
        <span>제목</span>
        <el-input v-model="newDiary.title" class="input-box" placeholder="제목을 입력하세요." />
      </div>

      <div class="demo-collapse">
        <el-collapse>
          <!-- 장소 -->
          <el-collapse-item title="장소" name="1">
            <div>
              장소
            </div>
          </el-collapse-item>

          <!-- 옵션 -->
          <el-collapse-item name="2">
            <template #title>
              <span>옵션</span>
              <!-- 태그 -->
              <div>
                <el-tag class="option-tag" type=''>
                  {{ partyTag }}
                </el-tag>
                <el-tag v-for="trans in transportationTag" :key="trans" class="option-tag" 
                closable :disable-transitions="false" type='' @close="handleClose(trans)">
                  {{ trans }}
                </el-tag>
              </div>
            </template>

            <div>
              <!-- 옵션:여행 기간 -->
              <div class="date-picker">
                <div class="block">
                  <span class="demonstration">여행 기간</span>
                  <el-date-picker
                    v-model="datePick"
                    type="daterange"
                    range-separator="To"
                    start-placeholder="Start date"
                    end-placeholder="End date"
                  />
                </div>
              </div>

              <!-- 옵션: 일행 타입 -->
              <div class="party-type">
                <span class="demonstration">일행 타입</span>
                <el-radio-group v-model="newDiary.company">
                  <el-radio label="가족">가족</el-radio>
                  <el-radio label="커플">커플</el-radio>
                  <el-radio label="친구">친구</el-radio>
                  <el-radio label="개인">개인</el-radio>
                </el-radio-group>
              </div>

              <!-- 옵션: 인원 수 -->
              <div class="member-num">
                <span class="demonstration">인원 수</span>
                <el-input-number v-model="newDiary.count" :min="1" :max="10"/>
              </div>

            <!-- 옵션: 이동수단 -->
              <div class="transport-type">
                <span class="demonstration">이동 수단</span>
                <el-checkbox-group v-model="newDiary.transport">
                  <el-checkbox label="뚜벅이" />
                  <el-checkbox label="대중교통" />
                  <el-checkbox label="따릉이" />
                  <el-checkbox label="택시" />
                  <el-checkbox label="자차" />
                </el-checkbox-group>
              </div>
            </div>
          </el-collapse-item>

          <!-- 루트 -->
          <el-collapse-item name="3">
            <template #title>
              <span>루트</span>
              <!-- 태그 -->
              <div>
                <el-tag class="option-tag" v-for="(route, idx) in newDiary.routes" :key="idx"
                :disable-route="false" type=''>
                  {{ route.routeName }}
                </el-tag>
              </div>
            </template>
            
            <!-- 지도 -->
            <div>
              <div class="route-div">
                <input id="pac-input" class="route-input" type="text" placeholder="루트를 추가해주세요." v-show="flag === 0 && newDiary.routes.length < 10">
                <el-button type="primary" @click="addMarkers" :disabled="newDiary.routes.length === 0">
                  <icon-base viewBox="0 0 1024 1024" width="24" height="24" iconColor="#fff" icon-name="maplocation">
                    <map-location/>
                  </icon-base>
                </el-button>
                <el-button type="primary" @click="initMap" :disabled="flag === 0">
                  <icon-base viewBox="0 0 1024 1024" width="24" height="24" iconColor="#fff" icon-name="plusicon">
                    <plus-icon/>
                  </icon-base>  
                </el-button>
              </div>
              <div id="map" style="height: 480px; position: relative; overflow: hidden;"></div>
              <div class="route-tag-group">
                <el-tag v-for="(route, idx) in newDiary.routes" :key="idx" class="route-tag"
                closable :disable-route="false" type='' @close="removeRoute(idx)">
                  {{ route.routeName }}
                </el-tag>
              </div>
            </div>
          </el-collapse-item>
        </el-collapse>
      </div>

      <!-- 스토리 -->
      <div class="story-form">
        <p class="title-box">스토리</p>

        <div>
          <div v-for="(newStory, k) in newStories" :key="k">
            <div class="title-box">
              <span>상세 장소</span>
              <el-input v-model="newStory.place" class="input-box" id="title-input-box" placeholder="상세 장소를 입력하세요." />
            </div>

            <div class="photo-content-div">
              <div>
                <div v-if="newStory.photoList.length === 0" class="photo-div">
                  <label :for=k>
                    <icon-base viewBox="0 0 1024 1024" width="50" height="50" iconColor="#409EFF" icon-name="addphoto" class="photo-icon">
                      <add-photo/>
                    </icon-base>
                  </label>
                  <input class="photo-input" type="file" :id=k ref="files" accept="image/*" @change="uploadPhoto(k)" multiple />
                </div>
                <div v-else class="photo-preview">
                  <input class="photo-input" type="file" :id=k ref="files" accept="image/*" @change="uploadPhoto(k)" multiple />
                  <el-carousel trigger="click" height="150px" :autoplay=false :initial-index=1 indicator-position="none">
                    <el-carousel-item>
                      <label :for=k>
                        <icon-base viewBox="0 0 1024 1024" width="50" height="50" iconColor="#409EFF" icon-name="addphoto" class="photo-icon">
                          <add-photo/>
                        </icon-base>
                      </label>
                    </el-carousel-item>
                    <el-carousel-item v-for="(photo, index) in newStory.photoList" :key="index" class="file-preview-wrapper">
                      <img :src="photo.preview" :alt="photo.preview"/>
                      <el-button class="remove-photo" @click="removePhoto(k, index)">
                        x
                      </el-button>
                    </el-carousel-item>
                  </el-carousel>
                </div>
              </div>

              <div>
                <el-input v-model="newStory.content" maxlength="500"
                placeholder="내용을 입력해주세요." show-word-limit type="textarea" rows=7 resize="none" class="content-input"/>
              </div>
            </div>

            <div class="rate-div">
              <span>별점</span>
              <el-rate v-model="newStory.rate" allow-half />
            </div>
            
            <el-button @click="removeStory(k)" v-show="(newStories.length >= 1) && k!=0">delete</el-button>
            <el-button @click="addStory()" v-show="newStories.length < 10" :disabled="k != newStories.length - 1">add story</el-button>
            <hr>
          </div>
        </div>

      </div>

      <div>
        <el-button @click="onSubmit">{{ action }}</el-button>
      </div>
    </form>
  </div>
</template>

<script>
/* eslint-disable no-undef */
import { mapActions, mapGetters } from 'vuex'
import MapLocation from '@/components/icon/MapLocation.vue'
import PlusIcon from '@/components/icon/PlusIcon.vue'
import AddPhoto from '@/components/icon/AddPhoto.vue'

export default {
  name: 'DiaryForm',
  components: {
    MapLocation,
    PlusIcon,
    AddPhoto
  },
  props: {
    diary: Object,
    action: String
  },
  data() {
    return {
      datePick: [this.diary.startDate, this.diary.endDate],
      flag: 0,
      route: {},
      newStories: [
        ...this.diary.stories,
        { 
          pk: 0,
          place: '',
          photoList: [],
          content: '',
          rate: null
        }
      ],      
      newDiary: {
        title: this.diary.title,
        startDate: this.diary.startDate,
        endDate: this.diary.endDate,
        company: this.diary.company,
        count: this.diary.count,
        transport: this.diary.transport,
        routes: this.diary.routes,
        stories: this.diary.stories
      }
    }
  },
  computed: {
    // update할 때 diaryTemp 대신 해당 pk 다이어리 가져와야 함 -> 편집 창으로 들어오면 해당 pk 다이어리 내용 fetch하는 함수
    ...mapGetters(['routeNames', 'stories', 'diaryTemp']),
    partyTag() {
      const party = this.newDiary.company
      return party
    },
    transportationTag() {
      const transportation = this.newDiary.transport
      return transportation
    }
  },
  methods: {
    ...mapActions(['createDiary']),

    handleClose(tag) {
      this.newDiary.transport.splice(this.newDiary.transport.indexOf(tag), 1)
    },

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

      const geocoder = new google.maps.Geocoder();
      const marker = new google.maps.Marker({ map: map });
      autocomplete.addListener("place_changed", () => {
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

              let routeName = place.name
              let geocode = { lat: place.geometry.location.lat(), lng: place.geometry.location.lng() }

              this.addRoute(routeName, geocode)

              marker.setVisible(true)
          })
              .catch((e) => window.alert("Geocoder failed due to: " + e))
      input.value = ''
      });
    },

    addRoute(routeName, geocode) {
      this.route.routeNum = this.newDiary.routes.length + 1
      this.route.routeName = routeName
      this.route.lat = geocode.lat
      this.route.lng = geocode.lng
      this.newDiary.routes.push(this.route)
      this.route = {}
      console.log(this.newDiary.routes)
    },

    addMarkers() {
      this.flag = 1
      const map = new google.maps.Map(document.getElementById("map"), {
          center: { lat: this.newDiary.routes[this.newDiary.routes.length - 1].lat, lng: this.newDiary.routes[this.newDiary.routes.length - 1].lng},
          zoom: 13,
      });
      const geocodes = []
      // console.log(this.routeGeocodes)
      this.newDiary.routes.forEach((each) => {
        // console.log(each)
        let labelNum = (each.routeNum).toString()
        geocodes.push({ lat: each.lat, lng: each.lng })
        new google.maps.Marker({
            position: { lat: each.lat, lng: each.lng },
            label: labelNum,
            map: map,
        });
      })
      console.log(geocodes)
      const routePath = new google.maps.Polyline({
        path: geocodes,
        geodesic: true,
        strokeColor: '#FF0000',
        strokeOpacity: 1.0,
        strokeWeight: 2
      })
      routePath.setMap(map)
    },

    removeRoute(index) {
      this.newDiary.routes.splice(index, 1)
      this.newDiary.routes.forEach((each) => {
        each.routeNum = this.newDiary.routes.indexOf(each) + 1
      })
      this.addMarkers()
    },

    addStory() {
      if (this.newStories[this.newStories.length - 1].place === '' || this.newStories[this.newStories.length - 1].content === '') {
        alert('내용 작성 후 스토리를 추가해주세요!')
      } else {
        this.newStories.push({
          pk: 0,
          place: '',
          photoList: [],
          content: '',
          rate: null
        })
      }
    },

    removeStory(index) {
      this.newStories.splice(index, 1)
    },

    uploadPhoto(index) {
      // console.log(index)
      let addedPhotoList = this.newStories[index].photoList
      // fileInput.value = index
      // console.log(this.$refs.files)
      // console.log(this.$refs.files[index].files)
      for (let i = 0; i < this.$refs.files[index].files.length; i++) {
        let photo = this.$refs.files[index].files[i]
        console.log(photo)
        if (photo.type.substr(0, 5) === "image") {
          addedPhotoList = [
            ...addedPhotoList,
            {
              file: this.$refs.files[index].files[i],
              preview: URL.createObjectURL(this.$refs.files[index].files[i]),
            }
          ]
        } else {
          alert("사진 파일만 추가 가능합니다")
        }
      }
      this.newStories[index].photoList = addedPhotoList
      let fileInput = document.getElementsByClassName("photo-input")
      fileInput[fileInput.length - 1].value = ''
    },

    removePhoto(index, num) {
      this.newStories[index].photoList.splice(num, 1)
    },

    // addPhoto(index) {
    //   console.log('add', index)
    //   let addedPhotoList = this.newStories[index].photoList
    //   let fileInput = document.getElementsByClassName("photo-input-sec")
    //   console.log(fileInput)
    //   // console.log(this.$refs.files)
    //   console.log(this.$refs.files[index].files)
    //   for (let i = 0; i < this.$refs.files[index].files.length; i++) {
    //     let photo = this.$refs.files[index].files[i]
    //     if (photo.type.substr(0, 5) === "image") {
    //       addedPhotoList = [
    //         ...addedPhotoList,
    //         {
    //           file: this.$refs.files[index].files[i],
    //           preview: URL.createObjectURL(this.$refs.files[index].files[i]),
    //         }
    //       ]
    //     } else {
    //       alert("사진 파일만 추가 가능합니다")
    //     }
    //   }
    //   this.newStories[index].photoList = addedPhotoList
    //   fileInput[0].value = ''
    //   console.log(fileInput.value)
    // },

    onSubmit() {
      if (this.action === 'create') {
        // 날짜 변환해서 저장
        this.newDiary.startDate = this.datePick[0]
        this.newDiary.endDate = this.datePick[1]
        this.newStories.forEach((each) => {
          each.pk = this.newStories.indexOf(each) + 1
        })
        this.newDiary.stories = this.newStories

        if (this.newDiary.title && this.datePick.length && this.newDiary.transport.length
        && this.newDiary.routes.length && this.newDiary.stories.length) {
          this.createDiary(this.newDiary)
        } else {
          alert("빈 칸 없이 모든 필드를 채워주세요!")
        }
      }
    }
  },
  
  mounted() {
    this.initMap()
  },
}
</script>

<style scoped>
.title-box {
  text-align: left;
  color: var(--el-text);
  font-size: 14px;
}
.input-box {
  width: 300px;
  margin-left: 15px;
  margin-bottom: 10px;
}
.option-tag {
  margin-left: 10px;
}
.date-picker {
  display: flex;
  width: 100%;
  padding: 0;
  flex-wrap: wrap;
}
.date-picker .block {
  padding: 30px 0;
  text-align: center;
  border-right: solid 1px var(--el-border-color);
  flex: 1;
}
.date-picker .block:last-child {
  border-right: none;
}
.date-picker .demonstration {
  color: var(--el-text-color-secondary);
  font-size: 14px;
  margin-right: 20px;
}
.party-type {
  display: flex;
  width: 100%;
  flex-wrap: wrap;
  justify-content: center;
  padding: 0 0 30px;
}
.party-type .demonstration {
  color: var(--el-text-color-secondary);
  font-size: 14px;
  margin: 5px 20px 0 0;
}
.member-num {
  display: flex;
  width: 100%;
  flex-wrap: wrap;
  justify-content: center;
  padding: 0 0 30px;
}
.member-num .demonstration {
  color: var(--el-text-color-secondary);
  font-size: 14px;
  margin: 5px 20px 0 0;
}
.transport-type {
  display: flex;
  width: 100%;
  flex-wrap: wrap;
  justify-content: center;
  padding: 0 0 30px;
}
.transport-type .demonstration {
  color: var(--el-text-color-secondary);
  font-size: 14px;
  margin: 5px 20px 0 0;
}
.story-form .demonstration {
  color: var(--el-text-color-secondary);
  font-size: 14px;
  margin: 10px 20px 0 0;
  text-align: left;
}
.route-div {
  text-align: left;
  margin-bottom: 10px;
}
#pac-input {
  background-color: #fff;
  font-size: 14px;
  font-weight: 300;
  padding: 0 11px 0 13px;
  text-overflow: ellipsis;
  width: 400px;
  height: 30px;
  box-shadow: none;
  border: solid 1px var(--el-border-color);
  border-radius: 5px;
  margin-right: 10px;
}
.route-tag-group {
  margin-top: 10px;
  text-align: left;
}
.route-tag {
  margin-left: 10px;
}
.title-box {
  text-align: left;
  color: var(--el-text);
  font-size: 14px;
  /* margin-left: 85px; */
}
.input-box {
  width: 300px;
  margin-left: 15px;
  margin-bottom: 10px;
}
.photo-content-div {
  display: flex;
  justify-content: left;
  align-items: center;
}
.photo-div {
  text-align: center;
  align-content: center;
  width: 150px;
  height: 150px;
  display: flex;
  justify-content: center;
  align-items: center;
  flex-direction: column;
}
.photo-input {
  visibility: hidden;
}
.photo-icon {
  margin-top: 30px;
}
.photo-preview {
  width: 150px;
}
.file-preview-wrapper>img {
  position: absolute;
  top: 0px;
  right: 0px;
  width: 150px;
  height: 150px;
}
.remove-photo {
  position: absolute;
  top: 5px;
  right: 0px;
}
.add-photo {
  align-content: center;
  display: flex;
  align-items: center;
  justify-content: center;
  flex-direction: column;
}
.content-input {
  margin-left: 10px;
  height: 150px;
  width: 300px;
}
.rate-div {
  margin: 10px 0;
}
</style>