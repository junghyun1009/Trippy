<template>
  <div>
    <div class="detail-header">
      <div>
        <el-tag class="tag">{{ temp.category }}</el-tag>
        <el-tag class="tag">장소</el-tag>
      </div>
      <router-link :to="{ name: 'profile' }">
        <div class="profile">
          <el-avatar :size="50" src="" />
          <span>나유저</span>
        </div>
      </router-link>
      <hr>
    <div class="title">
      <span>{{ recruitState }}</span>
      <h4>{{ temp.title }}</h4>
    </div>
     <div class="title-icons">
        <div class="icons">
          <!-- 로그인한 유저와 글 쓴 유저가 같다면 -->
          <router-link :to="{ name: 'communityEdit' }">
            <icon-base viewBox="0 0 1024 1024" width="24" height="24" iconColor="#F16B51" icon-name="editicon">
              <edit-icon/>
            </icon-base>
          </router-link>
          <icon-base viewBox="0 0 1024 1024" width="24" height="24" iconColor="#F16B51" icon-name="deleteicon">
            <delete-icon/>
          </icon-base>
          <!-- 여기부터는 공통 -->
          <icon-base v-if="!isBookmark" viewBox="0 0 512 512" width="24" height="24" iconColor="#F16B51" icon-name="emptyheart" @click="isBookmark=true">
            <empty-heart/>
          </icon-base>
          <icon-base v-else viewBox="0 0 512 512" width="24" height="24" iconColor="#F16B51" icon-name="filledheart" @click="isBookmark=false">
            <filled-heart/>
          </icon-base>
        </div>
    </div>

      <div class="option">
        <p>{{ temp.option.age[0] }}~{{ temp.option.age[1] }}세 | {{ temp.option.gender }}</p>
        <p>{{ convertDate[0] }} ~ {{ convertDate[1] }}, {{ convertTime }}</p>
        <p>{{ temp.place }}</p>
      </div>
    </div>
    <p>{{ this.temp.desc }}</p>
    <hr>
    <div>
      <p>{{ recruitCount}} / {{ temp.recruit_volume }}명 참여</p>
      <el-avatar :size="50" src="" />
    </div>
    <el-button>참가하기</el-button>
  </div>
</template>

<script>
import { mapGetters } from 'vuex'
import EditIcon from '@/components/icon/EditIcon.vue'
import DeleteIcon from '@/components/icon/DeleteIcon.vue'
import EmptyHeart from '@/components/icon/EmptyHeart.vue'
import FilledHeart from '@/components/icon/FilledHeart.vue'

export default {
    name: 'CommunityDetailView',
    components: {
      EditIcon,
      DeleteIcon,
      EmptyHeart,
      FilledHeart,
    },
    data() {
      return {
        isBookmark: true
      }
    },
    computed: {
      ...mapGetters(['temp']),
      recruitState() {
        return '모집 중'
      },
      recruitCount() {
        return 3
      },
      optionTag() {
        const gender = this.temp.option.gender
        const start_age = this.temp.option.age[0]
        const end_age = this.temp.option.age[1]
        let age = start_age + '~' + end_age + '살'
        if (start_age === undefined && end_age === undefined || start_age === 19 && end_age === 50) {
          age = '누구나'
        }
        const isLocal = this.temp.option.isLocal
        let local = ''
        if (isLocal === false) {
          local = '어디서나'
        } else {
          local = '같은 지역만'
        }
        return [gender, age, local]
      },
      convertDate() {
        const months = {'Jan': 1, 'Feb': 2, 'Mar': 3, 'Apr': 4, 'May': 5, 'Jun': 6, 'Jul': 7, 'Aug': 8, 'Sep': 9, 'Oct': 10, 'Nov': 11, 'Dec': 12}
        const start_year = this.temp.date[0].substr(11, 4)
        let start_month = this.temp.date[0].substr(4,3)
        const start_day = this.temp.date[0].substr(8,2)
        const end_year = this.temp.date[1].substr(11, 4)
        let end_month = this.temp.date[1].substr(4,3)
        const end_day = this.temp.date[1].substr(8,2)
        if (start_month in months || end_month in months) {
          start_month = months[start_month]
          end_month = months[end_month]
        }
        
        const start_date = start_year + '-' + start_month.toString().padStart(2, '0') + '-' + start_day
        const end_date = end_year + '-' + end_month.toString().padStart(2, '0') + '-' + end_day
        return [start_date, end_date]
      }
    },
}
</script>

<style scoped>
* {
  text-align: left;
}

.detail-header {
  width: 100%;
  background-color: bisque;
  padding: 0 0 20px 0;
}

.tag {
  margin:10px 10px 10px 0;
}

.tag:first-child {
  margin-left: 10px;
}

.profile {
  display: flex;
  align-items: center;
  margin-left: 10px;
}

.diary-detail-header > h3 {
  margin-bottom: 10px;
}

.title-icons {
  display: flex;
  justify-content: space-around;
  align-items: center;
}

.icons {
  width: 120px;
  display: flex;
  justify-content: space-evenly;
}
</style>