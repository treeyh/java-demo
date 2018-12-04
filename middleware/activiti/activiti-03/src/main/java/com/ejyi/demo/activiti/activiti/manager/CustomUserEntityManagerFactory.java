package com.ejyi.demo.activiti.activiti.manager;

import org.activiti.engine.impl.interceptor.Session;
import org.activiti.engine.impl.interceptor.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author 余海
 * @version 1.0
 * @description 自定义user的管理工厂类
 * @create 2018-12-04 6:07 PM
 */
@Service
public class CustomUserEntityManagerFactory implements SessionFactory {

    @Resource
    private CustomUserEntityManager customUserEntityManager;

    @Override
    public Class<?> getSessionType() {
        //此处也必须为activiti原生类

        return UserIdentityManager.class;
    }

    @Override
    public Session openSession() {
        return customUserEntityManager;
    }

    @Autowired
    public void setCustomUserEntityManager(CustomUserEntityManager customUserEntityManager){
        this.customUserEntityManager=customUserEntityManager;
    }
}
