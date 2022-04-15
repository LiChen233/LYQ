<template>
  <a-upload ref="__aUpload"
            v-if="isAlive"
            :action="action"
            :headers="headers"
            :data="data"
            :default-file-list="defaultFileList"
            @change="handleChange"
            :remove="handleRemove"
            :multiple="multiple"
            :accept="accept">
    <slot :loading="fileUploading" :btnText="btnText">
      <a-button :type="btnType"
                :loading="fileUploading"
                :disabled="filesIdList.length >= limit">
        <a-icon type="upload"/>
        <span>{{ btnText }}</span>
      </a-button>
    </slot>
    <slot name="tip" :tip="tipText">
      <div>{{ tipText }}</div>
    </slot>
  </a-upload>
</template>

<script>
import {ACCESS_TOKEN} from '@/store/mutation-types'
import storage from 'store'
import {filesApiUrl, filesApiDelFile, filesApiGetListByIds, getDownLoadFileUrl} from "@/api/system/files";
import {filesTypeEnum} from "@/enums/system/files";

const serverUrl = process.env.VUE_APP_API_BASE_URL

export default {
  name: "MyFileUpload",
  props: {
    defaultFileIds: [String, Array],
    multiple: {
      type: Boolean,
      default: false
    },
    path: String,
    btnType: {
      type: String,
      default: 'primary'
    },
    fileType: {
      type: String
    },
    isDel: {
      type: Boolean,
      default: true
    },
    limit: {
      type: Number,
      default: 5
    },
    xId: {
      type: String,
      default: ''
    }
  },
  data() {
    return {
      isAlive: true,
      defaultFileList: [],
      filesIdList: [],
      fileUploading: false,
      accept: "",
      tipText: ""
    }
  },
  computed: {
    action: function () {
      return serverUrl + filesApiUrl.uploadFile
    },
    headers: function () {
      const headers = {}
      headers[ACCESS_TOKEN] = storage.get(ACCESS_TOKEN)
      return headers
    },
    data: function () {
      const data = {}
      if (this.$myUtils.str.isNotEmpty(this.path)) {
        data.path = this.path
      }
      return data
    },
    btnText: function () {
      const t = "文件上传 " + this.filesIdList.length + "/" + this.limit
      return t
    }
  },
  watch: {
    defaultFileIds: {
      handler(filesIds) {
        if ((filesIds != null
            && (this.$myUtils.str.isNotEmpty(filesIds)
              || this.$myUtils.coll.isNotEmpty(filesIds)))
          && this.$myUtils.coll.isEmpty(this.filesIdList)) {
          this.getDefaultFileList(filesIds)
        }
      },
      immediate: true
    },
    filesIdList: function (data) {
      this.$emit("update:filesIds", this.getFilesIds());
      this.$emit("update:filesIdList", data);
      this.$emit("upload", this.xId, this.getFilesIds());
    }
  },
  created() {
    this.setAccept()
  },
  methods: {
    setAccept() {
      const fun = (arr) => {
        let s = ""
        for (let i = 0; i < arr.length; i++) {
          s += Array(arr[i]).join()
          if (i < arr.length - 1) {
            s += ","
          }
        }
        return s
      }
      let type = this.fileType
      let accept = ""
      if (type === 'word') {
        accept = fun([filesTypeEnum.word, filesTypeEnum.pdf])
      } else if (type === 'image') {
        accept = fun(filesTypeEnum.image)
      } else if (type === 'pdf') {
        accept = fun(filesTypeEnum.pdf)
      } else if (type === 'doc') {
        accept = fun(filesTypeEnum.word)
      }
      if (accept === "") {
        accept = fun([filesTypeEnum.image, filesTypeEnum.word, filesTypeEnum.pdf])
      } else {
        let tipText = accept.replace(/,\./g, "/")
        tipText = tipText.substring(1)
        tipText = "只能上传 " + tipText + " 文件"
        this.tipText = tipText
      }
      this.accept = accept
    },
    reload() {
      this.isAlive = false
      this.$nextTick(() => (this.isAlive = true))
    },
    getDefaultFileList(filesIds) {
      let ids = ""
      if (filesIds.constructor == String) {
        ids = filesIds
      } else {
        ids = filesIds.join()
      }
      filesApiGetListByIds(ids).then(res => {
        const data = res.data
        let list = []
        data.forEach(item => {
          const {id, filename, suffix} = item
          let d = {
            uid: id,
            name: filename + suffix,
            status: 'done',
            response: {msg: '成功', code: 200, data: id},
            url: this.getDownLoadFileUrl(id)
          }
          list.push(d)
        })
        this.defaultFileList = list
        this.setFilesIdList()
      }).finally(() => {
        this.reload()
      })
    },
    getDownLoadFileUrl(filesId) {
      return getDownLoadFileUrl(filesId)
    },
    handleChange({file, fileList}) {
      var {status, response} = file
      if (status != "uploading") {
        this.fileUploading = false
        if (status == "done") {
          const code = response.code
          if (code != 200) {
            // 文件上传失败
            this.$message.error("文件上传失败")
            fileList.forEach(item => {
              if (item.uid === file.uid) {
                item.status = "error"
              }
            })
          }
        } else if (status == "error") {
          this.$message.error("文件传输失败，请检查您的网络")
        }
        this.setFilesIdList()
      } else {
        this.fileUploading = true
      }
    },
    handleRemove(file) {
      const fileId = file.response.data
      if (this.$myUtils.str.isNotEmpty(fileId)) {
        return new Promise((resolve, reject) => {
          filesApiDelFile(fileId, this.isDel).then(res => {
            this.$message.success("文件删除成功")
            resolve(true)
          }).catch(res => {
            reject()
          })
        })
      } else {
        return true
      }
    },
    setFilesIdList() {
      const list = [...this.$refs["__aUpload"].sFileList, ...this.defaultFileList]
      const filesIdList = []
      list.forEach(item => {
        const status = item.status
        if (status === 'done') {
          const filesId = item.response.data
          if (this.$myUtils.str.isNotEmpty(filesId) && filesIdList.indexOf(filesId) == -1) {
            filesIdList.push(filesId)
          }
        }
      })
      this.filesIdList = filesIdList
    },
    getFilesIds(s = ",") {
      return this.filesIdList.join(s)
    },
    getFilesIdList() {
      return this.filesIdList
    }
  }
}
</script>

<style scoped>

</style>