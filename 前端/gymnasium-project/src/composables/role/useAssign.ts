import { getMenuTreeApi } from "@/api/role";
import { type AssignParam } from "@/api/role/RoleModel";
import { reactive } from "vue";

export default function useAssign() {
    // 树的属性配置
    const defaultProps = reactive({
        children: "children",
        label: "title",
    });

    // 权限树数据
    const assignTreeData = reactive({
        list: [] as any[],
        assignTreeChecked: [] as number[], // 原来分配的权限id的集合
    });

    // 获取权限树数据
    const getMenuTree = async (param: AssignParam) => {
        let res = await getMenuTreeApi(param);
        if (res && res.code === 200) {
            // 设置权限树数据
            assignTreeData.list = res.data.listmenu;
            // 直接使用后端返回的勾选集合，统一转成 number 与 node-key 对齐
            assignTreeData.assignTreeChecked = (res.data.checkList || []).map((id: any) => Number(id));
        }
    };

    return {
        defaultProps,
        assignTreeData,
        getMenuTree,
    };
}
