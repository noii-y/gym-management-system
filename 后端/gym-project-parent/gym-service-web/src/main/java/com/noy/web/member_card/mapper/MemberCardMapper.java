package com.noy.web.member_card.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.noy.web.member_card.entity.MemberCard;

/**
 * 会员卡数据访问层接口
 * 
 * <p>继承MyBatis Plus的BaseMapper，提供会员卡相关的数据库操作，包括：</p>
 * <ul>
 *   <li>基础的CRUD操作（增删改查）</li>
 *   <li>会员卡信息的条件查询</li>
 *   <li>会员卡状态管理</li>
 * </ul>
 * 
 * @author noy
 * @version 1.0
 * @since 2024
 */
public interface MemberCardMapper extends BaseMapper<MemberCard> {
}
