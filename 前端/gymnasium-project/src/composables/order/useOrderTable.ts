/**
 * 订单表格管理组合式函数
 * 提供订单列表的查询、分页、搜索、重置等功能
 */
import { nextTick, onMounted, reactive, ref } from "vue";
import { type OrderListParam } from '@/api/order/OrderModel'
import { listApi } from "@/api/order";

export default function useOrderTable() {
    // 表格高度（动态计算）
    const orderTableHeight = ref(0)
    
    // 表格数据
    const tableData = reactive({
        list: []
    })
    
    // 列表查询的参数
    const listParam = reactive<OrderListParam>({
        name: '',
        currentPage: 1,
        pageSize: 20,
        total: 0,
        controlName: ''
    })
    
    /**
     * 搜索按钮处理
     */
    const searchBtn = () => {
        getList()
    }
    
    /**
     * 重置按钮处理
     * 清空搜索条件并重新获取数据
     */
    const resetBtn = () => {
        listParam.name = ''
        getList()
    }
    
    /**
     * 页容量改变时触发
     * @param size 新的页容量
     */
    const orderSizeChange = (size: number) => {
        listParam.pageSize = size;
        getList()
    }
    
    /**
     * 页数改变时触发
     * @param page 新的页码
     */
    const orderCurrentChange = (page: number) => {
        listParam.currentPage = page;
        getList()
    }
    
    /**
     * 获取订单列表数据
     */
    const getList = async () => {
        let res = await listApi(listParam)
        if (res && res.code == 200) {
            tableData.list = res.data.records;
            listParam.total = res.data.total;
        }
    }
    
    // 组件挂载时初始化
    onMounted(() => {
        getList()
        nextTick(() => {
            // 动态计算表格高度
            orderTableHeight.value = window.innerHeight - 230
        })
    })
    
    return {
        listParam,           // 查询参数
        searchBtn,           // 搜索方法
        resetBtn,            // 重置方法
        tableData,           // 表格数据
        getList,             // 获取列表方法
        orderSizeChange,     // 页容量变化方法
        orderCurrentChange,  // 页码变化方法
        orderTableHeight     // 表格高度
    }
}
