import axios from 'axios'

// 全局设置
axios.defaults.timeout = 5000
axios.defaults.headers.post['Content-Type'] = 'application/jsoncharset=UTF-8'

const instance = axios.create()
instance.defaults.headers.post['Content-Type'] = 'application/jsoncharset=UTF-8'

axios.interceptors.request.use = instance.interceptors.request.use


export default {
  findAllPerson(data){
    console.log(data)
    let {type, start, end, curPage, pageSize} = data
    if(start==null || end==null) {
      start = 0
      end = 99999999
    }
    start = parseInt(start)
    end = parseInt(end)
    return instance.get(`/api/person/list/all?type=${type}&start=${start}&end=${end}&curPage=${curPage}&pageSize=${pageSize}`)
  },
  selectTotal(data) {
    let {type, start, end} = data
    if(start==null || end==null) {
      start = 0
      end = 99999999
    }
    start = parseInt(start)
    end = parseInt(end)
    return instance.get(`/api/person/list/total?type=${type}&start=${start}&end=${end}`)
  },
  addPerson(data){
    return instance.post(`/api/person/add`, data)
  },
  updatePerson(data){
    let {id, gender, birth, mileage, hour} = data
    return instance.put(`/api/person/update?id=${id}&gender=${gender}&birth=${birth}&mileage=${mileage}&hour=${hour}`)
  },
  delPerson(data){
    let {id} = data
    return instance.delete(`/api/person/del?id=${id}`)
  }
}