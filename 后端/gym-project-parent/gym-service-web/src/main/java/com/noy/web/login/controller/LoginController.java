package com.noy.web.login.controller;

// ==================== Java标准库导入 ====================
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

// ==================== Java EE导入 ====================
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

// ==================== Apache Commons导入 ====================
import org.apache.commons.lang3.StringUtils;

// ==================== Spring框架导入 ====================
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// ==================== 第三方库导入 ====================
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.google.code.kaptcha.impl.DefaultKaptcha;

// ==================== 项目内部导入 ====================
import com.noy.jwt.JwtUtils;
import com.noy.utils.ResultUtils;
import com.noy.utils.ResultVo;
import com.noy.web.login.entity.InfoParam;
import com.noy.web.login.entity.LoginParam;
import com.noy.web.login.entity.LoginResult;
import com.noy.web.login.entity.UserInfo;
import com.noy.web.member.entity.Member;
import com.noy.web.member.service.MemberService;
import com.noy.web.sys_menu.entity.MakeMenuTree;
import com.noy.web.sys_menu.entity.RouterVO;
import com.noy.web.sys_menu.entity.SysMenu;
import com.noy.web.sys_menu.service.SysMenuService;
import com.noy.web.sys_user.entity.SysUser;
import com.noy.web.sys_user.service.SysUserService;

/**
 * 登录控制器
 * 
 * <p>提供用户登录认证相关的REST API接口，支持多种用户类型的登录验证</p>
 * 
 * <p>主要功能包括：</p>
 * <ul>
 *   <li>用户登录认证 - 支持会员和员工两种用户类型的登录</li>
 *   <li>验证码生成 - 提供图形验证码生成和验证功能</li>
 *   <li>用户信息获取 - 根据用户类型获取对应的用户详细信息</li>
 *   <li>菜单权限管理 - 根据用户角色动态生成菜单列表</li>
 *   <li>JWT令牌管理 - 生成和验证JWT访问令牌</li>
 *   <li>Spring Security集成 - 与Spring Security认证框架深度集成</li>
 * </ul>
 * 
 * <p>支持的用户类型：</p>
 * <ul>
 *   <li>会员用户 - 健身房会员，具有基础功能权限</li>
 *   <li>员工用户 - 健身房员工，具有管理功能权限</li>
 * </ul>
 * 
 * @author Gym Management System
 * @version 1.0
 * @since 2024
 */
@RestController
@RequestMapping("/api/login")
public class LoginController {

    // ==================== 服务层依赖注入 ====================
    
    @Autowired
    private MemberService memberService;
    
    @Autowired
    private SysUserService sysUserService;
    
    @Autowired
    private SysMenuService sysMenuService;
    
    // ==================== 工具类依赖注入 ====================
    
    @Autowired
    private JwtUtils jwtUtils;
    
    @Autowired
    private DefaultKaptcha defaultKaptcha;

    @Autowired
    private PasswordEncoder passwordEncoder;
    
    @Autowired
    private AuthenticationManager authenticationManager;

    // ==================== 登录相关接口 ====================
    
    /**
     * 用户登录接口
     * 
     * <p>支持会员和员工两种用户类型登录，包含验证码验证</p>
     * 
     * @param request HTTP请求对象，用于获取Session
     * @param param 登录参数，包含用户名、密码、用户类型、验证码
     * @return 登录结果，包含JWT令牌和用户信息
     */
    @PostMapping("/login")
    public ResultVo login(HttpServletRequest request, @RequestBody LoginParam param) {
        // 参数校验
        if (StringUtils.isEmpty(param.getUsername()) ||
                StringUtils.isEmpty(param.getPassword()) ||
                StringUtils.isEmpty(param.getUserType()) ||
                StringUtils.isEmpty(param.getCode())) {
            return ResultUtils.error("用户名、密码、验证码或用户类型不能为空!");
        }
        
        // 获取session中的验证码
        HttpSession session = request.getSession();
        String code = (String) session.getAttribute("code");
        if (StringUtils.isEmpty(code)) {
            return ResultUtils.error("验证码过期!");
        }
        
        // 验证验证码
        if (!code.equals(param.getCode())) {
            return ResultUtils.error("验证码错误!");
        }
        
        // 构造Spring Security需要的认证token
        UsernamePasswordAuthenticationToken authenticationToken = 
                new UsernamePasswordAuthenticationToken(
                        param.getUsername() + ":" + param.getUserType(), 
                        param.getPassword()
                );
        
        // 执行认证
        Authentication authenticate = authenticationManager.authenticate(authenticationToken);
        SecurityContextHolder.getContext().setAuthentication(authenticate);
        
        // 根据用户类型处理登录结果
        if ("1".equals(param.getUserType())) { 
            // 会员登录处理
            return handleMemberLogin(authenticate, param.getUserType());
        } else if ("2".equals(param.getUserType())) { 
            // 员工登录处理
            return handleEmployeeLogin(authenticate, param.getUserType());
        } else {
            return ResultUtils.error("用户类型错误!");
        }
    }

