// HTTP 封装：统一请求与响应拦截、错误处理
import axios, { 
  type AxiosInstance, 
  type AxiosRequestConfig, 
  type AxiosResponse, 
  type InternalAxiosRequestConfig,
  type RawAxiosRequestHeaders
} from "axios";
import { ElMessage } from 'element-plus';
import { userStore } from '@/store/user'
import router from '@/router'

// Axios 基础配置
const config = {
  // baseURL: 'http://localhost:8089', // 本地开发环境接口地址
  baseURL: process.env.BASE_API,        // 从环境变量获取API基础地址
  timeout: 10000,                       // 请求超时时间：10秒
  withCredentials: true                 // 允许携带cookie，解决session不一致问题
}

// 统一响应数据类型
export interface Result<T = any> {
  code: number;    // 响应状态码
  msg: string;     // 响应消息
  data: T;         // 响应数据
}

// 请求可选项（预留扩展）
export interface RequestOptions {
  // noAuth?: boolean // 已移除：不再注入 X-No-Auth 头或特殊处理
}

// HTTP 请求封装类
class Http {
  // Axios实例
  private instance: AxiosInstance;
  
  private showError(msg: string) {
    ElMessage.error(msg)
  }

  private mapStatusToMessage(status: number): string {
    const STATUS_MESSAGES: Record<number, string> = {
      400: '错误请求',
      401: '未授权，请重新登录',
      403: '拒绝访问',
      404: '请求错误,未找到该资源',
      405: '请求方法未允许',
      408: '请求超时',
      500: '服务器端出错',
      501: '网络未实现',
      502: '网络错误',
      503: '服务不可用',
      504: '网络超时',
      505: 'HTTP版本不支持该请求',
    }
    return STATUS_MESSAGES[status] || `连接错误${status}`
  }

  private handleAuthInvalid() {
    try { localStorage.clear() } catch (_) {}
    userStore().clearUserInfo()
    router.push({ path: '/login' })
  }
  
  // 初始化 axios 实例与拦截器
  constructor(config: AxiosRequestConfig) {
    this.instance = axios.create(config)
    // 初始化拦截器
    this.interceptors()
  }
  
  // 配置请求/响应拦截器
  private interceptors() {
    // ==================== 请求拦截器 ====================
    this.instance.interceptors.request.use(
      (config: InternalAxiosRequestConfig) => {
        // 从用户store获取token
        const store = userStore()
        const token = store.getToken;
        
        // 统一在存在 token 时添加到请求头
        if (token) {
          config.headers!['token'] = token
        }
        
        return config;
      }, 
      (error: any) => {
        // 请求错误处理
        error.data = {}
        error.data.msg = '服务器异常，请联系管理员!'
        return error;
      }
    )
    
    // ==================== 响应拦截器 ====================
    this.instance.interceptors.response.use(
      (res: AxiosResponse) => {
        // 成功响应处理
        if (res.data.code != 200) {
          const msgRaw = res.data?.msg ?? '服务器出错!'
          const lowerMsg = String(msgRaw).toLowerCase()
          // 将“token验证失败”替换为更加友好的提示
          const adjustedMsg = String(msgRaw).includes('token验证失败') ? '验证过期请重新登陆' : String(msgRaw)
          // 统一识别登录态失效相关文案
          const tokenErrorHints = ['token验证失败', '未授权', '未登录', '登录失效', '验证失败']
          const isTokenError = tokenErrorHints.some(h => lowerMsg.includes(h.toLowerCase()))

          if (isTokenError) {
            // 清理并跳转登录页，重新获取授权
            this.handleAuthInvalid()
            ElMessage.error(adjustedMsg)
            return Promise.reject(adjustedMsg)
          } else {
            ElMessage.error(adjustedMsg)
            return Promise.reject(adjustedMsg)
          }
        } else {
          return res.data
        }
      }, 
      (error) => {
        error.data = {};
        
        // 根据HTTP状态码进行错误处理
        if (error && error.response) {
          const status = error.response.status
          error.data.msg = this.mapStatusToMessage(status)
          this.showError(error.data.msg)
          if (status === 401) {
            this.handleAuthInvalid()
          }
        } else {
          // 网络连接错误
          error.data.msg = "连接到服务器失败";
          this.showError(error.data.msg)
        }
        return Promise.reject(error)
      }
    )
  }

  // ==================== RESTful API方法封装 ====================
  
  /**
   * GET请求方法
   * @param url 请求地址
   * @param params 查询参数
   * @returns Promise<T>
   */
  get<T = any>(url: string, params?: object, options?: RequestOptions): Promise<Result<T>> {
    // options 暂不使用
    return this.instance.get(url, { params })
  }
  
  /**
   * POST请求方法
   * @param url 请求地址
   * @param data 请求体数据
   * @returns Promise<T>
   */
  post<T = any>(url: string, data?: object, options?: RequestOptions): Promise<Result<T>> {
    // options 暂不使用
    return this.instance.post(url, data)
  }
  
  /**
   * PUT请求方法
   * @param url 请求地址
   * @param data 请求体数据
   * @returns Promise<T>
   */
  put<T = any>(url: string, data?: object, options?: RequestOptions): Promise<Result<T>> {
    // options 暂不使用
    return this.instance.put(url, data)
  }
  
  /**
   * DELETE请求方法
   * @param url 请求地址
   * @returns Promise<T>
   */
  delete<T = any>(url: string, options?: RequestOptions): Promise<Result<T>> {
    // options 暂不使用
    return this.instance.delete(url)
  }
  
  /**
   * 文件上传方法
   * @param url 上传地址
   * @param params 文件数据
   * @returns Promise<T>
   */
  upload<T = any>(url: string, params?: object, options?: RequestOptions): Promise<Result<T>> {
    const headers: RawAxiosRequestHeaders = {
      'Content-Type': 'multipart/form-data'
    }
    return this.instance.post(url, params, { headers })
  }
}

// 导出HTTP实例
export default new Http(config)