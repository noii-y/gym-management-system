<!--
  商品新增/编辑对话框组件
  用于添加新商品或编辑现有商品信息
  包含商品名称、价格、单位、规格、库存、图片等字段的表单
-->
<template>
    <!-- 系统对话框，用于显示商品表单 -->
    <SysDialog :title="dialog.title" :width="dialog.width" :height="dialog.height" :visible="dialog.visible"
        @onClose="onClose" @onConfirm="commit">
        <template #content>
            <!-- 商品信息表单 -->
            <el-form ref="addFormRef" :model="addModel" :rules="rules" label-width="80px" size="default">
                <!-- 商品名称和价格行 -->
                <el-row>
                    <el-col :span="12">
                        <!-- 商品名称输入框 -->
                        <el-form-item prop="name" label="商品名称">
                            <el-input v-model="addModel.name" />
                        </el-form-item>
                    </el-col>
                    <el-col :span="12">
                        <!-- 商品价格输入框（数字类型） -->
                        <el-form-item prop="price" label="商品价格">
                            <el-input v-model.number="addModel.price" />
                        </el-form-item>
                    </el-col>
                </el-row>

                <!-- 单位和规格行 -->
                <el-row>
                    <el-col :span="12">
                        <!-- 商品单位输入框 -->
                        <el-form-item prop="unit" label="单位">
                            <el-input v-model="addModel.unit" />
                        </el-form-item>
                    </el-col>
                    <el-col :span="12">
                        <!-- 商品规格输入框 -->
                        <el-form-item prop="specs" label="规格">
                            <el-input v-model="addModel.specs" />
                        </el-form-item>
                    </el-col>
                </el-row>

                <!-- 库存行 -->
                <el-row>
                    <el-col :span="12">
                        <!-- 商品库存输入框（数字类型） -->
                        <el-form-item prop="store" label="库存">
                            <el-input v-model.number="addModel.store" />
                        </el-form-item>
                    </el-col>
                </el-row>

                <!-- 商品图片上传 -->
                <el-form-item prop="image" label="商品图片">
                    <!-- 图片上传组件，支持预览和删除 -->
                    <el-upload ref="uploadRef" action="#" list-type="picture-card" :auto-upload="false" :limit="1"
                        :file-list="fileList" :on-change="uploadFile">
                        <!-- 上传按钮图标 -->
                        <el-icon>
                            <Plus />
                        </el-icon>

                        <!-- 已上传文件的显示模板 -->
                        <template #file="{ file }">
                            <img class="el-upload-list__item-thumbnail" :src="file.url" alt="" />
                            <span class="el-upload-list__item-actions">
                                <!-- 预览按钮 -->
                                <span class="el-upload-list__item-preview" @click="handlePictureCardPreview(file)">
                                    <el-icon>
                                        <ZoomIn />
                                    </el-icon>
                                </span>
                                <!-- 删除按钮（非禁用状态下显示） -->
                                <span v-if="!disabled" class="el-upload-list__item-delete" @click="handleRemove(file)">
                                    <el-icon>
                                        <Delete />
                                    </el-icon>
                                </span>
                            </span>
                        </template>
                    </el-upload>

                    <!-- 图片预览对话框 -->
                    <el-dialog v-model="dialogVisible" width="400px" :title="null" append-to-body>
                        <img class="w-full" :src="dialogImageUrl" alt="Preview Image" />
                    </el-dialog>
                </el-form-item>

                <!-- 商品详情富文本编辑器 -->
                <el-form-item prop="details" label="商品详情">
                    <div style="border: 1px solid #ccc">
                        <!-- 富文本编辑器工具栏 -->
                        <Toolbar :editor="editorRef" :defaultConfig="toolbarConfig" :mode="mode"
                            style="border-bottom: 1px solid #ccc" />
                        <!-- 富文本编辑器内容区域 -->
                        <Editor v-model="valueHtml" :defaultConfig="editorConfig" :mode="mode"
                            style="height: 300px; overflow-y: hidden" @onCreated="handleCreated" />
                    </div>
                </el-form-item>
            </el-form>
        </template>
    </SysDialog>
</template>

<script setup lang="ts">
/**
 * 商品新增/编辑对话框组件
 * 提供商品信息的添加和编辑功能，包含图片上传和富文本编辑
 */

// 导入富文本编辑器样式
import '@wangeditor/editor/dist/css/style.css'
import { nextTick, reactive, ref } from 'vue'
import { ElMessage, type FormInstance } from 'element-plus'
import { Plus, ZoomIn, Delete } from '@element-plus/icons-vue'
import { Editor, Toolbar } from '@wangeditor/editor-for-vue'
import SysDialog from '@/components/SysDialog.vue'
import useDialog from '@/hooks/useDialog'
import useUpload from '@/composables/course/useUpload'
import useEditor from '@/composables/course/useEditor'
import type { GoodsType } from '@/api/goods/GoodsModel'
import { addApi, editApi } from '@/api/goods/index'
import { EditType, Title } from '@/type/BaseEnum'
import useInstance from '@/hooks/useInstance'

/**
 * 图片上传相关功能
 * 包含上传组件引用、对话框状态、文件列表等
 */
