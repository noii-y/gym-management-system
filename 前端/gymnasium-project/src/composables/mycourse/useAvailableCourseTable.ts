/**
 * 未报名课程表格组合式函数
 * 展示用户未报名的课程列表，并提供搜索与分页能力
 */
import { nextTick, onMounted, reactive, ref } from 'vue'
import { listApi as courseListApi } from '@/api/course'
import { getMyCourseListApi } from '@/api/course'
import type { CourselistParam, MyCourseListParam } from '@/api/course/CourseModel'
import { userStore } from '@/store/user'
import { ElMessage } from 'element-plus'

export default function useAvailableCourseTable(){
  const store = userStore()
  /** 表格高度 */
  const tableHeight = ref(0)
  /** 表格数据源 */
  const tableData = reactive({ list: [] as any[] })

  /** 搜索与分页参数 */
  const listParam = reactive<CourselistParam>({
    courseName: '',
    teacherName: '',
    currentPage: 1,
    pageSize: 10,
    total: 0
  })

  /**
   * 获取未报名课程列表：并发获取“已报名课程”和“全部课程”，进行差集过滤
   */
  const getList = async () => {
    try {
      const myParam: MyCourseListParam = {
        userId: store.getUserId,
        userType: store.getUserType,
        currentPage: 1,
        pageSize: 9999,
        total: 0
      }
      const [enrolledRes, allRes] = await Promise.all([
        getMyCourseListApi(myParam),
        courseListApi(listParam)
      ])
      const enrolledIds = new Set<number | string>((enrolledRes?.data?.records || []).map((r: any) => r.courseId))
      const all = allRes?.data?.records || []
      const filtered = all.filter((c: any) => !enrolledIds.has(c.courseId))
      tableData.list = filtered
      // 兼顾后端 total 与前端过滤的结果
      listParam.total = (allRes?.data?.total || filtered.length) - (all.length - filtered.length)
    } catch (e:any) {
      ElMessage.error(e?.message || '获取未报名课程失败')
    }
  }

  /** 页容量变化 */
  const sizeChange = (size:number)=>{
    listParam.pageSize = size
    getList()
  }

  /** 页码变化 */
  const currentChange = (page:number)=>{
    listParam.currentPage = page
    getList()
  }

  /** 搜索（回到第一页） */
  const searchBtn = ()=>{
    listParam.currentPage = 1
    getList()
  }

  /** 重置搜索条件并重查 */
  const resetBtn = ()=>{
    listParam.courseName = ''
    listParam.teacherName = ''
    listParam.currentPage = 1
    listParam.pageSize = 10
    getList()
  }

  /** 初始化高度并首次拉取数据 */
  onMounted(()=>{
    nextTick(()=>{
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
