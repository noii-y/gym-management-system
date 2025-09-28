package com.noy.web.login.entity;

import lombok.Data;

/**
 * 用户信息参数类
 * 
 * <p>用于封装获取用户信息时的请求参数，包括：</p>
 * <ul>
 *   <li>用户ID - 用于标识具体用户</li>
 *   <li>用户类型 - 区分系统用户和会员用户</li>
 * </ul>
 * 
 * @author noy
 * @version 1.0
 * @since 2024
 */
@Data
public class InfoParam {

    /**
     * 用户ID
     */
    private Long userId;
    
    /**
     * 用户类型
     * 可能的值：admin（系统用户）、member（会员用户）
     */
    private String userType;
}
