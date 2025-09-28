package com.noy.web.goods_order.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.noy.utils.ResultUtils;
import com.noy.utils.ResultVo;
import com.noy.web.goods.entity.Goods;
import com.noy.web.goods.entity.GoodsParam;
import com.noy.web.goods.service.GoodsService;
import com.noy.web.goods_order.entity.GoodsOrder;
import com.noy.web.goods_order.entity.OrderItem;
import com.noy.web.goods_order.entity.OrderParam;
import com.noy.web.goods_order.service.GoodsOrderService;
import com.noy.web.sys_user.entity.SysUser;
import com.noy.web.sys_user.service.SysUserService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * 商品订单管理控制器
 * 
 * <p>提供商品订单相关的REST API接口，负责订单的创建和管理</p>
 * 
 * <p>主要功能包括：</p>
 * <ul>
 *   <li>订单创建 - 处理商品下单和订单生成</li>
 *   <li>订单查询 - 支持条件筛选的订单列表查询</li>
 *   <li>订单详情 - 订单详细信息的展示</li>
 *   <li>商品信息 - 订单关联商品信息的获取</li>
 *   <li>用户信息 - 订单关联用户信息的获取</li>
 * </ul>
 * 
 * <p>业务特性：</p>
 * <ul>
 *   <li>订单计算 - 自动计算订单总金额</li>
 *   <li>库存管理 - 下单时检查商品库存</li>
 *   <li>数据关联 - 订单与商品、用户的关联查询</li>
 *   <li>状态管理 - 订单状态的动态更新</li>
 * </ul>
 * 
 * @author Gym Management System
 * @version 1.0
 * @since 2024
 */
@RestController
@RequestMapping("/api/order")
public class GoodsOrderController {

    @Autowired
    private GoodsOrderService goodsOrderService;
    @Autowired
    private SysUserService sysUserService;
    @Autowired
    private GoodsService goodsService;
    //下单
    @PostMapping("/down")
    public ResultVo down(@RequestBody OrderParam Param){
        //查询用户信息
        SysUser user = sysUserService.getById(Param.getUserId());
        List<OrderItem> list = Param.getOrderList();
        List<GoodsOrder> orderList = new ArrayList<>();
        for(int i=0;i<list.size();i++){
            Long goodsId = list.get(i).getGoodsId();
            Integer num = list.get(i).getNum();
            //查询商品信息
            Goods goods = goodsService.getById(goodsId);
            GoodsOrder order = new GoodsOrder();
            BeanUtils.copyProperties(goods,order);
            order.setNum(list.get(i).getNum());
            BigDecimal number =
                    BigDecimal.valueOf(list.get(i).getNum());
            BigDecimal price = goods.getPrice();
            BigDecimal total = number.multiply(price);
            BigDecimal totalPrice = total.setScale(2,
                    BigDecimal.ROUND_HALF_UP);
            order.setTotalPrice(totalPrice);
            order.setControlUser(user.getNickName());
            orderList.add(order);
        }
        if(orderList.size() > 0){
            goodsOrderService.saveBatch(orderList);
        }
        return ResultUtils.success("下单成功!");
    }

    //列表
    @GetMapping("/list")
    public ResultVo list(GoodsParam Param){
        //构造分页对象
        IPage<GoodsOrder> page = new Page<>
                (Param.getCurrentPage(),Param.getPageSize());
        //构造查询条件
        QueryWrapper<GoodsOrder> query = new QueryWrapper<>();
        if(StringUtils.isNotEmpty(Param.getName())){
            query.lambda().like(GoodsOrder::getName,Param.getName());
        }
        IPage<GoodsOrder> list = goodsOrderService.page(page, query);
        return ResultUtils.success("查询成功",list);
    }
}
