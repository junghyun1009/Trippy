<template>
  <el-form action="submit">
    <el-form-item label="제목" placeholder="제목을 입력하세요.">
      <el-input v-model="newPost.title" />
    </el-form-item>
    <el-form-item label="카테고리">
      <el-select v-model="newPost.category" placeholder="어떤 활동을 같이 하고 싶나요?">
        <el-option label="밥/카페" value="eating"></el-option>
        <el-option label="동행" value="gowith"></el-option>
        <el-option label="파티" value="party"></el-option>
        <el-option label="이동수단 셰어" value="transport"></el-option>
      </el-select>
    </el-form-item>
    <el-form-item label="활동 내용">
      <el-input v-model="newPost.desc" type="textarea" />
    </el-form-item>
    <el-form-item label="날짜/시간">
      <el-date-picker v-model="newPost.dateTime" type="datetimerange"/>
    </el-form-item>
    <el-form-item label="인원">
      <el-input-number v-model="newPost.recruit_volume" :min="1" :max="10"/>
    </el-form-item>
    <el-collapse>
      <el-collapse-item title="장소">
        <div>장소</div>
      </el-collapse-item>
      <el-collapse-item title="모집 조건">
        <template>
          <span>모집 조건</span>
          <div>
            <el-tag>조건1</el-tag>
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
          <el-slider v-model="newPost.newOption.age" range />
        </el-form-item>
        <el-form-item label="지역 제한">
          <el-switch v-model="newPost.newOption.isLocal" />
        </el-form-item>
      </el-collapse-item>
    </el-collapse>
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
        dateTime: this.post.dateTime,
        recruit_volume: this.post.recruit_volume,
        newOption: {
          gender: this.post.option.gender,
          age: this.post.option.age,
          isLocal: this.post.option.isLocal
        }
      }
    }
  },
  methods: {
    onSubmit() {
      console.log(this.newPost)
    }
  }
}
</script>

<style>

</style>