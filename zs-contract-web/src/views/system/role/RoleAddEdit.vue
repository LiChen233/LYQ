<template>
  <my-drawer-form
      :title="isAdd ? '新增角色' : '编辑角色'"
      :width="700"
      :visible.sync="visible"
      :load-key="loadKey"
      @submit="submit">
    <a-row>
      <a-col :span="12">
        <a-tree :tree-data="menuTree"
                defaultExpandAll
                checkStrictly
                checkable
                v-model="menuCheckedKeys">
        </a-tree>
      </a-col>
      <a-col :span="12">
        <a-form-model ref="form" :model="data" :rules="rules" :label-col="labelCol" :wrapper-col="wrapperCol">
          <a-form-model-item label="标题" prop="title">
            <a-input v-model="data.title" placeholder="请输入标题"></a-input>
          </a-form-model-item>
          <a-form-model-item label="说明" prop="remark">
            <a-input v-model="data.remark" placeholder="请输入说明"></a-input>
          </a-form-model-item>
        </a-form-model>
      </a-col>
    </a-row>
  </my-drawer-form>
</template>

<script>
import Vue from "vue";
import {roleApiAdd, roleApiEdit, roleApiUrl} from "@/api/system/role"
import {getTreeList} from "@/api/system/menu";
import MyDrawerForm from '@/components/My/MyDrawerForm'
import { FormModel, TreeSelect } from 'ant-design-vue'
Vue.use(FormModel)
Vue.use(TreeSelect)

const formData = {
  title: null,
  remark: null,
  sysMenuIdList: []
}

export default {
  name: 'RoleAddEdit',
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
        title: { required: true, message: '标题不能为空' },
      },
      data: {},
      menuTree: [],
      menuCheckedKeys: {
        checked: []
      },
      menuIdKeys: []
    }
  },
  watch: {
    menuCheckedKeys: function (newVal) {
      const checked = newVal.checked
      const menuIdList = []
      checked.forEach(keys => {
        for (let i = 0; i < this.menuIdKeys.length; i++) {
          const item = this.menuIdKeys[i]
          if (item.keys === keys){
            menuIdList.push(item.id)
            break
          }
        }
      })
      this.data.sysMenuIdList = [...menuIdList]
    }
  },
  computed: {
    loadKey: function () {
      return this.isAdd ? roleApiUrl.add : roleApiUrl.edit
    }
  },
  created() {
    this.getMenuTree();
  },
  methods: {
    getMenuTree () {
      getTreeList().then(res => {
        this.menuTree = res.data
        this.setMenuIdKeys()
      });
    },
    setMenuIdKeys () {
      const list = []
      const fun = (l, k) => {
        for (let i = 0; i < l.length; i++) {
          const {id, children} = l[i]
          let keys = k + '-' + i
          list.push({id: id, keys: keys})
          if (this.$myUtils.coll.isNotEmpty(children)) {
            fun(children, keys)
          }
        }
      }
      const tree = this.menuTree
      fun(tree, '0')
      this.menuIdKeys = list
    },
    add () {
      this.isAdd = true
      this.data = {...formData}
      this.menuCheckedKeys = {checked: []}
      this.show()
    },
    edit (ref) {
      this.isAdd = false
      this.data = {...ref}
      const idList = ref.sysMenuIdList
      const checked = []
      idList.forEach(id => {
        for (let i = 0; i < this.menuIdKeys.length; i++) {
          const item = this.menuIdKeys[i]
          if (item.id === id){
            checked.push(item.keys)
            break
          }
        }
      })
      this.menuCheckedKeys = {checked: checked}
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
          if (this.isAdd) {
            roleApiAdd(this.data).then(success)
          }else{
            roleApiEdit(this.data).then(success)
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
