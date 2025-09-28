/**
 * Vue实例获取组合式函数
 * 用于获取当前组件实例和全局属性
 */
import { getCurrentInstance } from "vue"
import type { ComponentInternalInstance } from "vue"

export default function useInstance() {
  // 获取当前组件实例
  const { appContext, proxy } = getCurrentInstance() as ComponentInternalInstance
  
  // 获取全局属性配置
  const global = appContext.config.globalProperties

  return {
    proxy,  // 组件代理对象
    global  // 全局属性对象
  }
}