package com.noy.web.member_recharge.service.impl;

// MyBatis Plus 相关
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

// Spring 框架相关
import org.springframework.stereotype.Service;

// 项目内部类
import com.noy.web.member.entity.RechargeParamList;
import com.noy.web.member_recharge.entity.MemberRecharge;
import com.noy.web.member_recharge.mapper.MemberRechargeMapper;
import com.noy.web.member_recharge.service.MemberRechargeService;

/**
 * 会员充值服务实现类
 * 
 * <p>提供会员充值相关的业务逻辑处理，包括：</p>
 * <ul>
 *   <li>充值记录查询</li>
 *   <li>会员充值记录分页查询</li>
 *   <li>充值明细管理</li>
 * </ul>
 * 
 * @author noy
 * @version 1.0
 * @since 2024
 */
@Service
public class MemberRechargeServiceImpl extends ServiceImpl<MemberRechargeMapper, MemberRecharge> implements MemberRechargeService {

    // ==================== 查询方法 ====================

    /**
     * 获取充值记录列表
     * 
     * <p>分页查询所有会员的充值记录</p>
     * 
     * @param paramList 查询参数，包含分页信息
     * @return 分页的充值记录列表
     */
    @Override
    public IPage<MemberRecharge> getRecherList(RechargeParamList paramList) {
        // 构造分页对象
        IPage<MemberRecharge> page = new Page<>(paramList.getCurrentPage(), paramList.getPageSize());
        return this.baseMapper.getRecherList(page);
    }

    /**
     * 根据会员ID获取充值记录
     * 
     * <p>分页查询指定会员的充值记录</p>
     * 
     * @param paramList 查询参数，包含会员ID和分页信息
     * @return 分页的会员充值记录列表
     */
    @Override
    public IPage<MemberRecharge> getRecherByMember(RechargeParamList paramList) {
        // 构造分页对象
        IPage<MemberRecharge> page = new Page<>(paramList.getCurrentPage(), paramList.getPageSize());
        return this.baseMapper.getRecherByMember(page, paramList.getMemberId());
    }
}
