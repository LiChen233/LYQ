<template>
  <a-modal
    title="作业完成情况"
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
          <template slot="done" slot-scope="text, record">
            <div class="tags">
              <a-tag v-if="record.rate==null">
                未完成
              </a-tag>
              <a-tag color="#a0d911" v-if="record.rate && record.rate.rateAuto!=null">
                系统评分 {{ record.rate.rateAuto }} 分
              </a-tag>
              <a-tag color="#1890ff" v-if="record.rate && record.rate.rate!=null">
                老师评分 {{ record.rate.rate }} 分
              </a-tag>
            </div>
          </template>
          <template slot="action" slot-scope="text, record">
            <div class="btn">
              <a-button type="primary" icon="eye" size="small" @click="look(record)" v-if="record.rate!=null">
                查看作业
              </a-button>
            </div>
          </template>
        </a-table>
      </template>
    </my-page-table>
  </a-modal>
</template>

<script>
import {api} from "@/api/system/user";
import {baseUrl} from "@/api/system/user";
import MySearch from "@/components/My/MySearch";
import MyPageTable from "@/components/My/MyPageTable";
import MyRoleInfo from "@/components/My/info/MyRoleInfo";

export default {
  name: "MyStudentWork",
  components: {
    MySearch,
    MyPageTable,
    MyRoleInfo
  },
  props: {
    clazzId: {
      type: String,
      default: ''
    },
    id: {
      type: String,
      default: ''
    },
    visible: {
      type: Boolean,
      default: false
    }
  },
  watch: {
    id(id) {
      if (id) {
        this.where.clazzId = this.clazzId
        this.where.workId = this.id
        setTimeout(() => {
          this.search(this.where)
        }, 300)
      }
    }
  },
  data() {
    return {
      reqUrl: baseUrl.work.getWorkDone,
      where: {},
      page: {
        model: [
          {type: 'text', name: 'username', title: '姓名'},
        ],
      },
      columns: [
        {
          title: "用户名",
          dataIndex: "username",
        },
        {
          title: "手机号",
          dataIndex: "phone",
        },
        {
          title: "完成情况",
          align: 'center',
          scopedSlots: {customRender: "done"}
        },
        {
          title: "操作",
          width: 150,
          dataIndex: "action",
          scopedSlots: {customRender: "action"}
        },
      ],
    }
  },
  methods: {
    search(where) {
      this.$refs.myStuTable.reload(where)
    },
    close() {
      this.$emit('close');
    },
    look(record) {
      this.$router.push({
        path: '/DoWork',
        query: {
          workId: this.id,
          showRemark: '2',
          userId: record.id,
          rate: record.rate.rate
        }
      })
    }
  }
}
</script>

<style lang="less" scoped>
.model {
  width: 80%;
}

.tags {
  display: flex;
  flex-direction: column;
  align-items: center;
}

.tags /deep/ .ant-tag {
  margin: 2px 0;
}

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