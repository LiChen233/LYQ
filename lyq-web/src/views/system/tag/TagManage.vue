<template>
  <page-header-wrapper>
    <a-card :bordered="false">
      <div class="table-operator">
        <a-button type="primary" icon="plus" @click="handleAdd">添加标签</a-button>
      </div>
      <tag-add-edit ref="addEdit" @success="search"></tag-add-edit>
      <my-page-table ref="myPageTable" :req-url="reqUrl" :where="where">
        <template slot-scope="{ list }">
          <a-table :columns="columns"
                   :data-source="list"
                   :pagination="false"
                   :rowKey="record=>record.id">
            <template slot="tagType" slot-scope="text, record">
              {{tagGetByType(text).label}}
            </template>
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
import TagAddEdit from '@/views/system/tag/TagAddEdit';
import MyPageTable from "@/components/My/MyPageTable";
import {tagApiDel, tagApiUrl} from "@/api/system/tag";
import {getByType as tagGetByType} from "@/enums/system/tag";

export default {
  name: "TagManage",
  components: {
    TagAddEdit,
    MyPageTable
  },
  data(){
    return {
      columns: [
        {
          title: "类型",
          dataIndex: "tagType",
          scopedSlots: { customRender: "tagType" }
        },
        {
          title: "名称",
          dataIndex: "tagName"
        },
        {
          title: "操作",
          width: 150,
          dataIndex: "action",
          scopedSlots: { customRender: "action" }
        },
      ],
      reqUrl: tagApiUrl.queryPage,
      where: {
      },
    }
  },
  mounted() {
    this.search()
  },
  methods:{
    tagGetByType,
    handleAdd () {
      this.$refs.addEdit.add()
    },
    handleEdit(record) {
      this.$refs.addEdit.edit(record)
    },
    handleDelete(record) {
      let model = this.$confirm({
        title: "提示",
        content: "确定要删除 " + record.tagName + " 吗?",
        onOk: () => {
          tagApiDel(record.id).then(res => {
            this.$message.success("删除成功");
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
  }
}
</script>

<style scoped>

</style>