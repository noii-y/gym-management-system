package com.noy.config.minio;

import io.minio.MinioClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * MinIO对象存储配置类
 * 用于配置MinIO客户端连接，提供文件上传下载服务
 * 
 * @author noy
 * @version 1.0
 */
@Configuration
@EnableConfigurationProperties(MinioProp.class)
public class MinioConfig {

    @Autowired
    private MinioProp minioProp;

    /**
     * 创建MinIO客户端Bean
     * 根据配置文件中的MinIO连接信息创建客户端实例
     * 
     * @return MinioClient 客户端实例
     * @throws RuntimeException 当创建客户端失败时抛出异常
     */
    @Bean
    public MinioClient minioClient() {
        try {
            return MinioClient.builder()
                    .endpoint(minioProp.getEndpoint())
                    .credentials(minioProp.getAccesskey(), minioProp.getSecretKey())
                    .build();
        } catch (Exception e) {
            throw new RuntimeException("Failed to create MinioClient: " + e.getMessage(), e);
        }
    }
}
