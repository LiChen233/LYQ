const matterTypeEnum = {
  // 背景材料
  survey: {
    type: "S",
    title: "背景材料",
    path: "/matter/survey-submit"
  },
  //本单位合法性审查
  legality_review: {
    type: 'U',
    title: "本单位合法性审查",
    path: "/matter/legalityReview"
  },
  // 公平竞争审查
  fair_review: {
    type: "F",
    title: "公平竞争审查",
    path: "/matter/fairReview"
  },
  // 征求意见
  seek: {
    type: "K",
    title: "征求意见",
    path: "/matter/seek-submit"
  },
  // 合同正档上传
  document_upload: {
    type: "Z",
    title: "合同正档上传",
    path: "/matter/documentUpload"
  },
  //本单位集体决策
  unit_decision: {
    type: 'UD',
    title: "本单位集体决策",
    path: "/matter/decision"
  },
  //政府集体决策
  gov_decision: {
    type: 'GD',
    title: "政府集体决策",
    path: "/matter/decision"
  },
  //司法局合法性审查
  jud_legality_review: {
    type: 'J',
    title: "司法局合法性审查",
    path: "/matter/legalityReview"
  },
  // 自定义审批
  approval: {
    type: "A",
    title: "自定义审批",
    path: "/matter/approval"
  }
}

export function getByType(type) {
  return Object.values(matterTypeEnum).find(typeEnum => typeEnum.type === type)
}

export {
  matterTypeEnum
}