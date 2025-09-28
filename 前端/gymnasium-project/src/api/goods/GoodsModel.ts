/**
 * 商品管理相关的类型定义
 * 包含商品查询参数、商品数据模型和购物车相关类型
 */

/**
 * 商品列表查询参数类型
 */
export type GoodsParam = {
  currentPage: number  // 当前页码
  pageSize: number     // 每页大小
  name: string         // 商品名称
  total: number        // 总数量
}

/**
 * 商品数据模型类型
 */
export type GoodsType = {
  type: string     // 操作类型
  goodsId: string  // 商品ID
  name: string     // 商品名称
  image: string    // 商品图片
  details: string  // 商品详情
  unit: string     // 计量单位
  specs: string    // 商品规格
  price: number    // 商品价格
  store: number    // 库存数量
  num?: number     // 购买数量（可选）
}

/**
 * 购物车类型
 */
export type AddCar = {
  list: GoodsType[]  // 商品列表
}