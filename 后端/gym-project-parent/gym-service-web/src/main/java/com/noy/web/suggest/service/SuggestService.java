package com.noy.web.suggest.service;

// MyBatis Plus 相关
import com.baomidou.mybatisplus.extension.service.IService;

// 项目内部实体类
import com.noy.web.suggest.entity.Suggest;

/**
 * 建议反馈服务接口
 * 
 * <p>定义建议反馈管理相关的业务逻辑接口，继承MyBatis Plus的IService接口</p>
 * 
 * <p>主要功能包括：</p>
 * <ul>
 *   <li>建议信息管理 - 增删改查操作</li>
 *   <li>建议状态管理 - 待处理、处理中、已完成</li>
 *   <li>建议分类管理 - 按类型分类建议</li>
 *   <li>建议统计分析 - 建议数量和处理效率</li>
 * </ul>
 * 
 * @author noy
 * @version 1.0
 * @since 2024
 */
public interface SuggestService extends IService<Suggest> {
    // 继承IService接口，提供基础的CRUD操作
    // 具体的业务逻辑方法可在实现类中添加
}
