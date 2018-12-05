package com.ejyi.demo.activiti04.activiti.listener;

import com.ejyi.demo.activiti04.activiti.ActivitiConstants;
import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.delegate.TaskListener;

/**
 * @author 余海
 * @version 1.0
 * @description 描述
 * @create 2018-12-05 5:36 PM
 */
public class Level1CheckCompleteListener implements TaskListener {


    @Override
    public void notify(DelegateTask delegateTask) {
        System.out.println("Level1CheckCompleteListener:getId:"+ delegateTask.getId());
        System.out.println("Level1CheckCompleteListener:getName:"+ delegateTask.getName());
        System.out.println("Level1CheckCompleteListener:getAssignee:"+ delegateTask.getAssignee());
        System.out.println("Level1CheckCompleteListener:checkResult:"+ delegateTask.getVariable(ActivitiConstants.checkResult));

        Integer classHours = Integer.parseInt(delegateTask.getVariable(ActivitiConstants.classHours).toString());

        if(classHours > ActivitiConstants.maxFreeCheck){
            delegateTask.setVariable(ActivitiConstants.level2, 1);
        }else{
            delegateTask.setVariable(ActivitiConstants.level2, 0);
        }
        System.out.println("一级审批完成. classHours:"+classHours);
    }


}
