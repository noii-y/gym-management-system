/**
 * 课程表格管理组合式函数
 * 提供课程列表的数据管理、分页、搜索和重置功能
 */
import { nextTick, onMounted, reactive, ref } from "vue";
import type { CourselistParam } from '@/api/course/CourseModel'
import { listApi } from "@/api/course";

export default function useTable() {
    /**
     * 表格高度，用于动态计算表格显示高度
     */
    const tableHeight = ref(0)
    
    /**
     * 表格数据对象，包含课程列表
     */
    const tableDate = reactive({
        list: []
    })
    
    /**
     * 列表查询参数，包含搜索条件和分页信息
     */
    const listParam = reactive<CourselistParam>({
        courseName: '',    // 课程名称搜索条件
        teacherName: '',   // 教师名称搜索条件
        currentPage: 1,    // 当前页码
        pageSize: 10,      // 每页显示数量
        total: 0           // 总记录数
    })
    
    /**
     * 获取课程列表数据
     * 调用API获取课程数据并更新表格和分页信息
     */
    const getList = async () => {
        let res = await listApi(listParam)
        if (res && res.code == 200) {
            console.log(res)
            tableDate.list = res.data.records;
            listParam.total = res.data.total;
        }
    }
    
    /**
     * 页容量改变时的处理函数
     * @param size 新的页容量
     */
    const sizeChange = (size: number) => {
        listParam.pageSize = size;
        getList()
    }
    
    /**
     * 页数改变时的处理函数
     * @param page 新的页码
     */
    const currentChange = (page: number) => {
        listParam.currentPage = page;
        getList()
    }
    
    /**
     * 搜索按钮处理函数
     * 根据当前搜索条件重新获取数据
     */
    const searchBtn = () => {
        getList()
    }
    
    /**
     * 重置按钮处理函数
     * 清空搜索条件并重置分页参数，然后重新获取数据
     */
    const resetBtn = () => {
        listParam.courseName = ''
        listParam.teacherName = ''
        listParam.currentPage = 1;
        listParam.pageSize = 10
        getList()
    }
    
    /**
     * 刷新表格数据
     * 重新获取当前页的数据
     */
    const reFresh = () => {
        getList()
    }
    
    /**
     * 组件挂载时的初始化操作
     * 计算表格高度并获取初始数据
     */
    onMounted(() => {
        nextTick(() => {
            // 动态计算表格高度，减去头部和其他元素的高度
            tableHeight.value = window.innerHeight - 230
        })
        // 获取初始数据
        getList()
    })
    
    return {
        listParam,      // 查询参数
        tableDate,      // 表格数据
        getList,        // 获取列表数据方法
        sizeChange,     // 页容量改变处理方法
        currentChange,  // 页码改变处理方法
        tableHeight,    // 表格高度
        searchBtn,      // 搜索按钮处理方法
        resetBtn,       // 重置按钮处理方法
        reFresh         // 刷新表格方法
    }
}