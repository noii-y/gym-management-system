import { nextTick, onMounted, reactive, ref } from "vue";
import { listApi, type CourseOrderListParam } from "@/api/courseOrder";

export default function useTable() {
  const tableHeight = ref(0)
  const tableData = reactive({
    list: [] as any[]
  })
  const listParam = reactive<CourseOrderListParam>({
    courseName: "",
    teacherName: "",
    currentPage: 1,
    pageSize: 10,
    total: 0
  })

  const getList = async () => {
    const res = await listApi(listParam)
    if (res && res.code == 200) {
      tableData.list = res.data.records
      listParam.total = res.data.total
    }
  }

  const sizeChange = (size: number) => {
    listParam.pageSize = size
    getList()
  }

  const currentChange = (page: number) => {
    listParam.currentPage = page
    getList()
  }

  const searchBtn = () => {
    getList()
  }

  const resetBtn = () => {
    listParam.courseName = ""
    listParam.teacherName = ""
    listParam.currentPage = 1
    listParam.pageSize = 10
    getList()
  }

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