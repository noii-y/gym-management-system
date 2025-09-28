<!-- 角色权限分配对话框组件 -->
<!-- 为指定角色分配菜单权限，使用树形结构展示权限菜单 -->
<template>
  <!-- 系统对话框，用于显示权限分配界面 -->
  <SysDialog :title="dialog.title" :width="dialog.width" :height="dialog.height" :visible="dialog.visible"
    @onClose="onClose" @onConfirm="commit">
    <template v-slot:content>
      <!-- 权限菜单树，支持多选和默认展开 -->
      <el-tree ref="assignTree" :data="assignTreeData.list" node-key="menuId" :props="defaultProps" empty-text="暂无数据"
        :show-checkbox="true" default-expand-all :highlight-current="true"
        :default-checked-keys="assignTreeData.assignTreeChecked"></el-tree>
    </template>
  </SysDialog>
</template>

<script setup lang="ts">
/**
 * 角色权限分配对话框组件
 * 提供为角色分配菜单权限的功能
 */
import SysDialog from "@/components/SysDialog.vue";
import useDialog from "@/hooks/useDialog";
import useAssign from "@/composables/role/useAssign";
import { userStore } from "@/store/user";
import { reactive, ref } from "vue";
import { ElMessage, ElTree } from "element-plus";
import { saveRoleMenuApi } from "@/api/role/index";
import { type SaveAssignParam } from "@/api/role/RoleModel";

/**
 * 权限树组件引用
 */
const assignTree = ref<InstanceType<typeof ElTree>>();

/**
 * 用户状态管理
 */
const store = userStore();

/**
 * 保存权限分配的参数
 */
const saveParam = reactive<SaveAssignParam>({
  roleId: "",  // 角色ID
  list: [],    // 权限菜单ID列表
});

/**
 * 权限树相关功能
 */
const { assignTreeData, getMenuTree, defaultProps } = useAssign();

/**
 * 对话框相关属性和方法
 */
const { dialog, onClose, onConfirm, onShow } = useDialog();

/**
 * 显示权限分配对话框
 * @param roleId 角色ID
 * @param name 角色名称
 */
const show = (roleId: string, name: string) => {
  saveParam.roleId = roleId;
  const param = {
    roleId: roleId,
    userId: store.getUserId,
  };
  // 获取权限菜单树数据
  getMenuTree(param);

  // 设置对话框属性
  dialog.title = `为【${name}】分配权限`;
  dialog.width = 300;
  dialog.height = 450;
  onShow();
};

/**
 * 暴露show方法供父组件调用
 */
defineExpose({
  show,
});

/**
 * 提交保存权限分配
 */
const commit = async () => {
  // 获取完全选中的菜单ID
  const checkIds = assignTree.value?.getCheckedKeys() as string[];
  // 获取半选中的菜单ID（父节点部分子节点被选中）
  const hlfIds = assignTree.value?.getHalfCheckedKeys() as string[];
  // 合并完全选中和半选中的菜单ID
  const list = checkIds.concat(hlfIds);

  if (list.length === 0) {
    ElMessage.warning("请勾选权限信息!");
    return;
  }

  saveParam.list = list;

  // 调用API保存权限分配
  const res = await saveRoleMenuApi(saveParam);
  if (res && res.code === 200) {
    ElMessage.success(res.msg);
    onClose();
  }
};
</script>

<style scoped></style>
