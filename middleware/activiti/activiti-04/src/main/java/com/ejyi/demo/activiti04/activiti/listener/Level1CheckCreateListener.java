package com.ejyi.demo.activiti04.activiti.listener;

import com.ejyi.demo.activiti04.activiti.ActivitiConstants;
import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.delegate.TaskListener;

/**
 * @author 余海
 * @version 1.0
 * @description 描述
 * @create 2018-12-05 5:14 PM
 */
public class Level1CheckCreateListener implements TaskListener {

    @Override
    public void notify(DelegateTask delegateTask) {

        System.out.println("Level1CheckCreateListener:"+ delegateTask.getId());
        System.out.println("Level1CheckCreateListener:"+ delegateTask.getName());
        System.out.println("Level1CheckCreateListener:"+ delegateTask.getAssignee());


        String adminUserId = delegateTask.getVariable(ActivitiConstants.adminUserId).toString();
        System.out.println("adminUserId:"+adminUserId);
        if("1".equals(adminUserId)){
            delegateTask.setAssignee("2");
            delegateTask.setVariable(ActivitiConstants.checkUsers, "2");
        }else{
            delegateTask.setAssignee("3");
            delegateTask.setVariable(ActivitiConstants.checkUsers, "3");
        }

    }
}
