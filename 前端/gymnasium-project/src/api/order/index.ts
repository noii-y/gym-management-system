/**
 * 订单管理相关API接口
 * 包含下单、订单列表查询等功能
 */
import http from "@/http";
import { type OrderListParam } from '@/api/order/OrderModel'

/**
 * 下单
 * @param param 下单参数
 * @returns Promise 下单结果
 */
export const dowmApi = (param: any) => {
    return http.post("/api/order/down", param)
}

/**
 * 获取订单列表
 * @param Param 查询参数
 * @returns Promise 订单列表数据
 */
export const listApi = (Param: OrderListParam) => {
    return http.get("/api/order/list", Param)
}