<template>
    <div >
      <a-space class="space">
        <div v-for="(item, index) in data" :key="index">
          <a-button size="large"
                    :class="'btn ' + item.colorClass"
                    :disabled="item.disabled"
                    @click="handleClick(item)">
            {{ getBtnName(item) }}
          </a-button>
        </div>
      </a-space>
    </div>
</template>

<script>
import {getByType} from "@/enums/matter/MatterType";
import {mainApiGetMatterButtonInfo} from "@/api/matter/main";

export default {
  name: "MyButton",
  props: {
    id: {
      type: String,
      required: true,
    }
  },
  data() {
    return{
      data: [],
    }
  },
  created() {
    this.getData()
  },
  methods: {
    getData () {
      mainApiGetMatterButtonInfo(this.id).then(res => {
        this.data = res.data
        this.data.forEach(item => {
          item.matterTypeEnum = getByType(item.matterType)
        })
      })
    },
    getBtnName (item){
      const { btnName, matterTypeEnum, remark } = item
      let text = btnName;
      if (this.$myUtils.str.isEmpty(btnName)) {
        text = matterTypeEnum.title
      }
      if (this.$myUtils.str.isNotEmpty(remark)) {
        text += "(" + remark + ")";
      }
      return text
    },
    handleClick (item) {
      const { matterTypeEnum, query } = item
      this.$router.push({
        path: matterTypeEnum.path,
        query: { contractId: this.id, ...query }
      })
    }
  }
}
</script>

<style scoped>
.space{
  display: flex;
  flex-wrap: wrap;
  justify-content: flex-start;
}
.ant-btn {
  border-color: unset !important;
}
.btn {
  border-radius: 4px;
  color: #ffffff;
  margin-bottom: 5px;
}

.pending {
  background: #1890ff;
}

.pending:hover {
  background: #40a9ff;
  border-color: #40a9ff;
}

.complete {
  background: #73d13d;
}

.complete:hover {
  background: #95de64;
  border-color: #95de64;
}

.onway {
  background: #fa8c16;
}

.onway:hover {
  background: #ffc53d;
  border-color: #ffc53d;
}
.disabled {
  background: #8c8c8c;
  color: #d9d9d9;
}

.disabled:hover {
  color: #d9d9d9;
  background: #8c8c8c;
  border-color: #8c8c8c;
}
</style>