import type { listParam } from "@/api/material/MaterialModel";
import { nextTick, onMounted, reactive, ref } from "vue";
import { getListApi } from "@/api/material";
export default function useTable() {
    //表格高度
    const tableHeight = ref(0)
    //定义表格数据
    const tableData = reactive({
        list: []
    })
    //列表查询参数
    const listParam = reactive<listParam>({
        name: '',
        currentPage: 1,
        pageSize: 10,
        total: 10
    })
    //获取表格数据
    const getList = async () => {
        let res = await getListApi(listParam)
        if (res && res.code == 200) {
            console.log(res)
            tableData.list = res.data.records;
            listParam.total = res.data.total;
        }
    }
    //搜索
    const searchBtn = () => {
        getList()
    }
    //重置
    const resetBtn = () => {
        listParam.name = ''
        listParam.currentPage = 1
        getList()
    }
    //页容量改变时触发
    const sizeChange = (size: number) => {
        listParam.pageSize = size;
        getList()
    }
    //页数改变时触发
    const currentChange = (page: number) => {
        listParam.currentPage = page;
        getList()
    }
    //刷新列表
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
        searchBtn,
        resetBtn,
        tableData,
        sizeChange,
        currentChange,
        tableHeight,
        reFresh,
        getList
    }
}