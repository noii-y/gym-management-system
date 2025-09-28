/**
 * 会员卡表格管理组合式函数
 * 提供会员卡列表的数据管理、分页、搜索等功能
 */

// 导入类型定义
import type { listParam } from "@/api/member_card/MemberModel";
// 导入Vue相关
import { nextTick, onMounted, reactive, ref } from "vue";
// 导入API函数
import { getListApi } from "@/api/member_card";

/**
 * 会员卡表格管理组合式函数
 * @returns 返回表格相关的状态和方法
 */
export default function useMemberTable() {
    /**
     * 表格高度
     */
    const tableHeight = ref(0)
    
    /**
     * 表格数据
     */
    const tableList = reactive({
        list: []
    })
    
    /**
     * 查询参数
     */
    const listParam = reactive<listParam>({
        title: '',          // 搜索标题
        pageSize: 10,       // 每页显示数量
        currentPage: 1,     // 当前页码
        total: 0           // 总记录数
    })
    
    /**
     * 获取会员卡列表数据
     */
    const getList = async () => {
        let res = await getListApi(listParam)
        if (res && res.code == 200) {
            console.log(res)
            tableList.list = res.data.records
            listParam.total = res.data.total
        }
    }
    
    /**
     * 搜索按钮点击事件
     */
    const searchBtn = () => {
        getList()
    }
    
    /**
     * 重置搜索条件
     */
    const resetBtn = () => {
        listParam.title = ''
        getList()
    }
    
    /**
     * 页容量改变时的处理函数
     * @param size 新的页容量
     */
    const sizeChange = (size: number) => {
        listParam.pageSize = size
        getList()
    }
    
    /**
     * 页码改变时的处理函数
     * @param page 新的页码
     */
    const currentChange = (page: number) => {
        listParam.currentPage = page
        getList()
    }
    
    /**
     * 刷新表格数据
     */
    const refresh = () => {
        getList()
    }
    /**
     * 组件挂载时的初始化操作
     */
    onMounted(() => {
        // 获取初始数据
        getList()
        // 计算表格高度
        nextTick(() => {
            tableHeight.value = window.innerHeight - 230
        })
    })
    
    /**
     * 返回组合式函数的公共接口
     */
    return {
        listParam,      // 查询参数
        getList,        // 获取列表数据
        searchBtn,      // 搜索方法
        resetBtn,       // 重置方法
        tableList,      // 表格数据
        sizeChange,     // 页容量改变处理
        currentChange,  // 页码改变处理
        tableHeight,    // 表格高度
        refresh         // 刷新方法
    }
}
