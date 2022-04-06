<template>
  <div>
    <slot :info="info">
      <a-tooltip placement="top" :title="info.lineText">
        <span>{{info.unitName}}</span>
      </a-tooltip>
    </slot>
  </div>
</template>

<script>
import { getComUnitInfo } from "@/api/system/unit";

export default {
  name: "MyUnitInfo",
  props: {
    unitId: {
      type: [Number, String],
      required: true
    }
  },
  data () {
    return {
      info: {},
      newUnitId: null
    }
  },
  created() {
    this.newUnitId = Number(this.unitId)
    this.getInfo()
  },
  methods: {
    getInfo () {
      getComUnitInfo(this.newUnitId).then(res => {
        this.info = res.data
      })
    }
  }
}
</script>

<style scoped>

</style>