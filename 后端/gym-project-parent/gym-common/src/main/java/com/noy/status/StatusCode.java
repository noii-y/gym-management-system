package com.noy.status;

/**
 * 系统状态码常量类
 * 
 * <p>定义系统中使用的各种HTTP状态码常量</p>
 * 
 * <p>包含成功、错误、权限相关的状态码定义</p>
 * 
 * @author noy
 * @version 1.0
 * @since 2024
 */
public class StatusCode {
    
    /**
     * 操作成功状态码
     * <p>HTTP 200 - 请求成功</p>
     */
    public static final int SUCCESS_CODE = 200;
    
    /**
     * 服务器内部错误状态码
     * <p>HTTP 500 - 服务器内部错误</p>
     */
    public static final int ERROR_CODE = 500;
    
    /**
     * 用户未登录状态码
     * <p>HTTP 600 - 用户未登录或登录已过期</p>
     */
    public static final int NO_LOGIN = 600;
    
    /**
     * 用户无权限状态码
     * <p>HTTP 600 - 用户无权限访问该资源</p>
     */
    public static final int NO_AUTH = 600;
}