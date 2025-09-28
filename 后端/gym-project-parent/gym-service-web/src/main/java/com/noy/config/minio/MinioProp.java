package com.noy.config.minio;

// Lombok 注解
import lombok.Data;

// Spring Boot 配置属性
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * MinIO对象存储配置属性类
 * 
 * <p>用于读取application.yml中的MinIO相关配置信息</p>
 * 
 * <p>通过@ConfigurationProperties注解自动绑定配置文件中的minio前缀属性</p>
 * 
 * @author noy
 * @version 1.0
 * @since 2024
 */
@Data
@Component
@ConfigurationProperties(prefix = "minio")
public class MinioProp {

    /**
     * MinIO服务端点地址
     * 例如：http://127.0.0.1:9000
     */
    private String endpoint;
    
    /**
     * MinIO访问密钥
     * 用于身份验证的访问密钥ID
     */
    private String accesskey;
    
    /**
     * MinIO秘密密钥
     * 用于身份验证的秘密访问密钥
     */
    private String secretKey;
}
