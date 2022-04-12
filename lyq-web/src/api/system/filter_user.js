import reqApi from "@/utils/reqApi";

function getUrl (uri) {
  return 'sys_filter_user/' + uri
}

function getAdminUrl (uri) {
  return 'admin_' + getUrl(uri)
}

export const apiUrl = {
  getSelectUserData: getUrl('getSelectUserData'),
  getList: getAdminUrl('getList'),
  edit: getAdminUrl('edit')
}

export function getSelectUserData (key) {
  return reqApi({
    url: apiUrl.getSelectUserData,
    method: 'get',
    params: { key: key }
  })
}

export function getList () {
  return reqApi({
    url: apiUrl.getList,
    method: 'get'
  })
}

export function edit (data) {
  return reqApi({
    url: apiUrl.edit,
    method: 'put',
    data: data
  })
}