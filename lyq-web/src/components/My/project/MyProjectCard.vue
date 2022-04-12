<template>
    <a-card @dblclick="openDetail(data.id)" style="margin: 5px 0 10px 0;cursor: pointer" :bordered="false">
      <div style="display: flex; font-weight: bold; color: #000000">
        <div style="font-size: 18px;">{{ data.title }}</div>
        <div style="padding: 4px;">[{{ data.projectId }}]</div>
      </div>
      <slot name="progress"></slot>

      <div>
        <template>
          <a-descriptions :column="isMobile ? 1 : 3" size="small">
            <a-descriptions-item label="项目类型">{{ data.type }}</a-descriptions-item>
            <a-descriptions-item label="建设单位">
              <div  style="display: flex; flex-direction: row">
                <div style="display: flex; flex-direction: row" v-for="(item, index) in data.unit.split(',')">
                  <my-unit-info v-if="item" :unit-id="item"></my-unit-info>
                  <div v-if="index < data.unit.split(',').length - 1">，</div>
                </div>
              </div>
            </a-descriptions-item>
            <a-descriptions-item label="招标方式">{{ data.bidMode }}</a-descriptions-item>
            <a-descriptions-item label="项目金额（万元）">{{ data.generalEstimate }}</a-descriptions-item>
            <a-descriptions-item label="创建时间">{{ data.createTime }}</a-descriptions-item>
          </a-descriptions>
        </template>
      </div>
      <slot>
      </slot>
    </a-card>
</template>

<script>
import {baseMixin} from '@/store/app-mixin';
import {setObjDefaultKey} from "@/utils/util";

import MyUnitInfo from "@/components/My/info/MyUnitInfo";
import MyUserInfo from "@/components/My/info/MyUserInfo";


export default {
  name: "MyProjectCard",
  components: {
    MyUnitInfo,
    MyUserInfo,
  },
  props: {
    item:{
      type: Object,
    }
  },
  mixins: [baseMixin],
  created() {
    this.data = setObjDefaultKey(this.item);
  },
  data() {
    return {
      data: {},
    }
  },
  methods: {
    openDetail(id) {
      this.$router.push({ path: '/project/project-detail' ,query: {
          projectId: id
      }})
    },
  }
}
</script>

<style scoped>

</style>