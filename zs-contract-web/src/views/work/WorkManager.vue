<template>
  <page-header-wrapper>
    <a-card :bordered="false">
      <my-search :page="page" @search="search"></my-search>
      <a-form-item>
        <a-button type="primary" @click="handleAdd">发布作业</a-button>
      </a-form-item>
      <my-page-table ref="myPageTable" :req-url="queryPage" :where="where">
        <template slot-scope="{ list }">
          <a-table :columns="columns"
                   :data-source="list"
                   :pagination="false"
                   :rowKey="record=>record.id">
            <template slot="action" slot-scope="text, record">
              <div class="btn">
                <a-button type="primary" icon="check-circle" size="small" @click="look(record)">
                  完成情况
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

export default {
  name: "WorkManager",
  components: {
    MySearch,
    MyPageTable,
  },
  data() {
    return {
      advanced: false,
      labelCol: {span: 5},
      wrapperCol: {span: 18, offset: 1},
      queryPage: baseUrl.work.queryPage,
      crud: baseUrl.work.crud,
      where: {},
      page: {
        model: [
          {
            type: 'select',
            name: 'clazzId',
            title: '班级',
            //下拉选择需要传入数据
            data: [],
            id: 'id',
            value: 'name'
          },
          {type: 'date2', name: 'time', title: '时间'}
        ]
      },
      columns: [
        {
          title: "班级",
          dataIndex: "clazzName",
          align: 'center'
        },
        {
          title: "科目名称",
          dataIndex: "subjectName",
          align: 'center'
        },
        {
          title: "发布人",
          dataIndex: "teacherName",
          align: 'center'
        },
        {
          title: "作业日期",
          dataIndex: "time",
          align: 'center'
        },
        {
          title: "创建时间",
          dataIndex: "createTime",
          align: 'center'
        },
        {
          title: "操作",
          width: 150,
          dataIndex: "action",
          scopedSlots: {customRender: "action"}
        },
      ],
      student: {}
    };
  },
  mounted() {
    this.getClazzList()
    this.search()
  },
  methods: {
    handleAdd() {
      this.$router.push({
        path: '/WorkAddEdit'
      })
    },
    handleEdit(record) {
      this.$router.push({
        path: '/WorkAddEdit',
        query: {
          workId: record.id,
          clazzId: record.clazzId,
          time: record.time
        }
      })
    },
    handleDelete(record) {
      let model = this.$confirm({
        title: "提示",
        content: "确定删除后，此次作业的所有题目都会删除且无法撤销，请谨慎选择",
        okText: '我确定删除',
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
    look(record) {
      record.visible = true
      this.student = record
    },
    close() {
      this.student = this.student.visible = false
    },
    getClazzList() {
      reqApi({
        url: baseUrl.clazz.myList,
      }).then(res => {
        this.page.model[0].data = res.data
      })
    },
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