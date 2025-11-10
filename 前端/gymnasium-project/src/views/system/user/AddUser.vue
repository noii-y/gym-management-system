<!-- 用户新增/编辑弹框组件 -->
<template>
    <!-- 系统弹框组件，用于显示用户新增/编辑表单 -->
    <SysDialog :title="dialog.title" :height="dialog.height" :width="dialog.width" :visible="dialog.visible"
        @onClose="onClose" @onConfirm="commit">
        <template v-slot:content>
            <!-- 用户信息表单 -->
            <el-form :model="addModel" ref="addFormRef" :rules="rules" label-width="80px" size="default">
                <!-- 第一行：账户 和 密码（密码仅新增显示） -->
                <el-row>
                    <el-col :span="12" :offset="0">
                        <el-form-item prop="username" label="账户">
                            <el-input v-model="addModel.username"></el-input>
                        </el-form-item>
                    </el-col>
                    <el-col v-if="addModel.type == EditType.ADD" :span="12" :offset="0">
                        <el-form-item prop="password" label="密码">
                            <el-input v-model="addModel.password"></el-input>
                        </el-form-item>
                    </el-col>
                </el-row>
                <!-- 第二行：类型 和 状态 -->
                <el-row>
                    <el-col :span="12" :offset="0">
                        <el-form-item prop="userType" label="类型">
                            <el-radio-group v-model="addModel.userType">
                                <el-radio :label="'1'">员工</el-radio>
                                <el-radio :label="'2'">教练</el-radio>
                            </el-radio-group>
                        </el-form-item>
                    </el-col>
                    <el-col :span="12" :offset="0">
                        <el-form-item prop="status" label="状态">
                            <el-radio-group v-model="addModel.status">
                                <el-radio :label="'0'">停用</el-radio>
                                <el-radio :label="'1'">启用</el-radio>
                            </el-radio-group>
                        </el-form-item>
                    </el-col>
                </el-row>
                <!-- 第三行：姓名 和 性别 -->
                <el-row>
                    <el-col :span="12" :offset="0">
                        <el-form-item prop="nickName" label="姓名">
                            <el-input v-model="addModel.nickName"></el-input>
                        </el-form-item>
                    </el-col>
                    <el-col :span="12" :offset="0">
                        <el-form-item prop="sex" label="性别">
                            <el-radio-group v-model="addModel.sex">
                                <el-radio :label="'0'">男</el-radio>
                                <el-radio :label="'1'">女</el-radio>
                            </el-radio-group>
                        </el-form-item>
                    </el-col>
                </el-row>
                <!-- 第四行：电话 和 邮箱 -->
                <el-row>
                    <el-col :span="12" :offset="0">
                        <el-form-item prop="phone" label="电话">
                            <el-input v-model="addModel.phone"></el-input>
                        </el-form-item>
                    </el-col>
                    <el-col :span="12" :offset="0">
                        <el-form-item prop="email" label="邮箱">
                            <el-input v-model="addModel.email"></el-input>
                        </el-form-item>
                    </el-col>
                </el-row>
                <!-- 第五行：薪水 -->
                <el-row>
                    <el-col :span="12" :offset="0">
                        <el-form-item prop="salary" label="薪水">
                            <el-input v-model="addModel.salary"></el-input>
                        </el-form-item>
                    </el-col>
                </el-row>
                <!-- 角色（仅编辑显示） -->
                <el-row v-if="addModel.type != EditType.ADD">
                    <el-col :span="24" :offset="0">
                        <el-form-item prop="roleId" label="角色">
                            <el-select v-model="addModel.roleId" class="m-2" placeholder="请选择角色" size="default">
                                <el-option v-for="item in roleData.list" :key="item.value" :label="item.label"
                                    :value="item.value" />
                            </el-select>
                        </el-form-item>
                    </el-col>
                </el-row>
            </el-form>
        </template>
    </SysDialog>
</template>
/**
 * 用户新增/编辑弹框组件
 * 提供用户的新增和编辑功能，包含完整的用户信息表单
 */
<script setup lang="ts">
import type { AddUserModel } from "@/api/user/UserModel";
import SysDialog from "@/components/SysDialog.vue";
import useDialog from "@/hooks/useDialog";
import { nextTick, reactive, ref } from "vue";
import useSelectRole from "@/composables/user/useSelectRole";
import { ElMessage, type FormInstance } from "element-plus";
import { addApi, editApi } from "@/api/user/index";
import { EditType, Title, UserType } from "@/type/BaseEnum";
import useInstance from "@/hooks/useInstance";

