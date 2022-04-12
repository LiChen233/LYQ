import reqApi from "@/utils/reqApi";

const getUrl = (uri) => {
  return 'admin_sys_menu/' + uri
}

export function add (data) {
  return reqApi({
    url: getUrl('add'),
    method: 'post',
    data: data
  })
}

export function edit (data) {
  return reqApi({
    url: getUrl('edit'),
    method: 'put',
    data: data
  })
}

export function del (id) {
  return reqApi({
    url: getUrl('del'),
    method: 'delete',
    params: { id: id }
  })
}

export function getTreeList () {
  return reqApi({
    url: getUrl('getTreeList'),
    method: 'get'
  })
}

export function getUserMenuList () {
  return reqApi({
    url: getUrl('getUserMenuList'),
    method: 'get'
  })
}