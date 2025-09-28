/**
 * 订单管理相关的数据类型定义
 */

/**
 * 订单列表查询参数类型
 */
export type OrderListParam = {
  name: string,         // 商品名称
  currentPage: number,  // 当前页码
  pageSize: number,     // 每页大小
  total: number,        // 总记录数
  controlName: string   // 控制名称
}