    /**
     * 处理会员登录
     * 
     * @param authenticate 认证信息
     * @param userType 用户类型
     * @return 登录结果
     */
    private ResultVo handleMemberLogin(Authentication authenticate, String userType) {
        Member user = (Member) authenticate.getPrincipal();
        
        // 生成JWT token
        Map<String, String> tokenMap = new HashMap<>();
        tokenMap.put("userId", Long.toString(user.getMemberId()));
        tokenMap.put("username", user.getUsername());
        tokenMap.put("userType", userType);
        String token = jwtUtils.generateToken(tokenMap);
        
        // 构造返回结果
        LoginResult result = new LoginResult();
        result.setToken(token);
        result.setUserId(user.getMemberId());
        result.setUsername(user.getName());
        result.setUserType(userType);
        
        return ResultUtils.success("登录成功", result);
    }

    /**
     * 处理员工登录
     * 
     * @param authenticate 认证信息
     * @param userType 用户类型
     * @return 登录结果
     */
    private ResultVo handleEmployeeLogin(Authentication authenticate, String userType) {
        SysUser user = (SysUser) authenticate.getPrincipal();
        
        // 生成JWT token
        Map<String, String> tokenMap = new HashMap<>();
        tokenMap.put("userId", Long.toString(user.getUserId()));
        tokenMap.put("username", user.getUsername());
        tokenMap.put("userType", userType);
        String token = jwtUtils.generateToken(tokenMap);
        
        // 构造返回结果
        LoginResult result = new LoginResult();
        result.setToken(token);
        result.setUserId(user.getUserId());
        result.setUsername(user.getNickName());
        result.setUserType(userType);
        
        return ResultUtils.success("登录成功", result);
    }

    // ==================== 验证码相关接口 ====================

    /**
     * 生成验证码接口
     * 
     * <p>生成图片验证码并返回Base64编码的图片数据</p>
     * 
     * @param request HTTP请求对象，用于存储验证码到Session
     * @return 包含Base64编码图片的结果
     */
    @PostMapping("/image")
    public ResultVo imageCode(HttpServletRequest request) {
        try {
            // 生成验证码文字
            String text = defaultKaptcha.createText();
            
            // 生成验证码图片
            BufferedImage image = defaultKaptcha.createImage(text);
            
            // 将验证码存储到session中
            HttpSession session = request.getSession();
            session.setAttribute("code", text);
            
            // 将图片转换为Base64字符串
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            ImageIO.write(image, "jpg", outputStream);
            String base64 = Base64.getEncoder().encodeToString(outputStream.toByteArray());
            
            // 返回Base64编码的图片数据
            Map<String, String> result = new HashMap<>();
            result.put("image", "data:image/jpeg;base64," + base64);
            
            return ResultUtils.success("验证码生成成功", result);
        } catch (IOException e) {
            return ResultUtils.error("验证码生成失败");
        }
    }

    // ==================== 用户信息相关接口 ====================

    /**
     * 查询用户信息接口
     * 
     * <p>根据用户类型查询用户基本信息和权限</p>
     * 
     * @param param 包含用户ID和用户类型的参数
     * @return 用户信息，包含姓名、用户ID和权限列表
     */
    @GetMapping("/getInfo")
    public ResultVo getInfo(InfoParam param) {
        UserInfo userInfo = new UserInfo();
        
        if ("1".equals(param.getUserType())) { 
            // 会员信息查询
            return getMemberInfo(param, userInfo);
        } else if ("2".equals(param.getUserType())) { 
            // 员工信息查询
            return getEmployeeInfo(param, userInfo);
        } else {
            return ResultUtils.error("用户类型错误!");
        }
    }

