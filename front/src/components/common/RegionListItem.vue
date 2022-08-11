<template>
  <div>
    <div class="regions"
        data-flickity='{ "pageDots": false, "cellAlign": "left" }'>
        <div class="region">
            <div class="image" id="all" ><span @click="diaryFilter(this.target)">전체</span></div>
        </div>

        <div class="region">
            <div class="image" id="seoul" ><span @click="diaryFilter(this)">서울</span></div>
        </div>

        <div class="region">
            <div class="image" id="jeju"><span @click="diaryFilter(this)">제주</span></div>
        </div>

        <div class="region">
            <div class="image" id="busan"><span @click="diaryFilter(this)">부산</span></div>
        </div>

        <div class="region">
            <div class="image" id="tokyo"><span @click="diaryFilter(this)">도쿄</span></div>
        </div>
    </div>

  </div>
</template>

<script>
export default {
  name: 'RegionListItem',
  data() {
    return {

    }
  },
  methods: {
    diaryFilter() {
      console.log(event.currentTarget.innerText)
      if ( event.currentTarget.innerText == '전체') {
        console.log('all')
        // 모든 일지를 axios로 호출해서 뿌림
      } 
      else if ( event.currentTarget.innerText == '서울') {
        console.log('seoul')
        // 모든 일지를 axios로 호출해서 가져오면
        // 오 근데 도시별 게시물 전체조회가 있네...???
        // 그러면 백에서 걍 호출해주면될거같기도함-- 나중에 api 완성돼야 알수있을듯
      }
      else if ( event.currentTarget.innerText == '제주') {
        console.log('jeju')
      }
      else if ( event.currentTarget.innerText == '부산') {
        console.log('busan')
      }
      else if ( event.currentTarget.innerText == '도쿄') {
        console.log('tokyo')
      }
    }
  },
  created() {
    window.onpopstate = function () {
    location.reload()
    };
  }
}
</script>

<style scoped>

  * { 
    box-sizing: border-box; 
  }

  /* .flickity-enabled { 
    margin: 0 60px;
  } */

  .flickity-button {
  background-color: transparent;
  color: #F16B51;

  }

  /* .next {
    margin-right: -40px;
  }

  .previous {
    margin-left: -40px;
  } */

  .region {
    width: 60px;
    height: 60px;
    margin-right: 10px;
    border-radius: 50%;
    counter-increment: carousel-cell;
    overflow: hidden;
  }
  
  .region:hover {
    opacity: 0.4;
  }
  
  
  span {
    display: block;
    text-align: center;
    line-height: 60px;
    font-size: 20px;
    color: white;
    text-shadow: 2px 2px 4px #000000;
  }
  
  
  .region:nth-child(1) .image{
      background-image: url("@/assets/seoul.jpg");
      background-size: 100px 100px;
    }
  .region:nth-child(1) .image{
      background-image: url("@/assets/seoul.jpg");
      background-size: 100px 100px;
    }
    .region:nth-child(2) .image{
      background-image: url("@/assets/seoul.jpg");
      background-size: 100px 100px;
    }
    .region:nth-child(3) .image{
      background-image: url("@/assets/busan.jpg");
      background-size: 100px 100px;
    }
    .region:nth-child(4) .image{
      background-image: url("@/assets/jeju.jpg");
      background-size: 100px 60px;
    }
    .region:nth-child(5) .image{
      background-image: url("@/assets/tokyo.jpg");
      background-size: 100px 60px;
    }


</style>