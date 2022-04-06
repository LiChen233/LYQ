
export const resultEnum = {
  YES: {
    result: "Y",
    desc: "同意",
    tagColor: "green"
  },
  NO: {
    result: "N",
    desc: "驳回",
    tagColor: "red"
  }
}

export function getByResult (result) {
  return Object.values(resultEnum).find(obj => obj.result === result);
}