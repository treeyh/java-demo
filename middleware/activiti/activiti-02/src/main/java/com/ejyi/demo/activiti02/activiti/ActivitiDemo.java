package com.ejyi.demo.activiti02.activiti;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;

/**
 * @author 余海
 * @version 1.0
 * @description 描述
 * @create 2018-11-15 8:22 PM
 */
public class ActivitiDemo {


    /**
     * 定时器启动事件
     * @param engine
     */
    public static void timerProcessTask(ProcessEngine engine) throws InterruptedException {

        // 得到流程存储服务组件
        RepositoryService repositoryService = engine.getRepositoryService();
        // 得到运行时服务组件
        RuntimeService runtimeService = engine.getRuntimeService();
        // 获取流程任务组件
//        TaskService taskService = engine.getTaskService();
//        // 部署流程文件
//        Deployment deployment = repositoryService.createDeployment()
//                .addClasspathResource("bpmn/timer.bpmn20.xml").deploy();


        long count = runtimeService.createProcessInstanceQuery().count();
        System.out.println("启动流程数量："+count);


        Thread.sleep(50000L);

        count = runtimeService.createProcessInstanceQuery().count();
        System.out.println("启动流程数量："+count);
    }

    /**
     * 消息启动事件
     * @param engine
     */
    public static void messageProcessTask(ProcessEngine engine)  {

        // 得到流程存储服务组件
        RepositoryService repositoryService = engine.getRepositoryService();
        // 得到运行时服务组件
        RuntimeService runtimeService = engine.getRuntimeService();
        // 部署流程文件
        Deployment deployment = repositoryService.createDeployment()
                .addClasspathResource("bpmn/message.bpmn20.xml").deploy();


        ProcessInstance pi = runtimeService.startProcessInstanceByMessage("msgName");

        System.out.println(pi.getId());

        long count = runtimeService.createProcessInstanceQuery().count();
        System.out.println("启动流程数量："+count);

    }


    /**
     * 错误启动事件
     * @param engine
     */
    public static void errorProcessTask(ProcessEngine engine)  {

        // 得到流程存储服务组件
        RepositoryService repositoryService = engine.getRepositoryService();
        // 得到运行时服务组件
        RuntimeService runtimeService = engine.getRuntimeService();
        // 部署流程文件
        Deployment deployment = repositoryService.createDeployment()
                .addClasspathResource("bpmn/error.bpmn20.xml").deploy();

        ProcessDefinition pd = repositoryService.createProcessDefinitionQuery().deploymentId(deployment.getId()).singleResult();

        ProcessInstance pi = runtimeService.startProcessInstanceById(pd.getId());

        System.out.println(pi.getId());

        long count = runtimeService.createProcessInstanceQuery().count();
        System.out.println("启动流程数量："+count);

    }
}
