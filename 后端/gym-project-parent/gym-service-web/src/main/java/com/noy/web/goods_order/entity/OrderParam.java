package com.noy.web.goods_order.entity;

import lombok.Data;

import java.util.List;

/**
 * 订单参数类
 * 
 * <p>用于封装商品订单创建的请求参数，包括：</p>
 * <ul>
 *   <li>用户标识 - 下单用户的ID</li>
 *   <li>订单项列表 - 包含商品信息和数量的订单明细</li>
 *   <li>支持批量商品下单操作</li>
 * </ul>
 * 
 * @author noy
 * @version 1.0
 * @since 2024
 */
@Data
public class OrderParam {

    /**
     * 用户ID
     */
    private Long userId;
    
    /**
     * 订单项列表
     */
    private List<OrderItem> orderList;
}
