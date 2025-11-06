/**
 * 路由权限控制文件
 * 实现全局路由守卫，控制页面访问权限
 */
import router from "./router"
import { userStore } from "./store/user"
import { menuStore } from "./store/menu"

/**
 * 白名单路由 - 无需权限即可访问的页面
 */
const whiteList = ['/login']

/**
 * 全局前置路由守卫
 * 在每次路由跳转前进行权限验证
 */
router.beforeEach(async (to, from, next) => {
  // 获取用户store实例
  const ustore = userStore()
  // 获取菜单store实例
  const mstore = menuStore()
  
  // 白名单路由优先放行（即使存在token也不做鉴权或数据拉取）
  if (whiteList.indexOf(to.path) !== -1) {
    next()
    return
  }

  // 获取用户token
  const token = ustore.getToken
  
  // 判断token是否存在
  if (token) {
    // token存在的情况
    if (to.path === '/login' || to.path === '/') {
      // 已登录用户访问登录页或根路径，重定向到首页
      next({ path: '/' })
    } else {
      // 检查菜单是否已加载
      const menuList = mstore.getMenuList
      if (menuList.length > 0) {
        // 菜单已加载，直接放行
        next()
      } else {
        try {
          // 获取用户信息
          await ustore.getInfo()
          
          // 获取菜单信息，动态生成路由
          await mstore.getMenu(router, {
            userId: ustore.getUserId,
            userType: ustore.getUserType
          })
          
          // 等待路由全部挂载完成后跳转
          next({ ...to, replace: true })
        } catch (error) {
          // 获取用户信息失败，清除本地数据并跳转到登录页
          console.error('获取用户信息失败:', error)
          localStorage.clear()
          ustore.clearUserInfo()
          next({ path: '/login' })
        }
      }
    }
  } else {
    // token不存在的情况
    if (whiteList.indexOf(to.path) !== -1) {
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