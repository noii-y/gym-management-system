package com.noy.web.member_role.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.noy.web.member_role.entity.MemberRole;
import com.noy.web.member_role.mapper.MemberRoleMapper;
import com.noy.web.member_role.service.MemberRoleService;
import org.springframework.stereotype.Service;

/**
 * 会员角色服务实现类
 * 
 * <p>提供会员角色关联相关的业务逻辑处理，包括：</p>
 * <ul>
 *   <li>会员角色关系的基础CRUD操作</li>
 *   <li>会员权限管理</li>
 *   <li>角色分配和撤销</li>
 *   <li>继承MyBatis Plus的通用服务方法</li>
 * </ul>
 * 
 * @author noy
 * @version 1.0
 * @since 2024
 */
@Service
public class MemberRoleServiceImpl extends ServiceImpl<MemberRoleMapper, MemberRole> implements MemberRoleService {
}
