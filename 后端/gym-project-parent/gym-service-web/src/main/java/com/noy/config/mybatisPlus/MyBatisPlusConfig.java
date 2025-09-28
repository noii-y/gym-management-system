package com.noy.config.mybatisPlus;

// MyBatis Plus 相关
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;

// MyBatis Spring 相关
import org.mybatis.spring.annotation.MapperScan;

// Spring 框架相关
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * MyBatis Plus配置类
 * 
 * <p>配置MyBatis Plus的核心功能，包括：</p>
 * <ul>
 *   <li>自动扫描Mapper接口</li>
 *   <li>分页插件配置</li>
 *   <li>其他MyBatis Plus增强功能</li>
 * </ul>
 * 
 * @author noy
 * @version 1.0
 * @since 2024
 */
@Configuration  // Spring 配置类，Spring 容器会处理其中的 Bean 定义
@MapperScan("com.noy.*.*.mapper")  // MyBatis 扫描指定包路径下的接口作为 Mapper 接口
                                   // 自动为这些接口创建实现类并注册为 Spring Bean
                                   // 避免了在每个 Mapper 接口上单独添加 @Mapper 注解
public class MyBatisPlusConfig {

    /**
     * 配置MyBatis Plus拦截器
     * 
     * <p>添加分页插件，实现自动分页功能</p>
     * <ul>
     *   <li>无需手动编写分页SQL</li>
     *   <li>支持多种数据库的分页语法</li>
     *   <li>自动处理count查询</li>
     * </ul>
     * 
     * @return MybatisPlusInterceptor 配置好的拦截器实例
     */
    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor() {
        // MybatisPlusInterceptor是 MyBatis Plus的核心拦截器，用于添加各种功能插件
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        
        // 添加分页插件PaginationInnerInterceptor，启用 MyBatis Plus 的分页功能，无需手动编写分页 SQL
        interceptor.addInnerInterceptor(new PaginationInnerInterceptor());
        
        return interceptor;
    }
}
