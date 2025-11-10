/**
 * WangEditor富文本编辑器类型声明
 * 为@wangeditor/editor-for-vue包提供TypeScript类型支持
 */
declare module '@wangeditor/editor-for-vue' {
  import { DefineComponent } from 'vue';
  
  /**
   * 富文本编辑器组件
   * 用于内容编辑的主要组件
   */
  export const Editor: DefineComponent<any, any, any>;
  
  /**
   * 编辑器工具栏组件
   * 提供编辑功能的工具栏
   */
  export const Toolbar: DefineComponent<any, any, any>;
}