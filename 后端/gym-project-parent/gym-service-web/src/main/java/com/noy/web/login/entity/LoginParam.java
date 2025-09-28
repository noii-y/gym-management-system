package com.noy.web.login.entity;

import lombok.Data;

/**
 * 登录参数实体类
 * 
 * <p>用于封装用户登录时提交的参数信息，包括：</p>
 * <ul>
 *   <li>用户认证信息（用户名、密码）</li>
 *   <li>验证码信息</li>
 *   <li>用户类型标识</li>
 * </ul>
 * 
 * @author noy
 * @version 1.0
 * @since 2024
 */
@Data
public class LoginParam {

    /**
     * 用户名
     */
    private String username;
    
    /**
     * 密码
     */
    private String password;
    
    /**
     * 验证码
     */
    private String code;
    
    /**
     * 用户类型（会员/系统用户等）
     */
    private String userType;
}
