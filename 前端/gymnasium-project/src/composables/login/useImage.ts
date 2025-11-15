// 验证码图片：获取与展示
import { onMounted, ref } from "vue";
import { getImageApi } from "@/api/login";

export default function useImage() {
  // 图片地址
  const imgSrc = ref<string>()

  // 获取验证码图片
  const getImage = async () => {
    let res = await getImageApi()
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