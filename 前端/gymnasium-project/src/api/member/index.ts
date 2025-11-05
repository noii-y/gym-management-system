/**
 * 会员管理相关API接口
 * 包含会员的增删改查、办卡、充值等功能
 */
import http from "@/http"
import type { MemberType, MemberParam, ApplyCard, Recharge } from "./MemberModel"

/**
 * 新增会员
 * @param param 会员信息对象
 * @returns Promise 新增结果
 */
export const addApi = (param: MemberType) => {
  return http.post("/api/member", param)
}

/**
 * 获取会员列表
 * @param param 查询参数
 * @returns Promise 会员列表数据
 */
export const getListApi = (param: MemberParam) => {
  return http.get("/api/member/list", param)
}

/**
 * 编辑会员信息
 * @param param 会员信息对象
 * @returns Promise 编辑结果
 */
export const editApi = (param: MemberType) => {
  return http.put("/api/member", param)
}

/**
 * 删除会员
 * @param memberId 会员ID
 * @returns Promise 删除结果
 */
export const deleteApi = (memberId: string) => {
  return http.delete(`/api/member/${memberId}`)
}

/**
 * 根据会员ID查询角色ID
 * @param memberId 会员ID
 * @returns Promise 角色信息
 */
export const getRoleByMemberIdApi = (memberId: string) => {
  return http.get("/api/member/getRoleByMemberId", { 
    memberId: memberId 
  })
}

/**
 * 会员办卡申请
 * @param param 办卡申请信息
 * @returns Promise 办卡结果
 */
export const applySaveApi = (param: ApplyCard) => {
  return http.post("/api/member/joinApply", param)
}

/**
 * 会员充值
 * @param param 充值信息
 * @returns Promise 充值结果
 */
export const rechargeApi = (param: Recharge) => {
  return http.post("/api/member/recharge", param)
}

/**
 * 查询我的充值记录
 * @param param 查询参数
 * @returns Promise 充值记录列表
 */
export const getMyRechargeApi = (param: any) => {
  return http.get("/api/member/getMyRecharge", param)
}