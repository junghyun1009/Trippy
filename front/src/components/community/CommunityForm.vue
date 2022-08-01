<template>
    <el-form>
        <el-form-item label="제목">
            <el-input />
        </el-form-item>
        <el-form-item label="카테고리">
            <el-select placeholder="어떤 활동을 같이 하고 싶나요?">
                <el-option label="밥/카페"></el-option>
                <el-option label="동행"></el-option>
                <el-option label="파티"></el-option>
                <el-option label="이동수단 셰어"></el-option>
            </el-select>
        </el-form-item>
        <el-form-item label="활동 내용">
            <el-input type="textarea" />
        </el-form-item>
        <el-form-item label="날짜/시간">
            <el-col :span="11">
                <el-date-picker placeholder="모임 날짜를 선택하세요"/>
            </el-col>
            <el-col :span="2" class="text-center">
                <span class="text-gray-500">-</span>
            </el-col>
            <el-col :span="11">
                <el-time-select start="00:00" step="00:10" end="23:50" placeholder="모임 시간을 선택하세요"/>
            </el-col>
        </el-form-item>
        <el-form-item label="인원">
            <el-input-number :min="1" :max="10"/>
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
                    <el-radio-group>
                        <el-radio label="누구나" />
                        <el-radio label="여성만" />
                        <el-radio label="남성만" />
                    </el-radio-group>
                </el-form-item>
                <el-form-item label="나이">
                    <el-slider range />
                </el-form-item>
                <el-form-item label="지역 제한">
                    <el-switch />
                </el-form-item>
            </el-collapse-item>
        </el-collapse>
    </el-form>
</template>

<script>
export default {

}
</script>

<style>

</style>