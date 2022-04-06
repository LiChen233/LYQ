<template>
  <my-submit class="page"
             :is-submit="isSubmit"
             :form-index="formIndex"
             :load-key="loadKey"
             :btn-text="btnText"
             :not-validate="notValidate"
             @submit="submit">
    <a-page-header
      class="header"
      :title="title"
      :sub-title="subTitle"
      @back="onBack"
    />
    <div class="body">
      <slot></slot>
    </div>
  </my-submit>
</template>

<script>
import MySubmit from "@/components/My/MySubmit";

export default {
  name: "MyPageForm",
  components: {
    MySubmit
  },
  props: {
    loadKey: String,
    formIndex: {
      type: Array,
      default: () => {
        return [1, 0]
      }
    },
    title: {
      type: String
    },
    subTitle: {
      type: String
    },
    back: Function,
    reset: {
      type: Boolean,
      default: false
    },
    isSubmit: {
      type: Boolean,
      default: true
    },
    btnText: {
      type: String
    },
    notValidate: {
      type: Boolean,
      default: false
    }
  },
  methods: {
    submit() {
      this.$emit("submit")
    },
    onBack() {
      if (this.back) {
        this.back()
      } else {
        this.$router.back()
      }
    }
  }
}
</script>

<style scoped>

.page {
  margin: -24px -24px 0;
}

.header {
  border: 1px solid rgb(235, 237, 240);
  background-color: white;
  position: fixed;
  width: 100%;
  z-index: 999;
}

.body {
  padding: 80px 24px 0px;
}

</style>