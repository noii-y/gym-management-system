package com.noy.web.home.controller;

// ==================== Java EE导入 ====================
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// ==================== Java标准库导入 ====================
import java.util.List;

// ==================== Spring框架导入 ====================
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// ==================== 第三方库导入 ====================
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

// ==================== 项目内部导入 ====================
import com.noy.utils.ResultUtils;
import com.noy.utils.ResultVo;
import com.noy.web.equipment.service.MaterialService;
import com.noy.web.goods_order.service.GoodsOrderService;
import com.noy.web.home.entity.Echart;
import com.noy.web.home.entity.EchartItem;
import com.noy.web.home.entity.ResetPassword;
import com.noy.web.home.entity.TotalCount;
import com.noy.web.member.entity.Member;
import com.noy.web.member.service.MemberService;
import com.noy.web.suggest.entity.Suggest;
import com.noy.web.suggest.service.SuggestService;
import com.noy.web.sys_user.entity.SysUser;
import com.noy.web.sys_user.service.SysUserService;

/**
 * 首页控制器
 * 
 * <p>提供系统首页相关的REST API接口，负责首页数据展示和用户账户管理</p>
 * 
 * <p>主要功能包括：</p>
 * <ul>
 *   <li>统计数据 - 系统总体数据统计和图表展示</li>
 *   <li>热门推荐 - 热门商品、课程、会员卡的推荐展示</li>
 *   <li>建议反馈 - 用户建议反馈列表的查询展示</li>
 *   <li>密码管理 - 用户密码重置和修改功能</li>
 *   <li>登出功能 - 安全的用户登出处理</li>
 * </ul>
 * 
 * <p>数据统计功能：</p>
 * <ul>
 *   <li>会员总数统计</li>
 *   <li>员工总数统计</li>
 *   <li>器材总数统计</li>
 *   <li>订单总数统计</li>
 * </ul>
 * 
 * <p>安全特性：</p>
 * <ul>
 *   <li>密码加密 - 使用BCrypt算法加密存储密码</li>
 *   <li>权限验证 - 确保用户只能修改自己的密码</li>
 *   <li>安全登出 - 清理用户会话和安全上下文</li>
 * </ul>
 * 
 * @author Gym Management System
 * @version 1.0
 * @since 2024
 */
@RestController
@RequestMapping("/api/home")
public class HomeController {

    // ==================== 服务层依赖注入 ====================
    
    @Autowired
    private MemberService memberService;
    
    @Autowired
    private SysUserService sysUserService;
    
    @Autowired
    private MaterialService materialService;
    
    @Autowired
    private GoodsOrderService goodsOrderService;
    
    @Autowired
    private SuggestService suggestService;
    
    // ==================== 工具类依赖注入 ====================
    
    @Autowired
    private PasswordEncoder passwordEncoder;

    // ==================== 统计数据相关接口 ====================

    /**
     * 获取统计总数
     * 
     * <p>统计系统中的会员数、用户数、器材数、订单数</p>
     * 
     * @return 包含各项统计数据的结果
     */
    @GetMapping("/getTotal")
    public ResultVo getTotal() {
        TotalCount totalCount = new TotalCount();
        
        // 统计会员数量
        int memberCount = memberService.count();
        totalCount.setMemberCount(memberCount);
        
        // 统计用户数量
        int userCount = sysUserService.count();
        totalCount.setUserCount(userCount);
        
        // 统计器材数量
        int materialCount = materialService.count();
        totalCount.setMaterCount(materialCount);
        
        // 统计订单数量
        int orderCount = goodsOrderService.count();
        totalCount.setOrderCount(orderCount);
        
        return ResultUtils.success("查询成功", totalCount);
    }

    /**
     * 查询反馈建议列表
     * 
     * <p>获取最新的3条反馈建议，按时间倒序排列</p>
     * 
     * @return 反馈建议列表
     */
    @GetMapping("/getSuggestList")
    public ResultVo getSuggestList() {
        QueryWrapper<Suggest> query = new QueryWrapper<>();
        query.lambda()
             .orderByDesc(Suggest::getDateTime)
             .last("limit 3");
        
        List<Suggest> list = suggestService.list(query);
        return ResultUtils.success("查询成功", list);
    }

    // ==================== 热销数据统计接口 ====================

    /**
     * 查询热销商品统计
     * 
     * <p>获取热销商品的统计数据，用于图表展示</p>
     * 
     * @return 热销商品统计图表数据
     */
    @GetMapping("/getHotGoods")
    public ResultVo getHotGoods() {
        List<EchartItem> echartItems = goodsOrderService.hotGoods();
        Echart echart = new Echart();
        
        // 构造图表数据
        if (!echartItems.isEmpty()) {
            for (EchartItem item : echartItems) {
                echart.getNames().add(item.getName());
                echart.getValues().add(item.getValue());
            }
        }
        
        return ResultUtils.success("查询成功", echart);
    }

