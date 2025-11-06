<!-- 会员卡类型管理页面 -->
<!-- 提供会员卡类型的查询、新增、编辑、删除功能 -->
<template>
<!-- 删除冗余 el-main 包裹，避免产生双滚动条 -->
      <!-- 搜索栏：提供标题搜索和操作按钮 -->
      <el-form :model="listParam" :inline="true" size="default" ref="formRef">
          <el-form-item>
              <!-- 标题搜索输入框 -->
              <el-input v-model="listParam.title" placeholder="请输入标题"></el-input>
          </el-form-item>
          <el-form-item>
              <!-- 搜索按钮 -->
              <el-button :icon="Search" @click="searchBtn">搜索</el-button>
              <!-- 重置搜索条件按钮 -->
              <el-button :icon="Close" type="danger" plain @click="handleReset">重置查询条件</el-button>
              <!-- 新增会员卡类型按钮（需要权限） -->
              <el-button
                  v-permission="['sys:memberRoot:add']"
                  :icon="Plus"
                  type="primary"
                  @click="addBtn"
                  >新增</el-button
              >
          </el-form-item>
      </el-form>
      
      <!-- 会员卡类型数据表格 -->
      <el-table :height="tableHeight" :data="tableList.list" border stripe ref="tableRef">
          <!-- 卡类型标题列 -->
          <el-table-column prop="title" label="标题"></el-table-column>
          <!-- 卡类型列：显示不同类型的标签 -->
          <el-table-column prop="cardType" label="类型">
              <template #default="scope">
                  <el-tag v-if="scope.row.cardType == '1'" size="default">天卡</el-tag>
                  <el-tag v-if="scope.row.cardType == '2'" type="success" size="default">周卡</el-tag>
                  <el-tag v-if="scope.row.cardType == '3'" type="danger" size="default">月卡</el-tag>
                  <el-tag v-if="scope.row.cardType == '4'" type="danger" size="default">年卡</el-tag>
              </template>
          </el-table-column>
          <!-- 有效天数列 -->
          <el-table-column prop="cardDay" label="天数"></el-table-column>
          <!-- 价格列 -->
          <el-table-column prop="price" label="价格(元)"></el-table-column>
          <!-- 状态列：显示启用/停用状态 -->
          <el-table-column prop="status" label="状态">
              <template #default="scope">
                  <el-tag v-if="scope.row.status == '1'" type="success" size="default">启用</el-tag>
                  <el-tag v-if="scope.row.status == '0'" type="danger" size="default">停用</el-tag>
              </template>
          </el-table-column>
          <!-- 操作列：编辑和删除按钮 -->
          <el-table-column label="操作" align="center" width="220">
              <template #default="scope">
                  <!-- 编辑按钮（需要权限） -->
                  <el-button
                      v-permission="['sys:memberRoot:edit']"
                      type="primary"
                      :icon="Edit"
                      size="default"
                      @click="editBtn(scope.row)"
                      >编辑</el-button
                  >
                  <!-- 删除按钮（需要权限） -->
                  <el-button
                      v-permission="['sys:memberRoot:delete']"
                      type="danger"
                      :icon="Delete"
                      size="default"
                      @click="deleteBtn(scope.row)"
                      >删除</el-button
                  >
              </template>
          </el-table-column>
      </el-table>
      
      <!-- 分页组件：支持页面大小切换和页码跳转 -->
      <el-pagination
          @size-change="sizeChange"
          @current-change="currentChange"
          :current-page.sync="listParam.currentPage"
          :page-sizes="[10, 20, 40, 80, 100]"
          :page-size="listParam.pageSize"
          layout="total, sizes, prev, pager, next, jumper"
          :total="listParam.total"
          background
      >
      </el-pagination>
      
      <!-- 新增/编辑对话框组件 -->
      <AddCard ref="addRef" @refresh="refresh"></AddCard>

</template>

<script setup lang="ts">
/**
 * 会员卡类型管理页面
 * 提供会员卡类型的查询、新增、编辑、删除功能
 */
import AddCard from "./AddCard.vue";
import { Plus, Edit, Delete, Search, Close } from "@element-plus/icons-vue";
import useMemberTable from "@/composables/member_card/useMemberTable";
import useMember from "@/composables/member_card/useMember";
import { ref } from "vue";

/**
 * 表格相关操作和数据
 * 包含列表参数、数据获取、搜索、重置、分页等功能
 */
const {
  listParam,      // 列表查询参数
  getList,        // 获取列表数据
  resetBtn,       // 重置搜索条件
  searchBtn,      // 搜索功能
  tableList,      // 表格数据
  sizeChange,     // 页面大小变化处理
  currentChange,  // 当前页变化处理
  tableHeight,    // 表格高度
  refresh         // 刷新列表
} = useMemberTable();

/**
 * 会员卡类型操作功能
 * 包含新增、编辑、删除等操作
 */
const { addBtn, editBtn, deleteBtn, addRef } = useMember(getList);

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