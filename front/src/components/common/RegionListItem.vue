<template>
  <div>
    <div class="regions"
        data-flickity='{ "pageDots": false }'>
        <div class="region">
            <div class="image" id="all"><span>전체</span></div>
        </div>

        <div class="region">
            <div class="image" id="seoul"><span>서울</span></div>
        </div>

        <div class="region">
            <div class="image" id="jeju"><span>제주</span></div>
        </div>

        <div class="region">
            <div class="image" id="busan"><span>부산</span></div>
        </div>

        <div class="region">
            <div class="image" id="tokyo"><span>도쿄</span></div>
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

  .region {
    width: 100px;
    height: 100px;
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
    line-height: 100px;
    font-size: 30px;
    color: white;
    text-shadow: 2px 2px 4px #000000;
  }
  
  
  .region:nth-child(1) .image{
      background-image: url("@/assets/seoul.jpg");
      background-size: 200px 200px;
    }
  .region:nth-child(1) .image{
      background-image: url("@/assets/seoul.jpg");
      background-size: 200px 200px;
    }
    .region:nth-child(2) .image{
      background-image: url("@/assets/seoul.jpg");
      background-size: 200px 200px;
    }
    .region:nth-child(3) .image{
      background-image: url("@/assets/busan.jpg");
      background-size: 200px 200px;
    }
    .region:nth-child(4) .image{
      background-image: url("@/assets/jeju.jpg");
      background-size: 300px 200px;
    }
    .region:nth-child(5) .image{
      background-image: url("@/assets/tokyo.jpg");
      background-size: 300px 200px;
    }


</style>