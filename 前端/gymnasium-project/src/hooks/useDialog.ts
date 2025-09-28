/**
 * 对话框管理组合式函数
 * 提供对话框的显示、隐藏和确认功能
 */
import type { DialogModel } from '@/type/BaseType'
import { reactive } from 'vue'

export default function useDialog() {
  /**
   * 对话框属性配置
   */
  const dialog = reactive<DialogModel>({
    title: '',        // 对话框标题
    visible: false,   // 是否显示
    width: 630,       // 宽度
    height: 280       // 高度
  })

  /**
   * 显示对话框
   */
  const onShow = () => {
    dialog.visible = true
  }

  /**
   * 关闭对话框
   */
  const onClose = () => {
    dialog.visible = false
  }

  /**
   * 确认操作并关闭对话框
   */
  const onConfirm = () => {
    dialog.visible = false
  }

  return {
    dialog,    // 对话框配置对象
    onShow,    // 显示对话框方法
    onClose,   // 关闭对话框方法
    onConfirm  // 确认操作方法
  }
}