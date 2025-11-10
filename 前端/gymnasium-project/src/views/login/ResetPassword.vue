<template>
  <div class="reset-container">
    <el-form class="resetForm" :model="model" ref="formRef" :rules="rules" size="default">
      <el-form-item style="display:flex;justify-content:center">
        <div class="resetTitle">忘记密码</div>
      </el-form-item>

      <el-form-item prop="userType" label="用户身份">
        <el-radio-group v-model="model.userType">
          <el-radio label="1">会员</el-radio>
          <el-radio label="2">员工</el-radio>
        </el-radio-group>
      </el-form-item>

      <el-form-item prop="memberId" label="账号/会员号">
        <el-input v-model="model.memberId" :placeholder="model.userType==='1' ? '请输入会员号或会员账号' : '请输入员工账号'" size="large" />
      </el-form-item>

      <el-form-item prop="phone" label="手机号">
        <el-input v-model="model.phone" placeholder="请输入手机号" size="large" />
      </el-form-item>

      <el-form-item prop="code" label="验证码">
        <el-row :gutter="20" style="width:100%">
          <el-col :span="16">
            <el-input v-model="model.code" placeholder="请输入验证码" size="large" />
          </el-col>
          <el-col :span="8">
            <img :src="imgSrc" class="image" @click="getImage" />
          </el-col>
        </el-row>
      </el-form-item>

      <el-form-item prop="newPassword" label="新密码">
        <el-input v-model="model.newPassword" type="password" placeholder="至少6位" size="large" />
      </el-form-item>

      <el-form-item prop="confirmPassword" label="确认密码">
        <el-input v-model="model.confirmPassword" type="password" placeholder="请再次输入" size="large" />
      </el-form-item>

      <el-form-item>
        <el-row style="width:100%">
          <el-col :span="12" style="padding-right:10px">
            <el-button style="width:100%" size="large" type="primary" @click="onSubmit">重置密码</el-button>
          </el-col>
          <el-col :span="12" style="padding-left:10px">
            <el-button style="width:100%" size="large" plain @click="goLogin">返回登录</el-button>
          </el-col>
        </el-row>
      </el-form-item>
    </el-form>
  </div>
  </template>

<script setup lang="ts">
import { reactive, ref } from 'vue'
import { ElMessage } from 'element-plus'
import useImage from '@/composables/login/useImage'
import { useRouter } from 'vue-router'
import { resetByContactApi } from '@/api/password'

const router = useRouter()
const { imgSrc, getImage } = useImage()

const model = reactive({
  userType: '1',
  memberId: '',
  phone: '',
  code: '',
  newPassword: '',
  confirmPassword: ''
})

const rules = reactive({
  userType: [{ required: true, message: '请选择用户身份', trigger: 'change' }],
  memberId: [{ required: true, message: '请输入账号或会员号', trigger: 'blur' }],
  phone: [{ required: true, message: '请输入手机号', trigger: 'blur' }],
  code: [{ required: true, message: '请输入验证码', trigger: 'blur' }],
  newPassword: [
    { required: true, message: '请输入新密码', trigger: 'blur' },
    { min: 6, message: '至少6位', trigger: 'blur' }
  ],
  confirmPassword: [{ required: true, message: '请再次输入', trigger: 'blur' }]
})

const formRef = ref<any>(null)

const onSubmit = async () => {
  if (!formRef.value) return
  const valid = await formRef.value.validate()
  if (!valid) return

  if (model.newPassword !== model.confirmPassword) {
    ElMessage.error('两次密码不一致')
    return
  }

  const res = await resetByContactApi(model)
  if (res && res.code === 200) {
    ElMessage.success('密码重置成功，请使用新密码登录')
    router.push({ path: '/login' })
  } else {
    ElMessage.error(res?.msg || '重置失败')
  }
}

const goLogin = () => router.push({ path: '/login' })
</script>

<style scoped lang="scss">
.reset-container {
  background-color: #fff;
  height: 100%;
  display: flex;
  justify-content: center;
  align-items: center;
  background-image: url('../../assets/WDU.jpg');
  background-size: 100% 100%;

  .resetForm {
    border-radius: 12px;
    background-color: rgba(255, 255, 255, 0.7);
    backdrop-filter: blur(3px);
    -webkit-backdrop-filter: blur(3px);
    box-shadow: 0 8px 24px rgba(0, 0, 0, 0.12);
    width: 480px;
    min-height: 380px;
    padding: 20px 35px;
  }

  .resetTitle {
    width: 100%;
    font-size: 24px;
    font-weight: 600;
    display: flex;
    justify-content: center;
    align-items: center;
  }

  .image {
    height: 40px;
    width: 100%;
    display: flex;
    justify-content: center;
    align-items: center;
    cursor: pointer;
  }
}
</style>