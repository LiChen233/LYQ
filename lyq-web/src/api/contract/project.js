import reqApi from "@/utils/reqApi";

const getUrl = (uri) => {
  return 'project/' + uri
}

export const projectApiUrl = {
  add: getUrl('submit'),
  save: getUrl('shorSave'),
  get: getUrl('getInfo')
}

export function projectApiAdd(data) {
  return reqApi({
    url: projectApiUrl.add,
    method: 'post',
    data: data
  })
}

export function projectApiGet(projectId) {
  return reqApi({
    url: projectApiUrl.get,
    method: 'get',
    params: {projectId: projectId}
  })
}
