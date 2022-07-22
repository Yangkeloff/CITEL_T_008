<template>
  <div class="home">
    <el-row :gutter="20">
      <el-col :span="6" :offset="0">
        <span>出生年份</span>
        <el-input
          placeholder="开始年份"
          v-model="submitData.yearStart"
          type="number"
          clearable>
        </el-input>
        <el-input
          placeholder="结束年份"
          v-model="submitData.yearEnd"
          type="number"
          clearable>
        </el-input>
      </el-col>
      <el-col :span="6" :offset="0">
        <span>飞行里程</span>
        <el-input
          placeholder="里程下限"
          v-model="submitData.mileStart"
          type="number"
          clearable>
        </el-input>
        <el-input
          placeholder="里程上限"
          v-model="submitData.mileEnd"
          type="number"
          clearable>
        </el-input>
      </el-col>
      <el-col :span="6" :offset="0">
        <span>飞行时间</span>
        <el-input
          placeholder="时间下限"
          v-model="submitData.hourStart"
          type="number"
          clearable>
        </el-input>
        <el-input
          placeholder="时间上限"
          v-model="submitData.hourEnd"
          type="number"
          clearable>
        </el-input>
      </el-col>
      <el-col :span="5" :offset="0">
        <el-row :gutter="20" style="margin-top:10px;">
          <el-button type="primary" @click="submit">
            查询
          </el-button>
        </el-row>
        <el-row :gutter="20" style="margin-top:5px;">
          <el-button type="warning" plain @click="clear">
            清空
          </el-button>
        </el-row>
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
      submitData: {
        yearStart: null,
        yearEnd: null,
        mileStart: null,
        mileEnd: null,
        hourStart: null,
        hourEnd: null
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
