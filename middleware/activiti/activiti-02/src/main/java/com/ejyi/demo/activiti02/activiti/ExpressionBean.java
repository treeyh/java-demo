package com.ejyi.demo.activiti02.activiti;

import org.activiti.engine.runtime.Execution;

import java.io.Serializable;

/**
 * @author 余海
 * @version 1.0
 * @description 描述
 * @create 2018-11-19 5:01 PM
 */
public class ExpressionBean implements Serializable {

    private String name = "userA";

    public String getName(){
        return name;
    }

    public void print(Execution execution){
        System.out.println("执行print方法，执行流id："+ execution.getId());
    }
}
