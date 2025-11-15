<!--
  会员卡类型新增/编辑对话框组件
  用于添加新的会员卡类型或编辑现有的会员卡类型
  包含卡类型、标题、天数、价格、状态等字段的表单
-->
<template>
  <SysDialog 
    :title="dialog.title" 
    :width="dialog.width" 
    :height="dialog.height" 
    :visible="dialog.visible"
    @onClose="onClose" 
    @onConfirm="commit">
    <template v-slot:content>
      <!-- 会员卡类型表单 -->
      <el-form 
        :model="addModel" 
        ref="addFormRef" 
        :rules="rules" 
        label-width="80px" 
        size="default">
        <!-- 卡类型选择 -->
        <el-row>
          <el-col :span="24" :offset="0">
            <el-form-item prop="cardType" label="类型">
              <el-radio-group v-model="addModel.cardType">
                <el-radio :label="'1'">天卡</el-radio>
                <el-radio :label="'2'">周卡</el-radio>
                <el-radio :label="'3'">月卡</el-radio>
                <el-radio :label="'4'">年卡</el-radio>
              </el-radio-group>
            </el-form-item>
          </el-col>
        </el-row>
        
        <!-- 标题和天数 -->
        <el-row>
          <el-col :span="12" :offset="0">
            <el-form-item prop="title" label="标题">
              <el-input v-model="addModel.title"></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="12" :offset="0">
            <el-form-item prop="cardDay" label="天数">
              <el-input v-model="addModel.cardDay"></el-input>
            </el-form-item>
          </el-col>
        </el-row>
        
        <!-- 价格和状态 -->
        <el-row>
          <el-col :span="12" :offset="0">
            <el-form-item prop="price" label="价格">
              <el-input v-model="addModel.price"></el-input>
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
      </el-form>
    </template>
  </SysDialog>
</template>
<script setup lang="ts">
/**
 * 会员卡类型新增/编辑对话框组件
 * 提供会员卡类型的添加和编辑功能
 */
import SysDialog from "@/components/SysDialog.vue";
import useDialog from "@/hooks/useDialog";
import type { CardType } from "@/api/member_card/MemberModel";
import { nextTick, reactive, ref } from "vue";
import { addApi, editApi } from "@/api/member_card/index";
import { ElMessage } from "element-plus";
import type { FormInstance } from "element-plus";
import { EditType, Title } from "@/type/BaseEnum";
import useInstance from "@/hooks/useInstance";

/**
 * 全局实例工具
 */
const { global } = useInstance();

/**
 * 表单引用，用于表单验证
 */
const addFormRef = ref<FormInstance>();

/**
 * 对话框相关属性和方法
 */
const { dialog, onClose, onConfirm, onShow } = useDialog();

/**
 * 显示对话框
 * @param type 操作类型（新增/编辑）
 * @param row 编辑时的数据对象（可选）
 */
const show = (type: string, row?: CardType) => {
  dialog.height = 200;
  // 根据操作类型设置对话框标题
  type == EditType.ADD
    ? (dialog.title = Title.ADD)
    : (dialog.title = Title.EDIT);
    
  if (type == EditType.EDIT) {
    // 编辑模式：将要编辑的数据复制到表单对象
    nextTick(() => {
      global.$objCopy(row, addModel);
    });
  }
  addModel.type = type;
  onShow();
};

/**
 * 暴露方法给父组件调用
 */
defineExpose({
  show,
});

/**
 * 表单数据模型
 * 包含会员卡类型的所有字段
 */
const addModel = reactive<CardType>({
  type: "",        // 操作类型
  title: "",       // 卡类型标题
  cardType: "",    // 卡类型（1:天卡 2:周卡 3:月卡 4:年卡）
  cardId: "",      // 卡ID
  price: "",       // 价格
  cardDay: 0,      // 有效天数
  status: "",      // 状态（0:停用 1:启用）
});

/**
 * 表单验证规则
 * 定义各字段的验证要求
 */
const rules = reactive({
  title: [
    {
      required: true,
      message: "请填写标题",
      trigger: "change",
    },
  ],
  cardType: [
    {
      required: true,
      message: "请选择类型",
      trigger: "change",
    },
  ],
  price: [
    {
      required: true,
      message: "请填写价格",
      trigger: "change",
    },
  ],
  cardDay: [
    {
      required: true,
      message: "请填写天数",
      trigger: "change",
    },
  ],
  status: [
    {
      required: true,
      message: "请选择状态",
      trigger: "change",
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
  addFormRef.value?.validate(async (valid) => {
    if (valid) {
      let res = null;
      // 根据操作类型调用不同的API
      if (addModel.type == EditType.ADD) {
        res = await addApi(addModel);
      } else {
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