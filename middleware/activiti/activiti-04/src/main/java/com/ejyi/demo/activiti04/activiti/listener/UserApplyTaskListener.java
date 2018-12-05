package com.ejyi.demo.activiti04.activiti.listener;

import com.ejyi.demo.activiti04.activiti.ActivitiConstants;
import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.delegate.TaskListener;

/**
 * @author 余海
 * @version 1.0
 * @description 描述
 * @create 2018-12-05 4:07 PM
 */
public class UserApplyTaskListener implements TaskListener {


    @Override
    public void notify(DelegateTask delegateTask) {

        System.out.println("UserApplyTaskListener:"+ delegateTask.getId());
        System.out.println("UserApplyTaskListener:"+ delegateTask.getName());
        System.out.println("UserApplyTaskListener:"+ delegateTask.getAssignee());

        Integer classHours = Integer.parseInt(delegateTask.getVariable(ActivitiConstants.classHours).toString());

        if(classHours > ActivitiConstants.maxFreeCheck){
            delegateTask.setVariable(ActivitiConstants.level1, 1);
        }else{
            delegateTask.setVariable(ActivitiConstants.level1, 0);
        }
        System.out.println("任务创建完成. classHours:"+classHours);
    }
}
