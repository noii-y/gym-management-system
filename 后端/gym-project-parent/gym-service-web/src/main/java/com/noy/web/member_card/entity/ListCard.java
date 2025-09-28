package com.noy.web.member_card.entity;

import lombok.Data;

/**
 * 会员卡列表查询参数类
 * 
 * <p>用于封装会员卡列表查询的参数信息，包括：</p>
 * <ul>
 *   <li>分页参数 - 当前页码和页面大小</li>
 *   <li>查询条件 - 会员卡标题的模糊查询</li>
 *   <li>筛选功能 - 支持按标题进行筛选</li>
 * </ul>
 * 
 * @author noy
 * @version 1.0
 * @since 2024
 */
@Data
public class ListCard {
    /**
     * 当前页码
     */
    private Long currentPage;
    
    /**
     * 页面大小
     */
    private Long pageSize;
    
    /**
     * 会员卡标题（支持模糊查询）
     */
    private String title;
}
