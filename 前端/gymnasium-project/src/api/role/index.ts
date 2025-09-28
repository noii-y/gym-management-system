/**
 * 角色管理相关API接口
 * 包含角色的增删改查、权限分配等功能
 */
import http from "@/http";
import type { AddRoleModel, listParam, AssignParam, SaveAssignParam } from "./RoleModel";

/**
 * 新增角色
 * @param param 角色信息对象
 * @returns Promise 新增结果
 */
export const addApi = (param: AddRoleModel) => {
  return http.post("/api/role", param)
}

/**
 * 获取角色列表
 * @param param 查询参数
 * @returns Promise 角色列表数据
 */
export const getListApi = (param: listParam) => {
  return http.get("/api/role/list", param);
}

/**
 * 删除角色
 * @param roleId 角色ID
 * @returns Promise 删除结果
 */
export const deleteApi = (roleId: string) => {
  return http.delete(`/api/role/${roleId}`)
}

/**
 * 编辑角色信息
 * @param param 角色信息对象
 * @returns Promise 编辑结果
 */
export const editApi = (param: AddRoleModel) => {
  return http.put("/api/role", param)
}

/**
 * 查询权限树数据
 * @param param 查询参数
 * @returns Promise 权限树数据
 */
export const getMenuTreeApi = (param: AssignParam) => {
  return http.get('/api/role/getMenuTree', param)
}

/**
 * 保存角色权限
 * @param param 权限分配参数
 * @returns Promise 保存结果
 */
export const saveRoleMenuApi = (param: SaveAssignParam) => {
  return http.post('/api/role/saveRoleMenu', param)
}
