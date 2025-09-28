<template>
  <div>这是一个HelloWorld组件</div>
  <el-form-item>
    <el-button type="primary" native-type="submit" @click="addBtn">
      按钮1
    </el-button>
  </el-form-item>
  <h1>{{ store.count }}</h1>
</template>

<script setup lang="ts">
import { testStore } from '@/store/test/index'

const store = testStore();

const addBtn = () => {
  store.setCount(++store.count)
}
</script>

<style scoped>
</style>
  