
export const apProcessTypeEnum = {
  CONTRACT_MT_APPROVAL: {
    type: "C",
    desc: "合同自定义审批",
    path: "/backlog/handle/seek-handle"
  }
}

export function getByType (type) {
  return Object.values(apProcessTypeEnum).find(obj => obj.type === type)
}