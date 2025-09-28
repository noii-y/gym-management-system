<!--
  健身器材管理页面
  提供健身器材信息的查询、新增、编辑、删除功能
  包含器材名称搜索、数量显示、操作按钮等
-->
<template>
  <el-main>
    <!-- 搜索栏 -->
    <!-- 健身器材信息搜索表单，支持按器材名称搜索 -->
    <el-form :model="listParam" :inline="true" size="default">
      <el-form-item>
        <!-- 器材名称搜索输入框 -->
        <el-input v-model="listParam.name" placeholder="请输入器材名称" />
      </el-form-item>

      <el-form-item>
        <!-- 搜索按钮 -->
        <el-button :icon="Search" @click="searchBtn">搜索</el-button>
        <!-- 重置搜索条件按钮 -->
        <el-button :icon="Close" type="danger" plain @click="resetBtn">
          重置
        </el-button>
        <!-- 新增健身器材按钮 -->
        <el-button :icon="Plus" type="primary" @click="addBtn">
          新增
        </el-button>
      </el-form-item>
    </el-form>

    <!-- 健身器材信息数据表格 -->
    <el-table :data="tableData.list" :height="tableHeight" border stripe>
      <!-- 器材名称列 -->
      <el-table-column prop="name" label="器材名称" />
      <!-- 器材数量列 -->
      <el-table-column prop="numTotal" label="器材数量" />

      <!-- 操作列，包含编辑和删除按钮 -->
      <el-table-column label="操作" width="220" align="center">
        <template #default="scope">
          <!-- 编辑按钮 -->
          <el-button type="primary" :icon="Edit" size="default" @click="editBtn(scope.row)">
            编辑
          </el-button>

          <!-- 删除按钮 -->
          <el-button type="danger" :icon="Delete" size="default" @click="deleteBtn(scope.row)">
            删除
          </el-button>
        </template>
      </el-table-column>
    </el-table>

    <!-- 分页组件 -->
    <el-pagination background layout="total, sizes, prev, pager, next, jumper"
      v-model:current-page="listParam.currentPage" v-model:page-size="listParam.pageSize"
      :page-sizes="[10, 20, 40, 80, 100]" :total="listParam.total" @size-change="sizeChange"
      @current-change="currentChange" />

    <!-- 新增/编辑健身器材信息弹窗 -->
    <AddMaterial ref="addRef" @reFresh="reFresh" />
  </el-main>
</template>

<script setup lang="ts">
/**
 * 健身器材管理页面组件
 * 提供健身器材信息的查询、新增、编辑、删除功能
 */

// 导入Element Plus图标
import { Plus, Edit, Delete, Search, Close } from '@element-plus/icons-vue';
// 导入子组件
import AddMaterial from './AddMaterial.vue';
// 导入组合式函数
import useTable from '@/composables/material/useTable';
import useMaterial from '@/composables/material/useMaterial';

/**
 * 表格相关逻辑
 * 包含搜索参数、表格数据、分页等功能
 */
const {
  listParam,      // 列表查询参数
  searchBtn,      // 搜索按钮处理函数
  resetBtn,       // 重置按钮处理函数
  tableData,      // 表格数据
  sizeChange,     // 分页大小改变处理函数
  currentChange,  // 当前页改变处理函数
  tableHeight,    // 表格高度
  reFresh,        // 刷新列表函数
  getList,        // 获取列表数据函数
} = useTable();

/**
 * 健身器材业务逻辑
 * 包含新增、编辑、删除等操作
 */
const {
  addBtn,         // 新增按钮处理函数
  editBtn,        // 编辑按钮处理函数
  deleteBtn,      // 删除按钮处理函数
  addRef,         // 新增/编辑弹窗引用
} = useMaterial(getList);
</script>

<style scoped></style>