import reqApi from "@/utils/reqApi";

const getUrl = (uri) => {
  return 'reviewKey/' + uri
}

export const reviewApiUrl = {
  add: getUrl('addAdvise'),
  del: getUrl('del'),
}

export function reviewApiAdd(data) {
  return reqApi({
    url: reviewApiUrl.add,
    method: 'post',
    data: data
  })
}

export function reviewApiDel(data) {
  return reqApi({
    url: reviewApiUrl.del,
    method: 'delete',
    params: {id: data}
  })
}
