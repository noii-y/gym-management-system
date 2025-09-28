/**
 * 健身房管理系统前端应用入口文件
 * 配置Vue应用实例、路由、状态管理、UI组件库等
 */

// ==================== Vue核心导入 ====================
import { createApp } from 'vue'
import App from './App.vue'
import './permission'
import router from './router/index'

// ==================== Element Plus UI库 ====================
import ElementPlus from 'element-plus'
import 'element-plus/dist/index.css'
import * as ElementPlusIconsVue from '@element-plus/icons-vue'
import zhCn from 'element-plus/es/locale/lang/zh-cn'  // 中文国际化

// ==================== Pinia状态管理 ====================
import { createPinia } from 'pinia'
import { createPersistedState } from 'pinia-plugin-persistedstate'

// ==================== 工具函数导入 ====================
import objCoppy from './utils/objCoppy'      // 对象复制工具
import myconfirm from './utils/myconfirm'    // 信息确认框工具
import * as echarts from 'echarts'           // ECharts图表库

// ==================== Pinia配置 ====================
const pinia = createPinia()
// 配置持久化插件
pinia.use(createPersistedState({
  storage: localStorage,  // 使用 localStorage 存储
  auto: true,            // 自动持久化所有 store
}))

// ==================== 创建Vue应用实例 ====================
const app = createApp(App)

// ==================== 挂载全局属性 ====================
app.config.globalProperties.$objCoppy = objCoppy      // 对象复制工具
app.config.globalProperties.$myconfirm = myconfirm    // 确认框工具
app.config.globalProperties.$echarts = echarts        // ECharts图表库

// ==================== 注册插件 ====================
app.use(router)                    // 路由插件
  .use(ElementPlus, {              // Element Plus UI库
    locale: zhCn,                  // 设置中文语言
  })
  .use(pinia)                      // Pinia状态管理

// ==================== 全局注册Element Plus图标组件 ====================
for (const [key, component] of Object.entries(ElementPlusIconsVue)) {
  app.component(key, component)
}

// ==================== 挂载应用到DOM ====================
app.mount('#app')
