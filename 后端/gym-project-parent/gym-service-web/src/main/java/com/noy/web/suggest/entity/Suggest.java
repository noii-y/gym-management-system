package com.noy.web.suggest.entity;

// Java 标准库
import java.util.Date;

// MyBatis Plus 相关
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

// Jackson JSON 处理
import com.fasterxml.jackson.annotation.JsonFormat;

// Lombok 注解
import lombok.Data;

/**
 * 建议反馈实体类
 * 
 * <p>对应数据库中的suggest表，用于管理用户的建议和反馈信息</p>
 * 
 * <p>包含建议的标题、内容和提交时间等信息</p>
 * 
 * @author noy
 * @version 1.0
 * @since 2024
 */
@Data
@TableName("suggest")
public class Suggest {

    /**
     * 建议ID（主键，自增）
     */
    @TableId(type = IdType.AUTO)
    private Long id;
    
    /**
     * 建议标题
     */
    private String title;
    
    /**
     * 建议内容
     */
    private String content;
    
    /**
     * 提交时间
     * 格式化为：yyyy-MM-dd HH:mm:ss，时区：GMT+8
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date dateTime;
}
