import { ref, type Ref } from 'vue';
import { ElMessage, type FormInstance } from 'element-plus';

/**
 * 通用提交对话框组合式函数（useSubmitDialog）
 * 作用：
 * - 统一“确定”按钮的 loading/禁用 状态管理，防止重复提交
 * - 统一表单校验、调用新增/编辑 API、成功消息提示与关闭弹窗的流程
 * - 通过最少的接入代码，让各业务弹窗保持轻量、一致的行为
 *
 * 使用方式：
 * const { confirmLoading, confirmDisabled, commit } = useSubmitDialog(formRef, model, {
 *   addApi,
 *   editApi,
 *   close: onClose,
 *   onSuccess: () => emits('refresh'),
 *   typeKey: 'type'
 * });
 *
 * 参数说明：
 * - formRef: Element Plus 表单引用（用于触发表单校验）
 * - model: 表单数据对象（其中包含标识新增/编辑的字段，如 'type'）
 * - opts: 配置项
 *   - addApi: 新增接口方法，入参为表单模型，返回后端响应
 *   - editApi: 编辑接口方法，入参为表单模型，返回后端响应
 *   - close: 关闭弹窗的方法（通常为 useDialog 提供的 onClose）
 *   - onSuccess: 提交成功后的回调，可用于刷新列表等（可选）
 *   - typeKey: 模型中用于标识操作类型的字段名（默认 'type'）
 *
 * 返回值：
 * - confirmLoading: 是否显示“确定”按钮 loading 的响应式状态
 * - confirmDisabled: 是否禁用“确定”按钮的响应式状态
 * - commit: 统一的提交方法（包含校验、调用 API、提示、关闭、状态复位）
 */

/**
 * 后端响应类型（简化版）
 * - code: 业务状态码（期望 200 表示成功）
 * - msg: 提示消息（成功时展示给用户）
 */
type ApiResponse = {
  code?: number;
  msg?: string;
  [key: string]: any;
};

/**
 * useSubmitDialog 的配置项类型
 */
type UseSubmitOptions<TModel> = {
  addApi: (payload: TModel) => Promise<ApiResponse>;
  editApi: (payload: TModel) => Promise<ApiResponse>;
  close: () => void;
  onSuccess?: (res: ApiResponse) => void;
  /** 模型中标识“新增/编辑”的字段名，默认 'type' */
  typeKey?: keyof TModel;
};

/**
 * useSubmitDialog 主函数
 * @param formRef 表单引用（用于触发表单校验）
 * @param model 表单模型（包含操作类型字段，比如 'type'）
 * @param opts  提交流程配置（新增/编辑 API、关闭方法、成功回调等）
 */
export default function useSubmitDialog<TModel extends Record<string, any>>(
  formRef: Ref<FormInstance | undefined>,
  model: TModel,
  opts: UseSubmitOptions<TModel>
) {
  /** “确定”按钮 loading 状态（正在提交） */
  const confirmLoading = ref(false);
  /** “确定”按钮禁用状态（防止重复点击） */
  const confirmDisabled = ref(false);

  /** 操作类型字段名（默认 'type'） */
  const typeKey = (opts.typeKey ?? 'type') as keyof TModel;

  /**
   * 统一的提交方法：触发表单校验，区分新增/编辑调用对应 API，成功后提示并关闭弹窗
   */
  const commit = () => {
    // 若已在提交中，则直接返回，避免重复提交
    if (confirmLoading.value) return;

    // 进入提交状态：显示按钮 loading 并禁用按钮
    confirmLoading.value = true;
    confirmDisabled.value = true;

    // 触发表单校验
    formRef.value?.validate(async (valid) => {
      if (valid) {
        // 统一判断“新增/编辑”，适配常见项目写法：
        // - 数字或字符串 '0' 表示新增
        // - 字符串 'ADD'/'add' 也视为新增
        // - 其它情况默认为编辑
        const isAdd =
          String(model[typeKey]) === '0' ||
          model[typeKey] === 'ADD' ||
          model[typeKey] === 'add' ||
          model[typeKey] === 0;

        // 调用对应 API
        const res = isAdd ? await opts.addApi(model) : await opts.editApi(model);

        // 成功处理：提示消息 -> 成功回调 -> 关闭弹窗
        if (res && res.code === 200) {
          if (res.msg) ElMessage.success(res.msg);
          opts.onSuccess?.(res);
          opts.close();
        }
      }

      // 无论校验是否通过，最终都需要复位按钮状态
      confirmLoading.value = false;
      confirmDisabled.value = false;
    });
  };

  // 暴露统一的提交能力与按钮状态供组件使用
  return {
    confirmLoading,
    confirmDisabled,
    commit,
  };
}