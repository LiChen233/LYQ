<template>
  <a-modal
    :visible="visible"
    centered
    title="请选择地址"
    cancelText="取消"
    okText="确定"
    @cancel="close"
    @ok="handleOk"
  >
    <a-input v-model="keyword"
             style="width: 100%;margin-bottom: 10px"
             placeholder="请输入地址"
    >
    </a-input>
    <b-map :center="center"
           :zoom="zoom"
           :scroll-wheel-zoom="true"
           ref="bmap"
           style="width: 100%;height: 400px"
           @ready="handler"
           @click="getClickAddr"
           ak="RneEVWzhv6fK0yjgO8NTjBGfhb574pAV"
    >

    </b-map>
  </a-modal>
</template>

<script>
// import BMap from 'vue-baidu-map/components/map/Map.vue'

const circleOptions = {
  strokeColor: "#18A65E",
  strokeWeight: 2,
  fillColor: "#18A65E",
  fillOpacity: "0.1",
};

export default {
  name: "MapDialog",
  components: {
    // BMap
  },
  props: {
    show:{
      type:Number,
      default: false
    },
  },
  watch: {
    show(){
      this.visible = true;
    }
  },
  data () {
    return {
      visible: false,
      keyword: null,
      center: {lng: 0, lat: 0},
      zoom: 3,

    }
  },

  methods:{
    getAddrByPoint(point) {

    },
    getClickAddr(type, target, point, pixel, overlay) {
      console.log(type.point)
    },
    handleOk() {
      this.visible = false
    },
    close() {
      this.visible = false
      this.$emit('cancel')
    },
    handler ({BMap, map}) {
      // 地图初始化
      console.log(BMap, map);
      this.center.lng = 116.404
      this.center.lat = 39.915
      this.zoom = 15

    },
  }
}

</script>

<style scoped>

</style>