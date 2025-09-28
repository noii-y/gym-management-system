package com.noy.web.member_apply.service;

// MyBatis Plus 相关
import com.baomidou.mybatisplus.extension.service.IService;

// 项目内部实体类
import com.noy.web.member_apply.entity.MemberApply;

/**
 * 会员申请服务接口
 * 
 * <p>定义会员申请管理相关的业务逻辑接口，继承MyBatis Plus的IService接口</p>
 * 
 * <p>主要功能包括：</p>
 * <ul>
 *   <li>会员申请信息管理 - 增删改查操作</li>
 *   <li>申请状态管理 - 待审核、已通过、已拒绝</li>
 *   <li>申请审核流程 - 申请提交和审核处理</li>
 *   <li>申请统计分析 - 申请数量和通过率统计</li>
 * </ul>
 * 
 * @author noy
 * @version 1.0
 * @since 2024
 */
public interface MemberApplyService extends IService<MemberApply> {
    // 继承IService接口，提供基础的CRUD操作
    // 具体的业务逻辑方法可在实现类中添加
}
