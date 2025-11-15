<!--
  菜单栏组件
  渲染侧边栏菜单，支持折叠功能和路由导航
-->
<template>
  <!-- 菜单Logo -->
  <menu-logo :isCollapsed="isCollapse"></menu-logo>
  
  <!-- 菜单列表 -->
  <el-menu 
    :collapse="isCollapse" 
    :default-active="activeIndex" 
    class="el-menu-vertical-demo" 
    unique-opened
    background-color="#0f3d3a" 
    router
  >
    <menu-item :menuList="menuList"></menu-item>
  </el-menu>
</template>

<script setup lang="ts">
import { ref, reactive, computed } from "vue"
import { useRoute } from "vue-router"
import MenuItem from "@/layout/menu/MenuItem.vue"
import MenuLogo from "@/layout/menu/MenuLogo.vue"
import { collapseStore } from "@/store/collapse/Index"
import { menuStore } from "@/store/menu"

// 获取store实例
const colstore = collapseStore()
const mstore = menuStore()

/**
 * 菜单折叠状态
 */
const isCollapse = computed(() => {
  return colstore.getCollapse
})

// 获取当前路由
const route = useRoute()

/**
 * 当前激活的菜单index
 */
const activeIndex = computed(() => {
  const { path } = route
  return path
})

/**
 * 树形菜单数据
 */
const menuList = computed(() => {
  return mstore.getMenuList
})
</script>

<style scoped lang="scss">
// 菜单展开时的宽度设置
.el-menu-vertical-demo:not(.el-menu--collapse) {
  width: 280px;
  min-height: 400px;
}

// 移除菜单右边框
.el-menu {
  border-right: none;
}

// 子菜单标题颜色
:deep(.el-sub-menu .el-sub-menu__title) {
  color: #f4f4f5 !important;
}

// 菜单项默认颜色
:deep(.el-menu .el-menu-item) {
  color: #cfe7e4;
}

// 菜单激活状态文字颜色
:deep(.el-menu-item.is-active) {
  color: #17c5aa !important;
}

// 当前打开菜单的所有子菜单颜色
:deep(.is-opened .el-menu-item) {
  background-color: #0a2f2c !important;
}

// 鼠标悬停菜单的颜色
:deep(.el-menu-item:hover) {
  background-color: #124e48 !important;
}
</style>