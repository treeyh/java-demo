package com.ejyi.demo.springboot.base.demo;

import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.Banner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;

@MapperScan("com.ejyi.demo.springboot.base.demo.dao")
@SpringBootApplication
@EnableSwagger2
public class WebApplication extends SpringBootServletInitializer {
    private static final Logger logger = LoggerFactory.getLogger(WebApplication.class);

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return configureApplication(builder);
    }


    private static SpringApplicationBuilder configureApplication(SpringApplicationBuilder builder) {
        return builder.sources(WebApplication.class).bannerMode(Banner.Mode.CONSOLE).registerShutdownHook(true).web(true);
    }

    public static void main(String[] args) {

        logger.info("Application start....");
        HashMap<String, String> map = new HashMap<>();
        ConcurrentHashMap<String, String> map2 = new ConcurrentHashMap<>();

        // http://127.0.0.1:8081/swagger-ui.html
        configureApplication(new SpringApplicationBuilder()).run(args);
        logger.info("Application end....");

    }

    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.ejyi.demo.springboot.base.demo.controller"))
                .paths(PathSelectors.any())
                .build();
    }
    private ApiInfo apiInfo() {

        return new ApiInfoBuilder()
                .title("title")
                .description("description")
                .version("1.0")
                .build();
    }

}
