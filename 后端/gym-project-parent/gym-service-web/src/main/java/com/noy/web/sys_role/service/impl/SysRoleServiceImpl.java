package com.noy.web.sys_role.service.impl;

// Java 标准库
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

// MyBatis Plus 相关
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

// Apache Commons 工具类
import org.apache.commons.lang.StringUtils;

// Spring 框架相关
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

// 项目内部类
import com.noy.web.sys_menu.entity.MakeMenuTree;
import com.noy.web.sys_menu.entity.SysMenu;
import com.noy.web.sys_menu.service.SysMenuService;
import com.noy.web.sys_role.entity.RoleAssignParam;
import com.noy.web.sys_role.entity.RoleParam;
import com.noy.web.sys_role.entity.RolePermissionVo;
import com.noy.web.sys_role.entity.SysRole;
import com.noy.web.sys_role.mapper.SysRoleMapper;
import com.noy.web.sys_role.service.SysRoleService;
import com.noy.web.sys_user.entity.SysUser;
import com.noy.web.sys_user.service.SysUserService;

/**
 * 系统角色服务实现类
 * 
 * <p>提供系统角色相关的业务逻辑处理，包括：</p>
 * <ul>
 *   <li>角色信息的分页查询</li>
 *   <li>角色权限管理</li>
 *   <li>菜单树构建</li>
 *   <li>权限分配</li>
 * </ul>
 * 
 * @author noy
 * @version 1.0
 * @since 2024
 */
@Service
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRole> implements SysRoleService {

    // ==================== 依赖注入 ====================
    
    /**
     * 系统用户业务逻辑服务
     */
    @Autowired
    private SysUserService sysUserService;

    /**
     * 系统菜单业务逻辑服务
     */
    @Autowired
    private SysMenuService sysMenuService;

    // ==================== 业务方法 ====================

    /**
     * 根据条件分页查询角色列表
     * 
     * <p>支持按角色名称模糊查询</p>
     * 
     * @param param 查询参数，包含分页信息和查询条件
     * @return 分页的角色数据
     */
    @Override
    public IPage<SysRole> list(RoleParam param) {
        // 构造分页对象
        IPage<SysRole> page = new Page<>();
        page.setSize(param.getPageSize());
        page.setCurrent(param.getCurrentPage());
        
        // 构造查询条件
        QueryWrapper<SysRole> query = new QueryWrapper<>();
        if (StringUtils.isNotEmpty(param.getRoleName())) {
            query.lambda().like(SysRole::getRoleName, param.getRoleName());
        }
        
        return this.baseMapper.selectPage(page, query);
    }

    /**
     * 获取角色权限菜单树
     * 
     * <p>根据用户权限构建菜单树，并标记角色已分配的权限</p>
     * <ul>
     *   <li>超级管理员可查看所有菜单</li>
     *   <li>普通用户只能查看其权限范围内的菜单</li>
     *   <li>返回树形结构的菜单数据和已选中的权限</li>
     * </ul>
     * 
     * @param param 角色分配参数，包含用户ID和角色ID
     * @return 角色权限视图对象，包含菜单树和已选权限
     */
    @Override
    public RolePermissionVo getMenuTree(RoleAssignParam param) {
        // 查询用户信息
        SysUser user = sysUserService.getById(param.getUserId());
        List<SysMenu> list = null;
        
        if (StringUtils.isNotEmpty(user.getIsAdmin()) && user.getIsAdmin().equals("1")) {
            // 超级管理员，直接查询所有的菜单信息
            list = sysMenuService.list();
        } else {
            // 普通用户，查询其权限范围内的菜单
            list = sysMenuService.getMenuByUserId(user.getUserId());
        }
        
        // 组装树形数据
        List<SysMenu> menuList = MakeMenuTree.makeTree(list, 0L);
        
        // 查询角色原来的菜单分配信息
        List<SysMenu> roleList = sysMenuService.getMenuByRoleId(param.getRoleId());
        List<Long> ids = new ArrayList<>();
        
        Optional.ofNullable(roleList).orElse(new ArrayList<>())
                .stream()
                .filter(item -> item != null)
                .forEach(item -> {
                    ids.add(item.getMenuId());
                });
        
        // 组装数据
        RolePermissionVo vo = new RolePermissionVo();
        vo.setListmenu(menuList);
        vo.setCheckList(ids.toArray());
        
        return vo;
    }
}
