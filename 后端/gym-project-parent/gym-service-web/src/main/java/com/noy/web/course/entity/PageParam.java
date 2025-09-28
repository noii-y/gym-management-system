package com.noy.web.course.entity;

import lombok.Data;

/**
 * 课程分页查询参数类
 * 
 * <p>用于封装课程分页查询的请求参数，包括：</p>
 * <ul>
 *   <li>分页参数 - 当前页码和每页大小</li>
 *   <li>查询条件 - 用户类型和用户ID筛选</li>
 *   <li>支持分页查询和条件筛选</li>
 * </ul>
 * 
 * @author noy
 * @version 1.0
 * @since 2024
 */
@Data
public class PageParam {

    /**
     * 当前页码
     */
    private Long currentPage;
    
    /**
     * 每页大小
     */
    private Long pageSize;
    
    /**
     * 用户类型
     */
    private String userType;
    
    /**
     * 用户ID
     */
    private Long userId;
}
