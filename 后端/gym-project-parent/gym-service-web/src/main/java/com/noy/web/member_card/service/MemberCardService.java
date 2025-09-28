package com.noy.web.member_card.service;

// MyBatis Plus 相关
import com.baomidou.mybatisplus.extension.service.IService;

// 项目内部实体类
import com.noy.web.member_card.entity.MemberCard;

/**
 * 会员卡服务接口
 * 
 * <p>定义会员卡管理相关的业务逻辑接口，继承MyBatis Plus的IService接口</p>
 * 
 * <p>主要功能包括：</p>
 * <ul>
 *   <li>会员卡信息管理 - 增删改查操作</li>
 *   <li>会员卡类型管理 - 不同类型卡片配置</li>
 *   <li>会员卡状态管理 - 激活、冻结、过期状态</li>
 *   <li>会员卡统计分析 - 销售和使用情况统计</li>
 * </ul>
 * 
 * @author noy
 * @version 1.0
 * @since 2024
 */
public interface MemberCardService extends IService<MemberCard> {
    // 继承IService接口，提供基础的CRUD操作
    // 具体的业务逻辑方法可在实现类中添加
}
