<template>
    <div class="greeting-card">
        <div class="greeting-header">
            <div class="greeting-text">
                <div class="greeting-title">{{ greeting.title }}</div>
                <div class="greeting-subtitle">{{ greeting.subtitle }}</div>
            </div>
        </div>
    </div>
        <!-- 数据统计 -->
        <el-row :gutter="20" type="flex" class="row-bg" justify="center" style="margin-bottom: 80px;margin-top: 30px;">
            <el-col :span="6">
                <div class="show-header" style="background: rgb(45, 183, 245)">
                    <div class="show-num">{{ total.memberCount }}</div>
                    <div class="bottom-text">会员总数</div>
                </div>
            </el-col>
            <el-col :span="6">
                <div class="show-header" style="background: rgb(237, 64, 20)">
                    <div class="show-num">{{ total.userCount }}</div>
                    <div class="bottom-text">员工总数</div>
                </div>
            </el-col>
            <el-col :span="6">
                <div class="show-header">
                    <div class="show-num">{{ total.materCount }}</div>
                    <div class="bottom-text">器材总数</div>
                </div>
            </el-col>
            <el-col :span="6">
                <div class="show-header" style="background: rgb(255, 153, 0)">
                    <div class="show-num">{{ total.orderCount }}</div>
                    <div class="bottom-text">昨日订单</div>
                </div>
            </el-col>
        </el-row>
        <div style="display: flex">
            <el-card style="flex: 1">
                <template #header>
                    <div class="card-header">
                        <span>热销商品</span>
                    </div>
                </template>
                <div ref="myChart" :style="{ width: '400px', height: '250px' }"></div>
            </el-card>
            <el-card style="margin-left: 20px; flex: 1">
                <template #header>
                    <div class="card-header">
                        <span>热销卡</span>
                    </div>
                </template>
                <div ref="myChart1" :style="{ width: '400px', height: '250px' }"></div>
            </el-card>
            <el-card style="margin-left: 20px; flex: 1">
                <template #header>
                    <div class="card-header">
                        <span>热销课程</span>
                    </div>
                </template>
                <div ref="myChart2" :style="{ width: '400px', height: '250px' }"></div>
            </el-card>
        </div>
        <el-card class="box-card" style="margin-top: 30px">
            <template #header>
                <span style="color: #000000; font-weight: 600; margin-bottom: 10px">反馈列表</span>
                <el-divider></el-divider>
            </template>
            <div v-for="item in suggestList" :key="item.id" class="text item">
                <span style="font-weight: 600; font-size: 14px">{{ item.title }}</span>
                <span style="margin-left: 30px; font-size: 14px">{{ item.content }}</span>
                <span style="margin-left: 30px; font-size: 14px">{{ item.dateTime }}</span>
                <el-divider></el-divider>
            </div>
        </el-card>

</template>

<script setup lang="ts">
import { getTotalApi, getSuggestListApi, getHotCardstApi, getHotCourseApi, getHotGoodstApi } from "@/api/home";
import { onMounted, reactive, nextTick, ref } from "vue";
import useInstance from "@/hooks/useInstance";
import { userStore } from "@/store/user";

const store = userStore();
const { global } = useInstance();
const myChart = ref<HTMLElement>();
const myChart1 = ref<HTMLElement>();
const myChart2 = ref<HTMLElement>();
const mianHeight = ref(0);

// 个性化问候
const greeting = reactive({
    title: "",
    subtitle: "开启您美好的一天",
});

// 性别判定集合：后端/表单可能返回字符串或数字，这里统一做兼容
const maleSet = ["0", "男", "male", "Male", "M", "m"];

// 根据时间返回不同的问候语
const getTimeState = () => {
    let timeNow = new Date();
    let hours = timeNow.getHours();
    if (hours >= 0 && hours <= 10) return `早上好`;
    if (hours > 10 && hours <= 14) return `中午好`;
    if (hours > 14 && hours <= 18) return `下午好`;
    if (hours > 18 && hours <= 24) return `晚上好`;
};

// 根据用户身份显示不同问候语
 const getUserIdentity = () => {
     const { userType, name, sex } = store.userInfo;
     let identity = "用户"; // 默认身份
     switch (userType) {
         case "1":
             identity = "会员";
             break;
         case "2":
             identity = "员工";
             break;
         case "3":
             identity = "教练";
             break;
         default:
             identity = "用户";
     }
     return { name, identity, sex };
 };

// 设置问候语
 const setGreeting = () => {
     const { name, identity, sex } = getUserIdentity();
     const timeState = getTimeState();
     if (identity === '会员') {
        const displayName = name && name.trim() ? name : (store.userInfo.username || '');
        greeting.title = displayName
          ? `${timeState}，${displayName}同学！`
          : `${timeState}，亲爱的同学！`;
     } else {
         const genderToken = String(sex ?? '').trim();
         const gender = maleSet.includes(genderToken) ? '先生' : '女士';
         const displayName = name && name.trim() ? name : (store.userInfo.username || '');
         greeting.title = displayName
           ? `${timeState}，${displayName}${gender}！`
           : `${timeState}，先生/女士！`;
     }
 };

// 安全初始化图表实例：节点未准备好时不抛错
const safeInitChart = (el: HTMLElement | undefined) => {
    return el ? global.$echarts.init(el) : null;
};

