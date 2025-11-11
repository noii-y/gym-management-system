package com.noy.web.member.controller;

// Java标准库
import java.text.ParseException;
import java.util.List;

// MyBatis Plus
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

// Apache Commons
import org.apache.commons.lang.StringUtils;

// Spring框架
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

// 项目内部工具类
import com.noy.utils.ResultUtils;
import com.noy.utils.ResultVo;

// 项目内部实体类和服务类
import com.noy.web.member.entity.*;
import com.noy.web.member.service.MemberService;
import com.noy.web.member_card.entity.MemberCard;
import com.noy.web.member_card.service.MemberCardService;
import com.noy.web.member_recharge.entity.MemberRecharge;
import com.noy.web.member_recharge.service.MemberRechargeService;
import com.noy.web.member_role.entity.MemberRole;
import com.noy.web.member_role.service.MemberRoleService;

/**
 * 会员管理控制器
 * 
 * <p>提供健身房会员相关的REST API接口，负责会员的全生命周期管理</p>
 * 
 * <p>主要功能包括：</p>
 * <ul>
 *   <li>会员信息管理 - 会员的增删改查操作</li>
 *   <li>分页查询 - 支持条件筛选的会员列表分页查询</li>
 *   <li>角色管理 - 会员角色的分配和查询</li>
 *   <li>会员卡管理 - 会员卡类型查询和办理</li>
 *   <li>入会申请 - 处理新会员的入会申请流程</li>
 *   <li>充值功能 - 会员账户余额充值管理</li>
 *   <li>充值记录 - 会员充值历史记录查询</li>
 * </ul>
 * 
 * <p>业务特性：</p>
 * <ul>
 *   <li>权限分级 - 根据用户类型显示不同的会员信息</li>
 *   <li>数据安全 - 会员敏感信息的访问控制</li>
 *   <li>密码加密 - 会员密码的安全存储</li>
 *   <li>状态管理 - 会员账户状态的动态管理</li>
 * </ul>
 * 
 * @author Gym Management System
 * @version 1.0
 * @since 2024
 */
@RestController
@RequestMapping("api/member")
public class MemberController {

    // ==================== 服务层依赖注入 ====================
    
    @Autowired
    private MemberService memberService;

    @Autowired
    private MemberRoleService memberRoleService;

    @Autowired
    private MemberCardService memberCardService;

    @Autowired
    private MemberRechargeService memberRechargeService;
    
    // ==================== 工具类依赖注入 ====================

    @Autowired
    private PasswordEncoder passwordEncoder;

    // ==================== 会员基础管理接口 ====================

    /**
     * 新增会员
     * 
     * <p>创建新的会员账户，包括用户名重复性检查和密码加密处理</p>
     * 
     * @param member 会员信息对象
     * @return 操作结果，包含成功或失败信息
     */
    @PostMapping
    public ResultVo add(@RequestBody Member member) {
        // 判断卡号是否重复
        QueryWrapper<Member> query = new QueryWrapper<>();
        query.lambda().eq(Member::getUsername, member.getUsername());
        Member one = memberService.getOne(query);
        if (one != null) {
            return ResultUtils.error("会员卡号被占用!");
        }
        
        // 加密密码并保存
        member.setPassword(passwordEncoder.encode(member.getPassword()));
        memberService.addMember(member);
        return ResultUtils.success("新增成功!");
    }

    /**
     * 编辑会员信息
     * 
     * <p>更新会员信息，包括用户名重复性检查（排除自身）</p>
     * 
     * @param member 会员信息对象
     * @return 操作结果，包含成功或失败信息
     */
    @PutMapping
    public ResultVo edit(@RequestBody Member member) {
        // 判断卡号是否重复（排除自身）
        QueryWrapper<Member> query = new QueryWrapper<>();
        query.lambda().eq(Member::getUsername, member.getUsername());
        Member one = memberService.getOne(query);
        if (one != null && !one.getMemberId().equals(member.getMemberId())) {
            return ResultUtils.error("会员卡号被占用!");
        }
        
        memberService.editMember(member);
        return ResultUtils.success("编辑成功!");
    }

