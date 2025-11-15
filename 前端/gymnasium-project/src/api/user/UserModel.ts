/**
 * 用户管理相关的类型定义
 * 包含角色类型、用户查询参数和用户数据模型
 */

/**
 * 角色类型定义
 */
export type RoleType = {
  value: string  // 角色值
  label: string  // 角色标签
}

/**
 * 角色选择列表类型
 */
export type SelectRole = {
  list: RoleType[]  // 角色列表
}

/**
 * 用户列表查询参数类型
 */
export type listParam = {
  phone: string        // 手机号
  nickName: string     // 昵称
  currentPage: number  // 当前页码
  pageSize: number     // 每页大小
  total: number        // 总数量
}

/**
 * 用户数据模型类型
 */
export type AddUserModel = {
  userId: string    // 用户ID
  type: string      // 操作类型
  roleId: string    // 角色ID
  username: string  // 用户名
  password: string  // 密码
  phone: string     // 手机号
  email: string     // 邮箱
  sex: string       // 性别
  userType: string  // 用户类型
  status: string    // 状态
  salary: string    // 薪资
  nickName: string  // 昵称
  coachType?: string
}