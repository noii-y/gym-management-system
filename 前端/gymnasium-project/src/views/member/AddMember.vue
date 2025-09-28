<!-- 会员新增/编辑对话框 -->
<!-- 包含会员的基本信息表单，支持新增和编辑功能 -->
<template>
    <!-- 系统对话框组件，用于显示会员信息表单 -->
    <SysDialog :title="dialog.title" :width="dialog.width" :height="dialog.height" :visible="dialog.visible"
        @onClose="onClose" @onConfirm="commit">
        <template v-slot:content>
            <!-- 会员信息表单 -->
            <el-form :model="addModel" ref="addRormRef" :rules="rules" label-width="80px" size="default">
                <!-- 第一行：姓名和性别 -->
                <el-row>
                    <el-col :span="12" :offset="0">
                        <!-- 会员姓名输入框 -->
                        <el-form-item prop="name" label="姓名">
                            <el-input v-model="addModel.name"></el-input>
                        </el-form-item>
                    </el-col>
                    <el-col :span="12" :offset="0">
                        <!-- 性别选择（单选按钮组） -->
                        <el-form-item prop="sex" label="性别">
                            <el-radio-group v-model="addModel.sex">
                                <el-radio :label="'0'">男</el-radio>
                                <el-radio :label="'1'">女</el-radio>
                            </el-radio-group>
                        </el-form-item>
                    </el-col>
                </el-row>
                <!-- 第二行：电话和年龄 -->
                <el-row>
                    <el-col :span="12" :offset="0">
                        <!-- 联系电话输入框 -->
                        <el-form-item prop="phone" label="电话">
                            <el-input v-model="addModel.phone"></el-input>
                        </el-form-item>
                    </el-col>
                    <el-col :span="12" :offset="0">
                        <!-- 年龄输入框 -->
                        <el-form-item prop="age" label="年龄">
                            <el-input v-model="addModel.age"></el-input>
                        </el-form-item>
                    </el-col>
                </el-row>
                <!-- 第三行：生日和加入时间 -->
                <el-row>
                    <el-col :span="12" :offset="0">
                        <!-- 生日日期选择器 -->
                        <el-form-item prop="birthday" label="生日">
                            <el-date-picker v-model="addModel.birthday" type="date" placeholder="请选择生日" size="default"
                                value-format="YYYY-MM-DD" format="YYYY-MM-DD" />
                        </el-form-item>
                    </el-col>
                    <el-col :span="12" :offset="0">
                        <!-- 加入时间日期选择器 -->
                        <el-form-item prop="joinTime" label="加入时间">
                            <el-date-picker v-model="addModel.joinTime" type="date" placeholder="请选择加入时间" size="default"
                                format="YYYY-MM-DD" value-format="YYYY-MM-DD" />
                        </el-form-item>
                    </el-col>
                </el-row>
                <el-row>
                    <el-col :span="12" :offset="0">
                        <el-form-item prop="height" label="身高">
                            <el-input v-model="addModel.height"></el-input>
                        </el-form-item>
                    </el-col>
                    <el-col :span="12" :offset="0">
                        <el-form-item prop="weight" label="体重">
                            <el-input v-model="addModel.weight"></el-input>
                        </el-form-item>
                    </el-col>
                </el-row>
                <el-row>
                    <el-col :span="12" :offset="0">
                        <el-form-item prop="waist" label="腰围">
                            <el-input v-model="addModel.waist"></el-input>
                        </el-form-item>
                    </el-col>
                    <el-col :span="12" :offset="0">
                        <el-form-item prop="roleId" label="角色">
                            <el-select v-model="addModel.roleId" class="m-2" placeholder="请选择角色" size="default">
                                <el-option v-for="item in roleData.list" :key="item.value" :label="item.label"
                                    :value="item.value" />
                            </el-select>
                        </el-form-item>
                    </el-col>
                </el-row>
                <el-row>
                    <el-col :span="12" :offset="0">
                        <el-form-item prop="status" label="状态">
                            <el-radio-group v-model="addModel.status">
                                <el-radio :label="'0'">停用</el-radio>
                                <el-radio :label="'1'">启用</el-radio>
                            </el-radio-group>
                        </el-form-item>
                    </el-col>
                    <el-col :span="12" :offset="0">
                        <el-form-item prop="username" label="会员卡号">
                            <el-input type="number" v-model="addModel.username"></el-input>
                        </el-form-item>
                    </el-col>
                </el-row>
                <el-row>
                    <el-col :span="12" :offset="0">
                        <el-form-item prop="password" label="密码">
                            <el-input v-model="addModel.password"></el-input>
                        </el-form-item>
                    </el-col>
                </el-row>
            </el-form>
        </template>
    </SysDialog>
