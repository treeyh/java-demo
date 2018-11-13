package com.ejyi.demo.activiti01;

import com.alibaba.fastjson.JSON;
import com.ejyi.demo.activiti01.bo.PersonBo;
import com.ejyi.demo.activiti01.cache.MyDeploymentCache;
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
import org.activiti.engine.identity.User;
import org.activiti.engine.impl.cfg.ProcessEngineConfigurationImpl;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.DeploymentBuilder;
import org.activiti.engine.repository.ProcessDefinition;
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
import java.util.List;
import java.util.UUID;
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

//        activitiProcessInstance(engine);
//        activitiGroupOper(engine);

//        activitiDeployment(engine);

//        activitiDeployment2(engine);

//        activitiProcessCache(engine);

//        activitiTaskCandidateUser(engine);

//        activitiTaskOwnerUser(engine);

//        activitiTaskVar(engine);

//        activitiTaskVarLocal(engine);

        activitiProcessInstanceStart(engine);
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
        Deployment deployment = repositoryService.createDeployment()
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
        //过滤重复发布
        db.enableDuplicateFiltering();
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


    /**
     * 流程部署缓存
     * @param engine
     */
    private static void activitiProcessCache(ProcessEngine engine){

        ProcessEngineConfiguration config1= engine.getProcessEngineConfiguration();
        System.out.println(config1.getClass().getTypeName());

        // 得到流程存储服务组件
        RepositoryService repositoryService = engine.getRepositoryService();
        // 得到运行时服务组件
        RuntimeService runtimeService = engine.getRuntimeService();
        // 获取流程任务组件
        TaskService taskService = engine.getTaskService();
        // 部署流程文件
        String id = "";
        Deployment deployment = null;
        for(int i = 0; i<10; i++) {
            deployment = repositoryService.createDeployment()
                    .addClasspathResource("bpmn/First.bpmn").name("process_"+i).key("key_"+i).deploy();
            id = deployment.getId();
        }

        ProcessEngineConfigurationImpl config = (ProcessEngineConfigurationImpl)engine.getProcessEngineConfiguration();

        //获取缓存
        MyDeploymentCache cache = (MyDeploymentCache) config.getProcessDefinitionCache();

        System.out.println(id);
        System.out.println(cache.getCache());
        System.out.println(JSON.toJSON(cache.get(id)));
    }

    /**
     * 任务关联候选用户
     * @param engine
     */
    private static void activitiTaskCandidateUser(ProcessEngine engine){
        TaskService ts = engine.getTaskService();
        IdentityService is = engine.getIdentityService();

        //创建任务，绑定用户
        String taskId = UUID.randomUUID().toString();
        Task task = ts.newTask(taskId);
        task.setName("测试候选任务");
        ts.saveTask(task);

        String userId = UUID.randomUUID().toString();
        User user = is.newUser(userId);
        user.setFirstName("Tree");
        is.saveUser(user);

        ts.addCandidateUser(taskId, userId);


        //查询用户有权限处理的任务
        List<Task> tasks = ts.createTaskQuery().taskCandidateOrAssigned(userId).list();

        System.out.println("用户有权限处理的任务：");
        for (Task t : tasks){
            System.out.println(t.getName());
        }
    }


    /**
     * 任务关联持有用户和代理用户
     * @param engine
     */
    private static void activitiTaskOwnerUser(ProcessEngine engine){
        TaskService ts = engine.getTaskService();
        IdentityService is = engine.getIdentityService();

        //创建任务，绑定用户
        String taskId = UUID.randomUUID().toString();
        Task task = ts.newTask(taskId);
        task.setName("测试持有任务");
        ts.saveTask(task);

        String userId = UUID.randomUUID().toString();
        User user = is.newUser(userId);
        user.setFirstName("Tree");
        is.saveUser(user);

        ts.setOwner(taskId, userId);

        //创建代理人，代理人只能设置一次
        ts.claim(taskId, userId);

        //查询用户有权限处理的任务
        List<Task> tasks = ts.createTaskQuery().taskOwner(userId).list();

        System.out.println("用户持有的任务：");
        for (Task t : tasks){
            System.out.println(t.getName());
        }

        tasks = ts.createTaskQuery().taskAssignee(userId).list();
        System.out.println("用户代理的任务：");
        for (Task t : tasks){
            System.out.println(t.getName());
        }
    }


    /**
     * 任务参数及附件
     * @param engine
     */
    private static void activitiTaskVar(ProcessEngine engine){
        TaskService taskService = engine.getTaskService();

        Task task = taskService.newTask(Long.valueOf(System.currentTimeMillis()).toString());
        task.setName("测试任务");
        taskService.saveTask(task);

        PersonBo personBo = new PersonBo();
        personBo.setId(1);
        personBo.setName("tree");

        taskService.setVariable(task.getId(), "var1", "hello world");
        taskService.setVariable(task.getId(), "person", personBo);

        System.out.println(taskService.getVariable(task.getId(), "var1"));
        PersonBo personBo1 = taskService.getVariable(task.getId(), "person", PersonBo.class);
        System.out.println(personBo1.getId() +"-"+ personBo1.getName());
    }


    /**
     * 任务本地参数
     * @param engine
     */
    private static void activitiTaskVarLocal(ProcessEngine engine){

        // 得到流程存储服务组件
        RepositoryService repositoryService = engine.getRepositoryService();
        // 得到运行时服务组件
        RuntimeService runtimeService = engine.getRuntimeService();
        // 获取流程任务组件
        TaskService taskService = engine.getTaskService();
        // 部署流程文件
        Deployment deployment = repositoryService.createDeployment()
                .addClasspathResource("bpmn/First.bpmn").deploy();
        // 启动流程
//        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("process1");

        ProcessDefinition pd = repositoryService.createProcessDefinitionQuery().deploymentId(deployment.getId()).singleResult();

        ProcessInstance processInstance = runtimeService.startProcessInstanceById(pd.getId());

        Task task2 = taskService.createTaskQuery().processInstanceId(processInstance.getId()).singleResult();
        taskService.setVariable(task2.getId(), "var", 1);
        taskService.setVariableLocal(task2.getId(), "varLocal", 3);
        System.out.println("========================================================================第一个任务完成前，当前任务名称：" + task2.getName()+"; var:"+taskService.getVariable(task2.getId(), "var")+"; varLocal:"+taskService.getVariableLocal(task2.getId(), "varLocal"));
        taskService.complete(task2.getId());

        task2 = taskService.createTaskQuery().processInstanceId(processInstance.getId()).singleResult();
        System.out.println("========================================================================第二个任务完成前，当前任务名称：" + task2.getName()+"; var:"+taskService.getVariable(task2.getId(), "var")+"; varLocal:"+taskService.getVariableLocal(task2.getId(), "varLocal"));

        taskService.complete(task2.getId());
        task2 = taskService.createTaskQuery().processInstanceId(processInstance.getId()).singleResult();
        System.out.println("========================================================================流程结束后，当前任务名称：" + task2);

    }


    /**
     * 流程启动
     * @param engine
     */
    private static void activitiProcessInstanceStart(ProcessEngine engine){
        // 得到流程存储服务组件
        RepositoryService repositoryService = engine.getRepositoryService();
        // 得到运行时服务组件
        RuntimeService runtimeService = engine.getRuntimeService();
        // 获取流程任务组件
        TaskService taskService = engine.getTaskService();
        // 部署流程文件
        Deployment deployment = repositoryService.createDeployment()
                .addClasspathResource("bpmn/First.bpmn").deploy();

        // 启动流程
        ProcessDefinition pd = repositoryService.createProcessDefinitionQuery().deploymentId(deployment.getId()).singleResult();

        ProcessInstance processInstance = runtimeService.startProcessInstanceById(pd.getId());

        System.out.println(processInstance.getId());
    }





}
