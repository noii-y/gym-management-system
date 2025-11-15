<!--
  标签页组件
  管理系统顶部的标签页导航，支持标签页的添加、删除和切换
-->
<template>
  <!-- 标签页容器 -->
  <el-tabs 
    v-model="activeTab" 
    @tab-click="clickBtn" 
    type="card" 
    closable 
    @tab-remove="removeTab"
  >
    <!-- 动态渲染标签页 -->
    <el-tab-pane 
      v-for="item in tabsList" 
      :key="item.path" 
      :label="item.title" 
      :name="item.path"
    ></el-tab-pane>
  </el-tabs>
</template>

<script setup lang="ts">
import { ref, computed, watch, onMounted } from "vue";
import { tabStore } from "@/store/tabs";
import { useRoute, useRouter } from "vue-router";
import type { Tab } from "@/store/tabs/index";
import type { TabPaneName } from "element-plus";

// 路由相关
const route = useRoute();
const router = useRouter();

// 标签页状态管理
const store = tabStore();

/**
 * 获取标签页列表
 */
const tabsList = computed(() => {
  return store.getTabs;
});

/**
 * 当前激活的标签页路径
 */
const activeTab = ref("");

/**
 * 设置当前激活的标签页
 * 与当前路由保持同步
 */
const setActiveTab = () => {
  activeTab.value = route.path;
};

/**
 * 删除标签页
 * @param targetName 要删除的标签页名称（路径）
 */
const removeTab = (targetName: TabPaneName) => {
  // 首页标签页不允许删除
  if (targetName === "/dashboard") return;
  
  // 获取当前标签页列表
  const tabs = tabsList.value;
  let activeName = activeTab.value;
  
  // 如果删除的是当前激活的标签页，需要切换到相邻标签页
  if (activeName === targetName) {
    tabs.forEach((tab: Tab, index: number) => {
      if (tab.path === targetName) {
        // 优先选择下一个标签页，如果没有则选择上一个
        const nextTab = tabs[index + 1] || tabs[index - 1];
        if (nextTab) {
          activeName = nextTab.path;
        }
      }
    });
  }
  
  // 更新当前激活的标签页
  activeTab.value = activeName;
  
  // 从标签页列表中移除目标标签页
  store.tabList = tabs.filter((tab: Tab) => tab.path !== targetName);
  
  // 跳转到新的激活标签页
  router.push({ path: activeName });
};

/**
 * 添加标签页
 * @param tab 要添加的标签页对象
 */
const addTab = () => {
  // 从当前路由获取path和title
  const { path, meta } = route;
  // 通过vuex设置
  const tab: Tab = {
    path: path,
    title: meta.title as string,
  };
  store.addTab(tab);
};
// 监听路由的变化
watch(
  () => route.path,
  () => {
    // 设置激活的选项卡
    setActiveTab();
    // 把当前路由添加到选项卡数据
    addTab();
  }
);
// 解决刷新数据丢失的问题
const beforeRefresh = () => {
  if (route.path != "/login") {
    window.addEventListener("beforeunload", () => {
      sessionStorage.setItem("tabsView",
        JSON.stringify(tabsList.value));
    });
    let tabSesson = sessionStorage.getItem("tabsView");
    if (tabSesson) {
      let oldTabs = JSON.parse(tabSesson);
      if (oldTabs.length > 0) {
        store.tabList = oldTabs;
      }
    }
  }
};
onMounted(() => {
  // 解决刷新选项卡数据丢失的问题
  beforeRefresh();
  // 设置激活的选项卡
  setActiveTab();
  // 把当前路由添加到选项卡数据
  addTab();
});
// 选项卡点击事件
const clickBtn = (tab: any) => {
  const { props } = tab;
  router.push({ path: props.name });
};
</script>
<style scoped lang="scss">
:deep(.el-tabs__header) {
  margin: 0px;
}

:deep(.el-tabs__item) {
  height: 26px !important;
  line-height: 26px !important;
  text-align: center !important;
  border: 1px solid #d8dce5 !important;
  margin: 0px 3px !important;
  color: #495060;
  font-size: 12px !important;
  padding: 0px 10px !important;
}

:deep(.el-tabs__nav) {
  border: none !important;
}

:deep(.is-active) {
  border-bottom: 1px solid transparent !important;
  border: 1px solid #42b983 !important;
  background-color: #42b983 !important;
  color: #fff !important;
}

:deep(.el-tabs__item:hover) {
  color: #495060 !important;
}

:deep(.is-active:hover) {
  color: #fff !important;
}

:deep(.el-tabs__nav-next) {
  line-height: 26px !important;
}

:deep(.el-tabs__nav-prev) {
  line-height: 26px !important;
}
</style>