package com.ejyi.demo.activiti.activiti.manager;

import org.activiti.engine.impl.cfg.ProcessEngineConfigurationImpl;
import org.activiti.engine.impl.persistence.entity.MembershipEntity;
import org.activiti.engine.impl.persistence.entity.data.impl.MybatisMembershipDataManager;

/**
 * @author 余海
 * @version 1.0
 * @description 描述
 * @create 2018-12-04 8:04 PM
 */
public class CustomRoleUserEntityManager extends MybatisMembershipDataManager {

    public CustomRoleUserEntityManager(ProcessEngineConfigurationImpl processEngineConfiguration) {
        super(processEngineConfiguration);
    }

    @Override
    public Class<? extends MembershipEntity> getManagedEntityClass() {
        return super.getManagedEntityClass();
    }

    @Override
    public MembershipEntity create() {
        return super.create();
    }

    @Override
    public void deleteMembership(String userId, String groupId) {
        super.deleteMembership(userId, groupId);
    }

    @Override
    public void deleteMembershipByGroupId(String groupId) {
        super.deleteMembershipByGroupId(groupId);
    }

    @Override
    public void deleteMembershipByUserId(String userId) {
        super.deleteMembershipByUserId(userId);
    }
}
