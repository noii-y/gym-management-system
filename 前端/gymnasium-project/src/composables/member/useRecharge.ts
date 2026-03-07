/**
 * 会员充值弹窗组合式函数
 * 提供打开充值弹窗的统一入口，避免在视图中直接操作 ref
 */
import type { MemberType } from "@/api/member/MemberModel";
import { ref } from "vue";

/**
 * 返回充值弹窗引用与触发方法
 */
export default function useRecharge() {
    /** 充值弹窗组件引用（暴露 show(row)） */
    const rechargeRef = ref<{ show: (row: MemberType) => void }>()
    /** 点击“充值”时打开弹窗并传入当前会员行 */
    const rechargeBtn = (row: MemberType) => {
        rechargeRef.value?.show(row)
    }
    return {
        rechargeRef,
        rechargeBtn
    }
}
