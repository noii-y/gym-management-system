package com.noy.web.member_recharge.service;

// MyBatis Plus 相关
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

// 项目内部实体类
import com.noy.web.member.entity.RechargeParamList;
import com.noy.web.member_recharge.entity.MemberRecharge;

/**
 * 会员充值服务接口
 * 
 * <p>定义会员充值管理相关的业务逻辑接口，继承MyBatis Plus的IService接口</p>
 * 
 * <p>主要功能包括：</p>
 * <ul>
 *   <li>充值记录管理 - 增删改查操作</li>
 *   <li>充值记录查询 - 分页查询和条件筛选</li>
 *   <li>会员充值统计 - 充值金额和频次统计</li>
 *   <li>充值数据分析 - 充值趋势和收入分析</li>
 * </ul>
 * 
 * @author noy
 * @version 1.0
 * @since 2024
 */
public interface MemberRechargeService extends IService<MemberRecharge> {
    
    /**
     * 分页查询充值记录列表
     * 
     * @param paramList 充值查询参数，包含分页信息和查询条件
     * @return 分页结果，包含充值记录列表和分页信息
     */
    IPage<MemberRecharge> getRecherList(RechargeParamList paramList);
    
    /**
     * 根据会员查询充值记录
     * 
     * @param paramList 充值查询参数，包含会员信息和分页条件
     * @return 分页结果，包含指定会员的充值记录
     */
    IPage<MemberRecharge> getRecherByMember(RechargeParamList paramList);
}