// 柱状图
const charts1 = async () => {
    try {
        const echartInstance = safeInitChart(myChart.value);
        if (!echartInstance) return;
        const option = reactive({
            xAxis: {
                type: "category",
                data: [],
                axisLabel: {
                    show: true,
                    interval: 0, // 使x轴文字显示全
                },
            },
            yAxis: { type: "value" },
            series: [ { data: [], type: "bar" } ],
        });

        const res = await getHotGoodstApi();
        if (res && res.code === 200 && res.data) {
            option.xAxis.data = res.data.names || [];
            option.series[0].data = res.data.values || [];
        }
        echartInstance.setOption(option);
    } catch (err) {
        // 保护性处理，避免单个图表失败影响页面
        console.error('渲染热销商品柱状图失败:', err);
    }
};

// 饼图
const charts2 = async () => {
    try {
        const chart = safeInitChart(myChart1.value);
        if (!chart) return;
        const option = reactive({
            title: { left: "center" },
            tooltip: { trigger: "item" },
            legend: {
                orient: "vertical",
                left: "5%",
                top: "middle",
                textStyle: { color: '#666', fontSize: 12 },
                itemGap: 20,
                icon: 'circle',
                itemWidth: 14,
                itemHeight: 14
            },
            series: [
                {
                    name: "Access From",
                    type: "pie",
                    radius: "50%",
                    data: [],
                    emphasis: {
                        itemStyle: {
                            shadowBlur: 10,
                            shadowOffsetX: 0,
                            shadowColor: "rgba(0, 0, 0, 0.5)",
                        },
                    },
                },
            ],
        });

        const res = await getHotCardstApi();
        if (res && res.code === 200) {
            option.series[0].data = res.data || [];
        }
        chart.setOption(option);
    } catch (err) {
        console.error('渲染热销卡饼图失败:', err);
    }
};

// 环图
const charts3 = async () => {
    try {
        const chart = safeInitChart(myChart2.value);
        if (!chart) return;
        const option = reactive({
            tooltip: { trigger: "item" },
            legend: {
                orient: "vertical",
                left: "5%",
                top: "middle",
                textStyle: { color: '#666', fontSize: 12 },
                itemGap: 20,
                icon: 'circle',
                itemWidth: 14,
                itemHeight: 14
            },
            series: [
                {
                    name: "Access From",
                    type: "pie",
                    radius: ["25%", "65%"],
                    center: ["65%", "50%"],
                    avoidLabelOverlap: false,
                    label: { show: false, position: "center" },
                    emphasis: { label: { show: true, fontSize: "40", fontWeight: "bold" } },
                    labelLine: { show: false },
                    data: [],
                },
            ],
        });

        const res = await getHotCourseApi();
        if (res && res.code === 200) {
            option.series[0].data = res.data || [];
        }
        chart.setOption(option);
    } catch (err) {
        console.error('渲染热销课程环图失败:', err);
    }
};

// 总数
const total = reactive({
    memberCount: 0,
    userCount: 0,
    materCount: 0,
    orderCount: 0,
});

// 获取总数
const getTotal = async () => {
    try {
        const res = await getTotalApi();
        if (res && res.code === 200 && res.data) {
            total.memberCount = res.data.memberCount ?? 0;
            total.userCount = res.data.userCount ?? 0;
            total.materCount = res.data.materCount ?? 0;
            total.orderCount = res.data.orderCount ?? 0;
        }
    } catch (err) {
        console.error('获取首页总数失败:', err);
    }
};

type Suggest = {
    id: string;
    title: string;
    content: string;
    dateTime: string;
};

const suggestList = ref<Suggest[]>([]);
// 反馈列表
const suggest = async () => {
    try {
        const res = await getSuggestListApi();
        if (res && res.code === 200) {
            suggestList.value = res.data || [];
        }
    } catch (err) {
        console.error('获取反馈列表失败:', err);
    }
};
onMounted(async () => {
    // 先从后端获取用户信息，再设置问候语
    await store.getInfo().catch(() => {});
    setGreeting();

    // 并发加载数据与图表，提升整体加载效率
    await Promise.all([
        getTotal(),
        suggest(),
        charts1(),
        charts2(),
        charts3(),
    ]);

    nextTick(() => {
        mianHeight.value = window.innerHeight - 90;
    });
});
</script>
<style lang="scss" scoped>
.bottom-text {
    bottom: 0;
    width: 100%;
    background: rgba(0, 0, 0, 0.1);
    height: 25px;
    line-height: 25px;
    text-align: center;
    position: absolute;
    font-weight: 600;
}

.show-header {
    background: #00c0ef;
    color: #fff;
    height: 80px;
    border-radius: 5px;
    position: relative;
}

.show-num {
    font-size: 38px;
    font-weight: 600;
    padding: 5px;
}
.greeting-card {
    background-image: url('@/assets/WDU.jpg');
    background-repeat: no-repeat;
    background-size: cover;
    background-color: #fff;
    border-radius: 10px;
    box-shadow: 0 4px 12px rgba(0, 0, 0, 0.08);
    padding: 25px;
    margin-bottom: 30px;
    transition: all 0.3s ease;
}

.greeting-card:hover {
    transform: translateY(-5px);
    box-shadow: 0 8px 20px rgba(0, 0, 0, 0.12);
}

.greeting-header {
    display: flex;
    align-items: center;
}

.greeting-text {
    margin-left: 20px;
}

.greeting-title {
    font-size: 26px;
    font-weight: bold;
    color: #ffffff;
    margin-bottom: 8px;
}

.greeting-subtitle {
    font-size: 16px;
    color: #f5f5f5;
}
</style>