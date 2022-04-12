<template>
  <div>
    <slot>
      <a-select v-model="selectdUnitId" mode="multiple" style="width: 100%" :placeholder="placeholder" @dropdownVisibleChange="open">
        <div slot="dropdownRender">
        </div>
        <a-select-option v-for="(item, index) in selectdUnit" :value="item.id" :key="item.id">
          {{item.unitName}}
        </a-select-option>
      </a-select>
    </slot>

    <a-modal v-model="visible" title="选择单位" width="1000px" @cancel="visible = false" @ok="ok">
      <div class="modal-box">
        <a-row class="modal-box-row">
          <a-col :span="7" class="selectd-unit">
            <div>
              <span>已选单位</span>
              <span v-if="!multiple">{{selectdUnitId.length}}/{{limit}}</span>
              <span v-else>{{selectdUnitId.length}}</span>
            </div>
            <div>
              <template v-for="(unit, index) in selectdUnit">
                <a-tooltip placement="top" :key="unit.id" :title="getUnitText(unit)">
                  <div class="ant-tag">
                    <span class="unit-name">{{getUnitText(unit)}}</span>
                    <a-icon class="icon" type="close" @click="removeSelectdUnit(index)"></a-icon>
                  </div>
                </a-tooltip>
              </template>
            </div>
          </a-col>
          <a-col :span="1">
            <a-divider type="vertical"></a-divider>
          </a-col>
          <a-col :span="16">
            <a-row :gutter="10">
              <a-col :span="12">
                <a-tree-select style="width: 100%"
                               :dropdown-style="{ maxHeight: '400px', overflow: 'auto' }"
                               allowClear
                               show-search
                               :tree-data="unitTree"
                               :replace-fields="{key: 'id', value: 'id', title: 'unitName'}"
                               @select="searchUnitSelect"
                               @change="searchUnitChange"
                               placeholder="根据区域进行查询">
                </a-tree-select>
              </a-col>
              <a-col :span="12">
                <a-input v-model="search.unitName" allowClear placeholder="根据单位名进行查询" @change="searchUnitnameChange"></a-input>
              </a-col>
            </a-row>
            <a-row class="select-unit">
              <div class="select-unit-box">
                <template v-for="(unit, index) in showUnitList">
                  <a-checkbox :value="unit.id"
                              :key="unit.id"
                              :checked="selectdUnitId.indexOf(unit.id) > -1"
                              :disabled="isDisabledUnit(unit)"
                              @change="() => handleCheckedChange(unit.id)">
                    {{getUnitText(unit)}}
                  </a-checkbox>
                </template>
              </div>
            </a-row>
          </a-col>
        </a-row>
      </div>
      <div slot="footer">
        <a-button key="submit" type="primary" @click="ok">
          确认
        </a-button>
      </div>
    </a-modal>
  </div>
</template>

<script>
import Vue from "vue"
import { getSelectUnitData } from "@/api/system/filter_unit";
import { typeEnum } from "@/enums/system/unit";
import {TreeSelect} from "ant-design-vue";
Vue.use(TreeSelect)

