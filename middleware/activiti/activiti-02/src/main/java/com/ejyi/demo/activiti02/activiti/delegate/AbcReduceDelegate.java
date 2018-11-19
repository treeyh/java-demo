package com.ejyi.demo.activiti02.activiti.delegate;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;

/**
 * @author 余海
 * @version 1.0
 * @description 描述
 * @create 2018-11-19 3:15 PM
 */
public class AbcReduceDelegate implements JavaDelegate {
    @Override
    public void execute(DelegateExecution delegateExecution) {

        System.out.println("农业银行扣款");

    }
}
