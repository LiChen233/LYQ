<template>
  <div class="table-page-search-wrapper">
    <a-form layout="inline">
      <a-row :gutter="48">
        <template v-for="(item,index) in page.model">
          <a-col v-if="index < 2 || advanced"
                 :key="item.name"
                 :md="8" :sm="24">
            <a-form-item :label="item.title">
              <template v-if="item.type==='text'">
                <a-input v-model="data[item.name]"/>
              </template>

              <template v-if="item.type==='select'">
                <a-select allowClear
                          :maxTagCount="1"
                          :maxTagTextLength="10"
                          v-model="data['_' + item.name]"
                          :mode="item.multiple?'multiple':'default'"
                          @change="checkSelect(item.name,$event)">
                  <template v-for="op in item.data">
                    <a-select-option :value="op[item.id != null ? item.id : 'id']">
                      {{ op[item.value != null ? item.value : 'value'] }}
                    </a-select-option>
                  </template>
                </a-select>
              </template>

              <template v-if="item.type==='date'">
                <a-range-picker format="yyyy-MM-DD"
                                v-model="data[item.name]"
                                @change="checkTime(item.start,item.end,item.name)">
                </a-range-picker>
              </template>

              <template v-if="item.type==='date2'">
                <a-date-picker
                  v-model="data[item.name]"
                  @change="checkTime2"
                  type="date"
                  style="width: 100%;"
                />
              </template>

            </a-form-item>
          </a-col>
        </template>
        <a-col :md="!advanced && 8 || 24" :sm="24">
          <span class="table-page-search-submitButtons"
                :style="advanced && { float: 'right', overflow: 'hidden' } || {} ">
            <a-button type="primary" @click="search">查询</a-button>
            <a-button style="margin-left: 8px" @click="data = {}">重置</a-button>
            <a @click="toggleAdvanced" style="margin-left: 8px" v-if="page.model.length>2">
              {{ advanced ? '收起' : '展开' }}
              <a-icon :type="advanced ? 'up' : 'down'"/>
            </a>
          </span>
        </a-col>
      </a-row>
    </a-form>
  </div>
</template>

<script>
export default {
  name: "MySearch",
  components: {},
  props: {
    //数据源，用这个判断渲染什么类型的搜索框，以及name，标题等
    page: {
      type: Object,
      default: () => {
        return {
          model: [
            {type: 'text', name: 'search', title: '输入框'},
            {
              type: 'select',
              name: 'type',
              title: '下拉选择',
              //下拉选择需要传入数据
              data: [{id: 123, value: '下拉框'}],
              id: 'id',
              value: 'value',
              multiple: false
            },
            {type: 'date', start: 'startTime', end: 'endTime', title: '时间范围'},
          ]
        }
      }
    }
  },
  data() {
    return {
      data: {
        time: undefined
      },
      advanced: false,
    }
  },
  methods: {
    toggleAdvanced() {
      this.advanced = !this.advanced
    },
    search() {
      this.$emit('search', this.data);
    },
    checkTime(start, end, date) {
      if (start == null) {
        start = 'startTime'
      }
      if (end == null) {
        end = 'endTime'
      }
      this.data[start] = this.data[date][0]
      this.data[end] = this.data[date][1]
    },
    checkTime2(date, dateString) {
      this.data.time = dateString
    },
    checkSelect(name, v) {
      if (null != v) {
        v = v.toString()
      }
      this.data[name] = v
    }
  }
}
</script>

<style lang="less" scoped>

</style>