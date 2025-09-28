package com.noy.web.sys_role.entity;

import lombok.Data;

/**
 * 角色分配参数类
 * 
 * <p>用于封装用户角色分配的请求参数，包括：</p>
 * <ul>
 *   <li>用户标识 - 要分配角色的用户ID</li>
 *   <li>角色标识 - 要分配给用户的角色ID</li>
 *   <li>支持用户角色关联操作</li>
 * </ul>
 * 
 * @author noy
 * @version 1.0
 * @since 2024
 */
@Data
public class RoleAssignParam {
    
    /**
     * 用户ID
     */
    private Long userId;
    
    /**
     * 角色ID
     */
    private Long roleId;
}
