/**
 * 会员卡操作管理组合式函数
 * 提供会员卡的新增、编辑、删除等操作功能
 */

// 导入类型定义
import type { CardType } from "@/api/member_card/MemberModel"
import type { FuncList } from "@/type/BaseType";
// 导入枚举类型
import { EditType } from "@/type/BaseEnum"
// 导入Vue相关
import { ref } from "vue"
// 导入组合式函数
import useInstance from "@/hooks/useInstance";
// 导入API函数
import { deleteApi } from "@/api/member_card/index";
// 导入Element Plus组件
import { ElMessage } from "element-plus";

/**
 * 会员卡操作管理组合式函数
 * @param getList 刷新列表的回调函数
 * @returns 返回操作相关的方法和引用
 */
export default function useMember(getList: FuncList) {
    /**
     * 全局工具实例
     */
    const { global } = useInstance();
    
    /**
     * 新增/编辑弹框的引用
     */
    const addRef = ref<{ show: (type: string, row?: CardType) => void }>()
    
    /**
     * 新增按钮点击事件
     */
    const addBtn = () => {
        addRef.value?.show(EditType.ADD)
    }
    
    /**
     * 编辑按钮点击事件
     * @param row 要编辑的会员卡数据
     */
    const editBtn = (row: CardType) => {
        addRef.value?.show(EditType.EDIT, row)
    }
    
    /**
     * 删除按钮点击事件
     * @param row 要删除的会员卡数据
     */
    const deleteBtn = async (row: CardType) => {
        // 确认删除操作
        let confirm = await global.$myconfirm('确定删除该数据吗?')
        if (confirm) {
            // 调用删除API
            let res = await deleteApi(row.cardId)
            if (res && res.code == 200) {
                ElMessage.success(res.msg)
                // 刷新表格数据
                getList()
            }
        }
    }
    
    /**
     * 返回组合式函数的公共接口
     */
    return {
        addBtn,     // 新增方法
        editBtn,    // 编辑方法
        deleteBtn,  // 删除方法
        addRef      // 弹框引用
    }
}
