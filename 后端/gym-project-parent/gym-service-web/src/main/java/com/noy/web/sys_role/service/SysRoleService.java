package com.noy.web.sys_role.service;

// MyBatis Plus 相关
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

// 项目内部实体类
import com.noy.web.sys_role.entity.RoleAssignParam;
import com.noy.web.sys_role.entity.RoleParam;
import com.noy.web.sys_role.entity.RolePermissionVo;
import com.noy.web.sys_role.entity.SysRole;

/**
 * 系统角色服务接口
 * 
 * <p>定义系统角色管理相关的业务逻辑接口，继承MyBatis Plus的IService接口</p>
 * 
 * <p>主要功能包括：</p>
 * <ul>
 *   <li>角色基础信息管理 - 增删改查操作</li>
 *   <li>角色分页查询 - 支持条件筛选的分页列表</li>
 *   <li>角色权限管理 - 菜单权限分配和回显</li>
 *   <li>角色用户关联 - 用户角色分配管理</li>
 * </ul>
 * 
 * <p>服务层职责：</p>
 * <ul>
 *   <li>处理业务逻辑，协调和组合多个Mapper操作</li>
 *   <li>提供综合性的服务，如：分页查询、复杂业务处理</li>
 *   <li>调用 Mapper 层，被 Controller 层调用</li>
 * </ul>
 * 
 * @author noy
 * @version 1.0
 * @since 2024
 */
public interface SysRoleService extends IService<SysRole> {
    
    /**
     * 分页查询角色列表
     * 
     * @param param 角色查询参数，包含分页信息和查询条件
     * @return 分页结果，包含角色列表和分页信息
     */
    IPage<SysRole> list(RoleParam param);

    /**
     * 查询角色权限数据回显
     * 
     * <p>获取角色的菜单权限树形结构，用于权限分配页面的数据回显</p>
     * 
     * @param param 角色分配参数，包含角色ID等信息
     * @return 角色权限视图对象，包含菜单树和已分配权限
     */
    RolePermissionVo getMenuTree(RoleAssignParam param);
}
