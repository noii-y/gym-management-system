<!-- 我的课程管理页面组件 -->
<template>
    <template v-if="isMember">
        <el-tabs v-model="active">
            <el-tab-pane label="已报名" name="enrolled">
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
                    <el-table-column label="操作" align="center" width="180">
                        <template #default="scope">
                            <el-button v-permission="['sys:mycourse:export']" type="primary" :icon="Edit" size="default"
                                @click="exportBtn(scope.row)">导出学生</el-button>
                        </template>
                    </el-table-column>
                </el-table>
                <el-pagination @size-change="sizeChange" @current-change="currentChange"
                    :current-page.sync="listParam.currentPage" :page-sizes="[10, 20, 40, 80, 100]"
                    :page-size="listParam.pageSize" layout="total, sizes, prev, pager, next, jumper" :total="listParam.total"
                    background />
            </el-tab-pane>
            <el-tab-pane label="未报名" name="available">
                <el-form :model="availParam" :inline="true" size="default" ref="availFormRef">
                    <el-form-item>
                        <el-input v-model="availParam.courseName" placeholder="课程名称" />
                    </el-form-item>
                    <el-form-item>
                        <el-input v-model="availParam.teacherName" placeholder="教练" />
                    </el-form-item>
                    <el-form-item>
                        <el-button :icon="Search" @click="availSearch">搜索</el-button>
                        <el-button :icon="Close" type="danger" plain @click="availReset">重置查询条件</el-button>
                    </el-form-item>
                </el-form>
                <el-table :height="availHeight" :data="availData.list" border stripe>
                    <el-table-column prop="image" width="90" label="课程封面" align="center">
                        <template #default="scope">
                            <el-image style="width: 60px; height: 60px; border-radius: 50%" :src="scope.row.image"></el-image>
                        </template>
                    </el-table-column>
                    <el-table-column prop="courseName" label="课程名称" />
                    <el-table-column prop="courseHour" label="课时" />
                    <el-table-column prop="coursePrice" label="价格" />
                    <el-table-column prop="teacherName" label="授课教师" />
                    <el-table-column label="操作" align="center" width="180">
                        <template #default="scope">
                            <el-button type="primary" :icon="Plus" size="default" @click="join(scope.row)">报名</el-button>
                        </template>
                    </el-table-column>
                </el-table>
                <el-pagination @size-change="availSizeChange" @current-change="availCurrentChange"
                    :current-page.sync="availParam.currentPage" :page-sizes="[10, 20, 40, 80, 100]"
                    :page-size="availParam.pageSize" layout="total, sizes, prev, pager, next, jumper" :total="availParam.total"
                    background />
            </el-tab-pane>
        </el-tabs>
    </template>
    <template v-else>
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
            <el-table-column label="操作" align="center" width="180">
                <template #default="scope">
                    <el-button v-permission="['sys:mycourse:export']" type="primary" :icon="Edit" size="default"
                        @click="exportBtn(scope.row)">导出学生</el-button>
                </template>
            </el-table-column>
        </el-table>
        <el-pagination @size-change="sizeChange" @current-change="currentChange"
            :current-page.sync="listParam.currentPage" :page-sizes="[10, 20, 40, 80, 100]"
            :page-size="listParam.pageSize" layout="total, sizes, prev, pager, next, jumper" :total="listParam.total"
            background />
    </template>

</template>

<script setup lang="ts">
/**
 * 我的课程管理页面组件
 * 显示当前教师的课程列表，提供导出学生功能
 */

// 导入Element Plus图标
import { Edit, Plus, Search, Close } from "@element-plus/icons-vue";
// 导入组合式函数
import useMyCourseTable from "../../composables/mycourse/useMyCourseTable";
// 导入类型定义
import { type CourseType } from "@/api/course/CourseModel";
// 导入全局工具
import useInstance from "@/hooks/useInstance";
import useAvailableCourseTable from "@/composables/mycourse/useAvailableCourseTable";
import useCourse from "@/composables/course/useCourse";
import { userStore } from "@/store/user";

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

const {
    listParam: availParam,
    tableData: availData,
    sizeChange: availSizeChange,
    currentChange: availCurrentChange,
    tableHeight: availHeight,
    searchBtn: availSearch,
    resetBtn: availReset,
    getList: availRefresh
} = useAvailableCourseTable();

const { joinBtn } = useCourse(() => { availRefresh(); });
const isMember = userStore().getUserType === '1'
const active = isMember ? 'enrolled' : ''

const join = async (row: CourseType) => {
    await joinBtn(row)
    availRefresh()
}

/**
 * 导出学生信息
 * @param row 课程数据行
 */
const exportBtn = (row: CourseType) => {
    // TODO: 实现导出学生功能
}
</script>
<style scoped></style>