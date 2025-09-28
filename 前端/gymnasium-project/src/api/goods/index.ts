/**
 * 商品管理相关API接口
 * 包含商品的增删改查功能
 */
import type { GoodsParam, GoodsType } from "./GoodsModel"
import http from "@/http"

/**
 * 新增商品
 * @param param 商品信息对象
 * @returns Promise 新增结果
 */
export const addApi = (param: GoodsType) => {
  return http.post('/api/goods', param)
}

/**
 * 获取商品列表
 * @param param 查询参数
 * @returns Promise 商品列表数据
 */
export const listApi = (param: GoodsParam) => {
  return http.get("/api/goods/list", param)
}

/**
 * 编辑商品信息
 * @param param 商品信息对象
 * @returns Promise 编辑结果
 */
export const editApi = (param: GoodsType) => {
  return http.put('/api/goods', param)
}

/**
 * 删除商品
 * @param goodsId 商品ID
 * @returns Promise 删除结果
 */
export const deleteApi = (goodsId: string) => {
  return http.delete(`/api/goods/${goodsId}`)
}