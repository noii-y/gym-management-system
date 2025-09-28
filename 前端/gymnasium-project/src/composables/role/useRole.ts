/**
 * 角色管理组合式函数
 * 提供角色的增删改查和权限分配功能
 */
import type { AddRoleModel } from '@/api/role/RoleModel'
import { EditType } from '@/type/BaseEnum'
import type { FuncList } from '@/type/BaseType'
import { ref } from "vue"
import { deleteApi } from '@/api/role'
import { ElMessage } from 'element-plus'
import useInstance from '@/hooks/useInstance'

export default function useRole(getList: FuncList) {
  const { global } = useInstance()

  /**
   * 分配权限弹框组件的引用
   */
  const assignRoleRef = ref<{
    show: (roleId: string, name: string) => void
  }>()

  /**
   * 分配权限按钮处理
   * @param row 要分配权限的角色数据
   */
  const assignBtn = (row: AddRoleModel) => {
    assignRoleRef.value?.show(row.roleId, row.roleName)
  }

  /**
   * 新增/编辑角色弹框组件的引用
   * 子组件将方法暴露出去，父组件就可以调用子组件的方法
   */
  const addRef = ref<{ show: (type: string, row?: AddRoleModel) => void }>()

  /**
   * 新增角色按钮处理
   */
  const addBtn = () => {
    addRef.value?.show(EditType.ADD)
  }

  /**
   * 编辑角色按钮处理
   * @param row 要编辑的角色数据
   */
  const editBtn = (row: AddRoleModel) => {
    addRef.value?.show(EditType.EDIT, row)
  }

  /**
   * 删除角色按钮处理
   * @param row 要删除的角色数据
   */
  const deleteBtn = async (row: AddRoleModel) => {
    let confirm = await global.$myConfirm("确认删除该数据吗？")
    if (confirm) {
      let res = await deleteApi(row.roleId)
      if (res && res.code == 200) {
        ElMessage.success(res.msg)
        getList()
      } else {
        ElMessage.error(res.msg)
        getList()
      }
    }
  }

  return {
    addBtn,        // 新增角色方法
    editBtn,       // 编辑角色方法
    deleteBtn,     // 删除角色方法
    assignBtn,     // 分配权限方法
    addRef,        // 新增/编辑组件引用
    assignRoleRef  // 分配权限组件引用
  }
}