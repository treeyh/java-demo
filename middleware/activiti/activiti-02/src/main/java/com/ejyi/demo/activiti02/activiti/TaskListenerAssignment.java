package com.ejyi.demo.activiti02.activiti;

import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.delegate.TaskListener;

/**
 * @author 余海
 * @version 1.0
 * @description 描述
 * @create 2018-11-19 7:51 PM
 */
public class TaskListenerAssignment implements TaskListener {

    @Override
    public void notify(DelegateTask delegateTask) {
        System.out.println("指定代理人触发的...");
    }
}
