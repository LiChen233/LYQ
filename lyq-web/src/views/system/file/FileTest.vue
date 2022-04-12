<template>
  <page-header-wrapper>
    <my-file-upload :default-file-ids="filesIds" :files-ids.sync="filesIds">
    </my-file-upload>

    <div style="margin-top: 100px; width: 500px">
      <my-file-list :files-ids="filesIds"></my-file-list>
    </div>
  </page-header-wrapper>
</template>

<script>
import MyFileUpload from "@/components/My/file/MyFileUpload";
import MyFileList from "@/components/My/file/MyFileList";

export default {
  name: "FileTest",
  components: {
    MyFileUpload,
    MyFileList
  },
  data () {
    return {
      defaultFileIds: ['7bd0571f2b6a42a987d29b3909ff35cf'],
      filesIds: '7bd0571f2b6a42a987d29b3909ff35cf',
    }
  },
  watch: {
    filesIds: function (data) {
      console.log(data)
    },
    filesIdList: function (data) {
      console.log(data)
    }
  }
}
</script>

<style scoped>

</style>