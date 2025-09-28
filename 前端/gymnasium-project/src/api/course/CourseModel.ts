/**
 * 课程管理相关的数据类型定义
 */

/**
 * 课程列表查询参数类型
 */
export type CourselistParam = {
  courseName: string,   // 课程名称
  teacherName: string,  // 教师姓名
  currentPage: number,  // 当前页码
  pageSize: number,     // 每页大小
  total: number         // 总记录数
}

/**
 * 我的课程列表查询参数类型
 */
export type MyCourseListParam = {
  userId: string,       // 用户ID
  userType: string,     // 用户类型
  currentPage: number,  // 当前页码
  pageSize: number,     // 每页大小
  total: number         // 总记录数
}

/**
 * 课程数据类型
 */
export type CourseType = {
  type: string,           // 操作类型
  courseId: string,       // 课程ID
  courseName: string,     // 课程名称
  image: string,          // 课程图片
  teacherName: string,    // 教师姓名
  teacherId: string,      // 教师ID
  courseHour: number,     // 课程时长
  courseDetails: string,  // 课程详情
  coursePrice: number     // 课程价格
}

/**
 * 选课参数类型
 */
export type SelectCoure = {
  courseId: string,  // 课程ID
  memberId: string   // 会员ID
}