</template>
<script setup lang="ts">
/**
 * 会员新增/编辑对话框组件
 * 提供会员信息的添加和编辑功能，包含完整的会员信息表单
 */
import SysDialog from "@/components/SysDialog.vue";
import useDialog from "@/hooks/useDialog";
import { nextTick, reactive, ref } from "vue";
import type { MemberType } from "@/api/member/MemberModel";
import type { FormInstance } from "element-plus";
import { ElMessage } from "element-plus";
import { addApi, editApi } from "@/api/member/index";
import { EditType, Title } from "@/type/BaseEnum";
import useInstance from "@/hooks/useInstance";
import useSelectRole from "@/composables/user/useSelectRole";

/**
 * 全局实例工具
 */
const { global } = useInstance();

/**
 * 表单引用，用于表单验证
 */
const addRormRef = ref<FormInstance>();

/**
 * 角色相关数据和方法
 * 用于获取和管理会员角色信息
 */
const { roleData, listRole, roleMemberId, getMemberRole } =
    useSelectRole();

/**
 * 对话框相关属性和方法
 */
const { dialog, onClose, onConfirm, onShow } = useDialog();
/**
 * 显示对话框
 * @param type 操作类型（新增/编辑）
 * @param row 编辑时的会员数据（可选）
 */
const show = async (type: string, row?: MemberType) => {
    // 获取角色列表数据
    await listRole()
    // 获取会员的角色信息
    await getMemberRole(row!?.memberId)
    
    // 设置对话框尺寸
    dialog.width = 680;
    dialog.height = 350;
    
    // 根据操作类型设置对话框标题
    type == EditType.ADD
        ? (dialog.title = Title.ADD)
        : (dialog.title = Title.EDIT);
        
    if (EditType.EDIT == type) {
        // 编辑模式：将要编辑的数据复制到表单对象
        nextTick(() => {
            global.$objCoppy(row, addModel);
            addModel.roleId = roleMemberId.value
        });
    }
    addModel.type = type;
    onShow();
    // 重置表单验证状态
    addRormRef.value?.resetFields()
};

/**
 * 暴露方法给父组件调用
 */
defineExpose({
    show,
});

/**
 * 表单数据模型
 * 包含会员的所有基本信息字段
 */
const addModel = reactive<MemberType>({
    type: "",        // 操作类型
    memberId: "",    // 会员ID
    name: "",        // 姓名
    sex: "",         // 性别（0:男 1:女）
    phone: "",       // 联系电话
    age: "",         // 年龄
    birthday: "",    // 生日
    height: "",      // 身高
    weight: "",      // 体重
    waist: "",       // 腰围
    joinTime: "",    // 加入时间
    endTime: "",     // 结束时间
    username: "",    // 会员卡号
    password: "",    // 密码
    status: "",      // 状态（0:停用 1:启用）
    roleId: ''       // 角色ID
});
/**
 * 表单验证规则
 * 定义各字段的验证要求
 */
const rules = reactive({
    name: [
        {
            required: true,
            trigger: "change",
            message: "请填写姓名",
        },
    ],
    sex: [
        {
            required: true,
            trigger: "change",
            message: "请选择性别",
        },
    ],
    phone: [
        {
            required: true,
            trigger: "change",
            message: "请填写电话",
        },
    ],
    joinTime: [
        {
            required: true,
            trigger: "change",
            message: "请填写加入时间",
        },
    ],
    username: [
        {
            required: true,
            trigger: "change",
            message: "请填写会员卡号",
        },
    ],
    password: [
        {
            required: true,
            trigger: "change",
            message: "请填写密码",
        },
    ],
    status: [
        {
            required: true,
            trigger: "change",
            message: "请选择状态",
        },
    ],
});

/**
 * 定义组件事件
 */
const emits = defineEmits(["refresh"]);

/**
 * 表单提交处理
 * 验证表单并调用相应的API进行新增或编辑操作
 */
const commit = () => {
    addRormRef.value?.validate(async (valid) => {
        if (valid) {
            let res = null;
            // 根据操作类型调用不同的API
            if(addModel.type == EditType.ADD){
                res = await addApi(addModel);
            }else{
                res = await editApi(addModel)
            }
            
            if (res && res.code == 200) {
                ElMessage.success(res.msg);
                emits("refresh");  // 通知父组件刷新数据
                onClose();         // 关闭对话框
            }
        }
    });
};
</script>
<style scoped></style>