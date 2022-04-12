import reqApi from "@/utils/reqApi";

const getUrl = (uri) => {
  return 'admin_sys_unit/' + uri
}

export const apiUrl = {
  add: getUrl('add'),
  edit: getUrl('edit'),
  del: getUrl('del'),
  getTreeList: getUrl('getTreeList'),
  getComUnitInfo: 'sys_unit/getComUnitInfo',
  getAreaTree: 'sys_unit/getAreaTree'
}

export function add (data) {
  return reqApi({
    url: apiUrl.add,
    method: 'post',
    data: data
  })
}

export function edit (data) {
  return reqApi({
    url: apiUrl.edit,
    method: 'put',
    data: data
  })
}

export function del (id) {
  return reqApi({
    url: apiUrl.del,
    method: 'delete',
    params: { id: id }
  })
}

export function getTreeList () {
  return reqApi({
    url: apiUrl.getTreeList,
    method: 'get'
  })
}

export function getComUnitInfo (unitId) {
  return reqApi({
    url: apiUrl.getComUnitInfo,
    method: 'get',
    params: {unitId: unitId}
  })
}

export function getAreaTree () {
  return reqApi({
    url: apiUrl.getAreaTree,
    method: 'get'
  })
}