/**
 * 课程管理相关API接口
 * 包含课程的增删改查、选课、教练获取等功能
 */
import http from "@/http";
import type { CourseType, CourselistParam, MyCourseListParam, SelectCoure } from "./CourseModel";

/**
 * 新增课程
 * @param param 课程信息对象
 * @returns Promise 新增结果
 */
export const addApi = (param: CourseType) => {
  return http.post("/api/course", param)
}

/**
 * 图片上传
 * @param param 上传文件对象
 * @returns Promise 上传结果
 */
export const uploadImageApi = (param: object) => {
  return http.upload("/api/upload/uploadImage", param)
}

/**
 * 获取课程教练列表
 * @returns Promise 教练列表数据
 */
export const getTeacherApi = () => {
  return http.get("/api/user/getTeacher")
}

/**
 * 获取课程列表
 * @param param 查询参数
 * @returns Promise 课程列表数据
 */
export const listApi = (param: CourselistParam) => {
  return http.get("/api/course/list", param)
}

/**
 * 获取我的课程列表
 * @param param 查询参数
 * @returns Promise 我的课程列表数据
 */
export const getMyCourseListApi = (param: MyCourseListParam) => {
  return http.get("/api/course/getMyCourseList", param)
}

/**
 * 导出课程学生（教练）
 * @param courseId 课程ID
 * @returns Promise 导出内容（CSV字符串或文件流）
 */
export const exportStudentsApi = (courseId: number | string) => {
  return http.get("/api/course/exportStudents", { courseId })
}

/**
 * 编辑课程信息
 * @param param 课程信息对象
 * @returns Promise 编辑结果
 */
export const editApi = (param: CourseType) => {
  return http.put("/api/course", param)
}

/**
 * 删除课程
 * @param courseId 课程ID
 * @returns Promise 删除结果
 */
export const deleteApi = (courseId: string) => {
  return http.delete(`/api/course/${courseId}`)
}

/**
 * 选课功能
 * @param param 选课信息
 * @returns Promise 选课结果
 */
export const joinCourseApi = (param: SelectCoure) => {
  return http.post("/api/course/joinCourse", param)
}