package com.noy.web.equipment.entity;

import lombok.Data;

/**
 * 器材列表查询参数类
 * 
 * <p>用于封装器材列表查询的请求参数，包括：</p>
 * <ul>
 *   <li>分页参数 - 当前页码和每页大小</li>
 *   <li>查询条件 - 器材名称模糊搜索</li>
 *   <li>支持分页查询和条件筛选</li>
 * </ul>
 * 
 * @author noy
 * @version 1.0
 * @since 2024
 */
@Data
public class ListParam {
    
    /**
     * 当前页码
     */
    private Long currentPage;
    
    /**
     * 每页大小
     */
    private Long pageSize;
    
    /**
     * 器材名称（支持模糊搜索）
     */
    private String name;
}
