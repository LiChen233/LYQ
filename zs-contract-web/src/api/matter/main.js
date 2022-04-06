import reqApi from "@/utils/reqApi";

const getUrl = (uri) => {
  return 'mt_main/' + uri
}

export const mainApiUrl = {
  matterSubmit: getUrl('matterSubmit'),
  getMatterInfo: getUrl('getMatterInfo'),
  getMatterButtonInfo: getUrl('getBtnList')
}

export function mainApiMatterSubmit (contractId, matterType, data) {
  return reqApi({
    url: mainApiUrl.matterSubmit,
    method: "post",
    data: {
      contractId: contractId,
      matterType: matterType.type,
      data: data
    }
  })
}

export function mainApiGetMatterInfo(contractId, matterType, matterId) {
  return reqApi({
    url: mainApiUrl.getMatterInfo,
    method: "get",
    params: {
      matterType: matterType.type,
      contractId: contractId,
      matterId: matterId
    }
  })
}

export function mainApiGetMatterButtonInfo(contractId) {
  return reqApi({
    url: mainApiUrl.getMatterButtonInfo,
    method: "get",
    params: {
      contractId: contractId
    }
  })
}