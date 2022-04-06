<template>
  <my-drawer-form
    :title="isAdd ? '新建班级' : '编辑班级'"
    :visible.sync="visible"
    :load-key="url"
    @submit="submit">
    <a-form-model ref="form" :model="data" :rules="rules" :label-col="labelCol" :wrapper-col="wrapperCol">
      <a-form-model-item label="班级名称" prop="name">
        <a-input v-model="data.name" placeholder="请输入班级名称"></a-input>
      </a-form-model-item>
    </a-form-model>
  </my-drawer-form>
</template>

<script>
import {baseUrl} from '@/api/system/user'
import reqApi from "@/utils/reqApi";
import MyDrawerForm from '@/components/My/MyDrawerForm'
import MyUnitTreeSelect from "@/components/My/select/MyUnitTreeSelect";

export default {
  name: 'ClazzAddEdit',
  components: {
    MyUnitTreeSelect,
    MyDrawerForm
  },
  props: {},
  data() {
    return {
      data: {},
      url: baseUrl.clazz.crud,
      isAdd: true,
      visible: false,
      labelCol: {span: 5},
      wrapperCol: {span: 18},
      rules: {
        name: {required: true, message: '请输入班级名称'},
      },
    }
  },
  methods: {
    add() {
      this.data = {}
      this.isAdd = true
      this.visible = true
    },
    edit(ref) {
      this.isAdd = false
      this.data = {...ref}
      this.visible = true
    },
    submit() {
      this.$refs.form.validate(valid => {
        if (valid) {
          reqApi({
            url: this.url,
            data: this.data,
            method: this.isAdd ? 'post' : 'put'
          }).then(() => {
            this.$message.success('保存成功')
            this.$emit('success')
            this.visible = false
          })
        }
      })
    }
  }
}
</script>

<style scoped>

</style>
