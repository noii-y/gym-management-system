/**
 * 角色选择组合式函数
 * 提供角色列表获取和用户角色查询功能
 */

// 导入类型定义
import type { SelectRole } from "@/api/user/UserModel";
// 导入Vue相关
import { reactive, ref } from "vue";
// 导入API函数
import { getSelectApi, getRoleApi } from "@/api/user";
import { getRoleByMemberIdApi } from '@/api/member/index';

/**
 * 角色选择管理组合式函数
 * @returns 返回角色相关的状态和方法
 */
export default function useSelectRole() {
    /**
     * 用户角色ID
     */
    const roleId = ref('')
    
    /**
     * 会员角色ID
     */
    const roleMemberId = ref('')
    
    /**
     * 角色列表数据
     */
    const roleData = reactive<SelectRole>({
        list: []
    })
    
    /**
     * 获取角色列表数据
     */
    const listRole = async () => {
        let res = await getSelectApi()
        if (res && res.code == 200) {
            roleData.list = res.data
        }
    }
    
    /**
     * 根据用户ID查询用户角色
     * @param userId 用户ID
     */
    const getRole = async (userId: string) => {
        roleId.value = ''
        let res = await getRoleApi(userId)
        if (res && res.code == 200 && res.data) {
            roleId.value = res.data.roleId
        }
    }
    
    /**
     * 根据会员ID查询会员角色
     * @param memberId 会员ID
     */
    const getMemberRole = async (memberId: string) => {
        roleMemberId.value = ''
        let res = await getRoleByMemberIdApi(memberId)
        if (res && res.code == 200 && res.data) {
            roleMemberId.value = res.data.roleId
        }
    }
    
    /**
     * 返回组合式函数的公共接口
     */
    return {
        roleData,       // 角色列表数据
        listRole,       // 获取角色列表方法
        getRole,        // 获取用户角色方法
        roleId,         // 用户角色ID
        getMemberRole,  // 获取会员角色方法
        roleMemberId    // 会员角色ID
    }
}