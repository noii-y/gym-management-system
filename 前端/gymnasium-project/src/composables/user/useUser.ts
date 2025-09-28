/**
 * 用户管理组合式函数
 * 提供用户的增删改查和密码重置功能
 */
import type { AddUserModel } from "@/api/user/UserModel"
import { EditType } from "@/type/BaseEnum"
import { ref } from "vue"
import { deleteApi } from "@/api/user"
import { ElMessage } from "element-plus"
import type { FuncList } from "@/type/BaseType"
import useInstance from "@/hooks/useInstance"
import { resetPasswordApi } from "@/api/home"

export default function useUser(getList: FuncList) {
  const { global } = useInstance()
  
  /**
   * 新增/编辑用户弹框组件的引用
   */
  const addRef = ref<{ show: (type: string, row?: AddUserModel) => void }>()

  /**
   * 新增用户按钮处理
   */
  const addBtn = () => {
    addRef.value?.show(EditType.ADD)
  }

  /**
   * 编辑用户按钮处理
   * @param row 要编辑的用户数据
   */
  const editBtn = (row: AddUserModel) => {
    addRef.value?.show(EditType.EDIT, row)
  }

  /**
   * 删除用户按钮处理
   * @param row 要删除的用户数据
   */
  const deleteBtn = async (row: AddUserModel) => {
    const confirm = await global.$myconfirm('确定删除该数据吗?')
    if (confirm) {
      let res = await deleteApi(row.userId)
      if (res && res.code == 200) {
        ElMessage.success(res.msg)
        getList()
      }
    }
  }

  /**
   * 重置用户密码按钮处理
   * @param row 要重置密码的用户数据
   */
  const resetPasBtn = async (row: AddUserModel) => {
    const confirm = await global.$myconfirm('确定重置密码吗，重置后密码为【666666】?')
    if (confirm) {
      let Param = {
        userId: row.userId,
        userType: "2"
      }
      let res = await resetPasswordApi(Param)
      if (res && res.code == 200) {
        ElMessage.success(res.msg)
      }
    }
  }

  return {
    addBtn,      // 新增用户方法
    editBtn,     // 编辑用户方法
    deleteBtn,   // 删除用户方法
    resetPasBtn, // 重置密码方法
    addRef       // 新增/编辑组件引用
  }
}