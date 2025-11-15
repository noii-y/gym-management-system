import { ref } from 'vue'
import { ElMessage } from 'element-plus'
import type { UploadFile } from 'element-plus'
import { uploadImageApi } from '@/api/course/index'
// 图片上传：管理文件列表、上传与预览
export default function useUpload() {
    // 上传组件 ref
    const uploadRef = ref()
    // 预览地址
    const dialogImageUrl = ref('')
    // 预览弹窗开关
    const dialogVisible = ref(false)
    const disabled = ref(false)
    // 文件列表
    const fileList = ref<UploadFile[]>([])
    // 已上传图片地址
    const imgurl = ref('');
    //删除图片
    const handleRemove = (file: UploadFile) => {
        fileList.value = fileList.value.filter(item => item.name != file.name)
    }
    //点击预览图片
    const handlePictureCardPreview = (file: UploadFile) => {
        dialogImageUrl.value = file.url!
        dialogVisible.value = true
    }
    //图片上传
    const uploadFile = async (file: any) => {
        const typeArr = ["image/png", "image/gif", "image/jpeg",
            "image/jpg"];
        const isImg = typeArr.indexOf(file.raw.type) !== -1;
        const isMore3M = file.size / 1024 / 1024 < 3;
        if (!isImg) {
            ElMessage.warning("只能上传图片类型!");
            uploadRef.value?.clearFiles()
            return;
        }
        if (!isMore3M) {
            ElMessage.warning("只能上传图片类型!");
            uploadRef.value?.clearFiles()
            return;
        }
        const formData = new FormData();
        formData.append("file", file.raw);
        let res = await uploadImageApi(formData);
        if (res && res.code == 200 && res.data) {
            imgurl.value = res.data.msg;
            ElMessage.success("图片上传成功!");
        }
    }
    return {
        dialogImageUrl,
        dialogVisible,
        disabled,
        handleRemove,
        handlePictureCardPreview,
        fileList,
        uploadFile,
        uploadRef,
        imgurl
    }
}