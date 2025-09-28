package com.noy.config.swagger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

/**
 * Swagger API文档配置类
 * 用于生成在线API文档，方便前后端开发人员查看接口信息
 * 
 * @author noy
 * @version 1.0
 */
@Configuration
public class SwaggerConfig {

    /**
     * 配置Swagger Docket对象
     * Docket是Swagger中的全局配置对象，用于定义API文档的生成规则
     * 
     * @return Docket 配置好的Docket对象
     */
    @Bean
    public Docket getDocket() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(swaggerApiInfo())
                .select()
                // 可以通过以下配置指定扫描的包或路径
                // .apis(RequestHandlerSelectors.basePackage("com.noy.controller"))
                // .apis(not(withMethodAnnotation(NoIncludeSwagger.class)))
                // .paths(or(PathSelectors.regex("/user/.*"), PathSelectors.regex("/people/.*")))
                .build();
    }

    /**
     * 配置API文档的基本信息
     * 包括文档标题、描述、版本号、联系人信息等
     * 
     * @return ApiInfo API文档信息对象
     */
    private ApiInfo swaggerApiInfo() {
        return new ApiInfoBuilder()
                .contact(new Contact("健身房管理系统", "https://github.com/noy", "2803038543@qq.com"))
                .title("健身房管理系统 API文档")
                .description("提供健身房管理系统的完整API接口文档，包括会员管理、课程管理、器材管理等功能模块")
                .version("1.0.0")
                .build();
    }
}
