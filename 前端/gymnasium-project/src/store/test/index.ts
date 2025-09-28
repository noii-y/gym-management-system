/**
 * 测试状态管理
 * 用于开发和测试阶段的状态管理示例
 */
import { defineStore } from 'pinia'

/**
 * 测试状态Store
 * 简单的计数器状态管理，用于测试和演示
 */
export const testStore = defineStore('testStore', {
  /**
   * 状态定义
   */
  state: () => {
    return {
      count: 0  // 计数器值
    }
  },
  
  /**
   * 计算属性
   */
  getters: {
    /**
     * 获取当前计数值
     * @param state 当前状态
     * @returns number 计数值
     */
    getCount(state) {
      return state.count
    }
  },
  
  /**
   * 操作方法
   */
  actions: {
    /**
     * 设置计数值
     * @param count 新的计数值
     */
    setCount(count: number) {
      console.log('设置计数值:', count)
      this.count = count;
    }
  }
})