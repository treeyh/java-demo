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
public class Level2CheckCompleteListener  implements TaskListener {


    @Override
    public void notify(DelegateTask delegateTask) {
        System.out.println("Level2CheckCompleteListener..task.id:" + delegateTask.getId() + ";task.name:" +
                delegateTask.getName() + ";task.assignee:" + delegateTask.getAssignee() + ";task.checkResult:" +
                delegateTask.getVariable(ActivitiConstants.checkResult));

        Integer classHours = Integer.parseInt(delegateTask.getVariable(ActivitiConstants.classHours).toString());

        if(classHours > ActivitiConstants.maxCheck2){
            delegateTask.setVariable(ActivitiConstants.level3, 1);
        }else{
            delegateTask.setVariable(ActivitiConstants.level3, 0);
        }
        System.out.println("二级审批完成. classHours:"+classHours);
    }


}
