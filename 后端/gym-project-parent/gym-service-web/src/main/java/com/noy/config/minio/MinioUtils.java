package com.noy.config.minio;

// Java标准库
import java.io.InputStream;

// 第三方库
import com.alibaba.fastjson.JSONObject;
import io.minio.BucketExistsArgs;
import io.minio.MakeBucketArgs;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import lombok.extern.slf4j.Slf4j;

// Spring框架
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

/**
 * MinIO对象存储工具类
 * 
 * <p>提供MinIO对象存储服务的操作工具方法</p>
 * 
 * <p>主要功能包括：</p>
 * <ul>
 *   <li>存储桶管理 - 创建和检查存储桶</li>
 *   <li>文件上传 - 支持多种文件格式上传</li>
 *   <li>文件名生成 - 自动生成唯一文件名</li>
 *   <li>异常处理 - 完善的错误处理机制</li>
 * </ul>
 * 
 * @author noy
 * @version 1.0
 * @since 2024
 */
@Slf4j
@Component
public class MinioUtils {

    /**
     * MinIO客户端
     */
    @Autowired
    private MinioClient client;
    
    /**
     * MinIO配置属性
     */
    @Autowired
    private MinioProp minioProp;

    // ==================== 存储桶管理方法 ====================
    
    /**
     * 创建存储桶
     * 
     * <p>如果存储桶不存在则创建，存在则跳过</p>
     * 
     * @param bucketName 存储桶名称
     * @throws RuntimeException 当创建存储桶失败时抛出
     */
    public void createBucket(String bucketName) {
        BucketExistsArgs bucketExistsArgs = BucketExistsArgs.builder()
                .bucket(bucketName)
                .build();
        MakeBucketArgs makeBucketArgs = MakeBucketArgs.builder()
                .bucket(bucketName)
                .build();
        try {
            if (client.bucketExists(bucketExistsArgs)) {
                return;
            }
            client.makeBucket(makeBucketArgs);
        } catch (Exception e) {
            log.error("创建桶失败：{}", e.getMessage());
            throw new RuntimeException(e);
        }
    }

    // ==================== 文件上传方法 ====================
    
    /**
     * 上传文件到MinIO存储桶
     * 
     * <p>支持文件验证、自动生成文件名、异常处理等功能</p>
     * 
     * @param file 要上传的文件
     * @param bucketName 目标存储桶名称
     * @return JSON格式的上传结果，包含状态码和消息/文件URL
     */
    public JSONObject uploadFile(MultipartFile file, String bucketName) {
        JSONObject res = new JSONObject();
        res.put("code", 0);
        
        // 判断上传文件是否为空
        if (null == file || 0 == file.getSize()) {
            res.put("msg", "上传文件不能为空");
            return res;
        }
        
        // 判断文件名是否为空
        String originalFilename = file.getOriginalFilename();
        if (originalFilename == null || originalFilename.isEmpty()) {
            res.put("msg", "文件名不能为空");
            return res;
        }
        
        // 判断文件是否有扩展名
        if (!originalFilename.contains(".")) {
            res.put("msg", "文件必须有扩展名");
            return res;
        }
        
        InputStream inputStream = null;
        try {
            // 判断存储桶是否存在
            createBucket(bucketName);
            
            // 新的文件名 = 存储桶名称_时间戳.后缀名
            String fileName = bucketName + "_" +
                    System.currentTimeMillis() +
                    originalFilename.substring(originalFilename.lastIndexOf("."));
            
            // 开始上传
            inputStream = file.getInputStream();
            PutObjectArgs args = PutObjectArgs.builder()
                    .bucket(bucketName)
                    .object(fileName)
                    .stream(inputStream, inputStream.available(), -1)
                    .build();
            client.putObject(args);
            
            res.put("code", 1);
            //getEndpoint前缀 + bucketName 桶名称 + fileName文件名称
            res.put("msg", minioProp.getEndpoint() + "/" + bucketName + "/" + fileName);
            
        } catch (Exception e) {
            log.error("文件上传失败：{}", e.getMessage(), e);
            res.put("msg", "文件上传失败：" + e.getMessage());
        } finally {
            // 确保输入流被关闭
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (Exception e) {
                    log.error("关闭输入流失败：{}", e.getMessage());
                }
            }
        }
        
        return res;
    }
}
