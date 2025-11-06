<!--
  主布局组件
  定义整个应用的基本布局结构，包含侧边栏、头部和主内容区域
-->
<template>
  <el-container class="layout">
    <!-- 侧边栏区域 -->
    <el-aside width="auto" class="asside">
      <menu-bar></menu-bar>
    </el-aside>
    
    <!-- 主要内容区域 -->
    <el-container>
      <!-- 头部区域 -->
      <el-header height="50px" class="header">
        <Header></Header>
      </el-header>
      
      <!-- 主内容区域 -->
      <el-main class="main">
        <Tabs style="padding: 0px 20px"></Tabs>
        <router-view></router-view>
      </el-main>
    </el-container>
  </el-container>
</template>

<script setup lang="ts">
// Vue3中组件引入后可以直接使用，无需注册
import Header from "@/layout/header/Header.vue"
import MenuBar from "@/layout/menu/MenuBar.vue"
import Tabs from "./tabs/Tabs.vue"
import BreadCrumb from "./header/BreadCrumb.vue"
</script>

<style scoped lang="scss">
.full-screen {
  height: 100vh; /* 视口高度 */
  width: 100vw;  /* 视口宽度 */
}

.layout {
  height: 100%;

  .asside {
    // 侧边栏背景色
    background-color: #0f3d3a;
  }

  .header {
    display: flex;
    align-items: center;
    justify-content: space-between;
    background-color: #14b7a1;
  }

  .main {
    padding-top: 1px;
    /* 增加左右内边距，抵消 el-row(:gutter) 的负边距，删除底部横向滚动条 */
    padding-left: 10px !important;
    padding-right: 10px !important;
    overflow: auto;
  }
}
</style>