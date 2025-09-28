package com.noy.web.sys_menu.service;

// Java 标准库
import java.util.List;

// MyBatis Plus 相关
import com.baomidou.mybatisplus.extension.service.IService;

// 项目内部实体类
import com.noy.web.sys_menu.entity.SysMenu;

/**
 * 系统菜单服务接口
 * 
 * <p>定义系统菜单管理相关的业务逻辑接口，继承MyBatis Plus的IService接口</p>
 * 
 * <p>主要功能包括：</p>
 * <ul>
 *   <li>菜单基础信息管理 - 增删改查操作</li>
 *   <li>菜单权限控制 - 基于用户、角色的菜单访问</li>
 *   <li>菜单树形结构 - 父子菜单关系管理</li>
 *   <li>动态菜单生成 - 根据权限动态构建菜单</li>
 * </ul>
 * 
 * @author noy
 * @version 1.0
 * @since 2024
 */
public interface SysMenuService extends IService<SysMenu> {

    /**
     * 获取所有父级菜单
     * 
     * @return 父级菜单列表
     */
    List<SysMenu> getParent();

    /**
     * 根据员工ID查询菜单
     * 
     * @param userId 员工ID
     * @return 该员工可访问的菜单列表
     */
    List<SysMenu> getMenuByUserId(Long userId);
    
    /**
     * 根据会员ID查询菜单
     * 
     * @param userId 会员ID
     * @return 该会员可访问的菜单列表
     */
    List<SysMenu> getMenuByMemberId(Long userId);
    
    /**
     * 根据角色ID查询菜单
     * 
     * @param roleId 角色ID
     * @return 该角色可访问的菜单列表
     */
    List<SysMenu> getMenuByRoleId(Long roleId);
}
