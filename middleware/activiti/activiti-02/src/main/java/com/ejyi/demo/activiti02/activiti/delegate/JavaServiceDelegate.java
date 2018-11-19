package com.ejyi.demo.activiti02.activiti.delegate;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;

import java.io.Serializable;

/**
 * @author 余海
 * @version 1.0
 * @description 描述
 * @create 2018-11-19 4:44 PM
 */
public class JavaServiceDelegate implements JavaDelegate, Serializable {

    @Override
    public void execute(DelegateExecution delegateExecution) {
        System.out.println("JavaServiceDelegate execute...");
    }
}
