import reqApi from "@/utils/reqApi";

const getUrl = (uri) => {
  return 'contractquerypage/' + uri
}

export const queryPageApiUrl = {
  queryPageDraft: getUrl('queryPageDraft'),
  queryPageTemplate: getUrl('queryPageTemplate'),
  queryPageDecide: getUrl('queryPageDecide'),
  queryPageArchived: getUrl('queryPageArchived'),
  queryPageExecute: getUrl('queryPageExecute'),
}

export function DraftApiQueryPage (data) {
  return reqApi({
    url: queryPageApiUrl.queryPageDraft,
    method: 'get',
    params: data,
  })
}

export function TemplateApiQueryPage (data) {
  return reqApi({
    url: queryPageApiUrl.queryPageTemplate,
    method: 'get',
    params: data,
  })
}

export function DecideApiQueryPage (data) {
  return reqApi({
    url: queryPageApiUrl.queryPageDecide,
    method: 'post',
    data: data,
  })
}

export function ArchivedApiQueryPage (data) {
  return reqApi({
    url: queryPageApiUrl.queryPageArchived,
    method: 'post',
    data: data,
  })
}

