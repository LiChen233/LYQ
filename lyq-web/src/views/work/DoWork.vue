<template>
  <div>
    <a-card style="margin-bottom: 20px;" :bordered="false" v-if="work.files">
      <h1>资料下载</h1>
      <my-file-list :files-ids="work.files"></my-file-list>
    </a-card>
    <a-card :bordered="false">
      <topic v-for="(item,index) in topicList"
             :key="item.id"
             :info="item"
             :index="index+1"
             :bordered="topicList.length-1!==index"
             :show-remark="showRemark==='2'"
      ></topic>
      <a-button type="primary" @click="submit" :loading="loadKey" v-if="showRemark==='1'">
        提交作业
      </a-button>
      <div v-if="$route.query.rate">
        <h1>评分</h1>
        <span>
        <a-rate v-model="value" allow-half :tooltips="desc" @change="rate"/>
        <span class="ant-rate-text">{{ desc[value - 1] }}</span>
      </span>
      </div>
    </a-card>
  </div>
</template>

<script>
import reqApi from "@/utils/reqApi";
import {api as userApi, baseUrl} from "@/api/system/user";
import Topic from "@/views/topic/Topic";
import MyFileList from "@/components/My/file/MyFileList";

export default {
  name: "DoWork",
  components: {
    MyFileList,
    Topic
  },
  props: {},
  data() {
    return {
      value: 0,
      desc: ['很糟糕', '不太行', '一般', '很不错', '棒极了'],
      data: {},
      topicList: [],
      workId: '',
      userId: '',
      look: '',
      crud: baseUrl.rate.crud,
      showRemark: this.$route.query.showRemark,
      work: {}
    }
  },
  created() {
    this.workId = this.$route.query.workId
    this.userId = this.$route.query.userId
    this.look = this.$route.query.look
    this.value = this.$route.query.rate / 20
    this.showRemark = this.$route.query.showRemark
    this.getWork()
  },
  computed: {
    loadKey: function () {
      if (this.$myUtils.str.isNotEmpty(this.crud)) {
        return this.$store.getters.getLoad(this.crud)
      } else {
        return false
      }
    }
  },
  methods: {
    getTopicList() {
      reqApi({
        url: baseUrl.topic.list,
        params: {
          workId: this.workId,
          userId: this.userId
        }
      }).then(res => {
        let arr = res.data
        for(let key in arr){
          let t = arr[key]
          if (t.type==='E' && t.myCheck===null){
            t.myCheck = []
          }
        }
        this.topicList = res.data
      })
    },
    getWork() {
      reqApi({
        url: baseUrl.work.crud,
        params: {
          id: this.workId
        },
        method: 'get'
      }).then(res => {
        this.work = res.data
        this.getTopicList()
      })
    },
    submit() {
      let model = this.$confirm({
        title: "提示",
        content: "确认提交，提交后不可修改",
        onOk: () => {
          reqApi({
            url: this.crud,
            data: {
              workId: this.workId
            },
            method: 'post'
          }).then(res => {
            this.$message.success({content: '提交成功，你的得分为' + res.data.rateAuto})
            this.showRemark = '2'
            model.destroy()
          })
        }
      });
    },
    rate(v) {
      v *= 20
      reqApi({
        url: baseUrl.rate.crud,
        data: {
          workId: this.workId,
          userId: this.userId,
          rate: v
        },
        method: 'put'
      }).then(res => {
        this.$message.success('评分成功')
      })
    }
  }
}
</script>

<style scoped>

</style>