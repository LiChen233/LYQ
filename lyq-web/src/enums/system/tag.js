
export const tagEnum = {
  USER: {
    type: "U",
    label: "用户"
  },
  AGENCY: {
    type: "A",
    label: "单位"
  },
  CONTRACT: {
    type: "C",
    label: "合同"
  }
}

export const getByType = (type) => {
  return Object.values(tagEnum).find(tag => tag.type === type)
}