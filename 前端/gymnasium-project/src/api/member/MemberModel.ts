/**
 * 会员管理相关的数据类型定义
 */

/**
 * 会员列表查询参数类型
 */
export type MemberParam = {
  name: string,         // 会员姓名
  phone: string,        // 手机号码
  username: string,     // 用户名
  currentPage: number,  // 当前页码
  pageSize: number,     // 每页大小
  total: number,        // 总记录数
  userType: string,     // 用户类型
  memberId: string      // 会员ID
}

/**
 * 会员数据类型
 */
export type MemberType = {
  type: string,         // 操作类型
  memberId: string,     // 会员ID
  name: string,         // 姓名
  sex: string,          // 性别
  phone: string,        // 手机号码
  age: string,          // 年龄
  birthday: string,     // 生日
  height: string,       // 身高
  weight: string,       // 体重
  waist: string,        // 腰围
  joinTime: string,     // 入会时间
  endTime: string,      // 到期时间
  username: string,     // 用户名
  password: string,     // 密码
  status: string,       // 状态
  roleId: string        // 角色ID
}

/**
 * 办卡数据类型
 */
export type ApplyCard = {
  cardId: string,    // 卡片ID
  memberId: string   // 会员ID
}

/**
 * 充值数据类型
 */
export type Recharge = {
  memberId: string,  // 会员ID
  money: number,     // 充值金额
  userId: string     // 操作用户ID
}

/**
 * 会员充值查询参数类型
 */
export type MemberRecharge = {
  currentPage: number,  // 当前页码
  pageSize: number,     // 每页大小
  total: number,        // 总记录数
  memberId: string,     // 会员ID
  userType: string      // 用户类型
}
