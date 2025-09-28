package com.noy.web.login.entity;

import lombok.Data;

/**
 * 登录结果实体类
 * 用于封装登录成功后返回给前端的数据
 */
@Data
public class LoginResult {
    /**
     * JWT令牌
     */
    private String token;
    
    /**
     * 用户ID
     */
    private Long userId;
    
    /**
     * 用户名
     */
    private String username;
    
    /**
     * 用户类型 (1: 会员, 2: 员工)
     */
    private String userType;
}