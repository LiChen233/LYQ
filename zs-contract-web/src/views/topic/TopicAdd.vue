<template>
  <div style="margin-bottom: 20px;">
    <h1 v-if="type==='R'">添加单选题</h1>
    <h1 v-if="type==='C'">添加多选题</h1>
    <a-form-model
      ref="ruleForm"
      :model="form"
      :rules="rules"
    >
      <a-form-model-item label="题目名称" prop="title">
        <a-input v-model="form.title" placeholder="请输入题目名称"/>
      </a-form-model-item>
      <a-form-model-item label="请输入选项内容并选择正确答案" prop="key" v-if="type==='R'">
        <a-radio-group v-model="form.key" class="inputs">
          <a-form-model-item prop="optionA">
            <a-radio value="A">
              <a-input v-model="form.optionA" placeholder="请输入选项A的内容"/>
            </a-radio>
          </a-form-model-item>
          <a-form-model-item prop="optionB">
            <a-radio value="B">
              <a-input v-model="form.optionB" placeholder="请输入选项B的内容" prop="optionB"/>
            </a-radio>
          </a-form-model-item>
          <a-form-model-item prop="optionC">
            <a-radio value="C">
              <a-input v-model="form.optionC" placeholder="请输入选项C的内容" prop="optionC"/>
            </a-radio>
          </a-form-model-item>
          <a-form-model-item prop="optionD">
            <a-radio value="D">
              <a-input v-model="form.optionD" placeholder="请输入选项D的内容" prop="optionD"/>
            </a-radio>
          </a-form-model-item>
        </a-radio-group>
      </a-form-model-item>
      <a-form-model-item label="请输入选项内容并选择正确答案" prop="key" v-if="type==='C'">
        <a-checkbox-group v-model="form.key" class="inputs">
          <a-form-model-item prop="optionA">
            <a-checkbox value="A">
              <a-input v-model="form.optionA" placeholder="请输入选项A的内容"/>
            </a-checkbox>
          </a-form-model-item>
          <a-form-model-item prop="optionB">
            <a-checkbox value="B">
              <a-input v-model="form.optionB" placeholder="请输入选项B的内容" prop="optionB"/>
            </a-checkbox>
          </a-form-model-item>
          <a-form-model-item prop="optionC">
            <a-checkbox value="C">
              <a-input v-model="form.optionC" placeholder="请输入选项C的内容" prop="optionC"/>
            </a-checkbox>
          </a-form-model-item>
          <a-form-model-item prop="optionD">
            <a-checkbox value="D">
              <a-input v-model="form.optionD" placeholder="请输入选项D的内容" prop="optionD"/>
            </a-checkbox>
          </a-form-model-item>
        </a-checkbox-group>
      </a-form-model-item>
      <a-form-model-item style="line-height: 24px" label="解析" prop="remark">
        <a-input style="min-height: 100px" v-model="form.remark" type="textarea" placeholder="题目解析将在学生提交试卷后显示"/>
      </a-form-model-item>
      <a-button type="primary" @click="addTopic" :loading="loadKeyTopic">确定</a-button>
    </a-form-model>
  </div>
</template>

<script>
import {baseUrl} from '@/api/system/user'
import reqApi from "@/utils/reqApi";

export default {
  name: "TopicAdd",
  components: {},
  props: {
    bordered: {
      type: Boolean,
      default: true
    },
    workId: {
      type: String,
      default: ''
    },
    type: {
      type: String,
      default: ''
    }
  },
  data() {
    return {
      wrapperCol: {span: 7},
      form: {},
      rules: {
        title: {required: true, message: '请输入题目名称', trigger: 'blur'},
        optionA: {required: true, message: '请输入选项A', trigger: 'blur'},
        optionB: {required: true, message: '请输入选项B', trigger: 'blur'},
        optionC: {required: true, message: '请输入选项C', trigger: 'blur'},
        optionD: {required: true, message: '请输入选项D', trigger: 'blur'},
        key: {required: true, message: '请选择正确选项', trigger: 'blur'},
      },
      crud: baseUrl.topic.crud
    }
  },
  watch: {
    type() {
      this.form.key = null
    }
  },
  computed: {
    loadKeyTopic: function () {
      if (this.$myUtils.str.isNotEmpty(this.crud)) {
        return this.$store.getters.getLoad(this.crud)
      } else {
        return false
      }
    }
  },
  methods: {
    addTopic() {
      this.$refs.ruleForm.validate(valid => {
        if (valid) {
          let data = this.form
          data.workId = this.workId
          data.type = this.type
          if (data.key.length > 1) {
            data.key.sort()
          }
          data.rightKey = data.key.toString()
          reqApi({
            url: this.crud,
            data: data,
            method: 'post'
          }).then(() => {
            this.$message.open({content: '保存成功'})
            this.$emit("submit")
            this.form = {}
          })
        }
      });
    }
  }
}
</script>

<style lang="less" scoped>
.inputs {
  /deep/ .ant-form-item {

  }

  width: 400px !important;
}


/deep/ .ant-radio-wrapper {
  margin-bottom: 10px;
}

/deep/ .ant-checkbox-wrapper {
  display: flex;
  align-items: center;
}

/deep/ .ant-form-item {
  width: 70%;
}
</style>