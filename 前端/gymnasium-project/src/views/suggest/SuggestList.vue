<!-- 意见反馈管理页面组件 -->
<template>
    <el-main>
        <!-- 搜索栏 -->
        <el-form :model="listParam" :inline="true" size="default">
            <el-form-item>
                <!-- 反馈标题搜索输入框 -->
                <el-input v-model="listParam.title" placeholder="请输入反馈标题"></el-input>
            </el-form-item>
            <el-form-item>
                <!-- 搜索按钮 -->
                <el-button :icon="Search" @click="searchBtn">搜索</el-button>
                <!-- 重置按钮 -->
                <el-button :icon="Close" type="danger" plain @click="resetBtn">重置</el-button>
                <!-- 新增按钮 -->
                <el-button v-permission="['sys:suggestList:add']" :icon="Plus" type="primary"
                    @click="addBtn">新增</el-button>
            </el-form-item>
        </el-form>
        <!-- 意见反馈数据表格 -->
        <el-table :height="tableHeight" :data="tableData.list" border stripe>
            <!-- 标题列 -->
            <el-table-column prop="title" label="标题"></el-table-column>
            <!-- 内容列 -->
            <el-table-column prop="content" label="内容"></el-table-column>
            <!-- 操作列 -->
            <el-table-column label="操作" align="center" width="210">
                <template #default="scope">
                    <!-- 编辑按钮 -->
                    <el-button v-permission="['sys:suggestList:edit']" type="primary" :icon="Edit" size="default"
                        @click="editBtn(scope.row)">编辑</el-button>
                    <!-- 删除按钮 -->
                    <el-button v-permission="['sys:suggestList:delete']" type="danger" :icon="Delete" size="default"
                        @click="deleteBtn(scope.row)">删除</el-button>
                </template>
            </el-table-column>
        </el-table>
        <!-- 分页组件 -->
        <el-pagination @size-change="sizeChange" @current-change="currentChange" :current-page="listParam.currentPage"
            :page-sizes="[10, 20, 40, 80, 100]" :page-size="listParam.pageSize"
            layout="total, sizes, prev, pager, next, jumper" :total="listParam.total" background>
        </el-pagination>
        <!-- 新增/编辑意见反馈弹窗 -->
        <AddSuggest ref="addRef" @reFresh="reFresh"></AddSuggest>
    </el-main>
</template>

<script setup lang="ts">
/**
 * 意见反馈管理页面组件
 * 提供意见反馈信息的查询、新增、编辑、删除功能
 */

// 导入子组件
import AddSuggest from "./AddSuggest.vue";
// 导入Element Plus图标
import { Plus, Edit, Delete, Search, Close } from "@element-plus/icons-vue";
// 导入组合式函数
import useTable from "@/composables/suggest/useTable";
import useSuggest from "@/composables/suggest/useSuggest";

/**
 * 表格相关逻辑
 * 包含搜索参数、表格数据、分页等功能
 */
const { listParam, searchBtn, resetBtn, getList, tableData, sizeChange, currentChange, tableHeight, reFresh } = useTable();

/**
 * 意见反馈业务逻辑
 * 包含新增、编辑、删除等操作
 */
const { addBtn, editBtn, deleteBtn, addRef } = useSuggest(getList);
</script>

<style scoped></style>