<template>
  <div class="list">
    <template v-if="filesList != null && filesList.length > 0">
      <slot :filesList="filesList">
        <template v-for="(item, index) in filesList">
          <div class="a">
            <a-icon :type="getIconType(item)" />
            <a-tooltip placement="top" title="预览">
              <span class="filename" @click="previewFile(item)">{{item.filename}}{{item.suffix}}</span>
            </a-tooltip>
            <a-tooltip placement="top" title="下载">
              <a-icon type="cloud-download" @click="downloadFile(item)" />
            </a-tooltip>
          </div>
        </template>
        <my-a ref="myA"></my-a>
      </slot>
    </template>
    <template v-else>
      <slot name="empty">
        {{emptyText}}
      </slot>
    </template>
  </div>
</template>

<script>
import {filesApiGetListByIds, getDownLoadFileUrl, getPreviewFileUrl} from "@/api/system/files";
import { getTypeBySuffix } from "@/enums/system/files";
import MyA from "@/components/My/MyA";

export default {
  name: "MyFileList",
  components: {
    MyA
  },
  props: {
    filesIds: [String, Array],
    emptyText: {
      type: String,
      default: "暂无文件"
    }
  },
  data () {
    return {
      filesList: []
    }
  },
  watch: {
    filesIds: {
      handler: function (filesIds) {
        if (filesIds != null
              && (this.$myUtils.str.isNotEmpty(filesIds)
              || this.$myUtils.coll.isNotEmpty(filesIds))){
          this.getFilesList(filesIds)
        }else{
          this.filesList = []
        }
      },
      immediate: true
    }
  },
  methods: {
    getFilesList (filesIds) {
      let ids = ""
      if (filesIds.constructor == String){
        ids = filesIds
      }else{
        ids = filesIds.join()
      }
      filesApiGetListByIds(ids).then(res => {
        this.filesList = res.data
      })
    },
    getIconType (files) {
      const type = getTypeBySuffix(files.suffix)
      if (type){
        return "file-" + type
      } else {
        return "file"
      }
    },
    downloadFile (files) {
      const url = getDownLoadFileUrl(files.id)
      this.$refs.myA.target(url)
    },
    previewFile (files) {
      const url = getPreviewFileUrl(files.id)
      this.$refs.myA.target(url)
    }
  }
}
</script>

<style scoped>

.list > .a:not(last-child){
  margin-bottom: 5px;
}

.a{
  width: 100%;
  display: inline-flex;
  flex-direction: row;
  align-items: center;
  justify-content: left;
  vertical-align: middle;
  position: relative;
  text-decoration: none;
  outline: none;
  cursor: pointer;
  padding: 0;
  font-size: 14px;
  font-weight: 500;

  color: #409eff;
}

.a i{
  font-size: 16px;
}

/*.a:hover:after {*/
/*  content: "";*/
/*  position: absolute;*/
/*  left: 0;*/
/*  right: 0;*/
/*  height: 0;*/
/*  bottom: 0;*/
/*  border-bottom: 1px solid #409eff;*/
/*}*/

.a:hover{
  color: #66b1ff;
}

.filename {
  margin: 0 10px 0 2px;
  min-width: 1%;
  max-width: calc(100% - 27px);
  display: inline-block;
  overflow: hidden;
  white-space: nowrap;
  text-overflow: ellipsis;
}

.filename:hover {
  text-decoration: underline;
}

</style>