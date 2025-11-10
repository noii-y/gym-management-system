package com.noy.web.course.entity;

import lombok.Data;
import java.math.BigDecimal;

/**
 * 课程简要DTO
 * 统一“我的课程”返回的字段结构，兼容会员(MemberCourse)与教师(Course)两种来源
 */
@Data
public class CourseSummaryVo {
    private Long courseId;
    private String courseName;
    private String image;
    private String teacherName;
    private Integer courseHour;
    private BigDecimal coursePrice;
}