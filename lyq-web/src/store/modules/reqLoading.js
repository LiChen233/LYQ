import Vue from 'vue'

const reqLoading = {
  state: {
    map: {}
  },
  mutations: {
    startLoad: (state, key) => {
      Vue.set(state.map, key, true)
    },
    stopLoad: (state, key) => {
      Vue.set(state.map, key, null)
    },
  },
  getters: {
    getLoad: (state) => (key) => {
      const b = state.map[key]
      const r = (b == null || b == undefined) ? false : b
      return r;
    }
  }
}

export default reqLoading