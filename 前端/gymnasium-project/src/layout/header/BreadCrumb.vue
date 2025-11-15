<!--
  面包屑导航组件
  根据当前路由自动生成面包屑导航路径
-->
<template>
  <!-- 面包屑导航容器 -->
  <el-breadcrumb class="bred" separator="/">
    <!-- 动态渲染面包屑项 -->
    <!-- <el-breadcrumb-item v-for="item in tabs">{{ item.meta.title }}</el-breadcrumb-item> -->
  </el-breadcrumb>
</template>

<script setup lang="ts">
import { ref, watch } from "vue";
import { useRoute } from "vue-router";
import type { Ref } from "vue";
import type { RouteLocationMatched } from "vue-router";

/**
 * 面包屑数据列表
 */
const tabs: Ref<RouteLocationMatched[]> = ref([]);

/**
 * 获取当前路由实例
 */
const route = useRoute();

/**
 * 生成面包屑导航数据
 * 根据当前路由的匹配路径生成面包屑层级
 */
const getBreadcrumb = () => {
  // 从路由匹配项中筛选出有meta和title的路由
  let matched = route.matched.filter((item) => item.meta && item.meta.title);
  
  // 判断第一个路由是否是首页，如果不是则添加首页作为根路径
  const first = matched[0];
  if (first.path !== "/dashboard") {
    matched = [{ path: "/dashboard", meta: { title: "首页" } } as any].concat(matched);
  }
  
  // 更新面包屑数据
  tabs.value = matched;
};

// 初始化面包屑
getBreadcrumb();

/**
 * 监听路由变化，自动更新面包屑
 */
watch(
  () => route.path,
  () => getBreadcrumb()
);
</script>

<style scoped lang="scss">
// 修改面包屑文字颜色为白色
:deep(.el-breadcrumb__inner) {
  color: #fff !important;
}

// 面包屑左边距
.bred {
  margin-left: 20px;
}
</style>