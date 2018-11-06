package com.ejyi.demo.activiti01;

//import org.activiti.engine.ProcessEngine;
//import org.activiti.engine.ProcessEngines;
//import org.activiti.engine.RepositoryService;
//import org.activiti.engine.RuntimeService;
//import org.activiti.engine.TaskService;
//import org.activiti.engine.task.Task;

/**
 * @author tree.yu
 * @version 1.0
 * @description 描述
 * @create 2018-11-04 22:03
 */
public class FirstAct {

    public static void main(String[] args) {
//        // 创建流程引擎
//        ProcessEngine engine = ProcessEngines.getDefaultProcessEngine();
//        // 得到流程存储服务组件
//        RepositoryService repositoryService = engine.getRepositoryService();
//        // 得到运行时服务组件
//        RuntimeService runtimeService = engine.getRuntimeService();
//        // 获取流程任务组件
//        TaskService taskService = engine.getTaskService();
//        // 部署流程文件
//        repositoryService.createDeployment()
//                .addClasspathResource("bpmn/First.bpmn").deploy();
//        // 启动流程
//        runtimeService.startProcessInstanceByKey("process1");
//        // 查询第一个任务
//        Task task = taskService.createTaskQuery().singleResult();
//        System.out.println("第一个任务完成前，当前任务名称：" + task.getName());
//        // 完成第一个任务
//        taskService.complete(task.getId());
//        // 查询第二个任务
//        task = taskService.createTaskQuery().singleResult();
//        System.out.println("第二个任务完成前，当前任务名称：" + task.getName());
//        // 完成第二个任务（流程结束）
//        taskService.complete(task.getId());
//        task = taskService.createTaskQuery().singleResult();
//        System.out.println("流程结束后，查找任务：" + task);
//        // 退出
//        System.exit(0);
    }
}
