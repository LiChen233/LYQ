<template>
  <page-header-wrapper>
    <a-card :bordered="false">

    <div class="table-operator">
      <a-button type="primary" icon="plus" @click="handleAdd">新增角色</a-button>
    </div>
    <role-add-edit ref="addEdit" @success="search"></role-add-edit>

    <my-page-table ref="myPageTable" :req-url="reqUrl" :where="where">
      <template slot-scope="{ list }">
        <a-table :columns="columns"
                 :data-source="list"
                 :pagination="false"
                 :rowKey="record=>record.id">
          <template slot="action" slot-scope="text, record">
            <a @click="handleEdit(record)"> <a-icon type="edit" />编辑 </a>
            <a-divider type="vertical" />
            <a @click="handleDelete(record)"> <a-icon type="delete" />删除 </a>
          </template>
        </a-table>
      </template>
    </my-page-table>
  </a-card>
  </page-header-wrapper>
</template>

<script>
import RoleAddEdit from '@/views/system/role/RoleAddEdit'
import MyPageTable from "@/components/My/MyPageTable";
import {roleApiDel, roleApiUrl} from "@/api/system/role";

export default {
  name: "RoleManage",
  components: {
    RoleAddEdit,
    MyPageTable
  },
  data() {
    return {
      columns: [
        {
          title: "标题",
          dataIndex: "title"
        },
        {
          title: "说明",
          dataIndex: "remark"
        },
        {
          title: "操作",
          width: 150,
          dataIndex: "action",
          scopedSlots: { customRender: "action" }
        },
      ],
      reqUrl: roleApiUrl.queryPage,
      where: {
        title: null
      },
    };
  },
  created() {
  },
  mounted() {
    this.search()
  },
  methods: {
    handleAdd () {
      this.$refs.addEdit.add()
    },
    handleEdit(record) {
      this.$refs.addEdit.edit(record)
    },
    handleDelete(record) {
      let model = this.$confirm({
        title: "提示",
        content: "确定要删除 " + record.title + " 角色吗?",
        onOk: () => {
          roleApiDel(record.id).then(res => {
            this.$message.success("删除角色成功");
            model.destroy();
            this.search()
          })
        },
        onCancel() {},
      });
    },
    search() {
      this.$refs.myPageTable.reload()
    }
  },
};
</script>
