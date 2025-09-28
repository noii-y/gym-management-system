package com.noy.web.goods.service;

// MyBatis Plus 相关
import com.baomidou.mybatisplus.extension.service.IService;

// 项目内部实体类
import com.noy.web.goods.entity.Goods;

/**
 * 商品服务接口
 * 
 * <p>定义商品管理相关的业务逻辑接口，继承MyBatis Plus的IService接口</p>
 * 
 * <p>主要功能包括：</p>
 * <ul>
 *   <li>商品基础信息管理 - 增删改查操作</li>
 *   <li>商品库存管理 - 库存增减和查询</li>
 *   <li>商品分类管理 - 按类别筛选商品</li>
 *   <li>商品销售统计 - 销量和收入统计</li>
 * </ul>
 * 
 * @author noy
 * @version 1.0
 * @since 2024
 */
public interface GoodsService extends IService<Goods> {
    // 继承IService接口，提供基础的CRUD操作
    // 具体的业务逻辑方法可在实现类中添加
}
