package com.noy.web.member_course.service;

// MyBatis Plus 相关
import com.baomidou.mybatisplus.extension.service.IService;

// 项目内部实体类
import com.noy.web.member_course.entity.MemberCourse;

/**
 * 会员课程服务接口
 * 
 * <p>定义会员课程关联管理相关的业务逻辑接口，继承MyBatis Plus的IService接口</p>
 * 
 * <p>主要功能包括：</p>
 * <ul>
 *   <li>会员课程关联管理 - 增删改查操作</li>
 *   <li>课程报名功能 - 会员报名课程处理</li>
 *   <li>课程退课功能 - 会员退课处理</li>
 *   <li>课程统计分析 - 报名人数和课程热度统计</li>
 * </ul>
 * 
 * @author noy
 * @version 1.0
 * @since 2024
 */
public interface MemberCourseService extends IService<MemberCourse> {

    /**
     * 会员报名课程
     * 
     * <p>处理会员报名课程的业务逻辑，包括：</p>
     * <ul>
     *   <li>验证会员资格和课程可用性</li>
     *   <li>检查课程容量和时间冲突</li>
     *   <li>创建会员课程关联记录</li>
     *   <li>更新课程报名人数</li>
     * </ul>
     * 
     * @param memberCourse 会员课程关联信息
     */
    void joinCourse(MemberCourse memberCourse);
}
