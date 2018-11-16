package com.ejyi.demo.activiti02.activiti.delegate;

import org.activiti.engine.delegate.BpmnError;
import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;

/**
 * @author 余海
 * @version 1.0
 * @description 描述
 * @create 2018-11-15 9:28 PM
 */
public class CountDelegate implements JavaDelegate {

    @Override
    public void execute(DelegateExecution delegateExecution) {
        System.out.println("清点人数：抛出异常.");
        throw new BpmnError("countErrorCode");
    }
}
