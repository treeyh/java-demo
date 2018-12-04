package com.ejyi.demo.activiti03;

import com.ejyi.demo.activiti03.drools.DroolsDemo;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngineConfiguration;
import org.activiti.engine.ProcessEngines;
import org.activiti.spring.boot.SecurityAutoConfiguration;
import org.springframework.boot.Banner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.ConfigurableApplicationContext;

import java.io.FileNotFoundException;

/**
 * @author tree.yu
 * @version 1.0
 * @description 描述
 * @create 2018-11-25 21:37
 */

@SpringBootApplication(exclude = SecurityAutoConfiguration.class, scanBasePackages = {"com.ejyi.demo"})
public class Activiti03Application extends SpringBootServletInitializer {


    public static void main(String[] args) throws FileNotFoundException, InterruptedException {
        ConfigurableApplicationContext context = (new SpringApplicationBuilder()).sources(Activiti03Application.class)
                .bannerMode(Banner.Mode.CONSOLE).logStartupInfo(true).registerShutdownHook(true).web(true).run(args);

        System.out.println(context.getBean(ProcessEngineConfiguration.class).getDatabaseType());


        DroolsDemo droolsDemo = context.getBean(DroolsDemo.class);

        // 创建流程引擎
//        ProcessEngine engine = ProcessEngines.getDefaultProcessEngine();

        droolsDemo.personTestDrools();


        System.out.println("end...");


    }




}
