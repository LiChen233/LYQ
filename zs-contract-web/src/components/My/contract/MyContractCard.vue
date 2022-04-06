<template>
  <a-card @dblclick="openDetail(data.id)" style="margin: 5px 0 10px 0;cursor: pointer" :bordered="false">
    <div style="display: flex; font-weight: bold; color: #000000">
      <div style="font-size: 18px;">{{ data.title }}</div>
      <div style="padding: 4px;">[{{ data.number }}]</div>
    </div>
    <div>
      <template>
        <a-descriptions :column="isMobile ? 1 : 3" size="small">
          <a-descriptions-item v-if="data.attr" label="合同属性">{{ getAttr(data.attr).title }}</a-descriptions-item>
          <a-descriptions-item label="合同性质">{{ getPropertyByType(data.nature).title }}</a-descriptions-item>
          <a-descriptions-item label="项目">
            <template v-if="data.projectId!=null">
              <a @click="openProjectDetail(data.projectId)">{{ data.projectTitle }}</a>
            </template>
            <template v-else>
              --
            </template>
          </a-descriptions-item>
          <a-descriptions-item label="合同金额（万元）">{{ data.subjectMoney }}</a-descriptions-item>
          <a-descriptions-item label="登记时间">{{ data.createTime }}</a-descriptions-item>
          <a-descriptions-item label="承办人">
            <my-user-info :user-id="data.headUser"></my-user-info>
          </a-descriptions-item>
        </a-descriptions>
      </template>
    </div>
    <slot name="tips"></slot>
    <slot name="btn">
      <my-button :id="data.id"></my-button>
    </slot>
  </a-card>
</template>

<script>
import {baseMixin} from '@/store/app-mixin';
import MyUnitInfo from "@/components/My/info/MyUnitInfo";
import MyUserInfo from "@/components/My/info/MyUserInfo";
import MyButton from "@/components/My/Button/MyButton";
import {getPropertyByType, getByType,getAttr} from '@/enums/contract/ContractAttr'
import {setObjDefaultKey} from "@/utils/util";

export default {
  name: "MyContractCard",
  components: {
    MyUnitInfo,
    MyUserInfo,
    MyButton
  },
  props: {
    item:{
      type: Object,
    }
  },
  mixins: [baseMixin],

  data() {
    return {
      data: {},
    }
  },
  created() {
    this.data = this.item;
  },
  methods: {
    openProjectDetail(id) {
      this.$router.push({ path: '/project/project-detail' ,query: {
          projectId: id
        }})
    },
    openDetail(id) {
      this.$router.push({ path: '/contract/detail' ,query: {
        contractId: id
      }})
    },

    getPropertyByType,
    getByType,
    getAttr
  }
}
</script>

<style scoped>

</style>