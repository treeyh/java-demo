package com.ejyi.demo.activiti04.activiti.manager;

import com.ejyi.demo.activiti04.dao.RolePoMapper;
import com.ejyi.demo.activiti04.dao.RoleUserPoMapper;
import com.ejyi.demo.activiti04.dao.UserPoMapper;
import com.ejyi.demo.activiti04.dao.po.RolePo;
import com.ejyi.demo.activiti04.dao.po.RoleUserPo;
import com.ejyi.demo.activiti04.dao.po.UserPo;
import org.activiti.engine.identity.Group;
import org.activiti.engine.impl.cfg.ProcessEngineConfigurationImpl;
import org.activiti.engine.impl.persistence.entity.UserEntity;
import org.activiti.engine.impl.persistence.entity.UserEntityManagerImpl;
import org.activiti.engine.impl.persistence.entity.data.UserDataManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 余海
 * @version 1.0
 * @description 自定义用户管理类，管理用户方法, 添加其他方法
 * @create 2018-12-04 5:14 PM
 */
public class CustomUserEntityManager extends UserEntityManagerImpl {
    @Autowired
    private UserPoMapper userPoMapper;
    @Autowired
    private RolePoMapper rolePoMapper;
    @Autowired
    private RoleUserPoMapper roleUserPoMapper;

    public CustomUserEntityManager(ProcessEngineConfigurationImpl processEngineConfiguration, UserDataManager userDataManager) {
        super(processEngineConfiguration, userDataManager);
    }

    public CustomUserEntityManager(ProcessEngineConfigurationImpl processEngineConfiguration, UserDataManager userDataManager, UserPoMapper userPoMapper, RolePoMapper rolePoMapper, RoleUserPoMapper roleUserPoMapper) {
        this(processEngineConfiguration, userDataManager);
        this.userPoMapper = userPoMapper;
        this.rolePoMapper = rolePoMapper;
        this.roleUserPoMapper = roleUserPoMapper;
    }


    @Override
    public UserEntity findById(String entityId) {
        UserPo userPo = userPoMapper.selectByPrimaryKey(entityId);
        return ActivitiUserUtils.toActivitiUser(userPo);
    }

    @Override
    public List<Group> findGroupsByUser(final String userId) {
        if (userId == null) {
            return null;
        }
        List<RolePo> roleList = new ArrayList<RolePo>();
        List<RoleUserPo> userRoleList = roleUserPoMapper.selectByUserId(userId);
        for (RoleUserPo roleUserPo : userRoleList) {
            String roleId = roleUserPo.getRoleId();
            RolePo role = rolePoMapper.selectByPrimaryKey(roleId);
            roleList.add(role);
        }
        List<Group> gs = ActivitiUserUtils.toActivitiGroups(roleList);
        return gs;
    }
}