    /**
     * 获取会员信息
     * 
     * @param param 查询参数
     * @param userInfo 用户信息对象
     * @return 查询结果
     */
    private ResultVo getMemberInfo(InfoParam param, UserInfo userInfo) {
        // 根据会员ID查询权限字段
        List<SysMenu> menuList = sysMenuService.getMenuByMemberId(param.getUserId());
        
        // 获取全部的权限代码字段
        List<String> permissionCodes = Optional.ofNullable(menuList)
                .orElse(new ArrayList<>())
                .stream()
                .map(SysMenu::getCode)
                .filter(code -> code != null)
                .collect(Collectors.toList());
        
        // 转为数组
        String[] permissions = permissionCodes.toArray(new String[0]);
        
        // 查询用户信息
        Member member = memberService.getById(param.getUserId());
        
        // 设置返回信息
        userInfo.setName(member.getName());
        userInfo.setUserId(member.getMemberId());
        userInfo.setPermissons(permissions);
        
        return ResultUtils.success("查询成功", userInfo);
    }

    /**
     * 获取员工信息
     * 
     * @param param 查询参数
     * @param userInfo 用户信息对象
     * @return 查询结果
     */
    private ResultVo getEmployeeInfo(InfoParam param, UserInfo userInfo) {
        // 查询用户信息
        SysUser user = sysUserService.getById(param.getUserId());
        
        // 根据用户权限获取菜单列表
        List<SysMenu> menuList;
        if (StringUtils.isNotEmpty(user.getIsAdmin()) && "1".equals(user.getIsAdmin())) { 
            // 超级管理员获取所有菜单
            menuList = sysMenuService.list();
        } else {
            // 普通员工根据用户ID获取菜单
            menuList = sysMenuService.getMenuByUserId(user.getUserId());
        }
        
        // 获取全部的权限代码字段
        List<String> permissionCodes = Optional.ofNullable(menuList)
                .orElse(new ArrayList<>())
                .stream()
                .map(SysMenu::getCode)
                .filter(code -> code != null)
                .collect(Collectors.toList());
        
        // 转为数组
        String[] permissions = permissionCodes.toArray(new String[0]);
        
        // 设置返回信息
        userInfo.setName(user.getNickName());
        userInfo.setUserId(user.getUserId());
        userInfo.setPermissons(permissions);
        
        return ResultUtils.success("查询成功", userInfo);
    }

    /**
     * 获取菜单列表接口
     * 
     * <p>根据用户类型和权限返回对应的菜单树结构</p>
     * 
     * @param param 包含用户ID和用户类型的参数
     * @return 菜单树结构
     */
    @GetMapping("/getMenuList")
    public ResultVo getMenuList(InfoParam param) {
        if ("1".equals(param.getUserType())) { 
            // 会员菜单查询
            return getMemberMenuList(param);
        } else if ("2".equals(param.getUserType())) { 
            // 员工菜单查询
            return getEmployeeMenuList(param);
        } else {
            return ResultUtils.error("用户类型错误!");
        }
    }

    /**
     * 获取会员菜单列表
     * 
     * @param param 查询参数
     * @return 菜单列表
     */
    private ResultVo getMemberMenuList(InfoParam param) {
        List<SysMenu> menus = sysMenuService.getMenuByMemberId(param.getUserId());
        
        // 获取菜单和目录（过滤掉按钮类型）
        List<SysMenu> filteredMenus = Optional.ofNullable(menus)
                .orElse(new ArrayList<>())
                .stream()
                .filter(item -> item != null && !"2".equals(item.getType()))
                .collect(Collectors.toList());
        
        List<RouterVO> router = MakeMenuTree.makeRourer(filteredMenus, 0L);
        return ResultUtils.success("查询成功", router);
    }

    /**
     * 获取员工菜单列表
     * 
     * @param param 查询参数
     * @return 菜单列表
     */
    private ResultVo getEmployeeMenuList(InfoParam param) {
        SysUser user = sysUserService.getById(param.getUserId());
        
        // 根据用户权限获取菜单列表
        List<SysMenu> menuList;
        if (StringUtils.isNotEmpty(user.getIsAdmin()) && "1".equals(user.getIsAdmin())) { 
            // 超级管理员获取所有菜单
            menuList = sysMenuService.list();
        } else {
            // 普通员工根据用户ID获取菜单
            menuList = sysMenuService.getMenuByUserId(user.getUserId());
        }
        
        // 获取菜单和目录（过滤掉按钮类型）
        List<SysMenu> filteredMenus = Optional.ofNullable(menuList)
                .orElse(new ArrayList<>())
                .stream()
                .filter(item -> item != null && !"2".equals(item.getType()))
                .collect(Collectors.toList());
        
        List<RouterVO> router = MakeMenuTree.makeRourer(filteredMenus, 0L);
        return ResultUtils.success("查询成功", router);
    }

}
