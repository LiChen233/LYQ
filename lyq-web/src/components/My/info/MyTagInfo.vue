<template>
  <div>
    <slot :info="info">
      <a-tag color="blue">{{info.tagName}}</a-tag>
    </slot>
  </div>
</template>

<script>
import {getComTagInfo} from "@/api/system/tag";

export default {
  name: "MyTagInfo",
  props: {
    tagId: {
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
      getComTagInfo(this.newUnitId).then(res => {
        this.info = res.data
      })
    }
  }
}
</script>

<style scoped>

</style>