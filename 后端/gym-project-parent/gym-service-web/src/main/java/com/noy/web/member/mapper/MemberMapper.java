package com.noy.web.member.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.noy.web.member.entity.Member;
import com.noy.web.member.entity.RechargeParam;
import org.apache.ibatis.annotations.Param;

/**
 * 会员数据访问层接口
 * 
 * <p>继承MyBatis Plus的BaseMapper，提供会员相关的数据库操作，包括：</p>
 * <ul>
 *   <li>基础的CRUD操作（增删改查）</li>
 *   <li>会员信息的条件查询和分页查询</li>
 *   <li>会员余额管理（充值、扣费）</li>
 *   <li>会员状态管理</li>
 * </ul>
 * 
 * @author noy
 * @version 1.0
 * @since 2024
 */
public interface MemberMapper extends BaseMapper<Member> {

    /**
     * 增加会员余额
     * 
     * @param param 充值参数，包含会员ID和充值金额
     * @return 影响的行数
     */
    int addMoney(@Param("param") RechargeParam param);

    /**
     * 扣减会员余额
     * 
     * @param param 扣费参数，包含会员ID和扣费金额
     * @return 影响的行数
     */
    int subMoney(@Param("param") RechargeParam param);
}
