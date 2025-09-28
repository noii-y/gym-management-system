package com.noy.web.sys_role.entity;

import com.noy.web.sys_menu.entity.SysMenu;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * 角色权限视图对象
 * 
 * <p>用于封装角色权限分配相关的数据，包括：</p>
 * <ul>
 *   <li>菜单数据 - 当前登录系统用户的菜单列表</li>
 *   <li>权限检查 - 角色原来分配的菜单权限</li>
 *   <li>权限展示 - 用于前端权限分配界面展示</li>
 * </ul>
 * 
 * @author noy
 * @version 1.0
 * @since 2024
 */
@Data
public class RolePermissionVo {

    /**
     * 当前登录系统用户的菜单数据
     */
    List<SysMenu> listmenu = new ArrayList<>();
    
    /**
     * 角色原来分配的菜单
     */
    private Object[] checkList;
}
