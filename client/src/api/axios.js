import axios from 'axios'

// 全局设置
axios.defaults.timeout = 5000
axios.defaults.headers.post['Content-Type'] = 'application/jsoncharset=UTF-8'

const instance = axios.create()
instance.defaults.headers.post['Content-Type'] = 'application/jsoncharset=UTF-8'

axios.interceptors.request.use = instance.interceptors.request.use


export default {
  findAllPerson(pageSize, curPage){
    return instance.get(`/api/person/list/all?pageSize=${pageSize}&curPage=${curPage}`)
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
  },
  findPersonByBirth(data){
    let {start, end} = data
    return instance.get(`/api/person/list/birth?start=${start}&end=${end}`)
  },
  findPersonByMile(data){
    let {start, end} = data
    return instance.get(`/api/person/list/mile?start=${start}&end=${end}`)
  },
  findPersonByHour(data){
    let {start, end} = data
    return instance.get(`/api/person/list/hour?start=${start}&end=${end}`)
  }
}