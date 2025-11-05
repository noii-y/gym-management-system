<!--
  系统登录页面
  提供用户登录功能，支持会员和员工两种用户类型
  包含账号、密码、验证码输入和用户类型选择
-->
<template>
  <div class="logincontainer">
    <el-form 
      class="loginForm" 
      :model="loginModel" 
      ref="loginForm" 
      :rules="rules" 
      size="default"
    >
      <!-- 登录标题 -->
      <el-form-item style="display: flex; justify-content: center">
        <div class="loginTitle">系统登录</div>
      </el-form-item>
      
      <!-- 用户名输入 -->
      <el-form-item prop="username">
        <el-input 
          size="large" 
          v-model="loginModel.username" 
          placeholder="请输入账号"
        ></el-input>
      </el-form-item>
      
      <!-- 密码输入 -->
      <el-form-item prop="password">
        <el-input 
          size="large" 
          v-model="loginModel.password" 
          placeholder="请输入密码" 
          type="password"
        ></el-input>
      </el-form-item>
      
      <!-- 验证码输入和图片 -->
      <el-form-item prop="code">
        <el-row :gutter="20">
          <el-col :span="16" :offset="0">
            <el-input 
              size="large" 
              v-model="loginModel.code" 
              placeholder="请输入验证码"
            ></el-input>
          </el-col>
          <el-col :span="8" :offset="0">
            <img :src="imgSrc" class="image" @click="getImage" />
          </el-col>
        </el-row>
      </el-form-item>
      
      <!-- 用户类型选择 -->
      <el-form-item label="用户类型">
        <el-radio-group v-model="loginModel.userType">
          <el-radio :label="1">会员</el-radio>
          <el-radio :label="2">员工</el-radio>
        </el-radio-group>
      </el-form-item>
      
      <!-- 操作按钮 -->
      <el-form-item>
        <el-row style="width: 100%">
          <el-col :span="12" :offset="0" style="padding-right: 10px; padding-left: 0px">
            <el-button 
              style="width: 100%" 
              size="large" 
              type="primary" 
              @click="onSubmit"
            >
              登录
            </el-button>
          </el-col>
          <el-col :span="12" :offset="0" style="padding-right: 0px; padding-left: 10px">
            <el-button 
              type="danger" 
              plain 
              style="width: 100%" 
              size="large"
              @click="resetForm"
            >
              重置
            </el-button>
          </el-col>
        </el-row>
      </el-form-item>
    </el-form>
  </div>
</template>

<script setup lang="ts">
import { reactive, ref } from "vue";
import { ElMessage } from "element-plus";
import useImage from "@/composables/login/useImage";
import { loginApi } from "@/api/login/index";
import { useRouter } from "vue-router";
import { userStore } from "@/store/user";

// 初始化store和router
const store = userStore();
const router = useRouter();

// 使用验证码图片组合式函数
const { imgSrc, getImage } = useImage();

/**
 * 登录表单数据模型
 */
const loginModel = reactive({
  username: "",     // 用户名
  password: "",     // 密码
  code: "",         // 验证码
  userType: "1",    // 用户类型：1-会员，2-员工
});

/**
 * 表单验证规则
 */
const rules = reactive({
  username: [
    { required: true, message: "请输入账号", trigger: "blur" },
  ],
  password: [
    { required: true, message: "请输入密码", trigger: "blur" },
  ],
  code: [
    { required: true, message: "请输入验证码", trigger: "blur" },
  ],
});

// 表单引用
const loginForm = ref<any>(null);

/**
 * 处理登录提交
 */
const onSubmit = async () => {
  if (!loginForm.value) return;
  
  const valid = await loginForm.value.validate();
  if (valid) {
    const res = await loginApi(loginModel);
    if (res && res.code === 200) {
      // 保存token
      store.setToken(res.data.token);
      
      // 设置用户信息
      store.setUserInfo({
        userId: res.data.userId,
        username: res.data.username || loginModel.username,
        name: res.data.name || '',
        userType: res.data.userType || loginModel.userType.toString(),
        phone: res.data.phone || '',
        email: res.data.email || '',
        avatar: res.data.avatar || ''
      });
      
      // 跳转到首页
      router.push({ path: "/" });
    } else {
      ElMessage.error(res.msg || "登录失败");
    }
  }
};

/**
 * 重置表单
 */
const resetForm = () => {
  loginForm.value.resetFields();
};
</script>

<style scoped lang="scss">
/* 登录容器样式 */
.logincontainer {
  background-color: #fff;
  height: 100%;
  display: flex;
  justify-content: center;
  align-items: center;
  background-image: url("../../assets/WDU.jpg");
  background-size: 100% 100%;

  /* 登录表单样式 */
  .loginForm {
    border-radius: 12px;
    background-color: rgba(255, 255, 255, 0.7); /* 半透明白，透出背景 */
    backdrop-filter: blur(3px); /* 毛玻璃效果 */
    -webkit-backdrop-filter: blur(3px);
    box-shadow: 0 8px 24px rgba(0, 0, 0, 0.12); /* 柔和阴影提升层次 */
    width: 430px;
    height: 340px;
    padding: 20px 35px;

    /* 登录标题样式 */
    .loginTitle {
      width: 100%;
      font-size: 24px;
      font-weight: 600;
      display: flex;
      justify-content: center;
      align-items: center;
    }

    /* 验证码图片样式 */
    .image {
      height: 40px;
      width: 100%;
      display: flex;
      justify-content: center;
      align-items: center;
      cursor: pointer;
    }
  }
}
</style>