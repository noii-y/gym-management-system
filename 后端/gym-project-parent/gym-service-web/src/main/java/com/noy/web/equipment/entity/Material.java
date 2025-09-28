package com.noy.web.equipment.entity;

// MyBatis Plus 相关
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

// Lombok 注解
import lombok.Data;

/**
 * 器材实体类
 * 
 * <p>对应数据库中的material表，用于管理健身房的器材信息</p>
 * 
 * <p>包含器材的基本信息、详情描述、数量等</p>
 * 
 * @author noy
 * @version 1.0
 * @since 2024
 */
@Data
@TableName("material")
public class Material {

    /**
     * 器材ID（主键，自增）
     */
    @TableId(type = IdType.AUTO)
    private Long id;
    
    /**
     * 器材名称
     */
    private String name;
    
    /**
     * 器材详情描述
     */
    private String details;
    
    /**
     * 器材总数量
     */
    private Integer numTotal;
}
