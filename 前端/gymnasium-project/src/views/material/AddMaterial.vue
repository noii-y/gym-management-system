<!-- 健身器材新增/编辑弹窗组件 -->
<template>
    <!-- 系统弹窗组件，用于显示新增/编辑表单 -->
    <SysDialog :title="dialog.title" :height="dialog.height" :width="dialog.width" :visible="dialog.visible"
        @onConfirm="commit" @onClose="onClose">
        <template #content>
            <!-- 健身器材信息表单 -->
            <el-form ref="addFormRef" :model="addModel" :rules="rules" label-width="80px" size="default">
                <!-- 器材名称输入框 -->
                <el-form-item prop="name" label="器材名称">
                    <el-input v-model="addModel.name" />
                </el-form-item>

                <!-- 器材数量输入框 -->
                <el-form-item prop="numTotal" label="器材数量">
                    <el-input v-model.number="addModel.numTotal" />
                </el-form-item>

                <!-- 器材简介输入框 -->
                <el-form-item prop="details" label="简介">
                    <el-input type="textarea" v-model="addModel.details" />
                </el-form-item>
            </el-form>
        </template>
    </SysDialog>
</template>

<script setup lang="ts">
/**
 * 健身器材新增/编辑弹窗组件
 * 提供健身器材信息的新增和编辑功能
 */

// 导入Vue相关
import { nextTick, reactive, ref } from 'vue';
// 导入Element Plus组件和类型
import { ElMessage } from 'element-plus';
import type { FormInstance } from 'element-plus';
// 导入自定义组件
import SysDialog from '@/components/SysDialog.vue';
// 导入组合式函数
import useDialog from '@/hooks/useDialog';
import useInstance from '@/hooks/useInstance';
// 导入类型定义
import type { MaterialType } from '@/api/material/MaterialModel';
// 导入API函数
import { addApi, editApi } from '@/api/material/index';
// 导入枚举类型
import { EditType, Title } from '@/type/BaseEnum';

/**
 * 表单引用
 */
const addFormRef = ref<FormInstance>();

/**
 * 弹窗相关属性和方法
 */
const { dialog, onClose, onShow } = useDialog();

/**
 * 全局工具实例
 */
const { global } = useInstance();

/**
 * 显示弹窗方法
 * @param type 操作类型（新增/编辑）
 * @param row 编辑时的数据行
 */
const show = (type: string, row?: MaterialType) => {
    // 设置弹窗标题
    dialog.title = type === EditType.ADD ? Title.ADD : Title.EDIT;

    // 编辑模式下回显数据
    if (type === EditType.EDIT && row) {
        nextTick(() => global.$objCopy(row, addModel));
    }

    // 显示弹窗并重置表单
    onShow();
    addFormRef.value?.resetFields();
    addModel.type = type;
};

/**
 * 暴露给父组件的方法
 */
defineExpose({ show });

/**
 * 表单数据模型
 */
const addModel = reactive<MaterialType>({
    type: '',       // 操作类型
    name: '',       // 器材名称
    numTotal: 0,    // 器材总数量
    details: '',    // 器材详细介绍
    id: '',         // 器材ID
});

/**
 * 自定义数量校验函数
 * @param _ 规则对象（未使用）
 * @param value 输入值
 * @param cb 回调函数
 */
const validateNum = (_: any, value: any, cb: any) => {
    if (!value || value <= 0) cb(new Error('请填写数量'));
    else cb();
};

/**
 * 表单验证规则
 */
const rules = reactive({
    name: [
        { required: true, message: '请输入器材名称', trigger: 'blur' },
    ],
    numTotal: [
        { required: true, validator: validateNum, trigger: 'blur' },
    ],
});

/**
 * 定义组件事件
 */
const emit = defineEmits<{
    reFresh: [];    // 刷新列表事件
}>();

/**
 * 提交表单数据
 * 根据操作类型调用新增或编辑API
 */
const commit = () => {
    addFormRef.value?.validate(async (valid) => {
        if (!valid) return;

        // 根据类型调用对应API
        const res =
            addModel.type === EditType.ADD
                ? await addApi(addModel)
                : await editApi(addModel);

        // 处理响应结果
        if (res?.code === 200) {
            emit('reFresh');                // 通知父组件刷新列表
            ElMessage.success(res.msg);     // 显示成功消息
            onClose();                      // 关闭弹窗
        }
    });
};
</script>

<style scoped></style>