const {
    uploadRef,          // 上传组件引用
    dialogImageUrl,     // 预览图片URL
    dialogVisible,      // 预览对话框显示状态
    disabled,           // 是否禁用上传
    handleRemove,       // 删除文件处理函数
    handlePictureCardPreview, // 图片预览处理函数
    fileList,           // 文件列表
    uploadFile,         // 文件上传处理函数
    imgurl,             // 图片URL
} = useUpload()

/**
 * 富文本编辑器相关功能
 * 包含编辑器实例、配置、内容等
 */
const {
    editorRef,          // 编辑器实例引用
    handleCreated,      // 编辑器创建完成回调
    mode,               // 编辑器模式
    toolbarConfig,      // 工具栏配置
    valueHtml,          // 编辑器HTML内容
    editorConfig,       // 编辑器配置
} = useEditor()

/**
 * 对话框相关属性和方法
 */
const { dialog, onClose, onShow } = useDialog()

/**
 * 全局实例工具
 */
const { global } = useInstance()

/**
 * 显示对话框函数
 * @param type 操作类型（新增/编辑）
 * @param row 编辑时的商品数据（可选）
 */
const show = (type: string, row?: GoodsType) => {
    // 清空文件列表
    fileList.value = []

    // 设置对话框标题和尺寸
    dialog.title = type === EditType.ADD ? Title.ADD : Title.EDIT
    dialog.width = 900
    dialog.height = 500

    // 清空上传组件
    const upload = uploadRef.value
    if (upload) upload.clearFiles()

    // 新增模式：清空编辑器内容
    if (type === EditType.ADD) {
        const editor = editorRef.value
        if (editor) editor.clear()
    }

    // 编辑模式：填充现有数据
    if (type === EditType.EDIT && row) {
        nextTick(() => {
            // 复制商品数据到表单模型
            global.$objCopy(row, addModel)

            // 处理商品图片
            if (row.image) {
                const img = { 
                    name: '', 
                    url: row.image,
                    status: 'success' as const,
                    uid: Date.now()
                }
                imgurl.value = row.image
                fileList.value.push(img)
            }

            // 设置商品详情内容
            if (row.details) valueHtml.value = row.details
        })
    }

    // 显示对话框并重置表单
    onShow()
    addFormRef.value?.resetFields()
    addModel.type = type
}

/**
 * 暴露给父组件的方法
 */
defineExpose({ show })

/**
 * 表单引用，用于表单验证
 */
const addFormRef = ref<FormInstance>()

/**
 * 商品表单数据模型
 */
const addModel = reactive<GoodsType>({
    type: '',       // 操作类型（新增/编辑）
    goodsId: '',    // 商品ID
    name: '',       // 商品名称
    image: '',      // 商品图片URL
    details: '',    // 商品详情
    unit: '',       // 商品单位
    specs: '',      // 商品规格
    price: 0,       // 商品价格
    store: 0,       // 商品库存
})

/**
 * 商品价格自定义验证函数
 * @param _ 验证规则（未使用）
 * @param value 输入值
 * @param cb 回调函数
 */
const validatePrice = (_: any, value: any, cb: any) => {
    if (!value || value <= 0) cb(new Error('请填写商品价格'))
    else cb()
}

/**
 * 商品库存自定义验证函数
 * @param _ 验证规则（未使用）
 * @param value 输入值
 * @param cb 回调函数
 */
const validateStore = (_: any, value: any, cb: any) => {
    if (!value || value <= 0) cb(new Error('请填写商品库存'))
    else cb()
}

/**
 * 表单验证规则
 */
const rules = reactive({
    name: [{ required: true, trigger: 'blur', message: '请输入商品名称' }],
    image: [{ required: true, trigger: 'blur', message: '请上传商品图片' }],
    unit: [{ required: true, trigger: 'blur', message: '请填写单位' }],
    details: [{ required: true, trigger: 'blur', message: '请填写商品详情' }],
    specs: [{ required: true, trigger: 'blur', message: '请填写商品规格' }],
    price: [{ required: true, validator: validatePrice, trigger: 'blur' }],
    store: [{ required: true, validator: validateStore, trigger: 'blur' }],
})

/**
 * 事件定义
 */
const emit = defineEmits<{
    reFresh: []  // 刷新列表事件
}>()

/**
 * 提交表单函数
 * 处理商品的新增和编辑操作
 */
const commit = () => {
    // 设置图片和详情内容
    addModel.image = imgurl.value
    addModel.details = valueHtml.value

    // 表单验证
    addFormRef.value?.validate(async (valid) => {
        if (!valid) return

        // 根据操作类型调用对应API
        const res =
            addModel.type === EditType.ADD
                ? await addApi(addModel)
                : await editApi(addModel)

        // 处理响应结果
        if (res?.code === 200) {
            ElMessage.success(res.msg)
            emit('reFresh')  // 触发刷新事件
            onClose()        // 关闭对话框
        }
    })
}
</script>

<style scoped>
.w-full {
    width: 100%;
}
</style>


<style scoped>
/* 让预览图片宽度自适应 */
.w-full {
    width: 100%;
}
</style>