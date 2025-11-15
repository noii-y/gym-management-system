package com.noy.web.sys_user.entity;

// Java 标准库
import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;

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
 * 系统用户实体类
 * 
 * <p>对应数据库中的sys_user表，实现Spring Security的UserDetails接口以支持认证</p>
 * 
 * <p>包含系统用户的基本信息、权限信息、状态信息等</p>
 * 
 * @author noy
 * @version 1.0
 * @since 2024
 */
@Data
@TableName("sys_user")
public class SysUser implements UserDetails {

    // ==================== 基本信息 ====================
    
    /**
     * 用户ID（主键，自增）
     */
    @TableId(type = IdType.AUTO)
    private Long userId;
    
    /**
     * 角色ID（非数据库字段，用于业务逻辑）
     */
    @TableField(exist = false)
    private Long roleId;
    
    /**
     * 用户名（登录用）
     */
    private String username;
    
    /**
     * 密码
     */
    private String password;
    
    /**
     * 手机号码
     */
    private String phone;
    
    /**
     * 邮箱地址
     */
    private String email;
    
    /**
     * 性别
     */
    private String sex;
    
    /**
     * 昵称
     */
    private String nickName;

    // ==================== 权限和状态信息 ====================
    
    /**
     * 是否为管理员
     */
    private String isAdmin;
    
    /**
     * 用户类型（1：员工，2：教练）
     */
    private String userType;

    private String coachType;
    
    /**
     * 状态（0：停用，1：启用）
     */
    private String status;
    
    /**
     * 薪资
     */
    private BigDecimal salary;

    // ==================== 时间信息 ====================
    
    /**
     * 创建时间
     */
    private Date createTime;
    
    /**
     * 更新时间
     */
    private Date updateTime;

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
