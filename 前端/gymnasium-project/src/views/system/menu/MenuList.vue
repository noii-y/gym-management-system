<!-- 菜单管理列表页面 -->
<!-- 提供菜单的查看、新增、编辑、删除功能，支持树形结构展示 -->
<template>
<!-- 删除冗余 el-main 包裹，避免产生双滚动条 -->
      <!-- 操作按钮区域 -->
      <div style="display: flex; align-items: center">
          <!-- 新增菜单按钮 -->
          <el-button
              style="margin-bottom: 10px"
              type="primary"
              :icon="Plus"
              size="default"
              @click="addBtn"
              >新增</el-button
          >
      </div>
      <!-- 菜单数据表格，支持树形结构展示 -->
      <el-table
          :height="tableHeight"
          :data="tableList.list"
          row-key="menuId"
          :tree-props="{ children: 'children', hasChildren: 'hasChildren' }"
          border
          default-expand-all
          stripe
      >
          <!-- 菜单名称列 -->
          <el-table-column prop="title" label="菜单名称"></el-table-column>
          <!-- 菜单类型列，使用标签显示不同类型 -->
          <el-table-column prop="type" label="类型">
              <template #default="scope">
                  <el-tag v-if="scope.row.type == '0'" size="default">目录</el-tag>
                  <el-tag v-if="scope.row.type == '1'" type="success" size="default">菜单</el-tag>
                  <el-tag v-if="scope.row.type == '2'" type="danger" size="default">按钮</el-tag>
              </template>
          </el-table-column>
          <!-- 菜单图标列，动态显示图标 -->
          <el-table-column prop="icon" label="图标">
              <template #default="scope">
                  <el-icon>
                      <!-- 动态组件的方式显示图标 -->
                      <component :is="scope.row.icon"></component>
                  </el-icon>
              </template>
          </el-table-column>
          <!-- 路由名称列 -->
          <el-table-column prop="name" label="路由名称"></el-table-column>
          <!-- 路由地址列 -->
          <el-table-column prop="path" label="路由地址"></el-table-column>
          <!-- 组件路径列 -->
          <el-table-column prop="url" label="组件路径"></el-table-column>
          <!-- 权限字段列 -->
          <el-table-column prop="code" label="权限字段"></el-table-column>
          <!-- 操作列，包含编辑和删除按钮 -->
          <el-table-column label="操作" width="220" align="center">
              <template #default="scope">
                  <!-- 编辑按钮 -->
                  <el-button type="success" :icon="Edit" size="default" @click="editBtn(scope.row)"
                      >编辑</el-button
                  >
                  <!-- 删除按钮 -->
                  <el-button
                      type="danger"
                      :icon="Delete"
                      size="default"
                      @click="deleteBtn(scope.row)"
                      >删除</el-button
                  >
              </template>
          </el-table-column>
      </el-table>

      <!-- 新增、编辑菜单对话框组件 -->
      <add-menu ref="addRef" @refresh="refresh"></add-menu>

</template>

<script setup lang="ts">
/**
 * 菜单管理列表页面
 * 提供菜单的查看、新增、编辑、删除功能
 */
import AddMenu from "./AddMenu.vue";
import { Plus, Edit, Delete, Search, Close } from "@element-plus/icons-vue";
import useMenu from "@/composables/menu/useMenu";
import useMenuTable from "@/composables/menu/useMenuTable";

/**
 * 表格数据相关功能
 */
const { tableList, tableHeight, refresh, getList } = useMenuTable();

/**
 * 菜单操作相关功能（新增、编辑、删除）
 */
const { addBtn, editBtn, deleteBtn, addRef } = useMenu(getList);
</script>

<style scoped></style>