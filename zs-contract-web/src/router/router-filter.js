import router from '@/router'
import store from '@/store'
import storage from 'store'
import NProgress from 'nprogress' // progress bar
import '@/components/NProgress/nprogress.less' // progress bar custom style
import notification from 'ant-design-vue/es/notification'
import {ACCESS_TOKEN} from "@/store/mutation-types";

NProgress.configure({ showSpinner: false }) // NProgress Configuration

const allowList = ['login', 'register', 'registerResult'] // no redirect allowList
const loginRoutePath = '/user/login'
const defaultRoutePath = '/dashboard/workplace'

router.beforeEach((to, from, next) => {
  NProgress.start()
  const token = storage.get(ACCESS_TOKEN)
  if (token){
    if (to.path === loginRoutePath){
      next({ path: defaultRoutePath })
    }else{
      next()
    }
  }else{
    if (allowList.includes(to.name)) {
      // 在免登录名单，直接进入
      next()
    } else {
      next({path: loginRoutePath})
    }
  }
})

router.afterEach(() => {
  NProgress.done() // finish progress bar
})