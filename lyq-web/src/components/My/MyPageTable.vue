<template>
  <div>
    <a-spin :spinning="loading">
      <slot name="empty">
        <template v-if="customEmpty && (records == null || records.length === 0)">
          <a-empty/>
        </template>
      </slot>
      <slot :list="records"></slot>
    </a-spin>
    <slot name="page">
      <a-pagination style="margin-top: 10px"
                    v-model="current"
                    :total="total"
                    :default-page-size="pageSize"
                    :page-size-options="pageSizeOptions"
                    :show-total="total => `一共 ${total} 条数据`"
                    show-size-changer
                    @change="onchange"
                    @showSizeChange="onSizeChange">
      </a-pagination>
    </slot>
  </div>
</template>

<script>
import Vue from "vue";
import reqApi from "@/utils/reqApi";
import {Pagination, Spin} from "ant-design-vue";

Vue.use(Pagination)
Vue.use(Spin)

export default {
  name: "MyPageTable",
  components: {
    Spin
  },
  props: {
    customEmpty: {
      type: Boolean,
      default() {
        return false;
      }
    },
    reqUrl: {
      type: String,
      required: true
    },
    where: {
      type: Object,
      default() {
        return {}
      }
    },
    pageSize: {
      type: Number,
      default: 10
    }
  },
  data() {
    return {
      page: {
        page: 1,
        size: this.pageSize
      },
      total: 0,
      current: 1,
      records: [],
      upWhere: null,
    }
  },
  computed: {
    pageSizeOptions: function () {
      const n = this.pageSize
      return [n + '', n * 2 + '', n * 3 + '']
    },
    loading: function () {
      return this.$store.getters.getLoad(this.reqUrl)
    }
  },
  created() {

  },
  methods: {
    getData() {
      const params = {...this.page, ...this.where, ...this.upWhere}
      reqApi({
        url: this.reqUrl,
        method: 'post',
        data: params
      }).then(res => {
        const data = res.data
        this.total = data.total
        this.current = data.current
        this.records = data.records
      })
    },
    reload(where) {
      if (where != null) {
        this.upWhere = {...where};
      }
      this.page.page = 1;
      this.getData()
    },
    onchange(page, pageSize) {
      this.page.page = page
      this.page.size = pageSize
      this.getData()
    },
    onSizeChange(page, pageSize) {
      this.page.page = page
      this.page.size = pageSize
      this.getData()
    }
  }
}
</script>

<style scoped>

</style>