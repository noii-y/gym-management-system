<!-- 新增/编辑菜单对话框组件 -->
<!-- 提供菜单的新增和编辑功能，支持不同类型菜单的配置 -->
<template>
    <!-- 系统对话框，用于显示菜单表单 -->
    <SysDialog :title="dialog.title" :height="dialog.height" :width="dialog.width" :visible="dialog.visible"
        @onClose="onClose" @onConfirm="commit">
        <template v-slot:content>
            <!-- 菜单信息表单 -->
            <el-form :model="addModel" ref="addFormRef" :rules="rules" label-width="80px" size="default">
                <!-- 菜单类型选择：目录、菜单、按钮 -->
                <el-form-item prop="type" label="菜单类型">
                    <el-radio-group v-model="addModel.type">
                        <el-radio :label="'0'">目录</el-radio>
                        <el-radio :label="'1'">菜单</el-radio>
                        <el-radio :label="'2'">按钮</el-radio>
                    </el-radio-group>
                </el-form-item>
                <!-- 第一行：上级菜单和菜单名称 -->
                <el-row>
                    <el-col :span="12" :offset="0">
                        <!-- 上级菜单选择，点击弹出选择器 -->
                        <el-form-item label="上级菜单" prop="parentName">
                            <el-input type="hidden" v-model="addModel.parentId"></el-input>
                            <el-input @click="selectOpen" readonly v-model="addModel.parentName"></el-input>
                        </el-form-item>
                    </el-col>
                    <el-col :span="12" :offset="0">
                        <!-- 菜单名称输入 -->
                        <el-form-item label="菜单名称" prop="title">
                            <el-input v-model="addModel.title"></el-input>
                        </el-form-item>
                    </el-col>
                </el-row>
                <!-- 第二行：菜单图标和路由名称（按钮类型不显示） -->
                <el-row v-if="addModel.type != '2'">
                    <el-col :span="12" :offset="0">
                        <!-- 菜单图标输入 -->
                        <el-form-item label="菜单图标" prop="icon">
                            <el-input v-model="addModel.icon"></el-input>
                        </el-form-item>
                    </el-col>
                    <el-col :span="12" :offset="0">
                        <!-- 路由名称输入 -->
                        <el-form-item label="路由名称" prop="name">
                            <el-input v-model="addModel.name"></el-input>
                        </el-form-item>
                    </el-col>
                </el-row>
                <!-- 第三行：菜单序号和权限字段 -->
                <el-row>
                    <el-col :span="12" :offset="0">
                        <!-- 菜单排序序号 -->
                        <el-form-item label="菜单序号" prop="orderNum">
                            <el-input v-model="addModel.orderNum"></el-input>
                        </el-form-item>
                    </el-col>
                    <el-col :span="12" :offset="0">
                        <!-- 权限控制字段 -->
                        <el-form-item label="权限字段" prop="code">
                            <el-input v-model="addModel.code"></el-input>
                        </el-form-item>
                    </el-col>
                </el-row>
                <!-- 第四行：路由地址和组件路径（根据菜单类型显示） -->
                <el-row>
                    <!-- 路由地址（按钮类型不显示） -->
                    <el-col v-if="addModel.type != '2'" :span="12" :offset="0">
                        <el-form-item label="路由地址" prop="path">
                            <el-input v-model="addModel.path"></el-input>
                        </el-form-item>
                    </el-col>
                    <!-- 组件路径（仅菜单类型显示） -->
                    <el-col v-if="addModel.type == '1'" :span="12" :offset="0">
                        <el-form-item label="组件路径" prop="url">
                            <el-input v-model="addModel.url"></el-input>
                        </el-form-item>
                    </el-col>
                </el-row>
            </el-form>
        </template>
    </SysDialog>
    <!-- 上级菜单选择对话框 -->
    <ParentMenu ref="parentRef" @selectParent="selectParent"></ParentMenu>
</template>

<script setup lang="ts">
/**
 * 新增/编辑菜单对话框组件
 * 提供菜单的新增和编辑功能，支持目录、菜单、按钮三种类型
 */
