/**
 * 订单结算抽屉组合式函数
 * 管理父级抽屉显示、清空购物车与删除购物车项等逻辑
 */
import type { AddCar, GoodsType } from "@/api/goods/GoodsModel";
import { ref } from "vue";

export default function useFaDrawer(selectGoods: AddCar) {
    /** 父级抽屉显示状态 */
    const faDrawer = ref(false)
    /** 取消关闭抽屉 */
    const cancelClick = () => {
        faDrawer.value = false;
    };
    /** 确认关闭抽屉（此处仅关闭，实际下单逻辑在页面触发） */
    const confirmClick = () => {
        faDrawer.value = false;
    };
    /** 打开结算抽屉并清空购物车 */
    const order = () => {
        selectGoods.list = []
        faDrawer.value = true;
    }
    /** 从购物车删除指定商品 */
    const deleteCarBtn = (row: GoodsType) => {
        selectGoods.list = selectGoods.list.filter(item =>
            item.goodsId != row.goodsId)
    }
    return {
        faDrawer,
        cancelClick,
        confirmClick,
        order,
        deleteCarBtn
    }
}
