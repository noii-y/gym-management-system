package com.noy.web.sys_user.service;

// MyBatis Plus 相关
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

// 项目内部实体类
import com.noy.web.sys_user.entity.PageParam;
import com.noy.web.sys_user.entity.SysUser;

/**
 * 系统用户服务接口
 * 
 * <p>定义系统用户管理相关的业务逻辑接口，继承MyBatis Plus的IService接口</p>
 * 
 * <p>主要功能包括：</p>
 * <ul>
 *   <li>用户基础信息管理 - 增删改查操作</li>
 *   <li>用户分页查询 - 支持条件筛选的分页列表</li>
 *   <li>用户身份验证 - 用户名密码验证</li>
 *   <li>用户权限管理 - 角色分配和权限控制</li>
 * </ul>
 * 
 * @author noy
 * @version 1.0
 * @since 2024
 */
public interface SysUserService extends IService<SysUser> {
    
    /**
     * 分页查询系统用户列表
     * 
     * @param param 分页查询参数，包含页码、页大小和查询条件
     * @return 分页结果，包含用户列表和分页信息
     */
    IPage<SysUser> list(PageParam param);
    
    /**
     * 根据用户名查询用户
     * 
     * @param username 用户名
     * @return 匹配的用户信息，如果不存在则返回null
     */
    SysUser loadUser(String username);
}
