package com.ejyi.demo.activiti01;

import com.alibaba.fastjson.JSON;
import org.activiti.bpmn.model.BpmnModel;
import org.activiti.bpmn.model.EndEvent;
import org.activiti.bpmn.model.SequenceFlow;
import org.activiti.bpmn.model.StartEvent;
import org.activiti.bpmn.model.UserTask;
import org.activiti.engine.IdentityService;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngineConfiguration;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.repository.DeploymentBuilder;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.activiti.spring.boot.SecurityAutoConfiguration;
import org.springframework.boot.Banner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.ConfigurableApplicationContext;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.zip.ZipInputStream;

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

        activitiProcessInstance(engine);
        activitiGroupOper(engine);

//        activitiDeployment(engine);

//        activitiDeployment2(engine);
    }

    /**
     * 流程例子
     * @param engine
     */
    private static void activitiProcessInstance(ProcessEngine engine){

        // 得到流程存储服务组件
        RepositoryService repositoryService = engine.getRepositoryService();
        // 得到运行时服务组件
        RuntimeService runtimeService = engine.getRuntimeService();
        // 获取流程任务组件
        TaskService taskService = engine.getTaskService();
        // 部署流程文件
        repositoryService.createDeployment()
                .addClasspathResource("bpmn/First.bpmn").deploy();
        // 启动流程
        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("process1");

        //Demo 流程
//        // 查询第一个任务
//        Task task = taskService.createTaskQuery().singleResult();
//        System.out.println("========================================================================第一个任务完成前，当前任务名称：" + task.getName());
//        // 完成第一个任务
//        taskService.complete(task.getId());
//        // 查询第二个任务
//        task = taskService.createTaskQuery().singleResult();
//        System.out.println("========================================================================第二个任务完成前，当前任务名称：" + task.getName());
//        // 完成第二个任务（流程结束）
//        taskService.complete(task.getId());
//        task = taskService.createTaskQuery().singleResult();
//        System.out.println("========================================================================流程结束后，查找任务：" + task);


        Task task2 = taskService.createTaskQuery().processInstanceId(processInstance.getId()).singleResult();
        System.out.println("========================================================================第一个任务完成前，当前任务名称：" + task2.getName());
        taskService.complete(task2.getId());

        task2 = taskService.createTaskQuery().processInstanceId(processInstance.getId()).singleResult();
        System.out.println("========================================================================第二个任务完成前，当前任务名称：" + task2.getName());
        taskService.complete(task2.getId());
        task2 = taskService.createTaskQuery().processInstanceId(processInstance.getId()).singleResult();
        System.out.println("========================================================================流程结束后，当前任务名称：" + task2);

    }

    /**
     * 用户组相关操作
     * @param engine
     */
    private static void activitiGroupOper(ProcessEngine engine){
        IdentityService is = engine.getIdentityService();
//        // 创建用户组
//        Group group = is.newGroup("1");
//        group.setName("测试用户组");
//        group.setType("manager");
//        is.saveGroup(group);
//
//        Group data = is.createGroupQuery().groupId("1").singleResult();
//        System.out.println(JSON.toJSON(data));
//        data.setName("测试用户组3");
//        is.saveGroup(data);
//        System.out.println(JSON.toJSON(is.createGroupQuery().count()));
//        System.out.println(JSON.toJSON(is.createGroupQuery().orderByGroupId().desc().list()));

//        is.deleteGroup("1");
        System.out.println(JSON.toJSON(is.createGroupQuery().count()));

        System.out.println(JSON.toJSON(is.createGroupQuery().orderByGroupId().desc().listPage(0,1)));
        System.out.println(JSON.toJSON(is.createGroupQuery().orderByGroupId().desc().listPage(1,1)));
        System.out.println(JSON.toJSON(is.createGroupQuery().orderByGroupId().desc().listPage(2,1)));

        System.out.println(JSON.toJSON(is.createGroupQuery().groupName("测试用户组3").list()));

        System.out.println(JSON.toJSON(is.createGroupQuery().groupNameLike("测试用户%").list()));

        System.out.println(JSON.toJSON(is.createNativeGroupQuery().sql("select * from ACT_ID_GROUP where NAME_ = #{name}").parameter("name", "测试用户组3").list()));
    }


    /**
     * 部署流程文档
     * @param engine
     * @throws FileNotFoundException
     */
    private static void activitiDeployment(ProcessEngine engine) throws FileNotFoundException {
        RepositoryService rs = engine.getRepositoryService();

        DeploymentBuilder db = rs.createDeployment();

        FileInputStream fis = new FileInputStream(new File("/home/tree/work/99_tree/03_github/java-demo/middleware/activiti/activiti-01/src/main/resources/bpmn/test.zip"));
        ZipInputStream zis = new ZipInputStream(fis);

        db.addZipInputStream(zis);
        db.deploy();
    }

    /**
     * 部署流程文档2
     * @param engine
     */
    private static void activitiDeployment2(ProcessEngine engine){

        RepositoryService rs = engine.getRepositoryService();
        DeploymentBuilder db = rs.createDeployment();

        BpmnModel bm = new BpmnModel();

        db.addBpmnModel("MyCodeProcess", createProcessModel()).name("MyCodeDeploy").deploy();


    }

    /**
     * 创建BpmnModel
     * @return
     */
    private static BpmnModel createProcessModel() {
        // 创建BPMN模型对象
        BpmnModel model = new BpmnModel();
        org.activiti.bpmn.model.Process process = new org.activiti.bpmn.model.Process();
        model.addProcess(process);
        process.setId("myProcess");
        process.setName("My Process");
        // 开始事件
        StartEvent startEvent = new StartEvent();
        startEvent.setId("startEvent");
        process.addFlowElement(startEvent);
        // 用户任务
        UserTask userTask = new UserTask();
        userTask.setName("User Task");
        userTask.setId("userTask");
        process.addFlowElement(userTask);
        // 结束事件
        EndEvent endEvent = new EndEvent();
        endEvent.setId("endEvent");
        process.addFlowElement(endEvent);
        // 添加流程顺序
        process.addFlowElement(new SequenceFlow("startEvent", "userTask"));
        process.addFlowElement(new SequenceFlow("userTask", "endEvent"));
        return model;
    }



}
