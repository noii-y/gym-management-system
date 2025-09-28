package com.noy.web.sys_role_menu.service.impl;

// MyBatis Plus 相关
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

// Spring 框架相关
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

// 项目内部类
import com.noy.web.sys_role_menu.entity.RoleMenu;
import com.noy.web.sys_role_menu.entity.SaveMenuParam;
import com.noy.web.sys_role_menu.mapper.RoleMenuMapper;
import com.noy.web.sys_role_menu.service.RoleMenuService;

/**
 * 角色菜单关联服务实现类
 * 
 * <p>提供角色菜单关联相关的业务逻辑处理，包括：</p>
 * <ul>
 *   <li>角色权限分配</li>
 *   <li>角色菜单关联管理</li>
 *   <li>权限更新操作</li>
 * </ul>
 * 
 * @author noy
 * @version 1.0
 * @since 2024
 */
@Service
public class RoleMenuServiceImpl extends ServiceImpl<RoleMenuMapper, RoleMenu> implements RoleMenuService {

    // ==================== 业务方法 ====================

    /**
     * 保存角色菜单权限
     * 
     * <p>先删除角色原有的权限，然后重新分配新的权限</p>
     * <ul>
     *   <li>删除角色原有的菜单权限</li>
     *   <li>批量插入新的菜单权限</li>
     * </ul>
     * 
     * @param param 保存菜单参数，包含角色ID和菜单ID列表
     */
    @Transactional
    @Override
    public void saveMenu(SaveMenuParam param) {
        // 先删除角色原来的权限
        QueryWrapper<RoleMenu> query = new QueryWrapper<>();
        query.lambda().eq(RoleMenu::getRoleId, param.getRoleId());
        this.baseMapper.delete(query);
        
        // 重新保存
        this.baseMapper.saveRoleMenu(param.getRoleId(), param.getList());
    }
}
