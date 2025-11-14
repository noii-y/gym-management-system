<!-- 角色权限分配对话框组件 -->
<!-- 为指定角色分配菜单权限，使用树形结构展示权限菜单 -->
<template>
  <!-- 系统对话框，用于显示权限分配界面 -->
  <SysDialog :title="dialog.title" :width="dialog.width" :height="dialog.height" :visible="dialog.visible"
    @onClose="handleClose" @onConfirm="commit">
    <template v-slot:content>
      <!-- 权限菜单树，支持多选和默认展开 -->
      <el-tree ref="assignTree" :data="assignTreeData.list" node-key="menuId" :props="defaultProps" empty-text="暂无数据"
        :show-checkbox="true" default-expand-all :highlight-current="true"></el-tree>
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
import { menuStore } from "@/store/menu";
import router from "@/router";
import { reactive, ref, nextTick } from "vue";
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
const handleClose = () => {
  assignTree.value?.setCheckedKeys([])
  onClose()
}

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
  getMenuTree(param).then(() => {
    const typeMap = new Map<number, string>()
    const flatten = (arr: any[]) => {
      arr.forEach((it: any) => {
        if (it && typeof it.menuId !== 'undefined') {
          const mid = Number(it.menuId)
          typeMap.set(mid, String(it.type))
        }
        if (it && it.children && it.children.length > 0) flatten(it.children)
      })
    }
    flatten(assignTreeData.list || [])
    const checkedIds = (assignTreeData.assignTreeChecked || []).filter((id: number) => typeMap.get(Number(id)) !== '0')
    nextTick(() => {
      assignTree.value?.setCheckedKeys(checkedIds, false)
    })
  })

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
  // 仅使用“勾选的”菜单ID作为提交来源，避免半选页面被误保存
  const checkIds = assignTree.value?.getCheckedKeys() as string[];
  const list = checkIds;

  const typeMap = new Map<number, string>();
  const parentMap = new Map<number, number>();
  const flatten = (arr: any[]) => {
    arr.forEach((it: any) => {
      if (it && typeof it.menuId !== 'undefined') {
        const mid = Number(it.menuId)
        typeMap.set(mid, String(it.type));
        if (typeof it.parentId !== 'undefined') parentMap.set(mid, Number(it.parentId))
      }
      if (it && it.children && it.children.length > 0) flatten(it.children);
    })
  }
  flatten(assignTreeData.list || [])

  const selectedMenus = new Set<number>();
  checkIds.forEach((id: string) => {
    const n = Number(id)
    if (typeMap.get(n) === '1') selectedMenus.add(n)
  })

  const submitIds = checkIds
    .map((id: string) => Number(id))
    .filter((id: number) => {
      const t = typeMap.get(id)
      if (t === '1') return true
      if (t === '2') {
        const pid = parentMap.get(id)
        return pid !== undefined && selectedMenus.has(pid)
      }
      return false
    })
    .map((id: number) => String(id))

  if (submitIds.length === 0) {
    ElMessage.warning("请勾选页面或按钮权限，不要只选目录!");
    return;
  }

  if (checkIds.length === 0) {
    ElMessage.warning("请勾选权限信息!");
    return;
  }

  saveParam.list = submitIds;

  // 调用API保存权限分配
  const res = await saveRoleMenuApi(saveParam);
  if (res && res.code === 200) {
    ElMessage.success(res.msg);
    onClose();

    // 刷新菜单：清理持久化并重新拉取动态路由
    const mStore = menuStore();
    if (!router) {
      ElMessage.error("路由实例不可用，请刷新页面后重试");
      return;
    }
    try { localStorage.removeItem('menuStore') } catch (_) {}
    mStore.$reset();
    await mStore.getMenu(router, { userId: store.getUserId, userType: store.getUserType });
  }
};
</script>

<style scoped></style>
