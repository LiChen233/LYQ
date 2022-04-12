<template>
  <my-drawer-form
    :title="isAdd ? '新建用户' : '编辑用户'"
    :visible.sync="visible"
    :load-key="loadKey"
    @submit="submit">
    <a-form-model ref="form" :model="data" :rules="rules" :label-col="labelCol" :wrapper-col="wrapperCol">
      <a-form-model-item label="用户名" prop="username">
        <a-input v-model="data.username" placeholder="请输入用户名"></a-input>
      </a-form-model-item>
      <a-form-model-item label="账号" prop="account">
        <a-input v-model="data.account" placeholder="请输入账号"></a-input>
      </a-form-model-item>
      <a-form-model-item label="密码" prop="password">
        <a-input v-model="data.password" placeholder="请输入密码"></a-input>
      </a-form-model-item>
      <a-form-model-item label="手机号" prop="phone">
        <a-input v-model="data.phone" placeholder="请输入手机号"></a-input>
      </a-form-model-item>
      <a-form-model-item label="性别" prop="gender">
        <a-select v-model="data.gender" placeholder="请选择性别">
          <a-select-option value="1">男</a-select-option>
          <a-select-option value="0">女</a-select-option>
        </a-select>
      </a-form-model-item>
      <a-form-model-item label="科目" prop="subjectId">
        <a-select allowClear v-model="data.subjectId" placeholder="请选择科目">
          <a-select-option
            :value="item.id"
            v-for="item in subjectList"
            :key="item.id"
          >
            {{ item.name }}
          </a-select-option>
        </a-select>
      </a-form-model-item>
      <a-form-model-item label="班级" prop="classId">
        <a-select allowClear v-model="data.clazzList" mode="multiple" placeholder="请选择班级">
          <a-select-option
            :value="item.id"
            v-for="item in clazzList"
            :key="item.id"
          >
            {{ item.name }}
          </a-select-option>
        </a-select>
      </a-form-model-item>
      <a-form-model-item label="用户角色" prop="roleList">
        <a-select mode="multiple"
                  v-model="data.roleList"
                  placeholder="请选择用户角色">
          <a-select-option v-for="item in roleList" :key="item.id" :value="item.id">{{ item.title }}</a-select-option>
        </a-select>
      </a-form-model-item>
    </a-form-model>
  </my-drawer-form>
</template>

<script>
import {add as userApiAdd, edit as userApiEdit, api as userApi, baseUrl} from '@/api/system/user'
import reqApi from "@/utils/reqApi";
import {roleApiGetList} from "@/api/system/role";
import MyDrawerForm from '@/components/My/MyDrawerForm'
import MyUnitTreeSelect from "@/components/My/select/MyUnitTreeSelect";

export default {
  name: 'UserAddEdit',
  components: {
    MyUnitTreeSelect,
    MyDrawerForm
  },
  props: {},
  data() {
    return {
      isAdd: true,
      visible: false,
      labelCol: {span: 5},
      wrapperCol: {span: 18},
      rules: {
        username: {required: true, message: '请输入用户名'},
        account: {required: true, message: '请输入账号'},
        password: [],
        roleList: {required: true, message: '请选择用户角色'}
      },
      data: {},
      roleList: [],
      subjectList: [],
      clazzList: []
    }
  },
  computed: {
    loadKey: function () {
      return this.isAdd ? userApi.add : userApi.edit
    }
  },
  created() {
    this.getRoleList()
    this.getSubjectList()
    this.getClazzList()
  },
  methods: {
    getRoleList() {
      roleApiGetList().then(res => {
        this.roleList = res.data
      })
    },
    getSubjectList() {
      reqApi({
        url: baseUrl.subject.list,
        method: 'post'
      }).then(res => {
        this.subjectList = res.data
      })
    },
    getClazzList() {
      reqApi({
        url: baseUrl.clazz.list,
        method: 'post'
      }).then(res => {
        this.clazzList = res.data
      })
    },
    add() {
      this.isAdd = true
      this.data = {}
      this.show()
    },
    edit(ref) {
      this.isAdd = false
      this.data = {...ref}
      this.show()
    },
    show() {
      let rules = [{
        pattern: /^(?![\d]+$)(?![a-zA-Z]+$)(?![^\da-zA-Z]+$)([^\u4e00-\u9fa5\s]){6,20}$/,
        message: '设置密码复杂度太低，必须包含大小写，数字，特殊字符，两种以上，不少于6位'
      }]
      if (this.isAdd) {
        rules.push({required: true, message: '请输入密码'})
      }
      //this.rules.password = [...rules]
      this.visible = true
    },
    submit() {
      this.$refs.form.validate(valid => {
        if (valid) {
          let data = this.data
          if (data.clazzList) {
            data.clazzId = data.clazzList.toString()
          }
          if (data.subjectId) {
            data.subjectId = data.subjectId.toString()
          }
          const success = () => {
            this.$message.success('保存成功')
            this.$emit('success')
            this.visible = false
          }
          if (this.isAdd) {
            userApiAdd(data).then(success)
          } else {
            userApiEdit(data).then(success)
          }
        }
      });
    }
  }
}
</script>

<style scoped>

</style>
