<!--
  课程管理列表页面
  提供课程信息的查询、新增、编辑、删除功能
  支持按课程名称、教练姓名搜索，分页显示课程数据
-->
<template>
<!-- 删除冗余 el-main 包裹，避免产生双滚动条 -->
        <!-- 搜索栏 -->
        <el-form :model="listParam" :inline="true" size="default" ref="formRef">
            <el-form-item>
                <el-input v-model="listParam.courseName" placeholder="课程名称" />
            </el-form-item>

            <el-form-item>
                <el-input v-model="listParam.teacherName" placeholder="教练" />
            </el-form-item>

            <el-form-item>
                <el-button :icon="Search" @click="searchBtn">搜索</el-button>
          <el-button :icon="Close" @click="handleReset">重置查询条件</el-button>
                <el-button :icon="Plus" type="primary" @click="addBtn">
                    新增
                </el-button>
            </el-form-item>
        </el-form>

        <!-- 课程数据表格 -->
        <el-table :data="tableDate.list" :height="tableHeight" border stripe ref="tableRef">
            <el-table-column prop="image" label="课程封面" width="90" align="center">
                <template #default="scope">
                    <el-image style="width: 60px; height: 60px; border-radius: 50%" :src="scope.row.image" />
                </template>
            </el-table-column>

            <el-table-column prop="courseName" label="课程名称" />
            <el-table-column prop="courseHour" label="课时" />
            <el-table-column prop="coursePrice" label="价格" />
            <el-table-column prop="teacherName" label="授课教师">
                <template #default="scope">
                    <span v-if="scope.row.teacherName">{{ scope.row.teacherName }}</span>
                    <span v-else>暂无授课人</span>
                </template>
            </el-table-column>

            <el-table-column label="操作" width="290" align="center">
                <template #default="scope">
                    <el-button type="primary" :icon="Edit" size="default"
                        @click="editBtn(scope.row)">
                        编辑
                    </el-button>

                    <el-button v-permission="['sys:courseList:delete']" type="danger" :icon="Delete" size="default"
                        @click="deleteBtn(scope.row)">
                        删除
                    </el-button>
                </template>
            </el-table-column>
        </el-table>

        <!-- 分页组件 -->
        <el-pagination background layout="total, sizes, prev, pager, next, jumper"
            :current-page.sync="listParam.currentPage" :page-size="listParam.pageSize"
            :page-sizes="[10, 20, 40, 80, 100]" :total="listParam.total" @size-change="sizeChange"
            @current-change="currentChange" />

        <!-- 新增、编辑课程弹窗 -->
        <AddCourse ref="addRef" @reFresh="reFresh" />

</template>

<script setup lang="ts">
/**
 * 课程管理页面逻辑
 * 使用组合式API管理课程列表的查询、操作和状态
 */
import { Plus, Edit, Delete, Search, Close } from '@element-plus/icons-vue';
import AddCourse from './AddCourse.vue';
import useTable from '@/composables/course/useTable';
import useCourse from '@/composables/course/useCourse';
import { ref } from 'vue';

// 表格相关功能：查询参数、数据获取、搜索、重置、分页等
const {
    listParam,
    tableDate,
    getList,
    sizeChange,
    currentChange,
    tableHeight,
    searchBtn,
    resetBtn,
    reFresh,
} = useTable();

// 课程操作功能：新增、编辑、删除
const {
    addBtn,
    editBtn,
    deleteBtn,
    addRef,
    joinBtn,
} = useCourse(getList);

// 引用表单和表格
const formRef = ref<any>(null);
const tableRef = ref<any>(null);

// 更完整的重置逻辑
const handleReset = () => {
  listParam.currentPage = 1;
  formRef.value?.resetFields?.();
  tableRef.value?.clearSort?.();
  tableRef.value?.clearSelection?.();
  resetBtn();
};
</script>

<style scoped></style>