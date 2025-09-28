package com.noy.config.springSecurity;

// Java 标准库
import java.io.IOException;

// Servlet API
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// FastJSON 相关
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;

// Spring Security 相关
import org.springframework.security.authentication.*;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

// 项目内部类
import com.noy.utils.ResultVo;

/**
 * Spring Security 认证失败处理器
 * 
 * <p>处理用户认证失败的情况，包括：</p>
 * <ul>
 *   <li>账户过期、密码过期</li>
 *   <li>账户被禁用、账户被锁定</li>
 *   <li>用户名或密码错误</li>
 *   <li>账户不存在</li>
 *   <li>权限不足等各种认证异常</li>
 * </ul>
 * 
 * <p>根据不同的异常类型返回相应的错误信息</p>
 * 
 * @author noy
 * @version 1.0
 * @since 2024
 */
@Component("loginFailureHandler")
public class LoginFailureHandler implements AuthenticationEntryPoint {

    /**
     * 处理认证失败的情况
     * 
     * <p>根据不同的认证异常类型，返回相应的错误信息和状态码</p>
     * 
     * @param httpServletRequest HTTP请求对象
     * @param httpServletResponse HTTP响应对象
     * @param e 认证异常对象
     * @throws IOException IO异常
     * @throws ServletException Servlet异常
     */
    @Override
    public void commence(HttpServletRequest httpServletRequest,
                         HttpServletResponse httpServletResponse, 
                         AuthenticationException e) throws IOException, ServletException {
        int code = 500;
        String str = "";
        
        // 根据异常类型设置相应的错误信息
        if (e instanceof AccountExpiredException) {
            str = "账户过期，登录失败!";
        } else if (e instanceof BadCredentialsException) {
            str = "用户名或密码错误，登录失败!";
        } else if (e instanceof CredentialsExpiredException) {
            str = "密码过期，登录失败!";
        } else if (e instanceof DisabledException) {
            str = "账户被禁用，登录失败!";
        } else if (e instanceof LockedException) {
            str = "账户被锁，登录失败!";
        } else if (e instanceof InternalAuthenticationServiceException) {
            str = "账户不存在，登录失败!";
        } else if (e instanceof CustomAuthenticationException) {
            code = 600;
            str = e.getMessage();
        } else if (e instanceof InsufficientAuthenticationException) {
            str = "无权限访问资源!";
        } else {
            str = "登录失败!";
        }
        
        // 构建JSON响应
        String res = JSONObject.toJSONString(new ResultVo(str, code, null),
                SerializerFeature.DisableCircularReferenceDetect);
        
        // 设置响应格式
        httpServletResponse.setContentType("application/json;charset=UTF-8");
        ServletOutputStream out = httpServletResponse.getOutputStream();
        out.write(res.getBytes("UTF-8"));
        out.flush();
        out.close();
    }
}
