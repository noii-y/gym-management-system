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
 * 图片上传控制器
 * 
 * <p>提供图片文件上传相关的REST API接口，基于MinIO对象存储服务</p>
 * 
 * <p>主要功能包括：</p>
 * <ul>
 *   <li>图片文件上传 - 支持多种图片格式上传</li>
 *   <li>MinIO存储 - 将图片存储到MinIO对象存储服务</li>
 *   <li>文件管理 - 自动处理文件命名和路径管理</li>
 *   <li>异常处理 - 完善的文件上传异常处理机制</li>
 * </ul>
 * 
 * @author noy
 * @version 1.0
 * @since 2024
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
    
    /**
     * 上传图片文件
     * 
     * <p>将用户上传的图片文件存储到MinIO对象存储服务中</p>
     * 
     * @param file 要上传的图片文件
     * @return ResultVo 包含上传结果和文件信息的响应对象
     */
    @RequestMapping("/uploadImage")
    public ResultVo uploadImage(@RequestParam("file") MultipartFile file) {
        log.info("开始进行文件上传");
        JSONObject images = null;
        try {
            images = minioUtils.uploadFile(file, "gym"); // 这里记得填正确的bucket容器
        } catch (Exception e) {
            log.error("文件上传失败", e);
            e.printStackTrace();
        }
        return ResultUtils.success("图片上传成功", images);
    }
}
