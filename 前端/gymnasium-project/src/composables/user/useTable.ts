/**
 * 用户表格管理组合式函数
 * 提供用户列表的查询、分页、搜索、重置等功能
 */
import type { listParam } from "@/api/user/UserModel";
import { nextTick, onMounted, reactive, ref } from "vue";
import { getListApi } from "@/api/user";

export default function useTable() {
    // 表格高度（动态计算）
    const tableHeight = ref(0)
    
    // 表格数据
    const tableList = reactive({
        list: []
    })
    
    // 查询参数
    const listParam = reactive<listParam>({
        phone: '',
        pageSize: 10,
        currentPage: 1,
        nickName: '',
        total: 0
    })
    
    /**
     * 获取用户列表数据
     */
    const getList = async () => {
        let res = await getListApi(listParam)
        if (res && res.code == 200) {
            // 设置表格的数据
            console.log(res.data)
            tableList.list = res.data.records
            // 设置分页总条数
            listParam.total = res.data.total
        }
    }
    
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
        listParam.nickName = ''
        listParam.phone = ''
        getList()
    }
    
    /**
     * 页容量改变时触发
     * @param size 新的页容量
     */
    const sizeChange = (size: number) => {
        listParam.pageSize = size;
        getList()
    }
    
    /**
     * 页数改变时触发
     * @param page 新的页码
     */
    const currentChange = (page: number) => {
        listParam.currentPage = page;
        getList()
    }
    
    /**
     * 刷新列表数据
     */
    const refresh = () => {
        getList()
    }
    
    // 组件挂载时初始化
    onMounted(() => {
        getList()
        nextTick(() => {
            // 动态计算表格高度
            tableHeight.value = window.innerHeight - 230
        })
    })
    
    return {
        tableList,      // 表格数据
        listParam,      // 查询参数
        getList,        // 获取列表方法
        searchBtn,      // 搜索方法
        resetBtn,       // 重置方法
        sizeChange,     // 页容量变化方法
        currentChange,  // 页码变化方法
        tableHeight,    // 表格高度
        refresh         // 刷新方法
    }
}