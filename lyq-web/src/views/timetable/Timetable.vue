<template>
  <a-card :bordered="false">
    <div style="display: flex;justify-content: space-between;">
      <div style="width: 100%;margin-right: 40px;">
        <h2>本学期课表</h2>
        <img class="img" :src="src">
      </div>
      <div style="width: 30%;flex-shrink: 0;">
        <h2>所有科目资料列表</h2>
        <div class="means" v-for="(item,index) in means">
          <a-divider orientation="left">
            {{ index }}
          </a-divider>
          <div v-if="item!=null">
            <div v-for="(files,key) in item">
              <h1>{{ key | filter }}</h1>
              <my-file-list :files-ids="files"></my-file-list>
            </div>
          </div>
          <div v-else>暂无数据</div>
        </div>
      </div>
    </div>
  </a-card>
</template>

<script>
import {getPreviewFileUrl} from '@/api/system/files'
import {baseUrl} from "@/api/system/user";
import reqApi from '@/utils/reqApi'
import MyFileList from "@/components/My/file/MyFileList";

export default {
  name: "Timetable",
  components: {MyFileList},
  props: {},
  data() {
    return {
      crud: baseUrl.timetable.crud,
      src: null,
      means: []
    }
  },
  created() {
    this.getTimetable()
    this.getMeans()
  },
  methods: {
    getTimetable() {
      reqApi({
        url: this.crud,
        method: 'get'
      }).then(res => {
        this.src = getPreviewFileUrl(res.data)
      })
    },
    getMeans() {
      reqApi({
        url: baseUrl.means.getAll,
        method: 'get'
      }).then(res => {
        console.log(res.data)
        this.means = res.data
      })
    },
  },
  filters: {
    filter: res => {
      return res.split(",")[0]
    }
  }
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