<template>
  <div v-if="url!=null">
    <a-spin v-show="show" size="large" />
    <pdf v-for="i in numPages"
         :key="i"
         :src="url"
         :page="i"
         @page-loaded="loaded"
    >
    </pdf>
  </div>
</template>

<script>
import pdf from 'vue-pdf'
import {filesApiGetListByIds, getDownLoadFileUrl, getPreviewFileUrl} from "@/api/system/files";
import MySpin from "@/components/My/MySpin";

export default {
  name: "MyShowPdf",
  props: {
    id: {
      type: String
    }
  },
  components: {
    MySpin,
    pdf
  },
  data() {
    return {
      numPages: 1,
      url:null,
      show: true
    }
  },
  watch: {
    id: {
      handler: function (id) {
        if (id != null
          && (this.$myUtils.str.isNotEmpty(id)
            || this.$myUtils.coll.isNotEmpty(id))){
          this.getFilesList(id)
        }else{
          this.filesList = []
        }
      },
      immediate: true
    }
  },
  methods: {
    getNumPages() {
      let loadingTask = pdf.createLoadingTask(this.url)
      loadingTask.promise.then(pdf => {
        this.numPages = pdf.numPages
      })
    },
    getFilesList (id) {
      filesApiGetListByIds(id).then(res => {
        this.url = getPreviewFileUrl(res.data[0].id)
        this.getNumPages()
      })
    },
    loaded() {
      this.show = false
    }
  }
}
</script>

<style scoped>
.ant-spin-spinning {
  position: fixed;
  display: inline-block;
  opacity: 1;
  left: 36%;
  top: 40%;
}
</style>