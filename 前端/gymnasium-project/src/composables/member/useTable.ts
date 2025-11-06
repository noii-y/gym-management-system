import type { MemberParam } from "@/api/member/MemberModel";
import { nextTick, onMounted, reactive, ref, onBeforeUnmount, onActivated } from "vue";
import { getListApi } from "@/api/member";
import { userStore } from "@/store/user";
import { ElMessage } from "element-plus";
export default function useTable() {
    const store = userStore()
    //表格高度
    const tableHeight = ref(0)
    //表格数据定义
    const tableList = reactive({
        list: []
    })
    //参数列表
    const listParam = reactive<MemberParam>({
        name: '',
        phone: '',
        username: '',
        pageSize: 10,
        currentPage: 1,
        memberId: '',
        userType: '',
        total: 0
    })
    //列表（等待用户信息就绪，并在失败时重试一次）
    const getList = async () => {
        try {
            await waitForUserReady()
            // 同步用户类型（如后端需要）
            listParam.userType = store.getUserType || listParam.userType
            const res = await getListApi(listParam)
            if (res && res.code == 200) {
                tableList.list = res.data.records
                listParam.total = res.data.total
            } else {
                const retry = await getListApi(listParam)
                if (retry && retry.code == 200) {
                    tableList.list = retry.data.records
                    listParam.total = retry.data.total
                }
            }
        } catch (e: any) {
            ElMessage.error(e?.message || '获取会员列表失败')
        }
    }
    //搜索
    const searchBtn = () => {
        getList()
    }
    //重置
    const resetBtn = () => {
        listParam.name = ''
        listParam.phone = ''
        listParam.username = ''
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
    //刷新表格
    const refresh = () => {
        getList()
    }
    // 等待用户信息就绪（token/userId），最多等待2秒
    const waitForUserReady = async () => {
        const start = Date.now()
        return new Promise<void>((resolve) => {
            const timer = setInterval(() => {
                const ready = !!store.getToken
                if (ready || Date.now() - start > 2000) {
                    clearInterval(timer)
                    resolve()
                }
            }, 100)
        })
    }

    const calcHeight = () => {
        tableHeight.value = Math.max(300, window.innerHeight - 230)
    }

    onMounted(() => {
        nextTick(calcHeight)
        window.addEventListener('resize', calcHeight)
        getList()
    })

    onActivated(() => {
        // 从标签或路由返回时保证数据刷新
        getList()
    })

    onBeforeUnmount(() => {
        window.removeEventListener('resize', calcHeight)
    })
    return {
        listParam,
        getList,
        searchBtn,
        resetBtn,
        tableList,
        sizeChange,
        currentChange,
        tableHeight,
        refresh
    }
}
