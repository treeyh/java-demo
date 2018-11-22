package com.ejyi.demo.activiti02.activiti;

import com.ejyi.demo.activiti02.activiti.delegate.JavaServiceDelegate;
import org.activiti.engine.IdentityService;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.identity.Group;
import org.activiti.engine.identity.User;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.Execution;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 余海
 * @version 1.0
 * @description 描述
 * @create 2018-11-15 8:22 PM
 */
public class ActivitiDemo {


    /**
     * 定时器启动事件
     *
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
        System.out.println("启动流程数量：" + count);


        Thread.sleep(50000L);

        count = runtimeService.createProcessInstanceQuery().count();
        System.out.println("启动流程数量：" + count);
    }

    /**
     * 消息启动事件
     *
     * @param engine
     */
    public static void messageStartProcessTask(ProcessEngine engine) {

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
        System.out.println("启动流程数量：" + count);

    }


    /**
     * 错误启动事件
     *
     * @param engine
     */
    public static void errorStartProcessTask(ProcessEngine engine) {

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
        System.out.println("启动流程数量：" + count);

    }


    /**
     * 错误结束事件
     *
     * @param engine
     */
    public static void errorEndProcessTask(ProcessEngine engine) {

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
        System.out.println("启动流程数量：" + count);
    }


    /**
     * 取消结束事件
     *
     * @param engine
     */
    public static void cancelEndProcessTask(ProcessEngine engine) {

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
        System.out.println("启动流程数量：" + count);

        Task task = taskService.createTaskQuery().processInstanceId(pi.getId()).singleResult();
        System.out.println("当前流程任务：" + task.getName());

        taskService.complete(task.getId());

        task = taskService.createTaskQuery().processInstanceId(pi.getId()).singleResult();
        System.out.println("当前流程任务：" + task.getName());
    }


    /**
     * 终止结束事件
     *
     * @param engine
     */
    public static void terminalEndProcessTask(ProcessEngine engine) {

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
        System.out.println("启动流程数量：" + count);

        List<Task> tasks = taskService.createTaskQuery().processInstanceId(pi.getId()).list();

        for (Task task : tasks) {
            System.out.println("任务名称：" + task.getName());
            if ("Task1".equals(task.getName())) {
                taskService.complete(task.getId());
            }
        }

        count = runtimeService.createProcessInstanceQuery().count();
        System.out.println("启动流程数量：" + count);

        ProcessInstance pi2 = runtimeService.createProcessInstanceQuery().processInstanceId(pi.getId()).singleResult();
        System.out.println(pi2);

    }


