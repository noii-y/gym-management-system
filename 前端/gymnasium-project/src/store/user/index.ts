// 用户状态：用户信息、认证与权限
import { defineStore } from "pinia";
import { getInfoApi } from "@/api/login";

// 用户信息类型
export interface UserInfo {
  userId: string      // 用户ID
  username: string    // 用户名
  name: string        // 真实姓名
  userType: string    // 用户类型
  phone: string       // 手机号
  email: string       // 邮箱
    sex: string         // 性别
  avatar?: string     // 头像URL（可选）
}

// Store 状态类型
interface UserState {
  userInfo: UserInfo    // 用户基本信息
  token: string         // 认证令牌
  codeList: string[]    // 权限代码列表
}

// 用户状态 Store
export const userStore = defineStore('userStore', {
  /**
   * 状态定义
   */
  state: (): UserState => {
    return {
      userInfo: {
        userId: '',
        username: '',
        name: '',
        userType: '',
        phone: '',
        email: '',
        sex: '',
        avatar: ''
      },
      token: '',        // JWT认证令牌
      codeList: []      // 用户权限代码列表
    }
  },
  
  /**
   * 计算属性
   */
  getters: {
    /**
     * 获取用户ID
     * @param state 当前状态
     * @returns string 用户ID
     */
    getUserId(state): string {
      return state.userInfo.userId
    },
    
    /**
     * 获取用户名
     * @param state 当前状态
     * @returns string 用户名
     */
    getUserName(state): string {
      return state.userInfo.username
    },
    
    /**
     * 获取真实姓名
     * @param state 当前状态
     * @returns string 真实姓名
     */
    getName(state): string {
      return state.userInfo.name
    },
    
    /**
     * 获取用户类型
     * @param state 当前状态
     * @returns string 用户类型
     */
    getUserType(state): string {
      return state.userInfo.userType
    },
    
    /**
     * 获取手机号
     * @param state 当前状态
     * @returns string 手机号
     */
    getPhone(state): string {
      return state.userInfo.phone
    },
    
    /**
     * 获取邮箱
     * @param state 当前状态
     * @returns string 邮箱
     */
    getEmail(state): string {
      return state.userInfo.email
    },
    
    /**
     * 获取头像URL
     * @param state 当前状态
     * @returns string | undefined 头像URL
     */
    getAvatar(state): string | undefined {
      return state.userInfo.avatar
    },
    
    /**
     * 获取认证令牌
     * @param state 当前状态
     * @returns string JWT令牌
     */
    getToken(state): string {
      return state.token
    },
    
    /**
     * 获取权限代码列表
     * @param state 当前状态
     * @returns string[] 权限代码数组
     */
    getCodeList(state): string[] {
      return state.codeList
    }
  },
  
  /**
   * 操作方法
   */
  actions: {
    /**
     * 设置用户信息
     * @param info 用户信息对象
     */
    setUserInfo(info: UserInfo) {
      this.userInfo = { ...info }
    },
    
    /**
     * 更新用户信息（部分更新）
     * @param info 要更新的用户信息字段
     */
    updateUserInfo(info: Partial<UserInfo>) {
      this.userInfo = { ...this.userInfo, ...info }
    },
    
    /**
     * 设置用户ID
     * @param userId 用户ID
     */
    setUserId(userId: string) {
      this.userInfo.userId = userId
    },
    
    /**
     * 设置认证令牌
     * @param token JWT令牌
     */
    setToken(token: string) {
      this.token = token
    },
    
    /**
     * 设置用户类型
     * @param userType 用户类型
     */
    setUserType(userType: string) {
      this.userInfo.userType = userType
    },
    
    /**
     * 设置权限代码列表
     * @param codeList 权限代码数组
     */
    setCodeList(codeList: string[]) {
      this.codeList = codeList
    },
    
    /**
     * 清除用户信息
     * 用于用户登出时重置状态
     */
    clearUserInfo() {
      this.userInfo = {
        userId: '',
        username: '',
        name: '',
        userType: '',
        phone: '',
        email: '',
        sex: '',
        avatar: ''
      }
      this.token = ''
      this.codeList = []
    },
    
    /**
     * 获取用户信息和权限
     * 从后端获取用户详细信息和权限列表
     * @returns Promise<string[]> 权限代码列表
     */
    getInfo() {
      return new Promise((resolve, reject) => {
        getInfoApi({ 
          userType: this.userInfo.userType, 
          userId: this.userInfo.userId 
        }).then((res) => {
          if (res && res.code == 200) {
            // 设置用户权限列表
            this.codeList = res.data.permissons
            // 同步基础信息：姓名与性别
            this.userInfo.name = res.data.name || this.userInfo.name
            this.userInfo.sex = res.data.sex || this.userInfo.sex
            // 可选：同步后端返回的用户ID
            if (res.data.userId) this.userInfo.userId = String(res.data.userId)
          }
          resolve(this.codeList)
        }).catch((error) => {
          reject(error)
        })
      })
    }
  },
  
  /**
   * 持久化配置
   * 将用户状态保存到localStorage
   */
  persist: true
})