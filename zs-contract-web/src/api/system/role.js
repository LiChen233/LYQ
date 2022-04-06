import reqApi from "@/utils/reqApi";

const getAdminUrl = (uri) => {
  return 'admin_' + getUrl(uri)
}

const getUrl = (uri) => {
  return 'sys_role/' + uri
}

export const roleApiUrl = {
  add: getAdminUrl('add'),
  edit: getAdminUrl('edit'),
  del: getAdminUrl('del'),
  queryPage: getAdminUrl('queryPage'),
  getList: getAdminUrl('getList'),
  getComRoleInfo: getUrl('getComRoleInfo')
}

export function roleApiAdd (data) {
  return reqApi({
    url: roleApiUrl.add,
    method: 'post',
    data: data
  })
}

export function roleApiEdit (data) {
  return reqApi({
    url: roleApiUrl.edit,
    method: 'put',
    data: data
  })
}

export function roleApiDel (id) {
  return reqApi({
    url: roleApiUrl.del,
    method: 'delete',
    params: { id: id }
  })
}

export function roleApiGetList (type) {
  return reqApi({
    url: roleApiUrl.getList,
    method: 'get',
    params: {type: type}
  })
}

export function getComRoleInfo (id) {
  return reqApi({
    url: roleApiUrl.getComRoleInfo,
    method: 'get',
    params: { id: id }
  })
}