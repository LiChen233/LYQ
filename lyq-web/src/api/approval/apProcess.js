import reqApi from "@/utils/reqApi";

const getUrl = (uri) => {
  return "ap_process/" + uri
}

export const apiUrl = {
  queryPageMe: getUrl("queryPageMe"),
  approval: getUrl("approval"),
  getInfo: getUrl("getInfo")
}

export function queryPageMe(data) {
  return reqApi({
    url: apiUrl.queryPageMe,
    method: "post",
    data: data
  })
}

export function approval(apProcessId ,data) {
  return reqApi({
    url: apiUrl.approval,
    method: "post",
    data: {apProcessId, ...data}
  })
}

export function getInfo(apProcessId) {
  return reqApi({
    url: apiUrl.getInfo,
    method: "get",
    params: { apProcessId: apProcessId }
  })
}