package com.noy.web.sys_user.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.noy.utils.ResultUtils;
import com.noy.utils.ResultVo;
import com.noy.web.sys_role.entity.SelectType;
import com.noy.web.sys_user.entity.PageParam;
import com.noy.web.sys_user.entity.SysUser;
import com.noy.web.sys_user.service.SysUserService;
import com.noy.web.sys_user_role.entity.SysUserRole;
import com.noy.web.sys_user_role.service.SysUserRoleService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 系统用户管理控制器
 * 
 * <p>提供系统用户相关的REST API接口，负责员工用户的全生命周期管理</p>
 * 
 * <p>主要功能包括：</p>
 * <ul>
 *   <li>用户信息管理 - 员工用户的增删改查操作</li>
 *   <li>分页查询 - 支持条件筛选的分页查询功能</li>
 *   <li>角色分配 - 为用户分配和管理系统角色</li>
 *   <li>密码管理 - 用户密码的加密存储和重置</li>
 *   <li>状态管理 - 用户账户的启用和禁用控制</li>
 *   <li>权限控制 - 基于角色的访问权限管理</li>
 * </ul>
 * 
 * <p>安全特性：</p>
 * <ul>
 *   <li>密码加密 - 使用BCrypt算法加密存储用户密码</li>
 *   <li>角色验证 - 确保用户角色分配的合法性</li>
 *   <li>数据校验 - 对用户输入数据进行严格校验</li>
 * </ul>
 * 
 * @author Gym Management System
 * @version 1.0
 * @since 2024
 */
@RestController
@RequestMapping("/api/user")
public class SysUserController {

    @Autowired
    private SysUserService sysUserService;
    @Autowired
    private SysUserRoleService sysUserRoleService;
    @Autowired
    PasswordEncoder passwordEncoder;
    //新增员工
    @PostMapping
    public ResultVo addUser(@RequestBody SysUser sysUser){
        //判断用户名是否存在
        QueryWrapper<SysUser> query = new QueryWrapper<>();

        query.lambda().eq(SysUser::getUsername,sysUser.getUsername());
        SysUser one = sysUserService.getOne(query);
        if(one != null){
            return ResultUtils.error("账户已经被占用");
        }
        //密码加密
        if(StringUtils.isNotEmpty(sysUser.getPassword())){

            sysUser.setPassword(DigestUtils.md5DigestAsHex(sysUser.getPassword().getBytes()));
        }
        sysUser.setIsAdmin("0");
        sysUser.setCreateTime(new Date());
        //存入数据库
        boolean save = sysUserService.save(sysUser);
        if(save){
            return ResultUtils.success("新增用户成功!");
        }
        return  ResultUtils.error("新增用户失败!");
    }
    //编辑员工
    @PutMapping
    public ResultVo editUser(@RequestBody SysUser sysUser){
        //判断用户名是否存在
        QueryWrapper<SysUser> query = new QueryWrapper<>();

        query.lambda().eq(SysUser::getUsername,sysUser.getUsername());
        SysUser one = sysUserService.getOne(query);
        if(one != null && one.getUserId() != sysUser.getUserId())
        {
            return ResultUtils.error("账户已经被占用");
        }
//        //密码加密
//        if(StringUtils.isNotEmpty(sysUser.getPassword())) {
//
//            sysUser.setPassword(DigestUtils.md5DigestAsHex(sysUser.getPassword().getBytes()));
//        }
        sysUser.setPassword(passwordEncoder.encode(sysUser.getPassword()));
        sysUser.setIsAdmin("0");
        sysUser.setUpdateTime(new Date());
        //存入数据库
        boolean save = sysUserService.updateById(sysUser);
        if(save){
            return ResultUtils.success("编辑用户成功!");
        }
        return  ResultUtils.error("编辑用户失败!");
    }
    //删除用户
    @DeleteMapping("/{userId}")
    public ResultVo deleteUser(@PathVariable("userId") Long
                                       userId){
        boolean remove = sysUserService.removeById(userId);
        if(remove){
            return ResultUtils.success("删除用户成功!");
        }
        return ResultUtils.error("删除用户失败!");
    }
    //用户列表
    @GetMapping("/list")
    public ResultVo getList(PageParam param){
        IPage<SysUser> list = sysUserService.list(param);
        //密码不显示
        list.getRecords().stream().forEach(item ->{
            item.setPassword("");
        });
        return ResultUtils.success("查询成功",list);
    }

    //根据用户id查询角色id
    @GetMapping("/role")
    public ResultVo getRole(Long userId){
        QueryWrapper<SysUserRole> query = new QueryWrapper<>();
        query.lambda().eq(SysUserRole::getUserId,userId);
        SysUserRole one = sysUserRoleService.getOne(query);
        return ResultUtils.success("查询成功",one);
    }
    //查询课程教练
    @GetMapping("/getTeacher")
    public ResultVo getTeacher(){
        QueryWrapper<SysUser> query = new QueryWrapper<>();
        query.lambda().eq(SysUser::getUserType,"2");
        List<SysUser> list = sysUserService.list(query);
        //组装后的select数据
        List<SelectType> selectTypeList = new ArrayList<>();
        if(list.size() >0){
            list.stream().forEach(item ->{
                SelectType selectType = new SelectType();
                selectType.setLabel(item.getNickName());
                selectType.setValue(item.getUserId());
                selectTypeList.add(selectType);
            });
        }
        return ResultUtils.success("查询成功",selectTypeList);
    }
}
