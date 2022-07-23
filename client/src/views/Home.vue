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
      <el-col :span="5" :offset="0">
        <el-input
          placeholder="起始值"
          v-model="submitData.start"
          type="number"
          clearable>
        </el-input>
      </el-col>
      <el-col :span="5" :offset="0">
        <el-input
          placeholder="结束值"
          v-model="submitData.end"
          type="number"
          clearable>
        </el-input>
      </el-col>
      <el-col :span="2" :offset="0">
        <el-button type="primary" @click="submit">
          查询
        </el-button>
      </el-col>
      <el-col :span="2" :offset="0">
        <el-button type="warning" plain @click="clear">
          清空
        </el-button>
      </el-col>
    </el-row>
    <el-table
      :data="tableData"
      height="250"
      border
      style="width: 100%; margin-top: 20px;">
      <el-table-column
        prop="date"
        label="日期"
        width="180">
      </el-table-column>
      <el-table-column
        prop="name"
        label="姓名"
        width="180">
      </el-table-column>
      <el-table-column
        prop="address"
        label="地址">
      </el-table-column>
    </el-table>
  </div>
</template>

<script>

export default {
  name: 'HomeView',
  components: {
  },
  data() {
    return {
      selectedType: null,
      options: [
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
        type: null,
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
  methods:{
    submit() {

    },
    clear() {

    }
  }
}
</script>
