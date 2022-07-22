<template>
  <div>
    <div>
      <el-tag
        class="mx-1"
        closable
        type=''
      >
        {{ optionTag }}
      </el-tag>
    </div>
    <!-- 옵션:여행 기간 -->
    <div class="date-picker">
      <div class="block">
        <span class="demonstration">여행 기간</span>
        <el-date-picker
          v-model="value1"
          type="daterange"
          range-separator="To"
          start-placeholder="Start date"
          end-placeholder="End date"
          :size="size"
        />
      </div>
    </div>

    <!-- 옵션: 일행 타입 -->
    <div class="party-type">
      <span class="demonstration">일행 타입</span>
      <el-radio-group v-model="radio">
        <el-radio label="가족">가족</el-radio>
        <el-radio label="커플">커플</el-radio>
        <el-radio label="친구">친구</el-radio>
        <el-radio label="개인">개인</el-radio>
      </el-radio-group>
    </div>
  </div>
</template>

<script>

export default {
  name: 'DiaryForm',
  data() {
    return {
      value1: '',
      radio: '가족',
    }
  },
  computed: {
    optionTag() {
      const party = this.radio
      return party
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
.party-type .demonstration {
  color: var(--el-text-color-secondary);
  font-size: 14px;
  margin-right: 20px;
}
</style>