/**
 * 课程管理组合式函数
 * 提供课程的增删改查和选课功能
 */
import type { CourseType } from "@/api/course/CourseModel"
import { EditType } from "@/type/BaseEnum"
import { ref } from "vue"
import { deleteApi, joinCourseApi } from "@/api/course"
import { userStore } from "@/store/user"
import { ElMessage } from "element-plus"
import type { FuncList } from "@/type/BaseType"
import useInstance from "@/hooks/useInstance"

export default function useCourse(getList: FuncList) {
  const { global } = useInstance()
  
  /**
   * 新增/编辑课程组件的引用
   */
  const addRef = ref<{
    show: (type: string, row?: CourseType) => void
  }>()

  /**
   * 新增课程按钮处理
   */
  const addBtn = () => {
    addRef.value?.show(EditType.ADD)
  }

  /**
   * 编辑课程按钮处理
   * @param row 要编辑的课程数据
   */
  const editBtn = (row: CourseType) => {
    addRef.value?.show(EditType.EDIT, row)
  }

  /**
   * 删除课程按钮处理
   * @param row 要删除的课程数据
   */
  const deleteBtn = async (row: CourseType) => {
    // 确认删除操作
    let confirm = await global.$myconfirm('确定删除该数据吗?')
    if (confirm) {
      let res = await deleteApi(row.courseId)
      if (res && res.code == 200) {
        ElMessage.success(res.msg)
        getList()
      }
    }
  }

  /**
   * 选课按钮处理
   * @param row 要选择的课程数据
   */
  const joinBtn = async (row: CourseType) => {
    // 确认选课操作
    let confirm = await global.$myconfirm('确定选课该课程吗?')
    if (confirm) {
      let res = await joinCourseApi({
        courseId: row.courseId,
        memberId: userStore().getUserId
      })
      if (res && res.code == 200) {
        ElMessage.success(res.msg)
      }
    }
  }

  return {
    addBtn,    // 新增课程方法
    editBtn,   // 编辑课程方法
    deleteBtn, // 删除课程方法
    addRef,    // 新增/编辑组件引用
    joinBtn    // 选课方法
  }
}