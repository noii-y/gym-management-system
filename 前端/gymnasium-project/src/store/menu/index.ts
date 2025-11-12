/**
 * 菜单状态管理
 * 管理系统动态菜单的生成和路由配置
 */
import { defineStore } from "pinia";
import { getMenuListApi } from "@/api/login";
import { type RouteRecordRaw, type Router } from "vue-router";
import Layout from '@/layout/Index.vue'
import center from '@/layout/center/center.vue'
import { type InfoParam } from "@/api/login/LoginModel";

/**
 * 静态导入所有页面组件
 * 用于动态路由组件的加载
 */
// 课程管理
import AddCourse from '@/views/course/AddCourse.vue'
import CourseList from '@/views/course/CourseList.vue'
import TeachingList from '@/views/course/TeachingList.vue'
// 商品管理
import AddGoods from '@/views/goods/AddGoods.vue'
import GoodsList from '@/views/goods/GoodsList.vue'
// 失物招领
import AddLost from '@/views/lost/AddLost.vue'
import LostList from '@/views/lost/LostList.vue'
import LostPerson from '@/views/lost/LostPerson.vue'
// 器材管理
import AddMaterial from '@/views/material/AddMaterial.vue'
import MaterialList from '@/views/material/MaterialList.vue'
// 会员管理
import AddCard from '@/views/member/AddCard.vue'
import AddMember from '@/views/member/AddMember.vue'
import CardType from '@/views/member/CardType.vue'
import MemberList from '@/views/member/MemberList.vue'
import RechargeRecord from '@/views/member/RechargeRecord.vue'
import MyRecharge from '@/views/member/MyRecharge.vue'
import JoinApply from '@/views/member/list/JoinApply.vue'
import Recharge from '@/views/member/list/Recharge.vue'
// 我的课程
import MyCourse from '@/views/mycourse/mycourse.vue'
// 订单管理
import OrderList from '@/views/order/OrderList.vue'
// 建议管理
import AddSuggest from '@/views/suggest/AddSuggest.vue'
import SuggestList from '@/views/suggest/SuggestList.vue'
// 系统管理
import AssignRole from '@/views/system/AssignRole.vue'
import AddMenu from '@/views/system/menu/AddMenu.vue'
import MenuList from '@/views/system/menu/MenuList.vue'
import ParentMenu from '@/views/system/menu/ParentMenu.vue'
import RoleAdd from '@/views/system/role/RoleAdd.vue'
import RoleList from '@/views/system/role/RoleList.vue'
import AddUser from '@/views/system/user/AddUser.vue'
import UserList from '@/views/system/user/UserList.vue'

/**
 * 组件映射表
 * 将组件路径映射到实际的组件
 */
const componentMap: Record<string, any> = {
  '/course/AddCourse': AddCourse,
  '/course/CourseList': CourseList,
  '/course/TeachingList': TeachingList,
  '/goods/AddGoods': AddGoods,
  '/goods/GoodsList': GoodsList,
  '/lost/AddLost': AddLost,
  '/lost/LostList': LostList,
  '/lost/LostPerson': LostPerson,
  '/material/AddMaterial': AddMaterial,
  '/material/MaterialList': MaterialList,
  '/member/AddCard': AddCard,
  '/member/AddMember': AddMember,
  '/member/CardType': CardType,
  // 兼容数据库中的旧路径写法
  '/member/type/CardType': CardType,
  '/member/MemberList': MemberList,
  '/member/list/MemberList': MemberList,
  '/member/MyFee': RechargeRecord,
  '/member/fee/MyFee': RechargeRecord,
  // 新增“我的充值”兼容路径映射
  '/member/MyRecharge': MyRecharge,
  '/member/fee/MyRecharge': MyRecharge,
  '/member/list/JoinApply': JoinApply,
  '/member/list/Recharge': Recharge,
  '/mycourse/mycourse': MyCourse,
  '/order/OrderList': OrderList,
  // 课程订单：兼容后端可能配置的不同组件路径写法
  '/course/OrderList': OrderList,
  // 旧数据中的课程订单组件路径（数据库 url 为 '/course/Order'）
  '/course/Order': OrderList,
  '/courseOrder': OrderList,
  '/suggest/AddSuggest': AddSuggest,
  '/suggest/SuggestList': SuggestList,
  '/system/AssignRole': AssignRole,
  '/system/menu/AddMenu': AddMenu,
  '/system/menu/MenuList': MenuList,
  '/system/menu/ParentMenu': ParentMenu,
  '/system/role/RoleAdd': RoleAdd,
  '/system/role/RoleList': RoleList,
  '/system/user/AddUser': AddUser,
  '/system/user/UserList': UserList,
}

/**
 * 菜单状态类型定义
 */
interface MenuState {
  menuList: RouteRecordRaw[]  // 菜单路由列表
}

/**
 * 菜单状态Store
 * 用于管理系统的动态菜单和路由
 */
