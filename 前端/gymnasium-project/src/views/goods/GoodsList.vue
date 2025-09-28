<!--
  商品管理列表页面
  提供商品信息的查询、新增、编辑、删除功能
  支持按商品名称搜索，分页显示商品数据
-->
<template>
    <el-main>
        <!-- 搜索栏 -->
        <el-form :model="listParam" :inline="true" size="default">
            <el-form-item>
                <el-input v-model="listParam.name" placeholder="请输入商品名称" />
            </el-form-item>

            <el-form-item>
                <el-button :icon="Search" @click="searchBtn">搜索</el-button>
                <el-button :icon="Close" type="danger" plain @click="resetBtn">
                    重置
                </el-button>
                <el-button v-permission="['sys:goodsList:add']" :icon="Plus" type="primary" @click="addBtn">
                    新增
                </el-button>
            </el-form-item>
        </el-form>

        <!-- 商品数据表格 -->
        <el-table :data="tableData.list" :height="tableHeight" border stripe>
            <el-table-column prop="image" label="商品图片" width="90" align="center">
                <template #default="scope">
                    <el-image style="width: 60px; height: 60px; border-radius: 50%" :src="scope.row.image" />
                </template>
            </el-table-column>

            <el-table-column prop="name" label="商品名称" />
            <el-table-column prop="price" label="商品价格" />
            <el-table-column prop="store" label="商品库存" />
            <el-table-column prop="unit" label="单位" />
            <el-table-column prop="specs" label="规格" />

            <el-table-column label="操作" width="220" align="center">
                <template #default="scope">
                    <el-button v-permission="['sys:goodsList:edit']" type="primary" :icon="Edit" size="default"
                        @click="editBtn(scope.row)">编辑</el-button>
                    <el-button v-permission="['sys:goodsList:delete']" type="danger" :icon="Delete" size="default"
                        @click="deleteBtn(scope.row)">删除</el-button>
                </template>
            </el-table-column>
        </el-table>

        <!-- 分页组件 -->
        <el-pagination background layout="total, sizes, prev, pager, next, jumper"
            :current-page.sync="listParam.currentPage" :page-size="listParam.pageSize"
            :page-sizes="[10, 20, 40, 80, 100]" :total="listParam.total" @size-change="sizeChange"
            @current-change="currentChange" />

        <!-- 新增/编辑商品弹窗 -->
        <AddGoods ref="addRef" @reFresh="reFresh" />
    </el-main>
</template>

<script setup lang="ts">
/**
 * 商品管理页面逻辑
 * 使用组合式API管理商品列表的查询、操作和状态
 */
import { Plus, Edit, Delete, Search, Close } from '@element-plus/icons-vue';
import AddGoods from './AddGoods.vue';
import useTable from '@/composables/goods/useTable';
import useGoods from '@/composables/goods/useGoods';

// 表格相关功能：查询参数、数据获取、搜索、重置、分页等
const {
    tableHeight,
    listParam,
    searchBtn,
    resetBtn,
    getList,
    tableData,
    sizeChange,
    currentChange,
    reFresh,
} = useTable();

// 商品操作功能：新增、编辑、删除
const {
    addBtn,
    editBtn,
    deleteBtn,
    addRef,
} = useGoods(getList);
</script>

<style scoped></style>