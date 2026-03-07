/**
 * 我的课程表格组合式函数
 * 管理当前登录用户的课程列表，包含分页与高度计算
 */
import { ref, reactive, onMounted, nextTick, onBeforeUnmount, onActivated } from 'vue'
import { getMyCourseListApi } from '@/api/course'
import type { MyCourseListParam } from '@/api/course/CourseModel'
import { userStore } from '@/store/user'
import { ElMessage } from 'element-plus'

export default function useMyCourseTable(){
    const store = userStore()
    /** 表格高度 */
    const tableHeight = ref(0)
    /** 表格数据源 */
    const tableData = reactive({
        list:[]
    })
    /** 查询参数（包含用户标识与分页信息） */
    const listParam = reactive<MyCourseListParam>({
        userId:'',
        userType:'',
        currentPage:1,
        pageSize:10,
        total:0
    })

    /** 查询列表（等待用户信息就绪，失败重试一次） */
    const getList = async () =>{
        try {
            await waitForUserReady()
            listParam.userId = store.getUserId
            listParam.userType = store.getUserType
            const res = await getMyCourseListApi(listParam)
            if(res && res.code == 200){
                tableData.list = res.data.records
                listParam.total = res.data.total
            } else {
                const retry = await getMyCourseListApi(listParam)
                if (retry && retry.code == 200) {
                    tableData.list = retry.data.records
                    listParam.total = retry.data.total
                }
            }
        } catch (e:any) {
            ElMessage.error(e?.message || '获取我的课程失败')
        }
    }

    /** 页面容量改变 */
    const sizeChange = (size:number)=>{
        listParam.pageSize = size
        getList()
    }

    /** 页码改变 */
    const currentChange = (page:number)=>{
        listParam.currentPage = page
        getList()
    }

    /** 计算表格高度（适配窗口变化） */
    const calcHeight = () => {
        tableHeight.value = Math.max(300, window.innerHeight - 230)
    }

    /** 等待用户信息可用（最多2秒） */
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

    onMounted(()=>{
        nextTick(calcHeight)
        window.addEventListener('resize', calcHeight)
        getList()
    })

    onActivated(() => {
        // 从标签或路由返回时保证数据刷新
        getList()
    })

    onBeforeUnmount(()=>{
        window.removeEventListener('resize', calcHeight)
    })

    return {
        listParam,
        tableData,
        getList,
        sizeChange,
        currentChange,
        tableHeight
    }
}
