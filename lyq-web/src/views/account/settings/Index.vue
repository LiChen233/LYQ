<template>
  <a-card :bordered="false">
    <a-form-model :model="user" :label-col="labelCol" :wrapper-col="wrapperCol">
      <a-form-model-item label="姓名">
        <a-input v-model="user.username" disabled/>
      </a-form-model-item>
      <a-form-model-item label="性别">
        <a-input v-model="user.gender" disabled/>
      </a-form-model-item>
      <a-form-model-item label="学号/工号">
        <a-input v-model="user.account" disabled/>
      </a-form-model-item>
      <a-form-model-item label="所在班级">
        <a-input v-model="clazz" disabled/>
      </a-form-model-item>
      <a-form-model-item label="手机号">
        <a-input v-model="user.phone" placeholder="请输入手机号"/>
      </a-form-model-item>
      <a-form-model-item :wrapper-col="{ span: 14, offset: 4 }">
        <a-button type="primary" @click="onSubmit">
          提交
        </a-button>
      </a-form-model-item>
    </a-form-model>
  </a-card>
</template>

<script>
import {edit, baseUrl, add as userApiAdd, edit as userApiEdit} from "@/api/system/user";
import reqApi from "@/utils/reqApi";

export default {
  name: "settings",
  data() {
    return {
      labelCol: {span: 4},
      wrapperCol: {span: 8},
      user: {
        phone: ''
      },
      clazz: ''
    };
  },
  mounted() {
    this.$store.dispatch('GetInfo').then(data => {
      reqApi({
        url: baseUrl.clazz.crud,
        params: {id: data.clazzId}
      }).then(res => {
        let arr = []
        for (let key in res.data) {
          arr.push(res.data[key].name)
        }
        this.clazz = arr.toString()
      })
      data.gender = data.gender == 1 ? '男' : '女'
      this.user = data
    })
  },
  methods: {
    onSubmit() {
      let user = this.user
      edit(user).then(() => {
        this.$message.success('保存成功')
      })
    },
  },
}
</script>

<style lang="less" scoped>
.ant-input {
  background-color: #FFF;
}
</style>