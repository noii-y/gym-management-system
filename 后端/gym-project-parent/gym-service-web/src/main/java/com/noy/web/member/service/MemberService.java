package com.noy.web.member.service;

// MyBatis Plus 相关
import com.baomidou.mybatisplus.extension.service.IService;

// 项目内部实体类
import com.noy.web.member.entity.JoinParam;
import com.noy.web.member.entity.Member;
import com.noy.web.member.entity.RechargeParam;

// Java 标准库
import java.text.ParseException;

/**
 * 会员服务接口
 * 
 * <p>定义会员管理相关的业务逻辑接口，继承MyBatis Plus的IService接口</p>
 * 
 * <p>主要功能包括：</p>
 * <ul>
 *   <li>会员基础信息管理 - 增删改查操作</li>
 *   <li>会员申请处理 - 办卡申请业务逻辑</li>
 *   <li>会员充值功能 - 账户余额管理</li>
 *   <li>用户认证支持 - 用户名查询功能</li>
 * </ul>
 * 
 * @author noy
 * @version 1.0
 * @since 2024
 */
public interface MemberService extends IService<Member> {

    /**
     * 添加新会员
     * 
     * <p>处理新会员的注册逻辑，包括数据验证和初始化</p>
     * 
     * @param member 会员信息对象
     */
    void addMember(Member member);
    
    /**
     * 编辑会员信息
     * 
     * <p>更新已存在会员的基本信息</p>
     * 
     * @param member 包含更新信息的会员对象
     */
    void editMember(Member member);
    
    /**
     * 删除会员
     * 
     * <p>根据会员ID删除会员信息</p>
     * 
     * @param memberId 会员ID
     */
    void deleteMember(Long memberId);
    
    /**
     * 处理入会申请
     * 
     * <p>处理用户的入会申请，包括会员卡类型选择和有效期计算</p>
     * 
     * @param joinParam 入会申请参数
     * @throws ParseException 日期解析异常
     */
    void joinApply(JoinParam joinParam) throws ParseException;
    
    /**
     * 会员充值
     * 
     * <p>处理会员账户充值逻辑，更新账户余额</p>
     * 
     * @param param 充值参数
     */
    void recharge(RechargeParam param);

    /**
     * 根据用户名加载用户信息
     * 
     * <p>用于Spring Security认证，根据用户名查询会员信息</p>
     * 
     * @param username 用户名
     * @return 会员信息对象
     */
    Member loadUser(String username);
}
