package com.noy.web.member.entity;

import lombok.Data;

/**
 * 会员分页查询参数类
 * 
 * <p>用于封装会员分页查询的请求参数，包括：</p>
 * <ul>
 *   <li>分页参数 - 当前页码和每页大小</li>
 *   <li>查询条件 - 姓名、手机号、用户名模糊搜索</li>
 *   <li>筛选条件 - 用户类型和会员ID筛选</li>
 *   <li>支持多条件组合查询</li>
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
     * 姓名（支持模糊搜索）
     */
    private String name;
    
    /**
     * 手机号（支持模糊搜索）
     */
    private String phone;
    
    /**
     * 用户名（支持模糊搜索）
     */
    private String username;
    
    /**
     * 用户类型
     */
    private String userType;
    
    /**
     * 会员ID
     */
    private String memberId;
}
