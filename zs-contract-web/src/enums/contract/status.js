const statusEnums = {
  S_proType: {
    type: "S",
    title: "起草"
  },
  A_proType:{
    type:"A",
    title: "审批"
  },
  R_proType:{
    type:"R",
    title: "撤回"
  },
  D_proType:{
    type:"D",
    title: "签订",
    color:'#faad14'
  },
  Q_proType:{
    type:"Q",
    title: "履约",
    color:'#108ee9'
  },
  G_proType:{
    type:"G",
    title: "归档",
    color:'#52c41a'
  }
}
export {
  statusEnums,
}

export const getStatus = (type) => {
  let title  = Object.values(statusEnums).find(obj => obj.type == type);
  title = title == null ? {title: "-"} : title;
  return title;
}