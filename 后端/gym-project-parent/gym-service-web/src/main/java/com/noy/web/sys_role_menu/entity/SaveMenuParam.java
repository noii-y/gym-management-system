package com.noy.web.sys_role_menu.entity;
import java.util.List;

import lombok.Data;

/**
 * 保存菜单权限参数类
 * 
 * <p>用于封装角色菜单权限分配的请求参数，包括：</p>
 * <ul>
 *   <li>角色标识 - 指定要分配权限的角色</li>
 *   <li>菜单列表 - 要分配给角色的菜单权限集合</li>
 *   <li>支持批量权限分配操作</li>
 * </ul>
 * 
 * @author noy
 * @version 1.0
 * @since 2024
 */
@Data
public class SaveMenuParam {

    /**
     * 角色ID
     */
    private Long roleId;
    
    /**
     * 菜单ID列表
     */
    private List<Long> list;

}
