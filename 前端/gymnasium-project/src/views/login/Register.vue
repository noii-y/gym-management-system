<!--
  系统注册页面（按角色动态填写）
  先选择注册角色（管理人员/教练/会员），根据角色显示不同必填信息。
  会员提交至会员新增接口；管理人员/教练提交至用户新增接口。
-->
<template>
  <div class="register-container">
    <el-form class="registerForm" :model="formModel" ref="formRef" :rules="formRules" label-width="90px" size="default">
      <el-form-item style="display:flex;justify-content:center">
        <div class="title">注册</div>
      </el-form-item>

      <!-- 注册角色（仅保留此项，不显示系统管理员） -->
      <el-form-item label="注册角色">
        <el-radio-group v-model="registerRole" @change="onRoleChange">
          <el-radio label="staff">员工</el-radio>
          <el-radio label="coach">教练</el-radio>
          <el-radio label="member">会员</el-radio>
        </el-radio-group>
      </el-form-item>

      <!-- 会员表单字段：将会员卡号提前，并保持每行两列对称 -->
      <template v-if="registerRole === 'member'">
        <!-- 第1行：会员卡号 / 姓名 -->
        <el-row>
          <el-col :span="12">
            <el-form-item prop="username" label="会员卡号">
              <el-input v-model="memberModel.username" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item prop="name" label="姓名">
              <el-input v-model="memberModel.name" />
            </el-form-item>
          </el-col>
        </el-row>
        <!-- 第2行：电话 / 年龄 -->
        <el-row>
          <el-col :span="12">
            <el-form-item prop="phone" label="电话">
              <el-input v-model="memberModel.phone" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item prop="age" label="年龄">
              <el-input v-model="memberModel.age" />
            </el-form-item>
          </el-col>
        </el-row>
        <!-- 第3行：性别 / 状态（同一行保证对称） -->
        <el-row>
          <el-col :span="12">
            <el-form-item prop="sex" label="性别">
              <el-radio-group v-model="memberModel.sex">
                <el-radio :label="'0'">男</el-radio>
                <el-radio :label="'1'">女</el-radio>
              </el-radio-group>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item prop="status" label="状态">
              <el-radio-group v-model="memberModel.status">
                <el-radio :label="'1'">启用</el-radio>
                <el-radio :label="'0'">停用</el-radio>
              </el-radio-group>
            </el-form-item>
          </el-col>
        </el-row>
        <!-- 第4行：生日 / 加入时间 -->
        <el-row>
          <el-col :span="12">
            <el-form-item prop="birthday" label="生日">
              <el-date-picker v-model="memberModel.birthday" type="date" placeholder="请选择" value-format="YYYY-MM-DD" format="YYYY-MM-DD" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item prop="joinTime" label="加入时间">
              <el-date-picker v-model="memberModel.joinTime" type="date" placeholder="请选择" value-format="YYYY-MM-DD" format="YYYY-MM-DD" />
            </el-form-item>
          </el-col>
        </el-row>
        <!-- 第5行：身高 / 体重 / 腰围（三等分一行，更对称） -->
        <el-row>
          <el-col :span="8">
            <el-form-item prop="height" label="身高">
              <el-input v-model="memberModel.height" />
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item prop="weight" label="体重">
              <el-input v-model="memberModel.weight" />
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item prop="waist" label="腰围">
              <el-input v-model="memberModel.waist" />
            </el-form-item>
          </el-col>
        </el-row>
        <!-- 第6行：密码 / 确认密码 -->
        <el-row>
          <el-col :span="12">
            <el-form-item prop="password" label="密码">
              <el-input type="password" v-model="memberModel.password" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item prop="confirmPassword" label="确认密码">
              <el-input type="password" v-model="memberModel.confirmPassword" />
            </el-form-item>
          </el-col>
        </el-row>
      </template>

      <!-- 管理人员/教练表单字段：将账号提前 -->
      <template v-else>
        <!-- 第1行：账号 / 姓名 -->
        <el-row>
          <el-col :span="12">
            <el-form-item prop="username" label="账号">
              <el-input v-model="userModel.username" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item prop="nickName" label="姓名">
              <el-input v-model="userModel.nickName" />
            </el-form-item>
          </el-col>
        </el-row>
        <!-- 第2行：电话 / 邮箱（将邮箱上移） -->
        <el-row>
          <el-col :span="12">
            <el-form-item prop="phone" label="电话">
              <el-input v-model="userModel.phone" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item prop="email" label="邮箱">
              <el-input v-model="userModel.email" />
            </el-form-item>
          </el-col>
        </el-row>
        <!-- 第3行：性别 / 状态（同一行保证对称） -->
        <el-row>
          <el-col :span="12">
            <el-form-item prop="sex" label="性别">
              <el-radio-group v-model="userModel.sex">
                <el-radio :label="'0'">男</el-radio>
                <el-radio :label="'1'">女</el-radio>
              </el-radio-group>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item prop="status" label="状态">
              <el-radio-group v-model="userModel.status">
                <el-radio :label="'1'">启用</el-radio>
                <el-radio :label="'0'">停用</el-radio>
              </el-radio-group>
            </el-form-item>
          </el-col>
        </el-row>
        <!-- 第4行：密码 / 确认密码 -->
        <el-row>
          <el-col :span="12">
            <el-form-item prop="password" label="密码">
              <el-input type="password" v-model="userModel.password" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item prop="confirmPassword" label="确认密码">
              <el-input type="password" v-model="userModel.confirmPassword" />
            </el-form-item>
          </el-col>
        </el-row>
      </template>

      <el-form-item>
        <el-row style="width: 100%">
          <el-col :span="12" style="padding-right: 10px">
            <el-button type="primary" style="width: 100%" size="large" @click="submit">立即注册</el-button>
          </el-col>
          <el-col :span="12" style="padding-left: 10px">
            <el-button style="width: 100%" size="large" plain @click="backLogin">返回登录</el-button>
          </el-col>
        </el-row>
      </el-form-item>
    </el-form>
  </div>
