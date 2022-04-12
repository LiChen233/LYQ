import reqApi from "@/utils/reqApi";

const getAdminUrl = (uri) => {
  return 'admin_' + getUrl(uri)
}

const getUrl = (uri) => {
  return "sys_tag/" + uri
}

export const tagApiUrl = {
  add: getAdminUrl('add'),
  edit: getAdminUrl('edit'),
  del: getAdminUrl('del'),
  queryPage: getAdminUrl('queryPage'),
  getListByType:getAdminUrl('getListByType'),
  getComTagInfo: getUrl('getComTagInfo')
}

export function tagApiAdd (data) {
  return reqApi({
    url: tagApiUrl.add,
    method: 'post',
    data: data
  })
}

export function tagApiEdit (data) {
  return reqApi({
    url: tagApiUrl.edit,
    method: 'put',
    data: data
  })
}

export function tagApiDel (id) {
  return reqApi({
    url: tagApiUrl.del,
    method: 'delete',
    params: { id: id }
  })
}

export function tagApiGetList (type) {
  return reqApi({
    url: tagApiUrl.getListByType,
    method: 'get',
    params: {type: type.type}
  })
}

export function getComTagInfo (id) {
  return reqApi({
    url: tagApiUrl.getComTagInfo,
    method: 'get',
    params: { id: id }
  })
}