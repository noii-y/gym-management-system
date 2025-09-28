/**
 * 验证码图片管理组合式函数
 * 用于获取和管理登录页面的验证码图片
 */
import { onMounted, ref } from "vue";
import { getImageApi } from "@/api/login";

export default function useImage() {
  /**
   * 验证码图片的src地址
   */
  const imgSrc = ref<string>()

  /**
   * 获取验证码图片
   * 调用API获取新的验证码图片并更新imgSrc
   */
  const getImage = async () => {
    let res = await getImageApi()
    console.log(res)
    if (res && res.code == 200) {
      imgSrc.value = res.data.image;
    }
  }

  /**
   * 组件挂载时自动获取验证码图片
   */
  onMounted(() => {
    getImage()
  })

  return {
    imgSrc,    // 验证码图片地址
    getImage   // 获取验证码图片的方法
  }
}