<!--
  失物招领管理页面
  提供失物招领信息的查询、新增、编辑、删除功能
  包含物品名称搜索、状态显示、操作按钮等
-->
<template>
  <el-main>
    <!-- 搜索栏 -->
    <!-- 失物招领信息搜索表单，支持按物品名称搜索 -->
    <el-form :model="listParam" :inline="true" size="default">
      <el-form-item>
        <!-- 物品名称搜索输入框 -->
        <el-input v-model="listParam.lostName" placeholder="请输入物品名称" />
      </el-form-item>

      <el-form-item>
        <!-- 搜索按钮 -->
        <el-button :icon="Search" @click="searchBtn">搜索</el-button>
        <!-- 重置搜索条件按钮 -->
        <el-button type="danger" plain :icon="Close" @click="resetBtn">
          重置
        </el-button>
        <!-- 新增失物招领信息按钮 -->
        <el-button v-permission="['sys:lostList:add']" :icon="Plus" type="primary" @click="addBtn">
          新增
        </el-button>
      </el-form-item>
    </el-form>

    <!-- 失物招领信息数据表格 -->
    <el-table :data="tableData.list" :height="tableHeight" border stripe>
      <!-- 物品名称列 -->
      <el-table-column prop="lostName" label="物品名称" />
      <!-- 捡到人列 -->
      <el-table-column prop="foundPerson" label="捡到人" />
      <!-- 捡到地址列 -->
      <el-table-column prop="foundAddres" label="捡到地址" />
      <!-- 捡到时间列 -->
      <el-table-column prop="foundTime" label="捡到时间" />
      <!-- 捡到人电话列 -->
      <el-table-column prop="foundPhone" label="捡到人电话" />
      <!-- 认领人列 -->
      <el-table-column prop="lostPerson" label="认领人" />

      <!-- 认领状态列，使用标签显示状态 -->
      <el-table-column prop="status" label="认领状态">
        <template #default="scope">
          <!-- 已认领状态标签 -->
          <el-tag v-if="scope.row.status === '1'" type="success" size="default">
            已认领
          </el-tag>
          <!-- 未认领状态标签 -->
          <el-tag v-if="scope.row.status === '0'" type="danger" size="default">
            未认领
          </el-tag>
        </template>
      </el-table-column>

      <!-- 操作列，包含编辑和删除按钮 -->
      <el-table-column label="操作" width="290" align="center">
        <template #default="scope">
          <!-- 编辑按钮 -->
          <el-button v-permission="['sys:lostList:edit']" type="primary" :icon="Edit" size="default"
            @click="editBtn(scope.row)">
            编辑
          </el-button>

          <!-- 删除按钮 -->
          <el-button v-permission="['sys:lostList:delete']" type="danger" :icon="Delete" size="default"
            @click="deleteBtn(scope.row)">
            删除
          </el-button>

          <!-- 认领按钮 -->
          <el-button v-permission="['sys:lostList:get']" type="success" :icon="Edit" size="default"
            @click="lostBtn(scope.row)">
            认领
          </el-button>
        </template>
      </el-table-column>
    </el-table>

    <!-- 分页组件 -->
    <el-pagination background layout="total, sizes, prev, pager, next, jumper"
      :current-page.sync="listParam.currentPage" :page-size="listParam.pageSize" :page-sizes="[10, 20, 40, 80, 100]"
      :total="listParam.total" @size-change="sizeChange" @current-change="currentChange" />

    <!-- 新增/编辑失物招领信息弹窗 -->
    <AddLost ref="addRef" @reFresh="reFresh" />

    <!-- 认领失物弹窗 -->
    <LostPerson ref="lostPersonRef" @reFresh="reFresh" />
  </el-main>
</template>

<script setup lang="ts">
/**
 * 失物招领管理页面组件
 * 提供失物招领信息的查询、新增、编辑、删除和认领功能
 */

// 导入Element Plus图标
import { Plus, Edit, Delete, Search, Close } from '@element-plus/icons-vue'
// 导入子组件
import AddLost from './AddLost.vue'
import LostPerson from './LostPerson.vue'
// 导入组合式函数
import useTable from '@/composables/lost/useTable'
import useLost from '@/composables/lost/useLost'

/**
 * 表格相关逻辑
 * 包含搜索参数、表格数据、分页等功能
 */
const {
  listParam,      // 列表查询参数
  searchBtn,      // 搜索按钮处理函数
  resetBtn,       // 重置按钮处理函数
  tableData,      // 表格数据
  getList,        // 获取列表数据函数
  sizeChange,     // 分页大小改变处理函数
  currentChange,  // 当前页改变处理函数
  tableHeight,    // 表格高度
  reFresh,        // 刷新列表函数
} = useTable()

/**
 * 失物招领业务逻辑
 * 包含新增、编辑、删除、认领等操作
 */
const {
  addBtn,         // 新增按钮处理函数
  editBtn,        // 编辑按钮处理函数
  deleteBtn,      // 删除按钮处理函数
  addRef,         // 新增/编辑弹窗引用
  lostBtn,        // 认领按钮处理函数
  lostPersonRef,  // 认领弹窗引用
} = useLost(getList)
</script>

<style scoped></style>