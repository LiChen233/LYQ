<template>
  <a-modal
    :title="title"
    :visible="visible"
    width="80%"
    centered
    class="model"
    @ok="close"
    @cancel="close"
  >
    <my-search :page="page" @search="search"></my-search>
    <my-page-table ref="myStuTable" :req-url="reqUrl" :where="where">
      <template slot-scope="{ list }">
        <a-table :columns="columns"
                 :data-source="list"
                 :pagination="false"
                 :rowKey="record=>record.id">
          <template slot="roleList" slot-scope="roleList, record">
            <div class="tags">
              <my-role-info v-for="roleId in roleList" :role-id="roleId"></my-role-info>
            </div>
          </template>
        </a-table>
      </template>
    </my-page-table>
  </a-modal>
</template>

<script>
import {api} from "@/api/system/user";
import MySearch from "@/components/My/MySearch";
import MyPageTable from "@/components/My/MyPageTable";
import MyRoleInfo from "@/components/My/info/MyRoleInfo";

export default {
  name: "MyStudent",
  components: {
    MySearch,
    MyPageTable,
    MyRoleInfo
  },
  props: {
    title: {
      type: String,
      default: '查看学生'
    },
    clazzId: {
      type: String,
      default: ''
    },
    visible: {
      type: Boolean,
      default: false
    }
  },
  watch: {
    clazzId(id) {
      if (id) {
        this.where.clazzId = this.clazzId
        setTimeout(() => {
          this.search(this.where)
        }, 300)
      }
    }
  },
  data() {
    return {
      reqUrl: api.queryPage,
      where: {},
      page: {
        model: [
          {type: 'text', name: 'username', title: '姓名'},
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
          title: "角色",
          dataIndex: "roleList",
          align: 'center',
          scopedSlots: {customRender: "roleList"}
        },
        {
          title: "负责科目",
          dataIndex: "subjectName",
        }
      ],
    }
  },
  methods: {
    search(where) {
      this.$refs.myStuTable.reload(where)
    },
    close(){
      this.$emit('close');
    }
  }
}
</script>

<style lang="less" scoped>
.model {
  width: 80%;
}
.tags{
  display: flex;
  flex-direction: column;
  align-items: center;
}
.tags /deep/ .ant-tag {
  margin: 2px 0;
}
</style>