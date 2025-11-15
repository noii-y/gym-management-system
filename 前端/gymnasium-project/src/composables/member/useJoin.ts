import type { MemberType } from "@/api/member/MemberModel";
import { onMounted, reactive, ref } from "vue";
import { getListApi } from "@/api/member_card";
import type { CardType, listParam } from '@/api/member_card/MemberModel'
export default function useJoin() {
    //会员卡列表数据
    const cardList = ref<CardType[]>([])
    //弹框属性
    const joinRef = ref<{ show: (row: MemberType) => void }>()
    //办卡按钮
    const joinBtn = (row: MemberType) => {
        joinRef.value?.show(row)
    }
    //获取会员卡列表
    const getCardList = async () => {
        const param: listParam = {
            title: '',
            currentPage: 1,
            pageSize: 100,
            total: 0
        }
        let res = await getListApi(param)
        if (res && res.code == 200) {
            cardList.value = res.data?.records || []
        }
    }
    onMounted(() => {
        getCardList()
    })
    return {
        joinRef,
        joinBtn,
        cardList
    }
}