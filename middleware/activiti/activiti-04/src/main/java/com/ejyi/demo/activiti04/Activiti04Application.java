package com.ejyi.demo.activiti04;

import com.ejyi.demo.activiti04.activiti.ActivitiDemo;
import com.ejyi.demo.activiti04.activiti.manager.CustomGroupEntityManager;
import com.ejyi.demo.activiti04.activiti.manager.CustomUserEntityManager;
import com.ejyi.demo.activiti04.dao.RolePoMapper;
import com.ejyi.demo.activiti04.dao.RoleUserPoMapper;
import com.ejyi.demo.activiti04.dao.UserPoMapper;
import jdk.nashorn.internal.runtime.regexp.joni.ast.EncloseNode;
import org.activiti.engine.IdentityService;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngineConfiguration;
import org.activiti.engine.ProcessEngines;
import org.activiti.spring.SpringProcessEngineConfiguration;
import org.activiti.spring.boot.SecurityAutoConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
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

        UserPoMapper userPoMapper = context.getBean(UserPoMapper.class);
        RolePoMapper rolePoMapper = context.getBean(RolePoMapper.class);
        RoleUserPoMapper roleUserPoMapper = context.getBean(RoleUserPoMapper.class);

        SpringProcessEngineConfiguration springProcessEngineConfiguration = (SpringProcessEngineConfiguration) context.getBean(ProcessEngineConfiguration.class);
        springProcessEngineConfiguration.setGroupEntityManager(new CustomGroupEntityManager(springProcessEngineConfiguration, springProcessEngineConfiguration.getGroupDataManager(),userPoMapper, rolePoMapper, roleUserPoMapper));
        springProcessEngineConfiguration.setUserEntityManager(new CustomUserEntityManager(springProcessEngineConfiguration, springProcessEngineConfiguration.getUserDataManager(), userPoMapper, rolePoMapper, roleUserPoMapper));





        // 创建流程引擎
        ProcessEngine engine = ProcessEngines.getDefaultProcessEngine();


//        ActivitiDemo.createDeployment(engine);

//        ActivitiDemo.getProcessDefinition(engine);
//
//        ActivitiDemo.startProcessInstance(engine);
//
        ActivitiDemo.customUserCheck(engine);
//
//        ActivitiDemo.getTasksByUser(engine);
//
//        ActivitiDemo.checkProcessStatus(engine);

    }



}
