package com.noy.web.sys_role.entity;

import lombok.Data;

/**
 * 角色查询参数类
 * 
 * <p>用于封装角色查询的请求参数，包括：</p>
 * <ul>
 *   <li>分页参数 - 当前页码和每页大小</li>
 *   <li>查询条件 - 角色名称模糊搜索</li>
 *   <li>支持分页查询和条件筛选</li>
 * </ul>
 * 
 * @author noy
 * @version 1.0
 * @since 2024
 */
@Data
public class RoleParam {

    /**
     * 当前页码
     */
    private Long currentPage;
    
    /**
     * 页面数据容量
     */
    private Long pageSize;
    
    /**
     * 角色名称（支持模糊搜索）
     */
    private String roleName;
}
