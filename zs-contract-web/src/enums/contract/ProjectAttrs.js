//招标方式
const bidMode = {
  type_public: {
    title: '公开招标',
    type: 'P'
  },
  type_invite: {
    title: '邀请招标',
    type: 'I'
  },
  type_viton: {
    title: '比选',
    type: 'V'
  },
  type_jzqcg: {
    title: '竞争性谈判',
    type: 'J'
  },
  type_bot: {
    title: 'BOT项目招标',
    type: 'B'
  },
}

//项目类型
const projectType = {
  type_ppp: {
    title: '政府特许经营合同(ppp项目)',
    type: 'P'
  },
  type_cp: {
    title: '建设工程项目合同',
    type: 'C'
  },
  type_info: {
    title: '信息化项目合同',
    type: 'I'
  },
}

// 自有资金、吸收资金、专项资金
//资金来源
const capitalSource = {
  type_ppp: {
    title: '自有资金',
    type: 'O'
  },
  type_cp: {
    title: '吸收资金',
    type: 'A'
  },
  type_info: {
    title: '专项资金',
    type: 'S'
  },
}

export {
  bidMode,
  projectType,
  capitalSource
}

export const getMode = (type) =>{
  let title = Object.values(bidMode).find(obj => obj.type == type);
  return title == null ? {title:"-"} : title;
}

export const getProjectType = (type) =>{
  let title = Object.values(projectType).find(obj => obj.type == type);
  return title == null ? {title:"-"} : title;
}

export const getCapitalSource = (type) =>{
  let title = Object.values(capitalSource).find(obj => obj.type == type);
  return title == null ? {title:"-"} : title;
}
