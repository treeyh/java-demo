package com.ejyi.demo.activiti02.activiti.delegate;

import org.activiti.engine.delegate.BpmnError;
import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;

/**
 * @author 余海
 * @version 1.0
 * @description 描述
 * @create 2018-11-20 10:23 AM
 */
public class ErrorJavaDelegate implements JavaDelegate {
    @Override
    public void execute(DelegateExecution delegateExecution) {
        System.out.println("子流程错误");
        throw  new BpmnError("子流程异常");
    }
}
