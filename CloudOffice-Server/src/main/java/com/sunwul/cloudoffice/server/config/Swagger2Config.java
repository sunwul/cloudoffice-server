package com.sunwul.cloudoffice.server.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

/*****
 * @author sunwul
 * @date 2021/3/21 16:10
 * @description swagger配置类
 * - @EnableSwagger2 开启swagger
 */
@EnableSwagger2
@Configuration
public class Swagger2Config {

    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.sunwul.cloudoffice.server.controller"))
//                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build()
                .securityContexts(securityContexts())
                .securitySchemes(securitySchemes());
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("CloudOffice接口文档")
                .description("CloudOffice接口文档_sunwul")
                .contact(new Contact("sunwul", "http://localhost:8082/doc.html", "1404512053@qq.com"))
                .termsOfServiceUrl("doc.html")
                .version("1.0")
                .build();
    }

    private List<SecurityContext> securityContexts() {
        // 设置需要登录认证的路径
        List<SecurityContext> result = new ArrayList<>();
        result.add(getContextByPath("/test/.*"));
        return result;
    }

    /**
     * 根据路径获取Context
     *
     * @param pathRegex 正则匹配
     * @return SecurityContext
     */
    private SecurityContext getContextByPath(String pathRegex) {
        return SecurityContext.builder()
                .securityReferences(defaultAuth())
                .forPaths(PathSelectors.regex(pathRegex))
                .build();
    }

    /**
     * 设置默认授权
     *
     * @return 授权
     */
    private List<SecurityReference> defaultAuth() {
        List<SecurityReference> result = new ArrayList<>();
        // 授权范围
        AuthorizationScope authorizationScope = new AuthorizationScope("global", "全局");
        // SecurityReference()方法要求必须要添加数组，所以创建scopes数组
        AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
        authorizationScopes[0] = authorizationScope;
        result.add(new SecurityReference("Authorization", authorizationScopes));
        return result;
    }

    private List<ApiKey> securitySchemes() {
        // 设置请求头信息
        List<ApiKey> result = new ArrayList<>();
        // 参数key_name需要注意使用Authorization
        ApiKey apiKey = new ApiKey("token", "Authorization", "Header");
        result.add(apiKey);
        return result;
    }
}