</template>

<script setup lang="ts">
import { reactive, ref, onMounted, computed } from 'vue'
import { useRouter } from 'vue-router'
import { ElMessage, type FormInstance } from 'element-plus'
import { addApi as addMemberApi } from '@/api/member/index'
import type { MemberType } from '@/api/member/MemberModel'
import useSelectRole from '@/composables/user/useSelectRole'
import { EditType } from '@/type/BaseEnum'
import { addApi as addUserApi } from '@/api/user/index'
import type { AddUserModel } from '@/api/user/UserModel'

const router = useRouter()
const formRef = ref<FormInstance>()

// 角色选择与角色列表
const { roleData, listRole } = useSelectRole()
const registerRole = ref<'member' | 'staff' | 'coach'>('member')

// 扩展模型类型以支持确认密码
type RegisterMemberModel = MemberType & { confirmPassword?: string }
type RegisterUserModel = AddUserModel & { confirmPassword?: string }

// 会员模型
const memberModel = reactive<RegisterMemberModel>({
  type: EditType.ADD,
  memberId: '',
  name: '',
  sex: '',
  phone: '',
  age: '',
  birthday: '',
  height: '',
  weight: '',
  waist: '',
  joinTime: '',
  endTime: '',
  username: '',
  password: '',
  confirmPassword: '',
  status: '1',
  roleId: ''
})

// 用户（管理人员/教练）模型
const userModel = reactive<RegisterUserModel>({
  userId: '',
  type: EditType.ADD,
  roleId: '',
  username: '',
  password: '',
  confirmPassword: '',
  phone: '',
  email: '',
  sex: '',
  userType: '',
  status: '1',
  salary: '',
  nickName: ''
})

// 根据角色切换当前模型与校验
const formModel = computed(() => registerRole.value === 'member' ? memberModel : userModel)
const formRules = computed(() => registerRole.value === 'member' ? memberRules : userRules)

// 会员规则
const memberRules = reactive({
  username: [{ required: true, trigger: 'change', message: '请填写会员卡号' }],
  name: [{ required: true, trigger: 'change', message: '请填写姓名' }],
  sex: [{ required: true, trigger: 'change', message: '请选择性别' }],
  phone: [{ required: true, trigger: 'change', message: '请填写电话' }],
  joinTime: [{ required: true, trigger: 'change', message: '请填写加入时间' }],
  password: [{ required: true, trigger: 'change', message: '请填写密码' }],
  confirmPassword: [
    { required: true, trigger: 'change', message: '请再次输入密码' },
    { validator: (_r: any, v: string, cb: any) => v !== memberModel.password ? cb(new Error('两次密码不一致')) : cb(), trigger: 'change' }
  ],
  status: [{ required: true, trigger: 'change', message: '请选择状态' }],
})

