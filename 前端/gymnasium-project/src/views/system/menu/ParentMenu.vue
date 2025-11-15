<!-- 上级菜单选择对话框组件 -->
<!-- 提供树形结构的菜单选择功能，用于选择上级菜单 -->
<template>
    <!-- 系统对话框，用于显示菜单树 -->
    <SysDialog :title="dialog.title" :height="dialog.height" :width="dialog.width" :visible="dialog.visible"
        @onClose="onClose" @onConfirm="confirm">
        <template v-slot:content>
            <!-- 菜单树组件，支持自定义节点样式和展开/收起 -->
            <el-tree ref="parentTree" :data="treeData.list" node-key="menuId" default-expand-all :props="defaultProps"
                :highlight-current="true" :expand-on-click-node="false" @node-click="handleNodeClick">
                <!-- 自定义树节点模板 -->
                <template #default="{ node, data }">
                    <div class="custom-tree-container">
                        <!-- 叶子节点图标（没有子级菜单） -->
                        <span v-if="!data.children || data.children.length == 0">
                            <DocumentRemove style="
                       width: 1.3em;
                       height: 1.3em;
                       margin-right: 5px;
                       color: #8c8c8c;
                     "></DocumentRemove>
                        </span> 
                        <!-- 父节点展开/收起图标（有子级菜单） -->
                        <span v-else @click.stop="openBtn(data)">
                            <component style="
                       width: 1.1em;
                       height: 1.1em;
                       margin-right: 5px;
                       color: #8c8c8c;
                     " :is="data.open ? Plus : Minus" />
                        </span>
                        <!-- 菜单名称 -->
                        <span>{{ node.label }}</span>
                    </div>
                </template>
            </el-tree>
        </template>
    </SysDialog>
</template>
<script setup lang="ts">
/**
 * 上级菜单选择对话框组件
 * 提供树形结构的菜单选择功能，用于选择上级菜单
 */
import { DocumentRemove, Plus, Minus } from '@element-plus/icons-vue'
import SysDialog from "@/components/SysDialog.vue";
import useDialog from "@/hooks/useDialog";
import { getParentApi } from "@/api/menu";
import { reactive, ref } from "vue";
import type { MenuType, SelectNode } from "@/api/menu/MenuModel";
import { ElMessage, ElTree } from "element-plus";

/**
 * 菜单树组件引用
 */
const parentTree = ref<InstanceType<typeof ElTree>>();

/**
 * 树组件属性配置
 */
const defaultProps = {
    children: "children",  // 子节点字段名
    label: "title",        // 显示文本字段名
};

/**
 * 上级菜单树数据
 */
const treeData = reactive({
    list: [],
});

/**
 * 当前选中的菜单节点数据
 */
const selectNode = reactive<SelectNode>({
    parentId: "",    // 选中的父菜单ID
    parentName: "",  // 选中的父菜单名称
});

/**
 * 对话框相关属性和方法
 */
const { dialog, onClose, onShow } = useDialog();

/**
 * 显示上级菜单选择对话框
 */
const showParent = async () => {
    // 查询菜单树数据
    await getParent();
    dialog.width = 300;
    dialog.height = 450;
    onShow();
};

/**
 * 暴露showParent方法供父组件调用
 */
defineExpose({
    showParent,
});

/**
 * 查询上级菜单数据
 */
const getParent = async () => {
    let res = await getParentApi();
    if (res && res.code == 200) {
        treeData.list = res.data;
    }
};

/**
 * 树节点点击事件处理
 * @param node 点击的菜单节点
 */
const handleNodeClick = (node: MenuType) => {
    selectNode.parentId = node.menuId;
    selectNode.parentName = node.title;
};

/**
 * 定义组件事件
 */
const emits = defineEmits(["selectParent"]);

/**
 * 对话框确认按钮处理
 */
const confirm = () => {
    emits("selectParent", selectNode);
    onClose()
};

/**
 * 树节点展开/收起按钮点击事件
 * @param data 菜单节点数据
 */
const openBtn = (data: MenuType) => {
    // 切换展开/收起状态
    data.open = !data.open;
    // 更新Element Plus树组件的展开状态
    if (parentTree.value && parentTree.value.store && parentTree.value.store.nodesMap && parentTree.value.store.nodesMap[data.menuId]) {
        parentTree.value.store.nodesMap[data.menuId].expanded =
            !data.open;
    }
};
</script>
<style lang="scss">
.el-tree {

    // 将每一行的设置为相对定位 方便后面before after 使用绝对定位来固定位置
    .el-tree-node {
        position: relative;
        padding-left: 10px;
    }

    // 子集像右偏移 给数线留出距离
    .el-tree-node__children {
        padding-left: 20px;
    }

    //这是竖线
    .el-tree-node :last-child:before {
        height: 40px;
    }

    .el-tree>.el-tree-node:before {
        border-left: none;
    }

    .el-tree>.el-tree-node:after {
        border-top: none;
    }

    //这自定义的线 的公共部分
    .el-tree-node:before,
    .el-tree-node:after {
        content: "";
        left: -4px;
        position: absolute;
        right: auto;
        border-width: 1px;
    }

    .tree :first-child .el-tree-node:before {
        border-left: none;
    }

    // 竖线
    .el-tree-node:before {
        border-left: 1px dotted #d9d9d9;
        bottom: 0px;
        height: 100%;
        top: -25px;
        width: 1px;
    }

    //横线
    .el-tree-node:after {
        border-top: 1px dotted #d9d9d9;
        height: 20px;
        top: 14px;
        width: 24px;
    }

    .el-tree-node__expand-icon.is-leaf {
        width: 8px;
    }

    //去掉elementui自带的展开按钮  一个向下的按钮,打开时向右
    .el-tree-node__content>.el-tree-node__expand-icon {
        display: none;
    }

    //每一行的高度
    .el-tree-node__content {
        line-height: 30px;
        height: 30px;
        padding-left: 10px !important;
    }
}

//去掉最上级的before  after 即是去电最上层的连接线
.el-tree>div {
    &::before {
        display: none;
    }

    &::after {
        display: none;
    }
}
</style>