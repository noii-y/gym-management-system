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

/** 认证失败处理器：统一根据异常类型返回 JSON 错误响应 */
@Component("loginFailureHandler")
public class LoginFailureHandler implements AuthenticationEntryPoint {

    /** 处理认证失败并输出响应 */
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
