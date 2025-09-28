package com.noy.web.sys_menu.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * 路由视图对象
 * 
 * <p>用于封装前端路由配置信息，包括：</p>
 * <ul>
 *   <li>路由配置 - 路径、组件、名称等基本路由信息</li>
 *   <li>元数据 - 标题、图标、权限等路由元信息</li>
 *   <li>层级结构 - 支持子路由的树形结构</li>
 *   <li>权限控制 - 基于角色的路由访问控制</li>
 * </ul>
 * 
 * @author noy
 * @version 1.0
 * @since 2024
 */
@Data
// Jackson注解，表示在序列化为JSON时，只包含非空的字段
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class RouterVO {
    /**
     * 路由路径
     */
    private String path;
    
    /**
     * 路由对应的组件
     */
    private String component;
    
    /**
     * 路由名称
     */
    private String name;
    
    /**
     * 重定向路径
     */
    private String redirect;
    
    /**
     * 路由元信息
     */
    private Meta meta;
    
    /**
     * 内部类，定义路由的元数据信息
     * 
     * <p>包含路由的显示信息和权限控制信息：</p>
     * <ul>
     *   <li>标题 - 用于菜单显示的标题</li>
     *   <li>图标 - 菜单项的图标</li>
     *   <li>角色 - 可访问该路由的角色列表</li>
     * </ul>
     */
    @Data
    @AllArgsConstructor // 生成全参数构造函数
    public class Meta {
        /**
         * 路由标题（通常用于菜单显示）
         */
        private String title;

        /**
         * 路由图标
         */
        private String icon;

        /**
         * 可访问该路由的角色数组
         */
        private Object[] roles;
    }

    /**
     * 子路由列表，初始化为空ArrayList
     */
    private List<RouterVO> children = new ArrayList<>();
}