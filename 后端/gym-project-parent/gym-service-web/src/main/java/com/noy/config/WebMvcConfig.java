package com.noy.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 解决跨域问题的配置类
 * 跨域：浏览器出于安全考虑，限制网页从一个域名访问另一个域名的资源
 * 例如：前端运行在localhost:3000，后端API在localhost:8080，就会出现跨域问题
 */
@Configuration // 告诉Spring这是一个配置类，启动时会自动加载其中的配置
public class WebMvcConfig implements WebMvcConfigurer {

    /**
     * 配置跨域访问规则
     * 这个方法会在Spring启动时被自动调用，设置CORS(跨域资源共享)规则
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        // 添加跨域映射配置
        registry.addMapping("/**")          // 1. 对所有接口路径生效(例如:/api/user, /api/role等)
                .allowedOriginPatterns("*") // 2. 允许所有来源的请求访问(域名/IP+端口)
                .allowedMethods("*")        // 3. 允许所有HTTP方法(GET,POST,PUT,DELETE等)
                .allowedHeaders("*")        // 4. 允许所有请求头信息
                .maxAge(3600)               // 5. 预检请求的缓存时间(秒)，减少OPTIONS请求次数
                .allowCredentials(true);    // 6. 允许前端携带认证信息(如cookies)

        // 简单来说：这个配置告诉浏览器"任何网站都可以用任何方式访问我的所有API"
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry)
    {
        // MinIO的URL是 http://localhost:9000
        // 静态资源访问路径是 /gym/**
        registry.addResourceHandler("/gym/**")
                .addResourceLocations("http://localhost:9000/gym/");
    }
}