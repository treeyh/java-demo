package com.ejyi.demo.springboot.shardingjdbc;


import com.ejyi.demo.springboot.shardingjdbc.service.DemoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.Banner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.mybatis.spring.annotation.MapperScan;


@MapperScan("com.ejyi.demo.springboot.shardingjdbc.mapper")
@SpringBootApplication(scanBasePackages = {"com.ejyi.demo.springboot.shardingjdbc"})
@Configuration
public class WebApplication  extends SpringBootServletInitializer {

    private static final Logger logger = LoggerFactory.getLogger(WebApplication.class);


    // war启动入口
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return configureApplication(builder);
    }


    private static SpringApplicationBuilder configureApplication(SpringApplicationBuilder builder) {
        return builder.sources(WebApplication.class).bannerMode(Banner.Mode.CONSOLE).registerShutdownHook(true).web(true);
    }

    public static void main(String[] args) {

        logger.info("Application start....");
        ApplicationContext applicationContext = configureApplication(new SpringApplicationBuilder()).run(args);
        applicationContext.getBean(DemoService.class).demoConfig();
        logger.info("Application end....");
    }
}
