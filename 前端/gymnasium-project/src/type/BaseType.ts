// 基础类型定义

// 对话框属性类型
export type DialogModel = {
  title: string    // 对话框标题
  visible: boolean // 是否显示
  height: number   // 高度
  width: number    // 宽度
}

// 通用函数类型
export type FuncList = () => unknown