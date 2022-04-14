import reqApi from "@/utils/reqApi";

const getUrl = (uri) => {
  return 'admin_sys_user/' + uri
}

export const api = {
  login: getUrl("login"),
  logout: getUrl('logout'),
  getUserInfo: getUrl('getUserInfo'),
  add: getUrl('add'),
  edit: getUrl('edit'),
  del: getUrl('del'),
  queryPage: getUrl('queryPage'),
  getComUserInfo: 'sys_user/getComUserInfo'
}

export const baseUrl = {
  clazz: {
    queryPage: 'clazz/queryPage',
    crud: 'clazz/',
    list: 'clazz/list',
    myList: 'clazz/myList'
  },
  subject: {
    queryPage: 'subject/queryPage',
    crud: 'subject/',
    list: 'subject/list',
  },
  work: {
    queryPage: 'work/queryPage',
    myWork: 'work/myWork',
    crud: 'work/',
    upload: 'work/upload',
    list: 'work/list',
    getWorkDone: 'work/getWorkDone'
  },
  topic: {
    queryPage: 'topic/queryPage',
    crud: 'topic/',
    list: 'topic/list'
  },
  answer: {
    queryPage: 'answer/queryPage',
    crud: 'answer/',
    list: 'answer/list'
  },
  topicCheck: {
    queryPage: 'topiccheck/queryPage',
    crud: 'topiccheck/',
    list: 'topiccheck/list'
  },
  notice: {
    queryPage: 'notice/queryPage',
    crud: 'notice/',
    list: 'notice/list',
    getNotice: 'notice/getNotice',
    push: 'notice/push',
    off: 'notice/off'
  },
  rate: {
    crud: 'rate/'
  }
}

export function login(data) {
  return reqApi({
    url: api.login,
    method: 'post',
    params: data
  })
}

export function logout() {
  return reqApi({
    url: api.logout,
    method: 'get'
  })
}

export function getUserInfo() {
  return reqApi({
    url: api.getUserInfo,
    method: 'get'
  })
}

export function add(data) {
  return reqApi({
    url: api.add,
    method: 'post',
    data: data
  })
}

export function edit(data) {
  return reqApi({
    url: api.edit,
    method: 'put',
    data: data
  })
}

export function del(userId) {
  return reqApi({
    url: api.del,
    method: 'delete',
    params: {userId: userId}
  })
}

export function getComUserInfo(userId) {
  return reqApi({
    url: api.getComUserInfo,
    method: 'get',
    params: {userId: userId}
  })
}