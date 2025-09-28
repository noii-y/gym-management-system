package com.noy.web.lost.service;

// MyBatis Plus 相关
import com.baomidou.mybatisplus.extension.service.IService;

// 项目内部实体类
import com.noy.web.lost.entity.Lost;

/**
 * 失物招领服务接口
 * 
 * <p>定义失物招领管理相关的业务逻辑接口，继承MyBatis Plus的IService接口</p>
 * 
 * <p>主要功能包括：</p>
 * <ul>
 *   <li>失物信息管理 - 增删改查操作</li>
 *   <li>失物状态管理 - 待认领、已认领、已处理</li>
 *   <li>失物分类管理 - 按物品类型分类</li>
 *   <li>失物查询功能 - 多条件搜索失物</li>
 * </ul>
 * 
 * @author noy
 * @version 1.0
 * @since 2024
 */
public interface LostService extends IService<Lost> {
    // 继承IService接口，提供基础的CRUD操作
    // 具体的业务逻辑方法可在实现类中添加
}
