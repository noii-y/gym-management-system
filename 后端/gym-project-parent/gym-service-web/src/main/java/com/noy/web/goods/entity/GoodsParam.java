package com.noy.web.goods.entity;

import lombok.Data;

/**
 * 商品查询参数类
 * 
 * <p>用于封装商品列表查询的分页和过滤条件，包括：</p>
 * <ul>
 *   <li>分页参数 - 当前页码和每页大小</li>
 *   <li>过滤条件 - 商品名称模糊查询</li>
 * </ul>
 * 
 * @author noy
 * @version 1.0
 * @since 2024
 */
@Data
public class GoodsParam {
    
    /**
     * 当前页码
     * 从1开始计数
     */
    private Long currentPage;
    
    /**
     * 每页显示数量
     */
    private Long pageSize;
    
    /**
     * 商品名称过滤条件
     * 支持模糊查询
     */
    private String name;
}
