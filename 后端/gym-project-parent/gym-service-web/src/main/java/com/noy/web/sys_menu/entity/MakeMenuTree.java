package com.noy.web.sys_menu.entity;

import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * 菜单树构建工具类
 * 
 * <p>提供菜单数据的树形结构构建功能，包括：</p>
 * <ul>
 *   <li>菜单树构建 - 将平铺的菜单数据构建为层级树形结构</li>
 *   <li>路由数据构建 - 将菜单数据转换为前端路由配置</li>
 *   <li>递归处理 - 支持多层级菜单的递归构建</li>
 *   <li>权限处理 - 处理菜单权限和角色信息</li>
 * </ul>
 * 
 * @author noy
 * @version 1.0
 * @since 2024
 */
public class MakeMenuTree {
    
    /**
     * 构造菜单树
     * 
     * <p>将平铺的菜单列表按照父子关系构建为树形结构</p>
     * 
     * @param menuList 菜单列表
     * @param pid 父级菜单ID
     * @return 构建好的菜单树列表
     */
    public static List<SysMenu> makeTree(List<SysMenu> menuList, Long pid) {
        List<SysMenu> list = new ArrayList<>();
        Optional.ofNullable(menuList).orElse(new ArrayList<>())
                .stream()
                .filter(item -> item != null &&
                        item.getParentId().equals(pid))
                .forEach(item -> {
                    SysMenu menu = new SysMenu();
                    BeanUtils.copyProperties(item, menu);
                    // 递归查找下级,自己调用自己
                    List<SysMenu> children = makeTree(menuList,
                            item.getMenuId());
                    menu.setChildren(children);
                    list.add(menu);
                });
        return list;
    }
    
    /**
     * 构造路由数据
     * 
     * <p>将菜单数据转换为前端Vue Router所需的路由配置格式</p>
     * <p>处理不同类型的菜单（目录、菜单）的路由配置</p>
     * 
     * @param menuList 菜单列表
     * @param pid 父级菜单ID
     * @return 构建好的路由配置列表
     */
    public static List<RouterVO> makeRourer(List<SysMenu> menuList,Long pid){
        // 构建存放路由数据的容器
        List<RouterVO> list = new ArrayList<>();
        Optional.ofNullable(menuList).orElse(new ArrayList<>())
                .stream()
                .filter(item -> item != null &&
                        item.getParentId().equals(pid))
                .forEach(item ->{
                    RouterVO router = new RouterVO();
                    router.setName(item.getName());
                    router.setPath(item.getPath());
                    // 设置children 递归调用：自己调用自己
                    List<RouterVO> children =
                            makeRourer(menuList, item.getMenuId());
                    router.setChildren(children);
                    if(item.getParentId() == 0L){// 如果是上级是0,那么他的component是Layout
                        router.setComponent("Layout");
                        // 判断该数据是目录还是菜单
                        if(item.getType().equals("1")){ // 如果一级菜单是 菜单类型，单独处理
                            router.setRedirect(item.getPath());
                            // 菜单需要设置children
                            List<RouterVO> listChild = new
                                    ArrayList<>();
                            RouterVO child  = new RouterVO();
                            child.setName(item.getName());
                            child.setPath(item.getPath());
                            child.setComponent(item.getUrl());
                            child.setMeta(child.new Meta(
                                    item.getTitle(),
                                    item.getIcon(),
                                    item.getCode().split(",")
                            ));
                            listChild.add(child);
                            router.setChildren(listChild);
                            router.setPath(item.getPath()
                                    +"parent");

                            router.setName(item.getName()+"parent");
                        }
                    }else{
                        router.setComponent(item.getUrl());
                    }
                    router.setMeta(router.new Meta(
                            item.getTitle(),
                            item.getIcon(),
                            item.getCode().split(",")
                    ));
                    list.add(router);
                });
        return  list;
    }
}
