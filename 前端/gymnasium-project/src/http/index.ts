/**
 * HTTP请求封装配置
 * 基于axios封装的HTTP请求工具类，包含请求拦截器、响应拦截器和错误处理
 */
import axios, { 
  type AxiosInstance, 
  type AxiosRequestConfig, 
  type AxiosResponse, 
  type AxiosRequestHeaders, 
  type InternalAxiosRequestConfig 
} from "axios";
import { ElMessage } from 'element-plus';
import { userStore } from '@/store/user'
import router from '@/router'

/**
 * Axios请求基础配置
 */
const config = {
  // baseURL: 'http://localhost:8089', // 本地开发环境接口地址
  baseURL: process.env.BASE_API,        // 从环境变量获取API基础地址
  timeout: 10000,                       // 请求超时时间：10秒
  withCredentials: true                 // 允许携带cookie，解决session不一致问题
}

/**
 * 统一响应数据类型定义
 * @template T 响应数据的具体类型
 */
export interface Result<T = any> {
  code: number;    // 响应状态码
  msg: string;     // 响应消息
  data: T;         // 响应数据
}

/**
 * HTTP请求封装类
 * 提供GET、POST、PUT、DELETE等RESTful API方法
 */
class Http {
  // Axios实例
  private instance: AxiosInstance;
  
  /**
   * 构造函数 - 初始化axios实例和拦截器
   * @param config axios配置对象
   */
  constructor(config: AxiosRequestConfig) {
    this.instance = axios.create(config)
    // 初始化拦截器
    this.interceptors()
  }
  
  /**
   * 配置请求和响应拦截器
   */
  private interceptors() {
    // ==================== 请求拦截器 ====================
    this.instance.interceptors.request.use(
      (config: InternalAxiosRequestConfig) => {
        // 从用户store获取token
        const store = userStore()
        const token = store.getToken;
        
        // 如果token存在，添加到请求头
        if (token) {
          config.headers!['token'] = token
        }
        
        console.log('请求配置:', config)
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
          const msg = res.data.msg || '服务器出错!'
          ElMessage.error(msg)
          // 针对登录态失效的统一处理：清理本地缓存并跳转登录
          // 后端常见返回文案："token验证失败"、"未授权"、"未登录" 等
          const tokenErrorHints = ['token', '未授权', '未登录', '登录失效', '验证失败']
          const lowerMsg = String(msg).toLowerCase()
          const isTokenError = tokenErrorHints.some(h => lowerMsg.includes(h.toLowerCase()))
          if (isTokenError) {
            const store = userStore()
            // 清空用户信息与缓存，避免手动清浏览器缓存
            try {
              localStorage.clear()
            } catch (_) {}
            store.clearUserInfo()
            // 跳转到登录页
            router.push({ path: '/login' })
          }
          return Promise.reject(msg)
        } else {
          return res.data
        }
      }, 
      (error) => {
        console.log('响应错误:', error)
        error.data = {};
        
        // 根据HTTP状态码进行错误处理
        if (error && error.response) {
          switch (error.response.status) {
            case 400:
              error.data.msg = '错误请求';
              ElMessage.error(error.data.msg)
              break
            case 401:
              error.data.msg = '未授权，请重新登录';
              ElMessage.error(error.data.msg)
              // 401 统一视为登录态失效，清理并跳转登录
              try {
                localStorage.clear()
              } catch (_) {}
              userStore().clearUserInfo()
              router.push({ path: '/login' })
              break
            case 403:
              error.data.msg = '拒绝访问';
              ElMessage.error(error.data.msg)
              break
            case 404:
              error.data.msg = '请求错误,未找到该资源';
              ElMessage.error(error.data.msg)
              break
            case 405:
              error.data.msg = '请求方法未允许';
              ElMessage.error(error.data.msg)
              break
            case 408:
              error.data.msg = '请求超时';
              ElMessage.error(error.data.msg)
              break
            case 500:
              error.data.msg = '服务器端出错';
              ElMessage.error(error.data.msg)
              break
            case 501:
              error.data.msg = '网络未实现';
              ElMessage.error(error.data.msg)
              break
            case 502:
              error.data.msg = '网络错误';
              ElMessage.error(error.data.msg)
              break
            case 503:
              error.data.msg = '服务不可用';
              ElMessage.error(error.data.msg)
              break
            case 504:
              error.data.msg = '网络超时';
              ElMessage.error(error.data.msg)
              break
            case 505:
              error.data.msg = 'HTTP版本不支持该请求';
              ElMessage.error(error.data.msg)
              break
            default:
              error.data.msg = `连接错误${error.response.status}`;
              ElMessage.error(error.data.msg)
          }
        } else {
          // 网络连接错误
          error.data.msg = "连接到服务器失败";
          ElMessage.error(error.data.msg)
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
  get<T = Result>(url: string, params?: object): Promise<T> {
    return this.instance.get(url, { params })
  }
  
  /**
   * POST请求方法
   * @param url 请求地址
   * @param data 请求体数据
   * @returns Promise<T>
   */
  post<T = Result>(url: string, data?: object): Promise<T> {
    return this.instance.post(url, data)
  }
  
  /**
   * PUT请求方法
   * @param url 请求地址
   * @param data 请求体数据
   * @returns Promise<T>
   */
  put<T = Result>(url: string, data?: object): Promise<T> {
    return this.instance.put(url, data)
  }
  
  /**
   * DELETE请求方法
   * @param url 请求地址
   * @returns Promise<T>
   */
  delete<T = Result>(url: string): Promise<T> {
    return this.instance.delete(url)
  }
  
  /**
   * 文件上传方法
   * @param url 上传地址
   * @param params 文件数据
   * @returns Promise<T>
   */
  upload<T = Result>(url: string, params?: object): Promise<T> {
    return this.instance.post(url, params, {
      headers: {
        'Content-Type': 'multipart/form-data'
      }
    })
  }
}

// 导出HTTP实例
export default new Http(config)