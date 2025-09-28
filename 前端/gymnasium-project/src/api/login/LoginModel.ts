/**
 * 登录相关数据模型定义
 * 定义登录接口所需的参数类型
 */

/**
 * 登录参数类型
 */
export type LoginParam = {
  username: string,   // 用户名
  password: string,   // 密码
  code: string,       // 验证码
  userType: string    // 用户类型
}

/**
 * 用户信息参数类型
 * 用于获取用户详细信息和菜单数据
 */
export type InfoParam = {
  userId: string,     // 用户ID
  userType: string    // 用户类型
}

