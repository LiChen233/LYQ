<template>
  <a-drawer
    :body-style="{ paddingBottom: '80px' }"
    :title="title"
    :width="width"
    :visible="visible"
    :mask-closable="maskClosable"
    @close="onClose"
    @submit="onSubmit"
  >
    <slot>

    </slot>
    <div
      style="
          position: absolute;
          right: 0;
          bottom: 0;
          width: 100%;
          borderTop: 1px solid #e9e9e9;
          padding: 10px 16px;
          background: #fff;
          textAlign: right;
          zIndex: 1
        "
    >
      <a-button :style="{ marginRight: '8px' }" @click="onClose">
        取消
      </a-button>
      <a-button type="primary" @click="onSubmit" :loading="submitLoad">
        提交
      </a-button>
    </div>
  </a-drawer>
</template>

<script>
export default {
  name: 'MyDrawerForm',
  props: {
    title: {
      type: String,
      default: '新增'
    },
    width: {
      type: Number,
      default: 400
    },
    visible: {
      type: Boolean,
      default: false
    },
    maskClosable: {
      type: Boolean,
      default: true
    },
    loadKey: {
      type: String
    }
  },
  data () {
    return {
    }
  },
  computed: {
    submitLoad: function () {
      if (this.loadKey){
        return this.$store.getters.getLoad(this.loadKey)
      }else{
        return false
      }
    }
  },
  methods: {
    onClose () {
      this.$emit('update:visible', false)
      this.$emit('close')
    },
    onSubmit () {
      this.$emit('submit')
    }
  }
}
</script>

<style scoped>

</style>
