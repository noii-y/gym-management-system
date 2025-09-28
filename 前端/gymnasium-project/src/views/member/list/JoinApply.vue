<!-- 会员办卡对话框组件 -->
<!-- 为指定会员选择并办理会员卡类型 -->
<template>
    <!-- 系统对话框，用于显示办卡选择界面 -->
    <SysDialog :title="dialog.title" :height="dialog.height" :width="dialog.width" :visible="dialog.visible"
        @onClose="onClose" @onConfirm="commit">
        <template v-slot:content>
            <!-- 提示文字 -->
            <div>请选择会员卡类型</div>
            <!-- 分割线 -->
            <el-divider style="margin: 10px 0px" />
            <!-- 会员卡类型选择下拉框 -->
            <el-select style="margin-top: 10px; width: 100%" v-model="param.cardId" class="m-2" placeholder="请选择会员卡类型"
                size="large">
                <el-option v-for="item in cardList" :key="item.cardId" :label="item.title" :value="item.cardId" />
            </el-select>
        </template>
    </SysDialog>
</template>

<script setup lang="ts">
/**
 * 会员办卡对话框组件
 * 提供为会员选择并办理会员卡的功能
 */
import type { MemberType, ApplyCard } from
    "@/api/member/MemberModel";
import SysDialog from "@/components/SysDialog.vue";
import useDialog from "@/hooks/useDialog";
import useJoin from "@/composables/member/useJoin";
import { reactive } from "vue";
import { ElMessage } from "element-plus";
import { applySaveApi } from "@/api/member/index";

/**
 * 办卡参数对象
 * 包含选择的卡类型ID和会员ID
 */
const param = reactive<ApplyCard>({
    cardId: "",    // 选择的会员卡类型ID
    memberId: "",  // 会员ID
});

/**
 * 对话框相关属性和方法
 */
const { dialog, onClose, onConfirm, onShow } = useDialog();

/**
 * 会员卡类型数据
 */
const { cardList } = useJoin();

/**
 * 显示办卡对话框
 * @param row 会员信息对象
 */
const show = (row: MemberType) => {
    param.memberId = row.memberId;
    dialog.title = "为【" + row.name + "】办卡";
    onShow();
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
const eimits = defineEmits(['refresh'])

/**
 * 提交办卡申请
 * 验证选择的卡类型并调用API进行办卡操作
 */
const commit = async () => {
    if (!param.cardId) {
        ElMessage.warning("请选择会员卡类型!");
        return;
    }
    
    let res = await applySaveApi(param);
    if (res && res.code == 200) {
        ElMessage.success(res.msg);
        eimits('refresh')  // 通知父组件刷新数据
        onClose()          // 关闭对话框
    }
};
</script>
<style scoped></style>