    /**
     * 查询热销卡片统计
     * 
     * <p>获取热销卡片的统计数据</p>
     * 
     * @return 热销卡片统计数据
     */
    @GetMapping("/getHotCards")
    public ResultVo getHotCards() {
        List<EchartItem> echartItems = goodsOrderService.hotCards();
        return ResultUtils.success("查询成功", echartItems);
    }

    /**
     * 查询热销课程统计
     * 
     * <p>获取热销课程的统计数据</p>
     * 
     * @return 热销课程统计数据
     */
    @GetMapping("/getHotCourse")
    public ResultVo getHotCourse() {
        List<EchartItem> echartItems = goodsOrderService.hotCourse();
        return ResultUtils.success("查询成功", echartItems);
    }

    // ==================== 密码管理相关接口 ====================

    /**
     * 重置密码
     * 
     * <p>将用户密码重置为默认密码"666666"</p>
     * 
     * @param resetPassword 重置密码参数，包含用户ID和用户类型
     * @return 重置结果
     */
    @PostMapping("/resetPassword")
    public ResultVo resetPassword(@RequestBody ResetPassword resetPassword) {
        if ("1".equals(resetPassword.getUserType())) {
            // 重置会员密码
            return resetMemberPassword(resetPassword.getUserId());
        } else if ("2".equals(resetPassword.getUserType())) {
            // 重置员工密码
            return resetEmployeePassword(resetPassword.getUserId());
        } else {
            return ResultUtils.error("用户类型错误!");
        }
    }

    /**
     * 重置会员密码
     * 
     * @param userId 用户ID
     * @return 重置结果
     */
    private ResultVo resetMemberPassword(Long userId) {
        Member member = new Member();
        member.setMemberId(userId);
        member.setPassword(passwordEncoder.encode("666666"));
        memberService.updateById(member);
        return ResultUtils.success("密码重置成功!");
    }

    /**
     * 重置员工密码
     * 
     * @param userId 用户ID
     * @return 重置结果
     */
    private ResultVo resetEmployeePassword(Long userId) {
        SysUser user = new SysUser();
        user.setUserId(userId);
        user.setPassword(passwordEncoder.encode("666666"));
        sysUserService.updateById(user);
        return ResultUtils.success("密码重置成功!");
    }

    /**
     * 修改密码
     * 
     * <p>用户主动修改密码，需要验证原密码</p>
     * 
     * @param resetPassword 修改密码参数，包含用户ID、用户类型、原密码、新密码
     * @return 修改结果
     */
    @PostMapping("/updatePassword")
    public ResultVo updatePassword(@RequestBody ResetPassword resetPassword) {
        if ("1".equals(resetPassword.getUserType())) {
            // 修改会员密码
            return updateMemberPassword(resetPassword);
        } else if ("2".equals(resetPassword.getUserType())) {
            // 修改员工密码
            return updateEmployeePassword(resetPassword);
        } else {
            return ResultUtils.error("用户类型错误!");
        }
    }

    /**
     * 修改会员密码
     * 
     * @param resetPassword 修改密码参数
     * @return 修改结果
     */
    private ResultVo updateMemberPassword(ResetPassword resetPassword) {
        // 验证原密码是否正确
        Member member = memberService.getById(resetPassword.getUserId());
        String dbPassword = member.getPassword();
        
        if (!passwordEncoder.matches(resetPassword.getOldPassword(), dbPassword)) {
            return ResultUtils.error("原密码不正确！");
        }
        
        // 更新密码
        Member updateMember = new Member();
        updateMember.setMemberId(resetPassword.getUserId());
        updateMember.setPassword(passwordEncoder.encode(resetPassword.getPassword()));
        memberService.updateById(updateMember);
        
        return ResultUtils.success("修改密码成功!");
    }

    /**
     * 修改员工密码
     * 
     * @param resetPassword 修改密码参数
     * @return 修改结果
     */
    private ResultVo updateEmployeePassword(ResetPassword resetPassword) {
        // 验证原密码
        SysUser sysUser = sysUserService.getById(resetPassword.getUserId());
        String dbPassword = sysUser.getPassword();
        
        if (!passwordEncoder.matches(resetPassword.getOldPassword(), dbPassword)) {
            return ResultUtils.error("原密码不正确！");
        }
        
        // 更新密码
        SysUser updateUser = new SysUser();
        updateUser.setUserId(resetPassword.getUserId());
        updateUser.setPassword(passwordEncoder.encode(resetPassword.getPassword()));
        sysUserService.updateById(updateUser);
        
        return ResultUtils.success("修改密码成功!");
    }

    // ==================== 登录登出相关接口 ====================

    /**
     * 退出登录
     * 
     * <p>清除用户的认证信息，实现安全登出</p>
     * 
     * @param request HTTP请求对象
     * @param response HTTP响应对象
     * @return 登出结果
     */
    @PostMapping("/loginOut")
    public ResultVo loginOut(HttpServletRequest request, HttpServletResponse response) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        
        if (authentication != null) {
            new SecurityContextLogoutHandler().logout(request, response, authentication);
        }
        
        return ResultUtils.success("退出登录成功");
    }
}
