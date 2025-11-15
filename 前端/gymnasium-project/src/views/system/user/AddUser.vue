<!-- 用户新增/编辑弹框组件 -->
<template>
    <!-- 系统弹框组件，用于显示用户新增/编辑表单 -->
    <SysDialog :title="dialog.title" :height="dialog.height" :width="dialog.width" :visible="dialog.visible"
        @onClose="onClose" @onConfirm="commit">
        <template v-slot:content>
            <!-- 用户信息表单 -->
            <el-form :model="addModel" ref="addFormRef" :rules="rules" label-width="80px" size="default">
                <!-- 第一行：账户 和 密码（密码仅新增显示） -->
                <el-row>
                    <el-col :span="12" :offset="0">
                        <el-form-item prop="username" label="账户">
                            <el-input v-model="addModel.username"></el-input>
                        </el-form-item>
                    </el-col>
                    <el-col v-if="addModel.type == EditType.ADD" :span="12" :offset="0">
                        <el-form-item prop="password" label="密码">
                            <el-input v-model="addModel.password"></el-input>
                        </el-form-item>
                    </el-col>
                </el-row>
                <!-- 第二行：类型（选择角色名称） 和 状态 -->
                <el-row>
                    <el-col :span="12" :offset="0">
                        <el-form-item prop="roleId" label="类型">
                            <el-select v-model="addModel.roleId" placeholder="请选择角色" style="width: 100%" @change="onRoleChange">
                                <el-option v-for="item in roleData.list" :key="item.value" :label="item.label" :value="item.value" />
                            </el-select>
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
                <el-row>
                    <el-col :span="12" :offset="0">
                        <el-form-item v-if="isCoach" prop="coachType" label="教练类型">
                            <el-select v-model="addModel.coachType" placeholder="请选择教练类型" style="width: 100%">
                                <el-option :label="'课程教练'" :value="'课程教练'" />
                                <el-option :label="'助教'" :value="'助教'" />
                            </el-select>
                        </el-form-item>
                    </el-col>
                </el-row>
                <!-- 第三行：性别 -->
                <el-row>
                    <el-col :span="12" :offset="0">
                        <el-form-item prop="sex" label="性别">
                            <el-radio-group v-model="addModel.sex">
                                <el-radio :label="'0'">男</el-radio>
                                <el-radio :label="'1'">女</el-radio>
                            </el-radio-group>
                        </el-form-item>
                    </el-col>
                </el-row>
                <!-- 第四行：姓名 和 电话 -->
                <el-row>
                    <el-col :span="12" :offset="0">
                        <el-form-item prop="nickName" label="姓名">
                            <el-input v-model="addModel.nickName"></el-input>
                        </el-form-item>
                    </el-col>
                    <el-col :span="12" :offset="0">
                        <el-form-item prop="phone" label="电话">
                            <el-input v-model="addModel.phone"></el-input>
                        </el-form-item>
                    </el-col>
                </el-row>
                <!-- 第四行：邮箱 和 薪水 -->
                <el-row>
                    <el-col :span="12" :offset="0">
                        <el-form-item prop="email" label="邮箱">
                            <el-input v-model="addModel.email"></el-input>
                        </el-form-item>
                    </el-col>
                    <el-col :span="12" :offset="0">
                        <el-form-item prop="salary" label="薪水">
                            <el-input v-model="addModel.salary"></el-input>
                        </el-form-item>
                    </el-col>
                </el-row>
                <!-- 角色字段移除：类型即为角色选择 -->
            </el-form>
        </template>
    </SysDialog>
    <SysDialog :title="'关联课程'" :height="courseDialog.height" :width="courseDialog.width" :visible="courseDialog.visible" @onClose="closeCourseDialog" @onConfirm="confirmCourseDialog">
        <template v-slot:content>
            <el-form :inline="true" :model="courseQuery">
                <el-form-item>
                    <el-input v-model="courseQuery.courseName" placeholder="课程名称" />
                </el-form-item>
                <el-form-item>
                    <el-input v-model="courseQuery.teacherName" placeholder="授课教师" />
                </el-form-item>
                <el-form-item>
                    <el-button @click="loadCourseList">搜索</el-button>
                    <el-button type="primary" @click="filterMine">仅显示本教练课程</el-button>
                    <el-button type="danger" plain @click="resetCourseQuery">重置</el-button>
                </el-form-item>
            </el-form>
            <el-table :data="courseTable.list" border stripe :height="380">
                <el-table-column prop="image" label="封面" width="90" align="center">
                    <template #default="scope">
                        <el-image style="width:60px;height:60px;border-radius:50%" :src="scope.row.image" />
                    </template>
                </el-table-column>
                <el-table-column prop="courseName" label="课程名称" />
                <el-table-column prop="courseHour" label="课时" />
                <el-table-column prop="coursePrice" label="价格" />
                <el-table-column prop="teacherName" label="授课教师" />
                <el-table-column label="操作" width="240" align="center">
                    <template #default="scope">
                        <el-button v-if="String(scope.row.teacherId) !== String(addModel.userId)" type="primary" @click="assignToCoach(scope.row)">设为本教练</el-button>
                        <el-button type="danger" @click="removeFromCoach(scope.row)" v-if="String(scope.row.teacherId)===String(addModel.userId)">移除</el-button>
                    </template>
                </el-table-column>
            </el-table>
            <el-pagination background layout="total, sizes, prev, pager, next, jumper" :current-page.sync="courseQuery.currentPage" :page-size="courseQuery.pageSize" :page-sizes="[10,20,40,80,100]" :total="courseQuery.total" @size-change="courseSizeChange" @current-change="coursePageChange" />
        </template>
    </SysDialog>
