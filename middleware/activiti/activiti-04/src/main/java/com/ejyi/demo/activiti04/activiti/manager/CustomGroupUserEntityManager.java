package com.ejyi.demo.activiti04.activiti.manager;

import com.ejyi.demo.activiti04.dao.RolePoMapper;
import com.ejyi.demo.activiti04.dao.RoleUserPoMapper;
import com.ejyi.demo.activiti04.dao.UserPoMapper;
import org.activiti.engine.impl.cfg.ProcessEngineConfigurationImpl;
import org.activiti.engine.impl.persistence.entity.MembershipEntity;
import org.activiti.engine.impl.persistence.entity.MembershipEntityManagerImpl;
import org.activiti.engine.impl.persistence.entity.data.DataManager;
import org.activiti.engine.impl.persistence.entity.data.MembershipDataManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author 余海
 * @version 1.0
 * @description 描述
 * @create 2018-12-05 12:12 PM
 */
public class CustomGroupUserEntityManager extends MembershipEntityManagerImpl {

    @Autowired
    private UserPoMapper userPoMapper;
    @Autowired
    private RolePoMapper rolePoMapper;
    @Autowired
    private RoleUserPoMapper roleUserPoMapper;


    public CustomGroupUserEntityManager(ProcessEngineConfigurationImpl processEngineConfiguration, MembershipDataManager membershipDataManager) {
        super(processEngineConfiguration, membershipDataManager);
    }

    @Override
    protected DataManager<MembershipEntity> getDataManager() {
        return super.getDataManager();
    }

    @Override
    public void createMembership(String userId, String groupId) {
        super.createMembership(userId, groupId);
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

    @Override
    public MembershipDataManager getMembershipDataManager() {
        return super.getMembershipDataManager();
    }

    @Override
    public void setMembershipDataManager(MembershipDataManager membershipDataManager) {
        super.setMembershipDataManager(membershipDataManager);
    }
}
