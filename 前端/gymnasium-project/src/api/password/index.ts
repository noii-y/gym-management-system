/**
 * 忘记密码相关API
 */
import http from '@/http/index'

export interface ResetByContactParam {
  memberId: string
  phone: string
  code: string
  newPassword: string
  confirmPassword: string
  userType: string // 1-会员，2-员工
}

export const resetByContactApi = (param: ResetByContactParam) => {
  return http.post('/api/password/resetByContact', param)
}