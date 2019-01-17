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



    public static void createDeployment(ProcessEngine engine){
        engine.getProcessEngineConfiguration().setAsyncExecutorActivate(true);

        // 得到流程存储服务组件
        RepositoryService repositoryService = engine.getRepositoryService();
        // 得到运行时服务组件
        RuntimeService runtimeService = engine.getRuntimeService();

        IdentityService is = engine.getIdentityService();

        TaskService taskService = engine.getTaskService();

        // 部署流程文件
        Deployment deployment = repositoryService.createDeployment()
                .addClasspathResource("bpmn/CompensateProcess.bpmn20.xml").deploy();

        System.out.println("createDeployment over:" + deployment.getId()+"---"+deployment.getName());
    }


    public static ProcessDefinition getProcessDefinition(ProcessEngine engine){


        engine.getProcessEngineConfiguration().setAsyncExecutorActivate(true);

        // 得到流程存储服务组件
        RepositoryService repositoryService = engine.getRepositoryService();
        // 得到运行时服务组件
        RuntimeService runtimeService = engine.getRuntimeService();

        IdentityService is = engine.getIdentityService();

        TaskService taskService = engine.getTaskService();

        ProcessDefinition pd = repositoryService.createProcessDefinitionQuery().processDefinitionKey("CompensateProcess").active().latestVersion().singleResult();

        System.out.println("getProcessDefinition..ProcessDefinition.id:"+pd.getId() + ";ProcessDefinition.name:"+pd.getName());

        return pd;
    }



    /**
     * 自定义用户
     *
     * @param engine
     */
    public static void startProcessInstance(ProcessEngine engine) {

        engine.getProcessEngineConfiguration().setAsyncExecutorActivate(true);

        // 得到流程存储服务组件
        RepositoryService repositoryService = engine.getRepositoryService();
        // 得到运行时服务组件
        RuntimeService runtimeService = engine.getRuntimeService();

        IdentityService is = engine.getIdentityService();

        TaskService taskService = engine.getTaskService();



        ProcessDefinition pd = getProcessDefinition(engine);

        System.out.println("ProcessDefinition.id:" + pd.getId());


        Map<String , Object> vars = new HashMap<>();
        vars.put("classHours", 15);
        vars.put("adminUserId", 1);
        vars.put("roleId", 1);

        ProcessInstance pi = runtimeService.startProcessInstanceById(pd.getId(), vars);
        System.out.println("ProcessInstance:"+pi.getId());

        Task task = taskService.createTaskQuery().processInstanceId(pi.getId()).singleResult();

        System.out.println(task.getName());
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

        Task task = taskService.createTaskQuery().processInstanceId("2501").singleResult();

        System.out.println("customUserCheck..taskId:"+task.getId()+";taskAssignee:"+task.getAssignee()+";taskName:"+
                task.getName()+";taskDefinitionKey:"+task.getTaskDefinitionKey());

        Map<String , Object> vars = new HashMap<>();
        vars.put(ActivitiConstants.checkResult, 1);

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

        List<Task> tasks = taskService.createTaskQuery().taskCandidateOrAssigned("6").list();

        System.out.println(tasks.size());

        for(Task task : tasks) {
            System.out.println("getTasksByUser..taskId:"+task.getId()+";taskAssignee:"+task.getAssignee()+";taskName:"+
                    task.getName()+";taskProcessInstanceId:"+task.getProcessInstanceId());
        }
    }




    public static void checkProcessStatus(ProcessEngine engine){

        engine.getProcessEngineConfiguration().setAsyncExecutorActivate(true);

        // 得到流程存储服务组件
        RepositoryService repositoryService = engine.getRepositoryService();
        // 得到运行时服务组件
        RuntimeService runtimeService = engine.getRuntimeService();

        IdentityService is = engine.getIdentityService();

        TaskService taskService = engine.getTaskService();

        ProcessInstance processInstance = runtimeService.createProcessInstanceQuery().processInstanceId("2501").singleResult(); //25001


        System.out.println("checkProcessStatus..processInstanceId:"+processInstance.getId());
    }



}
