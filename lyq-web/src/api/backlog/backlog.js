import reqApi from "@/utils/reqApi";

const getUrl = (uri) => {
  return "backlog/" + uri
}

export const apiUrl = {
  queryPageMe: getUrl("queryPageMe"),
  backlogSubmit: getUrl("backlogSubmit"),
  getBacklogSubmitInfo: getUrl("getBacklogSubmitInfo")
}

export function queryPageMe(data) {
  return reqApi({
    url: apiUrl.queryPageMe,
    method: "post",
    data: data
  })
}

export function backlogSubmit(backlogId, data) {
  return reqApi({
    url: apiUrl.backlogSubmit,
    method: "post",
    data: {backlogId: backlogId, data: data}
  })
}

export function getBacklogSubmitInfo(backlogId, data) {
  return reqApi({
    url: apiUrl.getBacklogSubmitInfo,
    method: "post",
    data: {backlogId: backlogId, ...Object(data)}
  })
}