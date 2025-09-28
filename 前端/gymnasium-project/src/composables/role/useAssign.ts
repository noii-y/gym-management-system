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
            // 设置角色原来的权限Id
            assignTreeData.assignTreeChecked = res.data.checkList;

            // 数据回显，判断角色原来是否已经分配过权限，如果有，回显
            if (assignTreeData.assignTreeChecked.length > 0) {
                let newArr: number[] = [];
                assignTreeData.assignTreeChecked.forEach((id) => {
                    checked(id, assignTreeData.list, newArr);
                });
                assignTreeData.assignTreeChecked = newArr;
            }
        }
    };

    // 递归检查权限
    const checked = (id: number, data: any[], newArr: number[]) => {
        data.forEach((item: any) => {
            if (item.menuId === id) {
                if (item.children && item.children.length === 0) {
                    newArr.push(item.menuId);
                }
            } else {
                if (item.children && item.children.length > 0) {
                    // 递归调用
                    checked(id, item.children, newArr);
                }
            }
        });
    };

    return {
        defaultProps,
        assignTreeData,
        getMenuTree,
    };
}
