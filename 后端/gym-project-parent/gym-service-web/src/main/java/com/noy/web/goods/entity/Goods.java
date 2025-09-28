package com.noy.web.goods.entity;

// Java 标准库
import java.math.BigDecimal;

// MyBatis Plus 相关
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

// Lombok 注解
import lombok.Data;

/**
 * 商品实体类
 * 
 * <p>对应数据库中的goods表，用于管理健身房的商品信息</p>
 * 
 * <p>包含商品的基本信息、价格、库存等</p>
 * 
 * @author noy
 * @version 1.0
 * @since 2024
 */
@Data
@TableName("goods")
public class Goods {

    /**
     * 商品ID（主键，自增）
     */
    @TableId(type = IdType.AUTO)
    private Long goodsId;
    
    /**
     * 商品名称
     */
    private String name;
    
    /**
     * 商品图片
     */
    private String image;
    
    /**
     * 商品详情描述
     */
    private String details;
    
    /**
     * 商品单位
     */
    private String unit;
    
    /**
     * 商品规格
     */
    private String specs;
    
    /**
     * 商品价格
     */
    private BigDecimal price;
    
    /**
     * 库存数量
     */
    private Integer store;
}