</template>
/**
 * 用户新增/编辑弹框组件
 * 提供用户的新增和编辑功能，包含完整的用户信息表单
 */
<script setup lang="ts">
import type { AddUserModel } from "@/api/user/UserModel";
import SysDialog from "@/components/SysDialog.vue";
import useDialog from "@/hooks/useDialog";
import { nextTick, reactive, ref, watch } from "vue";
import useSelectRole from "@/composables/user/useSelectRole";
import { ElMessage, type FormInstance } from "element-plus";
import { addApi, editApi } from "@/api/user/index";
import { listApi as courseListApi, editApi as courseEditApi, unassignTeacherApi } from "@/api/course/index";
import { EditType, Title, UserType } from "@/type/BaseEnum";
import useInstance from "@/hooks/useInstance";

/** 全局实例，用于对象拷贝等工具方法 */
const { global } = useInstance();

/** 表单的ref引用，用于表单验证 */
const addFormRef = ref<FormInstance>();

/** 角色相关数据和方法 */
const { roleData, listRole, roleId, getRole } = useSelectRole();

/** 弹框相关属性和方法 */
const { dialog, onClose, onShow } = useDialog();
/**
 * 显示弹框的方法，供父组件调用
 * @param {string} type - 操作类型（新增/编辑）
 * @param {AddUserModel} row - 编辑时的用户数据（可选）
 */
const show = async (type: string, row?: AddUserModel) => {
    roleId.value = "";
    addModel.roleId = "";
    dialog.height = 280;
    addModel.type = type;
    // 获取角色数据列表
    await listRole();
    await getRole(row!?.userId);
    // 根据操作类型设置弹框标题
    type == EditType.ADD
        ? (dialog.title = Title.ADD)
        : (dialog.title = Title.EDIT);
    if (type == EditType.EDIT && row) {
        // 编辑时回显数据到表单
            nextTick(() => {
                global.$objCoppy(row, addModel);
                addModel.roleId = roleId.value;
                onRoleChange(addModel.roleId);
            });
    }
    onShow();
    // 新增时重置表单验证状态；编辑时保留回显数据
    if (type == EditType.ADD) addFormRef.value?.resetFields();
};

/** 暴露show方法给父组件使用 */
defineExpose({
    show,
});

/** 
 * 用户表单数据模型
 * @property {string} userId - 用户ID
 * @property {string} type - 操作类型（新增/编辑）
 * @property {string} roleId - 角色ID
 * @property {string} username - 登录账户
 * @property {string} password - 登录密码
 * @property {string} phone - 电话号码
 * @property {string} email - 邮箱地址
 * @property {string} sex - 性别（0:男，1:女）
 * @property {string} userType - 用户类型（1:员工，2:教练）
 * @property {string} status - 状态（0:停用，1:启用）
 * @property {string} salary - 薪水
 * @property {string} nickName - 姓名
 */
