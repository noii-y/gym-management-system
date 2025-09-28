package com.noy.web.equipment.service;

// MyBatis Plus 相关
import com.baomidou.mybatisplus.extension.service.IService;

// 项目内部实体类
import com.noy.web.equipment.entity.Material;

/**
 * 器材服务接口
 * 
 * <p>定义健身器材管理相关的业务逻辑接口，继承MyBatis Plus的IService接口</p>
 * 
 * <p>主要功能包括：</p>
 * <ul>
 *   <li>器材基础信息管理 - 增删改查操作</li>
 *   <li>器材状态管理 - 正常、维修、报废状态</li>
 *   <li>器材使用记录 - 使用时长和频率统计</li>
 *   <li>器材维护管理 - 维护计划和记录</li>
 * </ul>
 * 
 * @author noy
 * @version 1.0
 * @since 2024
 */
public interface MaterialService extends IService<Material> {
    // 继承IService接口，提供基础的CRUD操作
    // 具体的业务逻辑方法可在实现类中添加
}
