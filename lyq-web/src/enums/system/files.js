
const filesTypeEnum = {
  image: [".png", ".jpeg", ".jpg"],
  word: [".doc", ".docx"],
  pdf: [".pdf"]
}

const getTypeBySuffix = (suffix) => {
  for (let key in filesTypeEnum) {
    const arr = filesTypeEnum[key]
    if (arr.indexOf(suffix) > -1) {
      return key
    }
  }
}

export {
  filesTypeEnum,
  getTypeBySuffix
}