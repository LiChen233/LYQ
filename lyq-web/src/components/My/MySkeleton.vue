<template>
  <a-skeleton :loading="$store.getters.getLoad(reqUrl)">
    <slot>
    </slot>
  </a-skeleton>
</template>

<script>
export default {
  name: "MySkeleton",
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