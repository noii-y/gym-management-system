package com.noy.web.member.entity;

import lombok.Data;

/**
 * 充值记录查询参数类
 * 
 * <p>用于封装会员充值记录查询的请求参数，包括：</p>
 * <ul>
 *   <li>分页参数 - 当前页码和每页大小</li>
 *   <li>查询条件 - 会员ID和用户类型筛选</li>
 *   <li>支持分页查询和条件筛选</li>
 * </ul>
 * 
 * @author noy
 * @version 1.0
 * @since 2024
 */
@Data
public class RechargeParamList {

    /**
     * 当前页码
     */
    private Long currentPage;
    
    /**
     * 每页大小
     */
    private Long pageSize;
    
    /**
     * 会员ID
     */
    private Long memberId;
    
    /**
     * 用户类型
     */
    private String userType;
}
