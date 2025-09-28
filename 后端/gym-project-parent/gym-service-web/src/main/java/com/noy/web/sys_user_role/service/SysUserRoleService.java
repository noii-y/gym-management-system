package com.noy.web.sys_user_role.service;

// MyBatis Plus 相关
import com.baomidou.mybatisplus.extension.service.IService;

// 项目内部实体类
import com.noy.web.sys_user_role.entity.SysUserRole;

/**
 * 系统用户角色服务接口
 * 
 * <p>定义系统用户角色关联管理相关的业务逻辑接口，继承MyBatis Plus的IService接口</p>
 * 
 * <p>主要功能包括：</p>
 * <ul>
 *   <li>用户角色关联管理 - 增删改查操作</li>
 *   <li>用户角色分配 - 为用户分配不同角色</li>
 *   <li>角色权限继承 - 用户通过角色获得权限</li>
 *   <li>权限验证支持 - 提供用户权限验证数据</li>
 * </ul>
 * 
 * @author noy
 * @version 1.0
 * @since 2024
 */
public interface SysUserRoleService extends IService<SysUserRole> {
    // 继承IService接口，提供基础的CRUD操作
    // 具体的业务逻辑方法可在实现类中添加
}
