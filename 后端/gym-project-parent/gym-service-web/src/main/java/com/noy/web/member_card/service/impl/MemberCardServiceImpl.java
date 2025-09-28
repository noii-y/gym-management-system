package com.noy.web.member_card.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.noy.web.member_card.entity.MemberCard;
import com.noy.web.member_card.mapper.MemberCardMapper;
import com.noy.web.member_card.service.MemberCardService;
import org.springframework.stereotype.Service;

/**
 * 会员卡服务实现类
 * 
 * <p>提供会员卡相关的业务逻辑处理，包括：</p>
 * <ul>
 *   <li>会员卡信息的基础CRUD操作</li>
 *   <li>会员卡类型管理</li>
 *   <li>会员卡价格和有效期管理</li>
 *   <li>会员卡状态控制</li>
 *   <li>继承MyBatis Plus的通用服务方法</li>
 * </ul>
 * 
 * @author noy
 * @version 1.0
 * @since 2024
 */
@Service
public class MemberCardServiceImpl extends ServiceImpl<MemberCardMapper, MemberCard> implements MemberCardService {
}
