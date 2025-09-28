package com.noy.web.goods_order.service;

// Java 标准库
import java.util.List;

// MyBatis Plus 相关
import com.baomidou.mybatisplus.extension.service.IService;

// 项目内部实体类
import com.noy.web.goods_order.entity.GoodsOrder;
import com.noy.web.home.entity.EchartItem;

/**
 * 商品订单服务接口
 * 
 * <p>定义商品订单管理相关的业务逻辑接口，继承MyBatis Plus的IService接口</p>
 * 
 * <p>主要功能包括：</p>
 * <ul>
 *   <li>订单基础信息管理 - 增删改查操作</li>
 *   <li>订单状态管理 - 待支付、已支付、已完成、已取消</li>
 *   <li>销售统计分析 - 热销商品、卡片、课程统计</li>
 *   <li>订单数据报表 - 销售趋势和收入分析</li>
 * </ul>
 * 
 * @author noy
 * @version 1.0
 * @since 2024
 */
public interface GoodsOrderService extends IService<GoodsOrder> {

    /**
     * 获取热销商品统计
     * 
     * @return 热销商品列表，包含商品名称和销量数据
     */
    List<EchartItem> hotGoods();
    
    /**
     * 获取热销卡片统计
     * 
     * @return 热销卡片列表，包含卡片类型和销量数据
     */
    List<EchartItem> hotCards();
    
    /**
     * 获取热销课程统计
     * 
     * @return 热销课程列表，包含课程名称和报名数据
     */
    List<EchartItem> hotCourse();
}
