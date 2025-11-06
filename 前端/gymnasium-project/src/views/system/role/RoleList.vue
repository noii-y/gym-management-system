<template>
<!-- 删除冗余 el-main 包裹，避免产生双滚动条 -->
    <!-- 搜索栏 -->
    <el-form :model="listParam" :inline="true" size="default" ref="formRef">
      <el-form-item>
        <el-input v-model="listParam.roleName" placeholder="请输入角色名称"></el-input>
      </el-form-item>
      <el-form-item>
        <el-button @click="searchBtn" :icon="Search">搜索</el-button>
          <el-button @click="handleReset" :icon="Close" type="danger" plain>重置查询条件</el-button>
        <el-button type="primary" @click="addBtn" :icon="Plus">新增</el-button>
      </el-form-item>
    </el-form>
    <!-- 表格 -->
    <el-table :height="tableHeight" :data="tableList.list" border stripe ref="tableRef">
      <el-table-column prop="roleName" label="角色名称"></el-table-column>
      <el-table-column prop="remark" label="角色描述"></el-table-column>
      <el-table-column label="操作" width="320" align="center">
        <template #default="scope">
          <el-button type="primary" :icon="Edit" size="default" @click="editBtn(scope.row)">编辑</el-button>
          <el-button type="success" :icon="Edit" size="default" @click="assignBtn(scope.row)">分配权限</el-button>
          <el-button type="danger" :icon="Delete" size="default" @click="deleteBtn(scope.row)">删除</el-button>
        </template>
      </el-table-column>
    </el-table>
    <!-- 分页 -->
    <el-pagination @size-change="sizeChange" @current-change="currentChange" :current-page="listParam.currentPage"
      :page-sizes="[10, 20, 40, 80, 100]" :page-size="listParam.pageSize"
      layout="total, sizes, prev, pager, next, jumper" :total="listParam.total" background></el-pagination>

    <!-- 新增、编辑弹框 -->
    <role-add ref="addRef" @refresh="refresh"></role-add>
    <!-- 分配权限 -->
    <AssignRole ref="assignRoleRef"></AssignRole>

</template>

<script setup lang="ts">
import RoleAdd from "./RoleAdd.vue";
import AssignRole from "../AssignRole.vue";
import { Plus, Edit, Delete, Search, Close } from "@element-plus/icons-vue";
import useTable from "@/composables/role/useTable";
import useRole from "@/composables/role/useRole";
import { ref } from "vue";

// 表格相关的业务
const { listParam, getList, searchBtn, resetBtn, tableList, sizeChange, currentChange, tableHeight, refresh } = useTable();

// 增删改查业务
const { addBtn, editBtn, deleteBtn, addRef, assignBtn, assignRoleRef } = useRole(getList);

// 引用表单和表格，用于清理校验、排序与选择
const formRef = ref<any>(null);
const tableRef = ref<any>(null);

// 更完整的重置逻辑
const handleReset = () => {
  // 分页归位到第一页
  listParam.currentPage = 1;
  // 重置表单字段与校验
  formRef.value?.resetFields?.();
  // 清理表格排序与选择
  tableRef.value?.clearSort?.();
  tableRef.value?.clearSelection?.();
  // 刷新数据
  resetBtn();
};
</script>

<style scoped></style>
