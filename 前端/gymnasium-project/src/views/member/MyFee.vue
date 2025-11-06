<!--
  我的充值记录页面
  显示当前登录会员的充值历史记录
  包含充值金额、时间、操作人等信息的分页展示
-->
<template>
<!-- 删除冗余 el-main 包裹，避免产生双滚动条 -->
    <!-- 充值记录表格 -->
    <el-table :height="tableHeight" :data="tableData.list" border stripe>
      <el-table-column label="会员卡号" prop="username"></el-table-column>
      <el-table-column label="会员姓名" prop="name"></el-table-column>
      <el-table-column label="充值金额" prop="money"></el-table-column>
      <el-table-column label="充值时间" prop="createTime"></el-table-column>
      <el-table-column label="操作人" prop="createUser"></el-table-column>
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
/**
 * 我的充值记录页面组件
 * 用于显示当前登录会员的充值历史记录
 */
import type { MemberRecharge } from "@/api/member/MemberModel";
import { nextTick, onMounted, reactive, ref, onBeforeUnmount, onActivated } from "vue";
import { getMyRechargeApi } from "@/api/member";
import { userStore } from "@/store/user";
import { ElMessage } from "element-plus";

/**
 * 表格高度，用于动态计算表格显示高度
 */
const tableHeight = ref(0);

/**
 * 用户状态管理实例
 */
const store = userStore();

/**
 * 表格查询参数
 * 包含分页信息和用户身份信息
 */
const listParam = reactive<MemberRecharge>({
  currentPage: 1,    // 当前页码
  pageSize: 10,      // 每页显示数量
  total: 0,          // 总记录数
  memberId: "",      // 会员ID
  userType: "",      // 用户类型
});

/**
 * 表格数据对象
 * 包含充值记录列表
 */
const tableData = reactive({
  list: [],
});

/**
 * 获取充值记录列表数据
 * 根据当前登录用户信息获取其充值历史记录
 */
const getList = async () => {
  try {
    await waitForUserReady();
    // 设置查询参数为当前登录用户信息
    listParam.memberId = store.getUserId;
    listParam.userType = store.getUserType;

    const res = await getMyRechargeApi(listParam);
    if (res && res.code == 200) {
      tableData.list = res.data.records;
      listParam.total = res.data.total;
    } else {
      // 失败重试一次，提升首屏稳定性
      const retry = await getMyRechargeApi(listParam);
      if (retry && retry.code == 200) {
        tableData.list = retry.data.records;
        listParam.total = retry.data.total;
      }
    }
  } catch (e: any) {
    ElMessage.error(e?.message || "获取充值记录失败");
  }
};

/**
 * 页容量改变时的处理函数
 * @param size 新的页容量
 */
const sizeChange = (size: number) => {
  listParam.pageSize = size;
  getList();
};

/**
 * 页数改变时的处理函数
 * @param page 新的页码
 */
const currentChange = (page: number) => {
  listParam.currentPage = page;
  getList();
};

/**
 * 组件挂载时的初始化操作
 * 计算表格高度并获取初始数据
 */
onMounted(() => {
  nextTick(() => {
    // 动态计算表格高度，减去头部和其他元素的高度
    calcHeight();
  });
  window.addEventListener("resize", calcHeight);
  // 获取初始充值记录数据
  getList();
});

onActivated(() => {
  // 从标签页或路由返回时，确保数据同步
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
</script>

<style scoped></style>