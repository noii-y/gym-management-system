package com.noy.web.sys_role_menu.service;

// MyBatis Plus 相关
import com.baomidou.mybatisplus.extension.service.IService;

// 项目内部实体类
import com.noy.web.sys_role_menu.entity.RoleMenu;
import com.noy.web.sys_role_menu.entity.SaveMenuParam;

/**
 * 角色菜单服务接口
 * 
 * <p>定义角色菜单关联管理相关的业务逻辑接口，继承MyBatis Plus的IService接口</p>
 * 
 * <p>主要功能包括：</p>
 * <ul>
 *   <li>角色菜单关联管理 - 增删改查操作</li>
 *   <li>角色权限分配 - 为角色分配菜单权限</li>
 *   <li>权限批量操作 - 批量保存和删除权限</li>
 *   <li>权限验证支持 - 提供权限验证数据</li>
 * </ul>
 * 
 * @author noy
 * @version 1.0
 * @since 2024
 */
public interface RoleMenuService extends IService<RoleMenu> {

    /**
     * 保存角色权限
     * 
     * <p>批量保存角色的菜单权限，包括：</p>
     * <ul>
     *   <li>删除角色原有的所有菜单权限</li>
     *   <li>批量插入新的菜单权限关联</li>
     *   <li>确保数据一致性和完整性</li>
     * </ul>
     * 
     * @param param 保存菜单参数，包含角色ID和菜单ID列表
     */
    void saveMenu(SaveMenuParam param);
}
