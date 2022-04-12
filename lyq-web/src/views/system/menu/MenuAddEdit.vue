<template>
  <my-drawer-form
      :title="isAdd?'新增菜单':'编辑菜单'"
      :visible.sync="visible"
      :load-key="loadKey"
      @submit="submit">
    <a-form-model ref="form" :model="data" :rules="rules" :label-col="labelCol" :wrapper-col="wrapperCol">
      <a-form-model-item label="类型" prop="type">
        <a-select v-model="data.type" placeholder="请选择类型">
          <a-select-option :value="0">菜单夹</a-select-option>
          <a-select-option :value="1">菜单</a-select-option>
        </a-select>
      </a-form-model-item>

      <a-form-model-item label="父级菜单" prop="pid">
        <a-tree-select
          v-model="data.pid"
          placeholder="请选择父级菜单"
          style="width: 100%"
          :dropdown-style="{ maxHeight: '400px', overflow: 'auto' }"
          :tree-data="menuList"
          :replace-fields="{key: 'id', value: 'id'}"
          tree-default-expand-all>
        </a-tree-select>
      </a-form-model-item>

      <a-form-model-item label="标题" prop="title">
        <a-input v-model="data.title" placeholder="请输入标题"></a-input>
      </a-form-model-item>

      <a-form-model-item label="图标" prop="icon">
        <a-select v-model="data.icon" placeholder="请选择图标" allow-clear show-search>
          <a-select-option
            :key="index"
            v-for="(item, index) in iconList"
            :value="item"
          >
            <a-icon :type="item" style="margin-right: 6px" />
            {{ item }}
          </a-select-option>
        </a-select>
      </a-form-model-item>

      <a-form-model-item label="序号" prop="sortNum">
        <a-input-number v-model="data.sortNum" :min="0" :max="20" :step="0.1" />
      </a-form-model-item>

      <a-form-model-item label="路径" prop="path">
        <a-select v-model="data.path" placeholder="请输入路径" show-search>
          <a-select-option
            v-for="(item, index) in pathList"
            :key="index"
            :value="item">
            {{ item }}
          </a-select-option>
        </a-select>
      </a-form-model-item>

      <template v-if="data.type === 1">
        <a-form-model-item label="权限" prop="permissionList">
          <div style="display: flex; flex-direction: column">
            <template v-for="(item, index) in data.permissionList">
              <div :key="index" style="display: flex; margin-bottom: 5px; align-items: center">
                <a-input style="width: calc(100% - 22px); margin-right: 8px" v-model="data.permissionList[index]"></a-input>
                <a-icon
                  class="dynamic-delete-button"
                  type="minus-circle-o"
                  @click="removePermission(index)"
                />
              </div>
            </template>
            <a-button type="dashed" style="width: 60%" @click="addPermission">
              <a-icon type="plus" /> 添加权限
            </a-button>
          </div>
        </a-form-model-item>

      </template>
    </a-form-model>
  </my-drawer-form>
</template>

<script>
import Vue from "vue";
import {add as menuApiAdd, edit as menuApiEdit} from '@/api/system/menu'
import {asyncRouterMap} from '@/config/router.config.js'
import MyDrawerForm from '@/components/My/MyDrawerForm'
import { customIcon } from "@/enums/system";
import { FormModel, TreeSelect } from 'ant-design-vue'
Vue.use(FormModel)
Vue.use(TreeSelect)

const formData = {
  pid: 0,
  title: null,
  icon: null,
  path: null,
  type: 0,
  sortNum: 0,
  permissionList: []
}

export default {
  name: 'MenuAdd',
  components: {
    MyDrawerForm
  },
  props: {
    menuTree: {
      type: Array,
      default: []
    }
  },
  data () {
    return {
      visible: false,
      labelCol: { span: 5 },
      wrapperCol: { span: 18 },
      rules: {
        pid: { required: true, message: '请选择父级菜单' },
        title: { required: true, message: '标题不能为空' },
        path: { required: true, message: '路径不能为空' },
      },
      data: {},
      iconList: customIcon,
      isAdd: true
    }
  },
  computed: {
    menuList: function () {
      const list = this.$myUtils.coll.filterList(this.menuTree ? this.menuTree : [], [1], 'children', 'type')
      return [{
        id: 0,
        pid: 0,
        type: 0,
        title: '一级菜单',
        children: list
      }]
    },
    pathList: function () {
      let pathList = []
      const fun = (list) => {
        for (let i = 0; i < list.length; i++) {
          const { path, children } = list[i]
          pathList.push(path)
          if (this.$myUtils.coll.isNotEmpty(children)){
            fun(children)
          }
        }
      }
      const {children} = asyncRouterMap[0]
      fun(children)
      return pathList
    },
    loadKey: function () {
      return 'admin_sys_menu/' + (this.isAdd ? 'add' : 'edit')
    }
  },
  created() {
  },
  methods: {
    add (pid, sortNum) {
      this.data = {...formData}
      this.isAdd = true
      this.data.pid = pid
      this.data.sortNum = sortNum
      if (pid > 0) {
        this.data.type = 1
      }
      this.show();
    },
    edit (ref) {
      this.isAdd = false
      this.data = {...ref}
      this.show()
    },
    show () {
      this.visible = true
    },
    addPermission () {
      this.data.permissionList.push('')
    },
    removePermission (index) {
      this.data.permissionList.splice(index, 1)
    },
    submit () {
      this.$refs.form.validate(valid => {
        if (valid) {
          const success = (res) => {
            this.$message.success('保存成功')
            this.$emit('success')
            this.visible = false
          }
          if (this.isAdd) {
            menuApiAdd(this.data).then(success)
          }else{
            menuApiEdit(this.data).then(success)
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
