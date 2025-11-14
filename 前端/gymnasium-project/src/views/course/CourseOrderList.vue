<template>
  <el-form :model="listParam" :inline="true" size="default" ref="formRef">
    <el-form-item>
      <el-input v-model="listParam.courseName" placeholder="请输入课程名称"></el-input>
    </el-form-item>
    <el-form-item>
      <el-input v-model="listParam.teacherName" placeholder="请输入教练名称"></el-input>
    </el-form-item>
    <el-form-item>
      <el-button :icon="Search" @click="searchBtn">搜索</el-button>
      <el-button :icon="Close" type="danger" plain @click="handleReset">重置查询条件</el-button>
    </el-form-item>
  </el-form>

  <el-table :height="tableHeight" :data="tableData.list" border stripe ref="tableRef">
    <el-table-column prop="image" width="90" label="课程图片" align="center">
      <template #default="scope">
        <el-image style="width: 60px; height: 60px; border-radius: 50%" :src="scope.row.image"></el-image>
      </template>
    </el-table-column>
    <el-table-column prop="courseName" label="课程名称"></el-table-column>
    <el-table-column prop="teacherName" label="教练"></el-table-column>
    <el-table-column prop="courseHour" label="课时"></el-table-column>
    <el-table-column prop="coursePrice" label="价格"></el-table-column>
    <el-table-column prop="memberId" label="学员ID"></el-table-column>
  </el-table>

  <el-pagination
    @size-change="sizeChange"
    @current-change="currentChange"
    :current-page.sync="listParam.currentPage"
    :page-sizes="[20, 40, 80, 100]"
    :page-size="listParam.pageSize"
    layout="total, sizes, prev, pager, next, jumper"
    :total="listParam.total"
    background
  />
</template>

<script setup lang="ts">
import { ref } from 'vue'
import { Search, Close } from '@element-plus/icons-vue'
import useTable from '@/composables/courseOrder/useTable'

const { listParam, searchBtn, resetBtn, tableData, getList, sizeChange, currentChange, tableHeight } = useTable()

const formRef = ref<any>(null)
const tableRef = ref<any>(null)

const handleReset = () => {
  listParam.currentPage = 1
  formRef.value?.resetFields?.()
  tableRef.value?.clearSort?.()
  tableRef.value?.clearSelection?.()
  resetBtn()
}

// 直接使用数据库中的完整图片地址，保持与课程列表一致
</script>