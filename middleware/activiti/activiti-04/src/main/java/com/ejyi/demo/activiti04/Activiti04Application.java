package com.ejyi.demo.activiti04;

import com.ejyi.demo.activiti04.activiti.ActivitiDemo;
import com.ejyi.demo.activiti04.activiti.manager.CustomGroupEntityManager;
import com.ejyi.demo.activiti04.activiti.manager.CustomUserEntityManager;
import jdk.nashorn.internal.runtime.regexp.joni.ast.EncloseNode;
import org.activiti.engine.IdentityService;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngineConfiguration;
import org.activiti.engine.ProcessEngines;
import org.activiti.spring.SpringProcessEngineConfiguration;
import org.activiti.spring.boot.SecurityAutoConfiguration;
import org.springframework.boot.Banner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

import java.io.FileNotFoundException;

/**
 * @author 余海
 * @version 1.0
 * @description 描述
 * @create 2018-12-05 10:44 AM
 */
@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
public class Activiti04Application {

    public static void main(String[] args) throws FileNotFoundException, InterruptedException {
        ConfigurableApplicationContext context = (new SpringApplicationBuilder()).sources(Activiti04Application.class)
                .bannerMode(Banner.Mode.CONSOLE).logStartupInfo(true).registerShutdownHook(true).web(true).run(args);

        SpringProcessEngineConfiguration springProcessEngineConfiguration = (SpringProcessEngineConfiguration) context.getBean(ProcessEngineConfiguration.class);
        springProcessEngineConfiguration.setGroupEntityManager(new CustomGroupEntityManager(springProcessEngineConfiguration, springProcessEngineConfiguration.getGroupDataManager()));
        springProcessEngineConfiguration.setUserEntityManager(new CustomUserEntityManager(springProcessEngineConfiguration, springProcessEngineConfiguration.getUserDataManager()));




        // 创建流程引擎
        ProcessEngine engine = ProcessEngines.getDefaultProcessEngine();

//        ActivitiDemo.customUser(engine);
        ActivitiDemo.customUserCheck(engine);

    }



}
