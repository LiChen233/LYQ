<template>
  <div>
    <slot :info="info">
      <template v-if="info">
        <router-link v-if="isDetails" :to="{ path: '/contract/detail', query: { contractId: id } }">{{ info.title }}</router-link>
        <span v-else>{{info.title}}</span>
      </template>
    </slot>
  </div>
</template>

<script>
import { getComContractInfo } from "@/api/contract/contract";

export default {
  name: "MyContractInfo",
  props: {
    id: {
      type: String,
      required: true
    },
    isDetails: {
      type: Boolean,
      default: true
    }
  },
  data () {
    return {
      info: {}
    }
  },
  created() {
    this.getInfo()
  },
  methods: {
    getInfo () {
      getComContractInfo(this.id).then(res => {
        this.info = res.data
      })
    }
  }
}
</script>

<style scoped>

</style>