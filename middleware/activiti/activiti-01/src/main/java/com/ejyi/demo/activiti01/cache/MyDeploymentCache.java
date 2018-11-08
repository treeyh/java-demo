package com.ejyi.demo.activiti01.cache;

import org.activiti.engine.impl.persistence.deploy.DefaultDeploymentCache;

import java.util.Map;

/**
 * @author 余海
 * @version 1.0
 * @description 描述
 * @create 2018-11-08 3:50 PM
 */
public class MyDeploymentCache<T> extends DefaultDeploymentCache<T> {


    public Map<String, T> getCache(){
        return this.cache;
    }
}