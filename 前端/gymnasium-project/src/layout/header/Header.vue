<!--
  头部组件
  包含菜单折叠按钮、面包屑导航和登出功能
-->
<template>
  <div class="header-container">
    <!-- 菜单折叠按钮 -->
    <div class="collapse-btn">
      <el-icon @click="toggleCollapse">
        <component :is="collapseIcon"></component>
      </el-icon>
    </div>
    
    <!-- 面包屑导航 -->
    <bread-crumb></bread-crumb>
  </div>
  
  <!-- 登出组件 -->
  <LoginOut></LoginOut>
</template>

<script setup lang="ts">
import { ref, reactive, computed } from 'vue'
import { Expand, Fold } from '@element-plus/icons-vue'
import { collapseStore } from "@/store/collapse/Index"
import Collapse from './Collapse.vue'
import BreadCrumb from "./BreadCrumb.vue"

// 获取折叠状态store
const colStore = collapseStore()

/**
 * 获取当前折叠状态
 */
const isCollapse = computed(() => {
  return colStore.getCollapse
})

/**
 * 根据折叠状态显示不同图标
 */
const collapseIcon = computed(() => {
  return isCollapse.value ? 'Expand' : 'Fold'
})

/**
 * 切换菜单折叠状态
 */
const toggleCollapse = () => {
  colStore.setCollapse(!isCollapse.value)
}
</script>

<style scoped lang='scss'>
.header-container {
  display: flex;
  align-items: center;
  height: 100%;

  .collapse-btn {
    margin-right: 20px;
    font-size: 20px;
    cursor: pointer;

    &:hover {
      color: #409EFF;
    }
  }
}
</style>