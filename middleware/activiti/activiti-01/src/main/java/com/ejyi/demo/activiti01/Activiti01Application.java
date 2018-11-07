package com.ejyi.demo.activiti01;

import org.activiti.spring.boot.SecurityAutoConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/**
 * @author tree.yu
 * @version 1.0
 * @description 描述
 * @create 2018-11-04 22:25
 */
@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
public class Activiti01Application extends SpringBootServletInitializer {

    public static void main(String[] args) {
//        ConfigurableApplicationContext context = (new SpringApplicationBuilder()).sources(Activiti01Application.class)
//                .bannerMode(Banner.Mode.CONSOLE).logStartupInfo(true).registerShutdownHook(true).web(true).run(args);
        SpringApplication.run(Activiti01Application.class, args);


    }
}
