// 测试状态：开发阶段示例
import { defineStore } from 'pinia'

// 测试 Store：计数器示例
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
      this.count = count;
    }
  }
})