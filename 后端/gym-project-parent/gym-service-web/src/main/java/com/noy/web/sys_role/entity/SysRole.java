package com.noy.web.sys_role.entity;

// Java 标准库
import java.util.Date;

// MyBatis Plus 相关
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

// Lombok 注解
import lombok.Data;

/**
 * 系统角色实体类
 * 
 * <p>对应数据库中的sys_role表，用于管理系统的角色信息</p>
 * 
 * <p>包含角色的基本信息、备注、时间信息等</p>
 * 
 * @author noy
 * @version 1.0
 * @since 2024
 */
@Data
@TableName("sys_role")
public class SysRole {

    /**
     * 角色ID（主键，自增）
     */
    @TableId(type = IdType.AUTO)
    private Long roleId;
    
    /**
     * 角色名称
     */
    private String roleName;
    
    /**
     * 角色备注
     */
    private String remark;

    /**
     * 角色类型（1：员工类型 2：会员类型）
     */
    private String types;
    
    /**
     * 创建时间
     */
    private Date createTime;
    
    /**
     * 更新时间
     */
    private Date updateTime;
}