    /**
     * 删除会员
     * 
     * <p>根据会员ID删除指定会员</p>
     * 
     * @param memberId 会员ID
     * @return 操作结果，包含成功信息
     */
    @DeleteMapping("/{memberId}")
    public ResultVo delete(@PathVariable("memberId") Long memberId) {
        memberService.deleteMember(memberId);
        return ResultUtils.success("删除成功!");
    }

    /**
     * 分页查询会员列表
     * 
     * <p>根据用户类型和查询条件分页查询会员信息</p>
     * <ul>
     *   <li>用户类型为1（会员）：查询指定会员ID的信息</li>
     *   <li>用户类型为其他（管理员）：查询所有会员信息</li>
     * </ul>
     * 
     * @param pageParam 分页查询参数，包含页码、页大小、查询条件等
     * @return 分页查询结果
     */
    @GetMapping("/list")
    public ResultVo list(PageParam pageParam) {
        if (pageParam.getUserType().equals("1")) {
            // 会员查询自己的信息
            return getMemberListForMember(pageParam);
        } else {
            // 管理员查询所有会员信息
            return getMemberListForAdmin(pageParam);
        }
    }

    /**
     * 根据会员ID查询角色信息
     * 
     * <p>查询指定会员的角色配置信息</p>
     * 
     * @param memberId 会员ID
     * @return 会员角色信息
     */
    @GetMapping("/getRoleByMemberId")
    public ResultVo getRoleByMemberId(Long memberId) {
        QueryWrapper<MemberRole> query = new QueryWrapper<>();
        query.lambda().eq(MemberRole::getMemberId, memberId);
        MemberRole one = memberRoleService.getOne(query);
        return ResultUtils.success("查询成功", one);
    }

    // ==================== 私有辅助方法 ====================

    /**
     * 会员查询自己的信息
     * 
     * @param pageParam 查询参数
     * @return 查询结果
     */
    private ResultVo getMemberListForMember(PageParam pageParam) {
        // 构造分页对象
        IPage<Member> page = new Page<>(pageParam.getCurrentPage(), pageParam.getPageSize());
        
        // 构造查询条件
        QueryWrapper<Member> query = new QueryWrapper<>();
        if (StringUtils.isNotEmpty(pageParam.getName())) {
            query.lambda().like(Member::getName, pageParam.getName());
        }
        if (StringUtils.isNotEmpty(pageParam.getPhone())) {
            query.lambda().like(Member::getPhone, pageParam.getPhone());
        }
        if (StringUtils.isNotEmpty(pageParam.getUsername())) {
            query.lambda().like(Member::getUsername, pageParam.getUsername());
        }
        
        query.lambda().eq(Member::getMemberId, pageParam.getMemberId());
        query.lambda().orderByDesc(Member::getJoinTime);
        
        IPage<Member> list = memberService.page(page, query);
        return ResultUtils.success("查询成功", list);
    }

    /**
     * 管理员查询所有会员信息
     * 
     * @param pageParam 查询参数
     * @return 查询结果
     */
    private ResultVo getMemberListForAdmin(PageParam pageParam) {
        // 构造分页对象
        IPage<Member> page = new Page<>(pageParam.getCurrentPage(), pageParam.getPageSize());
        
        // 构造查询条件
        QueryWrapper<Member> query = new QueryWrapper<>();
        if (StringUtils.isNotEmpty(pageParam.getName())) {
            query.lambda().like(Member::getName, pageParam.getName());
        }
        if (StringUtils.isNotEmpty(pageParam.getPhone())) {
            query.lambda().like(Member::getPhone, pageParam.getPhone());
        }
        if (StringUtils.isNotEmpty(pageParam.getUsername())) {
            query.lambda().like(Member::getUsername, pageParam.getUsername());
        }
        
        query.lambda().orderByDesc(Member::getJoinTime);
        
        IPage<Member> list = memberService.page(page, query);
        return ResultUtils.success("查询成功", list);
    }

