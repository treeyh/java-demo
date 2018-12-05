package com.ejyi.demo.activiti04.activiti.delegate;

import com.ejyi.demo.activiti04.activiti.ActivitiConstants;
import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;

/**
 * @author 余海
 * @version 1.0
 * @description 描述
 * @create 2018-12-05 6:14 PM
 */
public class CheckCompleteDelegate implements JavaDelegate {


    @Override
    public void execute(DelegateExecution execution) {

        String checkResult = execution.getVariable(ActivitiConstants.checkResult).toString();
        String checkUsers = execution.getVariable(ActivitiConstants.checkUsers).toString();


        System.out.println("审核人列表："+ checkUsers);
        System.out.println("审核结果："+ checkResult+";后续执行相应操作.");




    }
}
