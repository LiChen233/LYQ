<template>
  <div>
    <a-card class="nav" :bordered="false">
      <h2>老师好！</h2>
      <h1>您所教授的科目是：{{ subject.name }}</h1>
      <h1>选择班级与日期后，您可对此班级进行作业布置，如果此天已有作业，可直接进行编辑</h1>
      <a-form-model
        ref="workForm"
        :model="work"
        :rules="workRules"
        :label-col="{ span: 1 }"
        :wrapper-col="{ span: 8 }"
      >
        <a-form-model-item style="margin-top: 24px" label="班级" prop="clazzId">
          <a-select v-model="work.clazzId" placeholder="选择您所在的班级">
            <a-select-option :value="item.id" v-for="item in clazzList" :key="item.id">
              {{ item.name }}
            </a-select-option>
          </a-select>
        </a-form-model-item>
        <a-form-model-item label="时间" prop="time">
          <a-date-picker
            v-model="work.time"
            @change="checkTime"
            type="date"
            placeholder="可布置其他日期的作业"
            style="width: 100%;"
          />
        </a-form-model-item>
        <a-button type="primary" @click="getWork" :loading="loadKeyWork">确定</a-button>
      </a-form-model>
    </a-card>
    <a-card class="con" v-if="work.id!=null" :bordered="false">
      <h2>资料上传</h2>
      <h1>可以上传本次作业将使用的资料，或明日课程所需的资料</h1>
      <my-file-upload :multiple="true" :files-ids.sync="files" :default-file-ids="work.files">

      </my-file-upload>
    </a-card>
    <a-card class="con" v-if="work.id!=null" :bordered="false">
      <topic v-for="(item,index) in topicList"
             :key="item.id"
             :info="item"
             :index="index+1"
             :bordered="topicList.length-1!==index || active"
      ></topic>
      <topic-add
        :bordered="false"
        :work-id="work.id"
        :type="type"
        v-if="active"
        @submit="addTopic"
      ></topic-add>
      <div class="btn">
        <a-button type="primary" @click="addRadio" v-if="type!=='R'">
          {{ type !== 'C' ? '添加单选题' : '切换单选题' }}
        </a-button>
        <a-button type="primary" @click="addCheck" v-if="type!=='C'">
          {{ type !== 'R' ? '添加多选题' : '切换多选题' }}
        </a-button>
        <a-button type="primary" @click="addEx" v-if="type!=='E'">
          添加附加题
        </a-button>
      </div>
    </a-card>
  </div>
</template>

<script>
import {baseUrl} from '@/api/system/user'
import reqApi from "@/utils/reqApi";
import Topic from "@/views/topic/Topic";
import TopicAdd from "@/views/topic/TopicAdd";
import MyFileUpload from "@/components/My/file/MyFileUpload";

export default {
  name: 'WorkAddEdit',
  components: {
    MyFileUpload,
    TopicAdd,
    Topic,
  },
  props: {},
  data() {
    return {
      work: {
        clazzId: undefined,
        time: undefined,
      },
      workRules: {
        clazzId: {required: true, message: '请选择班级', trigger: 'blur'},
        time: {required: true, message: '请选择时间', trigger: 'blur'},
      },
      type: '',
      clazzList: [],
      topicList: [],
      subject: {},
      active: false,
      workCrud: baseUrl.work.crud,
      upload: baseUrl.work.upload,
      files: ''
    }
  },
  watch: {
    files: function (ids) {
      if (this.work.files !== ids) {
        reqApi({
          url: this.upload,
          data: {
            id: this.work.id,
            files: ids
          },
          method: 'put'
        }).then(res => {
          this.$message.success('资料已保存')
        })
      }
    }
  },
  mounted() {
    let q = this.$route.query
    if (q.workId) {
      this.work.id = q.workId
      this.work.time = q.time
      this.work.clazzId = q.clazzId
      this.getWork()
    }
    this.getSubject()
    this.getClazzList()
  },
  computed: {
    loadKeyWork: function () {
      if (this.$myUtils.str.isNotEmpty(this.workCrud)) {
        return this.$store.getters.getLoad(this.workCrud)
      } else {
        return false
      }
    }
  },
  methods: {
    getSubject() {
      this.$store.dispatch('GetInfo').then(data => {
        if (data.subjectId == null) {
          this.$message.info('您还未被分配科目，无法进行作业布置，请联系管理员进行设置')
          return
        }
        reqApi({
          url: baseUrl.subject.crud,
          params: {
            id: data.subjectId
          }
        }).then(res => {
          this.subject = res.data
        })
      })
    },
    getClazzList() {
      reqApi({
        url: baseUrl.clazz.myList,
      }).then(res => {
        this.clazzList = res.data
      })
    },
    getTopicList() {
      reqApi({
        url: baseUrl.topic.list,
        params: {
          workId: this.work.id
        }
      }).then(res => {
        this.topicList = res.data
      })
    },
    checkTime(date, dateString) {
      this.work.time = dateString
    },
    getWork() {
      this.$refs.workForm.validate(valid => {
        if (valid) {
          reqApi({
            url: this.workCrud,
            data: this.work,
            method: 'post'
          }).then(res => {
            this.work = res.data
            this.getTopicList()
          })
        }
      })
    },
    addRadio() {
      this.type = 'R'
      this.active = true
    },
    addCheck() {
      this.type = 'C'
      this.active = true
    },
    addTopic() {
      this.type = ''
      this.active = false
      this.getTopicList()
    },
    addEx() {
      this.type = 'E'
      this.active = true
    }
  }
}
</script>

<style lang="less" scoped>
.btn button:nth-child(1) {
  margin-right: 10px;
}

.btn button:nth-child(2) {
  margin-right: 10px;
}

.nav {
  transition: .2s;

  /deep/ .ant-form-item-label {
    margin-right: 20px;
  }
}

.con {
  margin-top: 20px;
}

/deep/ .ant-collapse-header {
  border-bottom: 1px solid #f7f7f7;
}

/deep/ .ant-collapse-no-arrow {
  border: none;
  background-color: #FFF;
}
</style>
