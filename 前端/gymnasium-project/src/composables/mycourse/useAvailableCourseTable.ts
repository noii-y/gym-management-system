import { nextTick, onMounted, reactive, ref } from 'vue'
import { listApi as courseListApi } from '@/api/course'
import { getMyCourseListApi } from '@/api/course'
import type { CourselistParam, MyCourseListParam } from '@/api/course/CourseModel'
import { userStore } from '@/store/user'
import { ElMessage } from 'element-plus'

export default function useAvailableCourseTable(){
  const store = userStore()
  const tableHeight = ref(0)
  const tableData = reactive({ list: [] as any[] })

  const listParam = reactive<CourselistParam>({
    courseName: '',
    teacherName: '',
    currentPage: 1,
    pageSize: 10,
    total: 0
  })

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
      listParam.total = (allRes?.data?.total || filtered.length) - (all.length - filtered.length)
    } catch (e:any) {
      ElMessage.error(e?.message || '获取未报名课程失败')
    }
  }

  const sizeChange = (size:number)=>{
    listParam.pageSize = size
    getList()
  }

  const currentChange = (page:number)=>{
    listParam.currentPage = page
    getList()
  }

  const searchBtn = ()=>{
    listParam.currentPage = 1
    getList()
  }

  const resetBtn = ()=>{
    listParam.courseName = ''
    listParam.teacherName = ''
    listParam.currentPage = 1
    listParam.pageSize = 10
    getList()
  }

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