import ParentMenu from "./ParentMenu.vue";
import SysDialog from "@/components/SysDialog.vue";
import useDialog from "@/hooks/useDialog";
import type { MenuType, SelectNode } from "@/api/menu/MenuModel";
import { nextTick, reactive, ref } from "vue";
import { ElMessage } from "element-plus";
import type { FormInstance } from "element-plus";
import { addApi, editApi } from "@/api/menu";
import { EditType, Title } from "@/type/BaseEnum";
import useInstance from "@/hooks/useInstance";

/**
 * 全局实例工具
 */
const { global } = useInstance();

/**
 * 上级菜单选择组件引用
 */
const parentRef = ref<{ showParent: () => void }>();

/**
 * 表单组件引用
 */
const addFormRef = ref<FormInstance>();

/**
 * 对话框相关属性和方法
 */
const { dialog, onClose, onConfirm, onShow } = useDialog();

/**
 * 显示对话框
 * @param type 操作类型（新增/编辑）
 * @param row 编辑时的菜单数据
 */
const show = (type: string, row?: MenuType) => {
    dialog.width = 680;
    type == EditType.ADD ? (dialog.title = Title.ADD) : (dialog.title = Title.EDIT);
    if (type == EditType.EDIT) {
        // 编辑模式：将要编辑的数据复制到表单对象中
        nextTick(() => {
            global.$objCopy(row, addModel);
        });
    }
    onShow();
    // 清空表单验证状态
    addFormRef.value?.resetFields();
    addModel.editType = type;
};

/**
 * 暴露show方法供父组件调用
 */
defineExpose({
    show,
});

/**
 * 表单绑定的菜单数据模型
 */
const addModel = reactive<MenuType>({
    editType: "",    // 编辑类型
    menuId: "",      // 菜单ID
    parentId: "",    // 父菜单ID
    title: "",       // 菜单名称
    code: "",        // 权限字段
    name: "",        // 路由名称
    path: "",        // 路由地址
    url: "",         // 组件路径
    type: "",        // 菜单类型（0:目录 1:菜单 2:按钮）
    icon: "",        // 菜单图标
    parentName: "",  // 父菜单名称
    orderNum: "",    // 排序序号
    open: true,      // 是否展开
});

/**
 * 表单验证规则
 */
const rules = reactive({
    parentName: [
        {
            required: true,
            trigger: "change",
            message: "请选择上级菜单",
        },
    ],
    title: [
        {
            required: true,
            trigger: "change",
            message: "请填写菜单名称",
        },
    ],
    code: [
        {
            required: true,
            trigger: "change",
            message: "请填写权限字段",
        },
    ],
    name: [
        {
            required: true,
            trigger: "change",
            message: "请填写路由名称",
        },
    ],
    path: [
        {
            required: true,
            trigger: "change",
            message: "请填写路由地址",
        },
    ],
    url: [
        {
            required: true,
            trigger: "change",
            message: "请填写组件路径",
        },
    ],
    type: [
        {
            required: true,
            trigger: "change",
            message: "请选择菜单类型",
        },
    ],
});

/**
 * 定义组件事件
 */
const emits = defineEmits(["refresh"]);

/**
 * 表单提交处理
 */
const commit = () => {
    addFormRef.value?.validate(async (valid) => {
        if (valid) {
            let res = null;
            // 根据编辑类型调用不同的API
            if (addModel.editType == EditType.ADD) {
                res = await addApi(addModel);
            } else {
                res = await editApi(addModel);
            }
            if (res && res.code == 200) {
                ElMessage.success(res.msg);
                // 通知父组件刷新表格
                emits("refresh");
                onClose();
            }
        }
    });
};

/**
 * 打开上级菜单选择对话框
 */
const selectOpen = () => {
    parentRef.value?.showParent();
};

/**
 * 上级菜单选择回调
 * @param node 选中的菜单节点
 */
const selectParent = (node: SelectNode) => {
    addModel.parentId = node.parentId;
    addModel.parentName = node.parentName;
};
</script>

<style scoped></style>