/**
 * 标签页状态管理
 * 管理系统顶部标签页的状态和操作
 */
import { defineStore } from 'pinia'

/**
 * 标签页数据类型定义
 */
export type Tab = {
  title: string,  // 标签页标题
  path: string    // 标签页路由路径
}

/**
 * 标签页状态类型定义
 */
export type TabState = {
  tabList: Tab[]  // 标签页列表
}

/**
 * 标签页状态Store
 * 用于管理系统顶部的标签页导航
 */
export const tabStore = defineStore('tabStore', {
  /**
   * 状态定义
   */
  state: (): TabState => {
    return {
      tabList: []  // 当前打开的标签页列表
    }
  },
  
  /**
   * 计算属性
   */
  getters: {
    /**
     * 获取所有标签页
     * @param state 当前状态
     * @returns Tab[] 标签页列表
     */
    getTabs(state) {
      return state.tabList
    }
  },
  
  /**
   * 操作方法
   */
  actions: {
    /**
     * 添加新标签页
     * 如果标签页已存在则不重复添加
     * @param tab 要添加的标签页对象
     */
    addTab(tab: Tab) {
      // 判断标签页是否已经存在，避免重复添加
      if (this.tabList.some(item => item.path === tab.path)) return;
      
      // 添加新标签页到列表
      this.tabList.push(tab)
    }
  },
  
  /**
   * 持久化配置
   * 将标签页状态保存到localStorage
   */
  persist: {
    key: 'tab-store',           // 存储键名
    storage: localStorage,      // 存储方式
    pick: ['tabList']          // 只持久化 tabList 状态
  }
})