
const str = {
  isEmpty: (s) => {
    if (s == null || s == undefined || s == ''){
      return true
    }else{
      return false
    }
  },
  isNotEmpty: (s) => {
    return !str.isEmpty(s)
  }
}

const coll = {
  isEmpty: (list) => {
    if (list == null || list.length === 0){
      return true
    }else{
      return false
    }
  },
  isNotEmpty: (list) => {
    return !coll.isEmpty(list)
  },
  filterList: (list, keyList, filterListName, filterKey, isIn = false) => {
    return list.filter(item => {
      const ind = keyList.indexOf(item[filterKey])
      if (isIn) {
        return ind > -1
      }else{
        return ind == -1
      }
    }).map(item => {
      item = Object.assign({}, item)
      if (coll.isNotEmpty(item[filterListName])) {
        item[filterListName] = coll.filterList(item[filterListName], keyList, filterListName, filterKey)
      }
      return item
    })
  },
  distinct: (list) => {
    return list.filter(function (item, index, self) {
      return self.indexOf(item) == index;
    });
  },
  setTreeKey: (tree, childrenName = 'children') => {
    const fun = (l, k) => {
      for (let i = 0; i < l.length; i++) {
        const children = l[i][childrenName]
        let keys = k + '-' + i
        l[i].key = keys
        if (coll.isNotEmpty(children)) {
          fun(children, keys)
        }
      }
    }
    fun(tree, '0')
  },
  filterKeys: (list, keys) => {
    let arr = []
    list.forEach(obj => {
      var ks = Object.keys(obj);
      var item = {}
      ks.forEach(k => {
        if (keys.indexOf(k) < 0) {
          item[k] = obj[k]
        }
      })
      arr.push(item)
    })
    return arr
  }
}

const obj = {
  copy: (o) => {
    if (o != null) {
      return JSON.parse(JSON.stringify(o))
    }
    return null
  }
}

const myUtils = {
  str,
  coll,
  obj
}


export default myUtils