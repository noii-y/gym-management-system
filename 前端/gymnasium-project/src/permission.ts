// 全局路由守卫：控制页面访问权限
import router from "./router"
import { userStore } from "./store/user"
import { menuStore } from "./store/menu"

/**
 * 白名单路由 - 无需权限即可访问的页面
 */
const whiteList = ['/login', '/register', '/resetPassword']

/**
 * 全局前置路由守卫
 * 在每次路由跳转前进行权限验证
 */
router.beforeEach(async (to, from, next) => {
  // 用户状态
  const ustore = userStore()
  // 菜单状态
  const mstore = menuStore()
  
  // 白名单：无需鉴权直接放行
  if (whiteList.includes(to.path)) {
    next()
    return
  }

  // 获取用户token
  const token = ustore.getToken
  
  // 判断token是否存在
  if (token) {
    if (to.path === '/login') {
      next({ path: '/' })
    } else {
      const menuList = mstore.getMenuList
      if (menuList.length > 0) {
        next()
      } else {
        try {
          await ustore.getInfo()
          await mstore.getMenu(router, {
            userId: ustore.getUserId,
            userType: ustore.getUserType
          })
          next({ ...to, replace: true })
        } catch (error) {
          if (import.meta.env.DEV) { console.error('获取用户信息失败:', error) }
          localStorage.clear()
          ustore.clearUserInfo()
          next({ path: '/login' })
        }
      }
    }
  } else {
    // token不存在的情况
    if (whiteList.includes(to.path)) {
      // 目标路径在白名单中，直接放行
      next()
    } else {
      // 未登录用户访问需要权限的页面，清除数据并跳转到登录页
      localStorage.clear()
      ustore.clearUserInfo()
      next({ path: '/login' })
    }
  }
})