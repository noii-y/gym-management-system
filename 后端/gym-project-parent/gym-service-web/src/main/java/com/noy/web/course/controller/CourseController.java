package com.noy.web.course.controller;

// MyBatis Plus
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

// Apache Commons
import org.apache.commons.lang.StringUtils;

// Spring框架
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.access.prepost.PreAuthorize;
import java.util.List;

// 项目内部工具类
import com.noy.utils.ResultUtils;
import com.noy.utils.ResultVo;

// 项目内部实体类和服务类
import com.noy.web.course.entity.Course;
import com.noy.web.course.entity.CourseList;
import com.noy.web.course.entity.PageParam;
import com.noy.web.course.service.CourseService;
import com.noy.web.member.entity.Member;
import com.noy.web.member.service.MemberService;
import com.noy.web.member_course.entity.MemberCourse;
import com.noy.web.member_course.service.MemberCourseService;

/**
 * 课程管理控制器
 * 
 * <p>负责处理课程相关的所有业务逻辑，包括：</p>
 * <ul>
 *   <li>课程信息的增删改查操作</li>
 *   <li>课程报名功能</li>
 *   <li>我的课程列表查询（会员和教师）</li>
 *   <li>课程余额验证</li>
 * </ul>
 * 
 * @author noy
 * @version 1.0
 * @since 2024
 */
@RestController
@RequestMapping("/api/course")
public class CourseController {

    // ==================== 服务层依赖注入 ====================
    
    @Autowired
    private CourseService courseService;
    
    @Autowired
    private MemberCourseService memberCourseService;

    @Autowired
    private MemberService memberService;

    // ==================== 课程基础管理接口 ====================

    /**
     * 新增课程
     * 
     * <p>创建新的课程信息</p>
     * 
     * @param course 课程信息对象
     * @return 操作结果，包含成功或失败信息
     */
    @PostMapping
    public ResultVo add(@RequestBody Course course) {
        if (courseService.save(course)) {
            return ResultUtils.success("新增成功!");
        }
        return ResultUtils.error("新增失败!");
    }

    /**
     * 编辑课程信息
     * 
     * <p>更新指定课程的信息</p>
     * 
     * @param course 课程信息对象
     * @return 操作结果，包含成功或失败信息
     */
    @PutMapping
    public ResultVo edit(@RequestBody Course course) {
        if (courseService.updateById(course)) {
            return ResultUtils.success("编辑成功!");
        }
        return ResultUtils.error("编辑失败!");
    }

    /**
     * 删除课程
     * 
     * <p>根据课程ID删除指定课程</p>
     * 
     * @param courseId 课程ID
     * @return 操作结果，包含成功或失败信息
     */
    @DeleteMapping("/{courseId}")
    public ResultVo delete(@PathVariable("courseId") Long courseId) {
        if (courseService.removeById(courseId)) {
            return ResultUtils.success("删除成功!");
        }
        return ResultUtils.error("删除失败!");
    }

    /**
     * 分页查询课程列表
     * 
     * <p>根据课程名称和教师名称进行模糊查询，支持分页</p>
     * 
     * @param courseList 查询参数，包含分页信息和筛选条件
     * @return 课程列表查询结果
     */
    @GetMapping("/list")
    public ResultVo list(CourseList courseList) {
        // 构造分页对象
        IPage<Course> page = new Page<>(
                courseList.getCurrentPage(), courseList.getPageSize());
        
        // 构造查询条件
        QueryWrapper<Course> query = new QueryWrapper<>();
        if (StringUtils.isNotEmpty(courseList.getCourseName())) {
            query.lambda().like(Course::getCourseName, courseList.getCourseName());
        }
        if (StringUtils.isNotEmpty(courseList.getTeacherName())) {
            query.lambda().like(Course::getTeacherName, courseList.getTeacherName());
        }
        
        IPage<Course> list = courseService.page(page, query);
        return ResultUtils.success("查询成功", list);
    }

    // ==================== 课程报名管理接口 ====================

    /**
     * 课程报名
     * 
     * <p>处理会员课程报名，包括重复报名检查和余额验证</p>
     * <ul>
     *   <li>检查是否已经报名该课程</li>
     *   <li>验证会员余额是否充足</li>
     *   <li>扣除相应费用并完成报名</li>
     * </ul>
     * 
     * @param memberCourse 会员课程关联信息
     * @return 报名结果
     */
    @PostMapping("/joinCourse")
    public ResultVo joinCourse(@RequestBody MemberCourse memberCourse) {
        // 查询是否已经报名该课程
        QueryWrapper<MemberCourse> query = new QueryWrapper<>();
        query.lambda().eq(MemberCourse::getCourseId, memberCourse.getCourseId())
                .eq(MemberCourse::getMemberId, memberCourse.getMemberId());
        MemberCourse one = memberCourseService.getOne(query);
        if (one != null) {
            return ResultUtils.error("您已经报名过该课程");
        }
        
        // 判断余额是否充足
        Course course = courseService.getById(memberCourse.getCourseId());
        Member member = memberService.getById(memberCourse.getMemberId());
        int flag = member.getMoney().compareTo(course.getCoursePrice());
        if (flag == -1) {
            return ResultUtils.error("您的余额不足，请先充值");
        }
        
        memberCourseService.joinCourse(memberCourse);
        return ResultUtils.success("报名成功");
    }

