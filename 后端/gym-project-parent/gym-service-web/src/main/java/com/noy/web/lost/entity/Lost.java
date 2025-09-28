package com.noy.web.lost.entity;

// MyBatis Plus 相关
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

// Lombok 注解
import lombok.Data;

/**
 * 失物招领实体类
 * 
 * <p>对应数据库中的lost表，用于管理健身房的失物招领信息</p>
 * 
 * <p>包含失物的基本信息、发现信息、状态等</p>
 * 
 * @author noy
 * @version 1.0
 * @since 2024
 */
@Data
@TableName("lost")
public class Lost {

    /**
     * 失物ID（主键，自增）
     */
    @TableId(type = IdType.AUTO)
    private Long lostId;
    
    /**
     * 失物名称
     */
    private String lostName;
    
    /**
     * 发现时间
     */
    private String foundTime;
    
    /**
     * 发现地址
     */
    private String foundAddres;
    
    /**
     * 发现人
     */
    private String foundPerson;
    
    /**
     * 发现人电话
     */
    private String foundPhone;
    
    /**
     * 状态
     */
    private String status;
    
    /**
     * 失主
     */
    private String lostPerson;
}
