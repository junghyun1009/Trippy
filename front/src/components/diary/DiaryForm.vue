<template>
  <div>
    <div class="demo-collapse">
      <el-collapse>
        <el-collapse-item title="장소" name="1">
          <div>
            장소
          </div>
        </el-collapse-item>
        <el-collapse-item name="2">
          <template #title>
            <span>옵션</span>
            <!-- 태그 -->
            <div>
              <el-tag class="mx-1" type=''>
                {{ partyTag }}
              </el-tag>
              <el-tag v-for="trans in transportationTag" :key="trans" class="mx-1" 
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
              <el-radio-group v-model="partyType">
                <el-radio label="가족">가족</el-radio>
                <el-radio label="커플">커플</el-radio>
                <el-radio label="친구">친구</el-radio>
                <el-radio label="개인">개인</el-radio>
              </el-radio-group>
            </div>

            <!-- 옵션: 인원 수 -->
            <div class="member-num">
              <span class="demonstration">인원 수</span>
              <el-input-number v-model="num" :min="1" :max="10"/>
            </div>

          <!-- 옵션: 이동수단 -->
            <div class="transport-type">
              <span class="demonstration">이동 수단</span>
              <el-checkbox-group v-model="transportationList">
                <el-checkbox label="뚜벅이" />
                <el-checkbox label="대중교통" />
                <el-checkbox label="따릉이" />
                <el-checkbox label="택시" />
                <el-checkbox label="자차" />
              </el-checkbox-group>
            </div>
          </div>
        </el-collapse-item>
        <el-collapse-item title="루트" name="3">
          <div>
            루트
          </div>
        </el-collapse-item>
      </el-collapse>
    </div>
  </div>
</template>

<script>

export default {
  name: 'DiaryForm',
  data() {
    return {
      datePick: '',
      partyType: '가족',
      num: 1,
      transportationList: [],
    }
  },
  computed: {
    partyTag() {
      const party = this.partyType
      return party
    },
    transportationTag() {
      const transportation = this.transportationList
      return transportation
    }
  },
  methods: {
    handleClose(tag) {
      this.transportationList.splice(this.transportationList.indexOf(tag), 1)
    }
  }
}
</script>

<style scoped>
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
</style>