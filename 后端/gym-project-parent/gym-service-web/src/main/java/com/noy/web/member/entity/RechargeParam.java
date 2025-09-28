package com.noy.web.member.entity;

import lombok.Data;

import java.math.BigDecimal;

/**
 * 会员充值参数类
 * 
 * <p>用于封装会员账户充值操作的请求参数，包括：</p>
 * <ul>
 *   <li>用户ID - 操作员标识</li>
 *   <li>会员ID - 被充值的会员标识</li>
 *   <li>充值金额 - 具体的充值数额</li>
 * </ul>
 * 
 * @author noy
 * @version 1.0
 * @since 2024
 */
@Data
public class RechargeParam {

    /**
     * 操作员用户ID
     * 记录是谁执行的充值操作
     */
    private Long userId;
    
    /**
     * 会员ID
     * 被充值的会员标识
     */
    private Long memberId;
    
    /**
     * 充值金额
     * 使用BigDecimal确保金额计算精度
     */
    private BigDecimal money;
}
