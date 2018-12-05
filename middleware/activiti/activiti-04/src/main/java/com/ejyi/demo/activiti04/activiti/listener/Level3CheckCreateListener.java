package com.ejyi.demo.activiti04.activiti.listener;

import com.ejyi.demo.activiti04.activiti.ActivitiConstants;
import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.delegate.TaskListener;

/**
 * @author 余海
 * @version 1.0
 * @description 描述
 * @create 2018-12-05 5:38 PM
 */
public class Level3CheckCreateListener implements TaskListener {

    @Override
    public void notify(DelegateTask delegateTask) {

        System.out.println("Level3CheckCreateListener:"+ delegateTask.getId());
        System.out.println("Level3CheckCreateListener:"+ delegateTask.getName());
        System.out.println("Level3CheckCreateListener:"+ delegateTask.getAssignee());


        String adminUserId = delegateTask.getVariable(ActivitiConstants.adminUserId).toString();
        System.out.println("adminUserId:"+adminUserId);
        if("1".equals(adminUserId)){
            delegateTask.setAssignee("6");
            delegateTask.setVariable(ActivitiConstants.checkUsers, delegateTask.getVariable(ActivitiConstants.checkUsers).toString()+","+"6");
        }else{
            delegateTask.setAssignee("7");
            delegateTask.setVariable(ActivitiConstants.checkUsers, delegateTask.getVariable(ActivitiConstants.checkUsers).toString()+","+"7");
        }

    }
}
