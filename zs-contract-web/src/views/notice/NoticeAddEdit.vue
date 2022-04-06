<template>
  <my-drawer-form
    :title="isAdd ? '新建公告' : '编辑公告'"
    :visible.sync="visible"
    :load-key="url"
    @submit="submit">
    <a-form-model ref="form" :model="data" :rules="rules" :label-col="labelCol" :wrapper-col="wrapperCol">
      <a-form-model-item label="公告标题" prop="name">
        <a-input v-model="data.title" placeholder="请输入公告标题"></a-input>
      </a-form-model-item>
      <a-form-model-item label="公告内容">
        <a-input style="min-height: 200px" v-model="data.remark" type="textarea" placeholder="请输入公告内容"/>
      </a-form-model-item>
      <a-form-model-item label="班级可见">
        <a-select allowClear v-model="data.clazzList" mode="multiple" placeholder="请选择班级，默认全体可见">
          <a-select-option
            :value="item.id"
            v-for="item in clazzList"
            :key="item.id"
          >
            {{ item.name }}
          </a-select-option>
        </a-select>
      </a-form-model-item>
      <a-form-model-item label="图标" prop="icon">
        <a-select v-model="data.icon" placeholder="请选择图标" allow-clear show-search>
          <a-select-option
            :key="index"
            v-for="(item, index) in iconList"
            :value="item"
          >
            <a-icon :type="item" style="margin-right: 6px"/>
            {{ item }}
          </a-select-option>
        </a-select>
      </a-form-model-item>
    </a-form-model>
  </my-drawer-form>
</template>

<script>
import {baseUrl} from '@/api/system/user'
import reqApi from "@/utils/reqApi";
import MyDrawerForm from '@/components/My/MyDrawerForm'
import MyUnitTreeSelect from "@/components/My/select/MyUnitTreeSelect";
import {customIcon} from "@/enums/system";

export default {
  name: 'NoticeAddEdit',
  components: {
    MyUnitTreeSelect,
    MyDrawerForm
  },
  props: {},
  data() {
    return {
      data: {},
      url: baseUrl.notice.crud,
      isAdd: true,
      visible: false,
      labelCol: {span: 5},
      wrapperCol: {span: 18},
      rules: {
        title: {required: true, message: '请输入公告名称'},
        remark: {required: true, message: '请输入公告内容'},
      },
      iconList: customIcon,
      clazzList: []
    }
  },
  mounted() {
    this.getClazzList()
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
          if (this.data.clazzList != null) {
            this.data.clazzId = this.data.clazzList.toString()
          }
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
    },
    getClazzList() {
      reqApi({
        url: baseUrl.clazz.list,
        method: 'post'
      }).then(res => {
        this.clazzList = res.data
      })
    }
  }
}
</script>

<style scoped>

</style>
