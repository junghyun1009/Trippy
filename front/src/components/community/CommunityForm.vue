<template>
  <el-form action="submit">
    <el-form-item label="제목">
      <el-input v-model="newPost.title" placeholder="제목을 입력하세요." />
    </el-form-item>
    <el-form-item label="카테고리">
      <el-select v-model="newPost.category" placeholder="어떤 활동을 같이 하고 싶나요?">
        <el-option label="밥/카페" value="밥/카페"></el-option>
        <el-option label="동행" value="동행"></el-option>
        <el-option label="파티" value="파티"></el-option>
        <el-option label="이동수단 셰어" value="이동수단 셰어"></el-option>
      </el-select>
    </el-form-item>
    <el-form-item label="활동 내용">
      <el-input v-model="newPost.desc" type="textarea" placeholder="간단히 하고 싶은 활동을 설명해주세요." />
    </el-form-item>
    <el-form-item label="날짜">
      <el-date-picker v-model="newPost.date" type="daterange"/>
    </el-form-item>
    <el-form-item label="시간">
      <el-time-picker v-model="newPost.time" :disabled-seconds="disabledSeconds"/>
    </el-form-item>
    <el-form-item label="인원">
      <el-input-number v-model="newPost.recruit_volume" :min="1" :max="10"/>
    </el-form-item>
    <el-form-item>
      <el-collapse class="collapse">
        <el-collapse-item title="장소">
          <div>장소</div>
        </el-collapse-item>
        <el-collapse-item>
          <template #title>
            <div>
              <span>모집 조건</span>
              <el-tag class="option-tag" v-for="option in optionTag" :key="option">{{ option }}</el-tag>
            </div>
          </template>
          <el-form-item label="성별">
            <el-radio-group v-model="newPost.newOption.gender">
              <el-radio label="누구나" />
              <el-radio label="여성만" />
              <el-radio label="남성만" />
            </el-radio-group>
          </el-form-item>
          <el-form-item label="나이">
            <el-slider class="slider" v-model="newPost.newOption.age" range :min="19" :max="70"/>
          </el-form-item>
          <el-form-item label="지역 제한">
            <el-switch v-model="newPost.newOption.isLocal" />
          </el-form-item>
        </el-collapse-item>
      </el-collapse>
    </el-form-item>
    <el-form-item label="모임 장소">
      <el-input v-model="newPost.place" placeholder="모임 장소를 입력하세요." />
    </el-form-item>
    <el-form-item>
      <el-button @click="onSubmit">{{ action }}</el-button>
    </el-form-item>
  </el-form>
</template>

<script>
export default {
  name: 'CommunityForm',
  props: {
    post: Object,
    action: String,
  },
  data() {
    return {
      newPost: {
        title: this.post.title,
        category: this.post.category,
        desc: this.post.desc,
        date: this.post.date,
        time: this.post.time,
        recruit_volume: this.post.recruit_volume,
        newOption: {
          gender: this.post.option.gender,
          age: this.post.option.age,
          isLocal: this.post.option.isLocal
        },
        place: this.post.place,
      }
    }
  },
  computed: {
    optionTag() {
      const gender = this.newPost.newOption.gender
      const start_age = this.newPost.newOption.age[0]
      const end_age = this.newPost.newOption.age[1]
      let age = start_age + '~' + end_age + '살'
      if (start_age === undefined && end_age === undefined || start_age === 19 && end_age === 70) {
        age = '누구나'
      }
      const isLocal = this.newPost.newOption.isLocal
      let local = ''
      if (isLocal === false) {
        local = '어디서나'
      } else {
        local = '같은 지역만'
      }
      return [gender, age, local]
    },
  },
  methods: {
    onSubmit() {
      console.log(this.newPost)
    },
    makeRange(start, end) {
      const result = []
      for (let i = start; i <= end; i ++) {
        result.push(i)
      }
      return result
    },
    disabledSeconds() {
      return this.makeRange(1, 59)
    }
  },
}
</script>

<style scoped>
.option-tag {
  margin-left: 10px;
}

.collapse {
  width: 100%;
}

.slider {
  position: relative;
  left: 0.5rem;
  width: 95%;
}
</style>