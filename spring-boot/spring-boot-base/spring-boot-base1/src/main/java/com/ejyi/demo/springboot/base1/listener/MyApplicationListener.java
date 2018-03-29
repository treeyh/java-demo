package com.ejyi.demo.springboot.base1.listener;

import org.springframework.boot.context.event.ApplicationEnvironmentPreparedEvent;
import org.springframework.boot.context.event.ApplicationPreparedEvent;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextClosedEvent;

//放开下面注解即能加入监听器
//@Component
public class MyApplicationListener implements ApplicationListener<ApplicationEvent> {

    @Override
    public void onApplicationEvent(ApplicationEvent event) {
        if (event instanceof ApplicationStartedEvent) {
            System.out.println("监听：ApplicationStartedEvent:"+event.getClass().getName());
        }
        else if (event instanceof ApplicationEnvironmentPreparedEvent) {
            System.out.println("监听：ApplicationEnvironmentPreparedEvent:"+event.getClass().getName());
        }
        else if (event instanceof ApplicationPreparedEvent) {
            System.out.println("监听：ApplicationPreparedEvent:"+event.getClass().getName());
        }
        else if (event instanceof ContextClosedEvent && ((ContextClosedEvent) event)
                .getApplicationContext().getParent() == null) {
            System.out.println("监听：ContextClosedEvent:"+event.getClass().getName());
        }

        if(event instanceof ContextClosedEvent){

        }

        System.out.println("监听：:"+event.getClass().getName());
    }
}