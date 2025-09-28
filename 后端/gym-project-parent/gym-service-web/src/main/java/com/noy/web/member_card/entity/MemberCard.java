package com.noy.web.member_card.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.math.BigDecimal;

/**
 * 会员卡实体类
 * 
 * <p>对应数据库中的member_card表，用于管理健身房的会员卡信息，包括：</p>
 * <ul>
 *   <li>会员卡基本信息（标题、类型、价格）</li>
 *   <li>会员卡有效期管理</li>
 *   <li>会员卡状态控制</li>
 * </ul>
 * 
 * @author noy
 * @version 1.0
 * @since 2024
 */
@Data
@TableName("member_card")
public class MemberCard {

    /**
     * 会员卡ID，主键，自增
     */
    @TableId(type = IdType.AUTO)
    private Long cardId;
    
    /**
     * 会员卡标题/名称
     */
    private String title;
    
    /**
     * 会员卡类型
     */
    private String cardType;
    
    /**
     * 会员卡价格
     */
    private BigDecimal price;
    
    /**
     * 会员卡有效天数
     */
    private Integer cardDay;
    
    /**
     * 会员卡状态（启用/禁用等）
     */
    private String status;
}
