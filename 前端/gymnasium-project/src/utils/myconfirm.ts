/**
 * 确认对话框工具类
 * 基于Element Plus的MessageBox封装的确认提示框
 */
import { ElMessageBox } from 'element-plus'

/**
 * 显示确认对话框
 * @param text 提示文本内容
 * @returns Promise<boolean> 用户确认返回true，取消返回false
 * 
 * @example
 * ```typescript
 * // 使用示例
 * myconfirm('确定要删除这条记录吗？')
 *   .then(() => {
 *     console.log('用户点击了确定')
 *     // 执行删除操作
 *   })
 *   .catch(() => {
 *     console.log('用户点击了取消')
 *   })
 * ```
 */
export default function myconfirm(text: string): Promise<boolean> {
  return new Promise<boolean>((resolve, reject) => {
    ElMessageBox.confirm(text, '系统提示', {
      confirmButtonText: '确定',
      cancelButtonText: '取消',
      type: 'warning',
    })
    .then(() => {
      // 用户点击确定按钮
      resolve(true)
    })
    .catch(() => {
      // 用户点击取消按钮
      reject(false)
    })
  })
}