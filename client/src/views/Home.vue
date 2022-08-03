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
        <el-button type="primary" @click="queryList">
          查询
        </el-button>
      </el-col>
      <el-col :span="2" :offset="3">
        <el-button type="success" @click="add">
          新增
        </el-button>
      </el-col>
    </el-row>
    <el-tabs v-model="activeTab" type="border-card" @tab-click="tabClick" style="margin-top: 20px;">
      <el-tab-pane v-for="(item, index) in sections" :key="index" 
      :label="item" :name="index.toString()">
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
        <el-table-column label="操作" width="200">
          <template slot-scope="scope">
            <el-button
              size="mini"
              type="warning"
              @click="handleEdit(scope.$index, scope.row)">编辑</el-button>
            <el-button
              size="mini"
              type="danger"
              @click="handleDelete(scope.$index, scope.row)">删除</el-button>
          </template>
        </el-table-column>
      </el-table>
      <el-pagination
        @current-change="getList"
        :current-page.sync="curPage"
        :page-size="pageSize"
        layout="prev, pager, next, jumper"
        :total="total">
      </el-pagination>
      </el-tab-pane>
    </el-tabs>
    <el-dialog :title="dialogTitle" :visible.sync="dialogVisible">
      <el-form :model="form">
        <el-form-item label="人员ID" label-width="120px">
          <el-input v-model="form.id" type="number" :disabled="dialogType!=2"></el-input>
        </el-form-item>
        <el-form-item label="性别" label-width="120px">
          <el-select style="width:100%"  v-model="form.gender" placeholder="请选择性别">
            <el-option label="女" :value="0"></el-option>
            <el-option label="男" :value="1"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="出生年份" label-width="120px">
          <el-input v-model="form.birth" type="number"></el-input>
        </el-form-item>
        <el-form-item label="总旅行里程" label-width="120px">
          <el-input v-model="form.mileage" type="number"></el-input>
        </el-form-item>
        <el-form-item label="总旅行时间" label-width="120px">
          <el-input v-model="form.hour" type="number"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogVisible = false">取 消</el-button>
        <el-button type="primary" @click="formSubmit">确 定</el-button>
      </div>
    </el-dialog>
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
      activeTab: '',
      sections: ['全部'],
      pageSize: 20,
      curPage: 1,
      total: 0,
      selectedType: 0,
      queryType: 0,
      queryStart: null,
      queryEnd: null,
      form: {
        id: null,
        gender: null,
        birth: null,
        mileage: null,
        hour: null
      },
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
      dialogType: 0,  // 0查看, 1编辑, 2新增
      dialogVisible: false,
      dialogTitle: '',
      submitData: {
        start: null,
        end: null
      },
      tableData: []
    }
  },
  mounted() {
    this.getList()
  },
  watch: {
    selectedType(newVal) {
      if(newVal == 0) {
        this.submitData.start = null
        this.submitData.end = null
        this.sections = ['全部']
      }
    }
  },
  methods:{
    genderFormatter(row) {
      const { gender } = row
      return gender == 0 ? '女' : '男'
    },
    async formSubmit() {
      let i, res
      for (i in this.form) {
        this.form[i] = parseInt(this.form[i])
      }
      switch (this.dialogType) {
        case 1:
          res = await api.updatePerson(this.form)
          break
        case 2:
          res = await api.addPerson(this.form)
          break;
        default:
          break;
      }
      if(res.data=='success') {
        let type
        if(this.dialogType == 1){
          type = '编辑'
        } else if (this.dialogType == 2) {
          type = '新增'
        }
        this.$message({
          message: `${type}成功`,
          type: 'success'
        })
        this.dialogVisible = false
        this.selectedType = 0
        this.queryList()
      } else {
        this.$message.error(`错误：${res.data}`)
      }
    },
    add() {
      const empty = {
        id: null,
        gender: null,
        birth: null,
        mileage: null,
        hour: null
      }
      this.form = empty
      this.dialogType = 2
      this.dialogTitle = '新增人员'
      this.dialogVisible = true
    },
    async queryPerson(id) {
      let res = await api.getPerson(id)
      this.form = res.data
    },
    handleEdit(index, row) {
      this.queryPerson(row.id)
      this.dialogType = 1
      this.dialogTitle = '编辑人员'
      this.dialogVisible = true
    },
    async handleDelete(index, row) {
      const res = await api.delPerson(row.id)
      if(res.data=='success') {
        this.$message({
          message: '删除成功',
          type: 'warning'
        })
        this.selectedType = 0
        this.queryList()
      } else {
        this.$message.error(`错误：${res.data}`)
      }
    },
    queryList(){
      const range = parseInt(this.submitData.end) - parseInt(this.submitData.start)
      if(range <= 0) {
        this.$message.error(`查询范围有误，结束值：${this.submitData.end}小于起始值：${this.submitData.start}`)
        return
      }
      this.queryType = this.selectedType
      this.activeTab = '0'
      switch(this.queryType){
        case 0:
          this.sections = ['全部']
          this.queryStart = null
          this.queryEnd = null
          this.getList()
          break
        case 1:
          this.sepBirth()
          this.queryStart = this.submitData.start
          this.queryEnd = this.submitData.start
          this.getList()
          break
        case 2:
          this.sepRange(1000)
          if(range >= 1000) {
            this.queryStart = parseInt(this.submitData.start)
            this.queryEnd = parseInt(this.submitData.start) + 1000
          } else {
            this.queryStart = parseInt(this.submitData.start)
            this.queryEnd = parseInt(this.submitData.end)
          }
          this.getList()
          break
        case 3:
          this.sepRange(50)
          if(range >= 50) {
            this.queryStart = parseInt(this.submitData.start)
            this.queryEnd = parseInt(this.submitData.start) + 50
          } else {
            this.queryStart = parseInt(this.submitData.start)
            this.queryEnd = parseInt(this.submitData.end)
          }
          this.getList()
          break
      }
    },
    tabClick(e) {
      const val = e.label
      this.curPage = 1
      let pos
      switch(this.queryType){
        case 0:
          break
        case 1:
          this.queryStart = val
          this.queryEnd = val
          this.getList()
          break
        default:
          pos = val.indexOf('~')
          this.queryStart = parseInt(val.substring(0, pos))
          this.queryEnd = parseInt(val.substring(pos+1))
          this.getList()
          break
      }
    },
    sepBirth() {
      this.sections = []
      for(let i = this.submitData.start; i <= this.submitData.end; i++) {
        this.sections.push(i.toString())
      }
    },
    sepRange(span) {
      this.sections = []
      const range = parseInt(this.submitData.end) - parseInt(this.submitData.start)
      if(range < span) {
        this.sections.push(`${this.submitData.start}~${this.submitData.end}`)
      } else {
        const num = parseInt(range / span)
        let temp = parseInt(this.submitData.start)
        for(let i = 0; i < num; i++) {
          this.sections.push(`${temp}~${temp+span}`)
          temp += span
        }
        if(range % span != 0) {
          this.sections.push(`${temp}~${this.submitData.end}`)
        }
      }
    },
    async getList() {
      const params = {
        type: this.queryType,
        start: this.queryStart,
        end: this.queryEnd,
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
