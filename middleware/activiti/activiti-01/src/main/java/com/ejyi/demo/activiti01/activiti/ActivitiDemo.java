package com.ejyi.demo.activiti01.activiti;

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
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.identity.Group;
import org.activiti.engine.identity.User;
import org.activiti.engine.impl.cfg.ProcessEngineConfigurationImpl;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.DeploymentBuilder;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.Execution;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.IdentityLink;
import org.activiti.engine.task.Task;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.zip.ZipInputStream;

/**
 * @author 余海
 * @version 1.0
 * @description 描述
 * @create 2018-11-14 11:13 AM
 */
public class ActivitiDemo {


    /**
     * 流程例子
     * @param engine
     */
    public static void activitiProcessInstance(ProcessEngine engine){

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
    public static void activitiGroupOper(ProcessEngine engine){
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
    public static void activitiDeployment(ProcessEngine engine) throws FileNotFoundException {
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
    public static void activitiDeployment2(ProcessEngine engine){

        RepositoryService rs = engine.getRepositoryService();
        DeploymentBuilder db = rs.createDeployment();

        BpmnModel bm = new BpmnModel();

        db.addBpmnModel("MyCodeProcess", createProcessModel()).name("MyCodeDeploy").deploy();


    }

    /**
     * 创建BpmnModel
     * @return
     */
    public static BpmnModel createProcessModel() {
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
    public static void activitiProcessCache(ProcessEngine engine){

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
    public static void activitiTaskCandidateUser(ProcessEngine engine){
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
    public static void activitiTaskOwnerUser(ProcessEngine engine){
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
    public static void activitiTaskVar(ProcessEngine engine){
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
    public static void activitiTaskVarLocal(ProcessEngine engine){

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
    public static void activitiProcessInstanceStart(ProcessEngine engine){
        // 得到流程存储服务组件
        RepositoryService repositoryService = engine.getRepositoryService();
        // 得到运行时服务组件
        RuntimeService runtimeService = engine.getRuntimeService();
        // 获取流程任务组件
        TaskService taskService = engine.getTaskService();
        // 部署流程文件
//        Deployment deployment = repositoryService.createDeployment()
//                .addClasspathResource("bpmn/First.bpmn").deploy();
        Deployment deployment = repositoryService.createDeployment()
                .addClasspathResource("bpmn/multi.bpmn").deploy();

        // 启动流程
        ProcessDefinition pd = repositoryService.createProcessDefinitionQuery().deploymentId(deployment.getId()).singleResult();

//        ProcessInstance processInstance = runtimeService.startProcessInstanceById(pd.getId());

        //startProcessInstanceByKey
        //startProcessInstanceByMessage
        ProcessInstance processInstance = runtimeService.startProcessInstanceById(pd.getId(), "business_key");

        System.out.println(processInstance.getId());
    }




    /**
     * 流程启动2
     * @param engine
     */
    public static void activitiProcessInstanceStart2(ProcessEngine engine){
        // 得到流程存储服务组件
        RepositoryService repositoryService = engine.getRepositoryService();
        // 得到运行时服务组件
        RuntimeService runtimeService = engine.getRuntimeService();
        // 获取流程任务组件
        TaskService taskService = engine.getTaskService();
        // 部署流程文件
//        Deployment deployment = repositoryService.createDeployment()
//                .addClasspathResource("bpmn/First.bpmn").deploy();
        Deployment deployment = repositoryService.createDeployment()
                .addClasspathResource("bpmn/scope.bpmn").deploy();

        // 启动流程
        ProcessDefinition pd = repositoryService.createProcessDefinitionQuery().deploymentId(deployment.getId()).singleResult();

        //startProcessInstanceByKey
        //startProcessInstanceByMessage
        ProcessInstance processInstance = runtimeService.startProcessInstanceById(pd.getId(), "business_key");

        System.out.println(processInstance.getId());

        List<Task> tasks = taskService.createTaskQuery().processInstanceId(processInstance.getId()).list();

        for (Task task : tasks){
            System.out.println("taskid:"+task.getId()+"-"+task.getName());
            Execution execution = runtimeService.createExecutionQuery().executionId(task.getExecutionId()).singleResult();

            if("TaskA".equals(task.getName())){
                runtimeService.setVariableLocal(execution.getId(), "taskVarA", "varA");
                //用下面的TaskService也可行
//                taskService.setVariableLocal(task.getId(), "taskVarA", "varA");
            }else if("TaskB".equals(task.getName())){
                runtimeService.setVariable(execution.getId(), "taskVarB", "varB");
//                taskService.setVariable(task.getId(), "taskVarB", "varB");
            }
            taskService.complete(task.getId());
        }

        tasks = taskService.createTaskQuery().processInstanceId(processInstance.getId()).list();

        for(Task task : tasks){
            System.out.println("taskid:"+task.getId()+"-"+task.getName());
            Execution execution = runtimeService.createExecutionQuery().executionId(task.getExecutionId()).singleResult();
//            System.out.println("taskVarA:"+runtimeService.getVariableLocal(execution.getId(), "taskVarA"));
//            System.out.println("taskVarB:"+runtimeService.getVariable(execution.getId(), "taskVarB"));
            System.out.println("taskVarA:"+taskService.getVariableLocal(task.getId(), "taskVarA"));
            System.out.println("taskVarB:"+taskService.getVariable(task.getId(), "taskVarB"));
        }

        System.out.println(processInstance.getId());
    }


    /**
     * 流程操作
     * @param engine
     */
    public static void activitiProcessInstanceOper(ProcessEngine engine){
        // 得到流程存储服务组件
        RepositoryService repositoryService = engine.getRepositoryService();
        // 得到运行时服务组件
        RuntimeService runtimeService = engine.getRuntimeService();
        // 获取流程任务组件
        TaskService taskService = engine.getTaskService();
        // 部署流程文件
//        Deployment deployment = repositoryService.createDeployment()
//                .addClasspathResource("bpmn/First.bpmn").deploy();
        Deployment deployment = repositoryService.createDeployment()
                .addClasspathResource("bpmn/First.bpmn").deploy();

        // 启动流程
        ProcessDefinition pd = repositoryService.createProcessDefinitionQuery().deploymentId(deployment.getId()).singleResult();

//        ProcessInstance processInstance = runtimeService.startProcessInstanceById(pd.getId());

        //startProcessInstanceByKey
        //startProcessInstanceByMessage
        ProcessInstance processInstance = runtimeService.startProcessInstanceById(pd.getId(), "business_key");

        //查询当前的执行流
        Execution execution = runtimeService.createExecutionQuery().processInstanceId(processInstance.getId()).onlyChildExecutions().singleResult();

        System.out.println(processInstance.getId()+";当前节点:"+execution.getActivityId());

        runtimeService.trigger(execution.getId());

        //查询当前的执行流
        execution = runtimeService.createExecutionQuery().processInstanceId(processInstance.getId()).onlyChildExecutions().singleResult();
        System.out.println(processInstance.getId()+";当前节点:"+execution.getActivityId());
    }

    /**
     * 权限数据查询
     * @param engine
     */
    public static void activitiCandidateQuery(ProcessEngine engine){
        // 获取身份服务组件
        IdentityService identityService = engine.getIdentityService();
        // 新建用户
        User user = creatUser(identityService, "user1", "张三", "last", "abc@163.com", "123");
        // 新建用户组
        Group groupA = createGroup(identityService, "group1", "经理组", "manager");
        Group groupB = createGroup(identityService, "group2", "员工组", "employee");
        // 获取任务服务组件
        TaskService taskService = engine.getTaskService();
        //保存第一个Task
        Task task1 = taskService.newTask("task1");
        task1.setName("申请假期");
        taskService.saveTask(task1);
        //保存第二个Task
        Task task2 = taskService.newTask("task2");
        task2.setName("审批假期");
        taskService.saveTask(task2);
        //绑定权限
        taskService.addCandidateGroup(task1.getId(), groupA.getId());
        taskService.addCandidateGroup(task2.getId(), groupB.getId());
        taskService.addCandidateUser(task2.getId(), user.getId());
        //根据用户组查询任务
        List<Task> tasks = taskService.createTaskQuery().taskCandidateGroup(groupA.getId()).list();
        System.out.println("经理组的候选任务有：");
        for (Task task : tasks) {
            System.out.println("   " + task.getName());
        }
        //根据用户查询任务
        tasks = taskService.createTaskQuery().taskCandidateUser(user.getId()).list();
        System.out.println("张三的候选任务有");
        for (Task task : tasks) {
            System.out.println("   " + task.getName());
        }
        //调用taskCandidateGroupIn
        List<String> groupIds = new ArrayList<String>();
        groupIds.add(groupA.getId());
        groupIds.add(groupB.getId());
        tasks = taskService.createTaskQuery().taskCandidateGroupIn(groupIds).list();
        System.out.println("经理组与员工组的任务有：");
        for (Task task : tasks) {
            System.out.println("   " + task.getName());
        }
        //查询权限数据
        List<IdentityLink> links = taskService.getIdentityLinksForTask(tasks.get(0).getId());
        System.out.println("关系数据量: " + links.size());

    }

    /**
     * 流程删除
     * @param engine
     */
    public static void activitiProcessInstanceDelete(ProcessEngine engine){
        // 得到流程存储服务组件
        RepositoryService repositoryService = engine.getRepositoryService();
        // 得到运行时服务组件
        RuntimeService runtimeService = engine.getRuntimeService();
        // 获取流程任务组件
        TaskService taskService = engine.getTaskService();
        // 部署流程文件
//        Deployment deployment = repositoryService.createDeployment()
//                .addClasspathResource("bpmn/First.bpmn").deploy();
        Deployment deployment = repositoryService.createDeployment()
                .addClasspathResource("bpmn/First.bpmn").deploy();

        // 启动流程
        ProcessDefinition pd = repositoryService.createProcessDefinitionQuery().deploymentId(deployment.getId()).singleResult();

//        ProcessInstance processInstance = runtimeService.startProcessInstanceById(pd.getId());

        //startProcessInstanceByKey
        //startProcessInstanceByMessage
        ProcessInstance processInstance = runtimeService.startProcessInstanceById(pd.getId(), "business_key");

        long count = runtimeService.createProcessInstanceQuery().count();
        System.out.println("启动流程数量："+count);

        runtimeService.deleteProcessInstance(processInstance.getId(), "process delete");

        count = runtimeService.createProcessInstanceQuery().count();
        System.out.println("启动流程数量："+count);
    }


    /**
     * 流程异步job
     * @param engine
     */
    public static void activitiProcessInstanceAsyncTask(ProcessEngine engine){
        engine.getProcessEngineConfiguration().setAsyncExecutorActivate(true);
        // 得到流程存储服务组件
        RepositoryService repositoryService = engine.getRepositoryService();
        // 得到运行时服务组件
        RuntimeService runtimeService = engine.getRuntimeService();
        // 获取流程任务组件
        TaskService taskService = engine.getTaskService();
        // 部署流程文件
//        Deployment deployment = repositoryService.createDeployment()
//                .addClasspathResource("bpmn/First.bpmn").deploy();
        Deployment deployment = repositoryService.createDeployment()
                .addClasspathResource("bpmn/service_task.bpmn").deploy();

        // 启动流程
        ProcessDefinition pd = repositoryService.createProcessDefinitionQuery().deploymentId(deployment.getId()).singleResult();

//        ProcessInstance processInstance = runtimeService.startProcessInstanceById(pd.getId());

        //startProcessInstanceByKey
        //startProcessInstanceByMessage
        ProcessInstance processInstance = runtimeService.startProcessInstanceById(pd.getId(), "activitiProcessInstanceAsyncTask");

        System.out.println("执行流id："+processInstance.getId());

        long count = runtimeService.createProcessInstanceQuery().count();
        System.out.println("启动流程数量："+count);

    }

    /**
     * 将用户组数据保存到数据库中
     * @param identityService
     * @param id
     * @param name
     * @param type
     * @return
     */
    private static Group createGroupcreateGroup(IdentityService identityService, String id,
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
     * @param identityService
     * @param id
     * @param first
     * @param last
     * @param email
     * @param passwd
     * @return
     */
    private static User creatUser(IdentityService identityService, String id, String first,
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
