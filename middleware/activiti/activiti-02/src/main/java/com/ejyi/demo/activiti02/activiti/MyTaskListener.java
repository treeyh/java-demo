package com.ejyi.demo.activiti02.activiti;

import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.delegate.TaskListener;
import org.activiti.engine.impl.el.FixedValue;

/**
 * @author 余海
 * @version 1.0
 * @description 描述
 * @create 2018-11-19 7:45 PM
 */
public class MyTaskListener implements TaskListener {
    private FixedValue userName;


    public FixedValue getUserName() {
        return userName;
    }

    public void setUserName(FixedValue userName) {
        this.userName = userName;
    }

    @Override
    public void notify(DelegateTask delegateTask) {
        System.out.println("任务监听器..." + this.userName.getExpressionText());
    }
}
