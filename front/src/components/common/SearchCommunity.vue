<template>
  <div>
		<div class="input">
			<input class="search-bar-input" v-model="searchInput.region" type="text" placeholder="어디에서 동행을 구하시나요?" @keyup.enter="showInput()"/>
		</div>
  </div>
</template>

<script>
import { mapActions } from 'vuex'

export default {
	name: 'SearchBar',
	data() {
		return {
			searchInput: {
				region: '',
			},
		}
	},
	methods: {
		...mapActions(['searchCommunity']),
		showInput() {
			console.log(this.searchInput.region)
			this.searchCommunity(this.searchInput)
			this.searchInput.region = ''
		},

	}
}
</script>

<style scoped>
	.search-bar-input {
		width: 85%;
		height: 30px;
		margin:5%;
		border-radius: 15px;
		padding-left: 15px;
		background-color: rgba(236, 236, 236, 0.735);
		border: none;
		outline: 1;
		outline-color: #F16B51;
	}

</style>