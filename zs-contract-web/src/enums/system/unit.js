
const typeEnum = [
  {
    type: "A",
    desc: "区域",
    color: "green"
  },
  {
    type: "N",
    desc: "节点",
    color: "green"
  },
  {
    type: "U",
    desc: "单位",
    color: "cyan"
  },
  {
    type: "D",
    desc: "部门",
    color: "blue"
  },
  {
    type: "O",
    desc: "科室",
    color: "purple"
  }
]

const getByType = (type) => {
  return typeEnum.find(o => o.type === type);
}

export {
  typeEnum,
  getByType
}