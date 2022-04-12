import reqApi from "@/utils/reqApi";
import {ACCESS_TOKEN} from "@/store/mutation-types";
import storage from "store";

const serverUrl = process.env.VUE_APP_API_BASE_URL

const getUrl = (uri) => {
  return 'sys_files/' + uri
}

export const filesApiUrl = {
  uploadFile: getUrl('uploadFile'),
  downloadFile: getUrl('downloadFile/'),
  previewFile: getUrl('previewFile/'),
  delFile: getUrl('delFile'),
  getListByIds: getUrl('getListByIds')
}

export function filesApiDelFile (fileId, isDel) {
  return reqApi({
    url: filesApiUrl.delFile,
    method: 'put',
    params: { fileId, isDel }
  })
}

export function filesApiGetListByIds (ids) {
  return reqApi({
    url: filesApiUrl.getListByIds,
    method: 'get',
    params: { ids }
  })
}

export function getDownLoadFileUrl (filesId) {
  var url = serverUrl + filesApiUrl.downloadFile + filesId
  url += "?" + ACCESS_TOKEN + "=" + storage.get(ACCESS_TOKEN)
  return url
}

export function getPreviewFileUrl (filesId) {
  var url = serverUrl + filesApiUrl.previewFile + filesId
  url += "?" + ACCESS_TOKEN + "=" + storage.get(ACCESS_TOKEN)
  return url
}