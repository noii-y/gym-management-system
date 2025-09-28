package com.noy.web.member.service.impl;

// Java 标准库
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

// Java EE 相关
import javax.annotation.Resource;

// MyBatis Plus 相关
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

// Apache Commons 工具类
import org.apache.commons.lang.StringUtils;

// Spring 框架相关
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

// 项目内部类
import com.noy.web.member.entity.JoinParam;
import com.noy.web.member.entity.Member;
import com.noy.web.member.entity.RechargeParam;
import com.noy.web.member.mapper.MemberMapper;
import com.noy.web.member.service.MemberService;
import com.noy.web.member_apply.entity.MemberApply;
import com.noy.web.member_apply.mapper.MemberApplyMapper;
import com.noy.web.member_card.entity.MemberCard;
import com.noy.web.member_card.mapper.MemberCardMapper;
import com.noy.web.member_recharge.entity.MemberRecharge;
import com.noy.web.member_recharge.service.MemberRechargeService;
import com.noy.web.member_role.entity.MemberRole;
import com.noy.web.member_role.service.MemberRoleService;

/**
 * 会员服务实现类
 * 
 * <p>提供会员相关的业务逻辑处理，包括：</p>
 * <ul>
 *   <li>会员信息的增删改查操作</li>
 *   <li>会员角色管理</li>
 *   <li>会员卡申请和办理</li>
 *   <li>会员充值功能</li>
 *   <li>用户认证支持</li>
 * </ul>
 * 
 * @author noy
 * @version 1.0
 * @since 2024
 */
@Service
public class MemberServiceImpl extends ServiceImpl<MemberMapper, Member> implements MemberService {

    // ==================== 依赖注入 ====================
    
    /**
     * 会员卡数据访问层
     */
    @Resource
    private MemberCardMapper memberCardMapper;
    
    /**
     * 会员申请数据访问层
     */
    @Resource
    private MemberApplyMapper memberApplyMapper;

    /**
     * 会员角色业务逻辑服务
     */
    @Autowired
    private MemberRoleService memberRoleService;
    
    /**
     * 会员充值业务逻辑服务
     */
    @Autowired
    private MemberRechargeService memberRechargeService;

    // ==================== 会员管理业务方法 ====================

    /**
     * 新增会员
     * 
     * <p>添加新会员信息并设置默认角色</p>
     * <ul>
     *   <li>插入会员基本信息</li>
     *   <li>设置会员角色关联</li>
     * </ul>
     * 
     * @param member 会员信息对象
     */
    @Override
    @Transactional
    public void addMember(Member member) {
        // 新增会员
        int insert = this.baseMapper.insert(member);
        
        // 设置会员角色
        if (insert > 0) {
            MemberRole role = new MemberRole();
            role.setMemberId(member.getMemberId());
            role.setRoleId(member.getRoleId());
            memberRoleService.save(role);
        }
    }

    /**
     * 编辑会员信息
     * 
     * <p>更新会员信息并重新设置角色</p>
     * <ul>
     *   <li>更新会员基本信息</li>
     *   <li>删除原有角色关联</li>
     *   <li>重新设置角色关联</li>
     * </ul>
     * 
     * @param member 包含更新信息的会员对象
     */
    @Override
    @Transactional
    public void editMember(Member member) {
        int i = this.baseMapper.updateById(member);
        
        // 设置角色：先删除，再插入
        if (i > 0) {
            // 删除原有角色关联
            QueryWrapper<MemberRole> query = new QueryWrapper<>();
            query.lambda().eq(MemberRole::getMemberId, member.getMemberId());
            memberRoleService.remove(query);
            
            // 重新插入角色关联
            MemberRole role = new MemberRole();
            role.setMemberId(member.getMemberId());
            role.setRoleId(member.getRoleId());
            memberRoleService.save(role);
        }
    }

