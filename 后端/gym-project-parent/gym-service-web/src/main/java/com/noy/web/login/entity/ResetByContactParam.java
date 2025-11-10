package com.noy.web.login.entity;

import lombok.Data;

/**
 * 忘记密码参数模型（方案A）
 * 会员号 + 手机号 + 图形验证码 + 新密码
 */
@Data
public class ResetByContactParam {
    private String memberId;
    private String phone;
    private String code;
    private String newPassword;
    private String confirmPassword;
    /** 用户类型：1-会员，2-员工 */
    private String userType;
}