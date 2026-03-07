/**
 * 课程订单表格组合式函数
 * 管理课程订单的列表数据、分页、搜索与重置逻辑
 */
import { nextTick, onMounted, reactive, ref } from "vue";
import { listApi, type CourseOrderListParam } from "@/api/courseOrder";

/**
 * 初始化并返回课程订单表格相关的状态与方法
 */
export default function useTable() {
  /** 动态表格高度，用于自适应窗口尺寸 */
  const tableHeight = ref(0)
  /** 表格数据源 */
  const tableData = reactive({
    list: [] as any[]
  })
  /** 列表查询参数（搜索+分页） */
  const listParam = reactive<CourseOrderListParam>({
    courseName: "",
    teacherName: "",
    currentPage: 1,
    pageSize: 10,
    total: 0
  })

  /** 获取课程订单列表 */
  const getList = async () => {
    const res = await listApi(listParam)
    if (res && res.code == 200) {
      tableData.list = res.data.records
      listParam.total = res.data.total
    }
  }

  /** 页容量变化 */
  const sizeChange = (size: number) => {
    listParam.pageSize = size
    getList()
  }

  /** 页码变化 */
  const currentChange = (page: number) => {
    listParam.currentPage = page
    getList()
  }

  /** 执行搜索（保留当前页） */
  const searchBtn = () => {
    getList()
  }

  /** 重置搜索条件并回到第一页 */
  const resetBtn = () => {
    listParam.courseName = ""
    listParam.teacherName = ""
    listParam.currentPage = 1
    listParam.pageSize = 10
    getList()
  }

  /** 首次挂载计算高度并拉取数据 */
  onMounted(() => {
    nextTick(() => {
      tableHeight.value = Math.max(300, window.innerHeight - 230)
    })
    getList()
  })

  return {
    listParam,
    tableData,
    getList,
    sizeChange,
    currentChange,
    tableHeight,
    searchBtn,
    resetBtn
  }
}