const addModel = reactive<AddUserModel>({
    userId: "",
    type: "",
    roleId: "",
    username: "",
    password: "",
    phone: "",
    email: "",
    sex: "",
    status: "",
    salary: "",
    nickName: "",
    userType: "",
    coachType: "",
});
/** 表单验证规则配置 */
const rules = reactive({
    nickName: [
        {
            required: true,
            trigger: "change",
            message: "请输入姓名",
        },
    ],
    phone: [
        {
            required: true,
            trigger: "change",
            message: "请输入电话",
        },
    ],
    sex: [
        {
            required: true,
            trigger: "change",
            message: "请选择性别",
        },
    ],
    // 用户类型移除；类型即为角色选择
    status: [
        {
            required: true,
            trigger: "change",
            message: "请选择状态",
        },
    ],
    salary: [
        {
            required: true,
            trigger: "change",
            message: "请输入薪水",
        },
    ],
    username: [
        {
            required: true,
            trigger: "change",
            message: "请输入账户",
        },
    ],
    password: [
        {
            required: true,
            trigger: "change",
            message: "请输入密码",
        },
    ],
    // 角色在新增时不必填，编辑时需要；使用自定义校验器保证一致性
    roleId: [
        {
            validator: (_rule: any, value: string, callback: (e?: Error) => void) => {
                if (!value) callback(new Error("请选择角色")); else callback();
            },
            trigger: "change",
        },
    ],
    coachType: [
        {
            validator: (_rule: any, _value: string, callback: (e?: Error) => void) => {
                if (isCoach && !addModel.coachType) callback(new Error("请选择教练类型")); else callback();
            },
            trigger: "change",
        },
    ],
});

/** 定义组件事件，用于通知父组件刷新列表 */
const emits = defineEmits(["refresh"]);

/**
 * 提交表单数据
 * 根据操作类型调用新增或编辑API，成功后刷新父组件列表并关闭弹框
 */
const commit = () => {
    addFormRef.value?.validate(async (valid) => {
        if (valid) {
            let res = null;
            if (addModel.type == EditType.ADD) {
                // 调用新增用户API
                res = await addApi(addModel);
            } else {
                // 调用编辑用户API
                res = await editApi(addModel);
            }
            if (res && res.code == 200) {
                ElMessage.success(res.msg);
                // 通知父组件刷新用户列表
                emits("refresh");
                onClose();
            }
        }
    });
};
const isCoach = ref(false);
const onRoleChange = (val: string) => {
    const role = roleData.list.find((r: any) => String(r.value) === String(val));
    const label = role ? role.label : "";
    isCoach.value = label === "教练";
    addModel.userType = isCoach.value ? "2" : "1";
    if (!isCoach.value) {
        addModel.coachType = "";
    }
};

const courseDialog = reactive({ visible: false, width: 900, height: 560 })
const courseQuery = reactive({ courseName: '', teacherName: '', currentPage: 1, pageSize: 10, total: 0 })
const courseTable = reactive({ list: [] as any[] })
const loadCourseList = async () => {
    const res = await courseListApi(courseQuery as any)
    if (res && res.code === 200) {
        courseTable.list = res.data.records
        courseQuery.total = res.data.total
    }
}
const courseSizeChange = (size: number) => { courseQuery.pageSize = size; loadCourseList() }
const coursePageChange = (page: number) => { courseQuery.currentPage = page; loadCourseList() }
const filterMine = () => { courseQuery.teacherName = addModel.nickName; courseQuery.currentPage = 1; loadCourseList() }
const resetCourseQuery = () => { courseQuery.courseName = ''; courseQuery.teacherName = ''; courseQuery.currentPage = 1; loadCourseList() }
const assignToCoach = async (row: any) => {
    const payload = { ...row, teacherId: addModel.userId, teacherName: addModel.nickName }
    const res = await courseEditApi(payload)
    if (res && res.code === 200) { loadCourseList() }
}
const removeFromCoach = async (row: any) => {
    try {
        const r1 = await unassignTeacherApi(row.courseId)
        if (!(r1 && r1.code === 200)) throw new Error('fallback')
    } catch (_) {
        const payload = { ...row, teacherId: null as any, teacherName: null as any }
        const r2 = await courseEditApi(payload as any)
        if (!(r2 && r2.code === 200)) {
            ElMessage.error('移除失败')
            return
        }
    }
    await loadCourseList()
    const updated = courseTable.list.find((it: any) => String(it.courseId) === String(row.courseId))
    const cleared = !updated || (!updated.teacherName && !updated.teacherId)
    if (cleared) {
        ElMessage.success('移除成功')
    } else {
        ElMessage.error('移除失败')
    }
}
const closeCourseDialog = () => { courseDialog.visible = false }
const confirmCourseDialog = () => { courseDialog.visible = false }
watch(() => addModel.coachType, (val) => {
    if (val === '课程教练' && addModel.type === EditType.EDIT) {
        courseDialog.visible = true
        courseQuery.teacherName = ''
        courseQuery.currentPage = 1
        loadCourseList()
    }
})

// 移除类型联动过滤与自动同步逻辑，类型直接对应所选角色
</script>
<style scoped></style>