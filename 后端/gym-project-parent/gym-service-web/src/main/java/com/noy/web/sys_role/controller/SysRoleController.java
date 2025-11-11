package com.noy.web.sys_role.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.noy.utils.ResultUtils;
import com.noy.utils.ResultVo;
import com.noy.web.sys_role.entity.*;
import com.noy.web.sys_role.service.SysRoleService;
import com.noy.web.sys_role_menu.entity.SaveMenuParam;
import com.noy.web.sys_role_menu.service.RoleMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 系统角色控制器
 * 处理与角色相关的HTTP请求
 */
@RestController
@RequestMapping("/api/role")
public class SysRoleController {

    @Autowired
    private SysRoleService sysRoleService;

    @Autowired
    private RoleMenuService roleMenuService;

    /**
     * 新增角色接口
     *
     * @param role 通过请求体传递的角色信息，Spring会自动将其转换为SysRole对象
     * @return 统一格式的响应结果，包含操作状态和信息
     * @PostMapping 用于创建新资源的场景
     */
    @PostMapping
    @PreAuthorize("hasAnyAuthority('sys:role:add')")
    public ResultVo addRole(@RequestBody SysRole role) {
        //设置角色的创建时间为现在
        role.setCreateTime(new Date());
        //save会调用服务层保存角色信息 到数据库
        //会返回一个布尔值显示是否保存成功
        boolean save = sysRoleService.save(role);
        if (save) {
            return ResultUtils.success("新增成功！");
        }
        return ResultUtils.error("新增失败");
    }

    /**
     * 修改（编辑）对象接口
     * ysRoleService.updateById(role)会自己写sql将结果返回给数据库
     *
     * @param role
     * @return
     * @PutMapping 用于更新完整资源的场景
     */
    @PutMapping
    public ResultVo editRole(@RequestBody SysRole role) {
        role.setUpdateTime(new Date());
        boolean save = sysRoleService.updateById(role);
        if (save) {
            return ResultUtils.success("编辑成功！");
        }
        return ResultUtils.error("编辑失败");
    }

    /**
     * 删除对象接口
     *
     * @DeleteMapping("/{roleId}") 用于删除资源的场景
     * {roleId}允许在 URL 中传递动态值  需要在方法参数中使用 @PathVariable 注解来获取这个值
     */
    @DeleteMapping("/{roleId}")
    public ResultVo deleteRole(@PathVariable("roleId") Long roleId) {
        //删除就不需要表示时间了
        boolean b = sysRoleService.removeById(roleId);
        if (b) {
            return ResultUtils.success("删除成功！");
        }
        return ResultUtils.error("删除失败！");
    }

    /**
     * 查询角色列表
     *
     * @param param
     * @return
     */
    @GetMapping("/list")
    public ResultVo getList(RoleParam param) {
        IPage<SysRole> list = sysRoleService.list(param);
        return ResultUtils.success("查询成功", list);
    }

    @GetMapping("/getSelect")
    public ResultVo getListSelect() {
        List<SysRole> list = sysRoleService.list();
        List<SelectType> selectTypeList = new ArrayList<>();
        if (list.size() > 0) {
            list.stream().forEach(item -> {
                SelectType type = new SelectType();
                type.setValue(item.getRoleId());
                type.setLabel(item.getRoleName());
                // 同步返回角色类型，供前端联动过滤使用
                type.setTypes(item.getTypes());
                selectTypeList.add(type);
            });
        }
        return ResultUtils.success("查询成功", selectTypeList);
    }

    //分配权限树数据回显查询
    @GetMapping("/getMenuTree")
    public ResultVo getMenuTree(RoleAssignParam param){
        RolePermissionVo tree =
                sysRoleService.getMenuTree(param);
        return ResultUtils.success("查询成功",tree);
    }

    //分配权限保存
    @PostMapping("/saveRoleMenu")
    public ResultVo saveRoleMenu(@RequestBody SaveMenuParam param){
        roleMenuService.saveMenu(param);
        return ResultUtils.success("分配成功");
    }
}
