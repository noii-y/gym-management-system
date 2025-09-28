package com.noy.web.course.service;

// MyBatis Plus 相关
import com.baomidou.mybatisplus.extension.service.IService;

// 项目内部实体类
import com.noy.web.course.entity.Course;

/**
 * 课程服务接口
 * 
 * <p>定义课程管理相关的业务逻辑接口，继承MyBatis Plus的IService接口</p>
 * 
 * <p>主要功能包括：</p>
 * <ul>
 *   <li>课程基础信息管理 - 增删改查操作</li>
 *   <li>课程列表查询 - 分页和条件筛选</li>
 *   <li>课程报名管理 - 会员课程关联</li>
 *   <li>教练课程管理 - 教练授课安排</li>
 * </ul>
 * 
 * @author noy
 * @version 1.0
 * @since 2024
 */
public interface CourseService extends IService<Course> {
    // 继承IService接口，提供基础的CRUD操作
    // 具体的业务逻辑方法可在实现类中添加
}
