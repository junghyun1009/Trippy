<template>
  <div>
  <el-menu
    :default-active="activeIndex"
    active-text-color="#ffd04b"
    class="el-menu-demo"
    mode="horizontal"
    :ellipsis="false"
    @select="handleSelect"
  >
    <el-menu-item index="0"><span class="trippyLogo" @click="$router.push('/')">TRIPPY</span></el-menu-item>
    <div class="flex-grow" />
    <el-menu-item index="1">Hamburger</el-menu-item>
  </el-menu>

  </div>
</template>

<script>
import { ref } from 'vue'


export default {
  name: "NavBar",
	methods: {
		handleSelect(key, keyPath) {
			var activeIndex = ref('1');
			console.log(activeIndex)
			var activeIndex2 = ref('1');
			console.log(activeIndex2)
			console.log(key, keyPath)
		}
	}


}
</script>

<style>
@import url('https://fonts.googleapis.com/css2?family=Alfa+Slab+One&display=swap');

.trippyLogo {
	font-family: 'Alfa Slab One', cursive;
	font-weight: 300;
	font-size: 20px;
  }

.flex-grow {
  flex-grow: 1;
}

</style>