export default {
  name: "MySelectUnit",
  props: {
    value: [String, Number, Array],
    placeholder: String,
    limit: {
      type: Number,
      default: 1
    },
    multiple: {
      type: Boolean,
      default: true
    },
    disabledList: Array
  },
  model: {
    prop: 'value',
    event: 'updateSelectdUnitId'
  },
  data () {
    return {
      visible: false,
      unitTree: [],
      unitList: [],
      showUnitList: [],
      selectdUnitId: [],
      search: {
        unitId: null,
        unitLevel: 0,
        unitName: null
      },
      dataValue: null
    }
  },
  watch: {
    value: function (data) {
      this.handleValue()
    },
    selectdUnitId: function (data) {
      let value = null
      if (this.$myUtils.str.isNotEmpty(data)
        || this.$myUtils.coll.isNotEmpty(data)) {
        // 判断类型
        if (typeof this.value === 'string'){
          value = data.join(',')
        } else {
          value = data
        }
      }
      this.dataValue = value
      this.$emit("updateSelectdUnitId", value)
    }
  },
  computed: {
    selectdUnit: function () {
      return this.unitList.filter(unit => this.selectdUnitId == unit.id || this.selectdUnitId.indexOf(unit.id) > -1)
    }
  },
  created() {
    this.getData()
    this.handleValue()
  },
  mounted() {

  },
  methods: {
    handleValue () {
      const data = this.value
      if (this.$myUtils.str.isNotEmpty(data)
        || this.$myUtils.coll.isNotEmpty(data)) {
        // 判断类型
        if (typeof data === 'string') {
          this.selectdUnitId = data.split(",").map(o => Number(o))
        } else if (typeof data === 'number') {
          this.selectdUnitId = [data]
        } else {
          this.selectdUnitId = data
        }
      }
    },
    getData () {
      getSelectUnitData().then(res => {
        const { sysUnitTree, sysUnitList } = res.data
        this.unitTree = sysUnitTree
        this.unitList = sysUnitList
        this.showUnitList = [...sysUnitList]
      })
    },
    getUnitText (unit) {
      const { unitName, parentList } = unit
      return  unitName + " " + parentList.filter(unit => unit.type === 'A').map(unit => unit.unitName).join("/")
    },
    handleCheckedChange(unitId) {
      const index = this.selectdUnitId.indexOf(unitId)
      if (index > -1) {
        // 存在，移除
        this.selectdUnitId.splice(index, 1)
      }else{
        // 不存在，添加
        // 判断limit个数,是否多选
        if (this.multiple) {
          this.selectdUnitId.push(unitId)
        } else {
          if (this.limit === 1){
            this.selectdUnitId.splice(index, 1, unitId)
          }else if (this.limit === this.selectdUnitId.length){
            this.$message.info('选择个数已达上限')
          }else{
            this.selectdUnitId.push(unitId)
          }
        }
      }
    },
    open () {
      this.visible = true
    },
    searchUnitSelect (unitId, unit) {
      this.search.unitId = unitId
      // 判断当前是第几级单位
      const pos = unit.pos
      const level = pos.split('-').length - 2
      this.search.unitLevel = level
      this.searchUnit()
    },
    searchUnitChange (value) {
      // 为空时 searchUnitSelect不会触发所以需要该方法
      if (!value) {
        this.search.unitId = null
        this.searchUnit()
      }
    },
    searchUnitnameChange (event) {
      this.searchUnit()
    },
    searchUnit () {
      console.log(this.search)
      const { unitName, unitLevel, unitId } = this.search
      this.showUnitList = this.unitList.filter(unit => {
        let nd = true
        let ud = true
        if (this.$myUtils.str.isNotEmpty(unitName)) {
          nd = unit.unitName.indexOf(unitName) > -1
        }
        if (unitId != null) {
          if (unit.parentList.length > unitLevel){
            ud = unit.parentList[unitLevel].id == unitId
          }else{
            ud = false
          }
        }
        return nd && ud
      })
    },
    removeSelectdUnit (index) {
      const unit = this.selectdUnit[index]
      const unitId = unit.id
      let idIndex = null
      for (let i = 0; i < this.selectdUnitId.length; i++) {
        if (this.selectdUnitId[i] === unitId) {
          idIndex = i
        }
      }
      if (idIndex != null){
        this.selectdUnitId.splice(idIndex, 1)
      }
    },
    isDisabledUnit (unit) {
      if (this.$myUtils.coll.isNotEmpty(this.disabledList)) {
        return this.disabledList.map(Number).indexOf(unit.id) > -1
      }
      return false
    },
    ok () {
      this.visible = false
      this.$emit("ok", this.dataValue)
    },
    clear () {
      this.selectdUnitId = []
    }
  },
}
</script>

<style scoped>

  .modal-box{
    height: 400px;
  }

  .modal-box .ant-checkbox-wrapper{
    margin-left: unset !important;
  }

  .modal-box .ant-divider{
    height: 400px !important;
  }

  .modal-box-row {
    height: 400px;
  }

  .modal-box-row > .ant-col{
    height: 400px;
  }

  .selectd-unit {
    height: 400px;
    overflow: hidden;
    overflow-y: scroll;
  }

  .select-unit {
    margin-top: 10px;
    height: calc(100% - 43px);
    overflow: hidden;
    overflow-y: scroll;
  }

  .select-unit-box {
    display: flex;
    flex-direction: column;
  }

  .ant-tag{
    margin-top: 5px;
    padding-right: 20px;
    position: relative;
    line-height: 1.5;
  }

  .ant-tag::v-deep .unit-name {
    max-width: 230px;
    overflow:hidden;
    text-overflow:ellipsis;
    white-space:nowrap;
    display: block;
  }

  .ant-tag::v-deep .icon {
    position: absolute;
    right: 4px;
    top: 3px;
  }

</style>