<!-- 课程新增/编辑对话框组件 -->
<!-- 提供课程信息的新增和编辑功能，包含课程基本信息、图片上传、富文本编辑等 -->
<template>
    <!-- 系统对话框，用于显示课程表单 -->
    <SysDialog :title="dialog.title" :height="dialog.height" :width="dialog.width" :visible="dialog.visible"
        @onClose="onClose" @onConfirm="commit">
        <template v-slot:content>
            <!-- 课程信息表单 -->
            <el-form :model="addModel" ref="addFormRef" :rules="rules" label-width="80px" size="default">
                <!-- 第一行：课程名称和课时 -->
                <el-row>
                    <el-col :span="12" :offset="0">
                        <!-- 课程名称输入框 -->
                        <el-form-item prop="courseName" label="课程名称">
                            <el-input v-model="addModel.courseName"></el-input>
                        </el-form-item>
                    </el-col>
                    <el-col :span="12" :offset="0">
                        <!-- 课程课时输入框 -->
                        <el-form-item prop="courseHour" label="课程课时">
                            <el-input v-model="addModel.courseHour"></el-input>
                        </el-form-item>
                    </el-col>
                </el-row>
                <!-- 第二行：课程价格和教练选择 -->
                <el-row>
                    <el-col :span="12" :offset="0">
                        <!-- 课程价格输入框 -->
                        <el-form-item prop="coursePrice" label="课程价格">
                            <el-input v-model="addModel.coursePrice"></el-input>
                        </el-form-item>
                    </el-col>
                    <el-col :span="12" :offset="0">
                        <!-- 教练选择下拉框 -->
                        <el-form-item prop="teacherName" label="教练">
                            <el-select style="width: 100%" v-model="addModel.teacherName" class="m-2"
                                placeholder="请选择教练" size="default" @change="selectTeach">
                                <el-option v-for="item in teacherData.list" :key="item.label" :label="item.label"
                                    :value="{ value: item.value, label: item.label }" />
                            </el-select>
                        </el-form-item>
                    </el-col>
                </el-row>
                <!-- 课程图片上传 -->
                <el-form-item prop="image" label="课程图片">
                    <!-- 图片上传组件，支持预览和删除 -->
                    <el-upload ref="uploadRef" action="#" :on-change="uploadFile" list-type="picture-card"
                        :auto-upload="false" :file-list="fileList" :limit="1">
                        <!-- 上传按钮 -->
                        <el-icon>
                            <Plus />
                        </el-icon>
                        <!-- 已上传文件的显示模板 -->
                        <template #file="{ file }">
                            <div>
                                <img class="el-upload-list__item-thumbnail" :src="file.url" alt="" />
                                <span class="el-upload-list__item-actions">
                                    <!-- 预览按钮 -->
                                    <span class="el-upload-list__item-preview" @click="handlePictureCardPreview(file)">
                                        <el-icon><zoom-in /></el-icon>
                                    </span>
                                    <!-- 删除按钮 -->
                                    <span v-if="!disabled" class="el-upload-list__item-delete"
                                        @click="handleRemove(file)">
                                        <el-icon>
                                            <Delete />
                                        </el-icon>
                                    </span>
                                </span>
                            </div>
                        </template>
                    </el-upload>
                    <!-- 图片预览对话框 -->
                    <el-dialog v-model="dialogVisible">
                        <img w-full :src="dialogImageUrl" alt="Preview Image" />
                    </el-dialog>
                </el-form-item>
                <!-- 课程详情富文本编辑器 -->
                <el-form-item prop="courseDetails" label="课程详情">
                    <div style="border: 1px solid #ccc">
                        <!-- 富文本编辑器工具栏 -->
                        <Toolbar style="border-bottom: 1px solid #ccc" :editor="editorRef"
                            :defaultConfig="toolbarConfig" :mode="mode" />
                        <!-- 富文本编辑器内容区域 -->
                        <Editor style="height: 300px; overflow-y: hidden" v-model="valueHtml"
                            :defaultConfig="editorConfig" :mode="mode" @onCreated="handleCreated" />
                    </div>
                </el-form-item>
            </el-form>
        </template>
    </SysDialog>
</template>

<script setup lang="ts">
/**
 * 课程新增/编辑对话框组件
 * 提供课程信息的新增和编辑功能，包含课程基本信息、图片上传、富文本编辑等
 */
import "@wangeditor/editor/dist/css/style.css"; // 引入富文本编辑器样式
import { Editor, Toolbar } from "@wangeditor/editor-for-vue";
import { type CourseType } from "@/api/course/CourseModel";
import SysDialog from "@/components/SysDialog.vue";
import useDialog from "@/hooks/useDialog";
import { ElMessage, type FormInstance } from "element-plus";
import { nextTick, reactive, ref } from "vue";
import useUpload from "@/composables/course/useUpload";
import useEditor from "@/composables/course/useEditor";
import useSelectTeacher from "@/composables/course/useSelectTeacher";
import { EditType, Title } from "@/type/BaseEnum";
import { addApi, editApi } from "@/api/course/index";
import useInstance from "@/hooks/useInstance";

/**
 * 全局实例，用于对象拷贝等工具方法
 */
const { global } = useInstance();

/**
 * 表单引用，用于表单验证
 */
const addFormRef = ref<FormInstance>();

/**
 * 对话框相关属性和方法
 */
const { dialog, onClose, onShow, onConfirm } = useDialog();

/**
 * 教练选择相关功能
 */
