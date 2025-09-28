package com.noy.web.goods_order.service.impl;

// Java 标准库
import java.util.List;

// MyBatis Plus 相关
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

// Spring 框架相关
import org.springframework.stereotype.Service;

// 项目内部类
import com.noy.web.goods_order.entity.GoodsOrder;
import com.noy.web.goods_order.mapper.GoodsOrderMapper;
import com.noy.web.goods_order.service.GoodsOrderService;
import com.noy.web.home.entity.EchartItem;

/**
 * 商品订单服务实现类
 * 
 * <p>提供商品订单相关的业务逻辑处理，包括：</p>
 * <ul>
 *   <li>热门商品统计</li>
 *   <li>热门会员卡统计</li>
 *   <li>热门课程统计</li>
 * </ul>
 * 
 * @author noy
 * @version 1.0
 * @since 2024
 */
@Service
public class GoodsOrderServiceImpl extends ServiceImpl<GoodsOrderMapper, GoodsOrder> implements GoodsOrderService {

    // ==================== 统计查询方法 ====================

    /**
     * 获取热门商品统计
     * 
     * <p>查询销量最高的商品列表，用于图表展示</p>
     * 
     * @return 热门商品统计数据列表
     */
    @Override
    public List<EchartItem> hotGoods() {
        return this.baseMapper.hotGoods();
    }

    /**
     * 获取热门会员卡统计
     * 
     * <p>查询销量最高的会员卡列表，用于图表展示</p>
     * 
     * @return 热门会员卡统计数据列表
     */
    @Override
    public List<EchartItem> hotCards() {
        return this.baseMapper.hotCards();
    }

    /**
     * 获取热门课程统计
     * 
     * <p>查询报名人数最多的课程列表，用于图表展示</p>
     * 
     * @return 热门课程统计数据列表
     */
    @Override
    public List<EchartItem> hotCourse() {
        return this.baseMapper.hotCourse();
    }
}
