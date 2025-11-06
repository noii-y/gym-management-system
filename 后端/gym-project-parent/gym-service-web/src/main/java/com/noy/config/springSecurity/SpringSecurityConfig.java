package com.noy.config.springSecurity;

// Spring 框架相关
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// Spring Security 相关
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * Spring Security安全配置类
 * 
 * <p>配置系统的安全策略，包括：</p>
 * <ul>
 *   <li>HTTP请求的访问控制</li>
 *   <li>密码加密策略</li>
 *   <li>用户认证和授权</li>
 *   <li>JWT Token过滤器</li>
 *   <li>异常处理器</li>
 * </ul>
 * 
 * @author noy
 * @version 1.0
 * @since 2024
 */
@Configuration
@EnableWebSecurity  // 启用Spring Security Web安全功能
@EnableGlobalMethodSecurity(prePostEnabled = true)  // 开启Spring Security注解的使用权限
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

    /**
     * 登录失败处理器
     * 处理认证失败的情况
     */
    @Autowired
    private LoginFailureHandler loginFailureHandler;
    
    /**
     * 自定义访问拒绝处理器
     * 处理权限不足的情况
     */
    @Autowired
    private CustomAccessDeniedHandler customAccessDeniedHandler;
    
    /**
     * Token检查过滤器
     * 验证JWT Token的有效性
     */
    @Autowired
    CheckTokenFilter checkTokenFilter;

    /**
     * 配置HTTP安全策略
     * 
     * <p>定义哪些请求需要认证，哪些可以直接访问</p>
     * 
     * @param http HttpSecurity配置对象
     * @throws Exception 配置异常
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // 解决跨域问题
        http.cors().and().headers().frameOptions().disable();
        
        // 关闭跨域请求伪造保护
        http.csrf().disable().authorizeRequests()
                // 放行登录、验证码、首页统计、课程列表以及开放注册接口
                .antMatchers(
                        "/api/login/login", 
                        "/api/login/image", 
                        "/api/login/getMenuList", 
                        "/api/login/getInfo", 
                        "/api/home/getTotal", 
                        "/api/home/getHotCourse", 
                        "/api/home/getHotGoods", 
                        "/api/home/getHotCards", 
                        "/api/course/list",
                        "/api/member",
                        "/api/user",
                        "/api/role/getSelect"
                ).permitAll()
                // 其他的任何接口访问都需要认证
                .anyRequest().authenticated()
                .and().exceptionHandling()
                .authenticationEntryPoint(loginFailureHandler)
                .accessDeniedHandler(customAccessDeniedHandler);
        
        // 配置token过滤器，在用户名密码认证过滤器之前执行
        http.addFilterBefore(checkTokenFilter, UsernamePasswordAuthenticationFilter.class);
    }

    /**
     * 密码加密器配置
     * 
     * <p>使用BCrypt算法对密码进行加密</p>
     * 
     * @return PasswordEncoder BCrypt密码加密器
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * 自定义用户详情服务
     * 用于从数据库加载用户信息
     */
    @Autowired
    CustomizedUserDetailService customizedUserDetailService;

    /**
     * 配置自定义的UserDetailService
     * 
     * <p>告诉Spring Security使用我们自定义的用户详情服务</p>
     * 
     * @param auth 认证管理器构建器
     * @throws Exception 配置异常
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(customizedUserDetailService);
    }

    /**
     * 注入AuthenticationManager
     * 
     * <p>将认证管理器注册为Spring Bean，供其他组件使用</p>
     * 
     * @return AuthenticationManager 认证管理器
     * @throws Exception 配置异常
     */
    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}
