package com.noy.web.goods.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.noy.web.goods.entity.Goods;
import com.noy.web.goods.mapper.GoodsMapper;
import com.noy.web.goods.service.GoodsService;
import org.springframework.stereotype.Service;

/**
 * 商品服务实现类
 * 
 * <p>提供商品相关的业务逻辑处理，包括：</p>
 * <ul>
 *   <li>商品信息的基础CRUD操作</li>
 *   <li>商品库存管理</li>
 *   <li>商品分类管理</li>
 *   <li>商品价格管理</li>
 *   <li>继承MyBatis Plus的通用服务方法</li>
 * </ul>
 * 
 * @author noy
 * @version 1.0
 * @since 2024
 */
@Service
public class GoodsServiceImpl extends ServiceImpl<GoodsMapper, Goods> implements GoodsService {
}
