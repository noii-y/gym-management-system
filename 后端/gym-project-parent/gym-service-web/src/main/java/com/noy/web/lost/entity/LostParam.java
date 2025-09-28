package com.noy.web.lost.entity;

import lombok.Data;

/**
 * 失物招领查询参数类
 * 
 * <p>用于封装失物招领查询的请求参数，包括：</p>
 * <ul>
 *   <li>分页参数 - 当前页码和每页大小</li>
 *   <li>查询条件 - 失物名称模糊搜索</li>
 *   <li>支持分页查询和条件筛选</li>
 * </ul>
 * 
 * @author noy
 * @version 1.0
 * @since 2024
 */
@Data
public class LostParam {

    /**
     * 当前页码
     */
    private Long currentPage;
    
    /**
     * 每页大小
     */
    private Long pageSize;
    
    /**
     * 失物名称（支持模糊搜索）
     */
    private String lostName;

}
