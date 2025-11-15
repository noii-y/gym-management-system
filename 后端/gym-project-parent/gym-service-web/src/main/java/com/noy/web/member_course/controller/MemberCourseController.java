package com.noy.web.member_course.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.noy.utils.ResultUtils;
import com.noy.utils.ResultVo;
import com.noy.web.member_course.entity.MemberCourse;
import com.noy.web.member_course.service.MemberCourseService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/memberCourse")
public class MemberCourseController {

    @Autowired
    private MemberCourseService memberCourseService;

    @GetMapping("/list")
    public ResultVo list(
            @RequestParam(value = "courseName", required = false) String courseName,
            @RequestParam(value = "teacherName", required = false) String teacherName,
            @RequestParam(value = "memberId", required = false) Long memberId,
            @RequestParam(value = "currentPage", defaultValue = "1") Long currentPage,
            @RequestParam(value = "pageSize", defaultValue = "10") Long pageSize
    ) {
        IPage<MemberCourse> page = new Page<>(currentPage, pageSize);
        QueryWrapper<MemberCourse> query = new QueryWrapper<>();
        if (StringUtils.isNotEmpty(courseName)) {
            query.lambda().like(MemberCourse::getCourseName, courseName);
        }
        if (StringUtils.isNotEmpty(teacherName)) {
            query.lambda().like(MemberCourse::getTeacherName, teacherName);
        }
        if (memberId != null) {
            query.lambda().eq(MemberCourse::getMemberId, memberId);
        }
        query.lambda().orderByDesc(MemberCourse::getMemberCourseId);
        IPage<MemberCourse> list = memberCourseService.page(page, query);
        return ResultUtils.success("查询成功", list);
    }
}