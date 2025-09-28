package com.noy.web.goods_order.entity;

import lombok.Data;

/**
 * 订单项实体类
 * 
 * <p>用于封装订单中的商品项信息，包括：</p>
 * <ul>
 *   <li>商品标识 - 商品ID</li>
 *   <li>数量信息 - 购买数量</li>
 *   <li>订单构成 - 作为订单的组成部分</li>
 * </ul>
 * 
 * @author noy
 * @version 1.0
 * @since 2024
 */
@Data
public class OrderItem {

    /**
     * 商品ID
     */
    private Long goodsId;
    
    /**
     * 购买数量
     */
    private Integer num;
}
