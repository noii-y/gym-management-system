package com.noy.web.sys_menu.service.impl;

// Java 标准库
import java.util.Arrays;
import java.util.List;

// MyBatis Plus 相关
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

// Spring 框架相关
import org.springframework.stereotype.Service;

// 项目内部类
import com.noy.web.sys_menu.entity.MakeMenuTree;
import com.noy.web.sys_menu.entity.SysMenu;
import com.noy.web.sys_menu.mapper.SysMenuMapper;
import com.noy.web.sys_menu.service.SysMenuService;

/**
 * 系统菜单服务实现类
 * 
 * <p>提供系统菜单相关的业务逻辑处理，包括：</p>
 * <ul>
 *   <li>菜单树形结构构建</li>
 *   <li>用户权限菜单查询</li>
 *   <li>角色权限菜单查询</li>
 *   <li>会员权限菜单查询</li>
 * </ul>
 * 
 * @author noy
 * @version 1.0
 * @since 2024
 */
@Service
public class SysMenuServiceImpl extends ServiceImpl<SysMenuMapper, SysMenu> implements SysMenuService {

    // ==================== 业务方法 ====================

    /**
     * 获取父级菜单树形结构
     * 
     * <p>查询目录和菜单类型的数据，构建树形结构</p>
     * <ul>
     *   <li>查询类型为目录(0)和菜单(1)的数据</li>
     *   <li>按排序号升序排列</li>
     *   <li>添加顶级菜单节点</li>
     *   <li>构建树形结构</li>
     * </ul>
     * 
     * @return 树形结构的菜单列表
     */
    @Override
    public List<SysMenu> getParent() {
        // 查询目录和菜单
        String[] type = {"0", "1"};
        List<String> strings = Arrays.asList(type);
        
        // 构造查询条件
        QueryWrapper<SysMenu> query = new QueryWrapper<>();
        query.lambda().in(SysMenu::getType, strings).orderByAsc(SysMenu::getOrderNum);
        List<SysMenu> menus = this.baseMapper.selectList(query);
        
        // 组装顶级菜单
        SysMenu menu = new SysMenu();
        menu.setMenuId(0L);
        menu.setParentId(-1L);
        menu.setTitle("顶级菜单");
        menus.add(menu);
        
        return MakeMenuTree.makeTree(menus, -1L);
    }

    /**
     * 根据用户ID获取菜单列表
     * 
     * <p>查询指定用户有权限访问的菜单</p>
     * 
     * @param userId 用户ID
     * @return 用户权限菜单列表
     */
    @Override
    public List<SysMenu> getMenuByUserId(Long userId) {
        return this.baseMapper.getMenuByUserId(userId);
    }

    /**
     * 根据会员ID获取菜单列表
     * 
     * <p>查询指定会员有权限访问的菜单</p>
     * 
     * @param userId 会员ID
     * @return 会员权限菜单列表
     */
    @Override
    public List<SysMenu> getMenuByMemberId(Long userId) {
        return this.baseMapper.getMenuByMemberId(userId);
    }

    /**
     * 根据角色ID获取菜单列表
     * 
     * <p>查询指定角色分配的菜单权限</p>
     * 
     * @param roleId 角色ID
     * @return 角色权限菜单列表
     */
    @Override
    public List<SysMenu> getMenuByRoleId(Long roleId) {
        return this.baseMapper.getMenuByRoleId(roleId);
    }
}
