/**
 * 侧边栏折叠状态管理
 * 管理系统侧边栏的展开/折叠状态
 */
import { defineStore } from 'pinia'

/**
 * 侧边栏折叠状态Store
 * 用于控制左侧导航菜单的展开和折叠
 */
export const collapseStore = defineStore('collapseStore', {
  /**
   * 状态定义
   */
  state: () => {
    return {
      collapse: false  // 侧边栏是否折叠，false=展开，true=折叠
    }
  },
  
  /**
   * 计算属性
   */
  getters: {
    /**
     * 获取当前折叠状态
     * @param state 当前状态
     * @returns boolean 折叠状态
     */
    getCollapse(state) {
      return state.collapse
    }
  },
  
  /**
   * 操作方法
   */
  actions: {
    /**
     * 设置侧边栏折叠状态
     * @param collapse 折叠状态，true=折叠，false=展开
     */
    setCollapse(collapse: boolean) {
      this.collapse = collapse;
    }
  }
})