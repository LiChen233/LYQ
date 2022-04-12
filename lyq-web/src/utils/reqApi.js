import request from "@/utils/request";
import message from "ant-design-vue/lib/message";
import store from "@/store";
import router from "@/router";

const errorHandler = (code, msg) => {
  message.error(msg);
  if (code == 504){
    store.commit('CLEAR_LOGIN_INFO')
    router.push({ name: 'login' })
  }
}

export default function api(data) {
  return new Promise((resolve, reject) => {
    request(data).then(res => {
      const { code, msg } = res
      if (code != 200){
        errorHandler(code, msg)
        reject(res)
      }else{
        resolve(res)
      }
    })
  });
}