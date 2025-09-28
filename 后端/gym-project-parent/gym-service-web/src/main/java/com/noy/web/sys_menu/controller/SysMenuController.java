package com.noy.web.sys_menu.controller;

// Java 标准库
import java.util.Date;
import java.util.List;

// MyBatis Plus 相关
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

// Spring 框架相关
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

// 项目内部类
import com.noy.utils.ResultUtils;
import com.noy.utils.ResultVo;
import com.noy.web.sys_menu.entity.MakeMenuTree;
import com.noy.web.sys_menu.entity.SysMenu;
import com.noy.web.sys_menu.service.SysMenuService;

/**
 * 系统菜单管理控制器
 * 
 * <p>提供系统菜单相关的REST API接口，包括：</p>
 * <ul>
 *   <li>菜单信息的增删改查操作</li>
 *   <li>菜单树形结构的构建和查询</li>
 *   <li>上级菜单的查询功能</li>
 *   <li>菜单排序管理</li>
 * </ul>
 * 
 * @author noy
 * @version 1.0
 * @since 2024
 */
@RestController
@RequestMapping("/api/menu")
public class SysMenuController {

    // ==================== 依赖注入 ====================
    
    /**
     * 系统菜单业务逻辑服务
     */
    @Autowired
    private SysMenuService sysMenuService;

    // ==================== 系统菜单管理接口 ====================
    
    /**
     * 新增系统菜单
     * 
     * <p>添加新的系统菜单信息，自动设置创建时间</p>
     * 
     * @param sysMenu 系统菜单信息对象
     * @return 新增操作结果
     */
    @PostMapping
    public ResultVo add(@RequestBody SysMenu sysMenu) {
        sysMenu.setCreateTime(new Date());
        if (sysMenuService.save(sysMenu)) {
            return ResultUtils.success("新增成功!");
        }
        return ResultUtils.error("新增失败!");
    }

    /**
     * 编辑系统菜单信息
     * 
     * <p>根据菜单ID更新菜单信息，自动设置更新时间</p>
     * 
     * @param sysMenu 系统菜单信息对象，包含要更新的数据
     * @return 编辑操作结果
     */
    @PutMapping
    public ResultVo edit(@RequestBody SysMenu sysMenu) {
        sysMenu.setUpdateTime(new Date());
        if (sysMenuService.updateById(sysMenu)) {
            return ResultUtils.success("编辑成功!");
        }
        return ResultUtils.error("编辑失败!");
    }

    /**
     * 删除系统菜单
     * 
     * <p>根据菜单ID删除指定的系统菜单</p>
     * 
     * @param menuId 菜单ID
     * @return 删除操作结果
     */
    @DeleteMapping("/{menuId}")
    public ResultVo delete(@PathVariable("menuId") Long menuId) {
        if (sysMenuService.removeById(menuId)) {
            return ResultUtils.success("删除成功!");
        }
        return ResultUtils.error("删除失败!");
    }

    /**
     * 查询系统菜单列表
     * 
     * <p>查询所有系统菜单并构建树形结构，按排序号升序排列</p>
     * 
     * @return 树形结构的菜单列表
     */
    @GetMapping("/list")
    public ResultVo list() {
        QueryWrapper<SysMenu> query = new QueryWrapper<>();
        query.lambda().orderByAsc(SysMenu::getOrderNum);
        List<SysMenu> list = sysMenuService.list(query);
        
        // 树形数据组装
        List<SysMenu> menuList = MakeMenuTree.makeTree(list, 0L);
        return ResultUtils.success("查询成功", menuList);
    }

    /**
     * 查询上级菜单树
     * 
     * <p>获取可作为上级菜单的菜单列表，用于菜单层级关系构建</p>
     * 
     * @return 上级菜单列表
     */
    @GetMapping("/parent")
    public ResultVo getParent() {
        List<SysMenu> parent = sysMenuService.getParent();
        return ResultUtils.success("查询成功", parent);
    }
}
