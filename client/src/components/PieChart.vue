<template>
  <div>
    <div class="echart" id="mychart" :style="myChartStyle"></div>
  </div>
</template>

<script>
export default {
  props: ['pieData'],
  data() {
    return {
      myChart: {},
      // pieData: [
      //   {
      //     value: 463,
      //     name: "江苏"
      //   },
      //   {
      //     value: 395,
      //     name: "浙江"
      //   },
      //   {
      //     value: 157,
      //     name: "山东"
      //   },
      //   {
      //     value: 149,
      //     name: "广东"
      //   },
      //   {
      //     value: 147,
      //     name: "湖南"
      //   }
      // ],
      pieName: [],
      myChartStyle: { float: "left", width: "100%", height: "200px" }
    }
  },
  mounted() {
    this.$nextTick(() => {
      console.log(this.pieData)
      this.initData()
      this.initChart()
    })
  },
  methods: {
    initData() {
      for (let i = 0; i < this.pieData.length; i++) {
        this.pieName[i] = this.pieData[i].name
      }
    }, 
    initChart(){
      const option = {
        legend: {
          data: this.pieName,
          right: "10%",
          top: "30%",
          orient: "vertical"
        },
        series: [
          {
            type: "pie",
            label: {
              show: true,
              formatter: "{b} : {c} ({d}%)" // b代表名称，c代表对应值，d代表百分比
            },
            radius: "80%", //饼图半径
            data: this.pieData
          }
        ]
      };
      console.log(this.seriesData)
      this.myChart = this.$echarts.init(document.getElementById("mychart"));
      this.myChart.setOption(option)
      
      window.addEventListener("resize", () => {
        this.myChart.resize()
      })
    } 
  }
}
</script>

<style lang="less" scoped>

</style>
