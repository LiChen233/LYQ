import reqApi from "@/utils/reqApi";
import request from "@/utils/request";

const getUrl = (uri) => {
  return 'contract/' + uri
}

export const contractApiUrl = {
  add: getUrl('submit'),
  save: getUrl('shorSave'),
  delShort: getUrl('delShortById'),
  getInfo: getUrl('getInfo'),
  createCode: getUrl('createCode'),
  getLogList: getUrl('getLogList'),
  getComContractInfo: getUrl('getComContractInfo')
}

export function contractApiAdd(data) {
  return reqApi({
    url: contractApiUrl.add,
    method: 'post',
    data: data
  })
}

export function contractApiShortSave(data) {
  return reqApi({
    url: contractApiUrl.save,
    method: 'post',
    data: data
  })
}

export function contractApiGetInfo(id) {
  return reqApi({
    url: contractApiUrl.getInfo,
    method: 'get',
    params: {contractId: id}
  })
}

export function contractApiGetLogList(id) {
  return reqApi({
    url: contractApiUrl.getLogList,
    method: 'get',
    params: {contractId: id}
  })
}


export function contractApiDelShort(id) {
  return reqApi({
    url: contractApiUrl.delShort,
    method: 'delete',
    params: {id: id},
  })
}

export function createCodeApi(data) {
  return request({
    url: contractApiUrl.createCode,
    method: 'get',
    responseType: 'arraybuffer',
    params: {content: data},
  }).then(res => {
    return 'data:image/png;base64,' + btoa(
      new Uint8Array(res)
        .reduce((data, byte) => data + String.fromCharCode(byte), '')
    );
  })
}

export function getComContractInfo(id) {
  return reqApi({
    url: contractApiUrl.getComContractInfo,
    method: 'get',
    params: { id: id }
  })
}
