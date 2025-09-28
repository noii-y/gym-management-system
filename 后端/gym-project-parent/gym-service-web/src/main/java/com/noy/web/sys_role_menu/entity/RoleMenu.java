package com.noy.web.sys_role_menu.entity;

// MyBatis Plus 相关
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

// Lombok 注解
import lombok.Data;

/**
 * 角色菜单关联实体类
 * 
 * <p>对应数据库中的sys_role_menu表，用于管理角色与菜单的关联关系</p>
 * 
 * <p>实现基于角色的权限控制（RBAC）中的角色-菜单映射</p>
 * 
 * @author noy
 * @version 1.0
 * @since 2024
 */
@Data
@TableName("sys_role_menu")
public class RoleMenu {

    /**
     * 角色菜单关联ID（主键，自增）
     */
    @TableId(type = IdType.AUTO)
    private Long roleMenuId;
    
    /**
     * 角色ID
     */
    private Long roleId;
    
    /**
     * 菜单ID
     */
    private Long menuId;
}
