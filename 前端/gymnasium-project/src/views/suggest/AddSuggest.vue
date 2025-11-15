<!-- 意见反馈新增/编辑弹窗组件 -->
<template>
    <!-- 系统弹窗组件，用于显示新增/编辑表单 -->
    <SysDialog :title="dialog.title" :height="dialog.height" :width="dialog.width" :visible="dialog.visible"
        @onClose="onClose" @onConfirm="commit">
        <template v-slot:content>
            <!-- 意见反馈信息表单 -->
            <el-form :model="addModel" ref="addFormRef" :rules="rules" label-width="80px" size="default">
                <!-- 反馈标题输入框 -->
                <el-form-item prop="title" label="标题">
                    <el-input v-model="addModel.title"></el-input>
                </el-form-item>
                <!-- 反馈内容输入框 -->
                <el-form-item prop="content" label="内容">
                    <el-input type="textarea" v-model="addModel.content"></el-input>
                </el-form-item>
            </el-form>
        </template>
    </SysDialog>
</template>

<script setup lang="ts">
/**
 * 意见反馈新增/编辑弹窗组件
 * 提供意见反馈信息的新增和编辑功能
 */

// 导入类型定义
import { type SuggestType } from "@/api/suggest/SuggestModel";
// 导入自定义组件
import SysDialog from "@/components/SysDialog.vue";
// 导入组合式函数
import useDialog from "@/hooks/useDialog";
import useInstance from "@/hooks/useInstance";
// 导入Element Plus组件和类型
import { ElMessage, type FormInstance } from "element-plus";
// 导入Vue相关
import { nextTick, reactive, ref } from "vue";
// 导入API函数
import { addApi, editApi } from "@/api/suggest/index";
// 导入枚举类型
import { EditType, Title } from "@/type/BaseEnum";

/**
 * 全局工具实例
 */
const { global } = useInstance();

/**
 * 表单引用
 */
const addFormRef = ref<FormInstance>();

/**
 * 弹窗相关属性和方法
 */
const { dialog, onClose, onConfirm, onShow } = useDialog();

/**
 * 显示弹窗方法
 * @param type 操作类型（新增/编辑）
 * @param row 编辑时的数据行
 */
const show = (type: string, row?: SuggestType) => {

    // 设置弹窗高度和标题
    dialog.height = 160;
    dialog.title = type === EditType.ADD ? Title.ADD : Title.EDIT;

    // 编辑模式下回显数据
    if (type === EditType.EDIT) {
        nextTick(() => {
            if (row) {
                global.$objCopy(row, addModel);
            }
        });
    }

    // 显示弹窗并重置表单
    onShow();
    addFormRef.value?.resetFields();
    addModel.type = type;
};

/**
 * 暴露给父组件的方法
 */
defineExpose({
    show,
});

/**
 * 表单数据模型
 */
const addModel = reactive<SuggestType>({
    type: "",       // 操作类型
    id: "",         // 反馈ID
    title: "",      // 反馈标题
    content: "",    // 反馈内容
});

/**
 * 表单验证规则
 */
const rules = reactive({
    title: [
        {
            required: true,
            trigger: "blur",
            message: "请输入标题",
        },
    ],
    content: [
        {
            required: true,
            trigger: "blur",
            message: "请输入内容",
        },
    ],
});

/**
 * 定义组件事件
 */
const emits = defineEmits(["reFresh"]);

/**
 * 提交表单数据
 * 根据操作类型调用新增或编辑API
 */
const commit = () => {
    addFormRef.value?.validate(async (valid) => {
        if (valid) {
            let res = null;
            // 根据类型调用对应API
            if (addModel.type === EditType.ADD) {
                res = await addApi(addModel);
            } else {
                res = await editApi(addModel);
            }

            // 处理响应结果
            if (res && res.code === 200) {
                ElMessage.success(res.msg);     // 显示成功消息
                emits("reFresh");               // 通知父组件刷新列表
                onClose();                      // 关闭弹窗
            }
        }
    });
};
</script>

<style scoped></style>