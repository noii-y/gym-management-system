<!-- 会员充值对话框组件 -->
<!-- 为指定会员进行账户充值操作 -->
<template>
    <!-- 系统对话框，用于显示充值表单 -->
    <SysDialog :title="dialog.title" :height="dialog.height" :width="dialog.width" :visible="dialog.visible"
        @onClose="onClose" @onConfirm="commit">
        <template v-slot:content>
            <!-- 充值表单 -->
            <el-form :model="addModel" ref="addFormRef" :rules="rules" label-width="80px" size="default">
                <!-- 充值金额输入框 -->
                <el-form-item prop="money" label="充值金额">
                    <el-input type="number" v-model="addModel.money">
                    </el-input>
                </el-form-item>
            </el-form>
        </template>
    </SysDialog>
</template>

<script setup lang="ts">
/**
 * 会员充值对话框组件
 * 提供为会员账户充值的功能
 */
import type { MemberType, Recharge } from
    "@/api/member/MemberModel";
import SysDialog from "@/components/SysDialog.vue";
import useDialog from "@/hooks/useDialog";
import { ElMessage } from "element-plus";
import type { FormInstance } from "element-plus";
import { reactive, ref } from "vue";
import { rechargeApi } from "@/api/member/index";

/**
 * 表单引用，用于表单验证
 */
const addFormRef = ref<FormInstance>();

/**
 * 充值表单数据模型
 */
const addModel = reactive<Recharge>({
    userId: "",    // 用户ID
    memberId: "",  // 会员ID
    money: 0,      // 充值金额
});

/**
 * 充值金额验证函数
 * @param rule 验证规则
 * @param value 输入值
 * @param callback 回调函数
 */
const validMoney = (rule: any, value: any, callback: any) => {
    if (value <= 0) {
        callback(new Error("充值金额不能小于0"));
    } else {
        callback();
    }
};

/**
 * 表单验证规则
 */
const rules = reactive({
    money: [
        {
            required: true,
            trigger: "change",
            validator: validMoney,
        },
    ],
});

/**
 * 对话框相关属性和方法
 */
const { dialog, onClose, onConfirm, onShow } = useDialog();

/**
 * 显示充值对话框
 * @param row 会员信息对象
 */
const show = (row: MemberType) => {
    dialog.title = "为【" + row.name + "】充值";
    addModel.memberId = row.memberId
    onShow();
    // 重置表单验证状态
    addFormRef.value?.resetFields();
};

/**
 * 暴露方法给父组件调用
 */
defineExpose({
    show,
});

/**
 * 定义组件事件
 */
const emits = defineEmits(['refresh'])

/**
 * 提交充值表单
 * 验证表单并调用充值API
 */
const commit = () => {
    addFormRef.value?.validate(async (valid) => {
        if (valid) {
            let res = await rechargeApi(addModel);
            if (res && res.code == 200) {
                ElMessage.success(res.msg)
                emits('refresh')  // 通知父组件刷新数据
                onClose();        // 关闭对话框
            }
        }
    });
};
</script>

<style scoped></style>