<!--
  失物认领对话框组件
  提供失物认领功能，用户可以输入认领人信息来认领失物
-->
<template>
    <!-- 系统对话框，用于显示认领表单 -->
    <SysDialog :title="dialog.title" :height="dialog.height" :width="dialog.width" :visible="dialog.visible"
        @onClose="onClose" @onConfirm="commit">
        <template v-slot:content>
            <!-- 认领人信息表单 -->
            <el-form :model="lostPerson" ref="lostRef" :rules="rules" label-width="80px" :inline="false" size="default">
                <!-- 认领人姓名输入框 -->
                <el-form-item prop="lostPerson" label="认领人">
                    <el-input v-model="lostPerson.lostPerson" placeholder="请输入认领人" />
                </el-form-item>
            </el-form>
        </template>
    </SysDialog>
</template>

<script setup lang="ts">
/**
 * 失物认领对话框组件
 * 提供失物认领功能，用户可以输入认领人信息来认领失物
 */

// 导入类型定义
import type { LostType } from "@/api/lost/LostModel";
// 导入组件
import SysDialog from "@/components/SysDialog.vue";
// 导入组合式函数
import useDialog from "@/hooks/useDialog";
// 导入Element Plus组件
import { ElMessage, type FormInstance } from "element-plus";
// 导入Vue函数
import { reactive, ref } from "vue";
// 导入API接口
import { editApi } from "@/api/lost";

// 表单引用
const lostRef = ref<FormInstance>();

/**
 * 认领人信息数据模型
 * 包含失物的基本信息和认领人信息
 */
const lostPerson = reactive<LostType>({
    type: "",           // 操作类型
    lostId: "",         // 失物ID
    lostName: "",       // 物品名称
    foundTime: "",      // 捡到时间
    foundAddres: "",    // 捡到地址
    foundPerson: "",    // 捡到人
    foundPhone: "",     // 联系电话
    status: "1",        // 认领状态（设为已认领）
    lostPerson: "",     // 认领人
});

/**
 * 对话框相关属性和方法
 */
const { dialog, onClose, onConfirm, onShow } = useDialog();

/**
 * 显示认领对话框的方法
 * @param row 要认领的失物信息
 */
const show = (row: LostType) => {
    dialog.title = "认领";
    dialog.height = 150;
    lostPerson.lostId = row.lostId;
    onShow();
    // 清空表单验证
    lostRef.value?.resetFields();
};

// 暴露给父组件的方法
defineExpose({
    show,
});

/**
 * 表单验证规则
 * 定义认领人字段的验证要求
 */
const rules = reactive({
    lostPerson: [
        {
            required: true,
            message: "请录入认领人",
            trigger: "blur",
        },
    ],
});

// 定义事件发射器
const emiets = defineEmits(['reFresh']);

/**
 * 表单提交处理函数
 * 验证表单并提交认领信息
 */
const commit = () => {
    lostRef.value?.validate(async (valid) => {
        if (valid) {
            // 调用编辑API更新认领信息
            let res = await editApi(lostPerson);
            if (res && res.code == 200) {
                ElMessage.success('认领成功');
                // 通知父组件刷新数据
                emiets('reFresh');
                onClose();
            }
        }
    });
};
</script>

<style scoped></style>