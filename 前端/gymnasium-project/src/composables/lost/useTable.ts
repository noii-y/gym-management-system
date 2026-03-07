/**
 * 失物招领表格组合式函数
 * 负责列表的搜索、分页、重置与高度计算
 */
import { nextTick, onMounted, reactive, ref } from "vue";
import type { LostParam } from '@/api/lost/LostModel'
import { getListApi } from "@/api/lost";

export default function useTable() {
    /** 表格高度 */
    const tableHeight = ref(0)
    /** 表格数据源 */
    const tableData = reactive({
        list: []
    })
    /** 搜索与分页参数 */
    const listParam = reactive<LostParam>({
        currentPage: 1,
        pageSize: 10,
        lostName: '',
        total: 0
    })
    /** 重置搜索条件并回到第一页 */
    const resetBtn = () => {
        listParam.lostName = ''
        listParam.currentPage = 1;
        getList()
    }
    /** 根据当前条件搜索 */
    const searchBtn = () => {
        getList()
    }
    /** 拉取列表数据 */
    const getList = async () => {
        let res = await getListApi(listParam)
        if (res && res.code == 200) {
            tableData.list = res.data.records;
            listParam.total = res.data.total
        }
    }
    /** 页容量改变 */
    const sizeChange = (size: number) => {
        listParam.pageSize = size;
        getList()
    }
    /** 页码改变 */
    const currentChange = (page: number) => {
        listParam.currentPage = page;
        getList()
    }
    /** 刷新当前页 */
    const reFresh = () => {
        getList()
    }
    onMounted(() => {
        nextTick(() => {
            tableHeight.value = window.innerHeight - 230
        })
        getList()
    })
    return {
        listParam,
        resetBtn,
        searchBtn,
        getList,
        tableData,
        sizeChange,
        currentChange,
        tableHeight,
        reFresh
    }
}
