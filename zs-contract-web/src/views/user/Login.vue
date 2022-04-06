<template>
  <div class="main">
    <div class="title">
      在线作业平台
    </div>
    <a-alert v-if="isLoginError" style="margin-bottom: 5px" :message="errorMessage" type="error" show-icon closable/>
    <a-form
      id="formLogin"
      class="user-layout-login"
      ref="formLogin"
      :form="form"
      @submit="handleSubmit"
    >
      <a-form-item>
        <a-input
          size="large"
          type="text"
          placeholder="请输入账号"
          v-decorator="[
            'account',
            {rules: [{ required: true, message: '请输入账号' }], validateTrigger: 'change'}
          ]"
        >
          <a-icon slot="prefix" type="user" :style="{ color: 'rgba(0,0,0,.25)' }"/>
        </a-input>
      </a-form-item>

      <a-form-item>
        <a-input-password
          size="large"
          placeholder="请输入密码"
          v-decorator="[
            'password',
            {rules: [{ required: true, message: '请输入密码' }], validateTrigger: 'blur'}
          ]"
        >
          <a-icon slot="prefix" type="lock" :style="{ color: 'rgba(0,0,0,.25)' }"/>
        </a-input-password>
      </a-form-item>

      <a-form-item>
        <div class="verify_box">
          <a-input
            size="large"
            type="text"
            placeholder="请输入验证码"
            v-decorator="[
              'verifyCode',
              {rules: [{ required: true, message: '请输入验证码' }, { pattern: /^[0-9a-zA-Z]{4}$/, message: '验证码格式错误' }], validateTrigger: 'blur'},
            ]"
          >
          </a-input>
          <img :src="verifyImageUrl" @click="refreshVerifyImage"/>
        </div>
      </a-form-item>

      <a-form-item style="margin-top:24px">
        <a-button
          size="large"
          type="primary"
          htmlType="submit"
          class="login-button"
          :loading="state.loginBtn"
          :disabled="state.loginBtn"
        >{{ $t('user.login.login') }}
        </a-button>
      </a-form-item>
    </a-form>
  </div>
</template>

<script>
import {mapActions} from 'vuex'
import {timeFix} from '@/utils/util'
import reqApi from "@/utils/reqApi";
import {baseUrl} from "@/api/system/user";

export default {
  components: {},
  data() {
    return {
      loginBtn: false,
      form: this.$form.createForm(this),
      state: {
        loginBtn: false
      },
      isLoginError: false,
      errorMessage: null,
      verifyImageKey: 123
    }
  },
  computed: {
    verifyImageUrl: function () {
      return process.env.VUE_APP_API_BASE_URL + "admin_sys_user/getLoginVerifyImg?key=" + this.verifyImageKey
    }
  },
  created() {
  },
  methods: {
    ...mapActions(['Login']),
    // handler
    handleSubmit(e) {
      e.preventDefault()
      const {
        form: {validateFields},
        state,
        Login
      } = this

      state.loginBtn = true

      validateFields({force: true}, (err, values) => {
        if (!err) {
          const loginParams = {...values}
          Login(loginParams)
            .then((res) => this.loginSuccess(res))
            .catch(err => this.requestFailed(err))
            .finally(() => {
              state.loginBtn = false
            })
        } else {
          setTimeout(() => {
            state.loginBtn = false
          }, 600)
        }
      })
    },
    loginSuccess(res) {
      this.$router.push({path: '/'})
      // 延迟 1 秒显示欢迎信息
      setTimeout(() => {
        this.$notification.success({
          message: '欢迎',
          description: `${timeFix()}，欢迎回来`
        })
        //通知
        reqApi({
          url: baseUrl.notice.getNotice
        }).then(res => {
          let icon = <a-icon type="smile" style="color: #52c41a"/>;
          for (let key in res.data) {
            let notice = res.data[key]
            icon.componentOptions.propsData.type = notice.icon
            this.$notification.open({
              message: notice.title,
              icon: icon,
              description: notice.remark,
              duration: 0,
            })
          }
        })
      }, 1000)
      this.isLoginError = false
    },
    requestFailed(err) {
      this.isLoginError = true
      this.errorMessage = err.msg
      this.refreshVerifyImage()
    },
    refreshVerifyImage() {
      this.verifyImageKey = Math.random()
    }
  }
}
</script>

<style lang="less" scoped>
.title {
  font-size: 18px;
  font-weight: 600;
  text-align: center;
  margin: 20px;
}

.user-layout-login {
  label {
    font-size: 14px;
  }

  .getCaptcha {
    display: block;
    width: 100%;
    height: 40px;
  }

  .forge-password {
    font-size: 14px;
  }

  button.login-button {
    padding: 0 15px;
    font-size: 16px;
    height: 40px;
    width: 100%;
  }

  .user-login-other {
    text-align: left;
    margin-top: 24px;
    line-height: 22px;

    .item-icon {
      font-size: 24px;
      color: rgba(0, 0, 0, 0.2);
      margin-left: 16px;
      vertical-align: middle;
      cursor: pointer;
      transition: color 0.3s;

      &:hover {
        color: #1890ff;
      }
    }

    .register {
      float: right;
    }
  }
}

.verify_box {
  display: flex;
  align-items: start;

  img {
    cursor: pointer;
    height: 45px;
    margin-left: 10px;
  }
}

</style>
