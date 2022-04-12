<template>
  <page-header-wrapper>
    <a-card :bordered="false">
      <my-search :page="page" @search="search"></my-search>
      <a-form-item>
        <a-button type="primary" @click="handleAdd">添加科目</a-button>
      </a-form-item>
      <subject-add-edit ref="addEdit" @success="search"></subject-add-edit>
      <my-page-table ref="myPageTable" :req-url="queryPage" :where="where">
        <template slot-scope="{ list }">
          <a-table :columns="columns"
                   :data-source="list"
                   :pagination="false"
                   :rowKey="record=>record.id">
            <template slot="action" slot-scope="text, record">
              <div class="btn">
                <a-button type="primary" icon="edit" size="small" @click="handleEdit(record)">
                  编辑
                </a-button>
                <a-button type="danger" icon="delete" size="small" @click="handleDelete(record)">
                  删除
                </a-button>
              </div>
            </template>
          </a-table>
        </template>
      </my-page-table>
    </a-card>
  </page-header-wrapper>
</template>

<script>
import {baseUrl} from "@/api/system/user";
import reqApi from "@/utils/reqApi";
import MyPageTable from "@/components/My/MyPageTable";
import MySearch from "@/components/My/MySearch";
import SubjectAddEdit from "@/views/subject/SubjectAddEdit";

export default {
  name: "SubjectManager",
  components: {
    SubjectAddEdit,
    MySearch,
    MyPageTable,
  },
  data() {
    return {
      advanced: false,
      labelCol: {span: 5},
      wrapperCol: {span: 18, offset: 1},
      queryPage: baseUrl.subject.queryPage,
      crud: baseUrl.subject.crud,
      where: {},
      page: {model: [{type: 'text', name: 'name', title: '班级名称'}]},
      columns: [
        {
          title: "科目名字",
          dataIndex: "name",
          align: 'center'
        },
        {
          title: "创建时间",
          dataIndex: "createTime",
          align: 'center'
        },
        {
          title: "创建人",
          dataIndex: "createMan",
          align: 'center'
        },
        {
          title: "修改时间",
          dataIndex: "updateTime",
          align: 'center'
        },
        {
          title: "修改人",
          dataIndex: "updateMan",
          align: 'center'
        },
        {
          title: "操作",
          width: 150,
          dataIndex: "action",
          scopedSlots: {customRender: "action"}
        },
      ]
    };
  },
  mounted() {
    this.search()
  },
  methods: {
    handleAdd() {
      this.$refs.addEdit.add()
    },
    handleEdit(record) {
      this.$refs.addEdit.edit(record)
    },
    handleDelete(record) {
      let model = this.$confirm({
        title: "提示",
        content: "请确认删除",
        onOk: () => {
          reqApi({
            url: this.crud,
            params: {
              id: record.id
            },
            method: 'delete'
          }).then(() => {
            this.$message.success("删除成功");
            this.search()
            model.destroy();
          })
        }
      });
    },
    toggleAdvanced() {
      this.advanced = !this.advanced
    },
    search(where) {
      this.$refs.myPageTable.reload(where)
    }
  },
};
</script>

<style scoped>
.btn {
  display: flex;
}

.btn /deep/ .ant-btn {
  margin-left: 5px;
}

.btn /deep/ .ant-btn:nth-child(1) {
  margin: 0;
}
</style>