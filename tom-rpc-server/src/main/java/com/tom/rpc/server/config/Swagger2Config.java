package com.tom.rpc.server.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author tom.tang
 * @date 2018/4/20
 * @email tom.tang@sainstore.com
 * @description
 * @since 2018/4/20
 */
@Configuration
@EnableSwagger2
public class Swagger2Config {
    @Bean
    public Docket config(ApiInfo apiInfo) {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo)
                .useDefaultResponseMessages(false)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.tom.data.server.controller"))
                .build();
    }

    @Bean
    public ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("tom-data-rpc系统API文档")
                .contact(new Contact("", "访问地址", "联系方式"))
                .build();
    }
}
