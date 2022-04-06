import reqApi from "@/utils/reqApi";

const getUrl = (uri) => {
  return 'projectquerypage/' + uri
}

export const projectApiUrl = {
  queryPageUnderway: getUrl('queryPageUnderway'),
  queryPageSupervise: getUrl('queryPageSupervise'),
  queryPageArchived: getUrl('queryPageArchived'),
}