    /**
     * 查询我的课程列表
     * 
     * <p>根据用户类型查询相应的课程列表</p>
     * <ul>
     *   <li>用户类型为1（会员）：查询已报名的课程列表</li>
     *   <li>用户类型为其他（教师）：查询自己教授的课程列表</li>
     * </ul>
     * 
     * @param param 查询参数，包含用户类型、用户ID和分页信息
     * @return 课程列表查询结果
     */
    @GetMapping("/getMyCourseList")
    public ResultVo getMyCourseList(PageParam param) {
        if (param.getUserType().equals("1")) {
            // 会员查询已报名的课程
            return getMemberCourseList(param);
        } else {
            // 教师查询自己教授的课程
            return getTeacherCourseList(param);
        }
    }

    // ==================== 私有辅助方法 ====================

    /**
     * 获取会员已报名的课程列表
     * 
     * @param param 查询参数
     * @return 会员课程列表
     */
    private ResultVo getMemberCourseList(PageParam param) {
        IPage<MemberCourse> page = new Page<>(param.getCurrentPage(), param.getPageSize());
        QueryWrapper<MemberCourse> query = new QueryWrapper<>();
        query.lambda().eq(MemberCourse::getMemberId, param.getUserId());
        IPage<MemberCourse> list = memberCourseService.page(page, query);
        return ResultUtils.success("查询成功", list);
    }

    /**
     * 获取教师教授的课程列表
     * 
     * @param param 查询参数
     * @return 教师课程列表
     */
    private ResultVo getTeacherCourseList(PageParam param) {
        IPage<Course> page = new Page<>(param.getCurrentPage(), param.getPageSize());
        QueryWrapper<Course> query = new QueryWrapper<>();
        query.lambda().eq(Course::getTeacherId, param.getUserId());
        IPage<Course> list = courseService.page(page, query);
        return ResultUtils.success("查询成功", list);
    }
    /**
     * 导出课程学生（教练权限）
     * 返回CSV字符串，前端保存为文件
     */
    @GetMapping("/exportStudents")
    @PreAuthorize("hasAnyAuthority('sys:myTeaching:export')")
    public ResultVo exportStudents(@RequestParam Long courseId) {
        Course course = courseService.getById(courseId);
        QueryWrapper<MemberCourse> query = new QueryWrapper<>();
        query.lambda().eq(MemberCourse::getCourseId, courseId);
        if (course != null) {
            String tId = course.getTeacherId();
            if (StringUtils.isNotEmpty(tId)) {
                try {
                    Long teacherId = Long.valueOf(tId);
                    query.lambda().or(i -> i.eq(MemberCourse::getCourseName, course.getCourseName())
                            .eq(MemberCourse::getTeacherId, teacherId));
                } catch (Exception e) {
                    query.lambda().or(i -> i.eq(MemberCourse::getCourseName, course.getCourseName()));
                }
            } else {
                query.lambda().or(i -> i.eq(MemberCourse::getCourseName, course.getCourseName()));
            }
        }
        List<MemberCourse> list = memberCourseService.list(query);

        // 组装CSV内容
        StringBuilder sb = new StringBuilder();
        sb.append("会员ID,姓名,手机,课程名称,教练,报名记录ID\n");
        for (MemberCourse mc : list) {
            Member mem = memberService.getById(mc.getMemberId());
            String name = mem != null ? mem.getName() : "";
            String phone = mem != null ? mem.getPhone() : "";
            sb.append(mc.getMemberId()).append(',')
              .append(escapeCsv(name)).append(',')
              .append(escapeCsv(phone)).append(',')
              .append(escapeCsv(mc.getCourseName())).append(',')
              .append(escapeCsv(mc.getTeacherName())).append(',')
              .append(mc.getMemberCourseId()).append('\n');
        }

        return ResultUtils.success("导出成功", sb.toString());
    }

    private String escapeCsv(String s) {
        if (s == null) return "";
        boolean needsQuote = s.contains(",") || s.contains("\n") || s.contains("\r") || s.contains("\"");
        String escaped = s.replace("\"", "\"\"");
        return needsQuote ? "\"" + escaped + "\"" : escaped;
    }
    /**
     * 清空课程授课教练
     */
    @PutMapping("/unassignTeacher/{courseId}")
    public ResultVo unassignTeacher(@PathVariable("courseId") Long courseId) {
        UpdateWrapper<Course> uw = new UpdateWrapper<>();
        uw.lambda().eq(Course::getCourseId, courseId)
                .set(Course::getTeacherId, null)
                .set(Course::getTeacherName, null);
        boolean ok = courseService.update(uw);
        if (ok) {
            return ResultUtils.success("移除成功!");
        }
        return ResultUtils.error("移除失败!");
    }
}
