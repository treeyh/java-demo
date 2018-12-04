package com.ejyi.demo.activiti.activiti.manager;

import com.ejyi.demo.activiti.dao.RolePoMapper;
import com.ejyi.demo.activiti.dao.RoleUserPoMapper;
import com.ejyi.demo.activiti.dao.UserPoMapper;
import com.ejyi.demo.activiti.dao.po.RolePo;
import com.ejyi.demo.activiti.dao.po.RoleUserPo;
import com.ejyi.demo.activiti.dao.po.UserPo;
import org.activiti.engine.identity.Group;
import org.activiti.engine.identity.User;
import org.activiti.engine.impl.cfg.ProcessEngineConfigurationImpl;
import org.activiti.engine.impl.persistence.entity.GroupEntity;
import org.activiti.engine.impl.persistence.entity.UserEntity;
import org.activiti.engine.impl.persistence.entity.UserEntityImpl;
import org.activiti.engine.impl.persistence.entity.UserEntityManager;
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
@Component
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


    @Override
    public UserEntity findUserById(String userId){
        UserEntity userEntity = new UserEntityImpl();
        UserPo userPo = userPoMapper.selectByPrimaryKey(userId);
        //将自定义的user转化为activiti的类
        userEntity = ActivitiUserUtils.toActivitiUser(userPo);
        //返回activiti的实体类
        return userEntity;
    }

    @Override
    public List<Group> findGroupsByUser(final String userId) {
        if(userId==null){
            return null;
        }
        List<RolePo> roleList=new ArrayList<RolePo>();
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