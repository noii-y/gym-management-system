package com.noy.web.member_apply.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 会员申请实体类
 * 
 * <p>对应数据库中的member_apply表，用于管理会员的入会申请信息，包括：</p>
 * <ul>
 *   <li>申请基本信息（申请ID、会员ID）</li>
 *   <li>申请的会员卡信息（类型、天数、价格）</li>
 *   <li>申请记录信息（创建时间、创建用户）</li>
 * </ul>
 * 
 * @author noy
 * @version 1.0
 * @since 2024
 */
@Data
@TableName("member_apply")
public class MemberApply {

    /**
     * 申请ID，主键，自增
     */
    @TableId(type = IdType.AUTO)
    private Long applyId;
    
    /**
     * 会员ID
     */
    private Long memberId;
    
    /**
     * 申请的会员卡类型
     */
    private String cardType;
    
    /**
     * 申请的会员卡有效天数
     */
    private Integer cardDay;
    
    /**
     * 申请的会员卡价格
     */
    private BigDecimal price;
    
    /**
     * 申请创建时间
     */
    private Date createTime;
    
    /**
     * 申请创建用户
     */
    private String createUser;
}