/** 全局实例，用于对象拷贝等工具方法 */
const { global } = useInstance();

/** 表单的ref引用，用于表单验证 */
const addFormRef = ref<FormInstance>();

/** 角色相关数据和方法 */
const { roleData, listRole, roleId, getRole } = useSelectRole();

/** 弹框相关属性和方法 */
const { dialog, onClose, onShow } = useDialog();
/**
 * 显示弹框的方法，供父组件调用
 * @param {string} type - 操作类型（新增/编辑）
 * @param {AddUserModel} row - 编辑时的用户数据（可选）
 */
const show = async (type: string, row?: AddUserModel) => {
    roleId.value = "";
    addModel.roleId = "";
    dialog.height = 270;
    addModel.type = type;
    // 获取角色数据列表
    await listRole();
    await getRole(row!?.userId);
    // 根据操作类型设置弹框标题
    type == EditType.ADD
        ? (dialog.title = Title.ADD)
        : (dialog.title = Title.EDIT);
    if (type == EditType.EDIT && row) {
        // 编辑时回显数据到表单
        nextTick(() => {
            global.$objCoppy(row, addModel);
            addModel.roleId = roleId.value;
        });
    }
    onShow();
    // 重置表单验证状态
    addFormRef.value?.resetFields();
};

/** 暴露show方法给父组件使用 */
defineExpose({
    show,
});

/** 
 * 用户表单数据模型
 * @property {string} userId - 用户ID
 * @property {string} type - 操作类型（新增/编辑）
 * @property {string} roleId - 角色ID
 * @property {string} username - 登录账户
 * @property {string} password - 登录密码
 * @property {string} phone - 电话号码
 * @property {string} email - 邮箱地址
 * @property {string} sex - 性别（0:男，1:女）
 * @property {string} userType - 用户类型（1:员工，2:教练）
 * @property {string} status - 状态（0:停用，1:启用）
 * @property {string} salary - 薪水
 * @property {string} nickName - 姓名
 */
const addModel = reactive<AddUserModel>({
    userId: "",
    type: "",
    roleId: "",
    username: "",
    password: "",
    phone: "",
    email: "",
    sex: "",
    userType: "",
    status: "",
    salary: "",
    nickName: "",
});
/** 表单验证规则配置 */
const rules = reactive({
    nickName: [
        {
            required: true,
            trigger: "change",
            message: "请输入姓名",
        },
    ],
    phone: [
        {
            required: true,
            trigger: "change",
            message: "请输入电话",
        },
    ],
    sex: [
        {
            required: true,
            trigger: "change",
            message: "请选择性别",
        },
    ],
    userType: [
        {
            required: true,
            trigger: "change",
            message: "请选择类型",
        },
    ],
    status: [
        {
            required: true,
            trigger: "change",
            message: "请选择状态",
        },
    ],
    salary: [
        {
            required: true,
            trigger: "change",
            message: "请输入薪水",
        },
    ],
    username: [
        {
            required: true,
            trigger: "change",
            message: "请输入账户",
        },
    ],
    password: [
        {
            required: true,
            trigger: "change",
            message: "请输入密码",
        },
    ],
    // 角色在新增时不必填，编辑时需要；使用自定义校验器保证一致性
    roleId: [
        {
            validator: (_rule: any, value: string, callback: (e?: Error) => void) => {
                if (addModel.type === EditType.ADD) {
                    callback();
                } else {
                    if (!value) callback(new Error("请选择角色")); else callback();
                }
            },
            trigger: "change",
        },
    ],
});

/** 定义组件事件，用于通知父组件刷新列表 */
const emits = defineEmits(["refresh"]);

/**
 * 提交表单数据
 * 根据操作类型调用新增或编辑API，成功后刷新父组件列表并关闭弹框
 */
const commit = () => {
    addFormRef.value?.validate(async (valid) => {
        if (valid) {
            let res = null;
            if (addModel.type == EditType.ADD) {
                // 调用新增用户API
                res = await addApi(addModel);
            } else {
                // 调用编辑用户API
                res = await editApi(addModel);
            }
            if (res && res.code == 200) {
                ElMessage.success(res.msg);
                // 通知父组件刷新用户列表
                emits("refresh");
                onClose();
            }
        }
    });
};
</script>
<style scoped></style>