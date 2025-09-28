package com.noy.web.login.entity;

import lombok.Data;

/**
 * 用户信息实体类
 * 
 * <p>用于封装登录用户的基本信息和权限数据，包括：</p>
 * <ul>
 *   <li>用户基本信息（用户ID、姓名）</li>
 *   <li>用户权限信息</li>
 * </ul>
 * 
 * @author noy
 * @version 1.0
 * @since 2024
 */
@Data
public class UserInfo {

    /**
     * 用户ID
     */
    private Long userId;
    
    /**
     * 用户姓名
     */
    private String name;
    
    /**
     * 用户权限列表
     */
    private Object[] permissons;
}
