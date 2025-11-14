import http from "@/http";

export type CourseOrderListParam = {
  courseName?: string,
  teacherName?: string,
  currentPage: number,
  pageSize: number,
  total?: number
}

export const listApi = (param: CourseOrderListParam) => {
  return http.get("/api/memberCourse/list", param)
}