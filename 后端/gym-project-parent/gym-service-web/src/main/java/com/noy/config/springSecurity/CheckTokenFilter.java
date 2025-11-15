package com.noy.config.springSecurity;

// Java 标准库
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Map;

// Servlet API
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// JWT 相关
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;

// Apache Commons 工具类
import org.apache.commons.lang.StringUtils;

// Spring 框架相关
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;


// 项目内部类
import com.noy.jwt.JwtUtils;

/** JWT 过滤器：校验 Token 并设置安全上下文，支持忽略 URL */
@Component("checkTokenFilter")
public class CheckTokenFilter extends OncePerRequestFilter {

    /**
     * 忽略Token验证的URL列表
     * 从配置文件中读取，这些URL不需要进行Token验证
     */
    @Value("#{'${ignore.url}'.split(',')}")
    private List<String> ignoreUrl = Collections.emptyList();
    
    /**
     * JWT工具类
     * 用于Token的生成、验证和解析
     */
    @Autowired
    private JwtUtils jwtUtils;
    
    /**
     * 自定义用户详情服务
     * 用于根据用户名加载用户信息
     */
    @Autowired
    private CustomizedUserDetailService customizedUserDetailService;
    
    /**
     * 登录失败处理器
     * 处理Token验证失败的情况
     */
    @Autowired
    private LoginFailureHandler loginFailureHandler;

    /** 过滤器核心方法 */
    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response, 
                                    FilterChain filterChain) throws ServletException, IOException {
        try {
            // 获取请求的URL
            String url = request.getRequestURI();
            
            // 判断是否需要进行Token验证（排除忽略列表中的URL和静态资源）
            if (!ignoreUrl.contains(url) && !url.startsWith("/images/")) {
                validateToken(request);
            }
        } catch (AuthenticationException e) {
            // Token验证失败，交给失败处理器处理
            loginFailureHandler.commence(request, response, e);
            return;
        }
        
        // 验证通过，继续执行后续过滤器
        filterChain.doFilter(request, response);
    }

    /** 验证 Token 并设置认证信息 */
    private void validateToken(HttpServletRequest request) {
        // 获取Token数据（优先从请求头获取）
        String token = request.getHeader("token");
        
        // 如果从头部获取Token失败，那么从参数获取
        if (StringUtils.isEmpty(token)) {
            token = request.getParameter("token");
        }
        
        // 如果没有获取到Token
        if (StringUtils.isEmpty(token)) {
            throw new CustomAuthenticationException("token获取失败！");
        }
        
        // Token格式和签名验证
        if (!jwtUtils.verify(token)) {
            throw new CustomAuthenticationException("token验证失败！");
        }
        
        // 解析Token，获取用户信息
        DecodedJWT decodedJWT = jwtUtils.jwtDecode(token);
        Map<String, Claim> claims = decodedJWT.getClaims();
        String username = claims.get("username").asString();
        String userType = claims.get("userType").asString();
        
        // 查询用户详细信息，交给Spring Security管理
        UserDetails details = customizedUserDetailService.loadUserByUsername(username + ":" + userType);
        
        // 构造认证Token
        UsernamePasswordAuthenticationToken authenticationToken = 
                new UsernamePasswordAuthenticationToken(details, null, details.getAuthorities());
        authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        
        // 设置到Spring Security上下文中
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
    }
}
