<!--
  失物招领新增/编辑对话框组件
  提供失物招领信息的新增和编辑功能
  包含物品名称、捡到时间、地址、人员、联系电话、状态等字段
-->
<template>
    <!-- 系统对话框，用于显示失物招领表单 -->
    <SysDialog :title="dialog.title" :height="dialog.height" :width="dialog.width" :visible="dialog.visible"
        @onConfirm="commit" @onClose="onClose">
        <template v-slot:content>
            <!-- 失物招领信息表单 -->
            <el-form :model="addModel" ref="addFormRef" :rules="rules" label-width="80px" size="default">
                <!-- 第一行：物品名称和捡到时间 -->
                <el-row>
                    <el-col :span="12" :offset="0">
                        <!-- 物品名称输入框 -->
                        <el-form-item prop="lostName" label="物品名称">
                            <el-input v-model="addModel.lostName"></el-input>
                        </el-form-item>
                    </el-col>
                    <el-col :span="12" :offset="0">
                        <!-- 捡到时间选择器 -->
                        <el-form-item prop="foundTime" label="捡到时间">
                            <el-date-picker style="width: 100%" v-model="addModel.foundTime" type="date"
                                placeholder="请选择捡到时间" size="default" value-format="YYYY-MM-DD" format="YYYY-MM-DD" />
                        </el-form-item>
                    </el-col>
                </el-row>
                <!-- 第二行：捡到地址和捡到人 -->
                <el-row>
                    <el-col :span="12" :offset="0">
                        <!-- 捡到地址输入框 -->
                        <el-form-item prop="foundAddres" label="捡到地址">
                            <el-input v-model="addModel.foundAddres"></el-input>
                        </el-form-item>
                    </el-col>
                    <el-col :span="12" :offset="0">
                        <!-- 捡到人输入框 -->
                        <el-form-item prop="foundPerson" label="捡到人">
                            <el-input v-model="addModel.foundPerson"></el-input>
                        </el-form-item>
                    </el-col>
                </el-row>
                <!-- 第三行：联系电话和状态 -->
                <el-row>
                    <el-col :span="12" :offset="0">
                        <!-- 联系电话输入框 -->
                        <el-form-item prop="foundPhone" label="联系电话">
                            <el-input v-model="addModel.foundPhone"></el-input>
                        </el-form-item>
                    </el-col>
                    <el-col :span="12" :offset="0">
                        <!-- 认领状态单选框组 -->
                        <el-form-item prop="status" label="状态">
                            <el-radio-group v-model="addModel.status">
                                <el-radio :label="'0'">未认领</el-radio>
                                <el-radio :label="'1'">已认领</el-radio>
                            </el-radio-group>
                        </el-form-item>
                    </el-col>
                </el-row>
            </el-form>
        </template>
    </SysDialog>
</template>
<script setup lang="ts">
/**
 * 失物招领新增/编辑对话框组件
 * 提供失物招领信息的新增和编辑功能
 */

// 导入API接口
import { addApi, editApi } from "@/api/lost/index";
// 导入类型定义
import type { LostType } from "@/api/lost/LostModel";
// 导入组件
import SysDialog from "@/components/SysDialog.vue";
// 导入组合式函数
import useDialog from "@/hooks/useDialog";
import useInstance from "@/hooks/useInstance";
// 导入枚举和类型
import { EditType, Title } from "@/type/BaseEnum";
// 导入Element Plus组件
import { ElMessage, type FormInstance } from "element-plus";
// 导入Vue函数
import { nextTick, reactive, ref } from "vue";

// 获取全局实例
const { global } = useInstance();

// 表单引用
const addFormRef = ref<FormInstance>();

/**
 * 对话框相关属性和方法
 */
const { dialog, onClose, onConfirm, onShow } = useDialog();

/**
 * 显示对话框的方法
 * @param type 操作类型（新增/编辑）
 * @param row 编辑时的数据行
 */
const show = (type: string, row?: LostType) => {
    // 设置对话框标题
    type == EditType.ADD
        ? (dialog.title = Title.ADD)
        : (dialog.title = Title.EDIT);
    dialog.height = 180;
    
    // 如果是编辑模式，设置回显数据
    if (type == EditType.EDIT) {
        nextTick(() => {
            global.$objCopy(row, addModel);
        });
    }
    
    onShow();
    addModel.type = type;
    // 清空表单验证
    addFormRef.value?.resetFields();
};

// 暴露给父组件的方法
defineExpose({
    show,
});

/**
 * 表单数据模型
 * 包含失物招领的所有字段信息
 */
const addModel = reactive<LostType>({
    type: "",           // 操作类型
    lostId: "",         // 失物ID
    lostName: "",       // 物品名称
    foundTime: "",      // 捡到时间
    foundAddres: "",    // 捡到地址
    foundPerson: "",    // 捡到人
    foundPhone: "",     // 联系电话
    status: "",         // 认领状态
    lostPerson: "",     // 认领人
});

/**
 * 表单验证规则
 * 定义各字段的验证要求
 */
const rules = reactive({
    lostName: [
        {
            trigger: "blur",
            message: "请填写物品名称",
            required: true,
        },
    ],
    foundTime: [
        {
            trigger: "blur",
            message: "请填写捡到时间",
            required: true,
        },
    ],
    foundAddres: [
        {
            trigger: "blur",
            message: "请填写捡到地址",
            required: true,
        },
    ],
    foundPerson: [
        {
            trigger: "blur",
            message: "请填写捡到人",
            required: true,
        },
    ],
    foundPhone: [
        {
            trigger: "blur",
            message: "请填写捡到人电话",
            required: true,
        },
    ],
    status: [
        {
            trigger: "blur",
            message: "请选择认领状态",
            required: true,
        },
    ],
});

// 定义事件发射器
const emits = defineEmits(["reFresh"]);

/**
 * 表单提交处理函数
 * 验证表单并调用相应的API进行新增或编辑操作
 */
const commit = () => {
    addFormRef.value?.validate(async (valid) => {
        if (valid) {
            let res = null;
            // 根据操作类型调用不同的API
            if (addModel.type == EditType.ADD) {
                res = await addApi(addModel);
            } else {
                res = await editApi(addModel);
            }
            
            // 处理API响应
            if (res && res.code == 200) {
                ElMessage.success(res.msg);
                // 通知父组件刷新数据
                emits("reFresh");
                onClose();
            }
        }
    });
};
</script>
<style scoped></style>