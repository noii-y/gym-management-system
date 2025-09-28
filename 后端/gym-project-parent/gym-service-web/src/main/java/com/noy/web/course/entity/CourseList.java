package com.noy.web.course.entity;

import lombok.Data;

/**
 * 课程列表查询参数类
 * 
 * <p>用于封装课程列表查询的参数信息，包括：</p>
 * <ul>
 *   <li>分页参数 - 当前页码和页面大小</li>
 *   <li>查询条件 - 课程名称和教师名称的模糊查询</li>
 *   <li>筛选功能 - 支持多条件组合查询</li>
 * </ul>
 * 
 * @author noy
 * @version 1.0
 * @since 2024
 */
@Data
public class CourseList {
    /**
     * 当前页码
     */
    private Integer currentPage;
    
    /**
     * 页面大小
     */
    private Integer pageSize;
    
    /**
     * 课程名称（支持模糊查询）
     */
    private String courseName;
    
    /**
     * 教师名称（支持模糊查询）
     */
    private String teacherName;
}
