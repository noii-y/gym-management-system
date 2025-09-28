package com.noy.web.member_course.service.impl;

// Java EE 相关
import javax.annotation.Resource;

// MyBatis Plus 相关
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

// Spring 框架相关
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

// 项目内部类
import com.noy.web.course.entity.Course;
import com.noy.web.course.service.CourseService;
import com.noy.web.member.entity.RechargeParam;
import com.noy.web.member.mapper.MemberMapper;
import com.noy.web.member_course.entity.MemberCourse;
import com.noy.web.member_course.mapper.MemberCourseMapper;
import com.noy.web.member_course.service.MemberCourseService;

/**
 * 会员课程服务实现类
 * 
 * <p>提供会员课程相关的业务逻辑处理，包括：</p>
 * <ul>
 *   <li>会员课程报名</li>
 *   <li>课程费用扣除</li>
 *   <li>报名记录管理</li>
 * </ul>
 * 
 * @author noy
 * @version 1.0
 * @since 2024
 */
@Service
public class MemberCourseServiceImpl extends ServiceImpl<MemberCourseMapper, MemberCourse> implements MemberCourseService {

    // ==================== 依赖注入 ====================
    
    /**
     * 课程业务逻辑服务
     */
    @Autowired
    private CourseService courseService;
    
    /**
     * 会员数据访问层
     */
    @Resource
    private MemberMapper memberMapper;

    // ==================== 业务方法 ====================

    /**
     * 会员课程报名
     * 
     * <p>处理会员的课程报名，包括：</p>
     * <ul>
     *   <li>获取课程信息并复制到报名记录</li>
     *   <li>插入报名记录</li>
     *   <li>扣除会员账户余额</li>
     * </ul>
     * 
     * @param memberCourse 会员课程报名信息
     */
    @Override
    @Transactional
    public void joinCourse(MemberCourse memberCourse) {
        // 根据课程ID查询课程信息
        Course course = courseService.getById(memberCourse.getCourseId());
        
        // 将课程信息复制到会员课程对象
        BeanUtils.copyProperties(course, memberCourse);
        
        // 插入报名记录
        int insert = this.baseMapper.insert(memberCourse);
        
        if (insert > 0) {
            // 扣除会员账户余额
            RechargeParam param = new RechargeParam();
            param.setMemberId(memberCourse.getMemberId());
            param.setMoney(course.getCoursePrice());
            memberMapper.subMoney(param);
        }
    }
}
