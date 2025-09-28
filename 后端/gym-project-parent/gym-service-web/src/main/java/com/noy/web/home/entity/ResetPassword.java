package com.noy.web.home.entity;

import lombok.Data;

/**
 * 重置密码实体类
 * 
 * <p>用于封装密码重置操作的相关数据，包括：</p>
 * <ul>
 *   <li>用户身份信息（用户ID、用户类型）</li>
 *   <li>密码信息（新密码、旧密码）</li>
 * </ul>
 * 
 * @author noy
 * @version 1.0
 * @since 2024
 */
@Data
public class ResetPassword {

    /**
     * 用户ID
     */
    private Long userId;
    
    /**
     * 用户类型（会员/系统用户等）
     */
    private String userType;
    
    /**
     * 新密码
     */
    private String password;
    
    /**
     * 旧密码
     */
    private String oldPassword;
}
