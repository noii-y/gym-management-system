<template>
  <!-- 教练我的授课列表 -->
  <el-table :height="tableHeight" :data="tableData.list" border stripe>
    <el-table-column prop="image" width="90" label="课程封面" align="center">
      <template #default="scope">
        <el-image style="width: 60px; height: 60px; border-radius: 50%" :src="scope.row.image"></el-image>
      </template>
    </el-table-column>
    <el-table-column prop="courseName" label="课程名称"></el-table-column>
    <el-table-column prop="courseHour" label="课时"></el-table-column>
    <el-table-column prop="coursePrice" label="价格"></el-table-column>
    <el-table-column prop="teacherName" label="授课教师"></el-table-column>
    <el-table-column label="操作" align="center" width="260">
      <template #default="scope">
        <el-button v-permission="['sys:myTeaching:export']" type="primary" :icon="Notebook" size="default"
          @click="exportStudents(scope.row)">导出学生</el-button>
      </template>
    </el-table-column>
  </el-table>
  <el-pagination @size-change="sizeChange" @current-change="currentChange"
    :current-page.sync="listParam.currentPage" :page-sizes="[10, 20, 40, 80, 100]"
    :page-size="listParam.pageSize" layout="total, sizes, prev, pager, next, jumper" :total="listParam.total"
    background />
</template>

<script setup lang="ts">
import { Notebook } from '@element-plus/icons-vue'
import useMyCourseTable from '@/composables/mycourse/useMyCourseTable'
import { exportStudentsApi } from '@/api/course'
import { ElMessage } from 'element-plus'

const { listParam, tableData, sizeChange, currentChange, tableHeight } = useMyCourseTable()

// 导出学生为CSV
const exportStudents = async (row: any) => {
  try {
    const res = await exportStudentsApi(row.courseId)
    if (res && res.code === 200) {
      const csv = typeof res.data === 'string' ? res.data : ''
      if (!csv) {
        ElMessage.warning('无可导出的数据')
        return
      }
      const blob = new Blob([csv], { type: 'text/csv;charset=utf-8;' })
      const url = window.URL.createObjectURL(blob)
      const a = document.createElement('a')
      a.href = url
      a.download = `${row.courseName}-学生清单.csv`
      document.body.appendChild(a)
      a.click()
      document.body.removeChild(a)
      window.URL.revokeObjectURL(url)
    } else {
      ElMessage.error(res?.msg || '导出失败')
    }
  } catch (e: any) {
    ElMessage.error(e?.message || '导出失败')
  }
}
</script>

<style scoped></style>