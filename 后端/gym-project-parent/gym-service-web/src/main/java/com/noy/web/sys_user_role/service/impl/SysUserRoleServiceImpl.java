package com.noy.web.sys_user_role.service.impl;

// MyBatis Plus 相关
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

// Spring 框架相关
import org.springframework.stereotype.Service;

// 项目内部类
import com.noy.web.sys_user_role.entity.SysUserRole;
import com.noy.web.sys_user_role.mapper.SysUserRoleMapper;
import com.noy.web.sys_user_role.service.SysUserRoleService;

/**
 * 系统用户角色关联服务实现类
 * 
 * <p>提供系统用户角色关联相关的业务逻辑处理，包括：</p>
 * <ul>
 *   <li>用户角色关联管理</li>
 *   <li>用户权限分配</li>
 *   <li>角色用户关联操作</li>
 * </ul>
 * 
 * @author noy
 * @version 1.0
 * @since 2024
 */
@Service
public class SysUserRoleServiceImpl extends ServiceImpl<SysUserRoleMapper, SysUserRole> implements SysUserRoleService {

    // ==================== 业务方法 ====================
    
    // 继承MyBatis Plus的基础CRUD操作，无需额外实现

}
