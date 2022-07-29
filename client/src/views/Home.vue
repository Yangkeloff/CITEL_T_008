<template>
  <div class="home">
    <el-row :gutter="20">
      <el-col :span="7" :offset="0">
        选择筛选类型
        <el-select v-model="selectedType" placeholder="请选择">
          <el-option
            v-for="item in options"
            :key="item.value"
            :label="item.label"
            :value="item.value">
          </el-option>
        </el-select>
      </el-col>
      <el-col :span="5" v-if="selectedType !== 0">
        <el-input
          placeholder="起始值"
          v-model="submitData.start"
          type="number"
          clearable>
        </el-input>
      </el-col>
      <el-col :span="5" v-if="selectedType !== 0">
        <el-input
          placeholder="结束值"
          v-model="submitData.end"
          type="number"
          clearable>
        </el-input>
      </el-col>
      <el-col :span="2" :offset="0">
        <el-button type="primary" @click="getList">
          查询
        </el-button>
      </el-col>
      <el-col :span="2" v-if="selectedType !== 0">
        <el-button type="warning" plain @click="clear">
          清空
        </el-button>
      </el-col>
      <el-col :span="2" :offset="1">
        <el-button type="success" @click="add">
          新增
        </el-button>
      </el-col>
    </el-row>
    <el-table :data="tableData" border style="width: 100%; margin-top: 20px;">
      <el-table-column
        prop="id"
        label="人员ID">
      </el-table-column>
      <el-table-column
        prop="gender"
        label="性别"
        :formatter="genderFormatter"
        width="180">
      </el-table-column>
      <el-table-column
        prop="birth"
        label="出生年份"
        width="180">
      </el-table-column>
      <el-table-column
        prop="mileage"
        label="旅行里程">
      </el-table-column>
      <el-table-column
        prop="hour"
        label="旅行时间">
      </el-table-column>
    </el-table>
    <el-pagination
      @current-change="getList"
      :current-page.sync="curPage"
      :page-size="pageSize"
      layout="prev, pager, next, jumper"
      :total="total">
    </el-pagination>
  </div>
</template>

<script>
import api from '@/api/axios'
export default {
  name: 'HomeView',
  components: {
  },
  data() {
    return {
      pageSize: 20,
      curPage: 1,
      total: 0,
      selectedType: 0,
      options: [
        {
          label: '全部',
          value: 0
        },
        {
          label: '出生年份',
          value: 1
        },{
          label: '飞行里程',
          value: 2
        },{
          label: '飞行时间',
          value: 3
        },
      ],
      submitData: {
        start: null,
        end: null
      },
      tableData: [{
        date: '2016-05-03',
        name: '王小虎',
        address: '上海市普陀区金沙江路 1518 弄'
      }, {
        date: '2016-05-02',
        name: '王小虎',
        address: '上海市普陀区金沙江路 1518 弄'
      }, {
        date: '2016-05-04',
        name: '王小虎',
        address: '上海市普陀区金沙江路 1518 弄'
      }, {
        date: '2016-05-01',
        name: '王小虎',
        address: '上海市普陀区金沙江路 1518 弄'
      }, {
        date: '2016-05-08',
        name: '王小虎',
        address: '上海市普陀区金沙江路 1518 弄'
      }, {
        date: '2016-05-06',
        name: '王小虎',
        address: '上海市普陀区金沙江路 1518 弄'
      }, {
        date: '2016-05-07',
        name: '王小虎',
        address: '上海市普陀区金沙江路 1518 弄'
      }]
    }
  },
  mounted() {
    this.getList()
  },
  methods:{
    genderFormatter(row) {
      const { gender } = row
      return gender == 0 ? '女' : '男'
    },
    add() {

    },
    clear() {

    },
    async getList() {
      const params = {
        type: this.selectedType,
        start: this.submitData.start,
        end: this.submitData.end,
        curPage: this.curPage,
        pageSize: this.pageSize
      }
      let listRes, totalRes
      listRes = await api.findAllPerson(params)
      totalRes = await api.selectTotal(params)
      this.tableData = listRes.data
      this.total = totalRes.data
    }
  }
}
</script>
