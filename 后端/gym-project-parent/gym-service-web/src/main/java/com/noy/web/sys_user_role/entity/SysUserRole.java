package com.noy.web.sys_user_role.entity;

// MyBatis Plus 相关
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

// Lombok 注解
import lombok.Data;

/**
 * 系统用户角色关联实体类
 * 
 * <p>对应数据库中的sys_user_role表，用于管理系统用户与角色的关联关系</p>
 * 
 * <p>实现基于角色的权限控制（RBAC）中的用户-角色映射</p>
 * 
 * @author noy
 * @version 1.0
 * @since 2024
 */
@Data
@TableName("sys_user_role")
public class SysUserRole {

    /**
     * 用户角色关联ID（主键，自增）
     */
    @TableId(type = IdType.AUTO)
    private Long userRoleId;
    
    /**
     * 用户ID
     */
    private Long userId;
    
    /**
     * 角色ID
     */
    private Long roleId;
}
