<template>
  <div>
    <div :class="'form-item-normal ' + type" :style="bottom?'':'margin-bottom: 0px;'">
      <div :class="bold?'title bold':'title'" v-if="title">
        {{ title }}：
      </div>
      <div v-if="type == 'tag'">
        <a-tag color="blue">
          <slot v-if="type == 'tag'">

          </slot>
        </a-tag>
      </div>
      <div v-else-if="type === 'file'" style="padding-top: 10px">
        <my-file-list :files-ids="fileIds"></my-file-list>
      </div>
      <slot v-else="type!=='file'">
        暂无数据
      </slot>
    </div>
  </div>
</template>

<script>
import MyFileList from "@/components/My/file/MyFileList";

export default {
  name: "MyFormEcho",
  components: {MyFileList},
  props: {
    title: {
      type: String,
      required: true
    },
    bold: {
      type: Boolean,
      default: true
    },
    type: {
      type: String,
      default: 'text'
    },
    bottom: {
      type: Boolean,
      default: true
    },
    fileIds: [String, Array]
  },
  data() {
    return {}
  }
}
</script>

<style scoped>
.bold {
  font-weight: bold;
}

.form-item-normal {
  display: flex;
  align-items: center;
  margin-bottom: 15px;
}

.title {
  flex-shrink: 0;
  color: rgba(0, 0, 0, 0.85);
}

/**
 * 使用
 */
.remark, .image, .file {
  display: block;
}

.image img {
  height: 300px;
}
</style>