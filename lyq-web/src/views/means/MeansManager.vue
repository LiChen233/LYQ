<template>
  <a-card :bordered="false">
    <h2>老师好！</h2>
    <h1>您所教授的科目是：{{ subject.name }}</h1>
    <h1>请在下方上传您为本科目所准备的学习资料吧！</h1>
    <my-file-upload :default-file-ids="files" :filesIds.sync="files">

    </my-file-upload>
  </a-card>
</template>

<script>
import {baseUrl} from "@/api/system/user";
import reqApi from "@/utils/reqApi";
import MyFileUpload from "@/components/My/file/MyFileUpload";

export default {
  name: "MeansManager",
  components: {
    MyFileUpload
  },
  props: {},
  data() {
    return {
      subject: {},
      files: '',
      crud: baseUrl.means.crud
    }
  },
  watch: {
    files: function (files) {
      reqApi({
        url: this.crud,
        method: 'post',
        data: {
          files: files
        }
      })
    }
  },
  created() {
    this.getSubject()
  },
  methods: {
    getMyMeans() {
      reqApi({
        url: this.crud,
        method: 'get'
      }).then(res => {
        this.files = res.data
      })
    },
    getSubject() {
      this.$store.dispatch('GetInfo').then(data => {
        if (data.subjectId == null) {
          this.$message.info('您还未被分配科目，无法进行资料上传，请联系管理员进行设置')
          return
        }
        reqApi({
          url: baseUrl.subject.crud,
          params: {
            id: data.subjectId
          }
        }).then(res => {
          this.subject = res.data
          this.getMyMeans()
        })
      })
    },
  }
}
</script>

<style lang="less" scoped>
.btn {
  display: flex;
}

.btn /deep/ .ant-btn {
  margin-left: 5px;
}

.btn /deep/ .ant-btn:nth-child(1) {
  margin: 0;
}
</style>