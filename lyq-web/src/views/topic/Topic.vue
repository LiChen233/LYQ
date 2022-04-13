<template>
  <div
    :style="bordered?'padding-bottom: 20px;margin-bottom: 20px;border-bottom: 1px solid #eee':'margin-bottom: 20px;'">
    <h1 :style="showRemark && info.rightKey && info.myCheck  && info.rightKey===info.myCheck?'color: #f5222d;':''">
      {{ index }}. {{ info.title }}</h1>
    <a-radio-group
      v-model="info.myCheck"
      @change="submit"
      style="margin: 10px 0;"
      v-if="info.type==='R'"
      :disabled="showRemark"
    >
      <a-radio value="A">{{ info.optionA }}</a-radio>
      <a-radio value="B">{{ info.optionB }}</a-radio>
      <a-radio value="C">{{ info.optionC }}</a-radio>
      <a-radio value="D">{{ info.optionD }}</a-radio>
    </a-radio-group>
    <a-checkbox-group
      v-model="info.myCheck"
      @change="submit"
      style="margin: 10px 0;"
      v-if="info.type==='C'"
      :disabled="showRemark"
    >
      <a-checkbox value="A">{{ info.optionA }}</a-checkbox>
      <a-checkbox value="B">{{ info.optionB }}</a-checkbox>
      <a-checkbox value="C">{{ info.optionC }}</a-checkbox>
      <a-checkbox value="D">{{ info.optionD }}</a-checkbox>
    </a-checkbox-group>
    <div>
      <a-input
        v-model="info.myCheck[0]"
        type="textarea"
        style="min-height: 80px;width: 50%;"
        v-if="info.type==='E'"
        placeholder="请输入答案"
        @change="submit"
      />
    </div>
    <a-form-model-item style="margin-bottom: 0px" label="题目解析" v-if="showRemark">
      {{ info.remark }}
    </a-form-model-item>
  </div>

</template>

<script>
import reqApi from "@/utils/reqApi";
import {baseUrl} from "@/api/system/user";

export default {
  name: "Topic",
  components: {},
  props: {
    info: {
      type: Object,
      default: () => {
        return {}
      }
    },
    index: {
      type: Number,
      default: 1
    },
    bordered: {
      type: Boolean,
      default: true
    },
    showRemark: {
      type: Boolean,
      default: true
    },
  },
  data() {
    return {
      timer: null
    }
  },
  methods: {
    submit() {
      let myCheck = this.info.myCheck
      let type = this.info.type
      if (myCheck.length > 1 && type !== 'E') {
        myCheck.sort();
      }
      this.debounce(() => {
        reqApi({
          url: baseUrl.topicCheck.crud,
          data: {
            topicId: this.info.id,
            myCheck: myCheck.toString()
          },
          method: 'post'
        })
      }, 1000)
    },
    debounce(func, wait) {
      if (this.timer) clearTimeout(this.timer);
      this.timer = setTimeout(() => {
        func()
      }, wait)
    }
  }
}
</script>

<style lang="less" scoped>
/deep/ .ant-form-item-control {
  line-height: 24px !important;
}
</style>