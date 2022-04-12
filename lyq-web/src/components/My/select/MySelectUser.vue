<template>
  <div>
    <slot>
      <a-select v-model="selectdUserId" mode="multiple" style="width: 100%" :placeholder="placeholder" @dropdownVisibleChange="open">
        <div slot="dropdownRender">
        </div>
        <a-select-option v-for="(item, index) in selectdUser" :value="item.id" :key="item.id">
          {{item.username}}
        </a-select-option>
      </a-select>
    </slot>

    <a-modal v-model="visible" title="选择用户" width="800px" @cancel="visible = false" @ok="ok">
      <div class="modal-box">
        <a-row style="height: 100%">
          <a-col :span="7">
            <div>已选用户 <span v-if="!multiple">{{selectdUserId.length}}/{{limit}}</span></div>
            <div>
              <template v-for="(user, index) in selectdUser">
                <span class="ant-tag" :key="user.id">
                  {{getUserText(user)}}
                  <a-icon type="close" @click="removeSelectdUser(index)"></a-icon>
                </span>
              </template>
            </div>
          </a-col>
          <a-col :span="1">
            <a-divider type="vertical"></a-divider>
          </a-col>
          <a-col style="height: 100%" :span="16">
            <a-row :gutter="10">
              <a-col :span="12">
                <a-tree-select style="width: 100%"
                               allowClear
                               show-search
                               :tree-data="unitList"
                               :replace-fields="{key: 'id', value: 'id', title: 'unitName'}"
                               @select="searchUnitSelect"
                               @change="searchUnitChange"
                               placeholder="根据单位进行查询">
                </a-tree-select>
              </a-col>
              <a-col :span="12">
                <a-input v-model="search.username" allowClear placeholder="根据用户名进行查询" @change="searchUsernameChange"></a-input>
              </a-col>
            </a-row>
            <a-row style="margin-top: 10px;overflow: hidden;overflow-y: scroll;height: calc(100% - 43px)">
              <div style="display: flex; flex-direction: column">
                <template v-for="(user, index) in showUserList">
                  <a-checkbox :value="user.id"
                              :key="user.id"
                              :checked="selectdUserId.indexOf(user.id) > -1"
                              :disabled="isDisabledUnit(user)"
                              @change="() => handleCheckedChange(user.id)">
                    {{getUserText(user)}}
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
import { getSelectUserData } from "@/api/system/filter_user";
import {TreeSelect} from "ant-design-vue";
Vue.use(TreeSelect)

export default {
  name: "MySelectUser",
  props: {
    value: [String, Array],
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
    event: 'updateSelectdUserId'
  },
  data () {
    return {
      visible: false,
      unitList: [],
      userList: [],
      showUserList: [],
      selectdUserId: [],
      search: {
        unitId: null,
        unitLevel: 0,
        username: null
      },
      dataValue: null
    }
  },
  watch: {
    value: function (data) {
      this.handleValue()
    },
    selectdUserId: function (data) {
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
      this.$emit("updateSelectdUserId", value)
    }
  },
  computed: {
    selectdUser: function () {
      var filter = this.userList.filter(user => this.selectdUserId.indexOf(user.id) > -1)
      return filter
    }
  },
  created() {
    this.getData()
  },
  mounted() {
    this.handleValue()
  },
  methods: {
    handleValue () {
      const data = this.value
      if (this.$myUtils.str.isNotEmpty(data)
        || this.$myUtils.coll.isNotEmpty(data)) {
        // 判断类型
        if (typeof data === 'string'){
          this.selectdUserId = data.split(",")
        } else {
          this.selectdUserId = data
        }
      }
    },
    getData () {
      getSelectUserData().then(res => {
        const { sysUnitList, sysUserList } = res.data
        this.unitList = sysUnitList
        this.userList = sysUserList
        this.showUserList = [...sysUserList]
      })
    },
    getUserText (user) {
      const { username, unitList } = user
      return  username + " " + unitList.map(unit => unit.unitName).join("/")
    },
    handleCheckedChange(userId) {
      const index = this.selectdUserId.indexOf(userId)
      if (index > -1) {
        // 存在，移除
        this.selectdUserId.splice(index, 1)
      }else{
        // 不存在，添加
        // 判断limit个数,是否多选
        if (this.multiple) {
          this.selectdUserId.push(userId)
        }else{
          if (this.limit === 1) {
            this.selectdUserId.splice(index, 1, userId)
          } else if (this.limit === this.selectdUserId.length) {
            this.$message.info('选择个数已达上限')
          }else{
            this.selectdUserId.push(userId)
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
      this.searchUser()
    },
    searchUnitChange (value) {
      // 为空时 searchUnitSelect不会触发所以需要该方法
      if (!value) {
        this.search.unitId = null
        this.searchUser()
      }
    },
    searchUsernameChange (event) {
      this.searchUser()
    },
    searchUser () {
      const { username, unitLevel, unitId } = this.search
      this.showUserList = this.userList.filter(user => {
        let nd = true
        let ud = true
        if (this.$myUtils.str.isNotEmpty(username)) {
          nd = user.username.indexOf(username) > -1
        }
        if (unitId != null) {
          if (user.unitList.length > unitLevel){
            ud = user.unitList[unitLevel].id == unitId
          }else{
            ud = false
          }
        }
        return nd && ud
      })
    },
    removeSelectdUser (index) {
      const user = this.selectdUser[index]
      const userId = user.id
      let idIndex = null
      for (let i = 0; i < this.selectdUserId.length; i++) {
        if (this.selectdUserId[i] === userId) {
          idIndex = i
        }
      }
      if (idIndex != null){
        this.selectdUserId.splice(idIndex, 1)
      }
    },
    isDisabledUnit (user) {
      if (this.$myUtils.coll.isNotEmpty(this.disabledList)) {
        return this.disabledList.indexOf(user.id) > -1
      }
      return false
    },
    ok () {
      this.visible = false
      this.$emit("ok", this.dataValue)
    },
    clear () {
      this.selectdUserId = []
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

  .ant-tag{
    margin-top: 5px;
  }

</style>