package com.noy.web.home.entity;

import lombok.Data;

/**
 * 首页统计数据实体类
 * 
 * <p>用于封装首页展示的各项统计数据，包括：</p>
 * <ul>
 *   <li>会员总数统计</li>
 *   <li>系统用户总数统计</li>
 *   <li>器材总数统计</li>
 *   <li>订单总数统计</li>
 * </ul>
 * 
 * @author noy
 * @version 1.0
 * @since 2024
 */
@Data
public class TotalCount {

    /**
     * 会员总数
     */
    private int memberCount;
    
    /**
     * 系统用户总数
     */
    private int userCount;
    
    /**
     * 器材总数
     */
    private int materCount;
    
    /**
     * 订单总数
     */
    private int orderCount;
}
