import { getUserMenuList } from "@/api/system/menu";
import { asyncRouterMap } from "@/config/router.config";

import myUtils from "@/utils/myUtils";

const getNameByPath = (menuPath) => {
  const list = asyncRouterMap[0].children
  const map = {}
  let fun = (list) => {
    for (let i = 0; i < list.length; i++) {
      const item = list[i]
      const { path, name, children } = item
      if (myUtils.coll.isNotEmpty(children)){
        fun(children)
      }else{
        map[path] = name
      }
    }
  }
  fun(list)
  return map[menuPath]
}

const menu = {
  state: {
    menuList: []
  },

  getters: {
    GetSysMenus: (stata) => {
      let menuList = stata.menuList
      const fun = (list) => {
        for (let i = 0; i < list.length; i++){
          let item = list[i]
          item.meta = {title: item.title, icon: item.icon}
          let { type, children, path } = item
          if (type === 0){
            if (myUtils.coll.isNotEmpty(children)){
              fun(children)
            }
          }else{
            const name = getNameByPath(path)
            if (name != null){
              item.name = name
            }
          }
        }
      }
      fun(menuList)
      return menuList
    }
  },

  mutations: {
    SET_MENULIST: (state, menuList) => {
      state.menuList = menuList
    }
  },

  actions: {
    GetMenuList ({ commit }) {
      return new Promise((resolve, reject) => {
        getUserMenuList().then(res => {
          commit('SET_MENULIST', res.data)
          resolve(res)
        }).catch(error => {
          reject(error)
        })
      })
    }
  }
}

export default menu