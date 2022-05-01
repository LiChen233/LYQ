<template>
  <page-header-wrapper>
    <a-card :bordered="false" v-show="show">
      <my-search :page="page" @search="search"></my-search>
      <my-page-table ref="myPageTable" :req-url="queryPage" :where="where">
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
                <a-button v-if="record.rate==null" type="primary" icon="edit" size="small" @click="look(record)">
                  去写作业
                </a-button>
                <a-button v-else type="primary" icon="edit" size="small" @click="look2(record)">
                  查看
                </a-button>
              </div>
            </template>
          </a-table>
        </template>
      </my-page-table>
    </a-card>
    <a-card :bordered="false" v-show="!show">
      <h1>你好</h1>
      <h2>你还未被分配班级，请联系管理员进行分配</h2>
    </a-card>
  </page-header-wrapper>
</template>

<script>
import {baseUrl} from "@/api/system/user";
import reqApi from "@/utils/reqApi";
import MyPageTable from "@/components/My/MyPageTable";
import MySearch from "@/components/My/MySearch";

export default {
  name: "MyWork",
  components: {
    MySearch,
    MyPageTable,
  },
  data() {
    return {
      advanced: false,
      labelCol: {span: 5},
      wrapperCol: {span: 18, offset: 1},
      queryPage: baseUrl.work.myWork,
      crud: baseUrl.work.crud,
      where: {},
      show: true,
      page: {
        model: [{type: 'date2', name: 'time', title: '时间'}]
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
          title: "完成情况",
          align: 'center',
          scopedSlots: {customRender: "done"}
        },
        {
          title: "操作",
          width: 150,
          scopedSlots: {customRender: "action"}
        },
      ],
      student: {}
    };
  },
  created() {
    console.log(this.$store.getters.userInfo)
    let clazz = this.$store.getters.userInfo.clazzId
    if (clazz === null || clazz === '' || clazz.length === 0) {
      this.show = false
      this.$message.info('你还未被分配班级，请联系管理员进行分配')
    }
  },
  mounted() {
    this.getClazzList()
    this.search()
  },
  methods: {
    toggleAdvanced() {
      this.advanced = !this.advanced
    },
    search(where) {
      this.$refs.myPageTable.reload(where)
    },
    look(record) {
      this.$router.push({
        path: '/DoWork',
        query: {
          workId: record.id,
          showRemark: '1'
        }
      })
    },
    look2(record) {
      this.$router.push({
        path: '/DoWork',
        query: {
          workId: record.id,
          showRemark: '2'
        }
      })
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

.tags {
  display: flex;
  flex-direction: column;
  align-items: center;
}

.tags /deep/ .ant-tag {
  margin: 2px 0;
}
</style>