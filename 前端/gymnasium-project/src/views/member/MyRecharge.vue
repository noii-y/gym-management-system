<!--
  我的充值页面
  仅展示当前登录会员的充值历史记录
  包含充值金额、时间、操作人等信息的分页展示
-->
<template>
    <!-- 充值记录表格 -->
    <el-table :height="tableHeight" :data="tableData.list" border stripe>
      <el-table-column label="会员卡号" prop="username"></el-table-column>
      <el-table-column label="会员姓名" prop="name"></el-table-column>
      <el-table-column label="充值金额" prop="money"></el-table-column>
      <el-table-column label="充值时间" prop="createTime">
        <template #default="{ row }">{{ formatTime(row.createTime) }}</template>
      </el-table-column>
      <el-table-column label="操作人" prop="createUser">
        <template #default="{ row }">{{ row.createUser || '-' }}</template>
      </el-table-column>
    </el-table>
    
    <!-- 分页组件 -->
    <el-pagination 
      @size-change="sizeChange" 
      @current-change="currentChange" 
      :current-page.sync="listParam.currentPage"
      :page-sizes="[10, 20, 40, 80, 100]" 
      :page-size="listParam.pageSize"
      layout="total, sizes, prev, pager, next, jumper" 
      :total="listParam.total" 
      background>
    </el-pagination>

</template>

<script setup lang="ts">
import type { MemberRecharge } from "@/api/member/MemberModel";
import { nextTick, onMounted, reactive, ref, onBeforeUnmount, onActivated } from "vue";
import { getMyRechargeApi } from "@/api/member";
import { userStore } from "@/store/user";
import { ElMessage } from "element-plus";

// 表格高度
const tableHeight = ref(0);

// 用户 store
const store = userStore();

// 查询参数（仅当前用户）
const listParam = reactive<MemberRecharge>({
  currentPage: 1,
  pageSize: 10,
  total: 0,
  memberId: "",
  userType: "",
});

// 表格数据
const tableData = reactive({
  list: [],
});

// 获取当前用户的充值记录
const getList = async () => {
  try {
    await waitForUserReady();
    listParam.memberId = store.getUserId;
    listParam.userType = store.getUserType;

    const res = await getMyRechargeApi(listParam);
    if (res && res.code == 200) {
      tableData.list = res.data.records;
      listParam.total = res.data.total;
    }
  } catch (e: any) {
    ElMessage.error(e?.message || "获取充值记录失败");
  }
};

const sizeChange = (size: number) => {
  listParam.pageSize = size;
  getList();
};

const currentChange = (page: number) => {
  listParam.currentPage = page;
  getList();
};

onMounted(() => {
  nextTick(() => {
    calcHeight();
  });
  window.addEventListener("resize", calcHeight);
  getList();
});

onActivated(() => {
  getList();
});

onBeforeUnmount(() => {
  window.removeEventListener("resize", calcHeight);
});

const calcHeight = () => {
  tableHeight.value = Math.max(300, window.innerHeight - 230);
};

// 等待用户信息就绪（token/userId），最多等待2秒
const waitForUserReady = async () => {
  const start = Date.now();
  return new Promise<void>((resolve) => {
    const timer = setInterval(() => {
      const ready = !!store.getToken;
      if (ready || Date.now() - start > 2000) {
        clearInterval(timer);
        resolve();
      }
    }, 100);
  });
};

// 统一时间格式化为：YYYY-MM-DD HH:mm:ss
const formatTime = (val: any) => {
  if (!val) return '';
  const d = new Date(val);
  const pad = (n: number) => (n < 10 ? `0${n}` : `${n}`);
  return `${d.getFullYear()}-${pad(d.getMonth() + 1)}-${pad(d.getDate())} ${pad(d.getHours())}:${pad(d.getMinutes())}:${pad(d.getSeconds())}`;
};
</script>

<style scoped></style>