    // ==================== 会员卡管理接口 ====================

    /**
     * 查询可用会员卡列表
     * 
     * <p>查询状态为启用的会员卡类型列表，用于办卡时选择</p>
     * 
     * @return 可用会员卡列表
     */
    @GetMapping("/getCardList")
    public ResultVo getCardList() {
        QueryWrapper<MemberCard> query = new QueryWrapper<>();
        query.lambda().eq(MemberCard::getStatus, "1");
        List<MemberCard> list = memberCardService.list(query);
        return ResultUtils.success("查询成功", list);
    }

    /**
     * 办卡申请提交
     * 
     * <p>处理会员办卡申请，创建新的会员账户和卡片关联</p>
     * 
     * @param joinParam 办卡申请参数，包含会员信息和卡片信息
     * @return 办卡结果
     * @throws ParseException 日期解析异常
     */
    @PostMapping("/joinApply")
    public ResultVo joinApply(@RequestBody JoinParam joinParam) throws ParseException {
        memberService.joinApply(joinParam);
        return ResultUtils.success("办卡成功!");
    }

    // ==================== 充值管理接口 ====================

    /**
     * 会员充值
     * 
     * <p>处理会员账户充值操作，增加会员余额</p>
     * 
     * @param param 充值参数，包含充值金额和会员信息
     * @return 充值结果
     */
    @PostMapping("/recharge")
    public ResultVo recharge(@RequestBody RechargeParam param) {
        memberService.recharge(param);
        return ResultUtils.success("充值成功!");
    }

    /**
     * 查询充值记录
     * 
     * <p>分页查询充值记录，按创建时间倒序排列</p>
     * 
     * @param pageParam 分页查询参数
     * @return 充值记录列表
     */
    @GetMapping("/myRecharge")
    public ResultVo myRecharge(PageParam pageParam) {
        // 构造分页对象
        IPage<MemberRecharge> page = new Page<>(
                pageParam.getCurrentPage(), pageParam.getPageSize());
        
        // 构造查询条件
        QueryWrapper<MemberRecharge> query = new QueryWrapper<>();
        // 这里可以根据需要添加会员ID过滤条件
        // if(pageParam.getMemberId() != null){
        //     query.lambda().eq(MemberRecharge::getMemberId, pageParam.getMemberId());
        // }
        // 改为按时间正序排列（时间越早越靠前）
        query.lambda().orderByAsc(MemberRecharge::getCreateTime);
        
        IPage<MemberRecharge> list = memberRechargeService.page(page, query);
        return ResultUtils.success("查询成功", list);
    }

    /**
     * 根据用户类型查询充值记录
     * 
     * <p>根据用户类型（会员或员工）查询相应的充值记录</p>
     * <ul>
     *   <li>用户类型为1（会员）：查询指定会员的充值记录</li>
     *   <li>用户类型为2（员工）：查询所有充值记录</li>
     * </ul>
     * 
     * @param param 查询参数，包含用户类型和其他筛选条件
     * @return 充值记录查询结果
     */
    @GetMapping("/getMyRecharge")
    public ResultVo getMyRecharge(RechargeParamList param) {
        // 判断是会员还是员工
        if (param.getUserType().equals("1")) {
            // 会员查询自己的充值记录
            IPage<MemberRecharge> list = memberRechargeService.getRecherByMember(param);
            return ResultUtils.success("查询成功", list);
        } else if (param.getUserType().equals("2")) {
            // 员工查询所有充值记录
            IPage<MemberRecharge> list = memberRechargeService.getRecherList(param);
            return ResultUtils.success("查询成功", list);
        } else {
            return ResultUtils.error("用户类型不存在!");
        }
    }

}
