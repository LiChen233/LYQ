import reqApi from "@/utils/reqApi";

function getUrl (uri) {
  return 'sys_filter_unit/' + uri
}

function getAdminUrl (uri) {
  return 'admin_' + getUrl(uri)
}

export const apiUrl = {
  getSelectUnitData: getUrl('getSelectUnitData'),
  getChildren: getUrl('getChildren'),
  getTopLineList: getUrl('getTopLineList'),
  getList: getAdminUrl('getList'),
  edit: getAdminUrl('edit')
}

export function getSelectUnitData (key) {
  return reqApi({
    url: apiUrl.getSelectUnitData,
    method: 'get',
    params: { key: key }
  })
}

export function getChildren (pid, include) {
  return reqApi({
    url: apiUrl.getChildren,
    method: 'get',
    params: {
      pid,
      include
    }
  })
}

export function getTopLineList (id) {
  return reqApi({
    url: apiUrl.getTopLineList,
    method: 'get',
    params: {
      id: id
    }
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