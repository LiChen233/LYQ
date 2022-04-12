<template>
  <a-spin :spinning="$store.getters.getLoad(reqUrl)">
    <slot>
    </slot>
  </a-spin>
</template>

<script>
export default {
  name: "MySpin",
  props: {
    reqUrl: {
      type: String,
      required: true
    }
  }
}
</script>

<style scoped>

</style>