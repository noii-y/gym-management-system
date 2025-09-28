/**
 * 登录相关API接口
 * 包含用户登录、验证码获取、菜单获取等功能
 */
import http from "@/http/index";
import { type LoginParam, type InfoParam } from "./LoginModel";

/**
 * 获取图片验证码
 * @returns Promise 验证码图片数据
 */
export const getImageApi = () => {
  return http.post("/api/login/image")
}

/**
 * 用户登录接口
 * @param Param 登录参数对象
 * @returns Promise 登录结果
 */
export const loginApi = (Param: LoginParam) => {
  return http.post("/api/login/login", Param)
}

/**
 * 获取用户菜单列表
 * 根据用户权限返回对应的菜单数据
 * @param param 用户信息参数
 * @returns Promise 菜单列表数据
 */
export const getMenuListApi = (param: InfoParam) => {
  return http.get("/api/login/getMenuList", param)
}

/**
 * 获取用户详细信息
 * 包含用户基本信息和权限列表
 * @param param 用户信息参数
 * @returns Promise 用户详细信息
 */
export const getInfoApi = (param: InfoParam) => {
  return http.get("/api/login/getInfo", param)
}