export const menuStore = defineStore('menuStore', {
  /**
   * 状态定义
   */
  state: (): MenuState => {
    return {
      menuList: []  // 当前用户的菜单列表
    }
  },
  
  /**
   * 计算属性
   */
  getters: {
    /**
     * 获取菜单列表
     * @param state 当前状态
     * @returns RouteRecordRaw[] 菜单路由列表
     */
    getMenuList(state: MenuState): RouteRecordRaw[] {
      // 读取时兼容旧菜单项，将 /myFee 的标题替换为“充值记录”
      const fixTitle = (route: any): any => {
        const tmp = { ...route }
        if (tmp && typeof tmp.path === 'string' && tmp.path === '/myFee') {
          tmp.meta = tmp.meta || {}
          tmp.meta.title = '充值记录'
        }
        if (tmp.children && tmp.children.length > 0) {
          tmp.children = tmp.children.map((child: any) => fixTitle(child))
        }
        return tmp
      }
      return (state.menuList || []).map((r: any) => fixTitle(r))
    }
  },
  
  /**
   * 操作方法
   */
  actions: {
    /**
     * 获取用户菜单并生成动态路由
     * @param router Vue路由实例
     * @param Param 用户信息参数
     * @returns Promise<RouteRecordRaw[]> 生成的路由列表
     */
    getMenu(router: Router, Param: InfoParam): Promise<RouteRecordRaw[]> {
      return new Promise((resolve, reject) => {
        getMenuListApi(Param).then((res) => {
          let accessRoute: RouteRecordRaw[] = [];
          
          if (res && res.code == 200) {
            // 调试日志：后端返回的菜单路由数据
            try { console.log('[menu] getMenuListApi data:', res.data) } catch (_) {}
            // 动态生成路由
            accessRoute = generateRoutes(res.data, router)
            
            // 添加首页路由
            const desk: RouteRecordRaw[] = [
              {
                path: "/dashboard",
                component: Layout,
                name: "dashboard",
                meta: {
                  title: "首页",
                  icon: "HomeFilled",
                  roles: ["sys:dashboard"],
                },
                children: []
              }
            ]
            
            // 合并首页和动态路由
            this.menuList = desk.concat(accessRoute)
          }
          
          resolve(this.menuList)
        }).catch((error) => {
          reject(error)
        })
      })
    }
  },
  
  /**
   * 持久化配置
   * 将菜单状态保存到localStorage
   */
  persist: true
})

/**
 * 动态生成路由函数
 * 根据后端返回的菜单数据生成前端路由配置
 * @param routes 后端返回的路由数据
 * @param router Vue路由实例
 * @returns RouteRecordRaw[] 生成的路由配置
 */
export function generateRoutes(routes: RouteRecordRaw[], router: Router): RouteRecordRaw[] {
  // 定义接收生成的菜单
  const res: RouteRecordRaw[] = [];
  
  routes.forEach((route: any) => {
    // 复制路由数据到新对象
    const tmp = { ...route }
    const component = tmp.component;
    
    // 设置路由组件
    if (route.component) {
      if (component === 'Layout') {
        // 布局组件
        tmp.component = Layout;
      } else {
        // 从组件映射表中获取组件
        const mapped = componentMap[component]
        if (!mapped) {
          // 映射缺失时记录日志，方便排查数据库中的 url 是否与前端组件映射一致
          try { console.warn('[menu] component not mapped:', component, 'path:', tmp.path) } catch (_) {}
        }
        tmp.component = mapped
      }
    }
    // 兼容旧菜单标题：将 MyFee 的显示名统一替换为“充值记录”
    if (component === '/member/MyFee' || component === '/member/fee/MyFee' || tmp.path === '/myFee') {
      tmp.meta = tmp.meta || {}
      tmp.meta.title = '充值记录'
    }
    // 新增：确保 /myRecharge 使用“我的充值”标题
    if (component === '/member/MyRecharge' || component === '/member/fee/MyRecharge' || tmp.path === '/myRecharge') {
      tmp.meta = tmp.meta || {}
      tmp.meta.title = '我的充值'
      // 标注仅会员角色适用，供前端可见性控制与路由守卫参考
      tmp.meta.role = '会员'
    }
    // 新增：课程管理三子菜单的前端可见性标注
    if (component === '/course/CourseList' || tmp.path === '/courseList') {
      tmp.meta = tmp.meta || {}
      tmp.meta.role = '员工'
    }
    if (component === '/mycourse/mycourse' || tmp.path === '/myCourse') {
      tmp.meta = tmp.meta || {}
      tmp.meta.role = '会员'
    }
    if (component === '/course/TeachingList' || tmp.path === '/myTeaching') {
      tmp.meta = tmp.meta || {}
      tmp.meta.role = '教练'
    }
    
    // 如果存在子路由，递归处理
    if (tmp.children && tmp.children.length > 0) {
      // 规范子路由路径：若为相对路径，则拼接父路径为绝对路径
      tmp.children = tmp.children.map((child: any) => {
        const childTmp = { ...child }
        if (childTmp && typeof childTmp.path === 'string' && !childTmp.path.startsWith('/')) {
          const parentPath = typeof tmp.path === 'string' ? tmp.path.replace(/\/$/, '') : ''
          childTmp.path = `${parentPath}/${childTmp.path}`
        }
        return childTmp
      })
      if (route.component !== 'Layout') {
        // 非布局组件使用center容器
        tmp.component = center;
      }
      // 递归生成子路由
      tmp.children = generateRoutes(tmp.children, router)
    }
    
    // 组件缺失的叶子节点不添加，避免报错
    if (!tmp.children || tmp.children.length === 0) {
      if (!tmp.component) {
        return
      }
    }

    // 动态添加路由到Vue Router
    router.addRoute(tmp)
    res.push(tmp)
  })
  
  return res;
}