package com.noy.web.sys_role.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.noy.web.sys_role.entity.SysRole;

/**
 * 系统角色数据访问层接口
 * 
 * <p>继承MyBatis Plus的BaseMapper，提供系统角色相关的数据库操作，包括：</p>
 * <ul>
 *   <li>基础的CRUD操作（增删改查）</li>
 *   <li>角色信息的条件查询和分页查询</li>
 *   <li>角色权限管理相关操作</li>
 *   <li>批量操作支持</li>
 * </ul>
 * 
 * @author noy
 * @version 1.0
 * @since 2024
 */
public interface SysRoleMapper extends BaseMapper<SysRole> {
}
