package com.noy.config.springSecurity;

import com.noy.web.member.entity.Member;
import com.noy.web.member.service.MemberService;
import com.noy.web.sys_menu.entity.SysMenu;
import com.noy.web.sys_menu.service.SysMenuService;
import com.noy.web.sys_user.entity.SysUser;
import com.noy.web.sys_user.service.SysUserService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 自定义用户详情服务类
 * 
 * <p>实现Spring Security的UserDetailsService接口，提供用户认证和授权功能，包括：</p>
 * <ul>
 *   <li>根据用户名加载用户详细信息</li>
 *   <li>支持系统用户和会员用户两种类型</li>
 *   <li>动态加载用户权限信息</li>
 *   <li>与Spring Security认证流程集成</li>
 * </ul>
 * 
 * @author noy
 * @version 1.0
 * @since 2024
 */
@Component("customizedUserDetailService")
public class CustomizedUserDetailService implements UserDetailsService {
        @Autowired
        private SysUserService sysUserService;
        @Autowired
        private MemberService memberService;

        @Autowired
        SysMenuService sysMenuService;
        @Override
        public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
            //admin:2  ls:1
            int index = s.indexOf(":");
            String username = s.substring(0, index);
            String userType = s.substring(index + 1);
            //认证:登录
            if (userType.equals("1")) {
                Member user = memberService.loadUser(username);
                if (user == null) {
                    throw new UsernameNotFoundException("用户名或密码错误!");
                }
                //授权:把该用户拥有的按钮权限，交给spring secuity进行管理
                //获取会员的按钮权限
                List<SysMenu> menuList =
                        sysMenuService.getMenuByMemberId(user.getMemberId());
                //取出code字段
                List<String> collect =
                        menuList.stream().map(item ->
                                item.getCode()).filter(item -> item != null &&
                                StringUtils.isNotEmpty(item)).collect(Collectors.toList());
                String[] strings = collect.toArray(new String[collect.size()]);
                List<GrantedAuthority> authorityList =
                        AuthorityUtils.createAuthorityList(strings);
                //授权
                user.setAuthorities(authorityList);
                return user;
            } else if (userType.equals("2")) {
                SysUser user = sysUserService.loadUser(username);
                if (user == null) {
                    throw new UsernameNotFoundException("用户名或密码错误!");
                }
                //授权:把该用户拥有的按钮权限，交给spring secuity进行管理
                List<SysMenu> menuList = null;
                if (StringUtils.isNotEmpty(user.getIsAdmin()) &&
                        user.getIsAdmin().equals("1")) {
                    menuList = sysMenuService.list();
                } else {
                    menuList =
                            sysMenuService.getMenuByUserId(user.getUserId());
                }
                //取出code字段
                List<String> collect =
                        menuList.stream().map(item ->
                                item.getCode()).filter(item -> item != null &&
                                StringUtils.isNotEmpty(item)).collect(Collectors.toList());
                String[] strings = collect.toArray(new
                        String[collect.size()]);
                List<GrantedAuthority> authorityList =
                        AuthorityUtils.createAuthorityList(strings);
                //授权
                user.setAuthorities(authorityList);
                return user;
            } else {
                throw new UsernameNotFoundException("用户类型错误!");
            }
         }
        }

