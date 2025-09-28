package com.noy.web.member_course.entity;

// Java 标准库
import java.math.BigDecimal;

// MyBatis Plus 相关
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

// Lombok 注解
import lombok.Data;

/**
 * 会员课程关联实体类
 * 
 * <p>对应数据库中的member_course表，用于管理会员报名的课程信息</p>
 * 
 * <p>包含课程的详细信息和会员报名记录</p>
 * 
 * @author noy
 * @version 1.0
 * @since 2024
 */
@Data
@TableName("member_course")
public class MemberCourse {

    /**
     * 会员课程关联ID（主键，自增）
     */
    @TableId(type = IdType.AUTO)
    private Long memberCourseId;
    
    /**
     * 课程ID
     */
    private Long courseId;
    
    /**
     * 会员ID
     */
    private Long memberId;
    
    /**
     * 教练ID
     */
    private Long teacherId;
    
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
