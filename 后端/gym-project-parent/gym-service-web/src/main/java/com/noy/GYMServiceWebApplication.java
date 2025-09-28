package com.noy;

// Spring Boot 相关
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// Swagger 相关
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * 健身房管理系统Web服务启动类
 * 
 * <p>这是Spring Boot应用程序的主入口点，负责启动整个健身房管理系统</p>
 * 
 * <p>系统功能模块包括：</p>
 * <ul>
 *   <li>会员管理 - 会员注册、信息维护、会员卡管理</li>
 *   <li>课程管理 - 课程发布、预约、教练安排</li>
 *   <li>器材管理 - 器材信息、维护记录、使用统计</li>
 *   <li>商品管理 - 商品销售、库存管理、订单处理</li>
 *   <li>系统管理 - 用户权限、菜单配置、系统设置</li>
 *   <li>失物招领 - 失物登记、认领管理</li>
 *   <li>建议反馈 - 用户建议收集和处理</li>
 * </ul>
 * 
 * @author noy
 * @version 1.0
 * @since 2024
 */
@SpringBootApplication  // Spring Boot 自动配置注解
@EnableSwagger2         // 开启Swagger2，自动扫描所有控制器生成API接口文档
public class GYMServiceWebApplication {
    
    /**
     * 应用程序主入口方法
     * 
     * @param args 命令行参数
     */
    public static void main(String[] args) {
        SpringApplication.run(GYMServiceWebApplication.class, args);
    }
}
