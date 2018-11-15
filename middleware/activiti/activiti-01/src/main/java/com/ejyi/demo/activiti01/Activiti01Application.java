package com.ejyi.demo.activiti01;

import com.ejyi.demo.activiti01.activiti.ActivitiDemo;
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
 * @create 2018-11-04 22:25
 */
@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
public class Activiti01Application extends SpringBootServletInitializer {


    public static void main(String[] args) throws FileNotFoundException {
        ConfigurableApplicationContext context = (new SpringApplicationBuilder()).sources(Activiti01Application.class)
                .bannerMode(Banner.Mode.CONSOLE).logStartupInfo(true).registerShutdownHook(true).web(true).run(args);

        System.out.println(context.getBean(ProcessEngineConfiguration.class).getDatabaseType());



        // 创建流程引擎
        ProcessEngine engine = ProcessEngines.getDefaultProcessEngine();

//        ActivitiDemo.activitiProcessInstance(engine);
//        ActivitiDemo.activitiGroupOper(engine);

//        ActivitiDemo.activitiDeployment(engine);

//        ActivitiDemo.activitiDeployment2(engine);

//        ActivitiDemo.activitiProcessCache(engine);

//        ActivitiDemo.activitiTaskCandidateUser(engine);

//        ActivitiDemo.activitiTaskOwnerUser(engine);

//        ActivitiDemo.activitiTaskVar(engine);

//        ActivitiDemo.activitiTaskVarLocal(engine);

//        ActivitiDemo.activitiProcessInstanceStart(engine);

//        ActivitiDemo.activitiProcessInstanceStart2(engine);

//        ActivitiDemo.activitiProcessInstanceOper(engine);

//        ActivitiDemo.activitiCandidateQuery(engine);

//        ActivitiDemo.activitiProcessInstanceDelete(engine);

        ActivitiDemo.activitiProcessInstanceAsyncTask(engine);



    }



}
