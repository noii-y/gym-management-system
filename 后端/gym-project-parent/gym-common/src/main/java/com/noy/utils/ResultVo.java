package com.noy.utils;

// 第三方库
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 统一API响应结果封装类
 * 
 * <p>用于封装所有API接口的返回结果，提供统一的响应格式</p>
 * 
 * <p>响应结构包括：</p>
 * <ul>
 *   <li>msg - 响应消息</li>
 *   <li>code - 状态码</li>
 *   <li>data - 响应数据（泛型支持）</li>
 * </ul>
 * 
 * @param <T> 响应数据的类型
 * @author noy
 * @version 1.0
 * @since 2024
 */
@Data
@AllArgsConstructor
public class ResultVo<T> {

    /**
     * 响应消息
     */
    private String msg;
    
    /**
     * 状态码
     */
    private int code;
    
    /**
     * 响应数据
     */
    private T data;
}
