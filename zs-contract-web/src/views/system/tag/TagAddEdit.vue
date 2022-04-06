<template>
  <my-drawer-form
    :title="isAdd ? '新增标签' : '编辑标签'"
    :visible.sync="visible"
    :load-key="loadKey"
    @submit="submit">
      <a-form-model ref="form" :model="data" :rules="rules" :label-col="labelCol" :wrapper-col="wrapperCol">
        <a-form-model-item label="类型" prop="tagType">
          <a-select v-model="data.tagType" placeholder="请选择类型">
            <a-select-option v-for="(item,index) in options" :value="item.type" :key="index">
              {{item.label}}
            </a-select-option>
          </a-select>
        </a-form-model-item>
        <a-form-model-item label="名称" prop="tagName">
          <a-input v-model="data.tagName" placeholder="请输入说明"></a-input>
        </a-form-model-item>
      </a-form-model>
  </my-drawer-form>
</template>

<script>
import {tagApiAdd, tagApiEdit, tagApiUrl} from "@/api/system/tag"
import { tagEnum } from "@/enums/system/tag"
import MyDrawerForm from '@/components/My/MyDrawerForm'

const formData = {
  id:null,
  tagType: null,
  tagName: null,
}

export default {
  name: "TagAddEdit",
  components: {
    MyDrawerForm
  },
  data () {
    return {
      isAdd: true,
      visible: false,
      labelCol: { span: 5 },
      wrapperCol: { span: 18 },
      rules: {
        tagType: { required: true, message: '类型不能为空' },
        tagName: { required: true, message: '标签名不能为空' },
      },
      options: Object.values(tagEnum),
      data: {},
    }
  },
  computed: {
    loadKey: function () {
      return this.isAdd ? tagApiUrl.add : tagApiUrl.edit
    }
  },
  created() {
  },
  methods: {
    add () {
      this.isAdd = true
      this.data = {...formData}
      this.show()
    },
    edit (ref) {
      this.isAdd = false
      this.data = {...ref}
      this.show()
    },
    show () {
      this.visible = true
    },
    submit () {
      this.$refs.form.validate(valid => {
        if (valid) {
          const success = (res) => {
            this.$message.success('保存成功')
            this.$emit('success')
            this.visible = false
          }
          console.log(this.data)
          if (this.isAdd) {
            tagApiAdd(this.data).then(success)
          }else{
            tagApiEdit(this.data).then(success)
          }
        } else {
          return false;
        }
      });
    }
  }
}
</script>

<style scoped>

</style>