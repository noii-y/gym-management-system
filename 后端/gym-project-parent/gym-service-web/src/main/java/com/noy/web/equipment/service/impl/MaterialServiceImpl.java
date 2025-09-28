package com.noy.web.equipment.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.noy.web.equipment.entity.Material;
import com.noy.web.equipment.mapper.MaterialMapper;
import com.noy.web.equipment.service.MaterialService;
import org.springframework.stereotype.Service;

/**
 * 器材管理服务实现类
 * 
 * <p>提供健身器材相关的业务逻辑处理，包括：</p>
 * <ul>
 *   <li>器材信息的基础CRUD操作</li>
 *   <li>器材库存管理</li>
 *   <li>器材状态监控</li>
 *   <li>器材分类管理</li>
 *   <li>继承MyBatis Plus的通用服务方法</li>
 * </ul>
 * 
 * @author noy
 * @version 1.0
 * @since 2024
 */
@Service
public class MaterialServiceImpl extends ServiceImpl<MaterialMapper, Material> implements MaterialService {
}
