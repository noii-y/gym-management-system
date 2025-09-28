package com.noy.web.member_recharge.entity;

// Java 标准库
import java.math.BigDecimal;
import java.util.Date;

// MyBatis Plus 相关
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

// Lombok 注解
import lombok.Data;

/**
 * 会员充值记录实体类
 * 
 * <p>对应数据库中的member_recharge表，用于记录会员的充值信息</p>
 * 
 * <p>包含充值的基本信息、金额、时间等</p>
 * 
 * @author noy
 * @version 1.0
 * @since 2024
 */
@Data
@TableName("member_recharge")
public class MemberRecharge {

    /**
     * 充值记录ID（主键，自增）
     */
    @TableId(type = IdType.AUTO)
    private Long rechargeId;
    
    /**
     * 会员ID
     */
    private Long memberId;
    
    /**
     * 充值金额
     */
    private BigDecimal money;
    
    /**
     * 创建时间
     */
    private Date createTime;
    
    /**
     * 创建用户
     */
    private String createUser;

    // ==================== 关联查询字段（非数据库字段） ====================
    
    /**
     * 会员姓名（非数据库字段，用于关联查询）
     */
    @TableField(exist = false)
    private String name;
    
    /**
     * 会员用户名（非数据库字段，用于关联查询）
     */
    @TableField(exist = false)
    private String username;
}
