/**
 * 子抽屉组合式函数
 * 用于管理订单页面中商品选择的子抽屉功能
 */

// 导入Vue相关
import { onMounted, reactive, ref } from "vue";
// 导入API函数
import { listApi } from "@/api/goods";
// 导入Element Plus组件
import { ElMessage } from "element-plus";
// 导入类型定义
import type { GoodsParam, GoodsType, AddCar } from "@/api/goods/GoodsModel";

/**
 * 子抽屉管理组合式函数
 * @returns 返回子抽屉相关的状态和方法
 */
export default function useChildDrawer() {
    /**
     * 内部抽屉显示状态
     */
    const innerDrawer = ref(false)
    
    /**
     * 关闭内部抽屉
     */
    const innerCloseClick = () => {
        innerDrawer.value = false;
    };
    
    /**
     * 商品表格高度
     */
    const goodsHeight = ref(0)
    
    /**
     * 商品列表查询参数
     */
    const goodsParam = reactive<GoodsParam>({
        currentPage: 1,     // 当前页码
        pageSize: 20,       // 每页显示数量
        name: '',           // 商品名称搜索
        total: 0           // 总记录数
    })
    
    /**
     * 商品表格数据
     */
    const tableList = reactive({
        list: []
    })
    
    /**
     * 获取商品列表数据
     */
    const getList = async () => {
        let res = await listApi(goodsParam)
        if (res && res.code == 200) {
            tableList.list = res.data.records;
            goodsParam.total = res.data.total;
        }
    }
    
    /**
     * 显示内部抽屉
     */
    const innerDrawerShow = () => {
        getList()
        innerDrawer.value = true;
    }
    
    /**
     * 页容量改变时的处理函数
     * @param size 新的页容量
     */
    const goodsSizeChange = (size: number) => {
        goodsParam.pageSize = size;
        getList()
    }
    
    /**
     * 页码改变时的处理函数
     * @param page 新的页码
     */
    const goodsCurrentChange = (page: number) => {
        goodsParam.currentPage = page;
        getList()
    }
    
    /**
     * 搜索商品按钮点击事件
     */
    const searchGoodsBtn = () => {
        getList()
    }
    
    /**
     * 重置搜索条件
     */
    const resetGoodsBtn = () => {
        goodsParam.name = ''
        getList()
    }
    
    /**
     * 选择的商品数据（购物车）
     */
    const selectGoods = reactive<AddCar>({
        list: []
    })
    
    /**
     * 加入购物车按钮点击事件
     * @param row 要加入购物车的商品数据
     */
    const addCarBtn = (row: GoodsType) => {
        row.num = 1;
        // 判断商品是否已经加入购物车
        const flag = selectGoods.list.some(item => item.goodsId === row.goodsId)
        if (flag) {
            ElMessage.warning('该商品已经添加到购物车!');
            return;
        }
        selectGoods.list.push(row)
        ElMessage.success('加入成功!');
    }
    /**
     * 组件挂载时的初始化操作
     */
    onMounted(() => {
        // 计算商品表格高度
        goodsHeight.value = window.innerHeight - 180
    })
    
    /**
     * 返回组合式函数的公共接口
     */
    return {
        innerDrawer,        // 内部抽屉显示状态
        innerCloseClick,    // 关闭内部抽屉方法
        goodsParam,         // 商品查询参数
        getList,            // 获取商品列表方法
        tableList,          // 商品表格数据
        innerDrawerShow,    // 显示内部抽屉方法
        goodsSizeChange,    // 页容量改变处理
        goodsCurrentChange, // 页码改变处理
        searchGoodsBtn,     // 搜索商品方法
        resetGoodsBtn,      // 重置搜索方法
        goodsHeight,        // 商品表格高度
        addCarBtn,          // 加入购物车方法
        selectGoods         // 选择的商品数据
    }
}