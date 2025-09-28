package com.noy.config.springSecurity;

// 无权限访问的处理器
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.noy.utils.ResultVo;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 自定义访问拒绝处理器
 * 
 * <p>处理用户访问被拒绝的情况，当用户已通过认证但没有足够权限访问资源时触发</p>
 * 
 * <p>主要功能：</p>
 * <ul>
 *   <li>捕获权限不足异常</li>
 *   <li>返回统一格式的错误响应</li>
 *   <li>设置适当的HTTP状态码</li>
 *   <li>提供友好的错误提示信息</li>
 * </ul>
 * 
 * @author noy
 * @version 1.0
 * @since 2024
 */
@Component("customAccessDeniedHandler")
public class CustomAccessDeniedHandler implements AccessDeniedHandler {

    /**
     * 处理访问拒绝异常
     * 
     * <p>当用户访问没有权限的资源时，返回统一格式的错误响应</p>
     * 
     * @param httpServletRequest HTTP请求对象
     * @param httpServletResponse HTTP响应对象
     * @param e 访问拒绝异常
     * @throws IOException IO异常
     * @throws ServletException Servlet异常
     */
    @Override
    public void handle(HttpServletRequest httpServletRequest,
                       HttpServletResponse httpServletResponse, AccessDeniedException e)
            throws IOException, ServletException {
        String res =  JSONObject.toJSONString(new ResultVo("无权限访问,请联系管理员!",700,null),
        SerializerFeature.DisableCircularReferenceDetect);
        // 设置返回格式
        httpServletResponse.setContentType("application/json;charset=UTF-8");
        ServletOutputStream out =
                httpServletResponse.getOutputStream();
        out.write(res.getBytes("UTF-8"));
        out.flush();
        out.close();
    }
}
