<template>
  <div>
    <slot :info="info">
      <a-tooltip placement="top" :title="info.remark">
        <a-tag color="blue">{{ info.title }}</a-tag>
      </a-tooltip>
    </slot>
  </div>
</template>

<script>
import {getComRoleInfo} from "@/api/system/role";

export default {
  name: "MyRoleInfo",
  props: {
    roleId: {
      type: [Number, String],
      required: true
    }
  },
  data() {
    return {
      info: {},
      newRoleId: null
    }
  },
  created() {
    this.newRoleId = Number(this.roleId)
    this.getInfo()
  },
  methods: {
    getInfo() {
      getComRoleInfo(this.newRoleId).then(res => {
        this.info = res.data
      })
    }
  }
}
</script>

<style lang="less" scoped>

</style>