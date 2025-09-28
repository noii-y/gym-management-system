<!--
  会员管理列表页面
  提供会员信息的查询、新增、编辑、删除、办卡、充值和重置密码功能
  支持按姓名、电话、会员卡号搜索，分页显示会员数据
-->
<template>
  <el-main>
    <!-- 搜索栏 -->
    <el-form :model="listParam" :inline="true" size="default">
      <el-form-item>
        <el-input v-model="listParam.name" placeholder="请输入姓名"></el-input>
      </el-form-item>
      <el-form-item>
        <el-input v-model="listParam.phone" placeholder="请输入电话"></el-input>
      </el-form-item>
      <el-form-item>
        <el-input v-model="listParam.username" placeholder="请输入会员卡号"></el-input>
      </el-form-item>
      <el-form-item>
        <el-button :icon="Search" @click="searchBtn">搜索</el-button>
        <el-button type="danger" plain :icon="Close" @click="resetBtn">重置</el-button>
        <el-button :icon="Plus" type="primary" @click="addBtn">新增</el-button>
      </el-form-item>
    </el-form>
    
    <!-- 会员数据表格 -->
    <el-table :height="tableHeight" :data="tableList.list" border stripe>
      <el-table-column prop="username" width="150" label="会员卡号"></el-table-column>
      <el-table-column prop="name" label="姓名"></el-table-column>
      <el-table-column prop="cardType" label="会员类型"></el-table-column>
      <el-table-column prop="price" label="会员价格"></el-table-column>
      <el-table-column prop="money" label="充值金额"></el-table-column>
      <el-table-column prop="endTime" width="150" label="到期时间"></el-table-column>
      <el-table-column prop="sex" label="性别">
        <template #default="scope">
          <el-tag v-if="scope.row.sex == '0'" type="success" size="default">男</el-tag>
          <el-tag v-if="scope.row.sex == '1'" type="danger" size="default">女</el-tag>
        </template>
      </el-table-column>
      <el-table-column prop="age" label="年龄"></el-table-column>
      <el-table-column prop="birthday" width="150" label="生日"></el-table-column>
      <el-table-column prop="joinTime" width="150" label="加入时间"></el-table-column>
      <el-table-column prop="height" label="身高"></el-table-column>
      <el-table-column prop="weight" label="体重"></el-table-column>
      <el-table-column prop="waist" label="腰围"></el-table-column>
      <el-table-column prop="status" label="状态">
        <template #default="scope">
          <el-tag v-if="scope.row.status == '1'" type="success" size="default">启用</el-tag>
          <el-tag v-if="scope.row.status == '0'" type="danger" size="default">停用</el-tag>
        </template>
      </el-table-column>
      <el-table-column label="操作" width="220" fixed="right" align="center">
        <template #default="scope">
          <el-button type="success" :icon="Edit" size="default" @click="joinBtn(scope.row)">办卡</el-button>
          <el-button type="primary">
            <el-dropdown>
              <span class="el-dropdown-link" style="color:#FFF">
                更多
                <el-icon class="el-icon--right">
                  <ArrowDown />
                </el-icon>
              </span>
              <template #dropdown>
                <el-dropdown-menu>
                  <el-dropdown-item :icon="ChatLineSquare" @click="rechargeBtn(scope.row)">充值</el-dropdown-item>
                  <el-dropdown-item :icon="Edit" @click="editBtn(scope.row)">编辑</el-dropdown-item>
                  <el-dropdown-item type="danger" :icon="Delete" @click="deleteBtn(scope.row)">删除</el-dropdown-item>
                  <el-dropdown-item :icon="Edit" @click="resetPasBtn(scope.row)">重置密码</el-dropdown-item>
                </el-dropdown-menu>
              </template>
            </el-dropdown>
          </el-button>
        </template>
      </el-table-column>
    </el-table>
    
    <!-- 分页组件 -->
    <el-pagination @size-change="sizeChange" @current-change="currentChange" :current-page.sync="listParam.currentPage"
      :page-sizes="[10, 20, 40, 80, 100]" :page-size="listParam.pageSize"
      layout="total, sizes, prev, pager, next, jumper" :total="listParam.total" background>
    </el-pagination>
    
    <!-- 新增、编辑会员弹框 -->
    <AddMember ref="addRef" @refresh="refresh"></AddMember>
    
    <!-- 办卡弹框 -->
    <JoinApply ref="joinRef" @refresh="refresh"></JoinApply>
    
    <!-- 充值弹框 -->
    <Recharge ref="rechargeRef" @refresh="refresh"></Recharge>
  </el-main>
</template>

<script setup lang="ts">
/**
 * 会员管理页面逻辑
 * 使用组合式API管理会员列表的查询、操作和状态
 */
import JoinApply from "./list/JoinApply.vue";
import AddMember from "./AddMember.vue";
import Recharge from "./list/Recharge.vue";
import { Plus, Edit, Delete, Search, Close, ChatLineSquare, ArrowDown } from
  "@element-plus/icons-vue";
import useTable from "@/composables/member/useTable";
import useMember from "@/composables/member/useMember";
import useJoin from "@/composables/member/useJoin";
import useRecharge from "@/composables/member/useRecharge";

// 表格相关功能：查询参数、数据获取、搜索、重置、分页等
const {
  listParam,
  getList,
  resetBtn,
  searchBtn,
  tableList,
  sizeChange,
  currentChange,
  tableHeight,
  refresh,
} = useTable();

// 会员操作功能：新增、编辑、删除、重置密码
const { addBtn, editBtn, deleteBtn, addRef, resetPasBtn } = useMember(getList);

// 办卡功能
const { joinRef, joinBtn } = useJoin();

// 充值功能
const { rechargeRef, rechargeBtn } = useRecharge();
</script>

<style scoped></style>