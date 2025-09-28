package com.noy.web.course.entity;

// Java 标准库
import java.math.BigDecimal;

// MyBatis Plus 相关
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

// Lombok 注解
import lombok.Data;

/**
 * 课程实体类
 * 
 * <p>对应数据库中的course表，用于管理健身房的课程信息</p>
 * 
 * <p>包含课程的基本信息、教练信息、价格等</p>
 * 
 * @author noy
 * @version 1.0
 * @since 2024
 */
@Data
@TableName("course")
public class Course {

    /**
     * 课程ID（主键，自增）
     */
    @TableId(type = IdType.AUTO)
    private Long courseId;
    
    /**
     * 课程名称
     */
    private String courseName;
    
    /**
     * 课程图片
     */
    private String image;
    
    /**
     * 教练姓名
     */
    private String teacherName;
    
    /**
     * 教练ID
     */
    private String teacherId;
    
    /**
     * 课程时长（单位：小时）
     */
    private Integer courseHour;
    
    /**
     * 课程详情描述
     */
    private String courseDetails;
    
    /**
     * 课程价格
     */
    private BigDecimal coursePrice;
}
