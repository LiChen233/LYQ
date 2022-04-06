<template>
  <page-header-wrapper>
    <a-card :bordered="false">
      <my-search :page="page" @search="search"></my-search>
      <a-form-item>
        <a-button type="primary" @click="handleAdd">添加公告</a-button>
      </a-form-item>
      <notice-add-edit ref="addEdit" @success="search"></notice-add-edit>
      <my-page-table ref="myPageTable" :req-url="queryPage" :where="where">
        <template slot-scope="{ list }">
          <a-table :columns="columns"
                   :data-source="list"
                   :pagination="false"
                   :rowKey="record=>record.id">
            <template slot="icon" slot-scope="text, record">
              <a-icon :type="record.icon"/>
            </template>
            <template slot="status" slot-scope="text, record">
              <a-tag color="blue" v-if="record.status==='N'">保存</a-tag>
              <a-tag color="blue" v-if="record.status==='P'">发布</a-tag>
              <a-tag color="blue" v-if="record.status==='O'">下架</a-tag>
            </template>
            <template slot="action" slot-scope="text, record">
              <div class="btn">
                <a-button v-if="record.status==='N' || record.status==='O'" type="primary" icon="edit" size="small"
                          @click="noticePush(record)">
                  发布
                </a-button>
                <a-button v-if="record.status==='P'" type="primary" icon="edit" size="small" @click="noticeOff(record)">
                  下架
                </a-button>
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
import NoticeAddEdit from "@/views/notice/NoticeAddEdit";
import {timeFix} from "@/utils/util";

export default {
  name: "NoticeManager",
  components: {
    NoticeAddEdit,
    MySearch,
    MyPageTable,
  },
  data() {
    return {
      advanced: false,
      labelCol: {span: 5},
      wrapperCol: {span: 18, offset: 1},
      queryPage: baseUrl.notice.queryPage,
      crud: baseUrl.notice.crud,
      push: baseUrl.notice.push,
      off: baseUrl.notice.off,
      where: {},
      page: {model: [{type: 'text', name: 'name', title: '公告标题'}]},
      columns: [
        {
          title: "公告标题",
          dataIndex: "title",
          align: 'center'
        },
        {
          title: "公告内容",
          dataIndex: "remark",
          align: 'center'
        },
        {
          title: "公告图标",
          dataIndex: "icon",
          align: 'center',
          scopedSlots: {customRender: "icon"}
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
          title: "公告状态",
          dataIndex: "status",
          align: 'center',
          scopedSlots: {customRender: "status"}
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
    },
    noticePush(record) {
      let model = this.$confirm({
        title: "提示",
        content: "请确认发布",
        onOk: () => {
          reqApi({
            url: this.push,
            params: {
              id: record.id
            },
            method: 'post'
          }).then(() => {
            this.$message.success("发布成功");
            this.search()
            model.destroy();
          })
        }
      })
    },
    noticeOff(record) {
      let model = this.$confirm({
        title: "提示",
        content: "请确认下架",
        onOk: () => {
          reqApi({
            url: this.off,
            params: {
              id: record.id
            },
            method: 'post'
          }).then(() => {
            this.$message.success("下架成功");
            this.search()
            model.destroy();
          })
        }
      })
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