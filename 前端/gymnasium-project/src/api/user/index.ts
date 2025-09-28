/**
 * 用户管理相关API接口
 * 包含用户的增删改查、角色管理等功能
 */
import http from "@/http";
import type { AddUserModel, listParam } from "./UserModel";

/**
 * 获取角色选择列表
 * @returns Promise 角色列表数据
 */
export const getSelectApi = () => {
  return http.get("/api/role/getSelect")
}

/**
 * 新增用户
 * @param param 用户信息对象
 * @returns Promise 新增结果
 */
export const addApi = (param: AddUserModel) => {
  return http.post("/api/user", param)
}

/**
 * 获取用户列表
 * @param param 查询参数
 * @returns Promise 用户列表数据
 */
export const getListApi = (param: listParam) => {
  return http.get("/api/user/list", param)
}

/**
 * 编辑用户信息
 * @param param 用户信息对象
 * @returns Promise 编辑结果
 */
export const editApi = (param: AddUserModel) => {
  return http.put('/api/user', param)
}

/**
 * 删除用户
 * @param userId 用户ID
 * @returns Promise 删除结果
 */
export const deleteApi = (userId: string) => {
  return http.delete(`/api/user/${userId}`)
}

/**
 * 根据用户ID查询角色信息
 * @param userId 用户ID
 * @returns Promise 角色信息
 */
export const getRoleApi = (userId: string) => {
  return http.get("/api/user/role", { userId: userId })
}