const { teacherData, listTeacher } = useSelectTeacher();

/**
 * 图片上传相关功能
 */
const {
    dialogImageUrl,    // 预览图片URL
    dialogVisible,     // 预览对话框显示状态
    disabled,          // 是否禁用删除
    handleRemove,      // 删除图片处理
    handlePictureCardPreview, // 预览图片处理
    uploadFile,        // 文件上传处理
    uploadRef,         // 上传组件引用
    imgurl,           // 图片URL
    fileList,         // 文件列表
} = useUpload();

/**
 * 富文本编辑器相关功能
 */
const {
    editorRef,        // 编辑器引用
    handleCreated,    // 编辑器创建回调
    mode,            // 编辑器模式
    editorConfig,    // 编辑器配置
    valueHtml,       // 编辑器内容
    toolbarConfig,   // 工具栏配置
} = useEditor();

/**
 * 显示对话框
 * @param type 操作类型（新增/编辑）
 * @param row 编辑时的课程数据
 */
const show = async (type: string, row?: CourseType) => {
    // 清空图片数据
    fileList.value = [];

    // 获取教练数据列表
    await listTeacher();

    // 设置弹框属性
    type == EditType.ADD
        ? (dialog.title = Title.ADD)
        : (dialog.title = Title.EDIT);
    dialog.width = 900;
    dialog.height = 500;

    // 清空图片和文本编辑器
    const upload = uploadRef.value;
    if (upload) {
        upload.clearFiles();
    }
    addModel.image = "";
    if (type == EditType.ADD) {
        const editor = editorRef.value;
        if (editor) {
            editor.clear();
        }
    }
    addModel.courseDetails = "";

    // 编辑数据回显
    if (type == EditType.EDIT) {
        nextTick(() => {
            // 把要编辑的数据复制到表单绑定的对象
            global.$objCopy(row, addModel);

            // 文本编辑器的回显
            valueHtml.value = addModel.courseDetails;

            // 图片回显
            if (row?.image) {
                // 构建图片对象用于显示
                let img = {
                    name: "course-image",
                    url: addModel.image,
                    status: 'success' as const,
                    uid: Date.now()
                };
                imgurl.value = addModel.image;
                fileList.value.push(img);
            }
        });
    }

    if (row && row.courseDetails) {
        // 文本编辑器的回显
        valueHtml.value = row.courseDetails;
    }

    onShow();

    // 重置表单验证状态
    addFormRef.value?.resetFields();

    addModel.type = type;
};

/**
 * 暴露方法给父组件调用
 */
defineExpose({
    show,
});

/**
 * 课程表单数据模型
 */
const addModel = reactive<CourseType>({
    type: "",         // 操作类型：区分新增/编辑
    courseId: "",     // 课程ID
    courseName: "",   // 课程名称
    image: "",        // 课程图片URL
    teacherName: "",  // 教练姓名
    teacherId: "",    // 教练ID
    courseHour: 0,    // 课程课时
    courseDetails: "", // 课程详情
    coursePrice: 0,   // 课程价格
});

/**
 * 课程课时验证函数
 * @param rule 验证规则
 * @param value 输入值
 * @param callback 回调函数
 */
const validateCourseHour = (rule: any, value: any, callback: any) => {
    if (value === 0 || value < 0) {
        callback(new Error("请填写课程课时"));
    } else {
        callback();
    }
};

/**
 * 课程价格验证函数
 * @param rule 验证规则
 * @param value 输入值
 * @param callback 回调函数
 */
const validateCoursePrice = (rule: any, value: any, callback: any) => {
    if (value === 0 || value < 0) {
        callback(new Error("请填写课程价格"));
    } else {
        callback();
    }
};

/**
 * 表单验证规则
 */
const rules = reactive({
    courseName: [
        {
            required: true,
            trigger: "blur",
            message: "请输入课程名称",
        },
    ],
    image: [
        {
            required: true,
            trigger: "blur",
            message: "请上传课程图片",
        },
    ],
    teacherName: [
        {
            required: true,
            trigger: "blur",
            message: "请选择课程教练",
        },
    ],
    courseDetails: [
        {
            required: true,
            trigger: "blur",
            message: "请填写课程详情",
        },
    ],
    coursePrice: [
        {
            required: true,
            validator: validateCoursePrice,
            trigger: "blur",
        },
    ],
    courseHour: [
        {
            required: true,
            validator: validateCourseHour,
            trigger: "blur",
        },
    ],
});

/**
 * 定义组件事件
 */
const emits = defineEmits(["reFresh"]);

/**
 * 提交表单
 * 验证表单并调用相应的API（新增或编辑）
 */
const commit = () => {
    // 设置封面图地址
    addModel.image = imgurl.value;

    // 设置课程详情
    addModel.courseDetails = valueHtml.value;

    addFormRef.value?.validate(async (valid) => {
        if (valid) {
            let res = null;
            if (addModel.type == EditType.ADD) {
                res = await addApi(addModel);
            } else {
                res = await editApi(addModel);
            }

            if (res && res.code == 200) {
                ElMessage.success(res.msg);
                emits("reFresh");  // 通知父组件刷新数据
                onClose();         // 关闭对话框
            }
        }
    });
};

/**
 * 教练选择变化处理
 * @param val 选择的教练对象
 */
const selectTeach = (val: any) => {
    addModel.teacherId = val.value;
    addModel.teacherName = val.label;
};
</script>

<style scoped></style>