    /**
     * 计时边界事件事件
     *
     * @param engine
     */
    public static void timerBoundaryProcessTask(ProcessEngine engine) {

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
        System.out.println("启动流程数量：" + count);

        List<Task> tasks = taskService.createTaskQuery().processInstanceId(pi.getId()).list();

        for (Task task : tasks) {
            System.out.println("任务名称1：" + task.getName());
        }

        try {
            Thread.sleep(70000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        count = runtimeService.createProcessInstanceQuery().count();
        System.out.println("启动流程数量：" + count);


        tasks = taskService.createTaskQuery().processInstanceId(pi.getId()).list();

        for (Task task : tasks) {
            System.out.println("任务名称2：" + task.getName());
        }

    }


    /**
     * 信号边界事件事件
     *
     * @param engine
     */
    public static void signalBoundaryProcessTask(ProcessEngine engine) {
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
        System.out.println("启动流程数量：" + count);

        List<Task> tasks = taskService.createTaskQuery().processInstanceId(pi.getId()).list();

        for (Task task : tasks) {
            System.out.println("任务名称1：" + task.getName());
            taskService.complete(task.getId());
        }

        tasks = taskService.createTaskQuery().processInstanceId(pi.getId()).list();
        for (Task task : tasks) {
            System.out.println("任务名称2：" + task.getName());
        }
        runtimeService.signalEventReceived("contactChangeSignalName");

        count = runtimeService.createProcessInstanceQuery().count();
        System.out.println("启动流程数量：" + count);

        tasks = taskService.createTaskQuery().processInstanceId(pi.getId()).list();
        for (Task task : tasks) {
            System.out.println("任务名称3：" + task.getName());
        }
    }


    /**
     * 定时器中间事件
     *
     * @param engine
     */
    public static void timerMiddleEventProcessTask(ProcessEngine engine) {

        engine.getProcessEngineConfiguration().setAsyncExecutorActivate(true);

        // 得到流程存储服务组件
        RepositoryService repositoryService = engine.getRepositoryService();
        // 得到运行时服务组件
        RuntimeService runtimeService = engine.getRuntimeService();

        TaskService taskService = engine.getTaskService();
        // 部署流程文件
        Deployment deployment = repositoryService.createDeployment()
                .addClasspathResource("bpmn/timer_middle_event.bpmn20.xml").deploy();

        ProcessDefinition pd = repositoryService.createProcessDefinitionQuery().deploymentId(deployment.getId()).singleResult();

        ProcessInstance pi = runtimeService.startProcessInstanceById(pd.getId());
        System.out.println(pi.getId());

        long count = runtimeService.createProcessInstanceQuery().count();
        System.out.println("启动流程数量：" + count);

        Task task = taskService.createTaskQuery().processInstanceId(pi.getId()).singleResult();


        System.out.println("任务名称1：" + task.getName());
        taskService.complete(task.getId());

        count = runtimeService.createProcessInstanceQuery().count();
        System.out.println("启动流程数量：" + count);

        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        task = taskService.createTaskQuery().processInstanceId(pi.getId()).singleResult();

        System.out.println("任务名称3：" + task.getName());
    }


    /**
     * 定时器中间事件
     *
     * @param engine
     */
    public static void signalMiddleEventProcessTask(ProcessEngine engine) {

        engine.getProcessEngineConfiguration().setAsyncExecutorActivate(true);

        // 得到流程存储服务组件
        RepositoryService repositoryService = engine.getRepositoryService();
        // 得到运行时服务组件
        RuntimeService runtimeService = engine.getRuntimeService();

        TaskService taskService = engine.getTaskService();
        // 部署流程文件
        Deployment deployment = repositoryService.createDeployment()
                .addClasspathResource("bpmn/user_pay.bpmn20.xml").deploy();

        ProcessDefinition pd = repositoryService.createProcessDefinitionQuery().deploymentId(deployment.getId()).singleResult();

        ProcessInstance pi = runtimeService.startProcessInstanceById(pd.getId());
        System.out.println(pi.getId());


        Task task = taskService.createTaskQuery().processInstanceId(pi.getId()).singleResult();
        System.out.println("任务名称1：" + task.getName());

        taskService.complete(task.getId());


        List<Task> tasks = taskService.createTaskQuery().processInstanceId(pi.getId()).list();

        for (Task t : tasks) {
            System.out.println("任务名称2：" + t.getName());
        }
    }


    /**
     * 补偿中间事件
     *
     * @param engine
     */
    public static void compensationMiddleEventProcessTask(ProcessEngine engine) {

        engine.getProcessEngineConfiguration().setAsyncExecutorActivate(true);

        // 得到流程存储服务组件
        RepositoryService repositoryService = engine.getRepositoryService();
        // 得到运行时服务组件
        RuntimeService runtimeService = engine.getRuntimeService();

        TaskService taskService = engine.getTaskService();
        // 部署流程文件
        Deployment deployment = repositoryService.createDeployment()
                .addClasspathResource("bpmn/compensation.bpmn20.xml").deploy();

        ProcessDefinition pd = repositoryService.createProcessDefinitionQuery().deploymentId(deployment.getId()).singleResult();

        ProcessInstance pi = runtimeService.startProcessInstanceById(pd.getId());
        System.out.println(pi.getId());

    }


    /**
     * 定义候选用户和用户组
     *
     * @param engine
     */
    public static void candidateProcessTask(ProcessEngine engine) {

        engine.getProcessEngineConfiguration().setAsyncExecutorActivate(true);

        // 得到流程存储服务组件
        RepositoryService repositoryService = engine.getRepositoryService();
        // 得到运行时服务组件
        RuntimeService runtimeService = engine.getRuntimeService();

        IdentityService is = engine.getIdentityService();

        TaskService taskService = engine.getTaskService();

        Group group1 = createGroup(is, "boss", "bossName", "boss");
        Group group2 = createGroup(is, "management", "managementName", "management");
        User user1 = createUser(is, "angus", "angusName", "last", "cr@111.com", "123123");

        // 部署流程文件
        Deployment deployment = repositoryService.createDeployment()
                .addClasspathResource("bpmn/candidate.bpmn20.xml").deploy();

        ProcessDefinition pd = repositoryService.createProcessDefinitionQuery().deploymentId(deployment.getId()).singleResult();


        ProcessInstance pi = runtimeService.startProcessInstanceById(pd.getId());
        System.out.println(pi.getId());

        List<Task> tasks = taskService.createTaskQuery().taskCandidateGroup("boss").list();
        System.out.println("boss任务数量：" + tasks.size());
        System.out.println("boss任务名：" + tasks.get(0).getName());

        tasks = taskService.createTaskQuery().taskCandidateUser("angus").list();
        System.out.println("angus任务数量：" + tasks.size());
        System.out.println("angus任务名：" + tasks.get(0).getName());

        tasks = taskService.createTaskQuery().taskCandidateGroup("management").list();
        System.out.println("management任务数量：" + tasks.size());
        System.out.println("management任务名：" + tasks.get(0).getName());

    }

    /**
     * 通过juel定义候选用户和用户组
     *
     * @param engine
     */
    public static void juelCandidateProcessTask(ProcessEngine engine) {

        engine.getProcessEngineConfiguration().setAsyncExecutorActivate(true);

        // 得到流程存储服务组件
        RepositoryService repositoryService = engine.getRepositoryService();
        // 得到运行时服务组件
        RuntimeService runtimeService = engine.getRuntimeService();

        IdentityService is = engine.getIdentityService();

        TaskService taskService = engine.getTaskService();

        User user1 = createUser(is, "userA", "userAName", "last", "cr@111.com", "123123");
        User user2 = createUser(is, "userB", "userBName", "lastB", "cr@222.com", "123123");

        // 部署流程文件
        Deployment deployment = repositoryService.createDeployment()
                .addClasspathResource("bpmn/juel.bpmn20.xml").deploy();

        ProcessDefinition pd = repositoryService.createProcessDefinitionQuery().deploymentId(deployment.getId()).singleResult();

        Map<String, Object> vars = new HashMap<>();
        vars.put("authService", new AuthService());

        ProcessInstance pi = runtimeService.startProcessInstanceById(pd.getId(), vars);
        System.out.println(pi.getId());

        List<Task> tasks = taskService.createTaskQuery().taskCandidateUser("userA").list();
        System.out.println("userA任务数量：" + tasks.size());
        System.out.println("userA任务名：" + tasks.get(0).getName());

        tasks = taskService.createTaskQuery().taskCandidateUser("userB").list();
        System.out.println("userB任务数量：" + tasks.size());
        System.out.println("userB任务名：" + tasks.get(0).getName());

    }

    /**
     * java service task定义
     *
     * @param engine
     */
    public static void javaServiceTaskProcessTask(ProcessEngine engine) {

        engine.getProcessEngineConfiguration().setAsyncExecutorActivate(true);

        // 得到流程存储服务组件
        RepositoryService repositoryService = engine.getRepositoryService();
        // 得到运行时服务组件
        RuntimeService runtimeService = engine.getRuntimeService();

        IdentityService is = engine.getIdentityService();

        TaskService taskService = engine.getTaskService();

        // 部署流程文件
        Deployment deployment = repositoryService.createDeployment()
                .addClasspathResource("bpmn/JavaServiceTask.bpmn20.xml").deploy();

        ProcessDefinition pd = repositoryService.createProcessDefinitionQuery().deploymentId(deployment.getId()).singleResult();

        Map<String, Object> vars = new HashMap<>();
        vars.put("javaServiceDelegate", new JavaServiceDelegate());

        ProcessInstance pi = runtimeService.startProcessInstanceById(pd.getId(), vars);
        System.out.println(pi.getId());
    }

    /**
     * expression java service task定义
     *
     * @param engine
     */
    public static void expressionServiceTaskProcessTask(ProcessEngine engine) {

        engine.getProcessEngineConfiguration().setAsyncExecutorActivate(true);

        // 得到流程存储服务组件
        RepositoryService repositoryService = engine.getRepositoryService();
        // 得到运行时服务组件
        RuntimeService runtimeService = engine.getRuntimeService();

        IdentityService is = engine.getIdentityService();

        TaskService taskService = engine.getTaskService();

        // 部署流程文件
        Deployment deployment = repositoryService.createDeployment()
                .addClasspathResource("bpmn/bean.bpmn20.xml").deploy();

        ProcessDefinition pd = repositoryService.createProcessDefinitionQuery().deploymentId(deployment.getId()).singleResult();

        Map<String, Object> vars = new HashMap<>();
        vars.put("expressionBean", new ExpressionBean());

        ProcessInstance pi = runtimeService.startProcessInstanceById(pd.getId(), vars);
        System.out.println(pi.getId());

        System.out.println("属性值：" + runtimeService.getVariable(pi.getId(), "myName"));
    }

    /**
     * java shell task
     *
     * @param engine
     */
    public static void javaShellTask(ProcessEngine engine) {

        List<String> argList = new ArrayList<>();
        argList.add("ls");
        argList.add("-a");

        ProcessBuilder processBuilder = new ProcessBuilder(argList);
        try {
            Process process = processBuilder.start();

            InputStream is = process.getInputStream();
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            int i;
            while ((i = is.read()) != -1) {
                baos.write(i);
            }
            String str = baos.toString();
            System.out.println(str);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * listener user task定义
     *
     * @param engine
     */
    public static void listenerUserTaskProcessTask(ProcessEngine engine) {

        engine.getProcessEngineConfiguration().setAsyncExecutorActivate(true);

        // 得到流程存储服务组件
        RepositoryService repositoryService = engine.getRepositoryService();
        // 得到运行时服务组件
        RuntimeService runtimeService = engine.getRuntimeService();

        IdentityService is = engine.getIdentityService();

        TaskService taskService = engine.getTaskService();

        // 部署流程文件
        Deployment deployment = repositoryService.createDeployment()
                .addClasspathResource("bpmn/listener.bpmn20.xml").deploy();

        ProcessDefinition pd = repositoryService.createProcessDefinitionQuery().deploymentId(deployment.getId()).singleResult();

        ProcessInstance pi = runtimeService.startProcessInstanceById(pd.getId());
        System.out.println(pi.getId());

        Task task = taskService.createTaskQuery().processInstanceId(pi.getId()).singleResult();

        System.out.println(task.getId());
        User user1 = createUser(is, "userListenerUserTaskProcessTask", "userAName", "last", "cr@111.com", "123123");

        taskService.setAssignee(task.getId(), user1.getId());
        System.out.println(task.getId());
        taskService.complete(task.getId());
    }

    /**
     * userTask 注入属性
     *
     * @param engine
     */
    public static void fieldUserTaskProcessTask(ProcessEngine engine) {

        engine.getProcessEngineConfiguration().setAsyncExecutorActivate(true);

        // 得到流程存储服务组件
        RepositoryService repositoryService = engine.getRepositoryService();
        // 得到运行时服务组件
        RuntimeService runtimeService = engine.getRuntimeService();

        IdentityService is = engine.getIdentityService();

        TaskService taskService = engine.getTaskService();

        // 部署流程文件
        Deployment deployment = repositoryService.createDeployment()
                .addClasspathResource("bpmn/field.bpmn20.xml").deploy();

        ProcessDefinition pd = repositoryService.createProcessDefinitionQuery().deploymentId(deployment.getId()).singleResult();

        Map<String, Object> vars = new HashMap<>();
        vars.put("expressionBean", new ExpressionBean());

        ProcessInstance pi = runtimeService.startProcessInstanceById(pd.getId(), vars);
        System.out.println(pi.getId());

        Task task = taskService.createTaskQuery().processInstanceId(pi.getId()).singleResult();

        System.out.println(task.getId());
        User user1 = createUser(is, "fieldUserTaskProcessTask1", "userAName", "last", "cr@111.com", "123123");

        taskService.setAssignee(task.getId(), user1.getId());
        System.out.println(task.getId());
        taskService.complete(task.getId());
    }

    /**
     * 嵌入式子流程
     *
     * @param engine
     */
    public static void embededSubProcess(ProcessEngine engine) {

        engine.getProcessEngineConfiguration().setAsyncExecutorActivate(true);

        // 得到流程存储服务组件
        RepositoryService repositoryService = engine.getRepositoryService();
        // 得到运行时服务组件
        RuntimeService runtimeService = engine.getRuntimeService();

        IdentityService is = engine.getIdentityService();

        TaskService taskService = engine.getTaskService();

        // 部署流程文件
        Deployment deployment = repositoryService.createDeployment()
                .addClasspathResource("bpmn/EmbededSubProcess.bpmn20.xml").deploy();

        ProcessDefinition pd = repositoryService.createProcessDefinitionQuery().deploymentId(deployment.getId()).singleResult();

        ProcessInstance pi = runtimeService.startProcessInstanceById(pd.getId());
        System.out.println(pi.getId());

        Task task = taskService.createTaskQuery().processInstanceId(pi.getId()).singleResult();

        System.out.println("当前流程任务：" + task.getName());
    }

    /**
     * 调用其他流程文件子流程
     *
     * @param engine
     */
    public static void callSubProcess(ProcessEngine engine) {

        engine.getProcessEngineConfiguration().setAsyncExecutorActivate(true);

        // 得到流程存储服务组件
        RepositoryService repositoryService = engine.getRepositoryService();
        // 得到运行时服务组件
        RuntimeService runtimeService = engine.getRuntimeService();

        IdentityService is = engine.getIdentityService();

        TaskService taskService = engine.getTaskService();

        // 部署流程文件
        Deployment deployment = repositoryService.createDeployment()
                .addClasspathResource("bpmn/call_activiti.bpmn20.xml").addClasspathResource("bpmn/off_work.bpmn20.xml").deploy();

        ProcessDefinition pd = repositoryService.createProcessDefinitionQuery().deploymentId(deployment.getId()).processDefinitionKey("off_work").singleResult();

        ProcessInstance pi = runtimeService.startProcessInstanceById(pd.getId());
        System.out.println(pi.getId());

        Task task = taskService.createTaskQuery().processInstanceId(pi.getId()).singleResult();

        System.out.println("当前流程任务1：" + task.getName());
        taskService.complete(task.getId());

        ProcessInstance pi2 = runtimeService.createProcessInstanceQuery().superProcessInstanceId(pi.getId()).singleResult();
        task = taskService.createTaskQuery().processInstanceId(pi2.getId()).singleResult();
        System.out.println("当前流程任务2：" + task.getName());

    }

    /**
     * 特殊子流程
     *
     * @param engine
     */
    public static void sequentialSubProcess(ProcessEngine engine) {

        engine.getProcessEngineConfiguration().setAsyncExecutorActivate(true);

        // 得到流程存储服务组件
        RepositoryService repositoryService = engine.getRepositoryService();
        // 得到运行时服务组件
        RuntimeService runtimeService = engine.getRuntimeService();

        IdentityService is = engine.getIdentityService();

        TaskService taskService = engine.getTaskService();

        // 部署流程文件
        Deployment deployment = repositoryService.createDeployment()
                .addClasspathResource("bpmn/special_process.bpmn20.xml").deploy();

        ProcessDefinition pd = repositoryService.createProcessDefinitionQuery().deploymentId(deployment.getId()).singleResult();

        ProcessInstance pi = runtimeService.startProcessInstanceById(pd.getId());
        System.out.println("流程id：" + pi.getId());

        Execution execution = runtimeService.createExecutionQuery().processInstanceId(pi.getId()).activityId("sid-F58F3201-BD12-44B4-9028-279E35F954CB").singleResult();

        System.out.println("executionId:" + execution.getId());

        //让流程到达任务2
        runtimeService.executeActivityInAdhocSubProcess(execution.getId(), "sid-FD9835FA-8DC4-4040-955B-0D43334BB990");
        // 查找任务2并完成
        Task subTask = taskService.createTaskQuery().processInstanceId(pi.getId()).taskDefinitionKey("sid-FD9835FA-8DC4-4040-955B-0D43334BB990").singleResult();
        taskService.complete(subTask.getId());
        System.out.println("完成任务：" + subTask.getName());

        // 完成特别子流程
        runtimeService.completeAdhocSubProcess(execution.getId());

        Task task = taskService.createTaskQuery().processInstanceId(pi.getId()).singleResult();
        System.out.println("当前任务：" + task.getName());

    }


    /**
     * 将用户组数据保存到数据库中
     *
     * @param identityService
     * @param id
     * @param name
     * @param type
     * @return
     */
    private static Group createGroup(IdentityService identityService, String id,
                                     String name, String type) {
        // 调用newGroup方法创建Group实例
        Group group = identityService.newGroup(id);
        group.setName(name);
        group.setType(type);
        identityService.saveGroup(group);
        return identityService.createGroupQuery().groupId(id).singleResult();
    }

    /**
     * 创建用户方法
     *
     * @param identityService
     * @param id
     * @param first
     * @param last
     * @param email
     * @param passwd
     * @return
     */
    private static User createUser(IdentityService identityService, String id, String first,
                                   String last, String email, String passwd) {
        // 使用newUser方法创建User实例
        User user = identityService.newUser(id);
        // 设置用户的各个属性
        user.setFirstName(first);
        user.setLastName(last);
        user.setEmail(email);
        user.setPassword(passwd);
        // 使用saveUser方法保存用户
        identityService.saveUser(user);
        return identityService.createUserQuery().userId(id).singleResult();
    }

}
