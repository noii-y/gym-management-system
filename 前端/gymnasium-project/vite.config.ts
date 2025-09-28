import { fileURLToPath, URL } from 'node:url'

import { defineConfig } from 'vite'
import vue from '@vitejs/plugin-vue'
import vueDevTools from 'vite-plugin-vue-devtools'

/**
 * Vite 配置文件
 * 健身房管理系统前端构建配置
 * @see https://vite.dev/config/
 */
export default defineConfig({
  // 插件配置
  plugins: [
    vue(),          // Vue 3 支持
    vueDevTools(),  // Vue 开发者工具
  ],
  
  // 路径解析配置
  resolve: {
    alias: {
      '@': fileURLToPath(new URL('./src', import.meta.url))  // 设置 @ 别名指向 src 目录
    },
  },
  
  // 全局变量定义
  define: {
    'process.env': {
      'BASE_API': "http://localhost:8089"  // 后端API基础地址
    }
  }
})
