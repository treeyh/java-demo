package com.ejyi.demo.activiti02.activiti;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;

import java.util.List;

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
    public static void timerStartProcessTask(ProcessEngine engine) throws InterruptedException {

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
    public static void messageStartProcessTask(ProcessEngine engine)  {

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
    public static void errorStartProcessTask(ProcessEngine engine)  {

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



    /**
     * 错误结束事件
     * @param engine
     */
    public static void errorEndProcessTask(ProcessEngine engine)  {

        // 得到流程存储服务组件
        RepositoryService repositoryService = engine.getRepositoryService();
        // 得到运行时服务组件
        RuntimeService runtimeService = engine.getRuntimeService();
        // 部署流程文件
        Deployment deployment = repositoryService.createDeployment()
                .addClasspathResource("bpmn/error2.bpmn20.xml").deploy();

        ProcessDefinition pd = repositoryService.createProcessDefinitionQuery().deploymentId(deployment.getId()).singleResult();

        ProcessInstance pi = runtimeService.startProcessInstanceById(pd.getId());

        System.out.println(pi.getId());

        long count = runtimeService.createProcessInstanceQuery().count();
        System.out.println("启动流程数量："+count);
    }


    /**
     * 取消结束事件
     * @param engine
     */
    public static void cancelEndProcessTask(ProcessEngine engine)  {

        // 得到流程存储服务组件
        RepositoryService repositoryService = engine.getRepositoryService();
        // 得到运行时服务组件
        RuntimeService runtimeService = engine.getRuntimeService();

        TaskService taskService = engine.getTaskService();
        // 部署流程文件
        Deployment deployment = repositoryService.createDeployment()
                .addClasspathResource("bpmn/transation.bpmn20.xml").deploy();

        ProcessDefinition pd = repositoryService.createProcessDefinitionQuery().deploymentId(deployment.getId()).singleResult();

        ProcessInstance pi = runtimeService.startProcessInstanceById(pd.getId());
        System.out.println(pi.getId());

        long count = runtimeService.createProcessInstanceQuery().count();
        System.out.println("启动流程数量："+count);

        Task task = taskService.createTaskQuery().processInstanceId(pi.getId()).singleResult();
        System.out.println("当前流程任务："+task.getName());

        taskService.complete(task.getId());

        task = taskService.createTaskQuery().processInstanceId(pi.getId()).singleResult();
        System.out.println("当前流程任务："+task.getName());
    }


    /**
     * 终止结束事件
     * @param engine
     */
    public static void terminalEndProcessTask(ProcessEngine engine)  {

        // 得到流程存储服务组件
        RepositoryService repositoryService = engine.getRepositoryService();
        // 得到运行时服务组件
        RuntimeService runtimeService = engine.getRuntimeService();

        TaskService taskService = engine.getTaskService();
        // 部署流程文件
        Deployment deployment = repositoryService.createDeployment()
                .addClasspathResource("bpmn/terminate_end.bpmn20.xml").deploy();

        ProcessDefinition pd = repositoryService.createProcessDefinitionQuery().deploymentId(deployment.getId()).singleResult();

        ProcessInstance pi = runtimeService.startProcessInstanceById(pd.getId());
        System.out.println(pi.getId());

        long count = runtimeService.createProcessInstanceQuery().count();
        System.out.println("启动流程数量："+count);

        List<Task> tasks = taskService.createTaskQuery().processInstanceId(pi.getId()).list();

        for(Task task : tasks){
            System.out.println("任务名称："+task.getName());
            if("Task1".equals(task.getName())){
                taskService.complete(task.getId());
            }
        }

        count = runtimeService.createProcessInstanceQuery().count();
        System.out.println("启动流程数量："+count);

        ProcessInstance pi2 = runtimeService.createProcessInstanceQuery().processInstanceId(pi.getId()).singleResult();
        System.out.println(pi2);

    }


    /**
     * 计时边界事件事件
     * @param engine
     */
    public static void timerBoundaryProcessTask(ProcessEngine engine)  {

//        engine.getProcessEngineConfiguration().setAsyncExecutorActivate(true);

        // 得到流程存储服务组件
        RepositoryService repositoryService = engine.getRepositoryService();
        // 得到运行时服务组件
        RuntimeService runtimeService = engine.getRuntimeService();

        TaskService taskService = engine.getTaskService();
        // 部署流程文件
        Deployment deployment = repositoryService.createDeployment()
                .addClasspathResource("bpmn/timer_boundary.bpmn20.xml").deploy();

        ProcessDefinition pd = repositoryService.createProcessDefinitionQuery().deploymentId(deployment.getId()).singleResult();

        ProcessInstance pi = runtimeService.startProcessInstanceById(pd.getId());
        System.out.println(pi.getId());

        long count = runtimeService.createProcessInstanceQuery().count();
        System.out.println("启动流程数量："+count);

        List<Task> tasks = taskService.createTaskQuery().processInstanceId(pi.getId()).list();

        for(Task task : tasks){
            System.out.println("任务名称1："+task.getName());
        }

        try {
            Thread.sleep(70000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        count = runtimeService.createProcessInstanceQuery().count();
        System.out.println("启动流程数量："+count);


        tasks = taskService.createTaskQuery().processInstanceId(pi.getId()).list();

        for(Task task : tasks){
            System.out.println("任务名称2："+task.getName());
        }

    }


    /**
     * 信号边界事件事件
     * @param engine
     */
    public static void signalBoundaryProcessTask(ProcessEngine engine)  {

//        engine.getProcessEngineConfiguration().setAsyncExecutorActivate(true);

        // 得到流程存储服务组件
        RepositoryService repositoryService = engine.getRepositoryService();
        // 得到运行时服务组件
        RuntimeService runtimeService = engine.getRuntimeService();

        TaskService taskService = engine.getTaskService();
        // 部署流程文件
        Deployment deployment = repositoryService.createDeployment()
                .addClasspathResource("bpmn/signal.bpmn20.xml").deploy();

        ProcessDefinition pd = repositoryService.createProcessDefinitionQuery().deploymentId(deployment.getId()).singleResult();

        ProcessInstance pi = runtimeService.startProcessInstanceById(pd.getId());
        System.out.println(pi.getId());

        long count = runtimeService.createProcessInstanceQuery().count();
        System.out.println("启动流程数量："+count);

        List<Task> tasks = taskService.createTaskQuery().processInstanceId(pi.getId()).list();

        for(Task task : tasks){
            System.out.println("任务名称1："+task.getName());
            taskService.complete(task.getId());
        }

        tasks = taskService.createTaskQuery().processInstanceId(pi.getId()).list();
        for(Task task : tasks){
            System.out.println("任务名称2："+task.getName());
        }
        runtimeService.signalEventReceived("contactChangeSignalName");

        count = runtimeService.createProcessInstanceQuery().count();
        System.out.println("启动流程数量："+count);

        tasks = taskService.createTaskQuery().processInstanceId(pi.getId()).list();
        for(Task task : tasks){
            System.out.println("任务名称3："+task.getName());
        }

    }
}
