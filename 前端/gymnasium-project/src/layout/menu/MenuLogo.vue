<template>
    <div class="logo">
        <img :src="MenuLogo" alt="WDU Logo"/>
        <span v-if="show" class="logo-title">{{ title }}</span>
    </div>
</template>

<script setup lang="ts">
// 直接使用图片路径，避免TypeScript类型检查问题
const MenuLogo = '/src/assets/WDU.png'
import { ref, watch } from "vue";

const title = ref("WDU健身房运营管理平台");

const props = defineProps({
    isCollapsed: {
        required: true,
        type: Boolean,
    },
});

const show = ref(true);

watch(
    () => props.isCollapsed,
    (collapsed: boolean) => {
        if (!collapsed) {
            setTimeout(() => {
                show.value = !collapsed;
            }, 300);
        } else {
            show.value = !collapsed;
        }
    }
);
</script>

<style lang="scss" scoped>
.logo {
    display: flex;
    width: 100%;
    height: 56px;
    line-height: 56px;
    background: #2b2f3a;
    text-align: center;
    cursor: pointer;
    align-items: center;
    
    img {
        width: 32px;
        height: 32px;
        margin-right: 12px;
        margin-left: 20px;
    }
    
    .logo-title {
        color: #fff;
        font-weight: 700;
        line-height: 56px;
        font-size: 18px;
        letter-spacing: 0.5px;
        font-family: "Segoe UI", system-ui, -apple-system, "PingFang SC", "Microsoft Yahei", sans-serif;
    }
}
</style>
   