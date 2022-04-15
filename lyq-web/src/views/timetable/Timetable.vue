<template>
  <a-card :bordered="false">
    <div>
      <img class="img" :src="src">
    </div>
    <div class="means">
      <h1>这里放资料</h1>
    </div>
  </a-card>
</template>

<script>
import {getPreviewFileUrl} from '@/api/system/files'
import {baseUrl} from "@/api/system/user";
import reqApi from '@/utils/reqApi'

export default {
  name: "Timetable",
  components: {},
  props: {},
  data() {
    return {
      crud: baseUrl.timetable.crud,
      src: null
    }
  },
  created() {
    reqApi({
      url: this.crud,
      method: 'get'
    }).then(res => {
      this.src = getPreviewFileUrl(res.data)
    })
  },
  methods: {}
}
</script>

<style lang="less" scoped>
.img {
  width: 100%;
  height: auto;
}

.means {
  margin-top: 20px;
}
</style>