
export const statusEnum = {
  EXECUTION:{
    status: "S",
    desc: "审批中",
    tagColor: "blue"
  },
  WAIT: {
    status: "Z",
    desc: "审批驳回等待重新审批",
    tagColor: "orange"
  },
  AGAIN_APPROVAL: {
    status: "C",
    desc: "重新审批",
    tagColor: "cyan"
  },
  END: {
    status: "E",
    desc: "审批结束",
    tagColor: "green"
  }
}

export function getByStatus (status) {
  return Object.values(statusEnum).find(obj => obj.status === status);
}