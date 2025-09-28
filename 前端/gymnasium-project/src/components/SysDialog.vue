<!--
  系统通用对话框组件
  提供统一的对话框样式和交互逻辑
  支持自定义标题、尺寸和内容插槽
-->
<template>
  <el-dialog 
    :title="title" 
    :model-value="visible" 
    :before-close="onClose" 
    append-to-body 
    :width="width + 'px'" 
    :close-on-click-modal="false"
  >
    <!-- 对话框内容区域 -->
    <div class="container" :style="{ height: height + 'px' }">
      <slot name="content"></slot>
    </div>
    
    <!-- 对话框底部按钮区域 -->
    <template #footer>
      <span class="dialog-footer">
        <el-button type="danger" @click="onClose">取消</el-button>
        <el-button type="primary" @click="onConfirm">确定</el-button>
      </span>
    </template>
  </el-dialog>
</template>

<script setup lang="ts">
/**
 * 对话框组件属性接口
 */
interface DialogProps {
  title: string,    // 对话框标题
  visible: boolean, // 对话框显示状态
  width: number,    // 对话框宽度
  height: number    // 对话框高度
}

/**
 * 组件属性定义，包含默认值
 */
const props = withDefaults(defineProps<DialogProps>(), {
  title: '标题',
  visible: false,
  width: 630,
  height: 280
});

/**
 * 组件事件定义
 */
const emit = defineEmits(["onClose", "onConfirm"]);

/**
 * 处理对话框关闭事件
 */
const onClose = () => {
  emit("onClose");
};

/**
 * 处理对话框确定事件
 */
const onConfirm = () => {
  emit("onConfirm");
};
</script>

<style lang="scss" scoped>
/* 内容容器样式 */
.container {
  overflow-x: initial;
  overflow-y: auto;
}

/* 对话框整体样式 */
.el-dialog {
  border-top-left-radius: 7px !important;
  border-top-right-radius: 7px !important;

  /* 对话框头部样式 */
  .el-dialog__header {
    margin-right: 0px;
    border-top-left-radius: 7px !important;
    border-top-right-radius: 7px !important;
    background-color: #009688 !important;

    /* 标题样式 */
    .el-dialog__title {
      color: #fff;
      font-size: 16px;
      font-weight: 600;
    }
  }

  /* 关闭按钮样式 */
  .el-dialog__headerbtn {
    .el-dialog__close {
      color: #fff;
    }
  }

  /* 对话框主体样式 */
  .el-dialog__body {
    padding: 10px;
  }

  /* 对话框底部样式 */
  .el-dialog__footer {
    border-top: 1px solid #e8eaec !important;
    padding: 10px;
  }
}
</style>