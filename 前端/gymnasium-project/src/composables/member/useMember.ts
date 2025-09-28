/**
 * 会员管理组合式函数
 * 提供会员的增删改查和密码重置功能
 */
import type { MemberType } from "@/api/member/MemberModel"
import { EditType } from "@/type/BaseEnum"
import { ref } from "vue"
import { deleteApi } from "@/api/member"
import { ElMessage } from "element-plus"
import type { FuncList } from "@/type/BaseType"
import useInstance from "@/hooks/useInstance"
import { resetPasswordApi } from "@/api/home"

export default function useMember(getList: FuncList) {
  const { global } = useInstance()
  
  /**
   * 新增/编辑会员组件的引用
   */
  const addRef = ref<{
    show: (type: string, row?: MemberType) => void
  }>()

  /**
   * 新增会员按钮处理
   */
  const addBtn = () => {
    addRef.value?.show(EditType.ADD)
  }

  /**
   * 编辑会员按钮处理
   * @param row 要编辑的会员数据
   */
  const editBtn = (row: MemberType) => {
    addRef.value?.show(EditType.EDIT, row)
  }

  /**
   * 删除会员按钮处理
   * @param row 要删除的会员数据
   */
  const deleteBtn = async (row: MemberType) => {
    const confirm = await global.$myconfirm('确定删除该数据吗?')
    if (confirm) {
      let res = await deleteApi(row.memberId)
      if (res && res.code == 200) {
        ElMessage.success(res.msg)
        getList()
      }
    }
  }

  /**
   * 重置会员密码按钮处理
   * @param row 要重置密码的会员数据
   */
  const resetPasBtn = async (row: MemberType) => {
    const confirm = await global.$myconfirm('确定重置密码吗，重置后密码为【666666】?')
    if (confirm) {
      let Param = {
        userId: row.memberId,
        userType: "1"
      }
      let res = await resetPasswordApi(Param)
      if (res && res.code == 200) {
        ElMessage.success(res.msg)
      }
    }
  }

  return {
    addBtn,      // 新增会员方法
    editBtn,     // 编辑会员方法
    deleteBtn,   // 删除会员方法
    addRef,      // 新增/编辑组件引用
    resetPasBtn  // 重置密码方法
  }
}