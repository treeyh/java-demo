package com.ejyi.demo.activiti04.activiti;

import jdk.nashorn.internal.ir.RuntimeNode;
import org.activiti.engine.IdentityService;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.identity.Group;
import org.activiti.engine.identity.User;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 余海
 * @version 1.0
 * @description 描述
 * @create 2018-12-05 10:44 AM
 */
public class ActivitiDemo {




    /**
     * 自定义用户
     *
     * @param engine
     */
    public static void customUser(ProcessEngine engine) {

        engine.getProcessEngineConfiguration().setAsyncExecutorActivate(true);

        // 得到流程存储服务组件
        RepositoryService repositoryService = engine.getRepositoryService();
        // 得到运行时服务组件
        RuntimeService runtimeService = engine.getRuntimeService();

        IdentityService is = engine.getIdentityService();

        TaskService taskService = engine.getTaskService();


        // 部署流程文件
        Deployment deployment = repositoryService.createDeployment()
                .addClasspathResource("bpmn/process.bpmn20.xml").deploy();

        ProcessDefinition pd = repositoryService.createProcessDefinitionQuery().deploymentId(deployment.getId()).singleResult();

        System.out.println("deployment.getId():"+deployment.getId());
        List<Deployment> deployments = repositoryService.createDeploymentQuery().list();
        System.out.println("Deployment.size:" + deployments.size());


        Map<String , Object> vars = new HashMap<>();
        vars.put("classHours", 15);
        vars.put("adminUserId", 1);
        vars.put("roleId", 1);

        ProcessInstance pi = runtimeService.startProcessInstanceById(pd.getId(), vars);
        System.out.println("ProcessInstance:"+pi.getId());



        Task task = taskService.createTaskQuery().processInstanceId(pi.getId()).singleResult();

        System.out.println(task.getName());

        taskService.setAssignee(task.getId(), "1");

        System.out.println(task.getAssignee());

        taskService.complete(task.getId());

    }


    public static void customUserCheck(ProcessEngine engine){

        engine.getProcessEngineConfiguration().setAsyncExecutorActivate(true);

        // 得到流程存储服务组件
        RepositoryService repositoryService = engine.getRepositoryService();
        // 得到运行时服务组件
        RuntimeService runtimeService = engine.getRuntimeService();

        IdentityService is = engine.getIdentityService();

        TaskService taskService = engine.getTaskService();


        Task task = taskService.createTaskQuery().processInstanceId("32505").singleResult();

        System.out.println(task.getId());
        System.out.println(task.getAssignee());

        Map<String , Object> vars = new HashMap<>();
        vars.put("checkResult", 1);

        taskService.complete(task.getId(), vars);
    }


    public static void getTasksByUser(ProcessEngine engine){

        engine.getProcessEngineConfiguration().setAsyncExecutorActivate(true);

        // 得到流程存储服务组件
        RepositoryService repositoryService = engine.getRepositoryService();
        // 得到运行时服务组件
        RuntimeService runtimeService = engine.getRuntimeService();

        IdentityService is = engine.getIdentityService();

        TaskService taskService = engine.getTaskService();


        List<Task> tasks = taskService.createTaskQuery().taskCandidateOrAssigned("2").list();

        for(Task task : tasks) {
            System.out.println(task.getId());
            System.out.println(task.getAssignee());
        }


    }



}
