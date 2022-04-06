// 合同类型
const typeEnums = {
  type_base: {
    type: "1001",
    title: "合作开发合同（战略合作协议等）"
  },
  type_nature: {
    type: "1002",
    title: "招商引资合同"
  },
  type_public: {
    type: "1003",
    title: "政府特许经营合同（PPP项目）*"
  },
  type_house: {
    type: "1004",
    title: "建设工程项目合同*"
  },
  type_deal: {
    type: "1005",
    title: "信息化项目合同*"
  },
  type_attract: {
    type: "1006",
    title: "国有自然资源使用权相关合同（土地、矿产、海域等）"
  },
  type_remove: {
    type: "1007",
    title: "公共房屋租赁合同"
  },
  type_cooperative: {
    type: "1008",
    title: "政府征收征用（土地、房屋等）"
  },
  type_agreement: {
    type: "1009",
    title: "拆迁改造合同"
  },
  type_subsidy: {
    type: "1010",
    title: "其他合同（中介、装饰、采购等）"
  },

}
// 合同性质
const propertyEnums = {
  income: {
    type: "0",
    title: "支入"
  },
  expenditure: {
    type: "1",
    title: "支出"
  },
  other:{
    type: "2",
    title:"其他"
  }
}

const attrEnums ={
  general: {
    type: 1,
    title: "一般性合同"
  },
  great:{
    type: 2,
    title: "重大合同"
  }
}

export {
  typeEnums,
  propertyEnums,
  attrEnums
}

export const getPropertyByType = (type) => {
  let title  = Object.values(propertyEnums).find(obj => obj.type == type);
  return title == null ? {title:"-"} : title;
}

export const getByType = (type) => {
  let title = Object.values(typeEnums).find(obj => obj.type == type);
  return title == null ? {title:"-"} : title;
}

export const getAttr = (type) =>{
  let title = Object.values(attrEnums).find(obj => obj.type == type);
  return title == null ? {title:"-"} : title;
}