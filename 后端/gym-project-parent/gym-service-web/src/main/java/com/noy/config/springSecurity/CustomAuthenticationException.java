package com.noy.config.springSecurity;

// 认证失败处理器
import org.springframework.security.core.AuthenticationException;

/**
 * 自定义认证异常类
 * 
 * <p>继承自Spring Security的AuthenticationException，用于处理自定义的认证失败情况</p>
 * 
 * <p>主要用途：</p>
 * <ul>
 *   <li>封装自定义的认证错误信息</li>
 *   <li>提供更具体的异常描述</li>
 *   <li>支持国际化的错误消息</li>
 *   <li>便于统一的异常处理</li>
 * </ul>
 * 
 * @author noy
 * @version 1.0
 * @since 2024
 */
public class CustomAuthenticationException extends AuthenticationException {

    /**
     * 构造函数
     * 
     * <p>创建一个带有错误消息的自定义认证异常</p>
     * 
     * @param msg 错误消息
     */
    public CustomAuthenticationException(String msg) {
        super(msg);
    }
}
