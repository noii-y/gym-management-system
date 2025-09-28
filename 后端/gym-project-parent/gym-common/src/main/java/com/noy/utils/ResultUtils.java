package com.noy.utils;

// 项目内部类
import com.noy.status.StatusCode;

/**
 * 数据返回工具类
 * 
 * <p>提供统一的API响应结果封装工具方法</p>
 * 
 * <p>包含成功和失败响应的各种重载方法，支持不同参数组合</p>
 * 
 * @author noy
 * @version 1.0
 * @since 2024
 */
public class ResultUtils {

    // ==================== 成功响应方法 ====================
    
    /**
     * 无参数成功返回
     * 
     * @return 成功响应结果
     */
    public static ResultVo success() {
        return new ResultVo(null, StatusCode.SUCCESS_CODE, null);
    }
    
    /**
     * 带消息的成功返回
     * 
     * @param msg 响应消息
     * @return 成功响应结果
     */
    public static ResultVo success(String msg) {
        return new ResultVo(msg, StatusCode.SUCCESS_CODE, null);
    }

    /**
     * 带消息和数据的成功返回
     * 
     * @param msg 响应消息
     * @param data 响应数据
     * @return 成功响应结果
     */
    public static ResultVo success(String msg, Object data) {
        return new ResultVo(msg, StatusCode.SUCCESS_CODE, data);
    }
    
    /**
     * 自定义状态码的成功返回
     * 
     * @param msg 响应消息
     * @param code 状态码
     * @param data 响应数据
     * @return 成功响应结果
     */
    public static ResultVo success(String msg, int code, Object data) {
        return new ResultVo(msg, code, data);
    }

    // ==================== 错误响应方法 ====================
    
    /**
     * 无参数错误返回
     * 
     * @return 错误响应结果
     */
    public static ResultVo error() {
        return new ResultVo(null, StatusCode.ERROR_CODE, null);
    }
    
    /**
     * 带消息的错误返回
     * 
     * @param msg 错误消息
     * @return 错误响应结果
     */
    public static ResultVo error(String msg) {
        return new ResultVo(msg, StatusCode.ERROR_CODE, null);
    }
    
    /**
     * 自定义状态码和数据的错误返回
     * 
     * @param msg 错误消息
     * @param code 状态码
     * @param data 响应数据
     * @return 错误响应结果
     */
    public static ResultVo error(String msg, int code, Object data) {
        return new ResultVo(msg, code, data);
    }
    
    /**
     * 自定义状态码的错误返回
     * 
     * @param msg 错误消息
     * @param code 状态码
     * @return 错误响应结果
     */
    public static ResultVo error(String msg, int code) {
        return new ResultVo(msg, code, null);
    }
    
    /**
     * 带数据的错误返回
     * 
     * @param msg 错误消息
     * @param data 响应数据
     * @return 错误响应结果
     */
    public static ResultVo error(String msg, Object data) {
        return new ResultVo(msg, StatusCode.ERROR_CODE, data);
    }
}

