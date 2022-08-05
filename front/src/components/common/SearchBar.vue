<template>
  <div>
		<input v-model="searchPlace" type="text" placeholder="어디로 떠날까요?" @click="isClicked=true" @keyup.enter="showInput"/>
		<div v-show="isClicked">
			<div class="switch-close-div">
				<el-switch v-model="isDetail" active-text="상세검색" inactive-text="전체검색"/>
				<icon-base viewBox="0 0 1024 1024" width="12" height="12" iconColor="#F16B51" icon-name="closeicon" @click="isClicked=false">
					<close-icon/>
				</icon-base>
			</div>
			<div v-if="isDetail">
				<div class="checkbox-div">
					<span>언제 가나요?</span>
					<el-checkbox-group v-model="selectMonth" size="small">
						<el-checkbox-button v-for="month in checkMonth" :key="month" :label="month">{{ month }}</el-checkbox-button>
					</el-checkbox-group>
				</div>
				<div class="checkbox-div">
					<span>어떻게 이동하나요?</span>
					<el-checkbox-group v-model="selectTrans" size="small">
						<el-checkbox-button v-for="trans in checkTrans" :key="trans" :label="trans">{{ trans }}</el-checkbox-button>
					</el-checkbox-group>
				</div>
				<div class="checkbox-div">
					<span>누구와 함께 가나요?</span>
					<el-checkbox-group v-model="selectCompany" size="small">
						<el-checkbox-button v-for="company in checkCompany" :key="company" :label="company">{{ company }}</el-checkbox-button>
					</el-checkbox-group>
				</div>
				<hr>
				<el-button size="small" @click="showInput">적용하기</el-button>
			</div>
		</div>
  </div>
</template>

<script>
import CloseIcon from '@/components/icon/CloseIcon.vue'

export default {
	name: 'SearchBar',
	components: {
		CloseIcon
	},
	data() {
		return {
			isClicked: false,
			searchPlace: '',
			// isDetail이 true면 상세검색, false면 전체검색
			isDetail: true,
			selectMonth: [],
			checkMonth: ['11월~2월', '3월~5월', '6월~8월', '9월~10월'],
			selectTrans: [],
			checkTrans: ['뚜벅이', '대중교통', '따릉이', '택시', '자차'],
			selectCompany: [],
			checkCompany: ['가족', '커플', '친구', '개인']
		}
	},
	methods: {
		showInput() {
			console.log(this.searchPlace)
			//전체검색인지 상세검색인지
			console.log(this.isDetail)
			// 필터 저장되는지 확인함
			console.log(this.selectMonth)
			console.log(this.selectTrans)
			console.log(this.selectCompany)
			this.searchPlace = ''
		}
	}
}
</script>

<style scoped>
	input {
		width: 80%;
		height: 30px;
		margin:5%;
		border-radius: 15px;
		padding-left: 15px;
	}

	.switch-close-div {
		display: flex;
		justify-content: space-between;
	}

	.checkbox-div {
		display: flex;
		align-items: center;
		margin-bottom: 5px;
	}

	.checkbox-div > span {
		font-size: 10px;
	}
</style>