<!-- 角色新增/编辑弹框组件 -->
<template>
  <!-- 系统弹框组件，用于显示角色新增/编辑表单 -->
  <sys-dialog :title="dialog.title" :width="dialog.width" :height="dialog.height" :visible="dialog.visible"
    @onClose="onClose" @onConfirm="commit">
    <template v-slot:content>
      <!-- 角色信息表单 -->
      <el-form :model="addModel" ref="addFormRef" :rules="rules" label-width="80px" size="default">
        <!-- 角色名称输入框 -->
        <el-form-item prop="roleName" label="角色名称">
          <el-input v-model="addModel.roleName"></el-input>
        </el-form-item>
        <!-- 角色类型选择器 -->
        <el-form-item prop="roleName" label="角色类型">
          <el-select style="width: 100%" v-model="addModel.type" class="m-2" placeholder="请选择角色类型" size="default">
            <el-option v-for="item in options" :key="item.value" :label="item.label" :value="item.value" />
          </el-select>
        </el-form-item>
        <!-- 备注输入框 -->
        <el-form-item prop="remark" label="备注">
          <el-input v-model="addModel.remark"></el-input>
        </el-form-item>
      </el-form>
    </template>
  </sys-dialog>
</template>
/**
 * 角色新增/编辑弹框组件
 * 提供角色的新增和编辑功能，包含角色名称、类型和备注字段
 */
<script setup lang="ts">
import type { AddRoleModel } from "@/api/role/RoleModel";
import SysDialog from "@/components/SysDialog.vue";
import useDialog from "@/hooks/useDialog";
import { nextTick, reactive, ref } from "vue";
import { ElMessage } from "element-plus";
import type { FormInstance } from "element-plus";
import { addApi, editApi } from "@/api/role/index";
import { EditType, Title } from "@/type/BaseEnum";
import useInstance from "@/hooks/useInstance";

/** 全局实例，用于对象拷贝等工具方法 */
const { global } = useInstance();

/** 表单的ref引用，用于表单验证 */
const addFormRef = ref<FormInstance>();

/** 
 * 角色表单数据模型
 * @property {string} type - 操作类型（新增/编辑）
 * @property {string} roleId - 角色ID
 * @property {string} roleName - 角色名称
 * @property {string} remark - 角色备注
 */
const addModel = reactive<AddRoleModel>({
  type: "",
  roleId: "",
  roleName: "",
  remark: "",
  // types: "",
});

/** 角色类型选项列表 */
const options = ref([
  {
    value: "1",
    label: "员工类型",
  },
  {
    value: "2",
    label: "会员类型",
  },
]);

/** 弹框相关属性和方法 */
const { dialog, onClose, onConfirm } = useDialog();
/**
 * 显示弹框的方法，供父组件调用
 * @param {string} type - 操作类型（新增/编辑）
 * @param {AddRoleModel} row - 编辑时的角色数据（可选）
 */
const show = (type: string, row?: AddRoleModel) => {
  dialog.height = 150;
  dialog.width = 630;
  // 根据操作类型设置弹框标题
  type == EditType.ADD ? (dialog.title = Title.ADD) : (dialog.title = Title.EDIT);
  // 如果是编辑操作，需要回显数据到表单
  if (type == EditType.EDIT) {
    nextTick(() => {
      global.$objCoppy(row, addModel);
    });
  }
  // 设置操作类型标识
  addModel.type = type;
  dialog.visible = true;
  addFormRef.value?.resetFields();
};

/** 暴露show方法给父组件使用 */
defineExpose({
  show,
});

/** 表单验证规则配置 */
const rules = reactive({
  roleName: [
    {
      required: true,
      trigger: "change",
      message: "请填写角色名称",
    },
  ],
});

/** 定义组件事件，用于通知父组件刷新列表 */
const emit = defineEmits(["refresh"]);

/**
 * 提交表单数据
 * 根据操作类型调用新增或编辑API，成功后刷新父组件列表并关闭弹框
 */
const commit = () => {
  // 执行表单验证
  addFormRef.value?.validate(async (valid) => {
    if (valid) {
      let res = null;
      if (addModel.type == EditType.ADD) {
        // 调用新增API
        res = await addApi(addModel);
      } else {
        // 调用编辑API
        res = await editApi(addModel);
      }
      if (res && res.code == 200) {
        ElMessage.success(res.msg);
        // 通知父组件刷新列表
        emit("refresh");
        dialog.visible = false;
      }
    }
  });
};
</script>
<style scoped></style>