    /**
     * 删除会员
     * 
     * <p>删除会员信息及其相关的角色关联</p>
     * <ul>
     *   <li>删除会员基本信息</li>
     *   <li>删除会员角色关联</li>
     * </ul>
     * 
     * @param memberId 会员ID
     */
    @Override
    @Transactional
    public void deleteMember(Long memberId) {
        // 删除会员信息
        int i = this.baseMapper.deleteById(memberId);
        
        if (i > 0) {
            // 删除角色关联
            QueryWrapper<MemberRole> query = new QueryWrapper<>();
            query.lambda().eq(MemberRole::getMemberId, memberId);
            memberRoleService.remove(query);
        }
    }

    // ==================== 会员卡申请和充值方法 ====================

    /**
     * 会员卡申请办理
     * 
     * <p>处理会员的会员卡申请，包括：</p>
     * <ul>
     *   <li>更新会员的卡类型和有效期</li>
     *   <li>计算新的到期时间</li>
     *   <li>记录申请明细</li>
     * </ul>
     * 
     * @param joinParam 申请参数，包含会员ID和卡类型ID
     * @throws ParseException 日期解析异常
     */
    @Override
    @Transactional
    public void joinApply(JoinParam joinParam) throws ParseException {
        // 获取当前会员信息
        Member select = this.baseMapper.selectById(joinParam.getMemberId());
        
        // 获取会员卡信息
        MemberCard card = memberCardMapper.selectById(joinParam.getCardId());
        
        // 更新会员信息
        Member member = new Member();
        member.setMemberId(joinParam.getMemberId());
        member.setCardType(card.getTitle());
        member.setCardDay(card.getCardDay());
        member.setPrice(card.getPrice());
        
        // 计算新的到期时间
        String endTime = select.getEndTime();
        Calendar calendar = Calendar.getInstance();
        
        if (StringUtils.isNotEmpty(endTime)) {
            // 如果已有到期时间，在原基础上延长
            Date date = new SimpleDateFormat("yyyy-MM-dd").parse(select.getEndTime());
            calendar.setTime(date);
            calendar.add(Calendar.DATE, card.getCardDay());
        } else {
            // 如果没有到期时间，从当前时间开始计算
            Date data = new Date();
            calendar.setTime(data);
            calendar.add(Calendar.DATE, card.getCardDay());
        }
        
        member.setEndTime(new SimpleDateFormat("yyyy-MM-dd").format(calendar.getTime()));
        this.baseMapper.updateById(member);
        
        // 插入申请明细
        MemberApply memberApply = new MemberApply();
        memberApply.setCardDay(card.getCardDay());
        memberApply.setCardType(card.getTitle());
        memberApply.setMemberId(joinParam.getMemberId());
        memberApply.setPrice(card.getPrice());
        memberApply.setCreateTime(new Date());
        memberApplyMapper.insert(memberApply);
    }

    /**
     * 会员充值
     * 
     * <p>为会员账户充值，包括：</p>
     * <ul>
     *   <li>生成充值明细记录</li>
     *   <li>更新会员账户余额</li>
     * </ul>
     * 
     * @param param 充值参数，包含会员ID和充值金额
     */
    @Override
    @Transactional
    public void recharge(RechargeParam param) {
        // 生成充值明细
        MemberRecharge recharge = new MemberRecharge();
        recharge.setMemberId(param.getMemberId());
        recharge.setMoney(param.getMoney());
        
        boolean save = memberRechargeService.save(recharge);
        if (save) {
            // 更新会员账号的金额
            this.baseMapper.addMoney(param);
        }
    }

    // ==================== 用户认证方法 ====================

    /**
     * 根据用户名加载用户信息
     * 
     * <p>用于用户认证，根据用户名查询会员信息</p>
     * 
     * @param username 用户名
     * @return 会员信息，如果不存在则返回null
     */
    @Override
    public Member loadUser(String username) {
        QueryWrapper<Member> query = new QueryWrapper<>();
        query.lambda().eq(Member::getUsername, username);
        return this.baseMapper.selectOne(query);
    }
}
