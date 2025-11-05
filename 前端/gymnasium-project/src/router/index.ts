/**
 * 健身房管理系统路由配置
 * 定义了系统的所有路由规则和权限控制
 */
import { createRouter, createWebHistory, type RouteRecordRaw } from 'vue-router'
import Layout from '@/layout/Index.vue'

/**
 * 常量路由配置
 * 包含所有静态路由定义，支持权限控制
 */
export const constantRoutes: Array<RouteRecordRaw> = [
  // 登录页面路由
  {
    path: "/login",
    component: () => import('@/views/login/Login.vue'),
    name: "login"
  },
  
  // 主页面布局路由
  {
    path: '/',
    component: Layout,
    redirect: '/dashboard',
    children: [
      {
        path: '/dashboard',
        component: () => import('@/layout/dashboard/Index.vue'),
        name: 'dashboard',
        meta: {
          title: '首页',
          icon: 'Dashboard'
        }
      }
    ]
  },
  
  // 系统管理模块
  {
    path: "/system",
    component: Layout,
    name: "systemRoot",
    meta: {
      title: "系统管理",
      icon: "Setting",
      roles: ["sys:manage"]
    },
    children: [
      // 员工管理
      {
        path: "/userList",
        component: () => import('@/views/system/user/UserList.vue'),
        name: "userList",
        meta: {
          title: "员工管理",
          icon: "User",
          roles: ["sys:user"]
        }
      },
      // 角色管理
      {
        path: "/roleList",
        component: () => import('@/views/system/role/RoleList.vue'),
        name: "roleList",
        meta: {
          title: "角色管理",
          icon: "UserFilled",
          roles: ["sys:role"]
        }
      },
      // 权限管理
      {
        path: "/menuList",
        component: () => import('@/views/system/menu/MenuList.vue'),
        name: "menuList",
        meta: {
          title: "权限管理",
          icon: "Document",
          roles: ["sys:menu"]
        }
      }
    ]
  },
  
  // 会员管理模块
  {
    path: "/member",
    component: Layout,
    name: "memberRoot",
    meta: {
      title: "会员管理",
      icon: "User",
      roles: ["sys:memberRoot"]
    },
    children: [
      // 会员卡类型管理
      {
        path: "/cardType",
        component: () => import('@/views/member/CardType.vue'),
        name: "cardType",
        meta: {
          title: "会员卡类型",
          icon: "CreditCard",
          roles: ["sys:cardType"]
        }
      },
      // 会员信息管理
      {
        path: "/memberList",
        component: () => import('@/views/member/MemberList.vue'),
        name: "memberList",
        meta: {
          title: "会员管理",
          icon: "UserFilled",
          roles: ["sys:memberList"]
        }
      },
      // 会员充值管理
      {
        path: "/myFee",
        component: () => import('@/views/member/MyFee.vue'),
        name: "myFee",
        meta: {
          title: "我的充值",
          icon: "Wallet",
          roles: ["sys:myFee"]
        }
      }
    ]
  },
  
  // 课程管理模块
  {
    path: "/course",
    component: Layout,
    name: "courseRoot",
    meta: {
      title: "课程管理",
      icon: "Reading",
      roles: ["sys:courseRoot"]
    },
    children: [
      // 课程列表管理
      {
        path: "/courseList",
        component: () => import('@/views/course/CourseList.vue'),
        name: "courseList",
        meta: {
          title: "课程列表",
          icon: "Document",
          roles: ["sys:courseList"]
        }
      },
      // 个人课程管理
      {
        path: "/myCourse",
        component: () => import('@/views/mycourse/mycourse.vue'),
        name: "myCourse",
        meta: {
          title: "我的课程",
          icon: "User",
          roles: ["sys:myCourse"]
        }
      },
      // 课程订单管理
      {
        path: "/courseOrder",
        component: () => import('@/views/order/OrderList.vue'),
        name: "courseOrder",
        meta: {
          title: "课程订单",
          icon: "ShoppingCart",
          roles: ["sys:courseOrder"]
        }
      }
    ]
  },
  
  // 器材管理模块
  {
    path: "/material",
    component: Layout,
    name: "materialRoot",
    meta: {
      title: "器材管理",
      icon: "Tools",
      roles: ["sys:materialRoot"]
    },
    children: [
      // 器材列表管理
      {
        path: "/materialList",
        component: () => import('@/views/material/MaterialList.vue'),
        name: "materialList",
        meta: {
          title: "器材列表",
          icon: "Document",
          roles: ["sys:materialList"]
        }
      }
    ]
  },
  
  // 商品管理模块
  {
    path: "/goods",
    component: Layout,
    name: "goodsRoot",
    meta: {
      title: "商品管理",
      icon: "Goods",
      roles: ["sys:goodsRoot"]
    },
    children: [
      // 商品列表管理
      {
        path: "/goodsList",
        component: () => import('@/views/goods/GoodsList.vue'),
        name: "goodsList",
        meta: {
          title: "商品列表",
          icon: "Document",
          roles: ["sys:goodsList"]
        }
      },
      // 订单管理
      {
        path: "/orderList",
        component: () => import('@/views/order/OrderList.vue'),
        name: "orderList",
        meta: {
          title: "订单管理",
          icon: "ShoppingCart",
          roles: ["sys:orderList"]
        }
      }
    ]
  },
  
  // 失物招领模块
  {
    path: "/lost",
    component: Layout,
    name: "lostRoot",
    meta: {
      title: "失物招领",
      icon: "Search",
      roles: ["sys:lostRoot"]
    },
    children: [
      // 失物列表管理
      {
        path: "/lostList",
        component: () => import('@/views/lost/LostList.vue'),
        name: "lostList",
        meta: {
          title: "失物列表",
          icon: "Document",
          roles: ["sys:lostList"]
        }
      }
    ]
  },
  
  // 反馈管理模块
  {
    path: "/suggest",
    component: Layout,
    name: "suggestRoot",
    meta: {
      title: "反馈管理",
      icon: "ChatDotRound",
      roles: ["sys:suggestRoot"]
    },
    children: [
      // 反馈列表管理
      {
        path: "/suggestList",
        component: () => import('@/views/suggest/SuggestList.vue'),
        name: "suggestList",
        meta: {
          title: "反馈列表",
          icon: "Document",
          roles: ["sys:suggestList"]
        }
      }
    ]
  }
]

/**
 * 创建路由实例
 * 使用HTML5 History模式
 */
const router = createRouter({
  history: createWebHistory(),
  routes: constantRoutes
})

export default router
