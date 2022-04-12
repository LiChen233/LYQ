<template>
  <div>
    <slot :info="info">
      <span>{{info.username}}</span>
    </slot>
  </div>
</template>

<script>
import { getComUserInfo } from "@/api/system/user";

export default {
  name: "MyUserInfo",
  props: {
    userId: {
      type: String,
      required: true
    }
  },
  data () {
    return {
      info: {
        username: "",
      }
    }
  },
  created() {
    this.getInfo()
  },
  methods: {
    getInfo() {
      getComUserInfo(this.userId).then(res => {
        let info = res.data;
        if (info != null) {
          this.info = res.data;
        }else{
          this.info = {username: "--"};
        }
      })
    }
  }
}
</script>

<style scoped>

</style>