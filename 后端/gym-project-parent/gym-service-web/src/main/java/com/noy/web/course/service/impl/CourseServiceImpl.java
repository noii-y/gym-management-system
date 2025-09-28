package com.noy.web.course.service.impl;

// MyBatis Plus 相关
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

// Spring 框架相关
import org.springframework.stereotype.Service;

// 项目内部类
import com.noy.web.course.entity.Course;
import com.noy.web.course.mapper.CourseMapper;
import com.noy.web.course.service.CourseService;

/**
 * 课程服务实现类
 * 
 * <p>提供课程相关的业务逻辑处理，包括：</p>
 * <ul>
 *   <li>课程信息的基础CRUD操作</li>
 *   <li>继承MyBatis Plus的通用服务方法</li>
 *   <li>支持分页查询和条件筛选</li>
 * </ul>
 * 
 * @author noy
 * @version 1.0
 * @since 2024
 */
@Service
public class CourseServiceImpl extends ServiceImpl<CourseMapper, Course> implements CourseService {
    
    // 继承ServiceImpl，提供基础的CRUD操作
    // 如需要自定义业务逻辑，可在此处添加方法实现
}
