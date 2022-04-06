<template>
  <a-tree-select v-model="selectd"
                 :allow-clear="allowClear"
                 tree-data-simple-mode
                 style="width: 100%"
                 :dropdown-style="{ maxHeight: '500px', overflow: 'auto' }"
                 :tree-data="unitList"
                 :load-data="onLoadData"
                 :placeholder="placeholder">
  </a-tree-select>
</template>

<script>
import { getChildren, getTopLineList, apiUrl } from "@/api/system/filter_unit";

import Vue from "vue";
import {TreeSelect} from "ant-design-vue";
Vue.use(TreeSelect)

export default {
  name: "MyUnitTreeSelect",
  components: {
    TreeSelect
  },
  props: {
    value: [Number, String],
    placeholder: {
      type: String,
      default: "请选择单位"
    },
    allowClear: {
      type: Boolean,
      default: true
    }
  },
  model: {
    prop: 'value',
    event: 'updateSelectdUnitId'
  },
  data () {
    return {
      selectd: null,
      unitList: []
    }
  },
  watch: {
    selectd: function (newVal, oldVal) {
      if (this.value != newVal) {
        let val = null
        if (this.$myUtils.str.isNotEmpty(newVal)) {
          val = Number(newVal)
        }
        this.$emit("updateSelectdUnitId", val)
      }
    },
    value: function (newVal) {
      if (newVal) {
        this.selectd = Number(this.value)
        this.getTopLineList()
      }
    }
  },
  created() {
    if (!this.value) {
      this.getUnitList()
    }
  },
  methods: {
    getUnitList() {
      getChildren(null,true).then(res => {
        this.unitList = this.setListData(res.data)
      })
    },
    getTopLineList () {
      getTopLineList(this.selectd).then(res => {
        this.unitList = this.setListData(res.data)
      })
    },
    setListData (list) {
      const arr = []
      list.forEach(item => {
        arr.push({
          id: item.id,
          pId: item.pid,
          title: item.unitName,
          value: String(item.id),
          isLeaf: item.isLast
        })
      })
      return arr
    },
    onLoadData (treeNode) {
      return new Promise(resolve => {
        const id = Number(treeNode._props.value)
        let obj = this.unitList.find(obj => obj.pId === id)
        if (obj == null) {
          const id = treeNode._props.value
          getChildren(id).then(res => {
            this.unitList = this.unitList.concat(this.setListData(res.data))
          })
        }
        resolve()
      })
    }
  }
}
</script>

<style scoped>

</style>