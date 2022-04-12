<template>
  <page-header-wrapper>
    <a-card :bordered="false">
      <div class="table-operator">
        <a-button type="primary" icon="plus" @click="handleAdd()">新建</a-button>
      </div>
      <menu-add-edit ref="addEdit" :menu-tree="list" @success="getData"></menu-add-edit>

      <my-spin req-url="admin_sys_menu/getTreeList">
        <a-table :columns="columns"
               :data-source="list"
               :pagination="false"
               :rowKey="record=>record.id">
        <template slot="_title" slot-scope="record">
          <a-icon :type="record.icon"></a-icon>
          {{record.title}}
        </template>
        <span slot="type" slot-scope="text">
          <template>
            <a-tag color='green' v-if="text===0">菜单夹</a-tag>
            <a-tag color="blue" v-else>菜单</a-tag>
          </template>
        </span>
        <span slot="permissionList" slot-scope="text, record">
          <template v-if="record.type === 1">
            <template v-if="record.permissionList && record.permissionList.length > 0">
              {{record.permissionList}}
            </template>
            <template v-else>
              暂无
            </template>
          </template>
        </span>
        <span slot="action" slot-scope="text, record">
          <a v-if="record.type===0" @click="handleAdd(record)"> <a-icon type="plus" />新增子菜单 </a>
          <a-divider v-if="record.type===0" type="vertical" />
          <a @click="handleEdit(record)"> <a-icon type="edit" />编辑 </a>
          <a-divider type="vertical" />
          <a @click="handleDelete(record)"> <a-icon type="delete" />删除 </a>
        </span>
      </a-table>
      </my-spin>
    </a-card>
  </page-header-wrapper>
</template>

<script>
import { getTreeList, del as menuDel } from "@/api/system/menu";

import MenuAddEdit from '@/views/system/menu/MenuAddEdit'
import MySpin from "@/components/My/MySpin";

export default {
  name: 'MenuManage',
  components: {
    MySpin,
    MenuAddEdit
  },
  data () {
    return {
      columns: [
        {
          title: "菜单名称",
          width: 200,
          key: "_title",
          scopedSlots: { customRender: '_title' },
        },
        {
          title: "路径",
          width: 180,
          dataIndex: "path"
        },
        {
          title: "类型",
          width: 80,
          align: "center",
          dataIndex: "type",
          scopedSlots: { customRender: "type" }
        },
        {
          title: "权限",
          dataIndex: "permissionList",
          scopedSlots: { customRender: "permissionList" }
        },
        {
          title: "操作",
          width: 300,
          dataIndex: "action",
          scopedSlots: { customRender: "action" }
        },
      ],
      list: []
    }
  },
  created() {
    this.getData();
  },
  methods: {
    getData () {
      getTreeList().then(res => {
        this.list = res.data
      })
    },
    handleAdd (ref) {
      let pid = 0
      let sortNum = 0
      if (ref) {
        pid = ref.id
        const children = ref.children
        if (this.$myUtils.coll.isNotEmpty(children)) {
          sortNum = children.length
        }
      } else {
        sortNum = this.list.length
      }
      this.$refs.addEdit.add(pid, sortNum)
    },
    handleEdit(ref) {
      this.$refs.addEdit.edit(ref)
    },
    handleDelete(record) {
      const { title, id } = record
      let model = this.$confirm({
        title: "提示",
        content: "确定要删除 " + title + " 这个菜单吗?",
        onOk: () => {
          menuDel(id).then(res => {
            this.$message.success("删除菜单成功");
            this.getData();
            model.destroy();
          })
        },
        onCancel() {},
      });
    },
  }
}
</script>

<style scoped>
</style>