// 管理人员/教练规则（不需要身高、体重、腰围、会员卡号）
const userRules = reactive({
  nickName: [{ required: true, trigger: 'change', message: '请输入姓名' }],
  sex: [{ required: true, trigger: 'change', message: '请选择性别' }],
  phone: [{ required: true, trigger: 'change', message: '请输入电话' }],
  status: [{ required: true, trigger: 'change', message: '请选择状态' }],
  username: [{ required: true, trigger: 'change', message: '请输入账号' }],
  password: [{ required: true, trigger: 'change', message: '请输入密码' }],
  confirmPassword: [
    { required: true, trigger: 'change', message: '请再次输入密码' },
    { validator: (_r: any, v: string, cb: any) => v !== userModel.password ? cb(new Error('两次密码不一致')) : cb(), trigger: 'change' }
  ]
})

onMounted(async () => {
  await listRole()
  applyDefaultRoleId()
  // 会员加入时间自动设为当前日期
  memberModel.joinTime = formatDate(new Date())
})

// 角色变化时设置默认roleId与userType
const onRoleChange = () => {
  applyDefaultRoleId()
  if (registerRole.value === 'member') {
    memberModel.joinTime = formatDate(new Date())
  }
}

const applyDefaultRoleId = () => {
  // 工具：过滤掉系统管理员类角色
  const notSystemAdmin = (label: string) => !/系统管理员|管理员|admin/i.test(label)
  const findRole = (match: RegExp) => roleData.list.find((r: any) => match.test(r.label) && notSystemAdmin(r.label))

  if (registerRole.value === 'member') {
    const r = findRole(/会员|member/i)
    if (r) memberModel.roleId = r.value
  } else if (registerRole.value === 'coach') {
    const r = findRole(/教练|coach/i)
    if (r) userModel.roleId = r.value
    userModel.userType = '2'
  } else {
    const r = findRole(/员工|staff/i) || roleData.list.find((r: any) => notSystemAdmin(r.label) && !/会员|教练/i.test(r.label))
    if (r) userModel.roleId = r.value
    userModel.userType = '1'
  }
}

// 工具：格式化日期为 YYYY-MM-DD
const formatDate = (d: Date) => {
  const y = d.getFullYear()
  const m = String(d.getMonth() + 1).padStart(2, '0')
  const day = String(d.getDate()).padStart(2, '0')
  return `${y}-${m}-${day}`
}

const submit = () => {
  formRef.value?.validate(async (valid) => {
    if (!valid) return
    if (registerRole.value === 'member') {
      // 确保加入时间为当前日期
      memberModel.joinTime = memberModel.joinTime || formatDate(new Date())
      const res = await addMemberApi(memberModel)
      if (res && res.code === 200) {
        ElMessage.success(res.msg || '注册成功')
        router.push({ path: '/login' })
      } else {
        ElMessage.error(res?.msg || '会员注册失败')
      }
    } else {
      const res = await addUserApi(userModel)
      if (res && res.code === 200) {
        ElMessage.success(res.msg || '注册成功')
        router.push({ path: '/login' })
      } else {
        ElMessage.error(res?.msg || '用户注册失败')
      }
    }
  })
}

const backLogin = () => {
  router.push({ path: '/login' })
}
</script>

<style scoped lang="scss">
.register-container {
  background-color: #fff;
  height: 100%;
  display: flex;
  justify-content: center;
  align-items: center;
  background-image: url('../../assets/WDU.jpg');
  background-size: 100% 100%;

  .registerForm {
    border-radius: 12px;
    background-color: rgba(255, 255, 255, 0.7);
    backdrop-filter: blur(3px);
    -webkit-backdrop-filter: blur(3px);
    box-shadow: 0 8px 24px rgba(0, 0, 0, 0.12);
    width: 720px; /* 更宽以保证两列对称舒适 */
    min-height: 420px;
    padding: 20px 35px;
  }

  .title {
    width: 100%;
    font-size: 24px;
    font-weight: 600;
    display: flex;
    justify-content: center;
    align-items: center;
  }

}
</style>