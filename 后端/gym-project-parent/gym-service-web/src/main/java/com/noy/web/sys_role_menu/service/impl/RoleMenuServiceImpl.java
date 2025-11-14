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
import com.noy.web.sys_menu.entity.SysMenu;
import com.noy.web.sys_menu.service.SysMenuService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;

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

    @Autowired
    private SysMenuService sysMenuService;

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

        // 扩展菜单ID集合（严格授权）：
        // 1) 勾选菜单(type==1)时自动包含其所有祖先目录，保证路由树构建
        // 2) 勾选目录(type==0)不级联子孙，仅作为分组，若无子菜单被授权则不会单独展示
        // 3) 忽略按钮(type==2)
        List<SysMenu> allMenus = sysMenuService.list();
        Map<Long, SysMenu> byId = new HashMap<>();
        Map<Long, List<SysMenu>> childrenMap = new HashMap<>();
        for (SysMenu m : allMenus) {
            if (m == null) continue;
            byId.put(m.getMenuId(), m);
            childrenMap.computeIfAbsent(m.getParentId(), k -> new ArrayList<>()).add(m);
        }

        Set<Long> finalIds = new LinkedHashSet<>();

        // 递归收集子孙节点（目录/菜单）
        final class Collector {
            void collectAnc(Long id) {
                SysMenu cur = byId.get(id);
                while (cur != null && cur.getParentId() != null && cur.getParentId() != 0L) {
                    SysMenu p = byId.get(cur.getParentId());
                    if (p == null) break;
                    if (!"2".equals(p.getType())) {
                        finalIds.add(p.getMenuId());
                    }
                    cur = p;
                }
            }
        }
        Collector collector = new Collector();

        for (Long id : param.getList()) {
            SysMenu item = byId.get(id);
            if (item == null) continue;
            String type = item.getType();
            if ("1".equals(type) || "2".equals(type)) { // 保存页面与按钮
                finalIds.add(id);
                collector.collectAnc(id);
            }
            // 目录不主动保存；祖先链上目录通过 collectAnc 自动补齐
        }

        // 重新保存
        if (!finalIds.isEmpty()) {
            this.baseMapper.saveRoleMenu(param.getRoleId(), new ArrayList<>(finalIds));
        }
    }
}
