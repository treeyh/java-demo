package com.ejyi.demo.activiti02;

import com.ejyi.demo.activiti02.activiti.ActivitiDemo;
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
 * @author 余海
 * @version 1.0
 * @description 描述
 * @create 2018-11-15 8:22 PM
 */
@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
public class Activiti02Application extends SpringBootServletInitializer {


    public static void main(String[] args) throws FileNotFoundException, InterruptedException {
        ConfigurableApplicationContext context = (new SpringApplicationBuilder()).sources(Activiti02Application.class)
                .bannerMode(Banner.Mode.CONSOLE).logStartupInfo(true).registerShutdownHook(true).web(true).run(args);

        System.out.println(context.getBean(ProcessEngineConfiguration.class).getDatabaseType());



        // 创建流程引擎
        ProcessEngine engine = ProcessEngines.getDefaultProcessEngine();

//        ActivitiDemo.timerStartProcessTask(engine);

//        ActivitiDemo.messageStartProcessTask(engine);

//        ActivitiDemo.errorStartProcessTask(engine);

//        ActivitiDemo.errorEndProcessTask(engine);

//        ActivitiDemo.cancelEndProcessTask(engine);

//        ActivitiDemo.terminalEndProcessTask(engine);

//        ActivitiDemo.timerBoundaryProcessTask(engine);

//        ActivitiDemo.signalBoundaryProcessTask(engine);

//        ActivitiDemo.timerMiddleEventProcessTask(engine);

//        ActivitiDemo.signalMiddleEventProcessTask(engine);

//        ActivitiDemo.compensationMiddleEventProcessTask(engine);

//        ActivitiDemo.candidateProcessTask(engine);

//        ActivitiDemo.juelCandidateProcessTask(engine);

//        ActivitiDemo.javaServiceTaskProcessTask(engine);

//        ActivitiDemo.expressionServiceTaskProcessTask(engine);

//        ActivitiDemo.javaShellTask(engine);

//        ActivitiDemo.listenerUserTaskProcessTask(engine);

//        ActivitiDemo.fieldUserTaskProcessTask(engine);

//        ActivitiDemo.embededSubProcess(engine);

//        ActivitiDemo.callSubProcess(engine);

        ActivitiDemo.sequentialSubProcess(engine);

    }





}
