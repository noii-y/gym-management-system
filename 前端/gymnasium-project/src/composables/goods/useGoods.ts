/**
 * 商品管理组合式函数
 * 提供商品的增删改查功能
 */
import type { GoodsType } from "@/api/goods/GoodsModel"
import { EditType } from "@/type/BaseEnum"
import { ref } from "vue"
import { deleteApi } from "@/api/goods"
import useInstance from "@/hooks/useInstance"
import { ElMessage } from "element-plus"
import type { FuncList } from "@/type/BaseType"

export default function useGoods(getList: FuncList) {
  const { global } = useInstance()
  
  /**
   * 新增/编辑商品组件的引用
   */
  const addRef = ref<{ show: (type: string, row?: GoodsType) => void }>()

  /**
   * 新增商品按钮处理
   */
  const addBtn = () => {
    addRef.value?.show(EditType.ADD)
  }

  /**
   * 编辑商品按钮处理
   * @param row 要编辑的商品数据
   */
  const editBtn = (row: GoodsType) => {
    addRef.value?.show(EditType.EDIT, row)
  }

  /**
   * 删除商品按钮处理
   * @param row 要删除的商品数据
   */
  const deleteBtn = async (row: GoodsType) => {
    const confirm = await global.$myconfirm('确定删除该数据吗?')
    if (confirm) {
      let res = await deleteApi(row.goodsId)
      if (res && res.code == 200) {
        ElMessage.success(res.msg)
        getList()
      }
    }
  }

  return {
    addBtn,    // 新增商品方法
    editBtn,   // 编辑商品方法
    deleteBtn, // 删除商品方法
    addRef     // 新增/编辑组件引用
  }
}