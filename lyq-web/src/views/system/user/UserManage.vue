<template>
  <page-header-wrapper>
    <a-card :bordered="false">
      <my-search :page="page" @search="search"></my-search>
      <a-form-item>
        <a-button type="primary" @click="handleAdd">添加用户</a-button>
      </a-form-item>
      <user-add-edit ref="addEdit" @success="search"></user-add-edit>
      <my-page-table ref="myPageTable" :req-url="reqUrl" :where="where">
        <template slot-scope="{ list }">
          <a-table :columns="columns"
                   :data-source="list"
                   :pagination="false"
                   :rowKey="record=>record.id">
            <template slot="roleList" slot-scope="roleList, record">
              <div class="tags">
                <my-role-info v-for="(roleId, index) in roleList" :role-id="roleId"></my-role-info>
              </div>
            </template>
            <template slot="clazzNameList" slot-scope="clazzNameList, record">
              <div class="tags">
                <a-tag color="blue" v-for="item in clazzNameList">{{ item }}</a-tag>
              </div>
            </template>
            <template slot="action" slot-scope="text, record">
              <a @click="handleEdit(record)">
                <a-icon type="edit"/>
                编辑</a>
              <a-divider type="vertical"/>
              <a @click="handleDelete(record)">
                <a-icon type="delete"/>
                删除</a>
            </template>
          </a-table>
        </template>
      </my-page-table>
    </a-card>
  </page-header-wrapper>
</template>

<script>
import {del as userDel, api, baseUrl} from "@/api/system/user";

import UserAddEdit from '@/views/system/user/UserAddEdit'
import MyPageTable from "@/components/My/MyPageTable";
import MyRoleInfo from "@/components/My/info/MyRoleInfo";
import MySearch from "@/components/My/MySearch";
import reqApi from "@/utils/reqApi";

export default {
  name: "UserManage",
  components: {
    MySearch,
    MyRoleInfo,
    MyPageTable,
    UserAddEdit,
  },
  data() {
    return {
      advanced: false,
      labelCol: {span: 5},
      wrapperCol: {span: 18, offset: 1},
      reqUrl: api.queryPage,
      where: {},
      page: {
        model: [
          {type: 'text', name: 'username', title: '姓名'},
          {type: 'text', name: 'account', title: '账号'},
          {type: 'text', name: 'phone', title: '手机号'},
          {
            type: 'select',
            name: 'clazzId',
            title: '班级',
            //下拉选择需要传入数据
            data: [],
            id: 'id',
            value: 'name'
          },
          {
            type: 'select',
            name: 'subjectId',
            title: '科目',
            //下拉选择需要传入数据
            data: [],
            id: 'id',
            value: 'name'
          },
          {
            type: 'select',
            name: 'gander',
            title: '性别',
            //下拉选择需要传入数据
            data: [{id: 1, value: '男生'}, {id: 0, value: '女生'}],
          },
        ],
      },
      columns: [
        {
          title: "用户名",
          dataIndex: "username",
        },
        {
          title: "账号",
          dataIndex: "account",
        },
        {
          title: "手机号",
          dataIndex: "phone",
        },
        {
          title: "负责科目",
          dataIndex: "subjectName",
        },
        {
          title: "角色",
          dataIndex: "roleList",
          align: 'center',
          scopedSlots: {customRender: "roleList"}
        },
        {
          title: "班级",
          dataIndex: "clazzNameList",
          align: 'center',
          scopedSlots: {customRender: "clazzNameList"}
        },
        {
          title: "操作",
          width: 150,
          dataIndex: "action",
          scopedSlots: {customRender: "action"}
        },
      ],
      subjectList: [],
      clazzList: []
    };
  },
  created() {
    this.getSubjectList()
    this.getClazzList()
  },
  mounted() {
    this.$refs.myPageTable.reload()
  },
  methods: {
    handleAdd() {
      this.$refs.addEdit.add()
    },
    handleEdit(record) {
      console.log(record)
      this.$refs.addEdit.edit(record)
    },
    handleDelete(record) {
      let model = this.$confirm({
        title: "提示",
        content: "确定要删除 " + record.username + " 这个用户吗?",
        onOk: () => {
          userDel(record.id).then(res => {
            this.$message.success("删除用户成功");
            this.search()
            model.destroy();
          })
        },
        onCancel() {
        },
      });
    },
    toggleAdvanced() {
      this.advanced = !this.advanced
    },
    search(where) {
      this.$refs.myPageTable.reload(where)
    },
    getSubjectList() {
      reqApi({
        url: baseUrl.subject.list,
        method: 'post'
      }).then(res => {
        this.$data.page.model[4].data = res.data
      })
    },
    getClazzList() {
      reqApi({
        url: baseUrl.clazz.list,
        method: 'post'
      }).then(res => {
        this.$data.page.model[3].data = res.data
      })
    },
  },
};
</script>

<style lang="less" scoped>
.tags {
  display: flex;
  flex-direction: column;
  align-items: center;
}

.tags /deep/ .ant-tag {
  margin: 2px 0;
}
</style>
