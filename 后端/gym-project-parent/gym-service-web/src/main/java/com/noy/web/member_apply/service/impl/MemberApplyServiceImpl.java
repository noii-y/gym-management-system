package com.noy.web.member_apply.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.noy.web.member_apply.entity.MemberApply;
import com.noy.web.member_apply.mapper.MemberApplyMapper;
import com.noy.web.member_apply.service.MemberApplyService;
import org.springframework.stereotype.Service;

/**
 * 会员申请服务实现类
 * 
 * <p>提供会员申请相关的业务逻辑处理，包括：</p>
 * <ul>
 *   <li>会员申请信息的基础CRUD操作</li>
 *   <li>会员卡申请流程管理</li>
 *   <li>申请状态跟踪</li>
 *   <li>申请审核处理</li>
 *   <li>继承MyBatis Plus的通用服务方法</li>
 * </ul>
 * 
 * @author noy
 * @version 1.0
 * @since 2024
 */
@Service
public class MemberApplyServiceImpl extends ServiceImpl<MemberApplyMapper, MemberApply> implements MemberApplyService {
}
