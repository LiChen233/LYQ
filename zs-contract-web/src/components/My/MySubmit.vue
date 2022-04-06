<template>
  <div>
    <slot>
    </slot>
    <footer-tool-bar style="width: 100%" v-if="isSubmit">
      <span class="popover-wrapper">
        <a-popover title="表单校验信息" overlayClassName="antd-pro-pages-forms-style-errorPopover" trigger="click"
                   :getPopupContainer="trigger => trigger.parentNode">
          <template slot="content">
            <li v-for="item in errors" :key="item.key" @click="scrollToField(item.fieldLabel)"
                class="antd-pro-pages-forms-style-errorListItem">
              <a-icon type="cross-circle-o" class="antd-pro-pages-forms-style-errorIcon"/>
              <div class="">{{ item.message }}</div>
              <div class="antd-pro-pages-forms-style-errorField">{{ item.fieldLabel }}</div>
            </li>
          </template>
          <span class="antd-pro-pages-forms-style-errorIcon" v-if="errors.length > 0">
            <a-icon type="exclamation-circle"/>{{ errors.length }}
          </span>
        </a-popover>
      </span>
      <a-button type="primary" @click="onReset" style="margin-right: 10px;" v-if="reset">重置</a-button>
      <a-button type="primary" :loading="loading" @click="handleSubmit">{{ btnText }}</a-button>
    </footer-tool-bar>
  </div>
</template>

<script>
import FooterToolBar from '@/components/FooterToolbar'
import MySpin from "@/components/My/MySpin";

export default {
  name: "MySubmit",
  components: {
    MySpin,
    FooterToolBar
  },
  props: {
    loadKey: String,
    reset: Boolean,
    isSubmit: Boolean,
    btnText: {
      type: String,
      default: '提交'
    },
    formIndex: {
      type: Array,
      default: () => {
        return [0]
      }
    },
    notValidate: {
      type: Boolean,
      default: false
    }
  },
  data() {
    return {
      errors: []
    }
  },
  computed: {
    loading: function () {
      const loadkey = this.loadKey
      if (this.$myUtils.str.isNotEmpty(loadkey)) {
        return this.$store.getters.getLoad(loadkey)
      } else {
        return false
      }
    }
  },
  methods: {
    // 提交处理
    handleSubmit() {
      if (this.notValidate) {
        this.$emit("submit")
      } else {
        this.getFormRef().validate((valid, objects) => {
          if (valid) {
            this.errors = []
            this.$emit("submit")
          } else {
            this.errorList(objects)
          }
        })
      }
    },
    // 生成错误列表
    errorList(error) {
      const list = Object.values(error).flat()
      list.forEach(item => {
        const {field} = item
        item.fieldLabel = this.getLabelByField(field)
      })
      this.errors = list
    },
    getLabelByField(field) {
      const list = this.getFormRef().$children
      const objArr = list.filter(item => item.prop == field)
      if (objArr.length > 0) {
        return objArr[0].label
      }
      return null
    },
    // 点击错误跳转到当前行数
    scrollToField(fieldKey) {
      if (this.$myUtils.str.isNotEmpty(fieldKey)) {
        const labelNode = document.querySelector(`label.ant-form-item-required[title="${fieldKey}"]`)
        if (labelNode) {
          labelNode.scrollIntoView(true)
        }
      }
    },
    onReset() {
      this.getFormRef().resetFields();
    },
    getFormRef() {
      console.log(this.$children)
      const indexList = this.formIndex
      let form = this.$children[indexList[0]]
      for (let i = 1; i < indexList.length; i++) {
        form = form.$children[indexList[i]]
      }
      return form
    }
  }
}
</script>

<style lang="less" scoped>
.popover-wrapper {
  /deep/ .antd-pro-pages-forms-style-errorPopover .ant-popover-inner-content {
    min-width: 256px;
    max-height: 290px;
    padding: 0;
    overflow: auto;
  }
}

.antd-pro-pages-forms-style-errorIcon {
  user-select: none;
  margin-right: 24px;
  color: #f5222d;
  cursor: pointer;

  i {
    margin-right: 4px;
  }
}

.antd-pro-pages-forms-style-errorListItem {
  padding: 8px 16px;
  list-style: none;
  border-bottom: 1px solid #e8e8e8;
  cursor: pointer;
  transition: all .3s;

  &:hover {
    background: #e6f7ff;
  }

  .antd-pro-pages-forms-style-errorIcon {
    float: left;
    margin-top: 4px;
    margin-right: 12px;
    padding-bottom: 22px;
    color: #f5222d;
  }

  .antd-pro-pages-forms-style-errorField {
    margin-top: 2px;
    color: rgba(0, 0, 0, .45);
    font-size: 12px;
  }
}
</style>