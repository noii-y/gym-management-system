package com.noy.web.member.entity;

// Java 标准库
import java.math.BigDecimal;
import java.util.Collection;

// MyBatis Plus 相关
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

// Lombok 注解
import lombok.Data;

// Spring Security 相关
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * 会员实体类
 * 
 * <p>对应数据库中的member表，实现Spring Security的UserDetails接口以支持认证</p>
 * 
 * <p>包含会员的基本信息、身体数据、会员卡信息、账户信息等</p>
 * 
 * @author noy
 * @version 1.0
 * @since 2024
 */
@Data
@TableName("member")
public class Member implements UserDetails {

    // ==================== 基本信息 ====================
    
    /**
     * 会员ID（主键，自增）
     */
    @TableId(type = IdType.AUTO)
    private Long memberId;
    
    /**
     * 角色ID（非数据库字段，用于业务逻辑）
     */
    @TableField(exist = false)
    private Long roleId;
    
    /**
     * 会员姓名
     */
    private String name;
    
    /**
     * 性别
     */
    private String sex;
    
    /**
     * 手机号码
     */
    private String phone;
    
    /**
     * 年龄
     */
    private Integer age;
    
    /**
     * 生日
     */
    private String birthday;

    // ==================== 身体数据 ====================
    
    /**
     * 身高（单位：cm）
     */
    private Integer height;
    
    /**
     * 体重（单位：kg）
     */
    private Integer weight;
    
    /**
     * 腰围（单位：cm）
     */
    private Integer waist;

    // ==================== 会员卡信息 ====================
    
    /**
     * 入会时间
     */
    private String joinTime;
    
    /**
     * 到期时间
     */
    private String endTime;
    
    /**
     * 会员卡类型
     */
    private String cardType;
    
    /**
     * 会员卡天数
     */
    private Integer cardDay;

    // ==================== 账户信息 ====================
    
    /**
     * 用户名（登录用）
     */
    private String username;
    
    /**
     * 密码
     */
    private String password;
    
    /**
     * 账户状态
     */
    private String status;
    
    /**
     * 账户余额
     */
    private BigDecimal money;
    
    /**
     * 会员卡价格
     */
    private BigDecimal price;

    // ==================== Spring Security 认证字段 ====================
    
    /**
     * 账户是否过期（true：未过期，false：已过期）
     */
    private boolean isAccountNonExpired = true;
    
    /**
     * 账户是否被锁定（true：未锁定，false：已锁定）
     */
    private boolean isAccountNonLocked = true;
    
    /**
     * 密码是否过期（true：未过期，false：已过期）
     */
    private boolean isCredentialsNonExpired = true;
    
    /**
     * 账户是否可用（true：可用，false：删除用户）
     */
    private boolean isEnabled = true;
    
    /**
     * 用户权限字段的集合（非数据库字段，用于Spring Security认证）
     */
    @TableField(exist = false)
    private Collection<? extends GrantedAuthority> authorities;
}
