<template>
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
  </a-card>
</template>

<script>
import reqApi from "@/utils/reqApi";
import {api as userApi, baseUrl} from "@/api/system/user";
import Topic from "@/views/topic/Topic";

export default {
  name: "DoWork",
  components: {
    Topic
  },
  props: {},
  data() {
    return {
      data: {},
      topicList: [],
      workId: '',
      crud: baseUrl.rate.crud,
      showRemark: this.$route.query.showRemark
    }
  },
  created() {
    this.workId = this.$route.query.workId
    this.showRemark = this.$route.query.showRemark
    this.getTopicList()
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
          workId: this.workId
        }
      }).then(res => {
        this.topicList = res.data
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
    }
  }
}
</script>

<style scoped>

</style>