
export const backlogTypeEnum = {
  MT_SEEK: {
    type: "S",
    desc: "征求意见反馈",
    path: "/backlog/handle/seek-handle"
  }
}

export function getByType (type) {
  return Object.values(backlogTypeEnum).find(obj => obj.type === type)
}