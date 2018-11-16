package com.ejyi.demo.activiti02.activiti.delegate;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;

/**
 * @author 余海
 * @version 1.0
 * @description 描述
 * @create 2018-11-16 2:24 PM
 */
public class CompensationDelegate implements JavaDelegate {
    @Override
    public void execute(DelegateExecution delegateExecution) {
        System.out.println("transation delegate 补偿处理");
    }
}
