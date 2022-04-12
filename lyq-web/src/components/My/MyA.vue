<template>
  <a id="__a__" style="display: none" target="_blank"></a>
</template>

<script>
export default {
  name: "MyA",
  data () {
    return {
      href: ""
    }
  },
  methods: {
    target (url) {
      var element = document.getElementById("__a__");
      element.setAttribute("href", url);
      element.click()
    }
  }
}
</script>

<style scoped>

</style>