package com.noy.web.member_course.service.impl;

// Java EE 相关
import javax.annotation.Resource;

// MyBatis Plus 相关
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

// Spring 框架相关
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

// 项目内部类
import com.noy.web.course.entity.Course;
import com.noy.web.course.service.CourseService;
import com.noy.web.member.entity.Member;
import com.noy.web.member.entity.RechargeParam;
import com.noy.web.member.mapper.MemberMapper;
import com.noy.web.member_course.entity.MemberCourse;
import com.noy.web.member_course.mapper.MemberCourseMapper;
import com.noy.web.member_course.service.MemberCourseService;

import java.util.List;
import java.util.stream.Collectors;

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

    /**
     * 根据课程ID获取所有报名会员
     * 
     * <p>实现逻辑：</p>
     * <ul>
     *   <li>1. 在会员课程关联表中查询该课程的所有记录</li>
     *   <li>2. 提取记录中的会员ID列表</li>
     *   <li>3. 根据会员ID列表批量查询会员详细信息</li>
     * </ul>
     * 
     * @param courseId 课程ID
     * @return 会员信息列表
     */
    @Override
    public List<Member> getMembersByCourseId(Long courseId) {
        // 1. 查询课程关联记录
        QueryWrapper<MemberCourse> query = new QueryWrapper<>();
        query.lambda().eq(MemberCourse::getCourseId, courseId);
        List<MemberCourse> courseRecords = this.baseMapper.selectList(query);
        
        // 2. 提取会员ID列表
        List<Long> memberIds = courseRecords.stream()
                .map(MemberCourse::getMemberId)
                .collect(Collectors.toList());
        
        // 3. 批量查询会员信息（如果ID列表为空则直接返回空列表）
        if (memberIds.isEmpty()) {
            return new java.util.ArrayList<>();
        }
        return memberMapper.selectBatchIds(memberIds);
    }
}
