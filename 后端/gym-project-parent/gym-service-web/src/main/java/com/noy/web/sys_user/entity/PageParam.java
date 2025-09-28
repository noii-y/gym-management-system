package com.noy.web.sys_user.entity;

import lombok.Data;

/**
 * 系统用户分页查询参数类
 * 
 * <p>用于封装系统用户列表查询的分页和过滤条件，包括：</p>
 * <ul>
 *   <li>分页参数 - 当前页码和每页大小</li>
 *   <li>过滤条件 - 手机号和昵称模糊查询</li>
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
     * 从1开始计数
     */
    private Long currentPage;
    
    /**
     * 每页显示数量
     */
    private Long pageSize;
    
    /**
     * 手机号过滤条件
     * 支持模糊查询
     */
    private String phone;
    
    /**
     * 昵称过滤条件
     * 支持模糊查询
     */
    private String nickName;
}
