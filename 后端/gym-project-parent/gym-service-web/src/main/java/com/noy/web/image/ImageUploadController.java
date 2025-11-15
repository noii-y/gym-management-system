package com.noy.web.image;

// 第三方库导入
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;

// Spring 框架相关
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

// 项目内部类
import com.noy.config.minio.MinioUtils;
import com.noy.utils.ResultUtils;
import com.noy.utils.ResultVo;

/**
 * 图片上传控制器：基于 MinIO 的图片上传接口
 */
@RestController
@RequestMapping("/api/upload/")
@Slf4j  // 打印相关日志
public class ImageUploadController {
    
    // ==================== 依赖注入 ====================
    
    /**
     * MinIO工具类，用于文件上传和管理
     */
    @Autowired
    MinioUtils minioUtils;
    
    // ==================== API接口方法 ====================
    
    /** 上传图片 */
    @RequestMapping("/uploadImage")
    public ResultVo uploadImage(@RequestParam("file") MultipartFile file) {
        log.info("开始进行文件上传");
        JSONObject images = null;
        try {
            images = minioUtils.uploadFile(file, "gym"); // 这里记得填正确的bucket容器
        } catch (Exception e) {
            log.error("文件上传失败", e);
        }
        return ResultUtils.success("图片上传成功", images);
    }
}
