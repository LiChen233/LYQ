<template>
  <a-card :bordered="false">
    <my-search :page="page" @search="search"></my-search>
    <my-page-table ref="myPageTable" :req-url="queryPage" :where="where">
      <template slot-scope="{ list }">
        <a-table :columns="columns"
                 :data-source="list"
                 :pagination="false"
                 :rowKey="record=>record.id">
          <template slot="action" slot-scope="text, record">
            <my-file-upload
              :default-file-ids="record.files"
              :filesIds.sync="record.files"
              :x-id="record.id"
              file-type="image"
              :limit="1"
              @upload="upload"
              :multiple="false">
            </my-file-upload>
          </template>
        </a-table>
      </template>
    </my-page-table>
  </a-card>
</template>

<script>
import {baseUrl} from "@/api/system/user";
import reqApi from "@/utils/reqApi";
import MyPageTable from "@/components/My/MyPageTable";
import MySearch from "@/components/My/MySearch";
import MyFileUpload from "@/components/My/file/MyFileUpload";

export default {
  name: "TimetableManager",
  components: {
    MyFileUpload,
    MySearch,
    MyPageTable,
  },
  props: {},
  data() {
    return {
      labelCol: {span: 5},
      wrapperCol: {span: 18, offset: 1},
      queryPage: baseUrl.clazz.queryPageAndTimetable,
      crud: baseUrl.timetable.crud,
      where: {},
      page: {model: [{type: 'text', name: 'name', title: '班级名称'}]},
      columns: [
        {
          title: "班级名字",
          dataIndex: "name",
          align: 'center'
        },
        {
          title: "操作",
          width: 150,
          dataIndex: "action",
          scopedSlots: {customRender: "action"}
        },
      ],
      files: ''
    }
  },
  mounted() {
    this.search()
  },
  methods: {
    search(where) {
      this.$refs.myPageTable.reload(where)
    },
    upload(id, fileIds) {
      reqApi({
        url: baseUrl.timetable.crud,
        data: {
          clazzId: id,
          files: fileIds
        },
        method: 'post'
      })
    },
  }
}
</script>

<style lang="less" scoped>
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