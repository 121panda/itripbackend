package com.ytzl.itrip.auth.controller;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 *
 * Created by Administrator on 2018/4/20 0020.
 */
@EnableSwagger2
@ComponentScan(basePackages = {"com.ytzl.itrip.auth.controller"})
@Configuration // bean节点
public class SwaggerConfig extends WebMvcConfigurationSupport {
    @Bean
    public Docket createDocket() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(getApiInfo()) // 获取api的一系列信息
                .select() // 扫描哪个路径下的哪些api
                .apis(RequestHandlerSelectors.any()) // 监控所有的api
                .paths(PathSelectors.any()) // 监控所有的api路径
                .build();
    }

    /**
     * 获取api信息
     * @return
     */
    private ApiInfo getApiInfo() {
        // 构建一个api信息
        /*
        * title ：api标题
        * contact ：相关链接
        * version：版本
        *
        * */
        return new ApiInfoBuilder().title("爱旅行项目Auth授权模块")
                .contact(new Contact("程小金", "https://gitee.com/bjscpq/itripbackend.git", "121xiongmao@sina.com"))
                .version("V1.0").build();
    }
}
