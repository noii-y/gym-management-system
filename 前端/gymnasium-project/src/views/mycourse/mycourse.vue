<!-- 我的课程管理页面组件 -->
<template>
    <el-main>
        <!-- 课程列表表格 -->
        <el-table :height="tableHeight" :data="tableData.list" border stripe>
            <!-- 课程封面列 -->
            <el-table-column prop="image" width="90" label="课程封面" align="center">
                <template #default="scope">
                    <el-image style="width: 60px; height: 60px; border-radius: 50%" :src="scope.row.image"></el-image>
                </template>
            </el-table-column>
            <!-- 课程名称列 -->
            <el-table-column prop="courseName" label="课程名称"></el-table-column>
            <!-- 课时列 -->
            <el-table-column prop="courseHour" label="课时"></el-table-column>
            <!-- 价格列 -->
            <el-table-column prop="coursePrice" label="价格"></el-table-column>
            <!-- 授课教师列 -->
            <el-table-column prop="teacherName" label="授课教师"></el-table-column>
            <!-- 操作列 -->
            <el-table-column label="操作" align="center" width="290">
                <template #default="scope">
                    <!-- 导出学生按钮 -->
                    <el-button v-permission="['sys:mycourse:export']" type="primary" :icon="Edit" size="default"
                        @click="exportBtn(scope.row)">导出学生</el-button>
                </template>
            </el-table-column>
        </el-table>
        <!-- 分页组件 -->
        <el-pagination @size-change="sizeChange" @current-change="currentChange"
            :current-page.sync="listParam.currentPage" :page-sizes="[10, 20, 40, 80, 100]"
            :page-size="listParam.pageSize" layout="total, sizes, prev, pager, next, jumper" :total="listParam.total"
            background>
        </el-pagination>
    </el-main>
</template>

<script setup lang="ts">
/**
 * 我的课程管理页面组件
 * 显示当前教师的课程列表，提供导出学生功能
 */

// 导入Element Plus图标
import { Edit } from "@element-plus/icons-vue";
// 导入组合式函数
import useMyCourseTable from "../../composables/mycourse/useMyCourseTable";
// 导入类型定义
import { type CourseType } from "@/api/course/CourseModel";
// 导入全局工具
import useInstance from "@/hooks/useInstance";

/**
 * 全局工具实例
 */
const { global } = useInstance();

/**
 * 表格相关逻辑
 * 包含列表参数、表格数据、分页等功能
 */
const {
    listParam,      // 列表查询参数
    tableData,      // 表格数据
    sizeChange,     // 分页大小改变处理函数
    currentChange,  // 当前页改变处理函数
    tableHeight,    // 表格高度
} = useMyCourseTable();

/**
 * 导出学生信息
 * @param row 课程数据行
 */
const exportBtn = (row: CourseType) => {
    // TODO: 实现导出学生功能
}
</script>
<style scoped></style>