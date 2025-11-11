/**
 * 角色管理相关的类型定义
 * 包含角色数据模型、查询参数和权限分配相关类型
 */

/**
 * 角色数据模型类型
 */
export type AddRoleModel = {
  types: string    // 角色类型（1：员工类型 2：会员类型）
  roleId: string   // 角色ID
  roleName: string // 角色名称
  remark: string   // 备注
}

/**
 * 角色列表查询参数类型
 */
export type listParam = {
  roleName: string     // 角色名称
  currentPage: number  // 当前页码
  pageSize: number     // 每页大小
  total: number        // 总数量
}

/**
 * 角色分配参数类型
 */
export type AssignParam = {
  roleId: string  // 角色ID
  userId: string  // 用户ID
}

/**
 * 保存角色分配参数类型
 */
export type SaveAssignParam = {
  roleId: string        // 角色ID
  list: Array<string>   // 权限ID列表
}